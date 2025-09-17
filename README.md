# CRUD Users API

Uma aplicação REST API completa para gerenciamento de usuários, desenvolvida com Spring Boot e suporte a múltiplos bancos de dados.

## 📋 Sobre o Projeto

Esta é uma aplicação CRUD (Create, Read, Update, Delete) que permite gerenciar usuários através de uma API REST versionada. O projeto demonstra boas práticas de desenvolvimento com Spring Boot, incluindo tratamento de exceções, validação de dados, logging e containerização.

## 🚀 Tecnologias Utilizadas

- **Java 17**
- **Spring Boot 3.1.2**
- **Spring Data JPA**
- **Spring Boot Validation**
- **Maven**
- **H2 Database** (desenvolvimento)
- **PostgreSQL** (produção)
- **Docker & Docker Compose**
- **JUnit 5** (testes)
- **JaCoCo** (cobertura de código)

## 📡 Endpoints da API

Base URL: `http://localhost:8080/api/v1`

| Método | Endpoint | Descrição |
|--------|----------|-----------|
| GET | `/users` | Lista todos os usuários |
| GET | `/users?lastName={nome}` | Filtra usuários por sobrenome |
| GET | `/users/{id}` | Busca usuário por ID |
| POST | `/users` | Cria novo usuário |
| PUT | `/users/{id}` | Atualiza usuário existente |
| DELETE | `/users/{id}` | Remove usuário |

### Exemplo de Payload (POST/PUT)
```json
{
  "userName": "joao.silva",
  "firstName": "João",
  "lastName": "Silva",
  "password": "senha123"
}
```

## 🛠️ Como Executar

### Pré-requisitos
- Java 17+
- Maven 3.6+
- Docker (opcional)

### 1. Build da Aplicação
```bash
mvn clean package
```

### 2. Executar com H2 (Desenvolvimento)
```bash
mvn spring-boot:run
```
- Console H2: http://localhost:8080/h2-console
- JDBC URL: `jdbc:h2:mem:testdb`

### 3. Executar com PostgreSQL
```bash
export SPRING_DATASOURCE_URL=jdbc:postgresql://localhost:5432/users_db
export SPRING_DATASOURCE_USERNAME=postgres
export SPRING_DATASOURCE_PASSWORD=password
export SPRING_JPA_HIBERNATE_DDL_AUTO=update
mvn spring-boot:run
```

### 4. Executar com Docker Compose
```bash
docker-compose up
```

## 🐳 Docker

### Criar Imagem
```bash
docker build -t ivancl4udio/crud-users:2.0.0 .
```

### Executar Container
```bash
docker run -p 8080:8080 ivancl4udio/crud-users:2.0.0
```

## 🧪 Testes

### Executar Testes
```bash
mvn test
```

### Gerar Relatório de Cobertura
```bash
mvn clean test -Pcoverage
```

## 📊 Monitoramento

A aplicação inclui Spring Boot Actuator para monitoramento:
- Health Check: http://localhost:8080/actuator/health
- Métricas: http://localhost:8080/actuator/metrics

## 🏗️ Estrutura do Projeto

```
src/
├── main/java/com/ivancl4udio/cruduser/
│   ├── controller/v1/          # Controllers da API v1
│   ├── service/                # Lógica de negócio
│   ├── model/                  # Entidades JPA
│   ├── repository/             # Repositórios de dados
│   ├── exception/              # Tratamento de exceções
│   └── CrudUserApplication.java
└── test/                       # Testes unitários e integração
```

## 🔧 Configurações

### Profiles Disponíveis
- `default`: H2 Database
- `prod`: PostgreSQL

### Variáveis de Ambiente
| Variável | Descrição | Padrão |
|----------|-----------|---------|
| `SPRING_DATASOURCE_URL` | URL do banco de dados | `jdbc:h2:mem:testdb` |
| `SPRING_DATASOURCE_USERNAME` | Usuário do banco | `sa` |
| `SPRING_DATASOURCE_PASSWORD` | Senha do banco | `` |
| `SPRING_JPA_HIBERNATE_DDL_AUTO` | Estratégia DDL | `create-drop` |

## 📝 Versionamento da API

A API segue versionamento semântico através da URL:
- **v1**: `/api/v1/*` - Versão atual
- Futuras versões: `/api/v2/*`, `/api/v3/*`

## 🤝 Contribuindo

1. Fork o projeto
2. Crie uma branch para sua feature (`git checkout -b feature/nova-feature`)
3. Commit suas mudanças (`git commit -m 'Adiciona nova feature'`)
4. Push para a branch (`git push origin feature/nova-feature`)
5. Abra um Pull Request

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo [LICENSE](LICENSE) para mais detalhes.

## 👨‍💻 Autor

**Ivan Cláudio** - [GitHub](https://github.com/ivancl4udio)
