## Aula 10

### Pair RDD

Chave-Valor

Criar Pair-RDDs a partir de tuplas

Cirar Pair-RDDs a partir de RDD

Filter podem ser aplicados sobre chave ou valor da tupla (Indice 0 e 1)

mapValues rodam somente sobre o valor da tupla (Indice 1)

Agregação reduceByKey **Não é uma operação, é transformação**
cada reduce irá resultar um valor por chave

Agrupamento por Chave
groupByKey gera uma tupla no estilo (Chave, Iterable<Value>)

Ordernar por chaves sortByKey(boolean) # true ascending
