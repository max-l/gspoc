
#1 cr�er un r�pertoire ./lib, t�l�charger GWT, et y copier les jars suivants :
 gwt-api-checker.jar
 gwt-dev.jar
 gwt-ll.dll
 gwt-servlet.jar
 gwt-soyc-vis.jar
 gwt-user.jar

#2 d�marrer SBT dans le r�pertoire racine du projet

#3 ex�cuter la commande : sbt> update

#4 Pour d�boguer :
- lancer Salut dans idea
- lorsque le code scala est modifi�, faire sbt> jetty-restart