DROP SCHEMA IF EXISTS productos;
CREATE SCHEMA productos;

CREATE TABLE productos.producto (
	id INT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion TEXT,
    precio DECIMAL(10, 2) NOT NULL,
    stock INT NOT NULL
);

INSERT INTO productos.producto (id, nombre, descripcion, precio, stock) VALUES
(101, 'Laptop', 'Laptop de alto rendimiento con 16GB RAM y 512GB SSD', 1200.00, 15),
(102, 'Smartphone', 'Smartphone con pantalla de 6.5 pulgadas y 128GB de almacenamiento', 800.00, 30),
(103, 'Tablet', 'Tablet con pantalla de 10 pulgadas y 64GB de almacenamiento', 400.00, 20),
(104, 'Auriculares', 'Auriculares inalámbricos con cancelación de ruido', 150.00, 50),
(105, 'Monitor', 'Monitor 4K de 27 pulgadas', 350.00, 10),
(106, 'Teclado Mecánico', 'Teclado mecánico con retroiluminación RGB', 100.00, 25),
(107, 'Ratón Gaming', 'Ratón gaming con 16000 DPI', 75.00, 40),
(108, 'Impresora', 'Impresora multifuncional con Wi-Fi', 250.00, 8),
(109, 'Disco Duro Externo', 'Disco duro externo de 2TB', 100.00, 35),
(110, 'Cámara', 'Cámara digital con resolución 24MP', 650.00, 5);
