package Le_robot;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;

/**
Une classe qui permet de controler les pinces du robot.
*/
public class Pince {
	/**
    Instance qui permet de determiner le temps d'ouverture et de fermetur de la pince
	 */
	private static final int TIME_PINCE = 3000; //pas touche : ca marche
	/**
    Instance qui represente le moteur qui controle les pinces
	 */
	static final BaseRegulatedMotor moteur_pince = Motor.B;//moteur pince
	
	/**
    Ouvre la pince, que si elle est ferme.
	 */
	public static void oPince() {
		if (!Memoire.getEtatPince()) {//si Pince fermees
			moteur_pince.forward();
			Delay.msDelay(TIME_PINCE);
			moteur_pince.stop();
			Memoire.mvmtPince(true);
		}
	}
	
	/**
    Ouvre la pince, que si elle est ouverte.
	 */
	public static void fPince() {
		if (Memoire.getEtatPince()) {//si Pince ouvertes
			if (Capteur.capteurTactileActive())//si avoir palet
				Memoire.setAvoirPalet(true);//inscrit memoire
			moteur_pince.backward();
			Delay.msDelay(TIME_PINCE);
			moteur_pince.stop();
			Memoire.mvmtPince(false);
		}
	}
	
	public static void oPince_mobile() {
		if (!Memoire.getEtatPince()) {//si Pince fermees
			try {
				int angle=10;//TODO : calculer angle
				moteur_pince.rotateTo(angle, true);
			} catch (Throwable t) {
				t.printStackTrace();
				Delay.msDelay(10000);
				System.exit(0);
			}
			moteur_pince.stop();
			Memoire.mvmtPince(true);
		}
	}
	
	public static void oPince_music() {
		if (!Memoire.getEtatPince()) {//si Pince fermer
			try {
				int angle=-10;//TODO : calculer angle
				moteur_pince.rotateTo(angle, true);
				Music.The_imperial_march();//TODO trouver music dont le tmoteur_pinces est egal ou inf o tmoteur_pinces de fermeture de la pince
			} catch (Throwable t) {
				t.printStackTrace();
				Delay.msDelay(10000);
				System.exit(0);
			}
			moteur_pince.stop();
			Memoire.mvmtPince(false);
		}
	}
	
	public static void fPince_music() {
		if (Memoire.getEtatPince()) {//si Pince ouverte
			try {
				int angle=10;//TODO : calculer angle
				moteur_pince.rotateTo(angle, true);
				Music.The_imperial_march();//TODO trouver music dont le tmoteur_pinces est egal ou inf o tmoteur_pinces de fermeture de la pince
			} catch (Throwable t) {
				t.printStackTrace();
				Delay.msDelay(10000);
				System.exit(0);
			}
			moteur_pince.stop();
			Memoire.mvmtPince(false);
		}
	}
}