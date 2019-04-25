# ServiceMilk

### Configuração para servidor LOCAL
1. Instale o XAMPP. [Install to Windows](https://www.apachefriends.org/pt_br/download.html) / [Install to Linux](http://www.linuxandubuntu.com/home/how-to-download-install-xampp-on-linux)
2. Instale o MAVEN como variável de ambiente. [Install to Windows](http://www.matera.com/blog/post/tutorial-instalacao-apache-maven-configuracao-eclipse) / [Install to Linux](https://sempreupdate.com.br/aprenda-a-instalar-o-maven-no-gnulinux/)
3. Verifique com o comando **mvn --version**.
4. [Instale o JAVA](https://docs.oracle.com/cd/E19182-01/820-7851/inst_cli_jdk_javahome_t/) como variável de ambiente. 
5. Verifique com o comando **java -version**.
6. Abra o XAMPP e inicie tanto o apache quanto o banco de dados MYSQL.
7. Abra a URL *localhost/phpmyadmin* e crie um banco de dados chamado **milk**.
8. Abra o arquivo **application.properties** e configure o usário e senha de acordo com seu banco MYSQL do phpmyadmin.
9. No diretório do projeto onde se encontra o pom.xml, execute o comando **mvn clean install**.
10. Após o build do projeto, entre no diretório **target** e execute o comando **java -jar nomedoarquivojar.jar**


> **IMPORTANTE**: caso você não tenha o XAMPP instalado, basta comentar os códigos de configurações do banco MYSQL que estão no arquivo 
> **application.properties** e descomentar as configurações do banco do **H2**. Feito isso, repita os passos **9** e **10**

>**Verbos Http:**

**#Controller do Cliente:**

1. GET
- 1.1 - Listar todos os clientes : /usuarios/clientes
- 1.2 - Listar clientes pelo id: /usuarios/clientes/id

2. POST
- 2.1 - Cadastrar cliente com credenciais: /usuarios/clientes

3.PUT
- 3.1 - Atualizar o cliente pelo id: /usuarios/clientes/id

4. DELETE
- 4.1 - Deletar cliente pelo id: /usuarios/clientes/id

**#Controller da Fazenda:**

1. GET
- 1.1 - Listar todos as fazendas: /fazenda
-  1.2 - Listar fazendas pelo id: /fazenda/id

2. POST
- 2.1 - Cadastrar Fazenda passando o id do cliente: /fazenda/id

3. PUT
- 3.1 - Atualizar a fazenda pelo id: /fazenda/id

4. DELETE
- 4.1 - Deletar fazenda pelo id: /fazenda/id
