# java-jdbc

Projeto de estudo que demonstra o uso de JDBC em Java, realizando a conexão com o banco de dados por meio de um arquivo de propriedades (`db.local.properties`) localizado na raiz do projeto

## 🛠️ Pré-requisitos

- Java JDK 
- Driver JDBC para o seu banco de dados (ex: MySQL Connector/J se estiver usando MySQL) 
- Um banco de dados configurado (MySQL, PostgreSQL ou outro suportado pelo driver)  

## 🔧 Configuração

1. Na raiz do projeto, **crie um arquivo** chamado `db.local.properties`.  
2. Dentro de `db.local.properties`, informe as propriedades necessárias para conexão com o banco, por exemplo:

   ```properties
   url=jdbc:mysql://localhost:3306/seu_banco
   user=seu_usuario
   password=sua_senha
   driver=com.mysql.cj.jdbc.Driver
Ajuste conforme o banco que estiver usando.

Certifique-se de que o driver JDBC esteja disponível no classpath do projeto (ou configurado via dependência, se estiver usando Maven/Gradle). 
Arquivo de Códigos


🚀 Como rodar
Abra o projeto na sua IDE favorita (Eclipse, IntelliJ, NetBeans, etc.) ou compile via linha de comando/Maven.

Garanta que o arquivo db.local.properties esteja presente na raiz.

Execute a classe principal do projeto.

A aplicação irá ler as configurações do arquivo de propriedades e estabelecer conexão com o banco via JDBC.

🎯 O que este projeto demonstra
Uso de JDBC (Java Database Connectivity) para conectar aplicações Java a bancos de dados relacionais. 

Separação das configurações de conexão em um arquivo de propriedades, facilitando a configuração local e evitando hard-code de credenciais.

Execução de operações básicas de banco (dependendo do que estiver implementado — CRUD, queries, etc.) por meio de JDBC.

📄 Exemplo de db.local.properties
# JDBC configuration
driver=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://localhost:3306/meu_banco?useSSL=false&serverTimezone=UTC
user=root
password=senha123


Certifique-se de criar o banco de dados e as tabelas necessárias no seu MySQL antes de rodar a aplicação.

Exemplo de script SQL base

```
CREATE DATABASE coursejdbc;
USE coursejdbc;
  
CREATE TABLE department (
    Id int(11) NOT NULL AUTO_INCREMENT,
    Name varchar(60) DEFAULT NULL,
    PRIMARY KEY (Id)
  );
  
CREATE TABLE seller (
    Id int(11) NOT NULL AUTO_INCREMENT,
    Name varchar(60) NOT NULL,
    Email varchar(100) NOT NULL,
    BirthDate datetime NOT NULL,
    BaseSalary double NOT NULL,
    DepartmentId int(11) NOT NULL,
    PRIMARY KEY (Id),
    FOREIGN KEY (DepartmentId) REFERENCES department (Id)
  );
  
INSERT INTO department (Name) VALUES 
  ('Computers'),
  ('Electronics'),
  ('Fashion'),
  ('Books');

INSERT INTO seller (Name, Email, BirthDate, BaseSalary, DepartmentId)
VALUES
('Ana Souza', 'ana.souza@example.com', '1988-11-23 00:00:00', 4200.00, 2),
('Carlos Pereira', 'carlos.pereira@example.com', '1995-02-08 00:00:00', 3000.00, 1),
('Julia Andrade', 'julia.andrade@example.com', '1990-07-19 00:00:00', 4600.00, 3),
('Renato Lima', 'renato.lima@example.com', '1985-09-03 00:00:00', 5100.00, 2);
```
