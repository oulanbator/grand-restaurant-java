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
