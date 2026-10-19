-- fazwendo os inserts do banco para testes p n ter q ficar subindo tudo dnv toda vez

SET NAMES utf8mb4; -- p nao bugar os acentooooosss
USE contacerta;

INSERT INTO categoria (nome, descricao) VALUES
    ('Alimentação', 'Mercado, restaurantes e lanches'),
    ('Transporte',  'Combustível, aplicativos e transporte público'),
    ('Lazer',       'Cinema, shows, jogos e passeios'),
    ('Moradia',     'Aluguel, contas de luz, água e internet'),
    ('Saúde',       'Farmácia, consultas e academia'),
    ('Educação',    'Faculdade, cursos e livros');

INSERT INTO forma_pagamento (nome) VALUES
    ('Pix'),
    ('Dinheiro'),
    ('Cartão de crédito'),
    ('Cartão de débito'),
    ('Boleto');

INSERT INTO conta (nome, banco, saldo_inicial) VALUES
    ('Carteira',         NULL,              150.00),
    ('Nubank',           'Nubank',         2500.00),
    ('Conta Salário BB', 'Banco do Brasil', 1800.00);

INSERT INTO estabelecimento (nome, cidade) VALUES
    ('Supermercado Big Box', 'Brasília'),
    ('Posto Shell',          'Brasília'),
    ('iFood',                'Online'),
    ('Cinemark',             'Brasília'),
    ('Drogasil',             'Taguatinga'),
    ('Neoenergia',           'Brasília'),
    ('Universidade Católica de Brasília', 'Taguatinga');

INSERT INTO gasto (descricao, valor, data_gasto, id_categoria, id_forma_pagamento, id_conta, id_estabelecimento) VALUES
    ('Compra do mês',            487.35, '2026-09-03', 1, 4, 2, 1),
    ('Gasolina',                 210.00, '2026-09-05', 2, 3, 2, 2),
    ('Conta de luz',             189.90, '2026-09-10', 4, 5, 3, 6),
    ('Mensalidade da faculdade', 980.00, '2026-09-10', 6, 5, 3, 7),
    ('Pizza sexta-feira',         72.50, '2026-09-12', 1, 1, 2, 3),
    ('Cinema',                    58.00, '2026-09-20', 3, 3, 2, 4),
    ('Remédio',                   34.90, '2026-09-24', 5, 2, 1, 5),
    ('Compra do mês',            512.80, '2026-10-02', 1, 4, 2, 1),
    ('Gasolina',                 195.00, '2026-10-04', 2, 3, 2, 2),
    ('Lanche',                    28.00, '2026-10-06', 1, 2, 1, 3),
    ('Conta de luz',             176.40, '2026-10-10', 4, 5, 3, 6),
    ('Mensalidade da faculdade', 980.00, '2026-10-10', 6, 5, 3, 7),
    ('Cinema com a turma',        64.00, '2026-10-11', 3, 1, 2, 4),
    ('Hambúrguer',                45.90, '2026-10-15', 1, 3, 2, 3),
    ('Vitaminas',                 89.90, '2026-10-16', 5, 4, 2, 5);
