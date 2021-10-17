package Le_code;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;

public class Pince {
	static final int TIME_PINCE = 3000; //pas touche : ca marche
	static final BaseRegulatedMotor mP = Motor.B;//moteur pince
	
	
	
	public static void oPince() {
		if (!Memoire.getEtatPince()) {//si Pince fermees
			mP.forward();
			Delay.msDelay(TIME_PINCE);
			mP.stop();
			Memoire.mvmtPince(true);
		}
	}
	
	public static void fPince() {
		if (Memoire.getEtatPince()) {//si Pince ouvertes
			if (Capteur.capteurTactileActive())//si le palet
				Memoire.setAvoirPalet(true);//inscrit memoire
			mP.backward();
			Delay.msDelay(TIME_PINCE);
			mP.stop();
			Memoire.mvmtPince(false);
		}
	}
	
}