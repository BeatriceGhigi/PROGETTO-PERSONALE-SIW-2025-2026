INSERT INTO brand (id, nome, descrizione, vegan, cruelty_free, paese, sito_web) VALUES (1, 'BioBella', 'Brand italiano specializzato in cosmetici biologici certificati.', true, true, 'Italia', 'https://www.biobella.it');
INSERT INTO brand (id, nome, descrizione, vegan, cruelty_free, paese, sito_web) VALUES (2, 'NaturePure', 'Brand francese di cosmetici naturali per la cura della pelle.', true, true, 'Francia', 'https://www.naturepure.fr');
INSERT INTO brand (id, nome, descrizione, vegan, cruelty_free, paese, sito_web) VALUES (3, 'GreenGlow', 'Marchio tedesco focalizzato su skincare sostenibile e biologica.', true, true, 'Germania', 'https://www.greenglow.de');
INSERT INTO categoria (id, nome, descrizione) VALUES (1, 'Crema Viso', 'Prodotti biologici per idratazione e cura quotidiana del viso.');
INSERT INTO categoria (id, nome, descrizione) VALUES (2, 'Detergente Viso', 'Prodotti naturali per la pulizia delicata della pelle.');
INSERT INTO categoria (id, nome, descrizione) VALUES (3, 'Burrocacao', 'Prodotti bio per protezione e nutrizione delle labbra.');
INSERT INTO ingrediente (id, nome, descrizione, funzione, naturale) VALUES (1, 'Aloe Vera', 'Gel naturale con proprietà idratanti e lenitive.', 'IDRATANTE', true);
INSERT INTO ingrediente (id, nome, descrizione, funzione, naturale) VALUES (2, 'Camomilla', 'Estratto vegetale usato per calmare la pelle sensibile.', 'LENITIVO', true);
INSERT INTO ingrediente (id, nome, descrizione, funzione, naturale) VALUES (3, 'Olio di Argan', 'Olio vegetale ricco di vitamina E e acidi grassi.', 'EMOLLIENTE', true);
INSERT INTO prodotto(id, nome, descrizione, prezzo, formato, vegan, cruelty_free, skin_type, brand_id, categoria_id) VALUES(1, 'Crema Viso Aloe Bio', 'Crema viso biologica idratante con aloe vera, adatta all uso quotidiano.', 12.90, '50ml', true, true, 'SECCA', 1, 1);
INSERT INTO prodotto(id, nome, descrizione, prezzo, formato, vegan, cruelty_free, skin_type, brand_id, categoria_id) VALUES(2, 'Detergente Delicato Camomilla', 'Detergente viso naturale con camomilla, ideale per pelli sensibili.', 9.90, '200ml', true, true, 'SENSIBILE', 2, 2);
INSERT INTO prodotto(id, nome, descrizione, prezzo, formato, vegan, cruelty_free, skin_type, brand_id, categoria_id) VALUES(3, 'Burrocacao Argan Natural', 'Burrocacao biologico nutriente con olio di argan.', 4.50, '5ml', true, true, 'NORMALE', 3, 3);


