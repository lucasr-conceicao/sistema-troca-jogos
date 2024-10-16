INSERT INTO tb_usuario (id, username, password, nome, email, preferencia_de_troca) VALUES
('56ec3fa4-74a8-40b3-9974-bd2770edc987', 'luquinha98', 'Ku8&*', 'Lucas', 'lucas@hotmail.com', 'Correio'),
('8a065017-bd3b-4dbf-acb9-9ac6ea323aa6', 'roberta99', 'Ro9&*', 'Roberta', 'roberta@hotmail.com', 'Presencial');

INSERT INTO tb_jogos (id, titulo, descricao, data_cadastro, usuario_id) VALUES
('5c9ae35d-d1c4-4a90-a9fb-1c1f4c0284cf', 'F1 2024', 'Formula 1', '2024-10-13 16:18:12', (SELECT id FROM tb_usuario WHERE username = 'luquinha98')),
('db1b4a41-320b-40ab-a1a4-a21321114cc3', 'FC 24', 'Futebol Clube 2024', '2024-10-13 16:18:12', (SELECT id FROM tb_usuario WHERE username = 'roberta99'));

INSERT INTO tb_proposta_troca (id, status_troca, data_criacao, jogo_oferecido_id, jogo_desejado_id, usuario_ofertante_id, usuario_destinatario_id) VALUES
('13b23f87-17de-45c9-a4ef-f89e7e743c0c', 'PENDENTE', '2024-10-14 01:35:33', '5c9ae35d-d1c4-4a90-a9fb-1c1f4c0284cf', 'db1b4a41-320b-40ab-a1a4-a21321114cc3', '56ec3fa4-74a8-40b3-9974-bd2770edc987', '8a065017-bd3b-4dbf-acb9-9ac6ea323aa6');

INSERT INTO tb_mensagens (id, conteudo, data_envio_mensagem, trade_id, usuario_id) VALUES
('a1e2d3f4-1234-5678-90ab-cdef12345678', 'Esta é uma mensagem de exemplo', '2024-10-14 01:35:33', '13b23f87-17de-45c9-a4ef-f89e7e743c0c', '56ec3fa4-74a8-40b3-9974-bd2770edc987');
