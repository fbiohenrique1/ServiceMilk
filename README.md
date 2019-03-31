# ServiceMilk

### Configuração para servidor LOCAL
1. Instale o XAMPP. [Install to Windows](https://www.apachefriends.org/pt_br/download.html) / [Install to Linux](http://www.linuxandubuntu.com/home/how-to-download-install-xampp-on-linux)
2. Instale o MAVEN como variável de ambiente. [Install to Windows](http://www.matera.com/blog/post/tutorial-instalacao-apache-maven-configuracao-eclipse) / [Install to Linux](https://sempreupdate.com.br/aprenda-a-instalar-o-maven-no-gnulinux/)
3. Verifique com o comando **mvn --version**.
4. [Instale o JAVA](https://docs.oracle.com/cd/E19182-01/820-7851/inst_cli_jdk_javahome_t/) como variável de ambiente. 
5. Verifique com o comando **java -version**.
6. Abra o XAMPP e inicie tanto o apache quanto o banco de dados MYSQL.
7. Abra a URL *localhost/phpmyadmin* e crie um banco de dados chamado **milk**.
8. Abra o arquivo **application.properties** e configure a usuario e senha de acordo com seu banco.
9. No diretório do projeto onde se encontra o pom.xml, execute o comando **mvn clean install**.
10. Após o build do projeto, entre no diretório **target** e execute o comando **java -jar nomedoarquivojar.jar**


> **IMPORTANTE**: caso você não tenha o XAMPP instalado, basta comentar os códigos de configuração de banco MYSQL que estão no arquivo 
> **application.properties** e descomentar a configuração de banco do **H2**. Feito isso, repita os passos **9** e **10**

