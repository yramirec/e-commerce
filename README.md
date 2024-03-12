## API para Registrar Productos

**Descripción:**

Esta API permite registrar productos en una base de datos oracle y obtener la lista de productos del día.

**Endpoints:**

* **POST /productos:** Registrar un nuevo producto.

**Curl para Postman (Registrar Producto):**

```bash
curl --location 'http://localhost:8080/productos' \
--header 'Content-Type: application/json' \
--data '{"id": 16,"nombre": "Gaseosa", "fecRegistro": "2024-03-12"}'
```

