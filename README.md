# proyectoServidor
Trabajo Evaluación 1 - Servidor
ProyectoServidor: API REST para el Curriculum de Proyectos Developers
Ciclo Formativo de Desarrollo de Aplicaciones Web (DAW)
Autor: Fernando Iglesias Leyva

📖 Índice
Introducción
Funcionalidades y Tecnologías
Guía de Instalación
Guía de Uso
Enlace a Figma
Conclusión
Contribuciones, Agradecimientos y Referencias
Licencia
Contacto
📜 Introducción
ProyectoServidor es una API REST desarrollada como parte de la asignatura Desarrollo de Aplicaciones en el Servidor del ciclo formativo de Desarrollo de Aplicaciones Web (DAW).
El propósito principal de este proyecto es servir como backend para una aplicación de curriculum personal, específicamente en la sección que detalla los proyectos desarrollados.

La motivación detrás de este proyecto es aprender y aplicar los conceptos de desarrollo de API REST, gestión de bases de datos y despliegue en la nube. Esta API también demuestra cómo estructurar un CRUD en un entorno profesional, conectando con una base de datos MySQL y utilizando el framework Spring Boot.

⚙️ Funcionalidades y Tecnologías
Funcionalidades
CRUD completo: Gestión de información sobre proyectos developers:
Crear un nuevo proyecto.
Leer detalles de proyectos existentes.
Actualizar información de un proyecto.
Eliminar un proyecto.
Conexión a base de datos MySQL para almacenar y recuperar los datos.
Despliegue en Azure para permitir su acceso desde aplicaciones cliente.
Integración con un portafolio personal como frontend.
Tecnologías Utilizadas
Lenguaje: Java
Framework: Spring Boot
Gestión de Dependencias: Maven
Base de Datos: MySQL
IDE: Visual Studio Code
Nube: Microsoft Azure
🛠️ Guía de Instalación
Clona el repositorio:

bash
Copiar código
git clone <URL_DEL_REPOSITORIO>
cd ProyectoServidor
Instala las dependencias:
Asegúrate de tener instalados Maven y Spring Boot. Luego, ejecuta:

bash
Copiar código
mvn install
Configura la base de datos MySQL:

Crea una base de datos llamada portfolio_projects.
Configura las credenciales en el archivo application.properties del proyecto:
properties
Copiar código
spring.datasource.url=jdbc:mysql://localhost:3306/portfolio_projects
spring.datasource.username=TU_USUARIO
spring.datasource.password=TU_CONTRASEÑA
Ejecuta la aplicación:

bash
Copiar código
mvn spring-boot:run
Despliega en Azure (opcional):
Sigue la documentación oficial de Azure para configurar tu aplicación.

🚀 Guía de Uso
Ejecuta la API:
Lanza el proyecto desde tu entorno local o tras su despliegue en Azure.
Conecta con el cliente:
El frontend (portafolio personal) consumirá esta API para mostrar los proyectos.
Rutas principales del CRUD:
GET /api/projects - Listar todos los proyectos.
POST /api/projects - Crear un nuevo proyecto.
PUT /api/projects/{id} - Actualizar un proyecto existente.
DELETE /api/projects/{id} - Eliminar un proyecto.
🎨 Enlace a Figma
Puedes acceder al diseño de la interfaz en Figma desde este enlace: Acceder al diseño en Figma (reemplaza con el enlace real).

📚 Conclusión
Este proyecto no solo cumple con los requisitos de la asignatura, sino que también sirve como una excelente base para integrar APIs REST en aplicaciones reales. Su despliegue en la nube y la conexión con un frontend demuestran la importancia de un desarrollo bien estructurado.

🙏 Contribuciones, Agradecimientos y Referencias
Contribuciones: Fernando Iglesias Leyva.
Agradecimientos: Profesores del Colegio Vedruna Sevilla por su apoyo y enseñanzas.
Referencias: Documentación oficial de Spring Boot, MySQL y Azure.
📜 Licencia
Este proyecto está licenciado bajo la GNU General Public License (GPL). Consulta el archivo LICENSE para más información.

📧 Contacto
Si tienes preguntas o deseas colaborar, no dudes en contactarme:
Email: tucorreo@example.com
LinkedIn: TuPerfil (reemplaza con el enlace real)


