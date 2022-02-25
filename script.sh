#!/bin/bash
# -*- ENCODING: UTF-8 -*-

### CLONE ###
echo "Clonage du dépôt Git.."
git clone https://github.com/oulanbator/grand-restaurant-java.git
cd grand-restaurant-java

### BRANCH ###
echo "Choix de la branche.."
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
echo "Build et tests du projet.."
mvn clean package 

### RESULTS ###
echo "Analyse des résultats de tests.."
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
	cd ..
	java -jar grand-restaurant-0.0.1-SNAPSHOT.jar --server.port=8888 &
	echo ">>> L'API tourne en background sur localhost:8888 !!"
else
	echo "Don't Deploy"
fi

### CURL ###
# curl une route de l'api

sleep 10

