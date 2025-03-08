CREATE TABLE categoria(
	id				UUID			PRIMARY KEY,
	nome			VARCHAR(25)		NOT NULL UNIQUE	
);
CREATE TABLE produto(
	id				UUID			PRIMARY KEY,
	nome			VARCHAR(50)		NOT NULL,
	preco			DECIMAL(10,2)	NOT NULL,
	quantidade		INT				NOT NULL,
	categoria_id	UUID			NOT NULL,
	FOREIGN KEY(categoria_id)
		REFERENCES categoria(id)
);


INSERT INTO categoria(id, nome) VALUES(gen_random_uuid(), 'Informática');
INSERT INTO categoria(id, nome) VALUES(gen_random_uuid(), 'Eletrônicos');
INSERT INTO categoria(id, nome) VALUES(gen_random_uuid(), 'Games');
INSERT INTO categoria(id, nome) VALUES(gen_random_uuid(), 'Livraria');
INSERT INTO categoria(id, nome) VALUES(gen_random_uuid(), 'Vestuário');
INSERT INTO categoria(id, nome) VALUES(gen_random_uuid(), 'Outros');
SELECT * FROM categoria
ORDER BY nome;


