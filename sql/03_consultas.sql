-- consultas de referencias para os daos q tem q fazer ainda 

USE contacerta;

-- join de gastos com nomes no lugar dos códigos
SELECT g.id_gasto,
       g.data_gasto,
       g.descricao,
       g.valor,
       c.nome  AS categoria,
       f.nome  AS forma_pagamento,
       ct.nome AS conta,
       e.nome  AS estabelecimento
FROM gasto g
INNER JOIN categoria       c  ON c.id_categoria       = g.id_categoria
INNER JOIN forma_pagamento f  ON f.id_forma_pagamento = g.id_forma_pagamento
INNER JOIN conta           ct ON ct.id_conta          = g.id_conta
INNER JOIN estabelecimento e  ON e.id_estabelecimento = g.id_estabelecimento
ORDER BY g.data_gasto DESC;

-- relatorio de total por categoria no período
SELECT c.nome       AS categoria,
       COUNT(*)     AS quantidade,
       SUM(g.valor) AS total
FROM gasto g
INNER JOIN categoria c ON c.id_categoria = g.id_categoria
WHERE g.data_gasto BETWEEN '2026-10-01' AND '2026-10-31'
GROUP BY c.nome
ORDER BY total DESC;

-- total geral do periodo
SELECT SUM(valor) AS total_geral
FROM gasto
WHERE data_gasto BETWEEN '2026-10-01' AND '2026-10-31';
