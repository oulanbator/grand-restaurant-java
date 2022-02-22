# SUJET

Une chaîne de restaurants souhaite informatiser ses salles.

Elles fonctionnent toutes de la même manière : un serveur est affecté à un 
certain nombre de tables par le maître d’hôtel en début de service.
Il les gère de bout en bout le temps d’un service.

Lorsqu’un client entre, il est installé à une table libre par l’hôtesse.
Le serveur vient prendre la commande, qu’il transmet numériquement à la cuisine. 
Lorsque la cuisine a préparé le plat, elle fait vibrer le téléphone du serveur, 
qui effectue le service à table. Elle peut aussi refuser une commande faute de stocks. 
Le serveur propose un remplacement au client, au prix du plat d’origine si la 
différence est inférieure à un pourcentage fixé par le gérant. Un plat en rupture 
est immédiatement retiré de la carte.

Les clients règlent en partant, après un certain nombre de plats. 
Le serveur doit ensuite nettoyer et dresser la table pour la libérer.

A tout moment, les clients peuvent solliciter leur serveur pour commander des extras. 
La nourriture doit être demandée numériquement à la cuisine, alors que les boissons 
sont servies par le serveur lui-même au bar. Tout est noté en vue de la note finale.
Les menus sont changés chaque jour par le siège de la franchise. Les gérants peuvent 
modifier les prix si leur restaurant est une franchise. Si c’est une filiale, 
ils ne peuvent pas.

Si un client part sans payer, la note est épinglée puis transmise à la gendarmerie 
après 15 jours sous forme d’un imprimé.

Le siège de la franchise doit pouvoir générer un rapport quotidien des impayés 
et du chiffre d’affaire à 3 niveaux : franchise, restaurant, serveur.

# Documentation générale sur les tests en java
> http://sdz.tdct.org/sdz/les-tests-unitaires-en-java.html
> MockWebServer (lib de test client HTTP) : https://blog.ippon.fr/2021/11/05/mockwebserver/

# Choix framework d'assertion (3 à essayer dans 3 branches différentes)
> JUnit : 
https://www.guru99.com/junit-assert.html#:~:text=What%20is%20Junit%20Assert%3F%20Assert%20is%20a%20method,types%20of%20assertions%20like%20Boolean%2C%20Null%2C%20Identical%20etc.

> AssertJ :

> JSpec : https://www.codeflow.site/fr/article/jspec
(Vulnérabilités sur cette dépendance)

# ITERATIONS 

## ITERATION 1 :
### Consigne :
Développez la solution.
Testez-là, de préférence en TDD.
But : jouer avec les tests et le code.
Git Push a minima à la fin de l’itération.
Git Tag marquant la fin de cette itération.
On critiquera ensuite tous ensemble.

## ITERATION 2 : 
### Consigne :
Ne touchez pas au code.
Refactoring des tests avec tout ce que nous avons vu.
But : des tests propres et lisibles par un techlead externe.
Contrainte : une branche par personne. Aucun setup de tests (frameworks d’assertions&de test) ne doit être identique dans l’équipe.
Mockiste ou anti-mockiste, mais pas de mélange dans une équipe.

### Les outils du testeur :
> Indispensables : 
Framework de test.
Bibliothèque d’assertions.
Console de tests

> Les patterns :
Builder
Generator

> Les doubles de test: Mocks, Stubs, etc.
Dummy
    Utilisé pour remplir les listes d’arguments.
    Ne doit pas être appelé.
    Potentiellement un code smell.
    Idéalement, doit throw une Exception si on l’appelle.

Stub
    Renvoie une valeur fixe indépendante du test.
    Le plus souvent une valeur par défaut, sans signification métier.
    Appeler une classe ou   variable Stub indique aux autres que la valeur n’importe pas dans le cadre du test.
    Si l’issue du test dépend de la valeur, n’appelez pas cela un stub.

Spy
    Enregistre un ou plusieurs appels qu’il reçoit.
    Se comporte comme un Stub.
    Peut allègrement violer les règles SOLID, il sert à cela.

Mock
    Objets répondant des réponses préprogrammés si elles reçoivent les paramètres attendus.
    Peuvent faire le travail des doubles vus précédemment.
    Utilisez toujours des objets plus spécifiques si possible
    Un test unitaire ne peut en compter qu’un seul.
    Un test non-unitaire doit en compter le moins possible.
    Code smell courant.


## ITERATION 3 
### Consigne :
Choisissez un Scope de votre choix dans UseCase
Rédigez un test de recette, un jeu de tests d’intégration (avec une fausse BDD de votre choix), des tests système et des tests unitaires.
Une fois terminé, discutez de ces tests. Écrivez dans le Readme :
Ce que vous pensez d’une telle stratégie.
Comment vous auriez testé ce code.

## ITERATION 4
### Consigne :
Créez un projet d’API. Développez un scope de votre choix.
Créez un script (bash, batch, powershell, perl, python) sur une VM ou un VPS.
Il doit 
    Fetch
    Se fixer sur une branche paramétrable
    Build
    Test
    Si toutes les étapes précédentes ont réussi, déployer en conservant la configuration précédente.
    (valider avec un curl sur localhost)
Incluez le script dans le rendu final !


## ITERATION BONUS
Integration du projet dans un pipeline de CI avec Jenkins et Sonarqube