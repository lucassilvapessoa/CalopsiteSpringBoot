##### GIT Guidelines #####
O repositório terá duas *branches* principais, `master` e `develop`.

É imperativo que todos os commits presentes nessas duas *branches* sejam compiláveis por qualquer pessoa, a qualquer tempo. Sendo assim, qualquer desenvolvedor deve ser capaz de clonar o repositório em qualquer *commit* nessas duas *branches* e ser capaz de realizar a compilação sem nenhuma alteração.

Da *branch* `develop`, serão criadas as *branches* de implementação/correção de *Issues*. Estas serão as *branches* onde ocorre, efetivamente, o desenvolvimento do código. Esta separação minimiza conflitos de `merge` e facilita o desenvolvimento paralelo por várias instâncias de programadores, sem interferência significativa do trabalho de uma na outra.

Quando for necessário gerar uma versão para *release*, deve-se fazer o *merge* **da** *branch* `develop` **na** *branch* `master`. É gerada uma **tag** com a versão corrente (e incrementa-se a versão em `develop`) e o binário é compilado. É permitido gerar versões intermediárias de *debug* na *branch* `develop`, as quais devem ser claramente indicadas no final de sua *string* de versão com a letra `d`. Deve-se também gerar uma *tag* para essas versões. Estes *releases* de *debug* podem/devem ser usados para testar o código no *targe* antes que as modificações sejam incorporadas em `master`. 

**Em hipótese alguma** deve-se gerar *tags* de versão nas *branches* de desenvolvimento (*Issue*). Quando necessário, deve-se fazer o `merge` na *branch* `develop`, observando sempre a regra da compilabilidade.

**Em hipótese alguma** deve-se desenvolver/modificar código na *branch* `master`. **Todos** os *commits* nessa *branch* devem ser *merges* da branch `develop`.

**Exemplo de árvore**
~~~
     L   M   N---O       issue branches
    / \ / \ /     \
---A---B---C-------J---- develop
    \       \       \
-----D-------G-------H-- master
~~~

Neste trecho, temos as *branches* `master` e `develop`, além das `issue branches`, onde é feito, de fato o desenvolvimento do código. Por exemplo, a partir do *commit* `A` na *branch* `develop`, cria-se uma nova *issue branch* que possui o *commit* `L`. O `merge` de `L` em `develop` cria o *commit* `B`. O *commit* `G` da *branch* `master` é o `merge` desta com a *branch* `develop`. Os *commits* `D`,`G` e `H` devem ser associados a *tags* de versão.

#### Exemplo de workflow de desenvolvimento ####

##### Issue branches e `develop` #####
**Preparação**:

Atualiza a árvore local e cria uma *branch* de desenvolvimento.

`git checkout develop # muda para a branch develop`
`git pull origin develop # atualiza a branch local com a branch remota`
`git checkout -b IssueX # Cria uma issue branch para o desenvolvimento`

**Realiza as alterações no código**:

Na *branch* `IssueX`, são feitas as modificações necessárias no código. Ao finalizar as alterações, testar o código e verificar se não quebrou nada. `X` é o número do issue no *bug tracker*.

`git status # Verifica os arquivos alterados. Notar quais são realmente necessários manter as modificaçoes na árvore!`
`git add <arquivos necessários> # Adiciona os arquivos modificados`
`git commit # Cria um commit e define a mensagem`

A mensagem de *commit* deve iniciar com `Issue #X:`, sendo X o número do *issue* criado no *bug tracker* do repositório. A mensagem deve indicar quais as alterações feitas e quaisquer outras informações importantes. Observe atentamente o formato de mensagem de *commit* do `git`: A primeira linha será o título do *commit*, a seguir uma linha em branco, e após a mensagem com quantas linhas forem necessárias. Exemplo:
~~~
Issue #123: Este é o título do commit

Após saltar uma linha em branco, que separa o título do corpo da mensagem, podemos escrever mais quantas linhas forem necessárias.
Daqui em diante, a mensagem pode ter quantas linhas forem necessárias. Apenas as primeiras linhas antes da linha em branco são usadas como título. Portanto, mantenha essa linha concisa e adicione os detalhes no corpo da mensagem.
~~~

**Observação**: Não omitir a palavra `Issue`, pois o caracter `#` no começo da linha é interpretado como comentário, podendo fazer com que o git detecte o commit com uma mensagem vazia, cancelando-o.

**Observação**: Crie quantos *commits* forem necessários para organizar a implementação. Evite *commits* que agregam muitas modificações pouco correlacionadas, priorizando agrupar num mesmo *commit* aquelas mais relacionadas. Lembre-se que **não** é necessário adicionar todos os arquivos modificados em um mesmo *commit*.

**Dica**: Após clonar o repositório, crie o arquivo `.git/hooks/commit-msg` com o conteúdo abaixo:

~~~
#!/bin/sh

BRed='\033[1;31m'         # Red
IRed='\033[0;91m'         # Red
Color_Off='\033[0m'       # Text Reset
BBlue='\033[1;34m'        # Blue
Green='\033[0;32m'        # Green

title=`head -1 $1 | grep -Ei "^(Issue #[0-9]+:|Merge)"`
if [ "T$title" = "T" ]; then
  echo "$BRed**************************************$Color_Off"
  echo "$BRed************* WARNING!!! *************$Color_Off"
  echo "$BRed************* WARNING!!! *************$Color_Off"
  echo "$BRed************* WARNING!!! *************$Color_Off"
  echo "$BRed**************************************$Color_Off"
  echo "Your message is not formatted correctly. Use $BBlue\"Issue #X: <title>\"$Color_Off format in the first line of file."
  echo "Use$Green git commit --amend$Color_Off to edit the commit message."
fi
~~~

Este Script vai alertá-lo se você esquecer de adicionar o número do *Issue* no título do commit e vai informar como editar a mensagem.

**Atualização da árvore remota**:

Sincroniza novamente a *branch* local (`develop`) com possíveis alterações no remoto, e envia as modificações locais para o remoto.

`git checkout develop`
`git pull origin develop # pulo do gato: como não houve alterações na branch develop, não vai haver conflito aqui`

> **Se houve atualização na branch** `develop` **remota**, é preciso fazer um rebase da *issue branch*:
> `git checkout IssueX`
> `git rebase develop`
> Repetir a partir de **Atualização da árvore remota** até que não seja mais necessário fazer o `rebase`.

**Atualiza a informação de versão** no arquivo `include/maintask.h`, `git add include/maintask.h` e `git commit -m"Version bump to vX.x"`. Este é o único *commit* permitido na *branch* `develop`.

`git merge --no-ff IssueX # Atenção: Esse comando deve ser executado estando na branch develop`

***
**Verificar se após o** `merge` **o código continua compilável.**

Não faça o push abaixo se a compilação do código não funcionar!
***

`git push origin develop # envia as alterações locais para o repositório remoto`

##### `develop` e `master` #####

Quando for necessário criar uma versão de *release*, deve-se criar um *commit* na *branch* `master`. O processo é semelhante ao de união das *issue branches* com (na) `develop`, porém, agora, une-se a `develop` com (na) `master`.

**Exemplo**:

`git checkout master`
`git pull origin master`
`git merge --no-ff develop`

Verificar se o código é ainda **compilável**. Se for verificado que as alterações e merge não quebraram nada, prosseguir com o `push`.

**Tag**: Cria a *tag* de versão na *branch* master. Deve-se criar uma *tag* do tipo anotada. Para *tags* pessoais do desenvolvedor, deve-se usar tags leves, sem o parametro `-a`. Estas tags não devem ser enviadas ao repositório remoto.
`git tag -a -m"Release vX.x" vX.x`
`git push --follow-tags origin master`

---
