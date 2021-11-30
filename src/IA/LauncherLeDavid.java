package IA;

import Protocole.LeDavid;

/**
 * Lance l'IA LeDavid
 * 
 * @author Hugo APELOIG
 */
public class LauncherLeDavid {

	public static void main(String[] args) {
		LeDavid david = new LeDavid(); //Creer un david (l'algo du robot)
		david.brutusPremierPalais(); //Va chercher le premier palais en brut
		david.leSangDeSesMorts(david.adresseArrivee); //Une boucle qui s'arrète que quand il n'y a plus de palais
		}
}