
CREATE TABLE item_venda
(
  id NUMBER NOT NULL,
  CONSTRAINT PK_item_venda PRIMARY KEY (id)
);

CREATE TABLE produto
(
  id    NUMBER NOT NULL,
  preco       ,
  CONSTRAINT PK_produto PRIMARY KEY (id)
);

CREATE TABLE usuario
(
  id NUMBER NOT NULL,
  CONSTRAINT PK_usuario PRIMARY KEY (id)
);

CREATE TABLE venda
(
  id         NUMBER    NOT NULL,
  usuario_id NUMBER    NOT NULL,
  data_venda TIMESTAMP,
  CONSTRAINT PK_venda PRIMARY KEY (id)
);

ALTER TABLE venda
  ADD CONSTRAINT FK_usuario_TO_venda
    FOREIGN KEY (usuario_id)
    REFERENCES usuario (id);
