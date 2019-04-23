

INSERT INTO `almacen`.`proveedor` (`nombre`, `direccion`, `telefono`, `email`, `cif`) VALUES ('Proveedor 1', 'Calle 1, Barcelona, 15337', '99999999', 'mailprov1@mail.com', 'S8399441H');
INSERT INTO `almacen`.`proveedor` (`nombre`, `direccion`, `telefono`, `email`, `cif`) VALUES ('Proveedor 2', 'Calle 2, Madrid, 15011', '988888888', 'mailprov2@mail.com', 'E85252823');
INSERT INTO `almacen`.`proveedor` (`nombre`, `direccion`, `telefono`, `email`, `cif`) VALUES ('Proveedor 3', 'Calle 3, Lugo, 15999', '97777777', 'mailprov3@mail.com', 'F3070665I');

INSERT INTO `almacen`.`empleado` (`nombre`, `dni`, `email`, `telefono`, `username`, `password`) VALUES ('Admin Admin Admin ', '99999999Z', 'admin@admin.com', '635787671', 'admin', 'admin');
INSERT INTO `almacen`.`empleado` (`nombre`, `dni`, `email`, `telefono`, `username`, `password`) VALUES ('Empleado Uno Uno', '36968534P', 'diegomar@gmail.com', '695857456', 'emp1', 'emp1');
INSERT INTO `almacen`.`empleado` (`nombre`, `dni`, `email`, `telefono`, `username`, `password`) VALUES ('Empelado Dos Dos', '42524565J', 'luispera@gmail.com', '612345654', 'emp2', 'emp2');


INSERT INTO `almacen`.`productogenerico` (`codigo`, `nombre`, `descripcion`, `marca`, `categoria`) VALUES ('Q2345QWE2', 'Modelo X', '', 'Marca 1', 'Complemento');
INSERT INTO `almacen`.`productogenerico` (`codigo`, `nombre`, `descripcion`, `marca`, `categoria`) VALUES ('R3EDF4355', 'Modelo Y', '', 'Marca 2', 'Calzado');
INSERT INTO `almacen`.`productogenerico` (`codigo`, `nombre`, `descripcion`, `marca`, `categoria`) VALUES ('TERWF764Q', 'Modelo Z', '', 'Marca 3', 'Ropa');



INSERT INTO `almacen`.`pedido` (`codigo`, `fecha_pedido`, `fecha_recibido`, `gastos`, `id_empleado`, `id_proveedor`) VALUES ('23454345', '2017-08-21', '2017-08-22', '12.12', '1', '1');
INSERT INTO `almacen`.`pedido` (`codigo`, `fecha_pedido`, `fecha_recibido`, `gastos`, `id_empleado`, `id_proveedor`) VALUES ('64566511', '2017-07-28', '2017-07-30', '23.12', '2', '3');
INSERT INTO `almacen`.`pedido` (`codigo`, `fecha_pedido`, `fecha_recibido`, `gastos`, `id_empleado`, `id_proveedor`) VALUES ('98767635', '2018-01-21', '2018-01-23', '8.21', '2', '3');



INSERT INTO `almacen`.`articulo` (`codigo_barras`,  `stock`, `id_producto_generico`, `color`, `talla`) VALUES ('5101018976781', '1', '1', 'Rojo', 'L');
INSERT INTO `almacen`.`articulo` (`codigo_barras`,  `stock`, `id_producto_generico`, `color`, `talla`) VALUES ('5019876787654', '1', '2', 'Verde', '44');
INSERT INTO `almacen`.`articulo` (`codigo_barras`,  `stock`, `id_producto_generico`, `color`, `talla`) VALUES ('7101987101981', '1', '3', 'Azul', 'XL');
INSERT INTO `almacen`.`articulo` (`codigo_barras`,  `stock`, `id_producto_generico`, `color`, `talla`) VALUES ('8091910912112', '1', '1', 'Verde', 'M');
INSERT INTO `almacen`.`articulo` (`codigo_barras`,  `stock`, `id_producto_generico`, `color`, `talla`) VALUES ('9871904723453', '1', '1', 'Rojo', 'S');
INSERT INTO `almacen`.`articulo` (`codigo_barras`,  `stock`, `id_producto_generico`, `color`, `talla`) VALUES ('5656756475756', '1', '2', 'Amarillo', '41');
INSERT INTO `almacen`.`articulo` (`codigo_barras`,  `stock`, `id_producto_generico`, `color`, `talla`) VALUES ('5101067689767', '1', '1', 'Rojo', 'M');
INSERT INTO `almacen`.`articulo` (`codigo_barras`,  `stock`, `id_producto_generico`, `color`, `talla`) VALUES ('5019877678765', '1', '2', 'Naranja', '39');
INSERT INTO `almacen`.`articulo` (`codigo_barras`,  `stock`, `id_producto_generico`, `color`, `talla`) VALUES ('7101987677191', '1', '3', 'Azul', 'L');
INSERT INTO `almacen`.`articulo` (`codigo_barras`,  `stock`, `id_producto_generico`, `color`, `talla`) VALUES ('8091916769121', '1', '1', 'Verde', 'XS');
INSERT INTO `almacen`.`articulo` (`codigo_barras`,  `stock`, `id_producto_generico`, `color`, `talla`) VALUES ('9871904767633', '1', '1', 'Rojo', 'S');
INSERT INTO `almacen`.`articulo` (`codigo_barras`,  `stock`, `id_producto_generico`, `color`, `talla`) VALUES ('5656756767556', '1', '2', 'Rosa', '41');



INSERT INTO `almacen`.`lineapedido` (`id_pedido`, `id_articulo`, `cantidad`, `precio`) VALUES ('1', '1', 1, '10');

INSERT INTO `almacen`.`lineapedido` (`id_pedido`, `id_articulo`, `cantidad`, `precio`) VALUES ('1', '2', 1, '12');

INSERT INTO `almacen`.`lineapedido` (`id_pedido`, `id_articulo`, `cantidad`, `precio`) VALUES ('2', '3', 1, '20');

INSERT INTO `almacen`.`lineapedido` (`id_pedido`, `id_articulo`, `cantidad`, `precio`)  VALUES ('2', '4', 1, '15');

INSERT INTO `almacen`.`lineapedido` (`id_pedido`, `id_articulo`, `cantidad`, `precio`)  VALUES ('3', '5', 1, '25');

INSERT INTO `almacen`.`lineapedido` (`id_pedido`, `id_articulo`, `cantidad`, `precio`)  VALUES ('3', '6', 1, '25');

