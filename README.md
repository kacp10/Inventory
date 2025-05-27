# Intouch IT Inventory

**Inventory Management Application** developed con Spring Boot, Spring Data JPA, Flyway y MySQL.

---

## 📖 Descripción

Este proyecto es una aplicación de inventario de TI que permite gestionar tipos de activos, activos y transacciones (préstamos, devoluciones). Utiliza Spring Boot para exponer APIs REST y Hibernate/JPA para persistencia en una base de datos MySQL.

## 🚀 Características

* CRUD de tipos de activo (`asset_types`).
* CRUD de activos (`assets`).
* Registro de transacciones de activos (`asset_transactions`).
* Migraciones de esquema con Flyway.
* Conexión a MySQL mediante HikariCP.
* Documentación de endpoints (puede añadirse Swagger/OpenAPI).

## ⚙️ Requisitos

* Java 17 o superior
* Maven 3.6+
* MySQL 8.0+

## 🛠️ Instalación y configuración

1. **Clonar el repositorio**:

   ```bash
   git clone https://github.com/tu-usuario/Intouch-Inventory.git
   cd Intouch-Inventory
   ```

2. **Configurar la base de datos**:

   * Crear base de datos y usuario en MySQL:

     ```sql
     CREATE DATABASE `InventoryDB` CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
     CREATE USER 'inventory_user'@'localhost' IDENTIFIED BY 'S3guraP4ss!';
     GRANT ALL PRIVILEGES ON `InventoryDB`.* TO 'inventory_user'@'localhost';
     FLUSH PRIVILEGES;
     ```

3. **Configurar `application.properties`** (en `src/main/resources`):

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/InventoryDB?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC
   spring.datasource.username=inventory_user
   spring.datasource.password=S3guraP4ss!

   spring.jpa.hibernate.ddl-auto=validate
   spring.jpa.show-sql=true

   spring.flyway.enabled=true
   spring.flyway.locations=classpath:db/migration
   ```

4. **Migraciones Flyway**:

   * Añade tus scripts SQL en `src/main/resources/db/migration`, por ejemplo:

     * `V1__create_schema.sql`
     * `V2__seed_data.sql`

## ▶️ Ejecución

Compilar y ejecutar la aplicación con Maven:

```bash
mvn clean spring-boot:run
```

La aplicación iniciará en `http://localhost:8080`.

## 🔌 Endpoints disponibles

> **Nota**: Ajusta las rutas según tus controladores.

| VERBO | Endpoint            | Descripción                                 |
| ----- | ------------------- | ------------------------------------------- |
| GET   | `/api/asset-types`  | Listar todos los tipos de activo            |
| POST  | `/api/asset-types`  | Crear nuevo tipo de activo                  |
| GET   | `/api/assets`       | Listar activos                              |
| POST  | `/api/assets`       | Crear activo                                |
| GET   | `/api/transactions` | Listar transacciones de activos             |
| POST  | `/api/transactions` | Registrar transacción (préstamo/devolución) |

## 🧪 Pruebas

Añade pruebas unitarias/integración en `src/test/java` usando JUnit y MockMvc.

## 🤝 Contribuciones

1. Haz un *fork* del proyecto
2. Crea una rama: `git checkout -b feature/mi-nueva-funcionalidad`
3. Realiza tus cambios y *commits*
4. Envía un *pull request*

## 📄 Licencia

Este proyecto está bajo la licencia MIT. Consulta el archivo `LICENSE` para más detalles.

---

¡Gracias por usar Intouch IT Inventory! 🎉
