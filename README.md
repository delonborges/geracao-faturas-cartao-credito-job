<h1 align=center>
  Geração de faturas de cartão de crédito Spring Batch Job
</h1>

<p align=center>
  <a href="#-technologies">Tecnologias</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-project">Projeto</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-configuration">Configuração</a>&nbsp;&nbsp;&nbsp;|&nbsp;&nbsp;&nbsp;
  <a href="#-developing">Construir e Executar</a>
</p>

<p align=center>
  <a href="https://www.udemy.com/certificate/404/"> <img alt="Udemy" src=https://img.shields.io/badge/Udemy-EC5252?style=flat-square&logo=Udemy&logoColor=white&labelColor=purple&color=purple&https://www.udemy.com/certificate/404/></a>
</p>

<br>

## ✨ Technologies

- [Java](https://www.oracle.com/java/technologies/downloads/)
- [Gradle](https://docs.gradle.org/current/userguide/userguide.html)
- [JUnit 5](https://junit.org/junit5/docs/current/user-guide/)
- [MySQL](https://dev.mysql.com/downloads/mysql/)
- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Batch](https://spring.io/projects/spring-batch)

## 💻 Projeto

geracao-faturas-cartao-credito-job é um serviço que efetua a geração de faturas de cartão de crédito.

Esse projeto foi elaborado durante o curso [Desenvolvimento de jobs com Spring Batch](https://www.udemy.com/share/103eh43@WkfdGKu8A6NAdGpSxOIOLN9tjcdECAP3NEiN3MdU4dH98wKcM9gwKAvTr6u4EryC/).

## 🛠️ Configuração

O projeto requer um banco de dados Mysql, então é necessário criar uma base de dados com os seguintes comandos:

```
$ sudo mysql

CREATE USER 'username'@'%%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON * . * TO 'username'@'%%';
FLUSH PRIVILEGES;

exit

$ mysql -u username -p

CREATE DATABASE spring-batch;
CREATE DATABASE faturas-cartao-credito;

exit
```

## 🚀 Construir e Executar

Para construir e executar o job, execute o comando:

```sh
$ gradle bootRun
```
