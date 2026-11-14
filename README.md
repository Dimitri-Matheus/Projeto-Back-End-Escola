# Escola API

API RESTful para gerenciar **matrículas escolares** e as **salas** de uma instituição de ensino.

O projeto é organizado em dois domínios independentes, cada um com sua própria rota base:

| Domínio | Rota base | Responsabilidade |
|---|---|---|
| Matrículas | `/matriculas` | Gerenciar as matrículas dos alunos |
| Salas | `/salas` | Gerenciar as salas da instituição |

## Tecnologias

- Java 21
- Spring Boot 4.1.2
- Spring Web MVC (API REST)
- Spring Data JPA / Hibernate
- Bean Validation
- MySQL
- SpringDoc OpenAPI (Swagger UI)
- Lombok
- Maven

## Domínios

### Matrículas — `/matriculas`

Representa a matrícula de um aluno em um curso.

| Campo | Descrição |
|---|---|
| Nome do aluno | Nome do aluno matriculado |
| Nome do curso | Curso em que o aluno se matriculou |
| Turma | Turma à qual a matrícula pertence |
| Data da matrícula | Data em que a matrícula foi realizada |

### Salas — `/salas`

Representa uma sala física da instituição.

| Campo | Descrição |
|---|---|
| Identificação da sala | Identificador da sala |
| Bloco | Bloco/prédio onde a sala está localizada |
| Tipo de sala | Tipo da sala (ex.: sala de aula, laboratório) |
| Capacidade | Capacidade de pessoas da sala |

## Endpoints

Ambos os recursos seguem o padrão REST:

| Método | Matrículas | Salas | Descrição |
|---|---|---|---|
| `GET` | `/matriculas` | `/salas` | Lista todos os registros |
| `GET` | `/matriculas/{id}` | `/salas/{id}` | Busca um registro pelo ID |
| `POST` | `/matriculas` | `/salas` | Cria um novo registro |
| `PUT` | `/matriculas/{id}` | `/salas/{id}` | Atualiza um registro existente |
| `DELETE` | `/matriculas/{id}` | `/salas/{id}` | Remove um registro |

## Documentação (Swagger)

Com a aplicação em execução, a documentação interativa da API fica disponível em:

```
http://localhost:8080/swagger-ui/index.html
```

## Mapeamento do Banco de Dados
<img width="4947" height="3976" alt="Diagram 2" src="https://github.com/user-attachments/assets/0fa6ae5e-6368-40ed-be40-0af05a52784a" />

[Visualização Online](https://app.chartdb.io/diagram/bed05aaed1704c5e88310f)

## Licença

Distribuído sob a licença MIT.
