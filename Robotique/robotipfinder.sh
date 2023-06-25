#!/bin/bash
echo "|------------------------|"
echo "| Robot IP finder script |"
echo "|------------------------|"
echo
# 1. éteindre le robot
echo "please make sure the robot is switched off and press any key"
read -n 1 -s
echo
#2. lancer la commande
echo "scanning wifi ..."
fping -gaq -r 0 192.168.2.0/24 > tmp_ip1.txt
echo "... done"
echo
#3. allumer le robot attendre la connection
echo "switch on your robot, wait for the connection led, and press any key"
read -n 1 -s
echo
#4. lancer la commande
echo "scanning wifi ..."
fping -gaq -r 0 192.168.2.0/24 > tmp_ip2.txt
echo "... done"
echo
#5. lancer la commande
echo "Your robot IP is "
diff tmp_ip1.txt tmp_ip2.txt
