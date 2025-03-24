# Tower Defense

Bienvenue dans Tower Defense, un jeu de stratégie où vous devez défendre votre base contre des vagues d'ennemis en plaçant judicieusement vos tours. Préparez-vous à tester vos compétences tactiques dans des environnements de plus en plus difficiles !

Jeu développé par Nidhal MOUSSA en Java à l'aide de la bibliothèque Swing et JavaFX.

## Prérequis

- Java 17 ou une version ultérieure.
- Gradle est utilisé pour la gestion du projet. Un wrapper Gradle est inclus, vous pouvez donc utiliser `gradlew` (Windows) ou `./gradlew` (Linux/Mac).

## Installation et Lancement

### 1. Cloner le Répertoire du Projet

Clonez le dépôt du projet sur votre machine :

```bash
git clone https://github.com/Nidhalm1/Tower-Defense.git
```
Se déplacer dans le dossier :
```bash
cd Tower-Defense
```

### 2. Compiler et Lancer le Jeu

Placez-vous dans le répertoire racine du projet (là où se trouve le fichier `build.gradle`) puis exécutez les commandes suivantes :

#### Compilation :

```bash
gradle build
```

ou, en utilisant le wrapper Gradle :

```bash
./gradlew build
```

#### Lancement du Jeu :

```bash
gradle run
```

ou, avec le wrapper :

```bash
./gradlew run
```

La commande `gradle run` lancera le mode graphique du jeu en utilisant la classe principale définie dans le fichier `build.gradle`.