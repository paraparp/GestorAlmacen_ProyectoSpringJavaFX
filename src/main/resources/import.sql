

INSERT INTO `almacen`.`proveedor` (`nombre`, `direccion`, `telefono`, `email`, `cif`) VALUES ('Merca23', 'Almos 21, Barcelona, 15337', '91123415', 'merca23@mail.com', 'S8399441H');
INSERT INTO `almacen`.`proveedor` (`nombre`, `direccion`, `telefono`, `email`, `cif`) VALUES ('RTE', 'Cruz 21, A Coruña, 15011', '981123471', 'erretee@gmail.com', 'E85252823');
INSERT INTO `almacen`.`proveedor` (`nombre`, `direccion`, `telefono`, `email`, `cif`) VALUES ('Sport Lugo', 'Juan Latorre 21, Lugo, 17023', '92010191', 'sportlugo@corp.com', 'F3070665I');

INSERT INTO `almacen`.`empleado` (`nombre`, `dni`, `email`, `telefono`, `username`, `password`) VALUES ('Marta Lore Prieto', '32152552F', 'martalore@gmail.com', '635787671', 'marta', 'marta');
INSERT INTO `almacen`.`empleado` (`nombre`, `dni`, `email`, `telefono`, `username`, `password`) VALUES ('Diego Marti Leo', '36968534P', 'diegomar@gmail.com', '695857456', 'diego', 'diego');
INSERT INTO `almacen`.`empleado` (`nombre`, `dni`, `email`, `telefono`, `username`, `password`) VALUES ('Luis Pera Lima', '42524565J', 'luispera@gmail.com', '612345654', 'luis', 'luis');


INSERT INTO `almacen`.`productogenerico` (`codigo`, `nombre`, `descripcion`, `marca`, `categoria`) VALUES ('Q2345QWE2', 'Street Run', '', 'Nike', 'Complemento');
INSERT INTO `almacen`.`productogenerico` (`codigo`, `nombre`, `descripcion`, `marca`, `categoria`) VALUES ('R3EDF45355', 'Max Shirt 3', '', 'Puma', 'Calzado');
INSERT INTO `almacen`.`productogenerico` (`codigo`, `nombre`, `descripcion`, `marca`, `categoria`)  VALUES ('TERWF764Q', 'Supernova X', '', 'Adidas', 'Ropa');



INSERT INTO `almacen`.`pedido` (`codigo`, `fecha_pedido`, `fecha_recibido`, `gastos`, `id_empleado`, `id_proveedor`) VALUES ('23454345', '2017-08-21', '2017-08-22', '12.12', '1', '1');
INSERT INTO `almacen`.`pedido` (`codigo`, `fecha_pedido`, `fecha_recibido`, `gastos`, `id_empleado`, `id_proveedor`)VALUES ('64566511', '2017-07-28', '2017-07-30', '23.12', '2', '3');
INSERT INTO `almacen`.`pedido` (`codigo`, `fecha_pedido`, `fecha_recibido`, `gastos`, `id_empleado`, `id_proveedor`) VALUES ('98767635', '2018-01-21', '2018-01-23', '8.21', '2', '3');



INSERT INTO `almacen`.`articulo` (`codigo_barras`,  `stock`, `id_producto_generico`, `color`, `talla`) VALUES ('5101018976781',  '1', '1', 'Rojo', 'L');
INSERT INTO `almacen`.`articulo` (`codigo_barras`,  `stock`, `id_producto_generico`, `color`, `talla`) VALUES ('5019876787654',  '1', '2', 'Verde', '44');
INSERT INTO `almacen`.`articulo` (`codigo_barras`,  `stock`, `id_producto_generico`, `color`, `talla`) VALUES ('7101987101981', '1', '3', 'Azul', 'XL');
INSERT INTO `almacen`.`articulo` (`codigo_barras`,  `stock`, `id_producto_generico`, `color`, `talla`)  VALUES ('8091910912112',  '1', '1', 'Verde', 'M');
INSERT INTO `almacen`.`articulo` (`codigo_barras`,  `stock`, `id_producto_generico`, `color`, `talla`) VALUES ('9871904723453',  '1', '1', 'Rojo', 'S');
INSERT INTO `almacen`.`articulo` (`codigo_barras`,  `stock`, `id_producto_generico`, `color`, `talla`)  VALUES ('5656756475756',  '1', '2', 'Amarillo', '41');
INSERT INTO `almacen`.`articulo` (`codigo_barras`,  `stock`, `id_producto_generico`, `color`, `talla`) VALUES ('510106768976781',  '1', '1', 'Rojo', 'M');
INSERT INTO `almacen`.`articulo` (`codigo_barras`,  `stock`, `id_producto_generico`, `color`, `talla`) VALUES ('50198776787654',  '1', '2', 'Naranja', '39');
INSERT INTO `almacen`.`articulo` (`codigo_barras`, ` `stock`, `id_producto_generico`, `color`, `talla`) VALUES ('71019876771981', '1', '3', 'Azul', 'L');
INSERT INTO `almacen`.`articulo` (`codigo_barras`,  `stock`, `id_producto_generico`, `color`, `talla`)  VALUES ('809191676912112',  '1', '1', 'Verde', 'XS');
INSERT INTO `almacen`.`articulo` (`codigo_barras`,  `stock`, `id_producto_generico`, `color`, `talla`) VALUES ('987190476763453',  '1', '1', 'Rojo', 'S');
INSERT INTO `almacen`.`articulo` (`codigo_barras`,  `stock`, `id_producto_generico`, `color`, `talla`)  VALUES ('56567567675756',  '1', '2', 'Rosa', '41');



INSERT INTO `almacen`.`lineapedido` (`id_pedido`, `id_articulo`, `cantidad`, `precio`) VALUES ('1', '1', 1, '10');

INSERT INTO `almacen`.`lineapedido` (`id_pedido`, `id_articulo`, `cantidad`, `precio`) VALUES ('1', '2', 1, '12');

INSERT INTO `almacen`.`lineapedido` (`id_pedido`, `id_articulo`, `cantidad`, `precio`) VALUES ('2', '3', 1, '20');

INSERT INTO `almacen`.`lineapedido` (`id_pedido`, `id_articulo`, `cantidad`, `precio`)  VALUES ('2', '4', 1, '15');

INSERT INTO `almacen`.`lineapedido` (`id_pedido`, `id_articulo`, `cantidad`, `precio`)  VALUES ('3', '5', 1, '25');

INSERT INTO `almacen`.`lineapedido` (`id_pedido`, `id_articulo`, `cantidad`, `precio`)  VALUES ('3', '6', 1, '25');

