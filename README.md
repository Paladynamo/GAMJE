# Proyecto GAMJE – Evolución de Servicios Monolíticos de EduTech
![Image](https://github.com/user-attachments/assets/5c75e880-deba-4cf2-9958-ccd5f8bb9dca)
Este repositorio contiene el desarrollo del proyecto GAMJE, cuyo objetivo es apoyar el proceso de transformación digital de EduTech Innovators SPA, una empresa emergente chilena dedicada a la creación de plataformas educativas en línea. Este trabajo forma parte de una iniciativa académica centrada en la evolución de una arquitectura monolítica hacia una orientada a microservicios, con el fin de mejorar la escalabilidad, rendimiento y disponibilidad del sistema.

📌 Contexto del Proyecto:
A partir del caso de estudio proporcionado en clases, se identificó que el sistema actual de EduTech presenta cuellos de botella y dificultades operativas debido a su crecimiento acelerado. En respuesta, este proyecto busca implementar una estructura modular basada en microservicios, permitiendo una gestión más ágil, mantenible y robusta para afrontar los nuevos desafíos tecnológicos.

A continuación, se presentan los principales aspectos técnicos del proyecto desarrollado, cuyo objetivo fue implementar un CRUD funcional aplicando los conocimientos adquiridos en clases.

En primer lugar, se utilizó XAMPP como entorno de desarrollo local, lo que permitió levantar el servidor y administrar la base de datos de forma sencilla y eficiente.

Desarrollo del CRUD

Como parte del aprendizaje práctico, se construyó un CRUD que permite realizar las operaciones básicas sobre una entidad específica. Estas operaciones son:

    Crear registros (Create)

    Leer información desde la base de datos (Read)

    Modificar registros existentes (Update)

    Eliminar registros (Delete)

La lógica de conexión a la base de datos fue implementada utilizando PHP, incorporando buenas prácticas enseñadas durante el curso, tales como:

    La separación de responsabilidades mediante la estructura Modelo-Vista-Controlador (MVC).

    La aplicación de validaciones básicas para garantizar la calidad y coherencia de los datos ingresados.

    El uso de una conexión segura a la base de datos a través de MySQLi o PDO, lo que permite prevenir vulnerabilidades comunes.

Este enfoque permitió construir una aplicación clara, organizada y funcional, sirviendo como base para futuros desarrollos más complejos

⚙️ Tecnologías Utilizadas:

- Java 17+
- Spring Boot 3.3.11
- Spring Cloud Gateway
- Spring WebClient (para comunicación entre MS)
- Spring Data JPA + MySQL
- Lombok
- Postman para pruebas de endpoints 
- Maven
- Visual Studio Code
- XAMPP como entorno local de desarrollo (servidor Apache + base de datos MySQL)
- Git y GitHub para control de versiones

🛠️ Implementación Actual:
Durante esta etapa inicial del proyecto, se ha trabajado en los siguientes aspectos:
## 📦 Microservicios implementados

| Microservicio   | Puerto | Descripción                                         |
|------------------|--------|----------------------------------------------------|
| Usuarios         | 8081   | Registro y gestión de usuarios (rol, email, RUT)   |
| Cursos           | 8099   | Administración de cursos, docentes y duración      |
| Inscripciones    | 8098   | Vinculación de usuarios a cursos                   |
| Pagos            | 8097   | Registro de pagos por curso y usuario              |
| Gateway          | 8300   | Entrada central para acceder a todos los servicios |

## 🌐 Acceso vía Gateway

El API Gateway enruta todas las solicitudes bajo el prefijo `/api`.


## 🧪 Cómo ejecutar localmente

1. Clona este repositorio y entra a la carpeta raíz:

```bash
git clone https://github.com/tu-usuario/gamje-microservicios.git
cd gamje-microservicios
```

2. Asegúrate de tener **MySQL** corriendo y crea las siguientes bases de datos:

- `servicio_usuario`
- `servicio_curso`
- `servicio_inscripcion`
- `servicio_pago`

3. Desde cada carpeta de microservicio:

```bash
mvn spring-boot:run
```

4. Inicia el gateway:

```bash
cd gateway
mvn spring-boot:run
```


🔄 Próximos Pasos:
*****POR COMPLETAR***************

🤝 Créditos:
Este proyecto fue desarrollado por Allen Ibañez-German Ormeño-Eduardo Lizama, estudiantes de Desarrollo Full Stack, como parte del proceso de evaluación académica. Agradecemos a nuestro docente por su guía en el aprendizaje de herramientas y buenas prácticas de desarrollo web, endpoint y springboot.
