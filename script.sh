#!/bin/bash
# -*- ENCODING: UTF-8 -*-

### CLONE ###
echo "##################################################"
echo "Clonage du dépôt Git.."
echo "##################################################"
git clone https://github.com/oulanbator/grand-restaurant-java.git
cd grand-restaurant-java

### BRANCH ###
echo "##################################################"
echo "Choix de la branche.."
echo "##################################################"
git branch -a
choixValide=false
while [ $choixValide = false ]
do
	echo "Veuillez saisir la branche souhaitée :"
	read branche
	success=$(git branch -a | grep $branche)
	
	if [ "$success" != '' ]; then
		git checkout $branche
		echo "Bravo, vous êtes dans la branche : $branche"
		choixValide=true
	# elif [ "$branche" == "main" ]; then
	# 	echo "On reste sur la branche main"
	# 	choixValide=true
	# 	continue
	else
		echo "Désolé mauvaise saisie"
		git branch -a
	fi
done

### BUILD & TESTS ###
echo "##################################################"
echo "Build et tests du projet.."
echo "##################################################"
mvn clean package 

### RESULTS ###
echo "##################################################"
echo "Analyse des résultats de tests.."
echo "##################################################"
cd target/surefire-reports
# Set un trigger pour le déploiement à true
deployApp=true
# Parcours tous les fichiers du répertoire avec une extension en .txt
while read fichier
do	
	# Lit le fichier txt, stocke chaque ligne dans une var temporaire
	while IFS= read -r line
	do 
		# stocke les lignes contenant la chaine "Tests run" dans "target"
		target=$(echo $line | grep "Tests run")
		# si c'est une ligne target, décompose la ligne
		if [ "$target" != '' ]; then
			set -- $target
			failures=${5:0:1} #récupère premier chiffre du nombre de failures
			errors=${7:0:1} # récupère premier chiffre du nombre d'erreurs
			# Si échecs => set trigger à false
			if [ $failures != '0' ]; then
				echo "Echecs détectés : $target"
				deployApp=false
			fi
			# Si erreurs => set trigger à false
			if [ $errors != '0' ]; then
				echo "Erreurs détectées : $target"
				deployApp=false
			fi
		fi
	done < "$fichier"
done <<EOT
$(find . | grep ".txt")
EOT

### DELOY APP ###
if [ $deployApp = "true" ]; then
	serverport=8888
	portinuse=$(netstat -ano | grep $serverport)
	if [ "$portinuse" != '' ]; then
		echo "Le port $serverport semble déjà utilisé. Choisissez le port sur lequel lancer l'API :"
		read input
		serverport=$input
	fi
	cd ..
	echo "##################################################"
	echo "Déploiment de l'API (sur le port $serverport)"
	echo "##################################################"
	nohup java -jar grand-restaurant-0.0.1-SNAPSHOT.jar --server.port=$serverport &
	echo "Sleep quelques secondes en attendant que l'api se lance..."

	deployed=false
	while [ $deployed = false ]
	do
		sleep 1
		portinuse=$(netstat -ano | grep $serverport)
		if [ "$portinuse" != '' ]; then
			deployed=true
		fi
	done

	echo "Déploiement terminé"

	echo "##################################################"
	echo "CURL de l'API"
	echo "##################################################"
	echo "Création d'un pool de restaurants :"
	echo "curl http://localhost:8888/create-restaurants"
	curl -i -H "Accept: application/json" "localhost:8888/create-restaurants"
	echo " "
	echo " ************************************************** "
	echo " "
	echo "Requête des restaurants de la BDD :"
	echo "curl http://localhost:8888/restaurants"
	curl -i -H "Accept: application/json" "localhost:8888/restaurants"
	echo " "
	echo " ************************************************** "
	echo " "

	echo "##################################################"
	echo "Script terminé"
	echo "##################################################"
	echo ">>> L'API continue à tourner en background sur localhost:$serverport !!" 
	echo "Pensez à la kill avec les commandes PowerShell :"
	echo "netstat -ano | findstr $serverport"
	echo "taskkill  /F  /PID <APP_PID>"

else
	echo "##################################################"
	echo "ERREURS DETECTEES : L'API ne sera pas déployée"
	echo "##################################################"
fi
echo " "
echo ">>> Appuyez sur ENTREE pour quitter"
read quitter

