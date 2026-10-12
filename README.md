# CampusConnect: Backend

REST API for **CampusConnect**, a Student Attendance and Result Management Portal.
Frontend repo: https://github.com/thrishikanaidu-source/campusconnect-frontend

## Tech stack
- Java 17+, Spring Boot
- Spring Web, Spring Data JPA
- MySQL

## Features
- Role-based login (Admin, Teacher, Student)
- CRUD APIs for students, teachers and courses
- Attendance marking with percentage calculation
- Marks entry and per-student results

## Project structure
```
controller/   REST endpoints
model/        Database entities
repository/   Database access (Spring Data JPA)
config/       CORS settings and default admin account
```

## How to run
1. Install JDK 17+ and MySQL.
2. Create the database:
```sql
   CREATE DATABASE campusconnect;
```
3. Copy `application.properties.example` to `src/main/resources/application.properties` and put in your MySQL password.
4. Run the app from your IDE (`CampusconnectApplication`) or with `mvnw spring-boot:run`.
5. The API runs at `http://localhost:8080`.

Tables are created automatically on first run.

## Default login
- Username: `admin`
- Password: `admin123`

## Main API endpoints
| Endpoint | Purpose |
|---|---|
| `POST /api/auth/login` | Log in |
| `POST /api/auth/register` | Create a login |
| `/api/students` | Student CRUD |
| `/api/teachers` | Teacher CRUD |
| `/api/courses` | Course CRUD |
| `/api/marks` | Marks CRUD, `/student/{id}` for one student |
| `/api/attendance` | Attendance, `/student/{id}/percentage` for attendance % |

## Author
D.Thrishika
