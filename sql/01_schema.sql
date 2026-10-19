-- script de criar o banco

SET NAMES utf8mb4; -- Sem isso vai bugar os acentos tudo blz

CREATE DATABASE IF NOT EXISTS contacerta
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE contacerta;

-- crud simples 1
CREATE TABLE categoria (
    id_categoria INT          NOT NULL AUTO_INCREMENT,
    nome         VARCHAR(50)  NOT NULL,
    descricao    VARCHAR(150),
    CONSTRAINT pk_categoria      PRIMARY KEY (id_categoria),
    CONSTRAINT uk_categoria_nome UNIQUE (nome)
);

-- crud simples 2
CREATE TABLE forma_pagamento (
    id_forma_pagamento INT         NOT NULL AUTO_INCREMENT,
    nome               VARCHAR(30) NOT NULL,
    CONSTRAINT pk_forma_pagamento      PRIMARY KEY (id_forma_pagamento),
    CONSTRAINT uk_forma_pagamento_nome UNIQUE (nome)
);

-- crud simples 3
CREATE TABLE conta (
    id_conta      INT           NOT NULL AUTO_INCREMENT,
    nome          VARCHAR(50)   NOT NULL,
    banco         VARCHAR(50),
    saldo_inicial DECIMAL(10,2) NOT NULL DEFAULT 0,
    CONSTRAINT pk_conta      PRIMARY KEY (id_conta),
    CONSTRAINT uk_conta_nome UNIQUE (nome)
);

-- crud simples 4
CREATE TABLE estabelecimento (
    id_estabelecimento INT         NOT NULL AUTO_INCREMENT,
    nome               VARCHAR(80) NOT NULL,
    cidade             VARCHAR(60),
    CONSTRAINT pk_estabelecimento PRIMARY KEY (id_estabelecimento)
);

-- crud principal (o restrict nao deixa apagar registros com gasto ligado)
CREATE TABLE gasto (
    id_gasto           INT           NOT NULL AUTO_INCREMENT,
    descricao          VARCHAR(150)  NOT NULL,
    valor              DECIMAL(10,2) NOT NULL,
    data_gasto         DATE          NOT NULL,
    id_categoria       INT           NOT NULL,
    id_forma_pagamento INT           NOT NULL,
    id_conta           INT           NOT NULL,
    id_estabelecimento INT           NOT NULL,
    CONSTRAINT pk_gasto PRIMARY KEY (id_gasto),
    CONSTRAINT ck_gasto_valor CHECK (valor > 0),
    CONSTRAINT fk_gasto_categoria FOREIGN KEY (id_categoria)
        REFERENCES categoria (id_categoria)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_gasto_forma_pagamento FOREIGN KEY (id_forma_pagamento)
        REFERENCES forma_pagamento (id_forma_pagamento)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_gasto_conta FOREIGN KEY (id_conta)
        REFERENCES conta (id_conta)
        ON UPDATE CASCADE ON DELETE RESTRICT,
    CONSTRAINT fk_gasto_estabelecimento FOREIGN KEY (id_estabelecimento)
        REFERENCES estabelecimento (id_estabelecimento)
        ON UPDATE CASCADE ON DELETE RESTRICT
);

CREATE INDEX idx_gasto_data ON gasto (data_gasto);
