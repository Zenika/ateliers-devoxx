## Prérequis pour dérouler les exercices

### Docker

Docker : https://docs.docker.com/engine/installation/linux/ubuntulinux/

### Neo4j

Neo4j tournant dans un docker.

Pour le TP pas besoin d'un volume persistant :
```
docker run --detach --name neo4j --publish 7474:7474 neo4j
```

Pour lancer le conteneur Neo4j avec un volume :
```
docker run --detach --name neo4j --publish 7474:7474 \
           --volume $HOME/neo4j/data:/data neo4j
```

Le console Neo4j sera disponible à l'adresse http://localhost:7474/browser
