 # Sistema de Gestión Escolar

Este proyecto es una aplicación **full-stack** que permite gestionar estudiantes, profesores, cursos e inscripciones.  
El backend está construido con **Spring Boot** y el frontend con **Angular** y **Angular Material**.
Adicionalmente, se incluyo tailwindCSS para agregar estilos a la aplicacion web (se siguieron las recomendaciones de la documentacion oficial: https://tailwindcss.com/docs/installation/framework-guides/angular)
## Tecnologías

- **Frontend:** Angular, Angular Material
- **Backend:** Spring Boot, Java 17, Maven
- **Base de datos:** MySQL

## 🚀 Estructura del proyecto
prueba-tecnica/
├── Prueba Tecnica Springboot/ # Backend hecho con Spring Boot
│ ├── src/
│ ├── pom.xml
│ └── application.properties
│
├── prueba-tecnica-ang/ # Frontend hecho con Angular
│ ├── src/
│ ├── angular.json
│ └── package.json
│
└── README.md # Este archivo

## 🛠️ Recomendaciones

- Se recomienda utilizar **IntelliJ IDEA** para ejecutar el backend.
- Se recomienda utilizar **Visual Studio Code** para el desarrollo del frontend.

## 💡 Funcionalidades

- CRUD de **personas** (clase abstracta)
- CRUD de **estudiantes** (hereda de persona)
- CRUD de **profesores** (hereda de persona)
- CRUD de **cursos** (cada curso está asignado a un profesor)
- CRUD de **inscripciones** (relaciona estudiantes con cursos)

## 📦 Cómo ejecutar el proyecto

1. Clona este repositorio/descargalo como .zip.
2. Importa el proyecto `Prueba Tecnica Springboot` como un proyecto Maven en **IntelliJ IDEA**.
3. Abre el archivo `application.properties` y **verifica la conexión a tu base de datos**. Cambia la contraseña por la correspondiente a tu configuración local.
4. El proyecto está configurado para ejecutarse por el **puerto 8080** el cual es el mismo que se comunica con el frontend. Si necesitas cambiarlo, puedes hacerlo editando la línea: por "server.port=8080" o eliminando la linea del comentario que agregue en el mismo archivo .properties (por favor ten en cuenta que si cambias el puerto, debes cambiarlo tambien en los archivos .service de angular).
5. Abre mySQL workbench y ejecuta el siguiente comando **CREATE DATABASE registroEscolar;** y **use registroEScolar;** para crear la base de datos
6. Inicia el proyecto de Springboot desde la clase **RegistroEscolarApplication**
7. (Opcional) si quieres mas informacion acerca de la base de datos puedes ejecutar los siguientes comandos para ver la relacion entre tablas mas detallada **SHOW TABLES;** 
**SELECT * FROM persona;**
**SELECT * FROM estudiante;**
**SELECT * FROM profesor;**
**SELECT * FROM curso;**
8. Abre la carpeta `prueba-tecnica-ang` en **Visual Studio Code** y ejecuta los siguientes comandos: npm install y luego ng serve, el proyecto arrancara automaticamente en el puerto 4200 

