# Revisão avaliação 1

Este documento é um guia rápido com os conceitos fundamentais de bancos de dados e comandos SQL para gerenciar e manipular dados.

---

### 1. Conceitos Fundamentais

Um **Banco de Dados** é um sistema para armazenar e organizar dados. Sua estrutura é hierárquica e pode ser resumida da seguinte forma:

* **TABELAS:** Estruturas que organizam os dados por tópicos. Uma tabela de "Pessoas" guarda informações sobre pessoas, uma de "Produtos" guarda informações sobre produtos, e assim por diante.
* **REGISTROS (ou Linhas):** Cada registro é uma linha na tabela e representa uma única entidade (por exemplo, uma pessoa, um produto).
* **CAMPOS (ou Colunas):** Cada campo é uma coluna na tabela e define o tipo de dado que será armazenado (por exemplo, `nome`, `idade`, `peso`).

---

### 2. Linguagem SQL

**SQL** (Structured Query Language) é a linguagem padrão para interagir com bancos de dados.

#### Gerenciando Bancos de Dados

-   **Criar um banco de dados:**

    ```sql
    CREATE DATABASE Cadastro;
    ```

-   **Ver todos os bancos de dados existentes:**

    ```sql
    SHOW DATABASES;
    ```

-   **Criar um banco de dados com suporte a caracteres especiais (UTF-8):**

    ```sql
    CREATE DATABASE Cadastro
    DEFAULT CHARACTER SET utf8
    DEFAULT COLLATE utf8_general_ci;
    ```

-   **Apagar um banco de dados:**

    ```sql
    DROP DATABASE Cadastro;
    ```

#### Gerenciando Tabelas

-   **Criar uma tabela básica:**

    ```sql
    CREATE TABLE Pessoas (
        nome varchar(30),
        idade tinyint,
        sexo char(1),
        peso float
    );
    ```

-   **Criar uma tabela com restrições e configurações avançadas:**

    ```sql
    CREATE TABLE Pessoas (
        id int NOT NULL AUTO_INCREMENT,
        nome varchar(30) NOT NULL,
        nascimento date,
        sexo enum('M', 'F'),
        peso decimal(5,2),
        altura decimal(3,2),
        nacionalidade varchar(20) DEFAULT 'Brasil',
        PRIMARY KEY (id)
    );
    ```

-   **Descrever a estrutura de uma tabela:**

    ```sql
    DESCRIBE Pessoas;
    ```

    **Resultado esperado:**
    
    | Field         | Type          | Null | Key | Default  | Extra          |
    |---------------|---------------|------|-----|----------|----------------|
    | id            | int           | NO   | PRI | NULL     | auto_increment |
    | nome          | varchar(30)   | NO   |     | NULL     |                |
    | nascimento    | date          | YES  |     | NULL     |                |
    | sexo          | enum('M','F') | YES  |     | NULL     |                |
    | peso          | decimal(5,2)  | YES  |     | NULL     |                |
    | altura        | decimal(3,2)  | YES  |     | NULL     |                |
    | nacionalidade | varchar(20)   | YES  |     | Brasil   |                |

#### Manipulando Dados

-   **Inserir um novo registro, especificando as colunas:**

    ```sql
    INSERT INTO Pessoas (nome, nascimento, sexo, peso, altura, nacionalidade)
    VALUES ('Victor', '2006-03-14', 'M', '68.5', '1.70', 'Brasil');
    ```

-   **Inserir um novo registro, seguindo a ordem das colunas da tabela:**

    > **Nota:** Use `DEFAULT` para campos com `AUTO_INCREMENT` ou `DEFAULT VALUE`.
    
    ```sql
    INSERT INTO Pessoas VALUES (
        DEFAULT,
        'Victor',
        '2006-03-14',
        'M',
        '68.5',
        '1.70',
        'Brasil'
    );
    ```

---

### 3. Tipos de Dados

O tipo de dado define o tipo de informação que um campo pode armazenar.

#### Numéricos

-   **Inteiros:** `INT`, `TINYINT`, `BIGINT`
-   **Reais:** `DECIMAL`, `FLOAT`, `DOUBLE`, `REAL`
-   **Lógico:** `BIT`, `BOOLEAN`

#### Data e Tempo

-   `DATE`: Armazena apenas a data (`YYYY-MM-DD`).
-   `DATETIME`: Armazena data e hora.
-   `TIMESTAMP`: Armazena a data e a hora do momento da criação/modificação do registro.
-   `TIME`: Armazena apenas a hora.
-   `YEAR`: Armazena o ano.

#### Literais (Texto)

-   **Caracter:** `CHAR` (tamanho fixo), `VARCHAR` (tamanho variável).
-   **Texto Longo:** `TEXT`
-   **Binário:** `BLOB`, `TINYBLOB` (para dados binários como imagens, áudios, etc.)

#### Coleções

-   `ENUM`: Permite escolher um valor de uma lista pré-definida de strings.
-   `SET`: Permite escolher um ou mais valores de uma lista pré-definida de strings.

#### Espaciais

-   `POLYGON`, `GEOMETRY` (para coordenadas geográficas).