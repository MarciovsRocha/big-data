# install some dependency packages 
apt update ; apt upgrade -y 
cat packages.txt | xargs sudo apt install -y
# -------------------------------------
# creating haddop user 
useradd -rm -d /home/hadoop -s /bin/bash -g root -G sudo -u 1001 hadoop
echo '\nhadoop ALL=(ALL:ALL) NOPASSWD:ALL\n' >> /etc/sudoers
# creating an rsa-Key for hadoop user
su - hadoop
ssh-keygen -t rsa
cat ~/.ssh/id_rsa.pub >> ~/.ssh/authorized_keys 
chmod 640 ~/.ssh/authorized_keys 
# -------------------------------------
# downloading hadoop
wget https://downloads.apache.org/hadoop/common/hadoop-3.3.0/hadoop-3.3.0.tar.gz 
tar -xvzf hadoop-3.3.0.tar.gz 
mv hadoop-3.3.0 hadoop 
# -------------------------------------
# creating PATH variables for hadoop user
cat variables.txt >> ~/.bashrc
source ~/.bashrc 
# configuring hadoop
mkdir -p ~/home/hadoop/hdfs/namenode
mkdir -p ~/home/hadoop/hdfs/datanode
# -------------------------------------
# alter core-site.xml
mv $HADOOP_HOME/etc/hadoop/core-site.xml $HADOOP_HOME/etc/hadoop/core-site.xml_bkp
echo "<?xml version=\"1.0\" encoding=\"UTF-8\"?>                    \
<?xml-stylesheet type=\"text/xsl\" href=\"configuration.xsl\"?>     \
<configuration>                                                     \
        <property>                                                  \
                <name>fs.defaultFS</name>                           \
                <value>hdfs://hadoop.$(hostname):9000</value>       \
        </property>                                                 \
</configuration>" > $HADOOP_HOME/etc/hadoop/core-site.xml
# -------------------------------------
# alter hdfs-site.xml
mv $HADOOP_HOME/etc/hadoop/hdfs-site.xml $HADOOP_HOME/etc/hadoop/hdfs-site.xml_bkp
echo "<?xml version=\"1.0\" encoding=\"UTF-8\"?>                              \
<?xml-stylesheet type=\"text/xsl\" href=\"configuration.xsl\"?>               \
<configuration>                                                               \
        <property>                                                            \
                <name>dfs.replication</name>                                  \
                <value>1</value>                                              \
        </property>                                                           \
        <property>                                                            \
                <name>dfs.name.dir</name>                                     \
                <value>file:///home/hadoop/hadoopdata/hdfs/namenode</value>   \
        </property>                                                           \
        <property>                                                            \
                <name>dfs.data.dir</name>                                     \
                <value>file:///home/hadoop/hadoopdata/hdfs/datanode</value>   \
        </property>                                                           \
</configuration>" > $HADOOP_HOME/etc/hadoop/hdfs-site.xml
# -------------------------------------
# alter mapred-site.xml
mv $HADOOP_HOME/etc/hadoop/mapred-site.xml $HADOOP_HOME/etc/hadoop/mapred-site.xml_bkp
echo "<?xml version=\"1.0\" encoding=\"UTF-8\"?>                    \
<?xml-stylesheet type=\"text/xsl\" href=\"configuration.xsl\"?>     \
<configuration>                                                     \
        <property>                                                  \
                <name>mapreduce.framework.name</name>               \
                <value>yarn</value>                                 \
        </property>                                                 \
</configuration>" > $HADOOP_HOME/etc/hadoop/mapred-site.xml
# -------------------------------------
# alter yarn-site.xml 
mv $HADOOP_HOME/etc/hadoop/yarn-site.xml $HADOOP_HOME/etc/hadoop/yarn-site.xml_bkp
echo "<?xml version=\"1.0\" encoding=\"UTF-8\"?>                    \
<?xml-stylesheet type=\"text/xsl\" href=\"configuration.xsl\"?>     \
<configuration>                                                     \
        <property>                                                  \
                <name>yarn.nodemanager.aux-services</name>          \
                <value>mapreduce_shuffle</value>                    \
        </property>                                                 \
</configuration>" > $HADOOP_HOME/etc/hadoop/yarn-site.xml
# -------------------------------------
# start hadoop cluster
hdfs namenode -format 
start-dfs.sh 
start-yarn.sh 
jps 