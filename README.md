# API de Productos en Línea

Este repositorio contiene la implementación de una solución de backend para una plataforma de venta de productos en línea. La API ofrece una variedad de características y funcionalidades para gestionar productos, clientes, pedidos, pagos y detalles de envío.

## Características Principales

### Modelo de Datos

El modelo de datos está compuesto por las siguientes entidades:

- **Product**: Representa un producto disponible para la venta.
- **Cliente**: Almacena información sobre los clientes que utilizan la plataforma.
- **Pedido**: Registra los pedidos realizados por los clientes.
- **ItemPedido**: Contiene detalles específicos de los productos en un pedido.
- **Pago**: Gestiona los pagos asociados a los pedidos.
- **DetalleEnvio**: Almacena información relevante sobre el envío de los productos.
  
### Diagrama de base de datos

![bdOnlineProducts](https://github.com/diego-Ballesteros/ApiOnlineProducts/assets/114629529/e709ee2a-474b-4981-a088-6d69bc13d68a)

### Capa de Persistencia

- se Utilizó **MySQL** como motor de base de datos.
- Además de los métodos CRUD estándar, se implementó consultas específicas para cada entidad:

  - **Producto**:
    - Buscar productos según un término de búsqueda.
    - Encontrar productos en stock.
    - Filtrar productos por precio y stock.
  - **Cliente**:
    - Encontrar clientes por dirección de correo electrónico.
    - Buscar clientes por dirección.
    - Filtrar clientes por nombre.
  - **Pedido**:
    - Buscar pedidos entre dos fechas.
    - Filtrar pedidos por cliente y estado.
    - Recuperar pedidos con sus artículos.
    - Obtener pagos dentro de un rango de fechas.
    - Buscar pagos por identificador de orden y método de pago.
  - **ItemPedido**:
    - Buscar ítems del pedido por ID de pedido.
    - Filtrar ítems del pedido para un producto específico.
    - Calcular la suma total de ventas para un producto utilizando la agregación SUM.
  - **DetalleEnvio**:
    - Buscar detalles de envío por ID de pedido.
    - Filtrar detalles de envío por transportadora.
    - Buscar detalles de envío por estado.

### Pruebas

- Se implementaron pruebas de integración utilizando **Spring Boot Test**, **JUnit** y **Testcontainers**.
- Cada clase Repository tiene casos de prueba para las operaciones básicas del CRUD y las consultas personalizadas.
- La capa de servicios también cuenta con pruebas unitarias, alcanzando un 100% de cobertura.

### Tecnologías Utilizadas

- **Spring Boot**: Framework para el desarrollo de aplicaciones Java.
- **MapStruct**: Utilizado para mapear DTOs.
- **Mockito**: Para las pruebas unitarias.

