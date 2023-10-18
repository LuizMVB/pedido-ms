CREATE TABLE pedido (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    valor_total NUMERIC(8, 2) NOT NULL,
    status VARCHAR(9) NOT NULL,
    data_inclusao DATETIME NOT NULL,
    data_atualizacao DATETIME DEFAULT NULL,
    PRIMARY KEY(id)
);