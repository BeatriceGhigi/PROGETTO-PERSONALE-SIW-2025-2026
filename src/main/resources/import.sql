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


-- Altri 2 brand (id 4, 5)
INSERT INTO brand (nome, descrizione, paese, sito_web) VALUES ('PureLeaf', 'Brand britannico di cosmesi naturale a basso impatto ambientale.', 'Regno Unito', 'https://www.pureleaf.co.uk');
INSERT INTO brand (nome, descrizione, paese, sito_web) VALUES ('Veganza', 'Linea spagnola di skincare 100% vegana e cruelty free.', 'Spagna', 'https://www.veganza.es');

-- Altre 2 categorie (id 4, 5)
INSERT INTO categoria (nome, descrizione) VALUES ('Shampoo Solido', 'Shampoo in formato solido, senza plastica e a basso impatto.');
INSERT INTO categoria (nome, descrizione) VALUES ('Maschera Viso', 'Trattamenti intensivi settimanali per il viso.');

-- Altri 3 ingredienti (id 4, 5, 6)
INSERT INTO ingrediente (nome, descrizione, funzione, naturale) VALUES ('Olio di Cocco', 'Olio vegetale nutriente, ricco di acidi grassi saturi.', 'EMOLLIENTE', true);
INSERT INTO ingrediente (nome, descrizione, funzione, naturale) VALUES ('Acido Ialuronico', 'Molecola idratante in grado di trattenere grandi quantità di acqua.', 'IDRATANTE', false);
INSERT INTO ingrediente (nome, descrizione, funzione, naturale) VALUES ('Tè Verde', 'Estratto vegetale ricco di polifenoli dall''azione antiossidante.', 'ANTIOSSIDANTE', true);

-- Altri 5 prodotti (id 4..8)
INSERT INTO prodotto(nome, descrizione, prezzo, formato, vegan, cruelty_free, skin_type, brand_id, categoria_id) VALUES('Crema Notte Rigenerante', 'Crema viso ricca per la notte, formulata per pelli miste.', 15.90, '50ml', true, true, 'MISTA', 3, 1);
INSERT INTO prodotto(nome, descrizione, prezzo, formato, vegan, cruelty_free, skin_type, brand_id, categoria_id) VALUES('Maschera Argilla Verde', 'Maschera purificante all''argilla verde per pelli grasse.', 8.50, '100ml', true, true, 'GRASSA', 2, 5);
INSERT INTO prodotto(nome, descrizione, prezzo, formato, vegan, cruelty_free, skin_type, brand_id, categoria_id) VALUES('Shampoo Solido Cocco', 'Shampoo solido nutriente all''olio di cocco, zero plastica.', 11.00, '80g', true, true, 'NORMALE', 4, 4);
INSERT INTO prodotto(nome, descrizione, prezzo, formato, vegan, cruelty_free, skin_type, brand_id, categoria_id) VALUES('Siero Acido Ialuronico', 'Siero viso ad alta concentrazione di acido ialuronico.', 19.90, '30ml', true, true, 'SECCA', 5, 1);
INSERT INTO prodotto(nome, descrizione, prezzo, formato, vegan, cruelty_free, skin_type, brand_id, categoria_id) VALUES('Detergente Tè Verde', 'Detergente viso quotidiano con estratto di tè verde.', 10.50, '150ml', true, true, 'MISTA', 1, 2);

-- Altri 2 utenti (id 3, 4)
INSERT INTO utente (username, password, ruolo, skin_type) VALUES ('giulia', '$2b$10$0pwGs.WGOqZBTunkLLVfKuW05Y5xKWoIRGYv/HgezDSa22BXC4nZW', 'USER', 'SECCA');
INSERT INTO utente (username, password, ruolo, skin_type) VALUES ('luca', '$2b$10$0pwGs.WGOqZBTunkLLVfKuW05Y5xKWoIRGYv/HgezDSa22BXC4nZW', 'USER', 'GRASSA');

-- Collegamenti prodotto <-> ingrediente (tabella di join del @ManyToMany).

INSERT INTO prodotto_ingrediente (prodotto_id, ingrediente_id) VALUES (1, 1); -- Crema Viso Aloe Bio - Aloe Vera
INSERT INTO prodotto_ingrediente (prodotto_id, ingrediente_id) VALUES (2, 2); -- Detergente Camomilla - Camomilla
INSERT INTO prodotto_ingrediente (prodotto_id, ingrediente_id) VALUES (3, 3); -- Burrocacao Argan - Olio di Argan
INSERT INTO prodotto_ingrediente (prodotto_id, ingrediente_id) VALUES (4, 3); -- Crema Notte - Olio di Argan
INSERT INTO prodotto_ingrediente (prodotto_id, ingrediente_id) VALUES (4, 5); -- Crema Notte - Acido Ialuronico
INSERT INTO prodotto_ingrediente (prodotto_id, ingrediente_id) VALUES (5, 2); -- Maschera Argilla - Camomilla
INSERT INTO prodotto_ingrediente (prodotto_id, ingrediente_id) VALUES (6, 4); -- Shampoo Solido - Olio di Cocco
INSERT INTO prodotto_ingrediente (prodotto_id, ingrediente_id) VALUES (7, 5); -- Siero Ialuronico - Acido Ialuronico
INSERT INTO prodotto_ingrediente (prodotto_id, ingrediente_id) VALUES (8, 6); -- Detergente Tè Verde - Tè Verde
INSERT INTO prodotto_ingrediente (prodotto_id, ingrediente_id) VALUES (8, 1); -- Detergente Tè Verde - Aloe Vera


INSERT INTO recensione (voto, commento, data_creazione, data_ultima_modifica, prodotto_id, utente_id) VALUES (5, 'Ottima crema, assorbita subito e non unge.', '2026-05-10 09:30:00', '2026-05-10 09:30:00', 1, 2);
INSERT INTO recensione (voto, commento, data_creazione, data_ultima_modifica, prodotto_id, utente_id) VALUES (4, 'Delicato sulla pelle sensibile, profumo leggero.', '2026-05-12 18:05:00', '2026-05-12 18:05:00', 2, 3);
INSERT INTO recensione (voto, commento, data_creazione, data_ultima_modifica, prodotto_id, utente_id) VALUES (5, 'Il migliore burrocacao che abbia mai provato.', '2026-05-14 08:15:00', '2026-05-14 08:15:00', 3, 4);
INSERT INTO recensione (voto, commento, data_creazione, data_ultima_modifica, prodotto_id, utente_id) VALUES (4, 'Ottima come crema notte, un po'' cara.', '2026-05-20 22:40:00', '2026-05-20 22:40:00', 4, 2);
INSERT INTO recensione (voto, commento, data_creazione, data_ultima_modifica, prodotto_id, utente_id) VALUES (3, 'Buona ma vorrei una texture più densa.', '2026-06-01 12:00:00', '2026-06-01 12:00:00', 5, 3);
INSERT INTO recensione (voto, commento, data_creazione, data_ultima_modifica, prodotto_id, utente_id) VALUES (5, 'Finalmente uno shampoo solido che fa schiuma vera!', '2026-06-03 17:20:00', '2026-06-03 17:20:00', 6, 4);


INSERT INTO wishlist (data_aggiunta, utente_id, prodotto_id) VALUES ('2026-06-05 10:00:00', 2, 7);
INSERT INTO wishlist (data_aggiunta, utente_id, prodotto_id) VALUES ('2026-06-06 11:30:00', 3, 5);
INSERT INTO wishlist (data_aggiunta, utente_id, prodotto_id) VALUES ('2026-06-07 09:15:00', 4, 8);