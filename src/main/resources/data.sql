INSERT INTO cliente (dni, nombre, apellido, deleted) VALUES ('12345678', 'Juancho', 'Perez', false);
INSERT INTO cliente (dni, nombre, apellido, deleted) VALUES ('12345678', 'Gonzalo', 'Lopez', false);
INSERT INTO cliente (dni, nombre, apellido, deleted) VALUES ('12345678', 'Tano', 'D Elia', false);
INSERT INTO cliente (dni, nombre, apellido, deleted) VALUES ('12345678', 'Day', 'Maurin', false);
INSERT INTO cliente (dni, nombre, apellido, deleted) VALUES ('12345678', 'Nacho', 'Contreras', false);

INSERT INTO domicilio (direccion, medidor_id, tarifa, cliente_id) VALUES ('Calle falsa 123', 1, 0, 1);
INSERT INTO domicilio (direccion, medidor_id, tarifa, cliente_id) VALUES ('Av siempre viva 123', 2, 1, 2);
INSERT INTO domicilio (direccion, medidor_id, tarifa, cliente_id) VALUES ('No la se 1234', 3, 1, 3);
INSERT INTO domicilio (direccion, medidor_id, tarifa, cliente_id) VALUES ('Tampoco la se 123', 4, 0, 4);
INSERT INTO domicilio (direccion, medidor_id, tarifa, cliente_id) VALUES ('Florida 167', 5, 1, 5);

INSERT INTO medicion (facturado, consumo, fecha_hora, id_medidor, nro_serie_medidor) VALUES (false, 0.0, '2021-03-01 00:05:00.00', 1, '000023J');
INSERT INTO medicion (facturado, consumo, fecha_hora, id_medidor, nro_serie_medidor) VALUES (false, 0.0, '2021-03-01 00:05:01.00', 2, '001253X');
INSERT INTO medicion (facturado, consumo, fecha_hora, id_medidor, nro_serie_medidor) VALUES (false, 0.0, '2021-03-01 00:05:02.00', 3, '000222');
INSERT INTO medicion (facturado, consumo, fecha_hora, id_medidor, nro_serie_medidor) VALUES (false, 0.0, '2021-03-01 00:05:03.00', 4, '000032');
INSERT INTO medicion (facturado, consumo, fecha_hora, id_medidor, nro_serie_medidor) VALUES (false, 0.0, '2021-03-01 00:05:04.00', 5, '0000PPP');

INSERT INTO medicion (facturado, consumo, fecha_hora, id_medidor, nro_serie_medidor) VALUES (false, 1.0, '2021-03-01 00:10:00.00', 1, '000023J');
INSERT INTO medicion (facturado, consumo, fecha_hora, id_medidor, nro_serie_medidor) VALUES (false, 1.5, '2021-03-01 00:10:01.00', 2, '001253X');
INSERT INTO medicion (facturado, consumo, fecha_hora, id_medidor, nro_serie_medidor) VALUES (false, 1.0, '2021-03-01 00:10:02.00', 3, '000222');
INSERT INTO medicion (facturado, consumo, fecha_hora, id_medidor, nro_serie_medidor) VALUES (false, 1.0, '2021-03-01 00:10:03.00', 4, '000032');
INSERT INTO medicion (facturado, consumo, fecha_hora, id_medidor, nro_serie_medidor) VALUES (false, 1.0, '2021-03-01 00:10:04.00', 5, '0000PPP');
INSERT INTO medicion (facturado, consumo, fecha_hora, id_medidor, nro_serie_medidor) VALUES (false, 2.3, '2021-03-01 00:15:00.00', 1, '000023J');
INSERT INTO medicion (facturado, consumo, fecha_hora, id_medidor, nro_serie_medidor) VALUES (false, 2.3, '2021-03-01 00:15:01.00', 2, '001253X');
INSERT INTO medicion (facturado, consumo, fecha_hora, id_medidor, nro_serie_medidor) VALUES (false, 2.3, '2021-03-01 00:15:02.00', 3, '000222');
INSERT INTO medicion (facturado, consumo, fecha_hora, id_medidor, nro_serie_medidor) VALUES (false, 2.3, '2021-03-01 00:15:03.00', 4, '000032');
INSERT INTO medicion (facturado, consumo, fecha_hora, id_medidor, nro_serie_medidor) VALUES (false, 2.3, '2021-03-01 00:15:04.00', 5, '0000PPP');
INSERT INTO medicion (facturado, consumo, fecha_hora, id_medidor, nro_serie_medidor) VALUES (false, 3.0, '2021-04-01 00:05:00.00', 1, '000023J');
INSERT INTO medicion (facturado, consumo, fecha_hora, id_medidor, nro_serie_medidor) VALUES (false, 3.0, '2021-04-01 00:05:01.00', 2, '001253X');
INSERT INTO medicion (facturado, consumo, fecha_hora, id_medidor, nro_serie_medidor) VALUES (false, 3.0, '2021-04-01 00:05:02.00', 3, '000222');
INSERT INTO medicion (facturado, consumo, fecha_hora, id_medidor, nro_serie_medidor) VALUES (false, 3.0, '2021-04-01 00:05:03.00', 4, '000032');
INSERT INTO medicion (facturado, consumo, fecha_hora, id_medidor, nro_serie_medidor) VALUES (false, 3.0, '2021-04-01 00:05:04.00', 5, '0000PPP');

INSERT INTO factura (cliente_id, domicilio_id, nro_serie_medidor, medicion_inicial, medicion_final, fecha_medicion_inicial, fecha_medicion_final, consumo_total, total_pagar, pagada) VALUES (1, 1, '000023J', 2.0, 15.0, '2021-03-15 00:10:00.00', '2021-04-01 00:05:00.00', 13.0, 235.0, 0);
INSERT INTO factura (cliente_id, domicilio_id, nro_serie_medidor, medicion_inicial, medicion_final, fecha_medicion_inicial, fecha_medicion_final, consumo_total, total_pagar, pagada) VALUES (1, 1, '000023J', 3.0, 15.0, '2021-03-15 00:10:00.00', '2021-04-01 00:05:00.00', 12.0, 333.3, 1);
INSERT INTO factura (cliente_id, domicilio_id, nro_serie_medidor, medicion_inicial, medicion_final, fecha_medicion_inicial, fecha_medicion_final, consumo_total, total_pagar, pagada) VALUES (2, 2, '001253X', 4.0, 15.0, '2021-03-15 00:10:00.00', '2021-04-01 00:05:00.00', 11.0, 562.0, 0);
INSERT INTO factura (cliente_id, domicilio_id, nro_serie_medidor, medicion_inicial, medicion_final, fecha_medicion_inicial, fecha_medicion_final, consumo_total, total_pagar, pagada) VALUES (1, 1, '000023J', 5.0, 15.0, '2021-03-15 00:10:00.00', '2021-04-01 00:05:00.00', 10.0, 320.0, 0);