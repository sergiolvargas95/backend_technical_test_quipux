# 🎵 Playlist API

API REST para gestión de listas de reproducción musical desarrollada con Spring Boot, JPA/Hibernate y Spring Security.

[![Java](https://img.shields.io/badge/Java-21-orange.svg)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-4.0.2-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

---

## 📋 Descripción

Sistema backend que permite crear, consultar y eliminar listas de reproducción musicales con sus respectivas canciones. Incluye autenticación y autorización basada en roles.

---

## ✨ Características

- ✅ CRUD completo de listas de reproducción
- ✅ Gestión de canciones asociadas a listas
- ✅ Relación ManyToMany (una canción puede estar en varias listas)
- ✅ Autenticación HTTP Basic
- ✅ Autorización basada en roles (ADMIN, USER)
- ✅ Validaciones de datos
- ✅ Manejo de errores estandarizado
- ✅ Base de datos H2 en memoria
- ✅ Documentación interactiva con Swagger

---

## 🛠️ Tecnologías

- **Java 21**
- **Spring Boot 4.0.2**
- **Spring Data JPA**
- **Spring Security**
- **H2 Database**
- **Maven**
- **Lombok**

---

## 📡 Endpoints

### Base URL: `/api/v1`

| Método | Endpoint | Descripción | Auth | Roles |
|--------|----------|-------------|------|-------|
| `POST` | `/lists` | Crear lista | ✅ | ADMIN |
| `GET` | `/lists` | Obtener todas las listas | ✅ | ADMIN, USER |
| `GET` | `/lists/{name}` | Obtener lista específica | ✅ | ADMIN, USER |
| `DELETE` | `/lists/{name}` | Eliminar lista | ✅ | ADMIN |

---

## 🔐 Autenticación y Autorización

### Credenciales de Prueba

**Administrador:**
```
Username: admin
Password: admin123
Role: ROLE_ADMIN
```

**Usuario:**
```
Username: myNewUser
Password: password123
Role: ROLE_USER
```

### Permisos por Rol

| Rol | CREATE | READ | DELETE |
|-----|--------|------|--------|
| **ADMIN** | ✅ | ✅ | ✅ |
| **USER** | ❌ | ✅ | ❌ |

---

## 📦 Instalación y Ejecución

### Prerrequisitos
- Java 21 o superior
- Maven 3.6+

### Pasos

1. **Clonar el repositorio**
```bash
git clone https://github.com/sergiolvargas95/backend_technical_test_quipux.git playlist-api
cd playlist-api
```

2. **Compilar el proyecto**
```bash
mvn clean install
```

3. **Ejecutar la aplicación**
```bash
mvn spring-boot:run
```

La API estará disponible en `http://localhost:8080`

---

## 🧪 Ejemplos de Uso

### 1. Crear Lista (ADMIN)

```bash
curl -X POST http://localhost:8080/api/v1/lists \
  -u admin:admin123 \
  -H "Content-Type: application/json" \
  -d '{
    "name": "Rock Clásico",
    "description": "Las mejores del rock",
    "songs": [
      {
        "title": "Bohemian Rhapsody",
        "artist": "Queen",
        "album": "A Night at the Opera",
        "releaseYear": "1975",
        "genre": "Rock"
      }
    ]
  }'
```

**Respuesta: 201 Created**
```json
{
  "id": 1,
  "name": "Rock Clásico",
  "description": "Las mejores del rock",
  "createdAt": "2024-02-02T10:30:00",
  "updatedAt": "2024-02-02T10:30:00",
  "totalSongs": 1,
  "songs": [...]
}
```

### 2. Obtener Todas las Listas (USER o ADMIN)

```bash
curl http://localhost:8080/api/v1/lists \
  -u myNewUser:password123
```

**Respuesta: 200 OK**
```json
[
  {
    "id": 1,
    "name": "Rock Clásico",
    "description": "Las mejores del rock",
    "totalSongs": 3
  }
]
```

### 3. Obtener Lista Específica (USER o ADMIN)

```bash
curl http://localhost:8080/api/v1/lists/Rock%20Clásico \
  -u myNewUser:password123
```

### 4. Eliminar Lista (ADMIN)

```bash
curl -X DELETE http://localhost:8080/api/v1/lists/Rock%20Clásico \
  -u admin:admin123
```

**Respuesta: 204 No Content**

---

## 📊 Códigos de Estado HTTP

| Código | Descripción |
|--------|-------------|
| `200` | Petición exitosa |
| `201` | Recurso creado exitosamente |
| `204` | Recurso eliminado (sin contenido) |
| `400` | Petición inválida (validación) |
| `401` | No autenticado |
| `403` | Sin permisos suficientes |
| `404` | Recurso no encontrado |
| `500` | Error interno del servidor |

---

### H2 Console (Desarrollo)
```
URL: http://localhost:8080/h2-console
JDBC URL: jdbc:h2:mem:playlistdb
Username: sa
Password: (vacío)
```

---

## 📂 Estructura del Proyecto

```
src/main/java/com/playlist/api/
├── config/              # Configuraciones (Security)
├── controller/          # Controladores REST
├── dtos/                # DTOs y Mappers
├── exception/           # Excepciones personalizadas
├── models/              # Entidades JPA
├── repositories/        # Repositorios JPA
└── service/             # Lógica de negocio
```

---

## 🗄️ Modelo de Datos

### Relación ManyToMany

```
ListSong ←→ Song
```

Una canción puede estar en múltiples listas, y una lista puede tener múltiples canciones.

### Tablas

- `list_songs` - Listas de reproducción
- `songs` - Canciones
- `list_song_songs` - Tabla intermedia (relación)

---

## ⚙️ Configuración

### application.properties

```properties
# Puerto
server.port=8080

# Base de datos H2
spring.datasource.url=jdbc:h2:mem:playlistdb
spring.h2.console.enabled=true

# JPA
spring.jpa.hibernate.ddl-auto=create-drop
spring.jpa.show-sql=true

```

---

## 🧪 Testing con Postman

Importa la colección de Postman incluida en el repositorio:

- [Playlist_API_postman_collection.json](https://drive.google.com/file/d/1hMA5BtZyb7SHP3ajjqKok_8yiSixi1po/view?usp=sharing)
- [Playlist_API_postman_environment.json](https://drive.google.com/file/d/1J5dk0_qYc6g2sffpmyFuzbp1WXWJy-iM/view?usp=sharing)

La colección incluye:
- Ejemplos de todas las peticiones
- Autenticación pre-configurada para ADMIN y USER
- Variables de entorno

---

## 🚨 Manejo de Errores

### Ejemplo: Usuario sin permisos

**Request:**
```bash
curl -X POST http://localhost:8080/api/v1/lists \
  -u myNewUser:password123 \
  -H "Content-Type: application/json" \
  -d '{...}'
```

**Respuesta: 403 Forbidden**
```json
{
  "timestamp": "2024-02-02T10:30:00",
  "status": 403,
  "error": "Forbidden",
  "message": "Access Denied",
  "path": "/api/v1/lists"
}
```

### Ejemplo: Lista no encontrada

**Respuesta: 404 Not Found**
```json
{
  "timestamp": "2024-02-02T10:30:00",
  "status": 404,
  "error": "Not Found",
  "message": "Lista no encontrado con nombre: 'No Existe'",
  "path": "/api/v1/lists/No Existe"
}
```

---

## 🔒 Seguridad

- ✅ Autenticación HTTP Basic
- ✅ Contraseñas encriptadas con BCrypt
- ✅ Control de acceso basado en roles
- ✅ Protección CSRF deshabilitada (API stateless)
- ✅ Validación de datos en DTOs

---

## 📝 Validaciones

- Nombre de lista no puede estar vacío
- Nombre de lista debe ser único
- Lista debe contener al menos una canción
- Título y artista de canción son obligatorios

---

## 🚀 Próximas Mejoras

- [ ] Paginación en listado de listas
- [ ] Búsqueda y filtros avanzados
- [ ] JWT en lugar de Basic Auth
- [ ] Endpoints para gestión de usuarios
- [ ] Soporte para imágenes de portada
- [ ] Rate limiting

---

## 👨‍💻 Autor

[Sergio Luis Vargas Meléndez](https://github.com/sergiolvargas95)

---

## 📄 Licencia

Este proyecto está bajo la Licencia MIT - ver el archivo [LICENSE](LICENSE) para más detalles.

---

## 📞 Contacto

- Email: sergiolvargas95@gmail.com
- LinkedIn: [sergiovargas95](https://www.linkedin.com/in/sergiovargas95/)
- GitHub: [@sergiovargas95](https://github.com/sergiolvargas95)

---

⭐ Si te ha gustado este proyecto, ¡dale una estrella!
