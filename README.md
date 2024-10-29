# Spring Librería

Este proyecto es una aplicación web de gestión de una librería, construida con Spring Boot y Java. La aplicación permite a los usuarios hacer el sisteme de ventas , añadir al carrito los productos seleccionados.

## Funcionalidades

- **Gestión de Libros:** Crear, leer, actualizar y eliminar productos en la base de datos.
- **Gestión de productos:** Añadir y administrar productos.
- **Interfaz Web:** Navegar y realizar operaciones CRUD en una interfaz web.

## Requisitos

- **Java 17 o superior**
- **Maven 3.8+**
- **Base de Datos MySQL**
- **Git**
- **Spring Boot**

## Instalación

1. Clonar el repositorio:
    ```bash
    git clone https://github.com/Jhon1172001/spring-libreria.git
    cd spring-libreria
    ```

2. Configurar la base de datos en `application.properties` con las credenciales de MySQL:
    ```properties
    spring.datasource.url=jdbc:mysql://localhost:3306/libreria
    spring.datasource.username=TU_USUARIO
    spring.datasource.password=TU_CONTRASEÑA
    ```

3. Construir y ejecutar la aplicación:
    ```bash
    mvn clean install
    mvn spring-boot:run
    ```

4. Acceder a la aplicación en `http://localhost:8080`.

## Tecnologías Utilizadas

- **Spring Boot**
- **Spring Data JPA**
- **Thymeleaf** para el renderizado del frontend
- **MySQL** para la base de datos
- **Maven** para la gestión de dependencias

## Contribuciones

Las contribuciones son bienvenidas. Para contribuir, por favor sigue estos pasos:

1. Haz un fork del repositorio.
2. Crea una rama con la funcionalidad (`git checkout -b feature/nueva-funcionalidad`).
3. Haz commit de tus cambios (`git commit -m 'Añadir nueva funcionalidad'`).
4. Sube tus cambios (`git push origin feature/nueva-funcionalidad`).
5. Abre un Pull Request.

## Licencia

Este proyecto está licenciado bajo la licencia MIT.
