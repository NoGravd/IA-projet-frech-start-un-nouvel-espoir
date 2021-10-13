package Le_code;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;

public class Pince {
	static final int TIME_PINCE = 3000;
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
			mP.backward();
			Delay.msDelay(TIME_PINCE);
			mP.stop();
			Memoire.mvmtPince(false);
		}
	}
	
}