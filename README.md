 # Sistema de Gestión Escolar

Este proyecto es una aplicación **full-stack** que permite gestionar estudiantes, profesores, cursos e inscripciones.  
El backend está construido con **Spring Boot** y el frontend con **Angular** y **Angular Material**.
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

1. Clona este repositorio.
2. Importa el proyecto `Prueba Tecnica Springboot` como un proyecto Maven en **IntelliJ IDEA** y ejecuta la clase `RegistroEscolarApplication`.
3. Abre el archivo `application.properties` y **verifica la conexión a tu base de datos**. Cambia la contraseña por la correspondiente a tu configuración local.
4. El proyecto está configurado para ejecutarse por el **puerto 8080**. Si necesitas cambiarlo, puedes hacerlo editando la línea: por "server.port=8080" o eliminando la linea del comentario que agregue 
5. Abre la carpeta `prueba-tecnica-ang` en **Visual Studio Code** y ejecuta los siguientes comandos: npm install y luego ng serve, el proyecto arrancara automaticamente en el puerto 4200 

