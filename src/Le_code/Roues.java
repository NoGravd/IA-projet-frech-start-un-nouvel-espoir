package Le_code;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;

public class Roues {
	static final BaseRegulatedMotor mA = Motor.A;//roue droite
	static final BaseRegulatedMotor mC = Motor.C;//roue gauche
	static final BaseRegulatedMotor[] l = new BaseRegulatedMotor[] {mC};
	static final int VITESSE_MAX = 400;
	
	
	//useless (mem)
	/*
	public Memoire memoire;
	public Roues (Memoire mem) {
		memoire = mem;
	}
	*/
	
	
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
		mC.rotate(360);
//		mC.rotate(180);
//		mA.rotate(-180);
	}
	
	public static void gauche() {
		mA.rotate(360);
//		mC.rotate(-180);
//		mA.rotate(180);
	}
	
	public static void demi_tour_droite() {
		mA.rotate(360);
		mA.rotate(360);
//		mC.rotate(180);
//		mA.rotate(-180);
//		mC.rotate(180);
//		mA.rotate(-180);
	}
	
	public static void demi_tour_gauche() {
		mC.rotate(360);
		mC.rotate(360);
//		mA.rotate(180);
//		mC.rotate(-180);
//		mA.rotate(180);
//		mC.rotate(-180);
	}
	
	public static void demi_tour() {
		//TODO pas sur que  math.random soit bien random ma o pire balek
		int random= (int)Math.random();
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
	}
	
	public static void rouleSeconde(int tmps) {
		mA.setSpeed(VITESSE_MAX);
		mC.setSpeed(VITESSE_MAX);
		Delay.msDelay(tmps*1000);
	}
	
	public static void pivote (int degre) {
		mC.rotate(degre/2);
		mA.rotate(-(degre/2));
	}
	
}
