# 📌 Task Management API

API REST desenvolvida em **Java com Spring Boot** para gerenciamento de atividades.
O projeto foi construído com foco em boas práticas de desenvolvimento backend, incluindo arquitetura em camadas, validação de dados, tratamento global de exceções e testes automatizados.

---

## 🚀 Tecnologias Utilizadas

* Java 17+
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Bean Validation (Jakarta Validation)
* JUnit e Mockito (testes)
* MockMvc (testes de controller)
* Tratamento global de exceções (@RestControllerAdvice)
* Docker e Docker Compose
* Swagger OpenAPI

---

## 🏗️ Arquitetura do Projeto

O projeto segue o padrão de arquitetura em camadas:

```
controller → service → repository → model
                   ↘ dto
```

### 🔹 Controller

Responsável por expor os endpoints REST e lidar com as requisições HTTP.

### 🔹 Service

Contém regras de negócio e validações da aplicação.

### 🔹 Repository

Comunicação com o banco de dados usando Spring Data JPA.

### 🔹 DTO

Objetos de transferência de dados para entrada e saída da API.

### 🔹 Exception Handling
Tratamento centralizado de erros com:
- Exceptions customizadas
- `@RestControllerAdvice`
- Respostas padronizadas para erros (400, 404, etc.)

---

## 📚 Funcionalidades Implementadas

* ✅ Criar atividade
* ✅ Buscar atividade por ID
* ✅ Buscar atividade por título
* ✅ Listar atividades com paginação (Pageable)
* ✅ Atualização parcial (PATCH)
* ✅ Deletar atividade
* ✅ Validação de campos com Bean Validation
* ✅ Tratamento global de exceções
* ✅ Respostas de erro padronizadas (404, 400, etc.)
* ✅ Testes unitários e de controller

---

## 📌 Exemplo de Requisição

### Criar Atividade

POST /activities

```json
{
  "title": "Estudar Spring",
  "description": "Revisar tratamento de exceções",
  "status": "TODO"
}
```

---

## 📌 Exemplo de Resposta de Erro (404)

```json
{
  "timestamp": "2026-03-03T10:15:30",
  "status": 404,
  "error": "Not Found",
  "message": "Atividade referente ao id (99) não encontrada",
  "path": "/activities/99"
}
```
---
## 🐳 Como executar com Docker

````
docker-compose up --build
````

 A api estará disponível em:

````
http://localhost:8080
````

---

## 🛠️ Como Executar o Projeto

1. Clonar o repositório:

```
git clone https://github.com/seu-usuario/agenda-de-atividades.git
```

2. Configurar o banco de dados no `application.properties`.

3. Executar o projeto:

```
mvn spring-boot:run
```

---

## 📖 Documentação da API

Após rodar o projeto, acesse: 

````
http://localhost:8080/swagger-ui.html
````

---

## 🎯 Objetivo do Projeto

Este projeto foi desenvolvido com foco em evolução técnica para backend Java, aplicando:

* Boas práticas de organização
* Separação de responsabilidades
* Tratamento estruturado de erros
* Integração com banco de dados
* Conteinerização com docker

---

## 👨‍💻 Autor

João Pedro
Estudante de Análise e Desenvolvimento de Sistemas
Foco em desenvolvimento backend Java
