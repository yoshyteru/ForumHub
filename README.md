# 🚀 ForumHub API

API RESTful desenvolvida como desafio do programa ONE (Oracle + Alura) - Formação Spring Boot 3.  
Sistema de fórum completo com autenticação JWT, CRUD de tópicos, respostas e cursos, utilizando Spring Security, Flyway migrations e MySQL.

![Spring Boot](https://img.shields.io/badge/Spring_Boot-3.2.0-green.svg)
![Java](https://img.shields.io/badge/Java-17-orange.svg)
![MySQL](https://img.shields.io/badge/MySQL-8.0-blue.svg)
![JWT](https://img.shields.io/badge/JWT-Auth0-red.svg)
![License](https://img.shields.io/badge/license-MIT-blue.svg)

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Tecnologias](#-tecnologias)
- [Pré-requisitos](#%EF%B8%8F-pré-requisitos)
- [Configuração do Banco de Dados](#-configuração-do-banco-de-dados)

## 🎯 Sobre o Projeto

O ForumHub é uma API REST que simula um fórum de discussão técnico, permitindo:

✅ Cadastro e autenticação de usuários com JWT  
✅ CRUD completo de tópicos (criar, listar, detalhar, atualizar, deletar)  
✅ Validação de dados e regras de negócio  
✅ Paginação e ordenação de resultados  
✅ Filtros por curso e ano  
✅ Tratamento global de exceções  
✅ Versionamento de banco com Flyway  

## 🛠 Tecnologias

| Tecnologia       | Versão   | Descrição                          |
|------------------|----------|------------------------------------|
| Spring Boot      | 3.2.0    | Framework principal                |
| Spring Security  | 6.x      | Autenticação e autorização         |
| Spring Data JPA  | 3.x      | Acesso ao banco de dados           |
| JWT (Auth0)      | 4.4.0    | Tokens de autenticação             |
| MySQL            | 8.0      | Banco de dados relacional          |
| Flyway           | 10.x     | Migrations do banco                |
| Lombok           | 1.18.x   | Redução de boilerplate             |
| Maven            | 3.9+     | Gerenciamento de dependências      |

## ⚙️ Pré-requisitos

Antes de começar, verifique se você tem:

- [ ] Java 17+ instalado
- [ ] MySQL 8.0+ instalado e rodando
- [ ] Maven instalado (ou use o wrapper do projeto)
- [ ] IntelliJ IDEA (Community ou Ultimate)
- [ ] Postman (para testar a API)

## 🔧 Configuração do Banco de Dados

1. Acesse o MySQL:
   ```bash
   mysql -u root -p

## Crie o banco de dados:

## sql
- CREATE DATABASE forumhub;
- Configure as credenciais no arquivo src/main/resources/application.properties:

- properties
- spring.datasource.url=jdbc:mysql://localhost:3306/forumhub?useSSL=false&createDatabaseIfNotExist=true&serverTimezone=UTC
- spring.datasource.username=root
- spring.datasource.password=sua_senha
- spring.jpa.show-sql=true
- spring.jpa.properties.hibernate.format_sql=true

- api.security.token.secret=${JWT_SECRET:12345678}
## ▶️ Como Executar
Com Maven
bash
# Clonar o repositório
git clone https://github.com/seu-usuario/forumhub-api.git

# Entrar no diretório
cd forumhub-api

# Executar a aplicação
./mvnw spring-boot:run
A API estará disponível em http://localhost:8080
