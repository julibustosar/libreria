# 📚 Tu Librería

Este proyecto es una API desarrollada en Java que permite **registrar libros en una base de datos**, así como **consultarlos por distintas características**, tales como:

- Nombre del autor
- Periodo de vida del autor
- Idioma del libro

---

## 🛠️ Tecnologías utilizadas

- **Java**
- **Spring Framework**
- **Spring Boot**
- **Maven**
- **JPA (Java Persistence API)**
- **PostgreSQL**

---

## ⚙️ Funcionalidades principales

### ✅ Registro de libros
Permite registrar nuevos libros con información como:
- Título
- Autor
- Idioma
- Año de publicación

### 🔍 Consulta de libros
El sistema permite buscar libros según:
- **Nombre del autor**
- **Fechas de nacimiento y muerte del autor**
- **Idioma del libro**

---

## 🗃️ Base de datos

Se utiliza **PostgreSQL** como sistema de gestión de base de datos. La información registrada incluye relaciones entre libros y autores.

---

## ▶️ Cómo ejecutar el proyecto

Por favor compila y ejecuta con Maven usando el siguiente comando en tu terminal:

mvn spring-boot:run

Desarrollado por Juliana Bustos
