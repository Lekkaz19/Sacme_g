-- Data for Grupo
INSERT INTO Grupo (codgrupo, nombre, estado) VALUES ('ACL', 'ACERO DE CONSTRUCCIÓN LISO', 'Activo');
INSERT INTO Grupo (codgrupo, nombre, estado) VALUES ('ACC', 'ACERO DE CONSTRUCCIÓN CORRUGADO', 'Activo');

-- Data for Unidad
INSERT INTO Unidad (abreviatura, descripcion, estado) VALUES ('KG', 'KILOGRAMO', 'Activo');
INSERT INTO Unidad (abreviatura, descripcion, estado) VALUES ('UNID', 'UNIDAD', 'Activo');
INSERT INTO Unidad (abreviatura, descripcion, estado) VALUES ('VAR', 'VARILLA', 'Activo');

-- Data for Material
-- Assuming IDs 1 and 2 for Groups ACL and ACC respectively
-- Assuming IDs 1, 2, 3 for Units KG, UNID, VAR respectively

-- ACL001 | ALAMBRE GALVANIZADO N°16 | Grupo: ACL | Unidad: KG
INSERT INTO Material (codcorrelativo, nombre, fecha_creacion, descripcion, observacion, estado, idgrupo, idunidad)
VALUES ('001', 'ALAMBRE GALVANIZADO N°16', CURDATE(), 'Alambre galvanizado de alta resistencia', 'Ninguna', 'Activo', 1, 1);

-- ACL002 | ALAMBRE NEGRO RECOCIDO N° 16 | Grupo: ACL | Unidad: KG
INSERT INTO Material (codcorrelativo, nombre, fecha_creacion, descripcion, observacion, estado, idgrupo, idunidad)
VALUES ('002', 'ALAMBRE NEGRO RECOCIDO N° 16', CURDATE(), 'Alambre negro recocido para construcción', 'Ninguna', 'Activo', 1, 1);

-- ACC001 | ACERO CORRUGADO ASTM A615 Ø 1/2" x 9m | Grupo: ACC | Unidad: VAR
INSERT INTO Material (codcorrelativo, nombre, fecha_creacion, descripcion, observacion, estado, idgrupo, idunidad)
VALUES ('001', 'ACERO CORRUGADO ASTM A615 Ø 1/2" x 9m', CURDATE(), 'Acero corrugado standard', 'Ninguna', 'Activo', 2, 3);

-- Data for Proyecto
INSERT INTO Proyecto (nombre, centro_costos, estado) VALUES ('Proyecto Alpha', 'CC-001', 'Activo');
INSERT INTO Proyecto (nombre, centro_costos, estado) VALUES ('Proyecto Beta', 'CC-002', 'Activo');

-- Data for Usuario
-- Formato: INSERT INTO Usuario (nombre, password, rol, estado) VALUES ('nombre_usuario', 'contraseña', 'Rol', 'Estado');

-- --- Usuario Administrador ---
INSERT INTO Usuario (id, nombre, password, rol, estado) VALUES
(1, 'admin', 'admin', 'Admin', 'Activo');

-- --- Usuario Jefe de Área ---
INSERT INTO Usuario (id, nombre, password, rol, estado) VALUES
(2, 'jefe.area', 'jefe123', 'Jefe de Area', 'Activo');

-- --- Usuario Empleado de Compras ---
INSERT INTO Usuario (id, nombre, password, rol, estado) VALUES
(3, 'compras.usr', 'compras123', 'Empleado de Compras', 'Activo');

-- --- Usuario Empleado Regular ---
INSERT INTO Usuario (id, nombre, password, rol, estado) VALUES
(4, 'juan.perez', 'empleado123', 'Empleado', 'Activo');

INSERT INTO Usuario (id, nombre, password, rol, estado) VALUES
(5, 'maria.rguez', 'empleado123', 'Empleado', 'Inactivo');


-- Data for Requerimiento
INSERT INTO Requerimiento (fecha_pedido, id_solicitante, id_proyecto, etapa_proyecto, tipo, estado, observacion)
VALUES (CURDATE(), 4, 1, 'Etapa Inicial', 'Materiales', 'Pendiente', 'Pedido urgente de Juan Perez');

-- Data for DetalleRequerimiento
INSERT INTO DetalleRequerimiento (id_requerimiento, id_material, cantidad, para_proyecto)
VALUES (1, 1, 100, 'Cimentación');
INSERT INTO DetalleRequerimiento (id_requerimiento, id_material, cantidad, para_proyecto)
VALUES (1, 3, 50, 'Estructura');

-- Data for Proveedor
INSERT INTO Proveedor (razon_social, ruc, direccion, telefono, contacto, correo, cuenta_bancaria, estado)
VALUES ('Proveedor A', '12345678901', 'Av. Principal 123', '987654321', 'Ana Lopez', 'proveedora@example.com', '123-456-789', 'Activo');
INSERT INTO Proveedor (razon_social, ruc, direccion, telefono, contacto, correo, cuenta_bancaria, estado)
VALUES ('Proveedor B', '10987654321', 'Calle Secundaria 456', '123456789', 'Pedro Martinez', 'proveedorb@example.com', '987-654-321', 'Activo');

-- Data for OrdenCompra
INSERT INTO OrdenCompra (numero_orden, fecha, id_proveedor, tipo_moneda, tipo_pago, forma_pago, fecha_entrega_prevista, lugar_entrega, total, igv, estado)
VALUES ('OC-001', CURDATE(), 1, 'Soles', 'Transferencia', 'Contado', CURDATE() + INTERVAL 7 DAY, 'Almacén Central', 1500.00, 270.00, 'Aprobada');

-- Data for DetalleOrdenCompra
INSERT INTO DetalleOrdenCompra (id_orden_compra, id_material, cantidad, precio_unitario)
VALUES (1, 1, 100, 15.00);

-- Data for RegistroCompra
INSERT INTO RegistroCompra (id_orden_compra, id_material, cantidad, id_requerimiento)
VALUES (1, 1, 100, 1);