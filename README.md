
# Weka Plus Grails

## Introdução ☄
O _Weka Plus Grails_ é uma aplicação (web) desenvolvida em ([Groovy](http://groovy.codehaus.org/ "http://groovy.codehaus.org/")) [Grails](http://grails.org/ "http://grails.org/") que usa algumas funções do [Weka](http://www.cs.waikato.ac.nz/ml/weka/ "http://www.cs.waikato.ac.nz/ml/weka/").

**In english**: The _Weka Plus Grails_ is an (web) aplication developed in ([Groovy](http://groovy.codehaus.org/ "http://groovy.codehaus.org/")) [Grails](http://grails.org/ "http://grails.org/") that use some [Weka](http://www.cs.waikato.ac.nz/ml/weka/ "http://www.cs.waikato.ac.nz/ml/weka/") functions.
 
This _Readme_ file is written in Brazilian Portuguese. But all the other files
of this project are in English. Enjoy!

---
## Autoria 
- **Autor do repositório**: Paulo de Lima Cavalcanti
([www.paulolimac.com](www.paulolimac.com "www.paulolimac.com"))

---
## Descrição 
Esta aplicação web foi desenvolvida para estudo da [API do Weka](http://weka.sourceforge.net/doc/ "http://weka.sourceforge.net/doc/").

São requisitos do sistema:

- uma aplicação usando técnicas de aprendizagem de máquina implementadas
no [Weka](http://www.cs.waikato.ac.nz/ml/weka/ "http://www.cs.waikato.ac.nz/ml/weka/") contemplando os seguintes requisitos.

	- Requisito 1: Leitura de arquivo de instâncias - O sistema deve conter uma rotina para leitura
de instâncias de treinamento previamente armazenadas em [arquivo ARFF](http://weka.wikispaces.com/ARFF "http://weka.wikispaces.com/ARFF").

	- Requisito 2: Treinamento do algoritmo de aprendizado – A partir das instâncias lidas do arquivo, o sistema deverá realizar o treinamento de um classificador do [Weka](http://www.cs.waikato.ac.nz/ml/weka/ "http://www.cs.waikato.ac.nz/ml/weka/"). A escolha do classificador e dos parâmetros de treinamento é livre.

	- Requisito 3: Armazenar classificador gerado – O sistema deverá salvar em arquivo o
classificador treinado, para posterior uso em novas instâncias.

	- Requisito 4: Uso do classificador para novas instâncias – O sistema deverá fazer a leitura do classificador treinado e ser usado em novas instâncias não vistas no treinamento. As novas instâncias poderão ser fornecidas manualmente pelo usuário em uma interface ou lidas de um [arquivo ARFF](http://weka.wikispaces.com/ARFF "http://weka.wikispaces.com/ARFF").

Este é um prototipo. Falhas e implementações estão presentes.

---
## Instalação 
### Requisitos/Recursos utilizados ⚙
Basicamente, deve-se ter o [Grails](http://grails.org/ "http://grails.org/") instalado em máquina e a [API do Weka](http://weka.sourceforge.net/doc/ "http://weka.sourceforge.net/doc/") para disponibilizar as funções.

Com detalhes, foram utilizados os seguintes recursos:

- **Programação**
	- [Grails](http://grails.org/ "http://grails.org/"): como framework para desenvolvimento.
		- [Groovy](http://groovy.codehaus.org/ "http://groovy.codehaus.org/"): linguagem implícita do framework
		- [Navigation Plugin](http://grails.org/plugin/navigation "http://grails.org/plugin/navigation"): plugin para gerar menus nas *.gsp's.
	- [Weka API](http://weka.wikispaces.com/ "http://weka.wikispaces.com/"): disponibiliza as funções de aprendizagem do Weka para uso noutros programas.
		- [Weka JavaDoc](http://weka.sourceforge.net/doc.stable/ "http://weka.sourceforge.net/doc.stable/"): documentação oficial da API do Weka.
		- [Weka Tutorial -   Using WEKA from Groovy](http://weka.wikispaces.com/Using+WEKA+from+Groovy "http://weka.wikispaces.com/Using+WEKA+from+Groovy"): tutorial do Weka sobre como aplicar a API em programas Groovy.
		- [Weka Tutorial -  Use Weka in your Java code](http://weka.wikispaces.com/Use+Weka+in+your+Java+code "http://weka.wikispaces.com/Use+Weka+in+your+Java+code"): tutorial do Weka sobre como aplicar a API em programas Java.
		- [Weka Tutorial -  Serialization](http://weka.wikispaces.com/Serialization "http://weka.wikispaces.com/Serialization"): tutorial do Weka sobre Serializar o classificador
		

- **Editor de textos**:
	- _Eclipse IDE_, versão 4 "Juno" 
	([http://www.eclipse.org](http://www.eclipse.org "http://www.eclipse.org"))
		- OBS: utilizado o plugin [_Spring Tool Suite_](www.springsource.org/sts "www.springsource.org/sts") na IDE


- **Controle de Versão** _(opcional)_:
	- _Git_, versão 1.7 ([http://git-scm.com](http://git-scm.com "http://git-scm.com"))
	- _GitHub_ ([https://github.com/paulolimac](https://github.com/paulolimac "https://github.com/paulolimac"))
	- _Bitbucket_ ([https://bitbucket.org/paulolimac](https://bitbucket.org/paulolimac "https://bitbucket.org/paulolimac"))
	- _Egit_, versão 2.0 ([http://eclipse.org/egit](http://http://eclipse.org/egit "http://eclipse.org/egit"))

Com este material é possível iniciar as implementações com [Weka](http://www.cs.waikato.ac.nz/ml/weka/ "http://www.cs.waikato.ac.nz/ml/weka/") no [Grails](http://grails.org/ "http://grails.org/").

### Instalação 
Basicamente, necessita-se do ambiente [Java](http://www.java.com "http://www.java.com"), [Grails](http://grails.org/ "http://grails.org/"), [Weka.jar](http://www.cs.waikato.ac.nz/ml/weka/index_downloading.html "http://www.cs.waikato.ac.nz/ml/weka/index_downloading.html") e uma IDE para facilitar o desenvolvimento.

Utilizando o [Ubuntu](http://www.ubuntu.com/ "http://www.ubuntu.com/"), é indicado o seguinte procedimento para instalação:

1. Instalação da [IDE Eclipse](www.eclipse.org/ "www.eclipse.org/"), que trará paralelamente o ambiente [Java](http://www.java.com "http://www.java.com"):
	- Digite no Terminal: `sudo apt-get install eclipse`.
	- Obs: o [Java](http://www.java.com "http://www.java.com") será instalado automaticamente.
2. Plugin [Spring Tool Suite](www.springsource.org/sts "www.springsource.org/sts")
	- Siga as instruções do [http://grails.org/STS+Integration](http://grails.org/STS+Integration "http://grails.org/STS+Integration")
3. API Weka
	- Baixe o jar do Weka pelo [http://www.cs.waikato.ac.nz/ml/weka/index_downloading.html](http://www.cs.waikato.ac.nz/ml/weka/index_downloading.html "http://www.cs.waikato.ac.nz/ml/weka/index_downloading.html"). 
	- Depois inclua o jar na pasta `libraries` do seu projeto Grails.
	- Por fim, clique com o botão direito no seu projeto Grails pelo Eclipse e adicione o Jar ao `classpath` do seu projeto.
4. [Navigation Plugin](http://grails.org/plugin/navigation "http://grails.org/plugin/navigation")
	- No diretório do seu projeto Grails, digite no terminal: `grails`
	- No ambiente `grails`, digite o comando `grails install-plugin navigation`
5. _(opcional)_ Controle de versão, sugerido o [Git](http://git-scm.com/ "http://git-scm.com/") (com o [plugin Egit](http://www.eclipse.org/egit/ "http://www.eclipse.org/egit/")). O uso de controle de versão é um boa prática para gerir mudanças no código, possibilitar SEGURANÇA 
com cópias de salva-guarda (_backup_) e, um histórico da sua codificação durante 
todo seu estudo. Este aprendizado é um adicional em seu conhecimento.
 E afinal, você está aqui e agora fazendo uso de um repositório [Git](http://git-scm.com/ "http://git-scm.com/"), não? 
	- No Eclipse: clique no menu `Help -> Eclipse Marketplace` e faça uma busca 
	pelo plugin `Egit`.
	- Outra opção é instalar o [Git](http://git-scm.com/ "http://git-scm.com/") e utilizá-lo por linha de comando. No Terminal, instale pelo comando: 
	`sudo apt-get install git-core git-doc git-gui gitk`.

---
## Downloads 

Caso desejar, brinque com o _Weka Plus Grails_ on-line. Você pode acessá-lo em [http://wekaplusgrails.herokuapp.com/](http://wekaplusgrails.herokuapp.com/ "http://wekaplusgrails.herokuapp.com/")

O aplicativo (war) para instalação no seu servidor pode ser baixado pelo link
 [WekaPlusGrails-0.1.war](https://bitbucket.org/paulolimac/wekaplusgrails/downloads/WekaPlusGrails-0.1.war "WekaPlusGrails-0.1.war")

Se preferir visualizar o código, navegue-copie-e-cole pelas implementações
 disponibilizadas neste repositório. Ou utilize [Git](http://git-scm.com/ "http://git-scm.com/")! 

---
## Começando a usar este material ⚒

Este repositório pode ser consultado online, ter seus textos e códigos copiados,
 ou mesmo ser clonado (_fork_). Além disso, utilize sua instalação [Git](http://git-scm.com/ "http://git-scm.com/") para copiar o repositório localmente e trabalhar em sua IDE ou editor de textos preferido.

_Use o material com bom discernimento!_

Um ponto importante é que a estrutura do material aqui apresentado não 
é a única forma de implementação, nem é a mais elegante. Entenda-o e ajuste-o ao seu gosto e preferência.  

### Funções do aplicativo 
Este aplicativo web faz uso básico da [biblioteca do Weka](http://weka.sourceforge.net/doc.dev/ "http://weka.sourceforge.net/doc.dev/") e do [Grails](http://grails.org/ "http://grails.org/").

O objetivo é atender os requisitos listados anteriormente. Nisso, foram criadas 4 entidades (Classes de domínio) e suas respectivas classes de controle e visualizações (gsp's). São as entidades:

![Diagrama UML de classes](https://bitbucket.org/paulolimac/wekaplusgrails/downloads/Class.svg "Diagrama UML de classes")

A descrição das entidades e atributos segue:

- _TrainSample_: Esta entidade armazena os [arquivos ARFF](http://weka.wikispaces.com/ARFF "http://weka.wikispaces.com/ARFF") (instâncias) para treino de algoritmos de classificação.
	- _name_: nome para identificar o TrainSample salvo
	- _description_: breve descrição sobre o TrainSample armazenado
	- _filename_: nome do [arquivo ARFF](http://weka.wikispaces.com/ARFF "http://weka.wikispaces.com/ARFF") salvo pelo sistema. É gerado pelo sistema de acordo o arquivo de upload feito pelo usuário
- _Classifier_: Salva os classificadores gerados no treino. É pré-requisito que um trainSample esteja salvo no sistema para ser usado na geração do classificador.
	- _name_: nome para identificar o Classifier salvo
	- _description_: breve descrição sobre o Classifier armazenado
	- _filename_: nome do arquivo model salvo pelo sistema. É gerado pelo sistema de acordo o tipo de algoritmo classificador e do TrainSample selecionados. O arquivo é binário, e portanto, ilegível ao usuário.
	- _modelTxt_: arquivo de texto contendo as regras de decisão do modelo de classificador criado. Variará de acordo com o classificador e com o TrainSample aplicados. É gerado automaticamente pelo aplicativo. Por ser texto, o arquivo é legível pelo usuário
	- _typeClassifier_: lista para o usuário as opções de algoritmo-classificador para criação do modelo.
	- _trainSample_: lista dos TrainSample existentes no sistema para treinar o classificador. O usuário escolhe um.
- _TestSample_: Testa um classificador criado pelo sistema. Para isso, o TestSample armazena instâncias novas fornecidas pelo usuário para teste. No final, ele relata (por estatística) os resultados obtidos. É necessário que um classificador esteja salvo no sistema e, que as instâncias fornecidas sejam compatíveis com as usadas no TrainSample que gerou o classificador.
	- _name_: nome para identificar o TestSample salvo
	- _description_: breve descrição sobre o TestSample armazenado
	- _filename_: nome do [arquivo ARFF](http://weka.wikispaces.com/ARFF "http://weka.wikispaces.com/ARFF") salvo para o sistema. É gerado pelo sistema de acordo o arquivo de upload feito pelo usuário. Sugere-se um arquivo com instâncias diferentes das usadas no TrainSample, afim de verificar a acuracia do modelo testado.
	- _evaluationTxt_: arquivo de texto onde consta o relatório do teste efetuado sob o Classifier.
	- _classifier_: lista dos Classifiers existentes no sistema para testar. O usuário escolhe um.
- _Labeler_: Aplica o classificador criado no sistema. O usuário fornece as instâncias (por texto ou, por [arquivo ARFF](http://weka.wikispaces.com/ARFF "http://weka.wikispaces.com/ARFF")) equivalentes a usada no TrainSample que gerou o classificador, seleciona o classificador e, por fim o sistema gera um [arquivo ARFF](http://weka.wikispaces.com/ARFF "http://weka.wikispaces.com/ARFF") com as instâncias classificadas.
	- _name_: nome para identificar o Labeler salvo
	- _description_: breve descrição sobre o Labeler armazenado
	- _typeInput_: tipo de entrada que o usuário deseja classificar, se pelo campo de texto ou pelo upload de [arquivo ARFF](http://weka.wikispaces.com/ARFF "http://weka.wikispaces.com/ARFF").
	- _unlabeledArffFilename_: nome do [arquivo ARFF](http://weka.wikispaces.com/ARFF "http://weka.wikispaces.com/ARFF") enviado pelo usuário para o sistema. É gerado o nome pelo sistema de acordo o arquivo de upload feito pelo usuário. As instâncias não devem estar "_labeled_", ou seja, não devem estar etiquetadas (classificadas).
	- _unlabeledTextFieldFilename_ similar ao anterior, mas o texto é digitado no formulário. É gerado o nome do [arquivo ARFF](http://weka.wikispaces.com/ARFF "http://weka.wikispaces.com/ARFF") pelo sistema automaticamente. As instãncias devem estar também "_unlabeled_".
	- _classifier_: lista dos Classifiers existentes no sistema para testar. O usuário escolhe um.
	- _labeledArffFilename_: nome do [arquivo ARFF](http://weka.wikispaces.com/ARFF "http://weka.wikispaces.com/ARFF") com as instâncias classificadas (labeled) pelo sistema. É gerado o nome pelo sistema. As instâncias unlabeled foram as enviadas pelo usuário, e aqui se tornam labeled pelo sistema. As classes são adicionadas em cada linha de instância num [arquivo ARFF](http://weka.wikispaces.com/ARFF "http://weka.wikispaces.com/ARFF") pelo sistema ao final.
		- OBS: Leia a seção "Perguntas Frequentes (FAQ)" sobre como diferenciar e preparar instâncias classificadas (labeled) e não classificadas (unlabeled)

Estas instâncias são gerados inicialmente com o [scaffold do Grails](http://grails.org/doc/latest/guide/scaffolding.html "http://grails.org/doc/latest/guide/scaffolding.html") resultando em [CRUD's básicos (criar, listar, atualizar, deletar, mostrar, editar e salvar)](http://grails.org/doc/latest/guide/GORM.html#basic "http://grails.org/doc/latest/guide/GORM.html#basicCRUD").

Métodos específicos do Weka foram importados nos Controllers das entidades. Com o scaffold das entidades gerado, os métodos e rotinas com uso da [biblioteca do Weka](http://weka.sourceforge.net/doc.dev/ "http://weka.sourceforge.net/doc.dev/") foram produzidas e adaptou-se os CRUDs para cada entidade.

#### Vantagens... ☻
Como pontos positivos do sistema temos que:

- Poucas entidades, para facilitar o entendimento
- Linguagem [Groovy](http://groovy.codehaus.org/ "http://groovy.codehaus.org/"), facilita a [leitura das rotinas](http://groovy.codehaus.org/Groovy+style+and+language+feature+guidelines+for+Java+developers "http://groovy.codehaus.org/Groovy+style+and+language+feature+guidelines+for+Java+developers")
- Serve de exemplo sobre uso da [biblioteca do Weka](http://weka.sourceforge.net/doc.dev/ "http://weka.sourceforge.net/doc.dev/"), com algumas das principais funções [que foram exemplificadas com java pelo site oficial](http://weka.wikispaces.com/Use+Weka+in+your+Java+code "http://weka.wikispaces.com/Use+Weka+in+your+Java+code")
- Usa mais funções do Weka, além do [básico com groovy exemplificado pelo site oficial](http://weka.wikispaces.com/Using+WEKA+from+Groovy "http://weka.wikispaces.com/Using+WEKA+from+Groovy").
- Sistema disponível a todos aqueles que desejarem conhecê-lo

#### ...E desvantagens ☠
Claro que há desvantagens. São:

- Usada a linguagem [Groovy](http://groovy.codehaus.org/ "http://groovy.codehaus.org/") ([Grails](http://grails.org/ "http://grails.org/")), consequentemente, não há exemplos noutras linguagens ([Java](http://www.java.com "http://www.java.com"), [Ruby](www.ruby-lang.org "www.ruby-lang.org"), [Python](www.python.org/ "www.python.org/")...)
- Código carece de elegância (concisão)
- Definir melhores nomes para entidades e métodos, nomes que definam melhor o modelo
- Reduzir o código gerado automaticamente ([scaffolded](http://grails.org/doc/latest/guide/scaffolding.html "http://grails.org/doc/latest/guide/scaffolding.html")) pelo [Grails](http://grails.org/ "http://grails.org/")
- Exceptions ocorrerão e não são tratadas
- Entradas nos formulários (e arquivos) não são validadas
- Não há instâncias previamente armazenadas para exemplificar o que é salvo
- Seria melhor ter menos instâncias...
- Diversas outras falhas ainda não identificadas ;)

---
## Perguntas frequentes (FAQ's) ⸮

- ***Pergunta:*** _Qual a diferença entre instâncias classificadas (_labeled_) e não classificadas (_unlabeled_)?_
	- **Resposta:** Na [documentação do Weka](http://weka.sourceforge.net/doc.dev/ "http://weka.sourceforge.net/doc.dev/") o termo _label_, em inglês _etiqueta_, equivale a _classificar_. Ou seja, instâncias que estão _labeled_ estão classificadas já. E instâncias _unlabeled_ ainda não foram classificadas. As instâncias _unlabeled_ são usadas em classificadores já criados. E as instâncias _labeled_ são usadas para treinar e/ou testar classificadores em sua construção.
- ***Pergunta:*** _Como reconhecer/diferenciar e fazer instâncias _unlabeled_ e _labeled_ nos [arquivos ARFF](http://weka.wikispaces.com/ARFF "http://weka.wikispaces.com/ARFF")?_
	- **Resposta:** Ao abrir um [arquivo ARFF](http://weka.wikispaces.com/ARFF "http://weka.wikispaces.com/ARFF"), verifique se nas linhas das instâncias, há escrito o nome da classe ou se _há um sinal de interrogação (`?`)_. Se houver um sinal de interrogação `?` então a instância está _unlabeled_, ou seja, não foi classificada. Ao contrário, se no final da linha da instância estiver escrito a classe, então esta instância está classificada, ou seja, ela está _labeled_. Na resposta dada no tópico [WEKA: Classifying an ARFF data with a given SMO model](http://stackoverflow.com/questions/10785398/weka-classifying-an-arff-data-with-a-given-smo-model "http://stackoverflow.com/questions/10785398/weka-classifying-an-arff-data-with-a-given-smo-model") é respondida indiretamente nossa questão. E para facilitar, veja o exemplo abaixo:
		- _Instâncias unlabeled_ num arquivo arff, repare no sinal obrigatório de `?`:
					
					@relation weather.symbolic
						@attribute outlook {sunny, overcast, rainy}
						@attribute temperature {hot, mild, cool}
						@attribute humidity {high, normal}
						@attribute windy {TRUE, FALSE}
						@attribute play {yes, no}

						@data
							sunny,hot,high,FALSE,?
							sunny,hot,high,TRUE,?
							overcast,hot,high,FALSE,?
							
		- _Instâncias labeled_ num arquivo arff, já com a classe no final da linha:
			
					@relation weather.symbolic
						@attribute outlook {sunny, overcast, rainy}
						@attribute temperature {hot, mild, cool}
						@attribute humidity {high, normal}
						@attribute windy {TRUE, FALSE}
						@attribute play {yes, no}

						@data
							sunny,hot,high,FALSE,no
							sunny,hot,high,TRUE,no
							overcast,hot,high,FALSE,yes


- ***Pergunta:*** _É complicado instalar/configurar tudo?_
	- **Resposta:** Para iniciante, sim!. Mas depois de prática com Grails e com os termos usados no Weka, tudo se torna fácil. Igual a tudo na vida.

- ***Pergunta:*** _Porque fazer este aplicativo?_
	- **Resposta:** Simples: praticar mais com o Grails, estudar sobre a biblioteca do Weka, etc. Desculpas não faltam.

- ***Pergunta:*** _Posso utilizar outros softwares para implementar o aplicativo?_
	- **Resposta:** Claro que sim! Utilize seu sistema Java. Sua IDE ou editor. Até mesmo se conhecer outra biblioteca, aprenda-a. Não fique preso neste modelo.

- ***Pergunta:*** _Neste repositório, como os branches foram criados e usados?_
	- **Resposta:** A organização deste repositório, principalmente os branches 
	e tags, baseou-se na idéia apresentada pelo 
	[Vincent Driessen](http://nvie.com "http://nvie.com"), numa postagem do seu 
	blog entitulada _[A successful Git branching model](http://nvie.com/posts/a-successful-git-branching-model/ "http://nvie.com/posts/a-successful-git-branching-model/")_. 
	Poucas diferenças para com a idéia original do Vicent foram efetuadas, e 
	visam estruturar (nomear) os branches-tags-commits voltados a organização 
	do repositório _orientados por tipo de arquivo/diretório_. 
	
- ***Pergunta:*** _Tenho que utilizar [Git](http://git-scm.com/ "http://git-scm.com/") para conhecer este aplicativo?_
	- **Resposta:** Não! Você pode baixar o código e usar outro versionador. Ou mesmo não utilizar nenhum. MAS, recomendamos o uso do [Git](http://git-scm.com/ "http://git-scm.com/") para seus projetos. Vale a pena aprender.

- ***Pergunta:*** _Não seria melhor implementar uma tela única, ao invés de tantas telas de cadastros e listagens?_
	- **Resposta:** Sim. Mas o foco foi utilizar a  [API do Weka](http://weka.sourceforge.net/doc.dev/ "http://weka.sourceforge.net/doc.dev/"). [Grails](http://grails.org/ "http://grails.org/") foi objetivo secundário. Ainda assim, melhorias precisam ser feitas.


---
## Licenciamento (copyright) 

O trabalho aqui foi criado e disponibilizado para estudo acadêmico.

Não somos donos da Linguagem, Biblioteca Weka etc. Cada um destes produtos tem sua própria licença.

Com o código aqui disponível, você pode: copiar, colar, alterar, clonar... enfim, fazer o que achar conveniente.

O _Weka Plus Grails_ é um projeto inicial, um protótipo.

Portanto, saiba que ao utilizá-lo, ele pode não estar conforme com as boas práticas de programação e de uso da [API do Weka](http://weka.sourceforge.net/doc.dev/ "http://weka.sourceforge.net/doc.dev/"). Use por sua conta e risco.

Mas enfim, aproveite-o!

---
## Changelog 

Caso queira visualizar o histórico de mudanças neste modelo, simples: leia os 
commits deste [repositório Git](https://bitbucket.org/paulolimac/wekaplusgrails "https://bitbucket.org/paulolimac/wekaplusgrails")!

Neles é possível saber, por exemplo, o que foi implementado, modificado, em que 
período de tempo...

Detalhe: procuramos fazer as mensagens dos commits bem organizadas e 
informativas. Mas há exceções, claro. Exemplo: commits enviados de forma 
equívocada, com pouca mensagem, commits sem importância alguma para o 
entendimento do modelo etc.

Ainda assim, a maioria dos commits representa muito bem o registro de mudanças 
(changelog) e avanços na criação do _Weka Plus Grails_.

---
## Melhorías para o futuro ∞

Algumas questões que podem ser implantados nas próximas versões:

- Disponibilizar o sistema online para facilitar a execução sem precisar baixar nada.
	- Ou seja, fazer deploy do arquivo war do projeto. Possíveis sites para armazenar a versão seria:
		- [http://www.cloudbees.com/](http://www.cloudbees.com/ "http://www.cloudbees.com/")
		- [http://java.herokuapp.com/](http://java.herokuapp.com/ "http://java.herokuapp.com/")
		- [http://www.cloudfoundry.com/](http://www.cloudfoundry.com/ "http://www.cloudfoundry.com/")
		- ... qualquer outro que suporte java.
	- Disponibilizar um banco de dados pré-salvo no aplicativo
		- Cadastrar nele 3 ou mais instâncias, salvas e prontas para consulta
			- Assim qualquer usuário já verá o sistema com dados armazenados e facilita o entendimento
		- Incluir rotina para limpar o banco de dados com frequência
- Reduzir a quantidade de telas: para tornar o aplicativo mais simples;
	- tudo numa única página, um único formulário, quase um script online;
	- usar um modelo (template, layout) mais leve e personalizado. Abandonar o template do scaffold provido pelo Grails;
- Melhorar a documentação, ou seja, este README.
	- Deixá-lo mais compacto, direto, objetivo e bem escrito;
- Refinar a implementação com um código mais elegante e simples.
	- Retirar redundâncias
	- Refatorar
	- Usar variáveis mais globais (exemplo a variável `webAppRootPath`)
	- Aplicar Exceptions e blocos try-catch
- Fazer um script [Groovy](http://groovy.codehaus.org/ "http://groovy.codehaus.org/"), para executar tudo com uma única linha de comando;
- Enviar o código criado para a wiki oficial do [Weka com o groovy](http://weka.wikispaces.com/Using+WEKA+from+Groovy "http://weka.wikispaces.com/Using+WEKA+from+Groovy")
	- Adicionar também link para este nosso projeto Grails do github/bitbucket
	- Incluir links dos deploies do projeto
	- submeter tutorial do Weka com foco no groovy
	- submeter tutorial do Weka com foco no grails
- Adotar novas idéias e práticas, caso aparecam :)

---
## Agradecimentos 
Queremos agradecer:

- Aos desenvolvedores da [IDE Eclipse](http://www.eclipse.org "http://www.eclipse.org"), [Git](http://git-scm.com "http://git-scm.com"), do [Weka](http://www.cs.waikato.ac.nz/ml/weka/ "http://www.cs.waikato.ac.nz/ml/weka/"), [Egit](http://http://eclipse.org/egit "http://eclipse.org/egit"), [Groovy](http://groovy.codehaus.org/ "http://groovy.codehaus.org/") e [Grails](http://grails.org/ "http://grails.org/"), pois estes sistemas facilitaram e muito a criação e organização deste projeto.
- As diversas comunidades online, pela documentação e tutoriais que auxiliaram quando os 
manuais oficiais não foram suficientes para responder as dúvidas;
- E antecipadamente, as futuras contribuições que venhamos receber pelos 
visitantes nestes repositórios remotos que se encontram online e acessíveis 
a todos.

---
### Desejamos bons desenvolvimentos de aplicativos, a quem interessar! 

Cordialmente,

Paulo de Lima Cavalcanti  
([www.paulolimac.com](www.paulolimac.com "www.paulolimac.com"))
