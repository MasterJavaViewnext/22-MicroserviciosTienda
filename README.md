## Microservicio productos
Desarrollar un microservicio que permita operar con productos. 
El microservicio deberá exponer los siguientes recursos: 

• Lista de productos existentes. 

• Actualizar el stock del producto.

• Obtener el precio de un producto. 


O sea, el servicio de productos expone tres recursos:

• Un recurso que ante una petición get, devuelve la lista de productos existentes.

• Un recurso que al recibir una petición put, actualiza el stock del producto indicado.
En la URL de la petición se reciben el código de producto y las unidades compradas.

• Un recurso que al recibir una petición get con el código del producto devuelve el precio unitario del mismo.

El microservicio estará disponible en el puerto 8000. 
Testing: http://localhost:8000


## Microservicio pedidos
Desarrollar un microservicio que permita operar con pedidos. 
El microservicio recibirá los datos del pedido y los dará de alta. Permitirá recuperar todos los pedidos existentes. Tendrá que interactuar con el microservicio de productos para las actualizaciones de stock y obtención del precio. 

O sea, el servicio de pedidos expone dos recursos:

• Un recurso que dará de alta un nuevo pedido al recibir en una petición de tipo post que incluirá en el cuerpo de la misma los datos del pedido. Desde este servicio se realizarán las correspondientes llamadas a los recursos del servicio de productos para actualizar el stock.

• Un recurso que al recibir una petición get devuelva todos los pedidos registrados.
El microservicio estará disponible en el puerto 7000. 

Testing: http://localhost:7000
## Authors

- [@RaulRodal](https://www.github.com/raulrodal)


## Features

- Creacion de 2 microservicios
- Interoperabilidad entre los mismos
- Actualizacion automatica de stock al insertar pedido
- Calculo de precio del pedido automatico al insertar pedid
