# Ez Learning

> e-learning web application made using Java, Spring Boot, MySql and Materialize

[![GitHub](https://img.shields.io/github/license/donnatto/ez-learning?color=purple)](https://opensource.org/licenses/MIT)
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/donnatto/ez-learning?color=red)](https://github.com/donnatto/ez-learning/releases)
[![GitHub issues](https://img.shields.io/github/issues/donnatto/ez-learning)](https://github.com/donnatto/ez-learning/issues)
![GitHub repo size](https://img.shields.io/github/repo-size/donnatto/ez-learning?color=blue&label=size)
[![GitHub stars](https://img.shields.io/github/stars/donnatto/ez-learning?style=social)](https://github.com/donnatto/ez-learning/stargazers)

---

## Start the Application

To start the application locally with the default profile (dev), run:

```bash
./mvnw spring-boot:run
```

The app will bind to `0.0.0.0` and listen on port `3001` by default. These defaults are configured in `src/main/resources/application.properties` and can be overridden via environment variables:

- SERVER_PORT — default: 3001
- SERVER_ADDRESS — default: 0.0.0.0

Examples:

```bash
# Use defaults (port 3001, address 0.0.0.0)
./mvnw spring-boot:run

# Override port only
SERVER_PORT=8080 ./mvnw spring-boot:run

# Override both address and port
SERVER_ADDRESS=127.0.0.1 SERVER_PORT=9090 ./mvnw spring-boot:run
```

Note: Do not pass `--server.*` CLI arguments to Maven. Use environment variables (SERVER_PORT, SERVER_ADDRESS) as shown above; the application reads them directly.

---

## Profiles

This project has two profiles:
- dev (H2, default)
- prod (MySQL)

Activate a profile with:

```bash
./mvnw spring-boot:run -Dspring-boot.run.profiles=prod
```

This change does not alter profile-specific properties in `application-dev.properties` or `application-prod.properties`.

---

## General Info

This application started as an academic project in August 2019, developed for the Business Applications Development II course at Isil, Lima, Perú.

It's an e-learning platform where you can explore courses, teachers, and register to take as many courses as you like.

---

## Technologies

Uses [Thymeleaf](https://www.thymeleaf.org/) as the template engine for the Frontend, which was styled using [Materialize](https://materializecss.com/).

The backend is developed in Java using [Spring Boot](https://spring.io/projects/spring-boot) with Spring MVC, Spring JPA and Spring Security dependencies.

It has 2 application profiles, one for development and one for production. The dev profile uses an in memory [H2 Database](https://www.h2database.com/), while the production one uses [MySql](https://www.mysql.com/). Both of them use SQL versioning with [Flyway](https://flywaydb.org/).

The web application is hosted in [Heroku](https://www.heroku.com/), while the MySql database is hosted in an [AWS RDS](https://aws.amazon.com/rds/) instance.

---

## Contact

Reach out to me at:

- My personal page : [donnatto.com](https://donnatto.com)
- My Blog : [blog.donnatto.com](https://blog.donnatto.com)
- LinkedIn : [linkedin.com/in/donnatto](https://linkedin.com/in/donnatto)
- Instagram : [@donnatto_](https://instagram.com/donnatto_)
- Email : [contact@donnatto.com](mailto:contact@donnatto.com)

---

## License

[MIT License](https://opensource.org/licenses/MIT)
