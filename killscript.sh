#!/bin/bash
# -*- ENCODING: UTF-8 -*-

### RECHERCHE DU PROCESS ###
echo "Recherche du processus en localhost:8080"
process=$(netstat -ano | grep "0.0.0.0:8080")
echo "Processus trouvé :"
echo "$process"

### RECUPERE LE PID ###
set -- $process
echo "PID du processus à kill : $5"
echo ">>> Confirmer l'arrêt du processus (Y)"

### CONFIRM KILL ###
read input
if [ "$input" == 'Y' ]; then
  echo "kill process"
else
  echo "don't kill"
fi

echo ">>> Appuyer sur ENTREE pour quitter"
read input

