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
1. Cloner le dépôt puis se placer à la racine du projet:
   - `git clone https://github.com/Deathwong/actualite`
   - `cd actualite`
2. Préparer la base de données PostgreSQL:
   - Créer une base nommée `actualite` (si votre encodage local supporte « actualité », vous pouvez aussi la nommer « actualité », mais gardez la cohérence avec la configuration).
   - Exécuter le script d'initialisation présent dans ce dépôt: [`conception/bdd/msActualitePlain`](conception/bdd/msActualitePlain) (via votre client SQL). Ce script crée le schéma/tables requis.
3. Configurer les identifiants de connexion si besoin (voir section Configuration). Assurez-vous que les credentials PostgreSQL correspondent à ceux de `src/main/resources/application.properties` ou modifiez-les en conséquence.
4. Construire le projet:
   - Windows PowerShell: `mvnw.cmd clean package`
   - Ou avec Maven installé: `mvn clean package`
5. Démarrer l'application:
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

Note: un export des requêtes Postman pour tester l'API est fourni ici: [`conception/postman/actualite ws.postman_collection.json`](conception/postman/actualite%20ws.postman_collection.json). Importez ce fichier dans Postman et définissez la variable d'environnement `base_url` (ex: http://localhost:8080).

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

### Requêtes Postman (détaillées)
- Fichier de collection: [`conception/postman/actualite ws.postman_collection.json`](conception/postman/actualite%20ws.postman_collection.json)
- Variable d'environnement attendue: `base_url` (ex: `http://localhost:8080`)

1) Créer actualité — OK
- Méthode: POST
- URL: `{{base_url}}/actualite?codesAcces=ADMIN,EDITEUR`
- Corps (JSON, exemple minimal):
```json
{
  "titre": "Titre de l'actualité",
  "introduction": "Texte d'introduction",
  "dateDebutDiffusion": "2024-01-01T00:00:00Z",
  "dateFinDiffusion": "2026-01-01T00:00:00Z",
  "prioritaire": true,
  "active": true,
  "habilitations": [
    { "codeAcces": "ADMIN" },
    { "codeAcces": "EDITEUR" }
  ],
  "sections": [
    {
      "titre": "Section 1",
      "texte": "Contenu",
      "ordre": 1,
      "image": {
        "titre": "visuel.png",
        "contentType": "image/png",
        "taille": 12345,
        "contenu": "BASE64..."
      },
      "ressources": [
        { "libelle": "Documentation", "url": "https://exemple.com", "ordre": 1 }
      ]
    }
  ],
  "filtres": [
    { "code": "THEME", "valeur": "INFO" }
  ]
}
```

2) Exceptions — 404 not found
- Méthode: POST
- URL: `{{base_url}}/actualite/toto`
- Corps: JSON quelconque (déclenche 404 car la route est invalide)

3) Exceptions — 500 internal error
- Méthode: POST
- URL: `{{base_url}}/actualite`
- Corps: Exemple présent dans la collection (destiné à illustrer une 500)

4) Exceptions — save validation Request (création invalide)
- Méthode: POST
- URL: `{{base_url}}/actualite`
- Corps (exemple minimal invalide — titre manquant):
```json
{
  "introduction": "introduction",
  "dateDebutDiffusion": "2022-02-04T18:35:24Z",
  "dateFinDiffusion": "2026-02-15T20:35:24Z",
  "prioritaire": true,
  "active": true
}
```

5) Exceptions — update validation Request (modification invalide)
- Méthode: PUT
- URL: `{{base_url}}/actualite?id=64`
- Corps: Exemple fourni dans la collection (volontairement invalide)

6) Exceptions — unauthorized exception (non habilité)
- Méthode: DELETE
- URL: `{{base_url}}/actualite?id=40&codesAcces=JEAN`

7) Consulter actualité
- Méthode: GET
- URL: `{{base_url}}/actualite?id=40&codesAcces=ADMIN,EDITEUR`

8) Modifier actualité
- Méthode: PUT
- URL: `{{base_url}}/actualite?id=73&codesAcces=ADMIN,EDITEUR`
- Corps (JSON, exemple minimal):
```json
{
  "titre": "Nouveau titre",
  "introduction": "Nouvelle intro",
  "dateDebutDiffusion": "2024-01-01T00:00:00Z",
  "dateFinDiffusion": "2026-01-01T00:00:00Z",
  "prioritaire": false,
  "active": true,
  "sections": [
    { "titre": "Section 1", "texte": "Contenu modifié", "ordre": 1 }
  ]
}
```

9) Supprimer actualité
- Méthode: DELETE
- URL: `{{base_url}}/actualite?id=73&codesAcces=ADMIN,EDITEUR`

10) Rechercher — pagination/tri
- Méthode: GET
- URL: `{{base_url}}/actualite/all?page=0&size=5&sort=dateCreation,desc`

11) Rechercher — avec critère dateCreation
- Méthode: GET
- URL: `{{base_url}}/actualite/all?dateCreation=2022-02-04`

Notes d’utilisation:
- Importez la collection Postman ci-dessus.
- Définissez la variable `base_url` (ex: `http://localhost:8080`).
- Certains endpoints exigent des `codesAcces` (ex: `ADMIN,EDITEUR`).
- Les validations peuvent retourner des erreurs si les contraintes DTO ne sont pas respectées.

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
