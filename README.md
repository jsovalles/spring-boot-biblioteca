# spring-boot-biblioteca
Proyecto realizado en Spring Boot que simula el comportamiento de un bibliotecario cuando un usuario desea prestar un libro. El bibliotecario identifica un libro como único por medio del ISBN

## Descripción del proyecto

Consiste en un sistema que simula el comportamiento de un bibliotecario cuando un usuario desea prestar un libro. El bibliotecario identifica un libro como único por medio del ISBN

### Reglas de negocio a cumplir

1. Cuando se desea prestar un libro, al bibliotecario se le debe entregar el ISBN

2. Un ISBN no se puede prestar más de una vez

3. Si el ISBN del libro que se va prestar es palíndromo (Un palíndromo es un número, palabra, o frase que se lee igual al derecho que al revés ejemplo: 1221), debe retornar una excepcion que contenga el siguiente mensaje **“los libros palíndromos solo se pueden utilizar en la biblioteca”** y no se deberá ejecutar el préstamo.

4. Partiendo de la regla de negocio 1, se deberá modificar para que a la hora de realizar el préstamo se solicite tanto el ISBN como el nombre de la persona que realiza el préstamo (esta nueva información deberá ser almacenada en la base de datos), es posible que para este caso tenga que modificar las pruebas y el código fuente existente. Para esto utilizar el atributo nombreUsuario de Prestamo

5. Si los dígitos numéricos que componen el ISBN suman más de 30, la fecha de entrega del libro prestado deberá ser máximo 15 días, contando a partir de la fecha actual (incluyendo el día en que se realiza el préstamo) sin contar los domingos. Si la fecha de entrega cae un domingo deberá ser el siguiente día hábil. Ejemplos:
- **ISBN:** A874B69Q **Fecha prestamo:** 24/05/2017 - **Fecha Entrega:** 09/06/2017
- **ISBN:** T878B85Z **Fecha prestamo:** 26/05/2017 - **Fecha Entrega:** 12/06/2017

Esta fecha usted la deberá calcular de acuerdo a los requerimientos descritos anteriormente, asegúrese de que esta fecha quede almacenada en la base de datos en la entidad del préstamo (fechaEntregaMaxima)

Si no se cumple los criterios descritos anteriormente, no se deberá calcular fecha de entrega, deberá ser vacía(null)

**Nota:**
- Para manipular los préstamos y los libros (CRUD), se deberá hacer uso de los componentes RepositorioLibro y RepositorioPrestamo
- Sólo debe existir un método prestar en la clase bibliotecario, si es necesario puede cambiar la firma de él, pero no crear mas métodos prestar

### Descripción técnica
El proyecto se encuentra construido en Java, con spring boot y con una base de datos en memoria H2 (la conexión a la base de datos ya se encuentra desarrollada y usted no tendrá que modificarla), se utiliza JPA para la manipulación de datos y ya se tienen unos contratos (definicion de los web services, objetos de ingreso y de retorno) definidos que no se deberán cambiar (ControladorLibro y ControladorPrestamo). Este proyecto se encuentra construido con el paradigma de orientación a objetos y la herramienta de configuración gradle.

El siguiente diagrama ilustra la arquitectura de la aplicación

![enter image description here](https://i.ibb.co/q9DX0Hz/ceiba.png)

### El reto del proyecto es

El proyecto tiene algunas pruebas unitarias y de integración que se encuentran fallando (6 en total) que se encuentran en el directorio src/test/java, se deberá realizar el desarrollo de la lógica de negocio para que todas las pruebas se ejecuten exitosamente. El código de los test entregados no deberá ser modificado a no ser de que se modifique para una nueva funcionalidad, al terminar el desarrollo se deberá verificar que el resultado de las pruebas sean exitosas.

# Desarrollado en

* [IntelliJ IDEA](https://www.jetbrains.com/idea/) - The Java IDE
* [Maven](https://maven.apache.org/) - Dependency Management
* Java 11 JDK
