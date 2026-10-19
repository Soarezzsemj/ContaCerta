# ContaCerta

Rastreador de gastos pessoais desenvolvido como trabalho prático de **Programação Orientada a Objetos**
(Universidade Católica de Brasília).

Sistema desktop em **Java 24** com **Swing (JFrame)**, padrão **MVC**, banco **MySQL** e acesso via **JDBC**.

## Equipe

- Carlos
- Daniel
- Marcos
- Pedro

## Sobre o sistema

O ContaCerta registra gastos do dia a dia e mostra para onde o dinheiro está indo.

| Requisito | No ContaCerta |
|---|---|
| CRUD simples 1 | Categoria (Alimentação, Transporte, Lazer...) |
| CRUD simples 2 | Forma de pagamento (Pix, Dinheiro, Cartão...) |
| CRUD simples 3 | Conta (Carteira, Nubank...) |
| CRUD simples 4 | Estabelecimento (Supermercado, Posto, iFood...) |
| **CRUD principal** | **Gasto** (relaciona as 4 tabelas acima) |
| Consulta com JOIN | Listagem de gastos com os nomes de categoria, conta, forma de pagamento e estabelecimento |
| Relatório | Total gasto por categoria em um período |

## Tecnologias

- Java 24 (JDK 24)
- NetBeans com Apache Ant
- Java Swing (JFrame)
- MySQL 8.4 rodando em Docker
- MySQL Connector/J 8.0.33 (JDBC)

## Estrutura do projeto

```
ContaCerta/
├── lib/                       # driver MySQL (mysql-connector-j-8.0.33.jar)
├── nbproject/                 # configuração do projeto no NetBeans
├── sql/
│   ├── 01_schema.sql          # criação do banco e das tabelas
│   ├── 02_dados.sql           # dados de demonstração
│   └── 03_consultas.sql       # consultas de referência (JOIN e relatório)
├── src/br/contacerta/
│   ├── model/                 # classes de domínio (Categoria, Gasto, ...)
│   ├── dao/                   # acesso ao banco (DAO, CategoriaDAO, GastoDAO, ...)
│   ├── controller/            # regras e validações (CategoriaController, ...)
│   └── view/                  # telas JFrame
├── build.xml
└── docker-compose.yml         # banco MySQL
```

### Camadas (MVC)

- **Model:** representa os dados do sistema.
- **View:** telas em Swing. Só mostram dados e repassam as ações do usuário.
- **Controller:** valida os dados e faz a ponte entre a tela e o banco.
- **DAO:** executa o SQL no MySQL via JDBC.

Fluxo de uma ação: `View → Controller → DAO → MySQL`

## Como rodar

### Pré-requisitos

- JDK 24
- NetBeans
- Docker Desktop

### 1. Clonar o repositório

```bash
git clone https://github.com/Soarezzsemj/ContaCerta.git
```

### 2. Subir o banco de dados

Com o Docker Desktop aberto, na pasta do projeto:

```bash
docker compose up -d
```

Na primeira execução, os scripts da pasta `sql/` criam as tabelas e inserem os dados de demonstração automaticamente.

### 3. Abrir no NetBeans

**File > Open Project** e selecione a pasta `ContaCerta`.
Confira se o `mysql-connector-j-8.0.33.jar` aparece em **Libraries**.
Se não aparecer: botão direito em **Libraries > Add JAR/Folder** e selecione o jar da pasta `lib/`.

### 4. Executar

Rode o projeto pelo NetBeans (**F6**).

## Banco de dados

| Campo | Valor |
|---|---|
| Host | `localhost` |
| Porta | `3307` |
| Banco | `contacerta` |
| Usuário | `root` |
| Senha | `root` |

URL JDBC: `jdbc:mysql://localhost:3307/contacerta`

> A porta é **3307** (e não a padrão 3306) para não conflitar com um MySQL já instalado no computador.

### Comandos úteis do Docker

| Comando | O que faz |
|---|---|
| `docker compose up -d` | Liga o banco |
| `docker compose down` | Desliga o banco (os dados continuam salvos) |
| `docker compose down -v` | Apaga o banco. No próximo `up`, os scripts de `sql/` rodam de novo |
| `docker ps` | Mostra se o container está rodando |
