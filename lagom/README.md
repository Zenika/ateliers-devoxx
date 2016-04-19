![lagom](https://d3gnpvjw8j16uq.cloudfront.net/assets/images/lagom/lagom-hero.jpg)

#Lagom
## Framework de microservices 

Ce framework édité par lightBend (ex TypeSafe) propose une solution permettant la mise en place d'une architecture basée sur des microservices. 

Lagom a été construit selon les principes réactifs décrit dans le [Reactive manifesto](http://www.reactivemanifesto.org/). Il met également en avant les designs comme CQRS et Event Sourcing.

Je vous invite à consulter le site du projet [Lagom](http://www.lagomframework.com/documentation/1.0.x/Installation.html) pour le découvrir plus profondeur.

Pour l'heure, je vous propose un petit TP qui vous permettra de jouer un peu avec!


## Objectifs du TP

L'objectif de ce TP est de coder un service qui **exposera un flux twitter**. Sur ce flux on appliquera un filtre pour ne recevoir que des tweets précis.

Pour démarrer, vous avez un squelette de projet à disposition. 

## Informations

Pour facilité la réalisation du TP, voici quelques informations : 

 - **sbt runAll** -> permet d'exécuter le projet
 - **com.zenika.ts.TwitterStreamBuilder** -> classe permettant de faciliter la création d'un stream sur le flux twitter
 - **com.zenika.ts.FunctionnalStatusListener** -> Interface exposant la seule méthode à implémenter pour récupérer le status courant du flux.
 - **[doc](http://www.lagomframework.com/documentation/1.0.x/PubSub.html)** -> documentation utile pour la réalisation du TP
 
*Bien évidemment, toutes les questions trouveront réponses en vous adressant aux Zenikéens qui vous accompagne ;)*

## Ce que nous allons faire pendant ce TP


	Recommandation pour faire ce TP, utiliser au maximum les informations fournies ci-dessus. 


### Etape 1 

C'est la classe **TwitterServiceImpl** qu'il faudra intervenir. Les imports laisser pourront vous guider.
La première chose à faire est de répondre au besoin de l'interface **TwitterService**

### Etape 2

Il va falloir initialiser les attributs de la classe. Pour l'attribut **pubsub**, consulter la documentation proposée dans le chapitre information.
En lisant cette doc, vous aurez un aperçu de ce que nous voulons obtenir à terme.

Il faudra également initialiser le flux Twitter. Pour cela on utilisera la classe helper **TwitterStreamBuilder** qui va initialiser et configurer le stream Twitter que l'on filtrera par la suite. L'attribut twitterStream servira à récupérer l'instance créée. 



### Etape 3

Implémentation de la méthode **tweets**. Cette méthode a pour objectif de streamer les tweets filtré issue du flux twitter.
Toujours en vous rapportant à la documentation, implémenter cette méthode. 


	On utilisera pour la création du TopicId le type String et la constante ID. Ce type correspond type des messages qui seront publiés, en l'occurence le status des tweets.

### Etape 4

Nous allons maintenant implémenter la méthode **updateFilter**. Pour se mettre à l'écoute et capter les nouveaux tweets, il faudra implémenter l'interface **FunctionalStatusListener** et fournir cette implémentation à notre **twitterStream**

	Avant chaque mise à jour, n'oubliez pas de faire un clean des listeners! 
	Aussi, pour lancer la consommation du stream twitter, il faut appliquer un filtre via la méthode **filter**
	
### Etape 5

Nous pouvons maintenant lancer le serveur via la méthode sbt runAll et ouvrir la page index.html située dans le module **web** du projet (le fichier doit être ouvert directement à partir du filesystem dans le navigateur).

Pour démarrer le flux renseigner un mot clef dans le champ de saisi et cliquer sur le bouton update filter. Après quelques secondes le flux devrait se mettre à jour. Pour mettre à jour le flux, recommencer l'opération.

#Si les tweets s'affichent dans la page, c'est gagné ! ;)
