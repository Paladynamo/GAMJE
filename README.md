# Proyecto GAMJE – Evolución de Servicios Monolíticos de EduTech
![Image](https://github.com/user-attachments/assets/5c75e880-deba-4cf2-9958-ccd5f8bb9dca)
Este repositorio contiene el desarrollo del proyecto GAMJE, cuyo objetivo es apoyar el proceso de transformación digital de EduTech Innovators SPA, una empresa emergente chilena dedicada a la creación de plataformas educativas en línea. Este trabajo forma parte de una iniciativa académica centrada en la evolución de una arquitectura monolítica hacia una orientada a microservicios, con el fin de mejorar la escalabilidad, rendimiento y disponibilidad del sistema.

📌 Contexto del Proyecto:
A partir del caso de estudio proporcionado en clases, se identificó que el sistema actual de EduTech presenta cuellos de botella y dificultades operativas debido a su crecimiento acelerado. En respuesta, este proyecto busca implementar una estructura modular basada en microservicios, permitiendo una gestión más ágil, mantenible y robusta para afrontar los nuevos desafíos tecnológicos.

⚙️ Tecnologías Utilizadas:
PHP & MySQL

XAMPP como entorno local de desarrollo (servidor Apache + base de datos MySQL)

HTML/CSS/JS para la parte visual del cliente

Postman para pruebas de endpoints (opcional)

Visual Studio Code

SpringBoot

Spring Initializr

Git y GitHub para control de versiones

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

1. Configuración del Entorno
Se utilizó XAMPP como entorno local para levantar el servidor y administrar la base de datos. Se configuraron los servicios de Apache y MySQL para permitir la conexión entre la lógica del sistema y el almacenamiento de datos.

Pasos seguidos:

Instalación de XAMPP.

Creación de la base de datos y tablas iniciales en phpMyAdmin.

Desarrollo de la estructura del proyecto en la carpeta htdocs.

2. Desarrollo del CRUD Básico
Como parte del aprendizaje aplicado en clases, se construyó un CRUD funcional que permite realizar operaciones básicas sobre una entidad seleccionada (por ejemplo, usuarios o cursos). Este CRUD incluye:

Crear registros (Create)

Leer información desde la base de datos (Read)

Modificar registros existentes (Update)

Eliminar registros (Delete)

La lógica de conexión a la base de datos fue implementada utilizando PHP, aplicando buenas prácticas aprendidas en clases como:

Separación de archivos por funciones (modelo, vista, controlador)

Validaciones básicas

Conexión segura utilizando mysqli o PDO

3. Aplicación de Conceptos Aprendidos
Todo lo desarrollado hasta el momento ha sido construido integrando lo aprendido en clases, incluyendo:

Estructuración de carpetas del proyecto.

Uso de formularios HTML para enviar datos a través del método POST.

Gestión de rutas básicas mediante archivos .php.

Conexión e interacción con la base de datos MySQL.

🔄 Próximos Pasos:
En fases siguientes del proyecto, se espera:

Modularizar la lógica de negocio en servicios independientes.

Iniciar la transición del sistema hacia una arquitectura distribuida basada en microservicios.

Implementar pruebas más robustas, documentación técnica y posiblemente una API RESTful.

🤝 Créditos:
Este proyecto fue desarrollado por Allen Ibañez-German Ormeño-Eduardo Lizama, estudiantes de Desarrollo Full Stack, como parte del proceso de evaluación académica. Agradecemos a nuestro docente por su guía en el aprendizaje de herramientas y buenas prácticas de desarrollo web, endpoint y springboot.
