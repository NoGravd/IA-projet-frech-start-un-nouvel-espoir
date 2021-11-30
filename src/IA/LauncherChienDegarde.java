package IA;

import Protocole.LeChienDeDavid;

/**
 * Lance l'IA LeDavid
 * 
 * @author Hugo APELOIG
 */
public class LauncherChienDegarde {
	
	public static void main(String[] args) {
		LeChienDeDavid groomit = new LeChienDeDavid();
		groomit.miseEnPlace();
		for(int i=0; i<5; i++) groomit.attaqueBrutus();
		
	}
}
