INSERT INTO tb_categoria (nome) VALUES ('Celular');

INSERT INTO tb_usuario (data_hora_cadastro, login, senha) VALUES (CURRENT_TIMESTAMP(), 'igor@email.com', '$2a$10$6cANkLnoJwlkh/WjsO8WZO4jylUg2gphZwCfDXZjvZUXyZkxE4bA.');

INSERT INTO tb_produto (data_hora_cadastro, nome, descricao, qtd_disponivel, valor, categoria_id, usuario_id) VALUES (CURRENT_TIMESTAMP(), 'Samsumg S20', 'Celular de última geração', 26, 2999.90, 1, 1);