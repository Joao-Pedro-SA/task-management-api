# 📌 Task Management API

API REST desenvolvida em **Java com Spring Boot** para gerenciamento de atividades.
O projeto foi construído com foco em boas práticas de arquitetura backend, separação de camadas e tratamento global de exceções.

---

## 🚀 Tecnologias Utilizadas

* Java 17+
* Spring Boot
* Spring Data JPA
* Hibernate
* MySQL
* Maven
* Jakarta Validation
* Tratamento global de exceções (@RestControllerAdvice)

---

## 🏗️ Arquitetura do Projeto

O projeto segue separação por camadas:

```
controller → service → repository → model
                   ↘ dto
```

### 🔹 Controller

Responsável por expor os endpoints REST.

### 🔹 Service

Contém regras de negócio e validações.

### 🔹 Repository

Comunicação com o banco de dados via Spring Data JPA.

### 🔹 DTO

Objetos de transferência de dados para entrada e saída da API.

### 🔹 Exceptions

Tratamento centralizado de erros utilizando:

* Exceptions customizadas
* GlobalExceptionHandler
* Padronização de respostas de erro

---

## 📚 Funcionalidades Implementadas

* ✅ Criar atividade
* ✅ Buscar atividade por ID
* ✅ Buscar atividade por título
* ✅ Listar atividades
* ✅ Atualização parcial (PATCH)
* ✅ Deletar atividade
* ✅ Validação de campos com Bean Validation
* ✅ Tratamento global de exceções
* ✅ Respostas de erro padronizadas (404, 400, etc.)

---

## 📌 Exemplo de Requisição

### Criar Atividade

POST /activities

```json
{
  "title": "Estudar Spring",
  "description": "Revisar tratamento de exceções",
  "status": "PENDING"
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

## 🎯 Objetivo do Projeto

Este projeto foi desenvolvido com foco em evolução técnica para backend Java, aplicando:

* Boas práticas de organização
* Separação de responsabilidades
* Tratamento estruturado de erros
* Uso correto de DTOs

---

## 📌 Próximas Melhorias

* Implementação de paginação
* Testes unitários com JUnit
* Autenticação com Spring Security
* Dockerização da aplicação
* Deploy em ambiente cloud

---

## 👨‍💻 Autor

João Pedro
Estudante de Análise e Desenvolvimento de Sistemas
Foco em desenvolvimento backend Java
