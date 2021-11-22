package Protocole;

import Le_robot.Capteur;
import Le_robot.Music;
import lejos.hardware.Button;

//class qui initialise les sensor et attend la presse du bouton pour terminer
/**
	Une classe qui initialise les sensors et attend la presse du bouton pour terminer
 */
public class Initiateur_de_sensor {
	
	
	/**
	Une méthode qui initialise les sensors et attend la presse du bouton pour terminer
 */
	public static void initialiseTout() throws InterruptedException {
		initialise_color();
		initialise_touch();
		initialise_US();
		System.out.print("Let's go !");
		Music.The_imperial_march();
		System.out.print("Yo, je suis ready !");
		Button.ENTER.waitForPress();
	}
	
	/**
	Une méthode qui initialise le capteur couleurs.
	 */
	public static void initialise_color() {
		
//		capteurCo.setFloodlight(true);					// A verifier mais normalement ça marche comme ça
//		capteurCo.setFloodlight(Color.WHITE);
		
	}
	
	/**
	Une méthode qui initialise le capteur tactile.
	 */
	public static void initialise_touch() {
		Capteur.capteurTactileActive();
	}
	
	/**
	Une méthode qui initialise le capteur ultrason.
	 */
	public static void initialise_US() {
		Capteur.capteurUS.enable();
	}
	
}
