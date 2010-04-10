
#1 créer un répertoire ./lib, télécharger GWT, et y copier les jars suivants :
 gwt-api-checker.jar
 gwt-dev.jar
 gwt-ll.dll
 gwt-servlet.jar
 gwt-soyc-vis.jar
 gwt-user.jar

#2 démarrer SBT dans le répertoire racine du projet

#3 exécuter la commande : sbt> update

#4 Pour déboguer :
- lancer Salut dans idea
- lorsque le code scala est modifié, faire sbt> jetty-restart