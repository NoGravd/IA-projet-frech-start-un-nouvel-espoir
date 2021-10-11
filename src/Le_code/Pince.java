package Le_code;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;

public class Pince {
	static final int TIME_PINCE = 3000;
	static final BaseRegulatedMotor mP = Motor.B;//moteur pince
	static final BaseRegulatedMotor mC = Motor.C;//moteur roue
	static final BaseRegulatedMotor[] l = new BaseRegulatedMotor[] {mC};
	
	
	//useless (mem)
	/*
	public static boolean etatPince = true;//Attention : il faut donc que la pince soit ouverte avant le run du prgrm
	public static  Memoire memoire;
	public Pince (Memoire mem) {
		memoire = mem;
	}
	*/
	
	
	public static void oPince() {
		if (!Memoire.getEtatPince()) {//si Pince fermées
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