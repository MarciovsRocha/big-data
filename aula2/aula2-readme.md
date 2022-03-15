## Aula 2 - Hadoop

### Como processar quantidades massivas de dados

Desafios: 
* Processamento é lento e não escalável
* Aquisição/Armazenamento dados em HDD é lento
* Não temos grandes quantidades de RAM disponível

Ideia: Armazenamento de dados distribuídos

Solução: Armazenar e processar dados utilizando vários computadores

Desafios:
* Sincronia
* Reabilidade
* Backup
* Custo
* Facibilidade de uso

Paralelismo não é trivial

* Coodenação
* Deadlocks
* Sincronização
* Banda de rede
* Divisão e Agregação
* Disponibilidade

### Hadoop

Armazenamento e Processamento Distribuído

base: HDFS + MapReduce

um sistema deve se auto-gerenciar e se recuperar de falhas e desastres

performance deve escalar linearmente de acordo com o número de processadores

Processamento deve estar próximo aos dados

Simples, modular e extensível

Armazenamento: Pode-se armazenar qualquer tipo de dado

Processamento: depende de cada tipo de dado

Escalabilidade: conforme adicionamos mais computadores ao cluster maior será o poder computacional

Economia: trabalha em softwares e hardwares heterogêneos

OpenSource

#### HDFS

Tolerante a falhas e irá dividir os arquivos em blocos

Arquitetura master-slave

1 Master --> n Slaves

Master = NameNode 
* Responsável por gerenciar o armazenamento HDFS 
* Armazenar metadados

Slave = DataNode
* Responsável por armazenar blocos de arquivos
* Reporta seu status para o NameNode

Secondary NameNome
* Backup do NameNode

Cada bloco é replicado e distribuído entre os computadores. (vide Fator de Replicação)

Controla o número de réplicas de blocos

Estas réplicas são cruzadas/distribuídas entre os slaves. (tolerância a falhas)

#### MapReduce

Altamente escalável

Uma vez que os dados estão divididos entre os nós, como processamos os dados? **MapReduce**

Combina programação Funcional e Orientação a Objetos

Racionalização: 
* o mesmo código vai receber o mesmo processamento de dados.
* O programador não precisa se preocupar com quantidade de blocos/computadores

Tem como origem Google

Modelo de programação simples 

Usado para processamento de Datasets massivos

Pequenos computadores podem ser utilizados para processar o dado que não seria possível ser processado por uma máquina mais potente.

**Importante**: o processamento de um bloco não pode impactar em outro, ou seja, não podem haver dependências entre os blocos

##### Modelo de programação:

* **Filter (Map)** *Executado de maneira distribuída*
* *Ordenação*
* *Shufle* 
* **Agregação (Reduce)** *Executado de maneira distribuída*

* Map function: cria uma tupla no formado chave-valor
* Reduce Function: recebe um input no formato Chave-listaDeValores 
  
Qual será a nossa chave?
Qual sera o valor na fase **Map** e **Reduce**

Definição de localização "final": $hash(key)\% R$

Arquitetura

NameNode -> JobTracker -> DataNode -> TaskTracker

JobTracker: recebe e gerencia job requests, monitorando os tasktrackers (Fica no NameNode)
TaskTracker: executa as funções MapReduce de acordo com os comandos do JobTracker (Fica no DataNode)
