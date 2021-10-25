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
	static final int TIME_PINCE = 3000; //pas touche : ca marche
	/**
    Instance qui represente le moteur qui controle les pinces
	 */
	static final BaseRegulatedMotor mP = Motor.B;//moteur pince
	
	/**
    Ouvre la pince, que si elle est ferme.
	 */
	public static void oPince() {
		if (!Memoire.getEtatPince()) {//si Pince fermees
			mP.forward();
			Delay.msDelay(TIME_PINCE);
			mP.stop();
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
			mP.backward();
			Delay.msDelay(TIME_PINCE);
			mP.stop();
			Memoire.mvmtPince(false);
		}
	}
	
	public static void oPince_mobile() {
		if (!Memoire.getEtatPince()) {//si Pince fermees
			try {
				int xx=10;//TODO : calculer xx
				mP.rotateTo(xx, true);
			} catch (Throwable t) {
				t.printStackTrace();
				Delay.msDelay(10000);
				System.exit(0);
			}
			mP.stop();
			Memoire.mvmtPince(true);
		}
	}
	
}