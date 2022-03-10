## Aula 1

Uma grande quantidade de dados tão grande e complexos que é inviável para armazenar e processar de maneira tradicional.

### Principais desafios:

* Armazenamento e gerenciametno de dados
* Arquiteturas descentralizadas
* Alta latência 
* "Gargalos" de Eficiência
* Aumento de requisitos de hardware (GPU,SSD,RAM) 

## 7 Vs de Big Data

* Volume
* Velocidade
* Variedade
* Variabilidade
* Veracidade
* Visualização
* Valor

### Volume

A característica mais óbvia

Quantidade astronômica de dados que é gerado, armazenado e processado 

### Velocidade

Velocidade com que os dados são feitos

Dados de maneira gerada sequencialmente --> Cadeia de dados

Como são processados essas cadeias de dados
* Periódico <-- Foco nesta modalidade durante a matéria
* Real time

### Variedade

Origem dos dados 

Dados Estruturados, Semi Estruturados e não estruturados

Como combinar estes tipos de dados?

**!! NÃO SÃO NECESSÁRIOS TODOS OS TIPOS DE ESTRUTURA PARA TERMOS BIG DATA !!**

### Variabilidade

**!! Não é a mesma coisa que Variedade !!**

como diferenciar entre fora da linha e desvio de conceito?

e se o dado for inconsistente?

e se o dado não tem padrão?

exemplo de variabilidade: "Tomar o mesmo café todos os dias, mas a cada dia tem um sabor diferente." 

### Veracidade

Um dos mais infelizes conceitos de Big Data

Quanto mais dados, maior é a inconsistência dos dados (menor é a confiança dos dados)

Note que isto vai contra o que diz a estatística *"Quanto maior for o exemplo, maior a probabilidade de ser verdadeiro."*

**Importante**: Os dados obtidos por pessoas e processados por pessoas.

###  Visualização

Como visualizar Big Data?

Motivos para visualização:
* Muitos dados (linhas e colunas) para carregar na RAM
* como plotar milhões/bilhões de dados?

Técnicas específicas: Clustering, tree maps, sunbusrt, parallel coordinates, cone trees

Como visualizar diferentes tipos de dados que são gerados em alta velocidade?

### Valor

Todos os outros tópicos são irrelevantes se os dados não trazem valor 

Ganhos sociais (comercial ou social)

Variabilidade de casos:
* Entendimento de clientes/segmentação
* Otimização de processo
* Identificação de fraudes

Nesta parte possui muito mais análise e/ou predição (Data Science/Machine Learning)

---

São necessários todos os V's? Não, a identificação das características não são triviais e podem ser confusas ou até conflitantes

---

## Dados e Big Data

Com a facilidade de aquisição de dados tudo está sendo armazenado

* Logs
* testos
* imagens
* vídeos
* Áudio
* etc.

Porém a maneira de trabalhar cada tipo de dado é diferente

Dados podem ser categorizados como:
* Estruturados
* Semi-estruturados
* não estruturados

### Dados Estruturados

São dados armazenados em formato de tabelas

LINHAS e COLUNAS

LINHAS == Instâncias/Exemplos/Registros
COLUNAS == Atributos/Características/Variáveis

### Dados Semi-Estruturados

Dados que não são armazenados em formato de tabela mas ainda sim possuem uma estrutura, ou seja uma estrutura irregular

Exemplo: 
* XML
* JSON

### Dados não estruturados

São dados que não possuem uma estrutura premeditada, ou seja, é necessário realizar um processamento específico nesses dados

## Modos de processamento

* Batch
  * Dados são armazenados em HDFS
  * Será realizada uma rotina
* Stream
  * Dados que tem que serem trabalhados assim que são gerados
  * facultativamente serão armazenados

## Hadoop

Quais são os objetivos do Hadoop
* Armazenamento
* Processamento

Cluster de um computador até milhares de computadores (funciona em máquinas heterogêneas)

*Máquinas heterogêneas: Computadores podem se diferenciar tanto em Sistemas Operacionais tanto em Hardware*

Pontos importantes:
* Detecção de Falhas
* Redundância

Composição do Hadoop
* Sistema distribuido de arquivos HDFS
* Escalabilidade
* MapReduce programming

Objetivos com Hadoop
* Entender HDFS
* Entender conceitos de MapReduce
* Codificar MapReduce

## Spark

Característias:
* Altamente escalável
* Aceita processamento batch e stream
* Otimizado para SQL e Machine Learning
* Programação e otimização baseado em DAGs(directed acyclic graphs)
* Flexibilidade: Java, Scala, Python e R
* Utilizaremos Python

Objetivos:
* Entender a arquiterura do spark
* Entender as estruturas principais
* Rotinas de codificação Spark
* SparkSQL
