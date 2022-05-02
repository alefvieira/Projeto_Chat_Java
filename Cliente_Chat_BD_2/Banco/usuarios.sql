CREATE DATABASE banco_chat;

-- CRIA USUÁRIO ADMIN 

CREATE USER 'monty'@'localhost' IDENTIFIED BY 'some_pass';
GRANT ALL PRIVILEGES ON *.* TO 'monty'@'localhost'
WITH GRANT OPTION;
 CREATE USER 'monty'@'%' IDENTIFIED BY 'some_pass';
GRANT ALL PRIVILEGES ON *.* TO 'monty'@'%'
WITH GRANT OPTION;

CREATE TABLE usuarios(
	codigo int primary key auto_increment,
	nickname varchar(100) not null unique,
	usuario varchar(20) unique not null,
	senha VARCHAR(100) not null
);

INSERT INTO `usuarios` (`codigo`, `nickname`, `usuario`, `senha`) VALUES (1, 'alef', 'alef', '202cb962ac59075b964b07152d234b70');
INSERT INTO `usuarios` (`codigo`, `nickname`, `usuario`, `senha`) VALUES (2, 'Eduardo', 'edu', '202cb962ac59075b964b07152d234b70');
INSERT INTO `usuarios` (`codigo`, `nickname`, `usuario`, `senha`) VALUES (3, 'Eric', 'eric', '202cb962ac59075b964b07152d234b70');
INSERT INTO `usuarios` (`codigo`, `nickname`, `usuario`, `senha`) VALUES (16, 'aleff', 'aleff', '202cb962ac59075b964b07152d234b70');
