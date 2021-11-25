package Le_robot;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;

/**
Une classe qui permet de controler les pinces du robot.
*/
public class Pince {
	private boolean ouverte = false;
	/**
    Instance qui permet de determiner le temps d'ouverture et de fermetur de la pince
	 */
	@SuppressWarnings("unused")
	private static final int TIME_PINCE = 3000; //pas touche : ca marche
	/**
    Instance qui represente le moteur qui controle les pinces
	 */
	static final BaseRegulatedMotor moteur_pince = Motor.B;//moteur pince
	
	/**
    Ouvre la pince, que si elle est ferme.
	 */
	public Pince() {
	}
	public void ouverture() {
		if(!ouverte) {
			moteur_pince.rotateTo(20);
			ouverte =true;
		}
		
	}
	
	/**
    Ouvre la pince, que si elle est ouverte.
	 */
	public void fermeture(Capteurs c) {
		//inscrit memoire
			if(ouverte) {
				moteur_pince.rotateTo(-20);
				ouverte = false;
			}
		
	}
	
	/**
    Ouvre la pince, que si elle est fermer.
	 */
	public static void ouverture_mobile() {
		if (!Memoire.getEtatPince()) {//si Pince fermees
			try {
				int angle=20;
				moteur_pince.rotateTo(angle, true);
			} catch (Throwable t) {
				t.printStackTrace();
				Delay.msDelay(10000);
				System.exit(0);
			}
			moteur_pince.stop();
			Memoire.setEtatPince(true);
		}
	}
	
	/**
    Ouvre la pince en musique, que si elle est fermer.
	 */
	public static void ouverture_music() {
		if (!Memoire.getEtatPince()) {//si Pince fermer
			try {
				int angle=20;
				moteur_pince.rotateTo(angle, true);
				Music.Mcdo();
			} catch (Throwable t) {
				t.printStackTrace();
				Delay.msDelay(10000);
				System.exit(0);
			}
			moteur_pince.stop();
			Memoire.setEtatPince(false);
		}
	}
	
	/**
    Ferme la pince en musique, que si elle est ouverte.
	 */
	public static void fermeture_music() {
		if (Memoire.getEtatPince()) {//si Pince ouverte
			try {
				int angle=20;
				moteur_pince.rotateTo(angle, true);
				Music.Mcdo();
			} catch (Throwable t) {
				t.printStackTrace();
				Delay.msDelay(10000);
				System.exit(0);
			}
			moteur_pince.stop();
			Memoire.setEtatPince(false);
		}
	}
}