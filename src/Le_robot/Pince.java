package Le_robot;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;

/**
 * <b>Permet de controler les pinces du robot</b>
 * @param ouverte : <i>boolean</i> (etat des pinces)
 * @param moteur_pince : <i>BaseRegulatedMotor</i> (moteur des pinces)
 * @param angle : <i>int</i> (angle de rotation des moteur necessaire)
 * 
 * @author Noe GRAVRAND
*/
public class Pince {
	
	/**
	 * Represente si les pinces sont ouvertes ou non
	 */
	private boolean ouverte = false;
	/**
	 * Represente le moteur qui controle les pinces
	 */
	static final BaseRegulatedMotor moteur_pince = Motor.B;//moteur pince 
	
	/**
	 * Represente l'angle de rotation des moteurs necessaire pour ouvrire les pinces
	 */
	private final int angle=20;
	
	
	
	/**
	 * <b>Constructeur inutil mais necessaire de la class Pince</b>
	 * @author Hugo Apeloig
	 */
	public Pince() {
	}
	
	/**
	 * <b>Constructeur de la class <i>Pince</i></b>
	 * @param ouvert : <i>boolean</i> (<b>true</b> si les pinces sont ouvertes, <b>false</b> sinon)
	 */
	public Pince(boolean ouvert) {
		ouverte = ouvert;
	}

	
	
	/**
	 * <b>Ouvre les pince, que si elles sont fermes</b>
	 * @author Noe GRAVRAND
	 */
	public void ouverture() {
		if(!ouverte) {
			moteur_pince.rotateTo(angle);
			ouverte =true;
		}
		
	}
	
	/**
	 * <b>Ouvre les pinces, que si elles sont ouvertes</b>
	 * @author Noe GRAVRAND
	 */
	public void fermeture() {
			if(ouverte) {
				moteur_pince.rotateTo(-angle);
				ouverte = false;
			}
		
	}
	
	
	/**
	 * <b>Ouvre les pinces, que si elles sont fermes</b>
	 * @author Noe GRAVRAND
	 */
	public void ouverture_mobile() {
		if (!ouverte) {//si pinces fermees
			try {
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
	 * <b>Ouvre les pinces en musique, que si elles sont fermes</b>
	 * @author Noe GRAVRAND
	 */
	public void ouverture_music() {
		if (!ouverte) {//si pinces fermes
			try {
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
	 * <b>Ferme les pinces en musique, que si elles sont ouvertes</b>
	 * @author Noe GRAVRAND
	 */
	public void fermeture_music() {
		if (ouverte) {//si pinces ouvertes
			try {
				moteur_pince.rotateTo(-angle, true);
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