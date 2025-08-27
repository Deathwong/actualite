# Actualité

Service REST Spring Boot pour la gestion d'actualités (création, consultation, modification, suppression et recherche par date), avec gestion d'habilitation et persistance PostgreSQL.

## Sommaire
- Description
- Fonctionnalités
- Prérequis
- Installation et démarrage
- Configuration (application.properties)
- API REST
- Tests
- Packaging
- Structure du projet

## Description
Ce projet est une application Spring Boot (Java 17) exposant des endpoints REST pour gérer des actualités et leurs éléments associés (sections, ressources, images, filtres, habilitations). Les données sont stockées dans une base PostgreSQL. La validation d'entrées et une gestion d'erreurs personnalisée sont intégrées.

## Fonctionnalités
- Créer une actualité avec ses sections, ressources, images, filtres et habilitations.
- Consulter une actualité en vérifiant les habilitations (codes d'accès).
- Modifier et supprimer une actualité en toute sécurité.
- Rechercher des actualités par date de création.
- Validation des DTOs et réponses normalisées.

## Prérequis
- Java 17+
- Maven 3.8+
- PostgreSQL 13+

## Installation et démarrage
1. Cloner le dépôt puis se placer à la racine du projet.
2. Configurer la base de données (voir section Configuration).
3. Construire le projet:
   - Windows PowerShell: `mvnw.cmd clean package`
   - Ou avec Maven installé: `mvn clean package`
4. Démarrer l'application:
   - `mvn spring-boot:run`
   - ou exécuter le jar: `java -jar target/actualite-0.0.1-SNAPSHOT.jar`

L'application démarre par défaut sur: http://localhost:8080

## Configuration (application.properties)
Extrait et valeurs par défaut:
- spring.jpa.hibernate.ddl-auto=update
- spring.jpa.show-sql=true
- spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
- spring.datasource.url=jdbc:postgresql://localhost:5432/actualite
- spring.datasource.username=postgres
- spring.datasource.password=admin

Adapter l'URL, l'utilisateur et le mot de passe à votre environnement PostgreSQL. Créez préalablement la base `actualite` si nécessaire.

## API REST
Base path: `/actualite`

- POST `/actualite`
  - Description: Créer une actualité.
  - Corps: `ActualiteDto` (JSON) validé.
  - Réponse: `{ "data": <id> }` avec l'identifiant créé.

- GET `/actualite?id={id}&codesAcces={code1}&codesAcces={code2}`
  - Description: Consulter une actualité. Vérifie l'existence et l'habilitation via les `codesAcces` (facultatifs selon votre logique).
  - Réponse: `{ "data": <ActualiteDto> }`

- PUT `/actualite?id={id}&codesAcces=...`
  - Description: Modifier une actualité existante (validation + habilitation).
  - Corps: `ActualiteDto`.
  - Réponse: `{ "data": null }` si succès.

- DELETE `/actualite?id={id}&codesAcces=...`
  - Description: Supprimer une actualité (vérifie existence + habilitation).
  - Réponse: `{ "data": null }` si succès.

- GET `/actualite/all?dateCreation=YYYY-MM-DD`
  - Description: Rechercher des actualités par date de création.
  - Réponse: `{ "data": [ <ActualiteDto>, ... ] }`

Codes d'erreur gérés via des exceptions personnalisées:
- `ConstraintViolationException` (validation)
- `EntityNotFoundException` (ressource inexistante)
- `NotHabilitatedException` (accès non autorisé)

## Tests
Lancer les tests unitaires:
- `mvn test`

## Packaging
Générer le jar exécutable:
- `mvn clean package`
- Jar produit: `target/actualite-0.0.1-SNAPSHOT.jar`

## Structure du projet (extrait)
- `src/main/java/com/jeff/actualite/controler/ActualiteController.java` — Endpoints REST.
- `src/main/java/com/jeff/actualite/service/**` — Services métiers.
- `src/main/java/com/jeff/actualite/domain/**` — DTOs, entités, mappers, réponses.
- `src/main/java/com/jeff/actualite/repository/**` — Accès aux données (Spring Data JPA).
- `src/main/resources/application.properties` — Configuration.
- `pom.xml` — Dépendances Maven.

## Notes
- Le dialecte JPA est configuré pour PostgreSQL; adaptez la configuration si vous utilisez un autre SGBD.
- `ddl-auto=update` convient au développement; préférez des migrations (Flyway/Liquibase) en production.

## Licence
Ce projet est fourni « tel quel ». Ajoutez une licence (MIT, Apache 2.0, etc.) si nécessaire.
