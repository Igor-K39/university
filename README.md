### Spring Boot REST API
Моделирует структуру университета, и предоставляет расписание занятий для конкретного студента в конкретный день.  
  
Стек технологий: Java 11, Spring Boot 2.5, Spring Security, REST API(Jackson), Maven, PostgreSQL, Model Mapper, Swagger API, Git, Docker

Команды запуска:  
docker build -t university-app.jar  
docker compose up

API: /swagger-ui/

<a href="doc/cv.md">Детальное пояснение</a>  
<a href="doc/usecase_general.png">Диаграмма вариантов использования</a>  
<a href="doc/activity_authentication.png">Диаграмма деятельности (аутентификация)</a>  
<a href="doc/activity_profile.png">Диаграмма деятельности (текущее расписание)</a>
