# EducaFlow - Sistema de Gerenciamento Escolar

Este projeto é uma aplicação Spring Boot que gerencia o relacionamento entre **Alunos** e **Cursos**, utilizando MariaDB como banco de dados. Desenvolvido como parte da avaliação prática de persistência de dados.

---

## 1. Objetivo

Implementar uma aplicação que utiliza Spring Boot, Spring Data JPA, Lombok, DevTools e Spring Web, aplicando conceitos de mapeamento objeto-relacional e CRUD completo para duas entidades distintas.

---

## 2. Descrição do Exercício

Baseado no exemplo de Empregado/Departamento, foi escolhido um cenário escolar com as entidades **Aluno** e **Curso**, seguindo os passos:

1. Configurar XAAMP/MariaDB.
2. Iniciar projeto no Spring Initializr com dependências:
   - Spring Web
   - Spring Data JPA
   - MariaDB Driver
   - Lombok
   - DevTools
3. Definir entidades JPA com Lombok.
4. Configurar relacionamento Many-to-Many.
5. Criar repositórios JpaRepository.
6. Implementar serviços com transações e lógica de CRUD.
7. Expor endpoints REST em controllers.
8. Testar via Postman/Bruno.
9. Validar persistência e integridade via SQL.

---

## 3. Tecnologias Utilizadas

- Java 17
- Spring Boot 3.x
- Spring Data JPA (Hibernate)
- MariaDB
- Lombok
- Spring Web
- Spring Boot DevTools
- Maven

---

## 4. Modelagem de Entidades

### 4.1 Aluno

- `id`: Long (PK)
- `nome`: String
- `email`: String (unique)
- `matricula`: String (unique)
- `cursos`: Set\<Curso\> (Many-to-Many)

### 4.2 Curso

- `id`: Long (PK)
- `nome`: String
- `descricao`: String
- `cargaHoraria`: int
- `alunos`: Set\<Aluno\> (Many-to-Many)

---

## 5. Arquitetura do Projeto

```
src/
└── main/
    ├── java/com/example/educaflow/
    │   ├── controller/       # REST Controllers
    │   ├── domain/
    │   │   ├── model/        # JPA Entities
    │   │   └── repository/   # JpaRepository interfaces
    │   ├── service/          # Business logic
    │   └── EducaFlowApplication.java
    └── resources/
        ├── application.yml   # Configurações do Spring e DB
        └── data.sql          # Script de dados iniciais
```

---

## 6. Endpoints REST

- **Cursos** (`/api/cursos`)
  - `GET` → listar todos
  - `GET /{id}` → buscar por ID
  - `POST` → criar
  - `PUT /{id}` → atualizar
  - `DELETE /{id}` → remover

- **Alunos** (`/api/alunos`)
  - `GET` → listar todos
  - `GET /{id}` → buscar por ID
  - `POST` → criar
  - `PUT /{id}` → atualizar
  - `DELETE /{id}` → remover

---

## 7. Configuração do Banco de Dados

Arquivo `application.yml`:
```yaml
spring:
  datasource:
    url: jdbc:mariadb://localhost:3306/escolaDB
    username: root
    password:
    driver-class-name: org.mariadb.jdbc.Driver
  jpa:
    hibernate:
      ddl-auto: update
    show-sql: true
    database-platform: org.hibernate.dialect.MariaDBDialect
server:
  port: 8080
```

Script para criação do schema:
```sql
CREATE DATABASE IF NOT EXISTS escolaDB
  CHARACTER SET utf8mb4
  COLLATE utf8mb4_unicode_ci;
```

---

## 8. Passo a Passo para Execução

1. Inicie o MariaDB via XAMPP.
2. Crie o banco `escolaDB`.
3. Clone o repositório:
   ```bash
   git clone https://github.com/seuusuario/educaflow.git
   cd educaflow
   ```
4. Importe a coleção Postman (`EducaFlow.postman_collection.json`).
5. Execute o projeto:
   ```bash
   mvn spring-boot:run
   ```
6. Teste as requisições no Postman.

---

## 9. Análise de Conformidade

| Requisito                                                       | Implementação                              | Status  |
|-----------------------------------------------------------------|--------------------------------------------|---------|
| Configurar XAAMP e MariaDB                                      | Configuração no `application.yml` e schema | ✅       |
| Dependências Spring Web, JPA, Lombok, DevTools                  | Declaradas no `pom.xml`                    | ✅       |
| Entidades com Lombok                                            | `@Data`, `@Builder`, `@NoArgsConstructor`  | ✅       |
| Relacionamento JPA Many-to-Many                                 | Mapeado em `Aluno` e `Curso`               | ✅       |
| Repositórios estendendo JpaRepository                           | `AlunoRepository`, `CursoRepository`       | ✅       |
| Serviços com CRUD e transações                                  | `AlunoService`, `CursoService`             | ✅       |
| Controllers REST para CRUD                                      | `AlunoController`, `CursoController`       | ✅       |
| Testes via Postman                                              | Coleção Postman provida                     | ✅       |
| Validação de integridade no banco                               | Script `data.sql` e SQL logs (`show-sql`)  | ✅       |
| README profissional com instruções                              | Esta documentação                          | ✅       |

Todos os pontos do enunciado foram contemplados, garantindo correção técnica, boas práticas, organização e conformidade com arquitetura limpa.

---

## 10. Contribuição e Licença

Contribuições são bem-vindas. Abra _issues_ ou _pull requests_.  
Este projeto está licenciado sob a [MIT License](LICENSE).
