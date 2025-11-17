# Collaborative Docs (Spring Boot)

Aplicatie demo tip Google Docs (simplificat) cu Spring Boot, WebSockets, JPA (MySQL), Security si front-end Thymeleaf.

## Functionalitati
- Editare document in timp real prin WebSocket/STOMP (`/ws`, topic `/topic/documents/{id}`).
- Persistenta MySQL via Spring Data JPA (`Document` cu versionare optimistica).
- Security pe sesiune cu login formular (utilizatori test: `user/password`, `editor/password`).
- UI minima in Thymeleaf (lista documente, editor live).

## Pornire rapida
1. Configureaza MySQL si creeaza baza (sau lasa `createDatabaseIfNotExist=true`):
   - actualizeaza `spring.datasource.username`/`password` in `src/main/resources/application.properties`.
2. Optional schimba portul/timeout in acelasi fisier.
3. Ruleaza aplicatia:
   ```bash
   mvn spring-boot:run
   ```
4. Acceseaza `http://localhost:8080`, autentifica-te (`user/password`), creeaza sau deschide un document. Deschide doua tab-uri pentru a vedea sincronizarea live.

## Rulare in Docker
- Totul cu un singur command, se construiește imaginea app + pornește MySQL:
  ```bash
  docker-compose up --build
  ```
- Aplicatia asculta pe `http://localhost:8080` (service `app`), MySQL expus pe `3306`.
- Variabile (vezi `docker-compose.yml`): `SPRING_DATASOURCE_URL` pointează la `mysql:3306`, user/parola root `changeme` (schimbă-le după nevoie).

## Endpoints principale
- UI: `/` (lista), `/documents/{id}` (editor).
- REST: `GET /api/documents`, `GET /api/documents/{id}`, `POST /api/documents?title=...`.
- WebSocket: endpoint `/ws` (SockJS), app destination `/app/documents.update`, broadcast `/topic/documents/{id}`.

## Extensii sugerate
- Persistenta utilizatorilor in DB si roluri reale.
- Gestionare conflicte mai avansata (diff/patch) si istoricul versiunilor.
- Teste pentru service si controlere WebSocket/REST.
