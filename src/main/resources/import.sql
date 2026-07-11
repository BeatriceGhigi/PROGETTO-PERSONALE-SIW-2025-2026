INSERT INTO brand (nome, descrizione, paese, sito_web) VALUES ('BioBella', 'Brand italiano specializzato in cosmetici biologici certificati.', 'Italia', 'https://www.biobella.it');
INSERT INTO brand (nome, descrizione, paese, sito_web) VALUES ('NaturePure', 'Brand francese di cosmetici naturali per la cura della pelle.', 'Francia', 'https://www.naturepure.fr');
INSERT INTO brand (nome, descrizione, paese, sito_web) VALUES ('GreenGlow', 'Marchio tedesco focalizzato su skincare sostenibile e biologica.', 'Germania', 'https://www.greenglow.de');
INSERT INTO categoria (nome, descrizione) VALUES ('Crema Viso', 'Prodotti biologici per idratazione e cura quotidiana del viso.');
INSERT INTO categoria (nome, descrizione) VALUES ('Detergente Viso', 'Prodotti naturali per la pulizia delicata della pelle.');
INSERT INTO categoria (nome, descrizione) VALUES ('Burrocacao', 'Prodotti bio per protezione e nutrizione delle labbra.');
INSERT INTO ingrediente (nome, descrizione, funzione, naturale) VALUES ('Aloe Vera', 'Gel naturale con proprietà idratanti e lenitive.', 'IDRATANTE', true);
INSERT INTO ingrediente (nome, descrizione, funzione, naturale) VALUES ('Camomilla', 'Estratto vegetale usato per calmare la pelle sensibile.', 'LENITIVO', true);
INSERT INTO ingrediente (nome, descrizione, funzione, naturale) VALUES ('Olio di Argan', 'Olio vegetale ricco di vitamina E e acidi grassi.', 'EMOLLIENTE', true);
INSERT INTO prodotto(nome, descrizione, prezzo, formato, vegan, cruelty_free, skin_type, brand_id, categoria_id) VALUES('Crema Viso Aloe Bio', 'Crema viso biologica idratante con aloe vera, adatta all uso quotidiano.', 12.90, '50ml', true, true, 'SECCA', 1, 1);
INSERT INTO prodotto(nome, descrizione, prezzo, formato, vegan, cruelty_free, skin_type, brand_id, categoria_id) VALUES('Detergente Delicato Camomilla', 'Detergente viso naturale con camomilla, ideale per pelli sensibili.', 9.90, '200ml', true, true, 'SENSIBILE', 2, 2);
INSERT INTO prodotto(nome, descrizione, prezzo, formato, vegan, cruelty_free, skin_type, brand_id, categoria_id) VALUES('Burrocacao Argan Natural', 'Burrocacao biologico nutriente con olio di argan.', 4.50, '5ml', true, true, 'NORMALE', 3, 3);

INSERT INTO utente (username, password, ruolo, skin_type) VALUES ('admin', '$2b$10$0pwGs.WGOqZBTunkLLVfKuW05Y5xKWoIRGYv/HgezDSa22BXC4nZW', 'ADMIN', 'NORMALE');
INSERT INTO utente (username, password, ruolo, skin_type) VALUES ('mario', '$2b$10$0pwGs.WGOqZBTunkLLVfKuW05Y5xKWoIRGYv/HgezDSa22BXC4nZW', 'USER', 'NORMALE');