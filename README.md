Trabajo Evaluación 1 - Servidor: ProyectoServidor
API REST para la Gestión de Proyectos de Developers
Ciclo Formativo de Desarrollo de Aplicaciones Web (DAW)
Autor: Fernando Iglesias Leyva

📖 Índice
Introducción
Funcionalidades y Tecnologías
Guía de Instalación
Guía de Uso
Endpoints y Swagger
Enlace a Figma
Conclusión
Contribuciones, Agradecimientos y Referencias
Licencia
Contacto

📜 Introducción
ProyectoServidor es una API REST desarrollada como parte de la asignatura Desarrollo de Aplicaciones en el Servidor del ciclo formativo de Desarrollo de Aplicaciones Web (DAW). Su propósito principal es ofrecer un backend para una aplicación de currículum personal, específicamente en la sección dedicada a los proyectos desarrollados por el usuario.

El proyecto integra conceptos avanzados de desarrollo backend, gestión de bases de datos y despliegue en la nube, con el objetivo de crear una aplicación profesional.

⚙️ Funcionalidades y Tecnologías
Funcionalidades
CRUD completo:

Crear, leer, actualizar y eliminar información de proyectos.
Gestión de desarrolladores y tecnologías asociadas a cada proyecto.
Endpoints avanzados:

Cambio de estado del proyecto (Development, Testing, Production).
Asociación de tecnologías y desarrolladores a proyectos específicos.
Búsqueda de proyectos por tecnología.
Validación y manejo de errores:

Gestión centralizada de excepciones con un RestControllerAdvice.
Validación de datos en los DTOs.
Documentación integrada:

Swagger UI para explorar y probar la API de manera interactiva.
Tecnologías Utilizadas
Lenguaje: Java 17
Framework: Spring Boot
Gestión de Dependencias: Maven
Base de Datos: MySQL
IDE: Visual Studio Code
Despliegue: Microsoft Azure
🛠️ Guía de Instalación
1. Clonar el repositorio
bash
Copiar código
git clone https://github.com/feriley/proyectoServidor.git
cd proyectoServidor
2. Instalar las dependencias
Asegúrate de tener instalado Maven. Luego, ejecuta:

bash
Copiar código
mvn install

3. Configurar la base de datos
Crea una base de datos llamada portfolio en tu servidor MySQL.
Configura las credenciales en el archivo application.properties:
properties
Copiar código
spring.datasource.url=jdbc:mysql://localhost:3306/portfolio
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA

5. Ejecutar la aplicación
bash
Copiar código
mvn spring-boot:run
6. Acceder al Swagger UI
Después de ejecutar la aplicación, abre un navegador e ingresa a:
bash
Copiar código
http://localhost:8080/swagger-ui.html



🚀 Guía de Uso
Rutas principales del CRUD
Proyectos
GET /api/v1/projects: Listar todos los proyectos.
POST /api/v1/projects: Crear un nuevo proyecto.
PUT /api/v1/projects/{id}: Actualizar un proyecto existente.
DELETE /api/v1/projects/{id}: Eliminar un proyecto.
Desarrolladores
GET /api/v1/developers: Listar todos los desarrolladores.
POST /api/v1/developers: Crear un nuevo desarrollador.
DELETE /api/v1/developers/{id}: Eliminar un desarrollador.
Tecnologías
GET /api/v1/technologies: Listar todas las tecnologías.
POST /api/v1/technologies: Crear una nueva tecnología.
DELETE /api/v1/technologies/{id}: Eliminar una tecnología.
Endpoints y Swagger
Swagger UI proporciona una interfaz interactiva para explorar los endpoints disponibles.

Endpoints Avanzados
Cambio de estado de proyecto:

PATCH /api/v1/projects/totesting/{id}: Cambiar un proyecto a estado Testing.
PATCH /api/v1/projects/toprod/{id}: Cambiar un proyecto a estado Production.
Asociación de datos:

POST /api/v1/technologies/used/{id}: Asociar tecnologías a un proyecto.
POST /api/v1/developers/worked/{id}: Asociar desarrolladores a un proyecto.
Búsqueda avanzada:

GET /api/v1/projects/tec/{tech}: Buscar proyectos por tecnología.
🎨 Enlace a Figma
Puedes acceder al diseño de la interfaz en Figma desde este enlace:
Acceder al diseño en Figma

📚 Conclusión
Este proyecto no solo cumple con los requisitos académicos, sino que también sirve como un ejemplo práctico de cómo construir APIs REST bien estructuradas y desplegarlas en un entorno real.

🙏 Contribuciones, Agradecimientos y Referencias
Contribuciones:
Fernando Iglesias Leyva.

Agradecimientos:
Profesores del Colegio Vedruna Sevilla por su apoyo y enseñanzas.

Referencias:

Documentación oficial de Spring Boot.
MySQL y Azure.

📜 Licencia
Este proyecto está licenciado bajo la GNU General Public License (GPL). Consulta el archivo LICENSE para más información.

📧 Contacto
Email: fernando.iglesias@a.vedrunasevillasj.com
LinkedIn: linkedin.com/fernando.iglesias
