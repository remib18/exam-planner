# Exam Planner

Un projet universitaire ayant pour but de simplifier la gestion et la planification des examens pour l'université 🥳.

## Installation

Note : Nous recommandons l'utilisation d'IntelliJ IDEA pour l'exécution de l'environnement de développement ou de
production.
Vous trouverez dans ce guide des actions exécutables tel que `Run App` qui sont cliquables dans IntelliJ IDEA.

### Environnement

Il est possible d'utiliser des versions différentes de Java, mais nous recommandons d'utiliser la version 18 au minimum.
Notez qu'il est possible que certaines fonctionnalités ne soient pas disponibles dans les versions antérieures à la 19.

- Java 19.0.1 (openJDK)
- Maven 3.8.5
- IntelliJ IDEA 2022.3.1
- Git 2.33.0
- MySQL 8.0.31
- Systèmes d'exploitations : MacOS ARM64 (13.1), Windows (10, 11)

### Configuration de la BDD

Il n'est à l'heure actuelle pas possible de modifier la configuration de la BDD depuis un fichier de configuration. Si
vous ne pouvez vous conformer à l'utilisateur `root` sans mot de passe sur le serveur `localhost` (port `3306`), vous
devrez modifier le fichier `src/main/java/fr/univtours/examplanner/utils/Database` et modifier les paramètres de
connexion à la BDD.

Lancez le script SQL `generation.sql` à la racine du projet.

Celui-ci se chargera de créer la base de donnée. Assurez-vous de ne pas déjà avoir de BDD nommée `exam_planner`.

### Ajout de données factices

Lancez le script SQL `insertion.sql` à la racine du projet.

Celui-ci se chargera d'ajouter des données factices à la base de donnée.

Vous disposerez alors de 3 utilisateurs:

- `scooling`
- `department`
- `manager`

Ils ont tous par défaut le même mot de passe que leur nom d'utilisateur et ils ont tous un type d'accès correspondant à
leur nom.

### Installation et exécution avec IntelliJ

Installez le projet à l'aide d'IntelliJ IDEA via la configuration `Run App` fournie avec le projet.

### Ou installation avec Maven

Installez le projet à l'aide de Maven

```bash
  mvn resources:copy-resources
  mvn dependency:copy-dependencies
```

Nous n'avons pas réussi à faire fonctionner `maven package`. Il est donc nécessaire de passer par IntelliJ pour lancer
l'application.

## Documentation

Vous trouverez la documentation du projet dans la branche `docs`. Vous avez également une documentation en ligne
déployée via GitHub Pages à l'adresse suivante: https://remib18.github.io/exam-planner

## Tests

### Exécution des tests

Pour lancer les tests, exécutez la configuration `Run Tests` fournie avec le projet.

### Code couvert par les tests

- La connection à la BDD
- Les différentes opérations CRUD sur les utilisateurs

## Avancement

### Fonctionnalités

- [x] Authentification
- [x] Gestion des utilisateurs
- [x] Gestion des départements
- [x] Gestion des salles
- [x] Gestion des examens
- [x] Gestion des groupes
- [x] Gestion des surveillants
- [x] Gestion des Horaires
- [x] Système de traduction intégré
- [ ] Fichier de configuration
- [ ] Export des données

### Known bugs

- [ ] RAS

## Authors

- Rémi Bernard • [@remib18](https://www.github.com/remib18)
- Gabriel Galli • [@KalityGabi](https://www.github.com/KalityGabi)
- Benoît Cheramy • [@beubeu28](https://www.github.com/beubeu28)
- Mya Dumas-Libre • [@MyaDL](https://www.github.com/MyaDL)
- Antonin Rathaux • [@Arokiel](https://www.github.com/Arokiel)

