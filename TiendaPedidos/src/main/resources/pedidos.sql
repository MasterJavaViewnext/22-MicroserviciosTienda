DROP SCHEMA IF EXISTS pedidos;
CREATE SCHEMA pedidos;

CREATE TABLE pedidos.pedido (
	id int PRIMARY KEY AUTO_INCREMENT,
    fecha DATETIME NOT NULL,
    nombre_cliente VARCHAR(100) NOT NULL,
    id_producto INT NOT NULL,
    cantidad INT NOT NULL,
    precio DECIMAL(10, 2) NOT NULL
);

INSERT INTO pedidos.pedido (fecha, nombre_cliente, id_producto, cantidad, precio) VALUES
('2024-05-01 14:23:55', 'Juan Pérez', 101, 2, 150.50),
('2024-05-02 09:17:30', 'María López', 102, 1, 75.00),
('2024-05-03 12:45:10', 'Carlos Sánchez', 103, 3, 200.75),
('2024-05-04 08:30:00', 'Ana Martínez', 104, 5, 50.00),
('2024-05-05 16:10:45', 'Luis Fernández', 105, 2, 100.00),
('2024-05-06 10:20:20', 'Laura Gómez', 106, 1, 300.25),
('2024-05-07 14:05:55', 'Sofía Díaz', 107, 4, 175.50),
('2024-05-08 11:12:00', 'Marta Rodríguez', 108, 2, 250.00),
('2024-05-09 13:35:30', 'Pedro González', 109, 3, 125.75),
('2024-05-10 15:50:40', 'Alberto Ruiz', 110, 1, 80.00);

