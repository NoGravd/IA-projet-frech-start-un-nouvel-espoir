package Le_robot;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;

public class Roues {
	static final BaseRegulatedMotor mA = Motor.A;//roue droite
	static final BaseRegulatedMotor mC = Motor.C;//roue gauche
	static final BaseRegulatedMotor[] l = new BaseRegulatedMotor[] {mC};
	static final int VITESSE_MAX = (int) mC.getMaxSpeed();
	
	
	
 	public static void demare() {
		//accélération
		int nAcc = 200; //definition du nb de marches d'accélération
//		int maxSpeed = 400; //vitesse max = 100xVbatterie
		for (int i=0; i<nAcc; i++) {
			mA.setSpeed(VITESSE_MAX/nAcc*i);//change la vitesse
			mC.setSpeed(VITESSE_MAX/nAcc*i);
			mA.forward();//lance le moteur 
			mC.forward();
			Delay.msDelay(1);// attend 3ms
		}
//		stop();
 	}
 	
 	public static void stop() {
 		mA.synchronizeWith(l);
		mA.startSynchronization();
		mC.stop();
		mA.stop();
		mA.endSynchronization();
 	}
 	
 	public static void droite() {
 		pivote (90);
	}
	
	public static void gauche() {
		pivote (-90);
	}
	
	public static void demi_tour_droite() {
		pivote (360);
	}
	
	public static void demi_tour_gauche() {
		pivote (-360);
	}
	
	public static void demi_tour() {
		int random= (int) Math.round(Math.random());
		if (random==0)
			demi_tour_gauche();
		else
			demi_tour_droite();
	}
	
	public static void recule() {
		//accélération
		int nAcc = 200; //definition du nb de marches d'accélération
		//				int maxSpeed = 400; //vitesse max = 100xVbatterie
		for (int i=0; i<nAcc; i++) {
			mA.setSpeed(VITESSE_MAX/nAcc*i);//change la vitesse
			mC.setSpeed(VITESSE_MAX/nAcc*i);
			mA.backward();//lance le moteur 
			mC.backward();
			Delay.msDelay(1);// attend 3ms
		}
		stop();
		if (Memoire.getEtatPince())//si les pince sont ouvertes
			Memoire.setAvoirPalet(false);//alors on a pas/plus de palet
	}
	
	public static void rouleSeconde(int tmps) {
		mA.setSpeed(VITESSE_MAX);
		mC.setSpeed(VITESSE_MAX);
		Delay.msDelay(tmps*1000);
	}
	
	public static void pivote (int degre) {
		int degre2 = degre;
		try {
			mA.rotateTo((-degre2)/2, true);
			mC.rotateTo(degre2/2, true);
		} catch (Throwable t) {
			t.printStackTrace();
			Delay.msDelay(10000);
			System.exit(0);
		}
		//TODO :
		//rslt test : marche pa, s'arrete tro to
		
		
		//old version :
//		mC.rotate(degre/2);
//		mA.rotate(-(degre/2));
	}
	
}