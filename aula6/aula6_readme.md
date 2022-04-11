## Aula 6

### FASTA File

formato de arquivo utilizado na bio-informática oara representar sequencias ribo-nucleicas.

Este tipo de arquivo pode variar de alguns Megabytes até Gigabytes

Desafios:

* Síntese proteíca
* Compactação de dados

### Atividade

Dado um arquivo FASTA, escreva uma rotina MapReduce que:

* Ignora o cabeçalho
* Computar a entropia de cada caratere do arquivo
* Entropia é um conceito de probilidade
* A entropia de um caractere se dá pela equação:
 
$\displaystyle Entropy(X)=-p*\log_ {2}(p) \therefore p = \frac{\sum X}{\sum T}$

*T=Total de caracteres*
