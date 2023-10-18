CREATE TABLE item (
    id BIGINT(20) NOT NULL AUTO_INCREMENT,
    valor NUMERIC(8, 2) NOT NULL,
    id_pedido BIGINT(20) NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(id_pedido) REFERENCES pedido(id)
);