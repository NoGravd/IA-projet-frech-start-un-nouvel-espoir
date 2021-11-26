package Le_robot;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;

/**
 * Permet de controler les pinces du robot.
 * @param ouverte : boolean (etat des pinces)
 * @param moteur_pince : BaseRegulatedMotor (moteur des pinces)
 * 
 * @author noegr
*/
public class Pince {
	
	/**
	 * Determine si les pinces sont ouvertes ou non
	 */
	private boolean ouverte = false;
	/**
	 * Represente le moteur qui controle les pinces
	 */
	static final BaseRegulatedMotor moteur_pince = Motor.B;//moteur pince
	
	
	
	/**
	 * Constructeur inutil mais necessaire de la class Pince
	 * @author hugoApeloig
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
	 * Ouvre la pince, que si elle est ouverte.
	 * @author noegr
	 */
	public void fermeture() {
			if(ouverte) {
				moteur_pince.rotateTo(-20);
				ouverte = false;
			}
		
	}
	
	
	/**
	 * Ouvre la pince, que si elle est fermer.
	 * @author noegr
	 */
	public void ouverture_mobile() {
		if (!ouverte) {//si Pince fermees
			try {
				int angle=20;
				moteur_pince.rotateTo(angle, true);
			} catch (Throwable t) {
				t.printStackTrace();
				Delay.msDelay(10000);
				System.exit(0);
			}
			moteur_pince.stop();
			ouverte = true;
		}
	}
	
	
	/**
	 * Ouvre la pince en musique, que si elle est fermer.
	 * @author noegr
	 */
	public void ouverture_music() {
		if (!ouverte) {//si Pince fermer
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
			ouverte = true;
		}
	}
	
	
	/**
	 * Ferme la pince en musique, que si elle est ouverte.
	 * @author noegr
	 */
	public void fermeture_music() {
		if (ouverte) {//si Pince ouverte
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
			ouverte = false;
		}
	}
}