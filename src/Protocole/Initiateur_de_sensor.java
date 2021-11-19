package Protocole;

import Le_robot.Capteur;
import Le_robot.Music;
import lejos.hardware.Button;

//class qui initialise les sensor et attend la presse du bouton pour terminer
public class Initiateur_de_sensor {
	
	
	public static void initialiseTout() throws InterruptedException {
		initialise_color();
		initialise_touch();
		initialise_US();
		System.out.print("Let's go !");
		Music.The_imperial_march();
		System.out.print("Yo, je suis ready !");
		Button.ENTER.waitForPress();
	}
	
	public static void initialise_color() {
		Capteur.couleurDetectee();
	}
	
	public static void initialise_touch() {
		Capteur.capteurTactileActive();
	}
	
	public static void initialise_US() {
		Capteur.capteurUS.enable();
	}
	
}
