package Le_robot;

import java.util.Arrays;


import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;

/**
Une classe qui permet de deplacer le robot
*/
public class Roues {
	
	/**
    Instance qui represente le moteur controler par la roue droite.
	 */
	static final BaseRegulatedMotor moteur_droit = Motor.A;//roue droite
	/**
    Instance qui represente le moteur controler par la roue gauche.
	 */
	static final BaseRegulatedMotor moteur_gauche = Motor.C;//roue gauche
	/**
    Instance qui represente la liste des moteurs a syncrhoniser avec le moteur A pour avancer et reculer.
	 */
	static final BaseRegulatedMotor[] l = new BaseRegulatedMotor[] {moteur_gauche};
	/**
    Instance qui represente la vitesse max du robot.
	 */
	static final int VITESSE_MAX = (int) moteur_gauche.getMaxSpeed();
	
	
	/**
    Demarre le moteur et avance petit à petit(aceleration).
	 */
	public static void demare() {
		//acceleration
		int nAcc = 200; //definition du nb de marches d'accélération
//		int maxSpeed = 400; //vitesse max = 100xVbatterie
		for (int i=0; i<nAcc; i++) {
			moteur_droit.setSpeed(VITESSE_MAX/nAcc*i);//change la vitesse
			moteur_gauche.setSpeed(VITESSE_MAX/nAcc*i);
			moteur_droit.forward();//lance le moteur 
			moteur_gauche.forward();
			Delay.msDelay(1);// attend 3ms
		}
//		stop();
 	}
	
	public static void avanceTQpalet() {
		Pince.oPince_mobile();
		Capteur.demarrerCapteurUltraSon();
		moteur_droit.forward();
		moteur_gauche.forward();
		while (Capteur.getDistanceOb()>1) { //tant qu'il ne fonce pas dans 1 mur
			while (!Capteur.capteurTactileActive())
				Delay.msDelay(1);
			stop();
			Pince.fPince();
		}
		stop();
		Capteur.eteindreCapteurUltraSon();
	}
	
	/**
    Arrete les moteurs.
	 */
	public static void stop() {
		moteur_droit.synchronizeWith(l);
		moteur_droit.startSynchronization();
		moteur_gauche.stop();
		moteur_droit.stop();
		moteur_droit.endSynchronization();
 	}
 	
	/**
    Pivote de 90° degres sur la droite.
	 */
 	public static void droite() {
 		pivote (90);
	}
	
	/**
    Pivote de 90° degres sur la gauche.
	 */
	public static void gauche() {
		pivote (-90);
	}
	
	/**
    Pivote de maniere a faire un demi tour, partant à droite.
	 */
	public static void demi_tour_droite() {
		pivote (180);
	}
	
	/**
    Pivote de maniere a faire un demi tour, partant à gauche.
	 */
	public static void demi_tour_gauche() {
		pivote (-180);
	}
	
	/**
    Pivote de maniere a faire un demi tour, en partant soit a gauche, soit a droite (determiner de maniere aleatoire.
	 */
	public static void demi_tour() {
		int random= (int) Math.round(Math.random());
		if (random==0)
			demi_tour_gauche();
		else
			demi_tour_droite();
	}
	
	/**
    Demarre le moteur et recule petit à petit(aceleration).
	 */
	public static void recule() {
		//accélération
		int nAcc = 200; //definition du nb de marches d'accélération
		//				int maxSpeed = 400; //vitesse max = 100xVbatterie
		for (int i=0; i<nAcc; i++) {
			moteur_droit.setSpeed(VITESSE_MAX/nAcc*i);//change la vitesse
			moteur_gauche.setSpeed(VITESSE_MAX/nAcc*i);
			moteur_droit.backward();//lance le moteur 
			moteur_gauche.backward();
			Delay.msDelay(1);// attend 3ms
		}
		stop();
		if (Memoire.getEtatPince())//si les pince sont ouvertes
			Memoire.setAvoirPalet(false);//alors on a pas/plus de palet
	}
	
	/**
    Permet d'avancer pendant tmps secondes.
    @param tmps Le temps que le moteur avance
	 */
	public static void rouleTemps(int tmps) {
		moteur_droit.setSpeed(VITESSE_MAX);
		moteur_gauche.setSpeed(VITESSE_MAX);
		moteur_droit.forward();
		moteur_gauche.forward();
		Delay.msDelay(tmps*1000);
	}
	
	public static void rouleDist (int metre) {
		//TODO
	}
	
	/**
    Pivote de l'angle fourni en parametre.
    @param degre Le degre de rotation voulu
	 */
	public static void pivote (int degre) {
		double degreD = degre*4.333;
		int degre2 = (int) Math.round(degreD);
		try {
			moteur_droit.rotateTo((-degre2)/2, true);
			moteur_gauche.rotateTo(degre2/2, true);
			int ii=0;
			while (moteur_gauche.isMoving())
				ii++;//juste pour que le bot calclul
			degreD+=ii;//ca c juste pour que eclipse rale pas car ii sert a rien
			Carto.rotateDeg(degre);
		} catch (Throwable t) {
			t.printStackTrace();
			Delay.msDelay(10000);
			System.exit(0);
		}
	}
	
	/**
    Fonction en chantier.
	 */
	public static void pivoteUS (int degre) {
		Capteur.demarrerCapteurUltraSon();
		float[] tab = new float [1000000];
		double degreD = degre*4.333;
		int degre2 = (int) Math.round(degreD);
		try {
			moteur_droit.rotateTo((-degre2)/2, true);
			moteur_gauche.rotateTo(degre2/2, true);
			int ii=0;
			while (moteur_gauche.isMoving()) {
				Capteur.capteurUS.getDistanceMode().fetchSample(tab, ii);
				ii++;//juste pour que le bot calclul
			}
			Capteur.eteindreCapteurUltraSon();
			int jj=0;
			while(tab[jj]!=(float) 0) {
				jj++;
			}
			tab = Arrays.copyOf(tab, jj);
			float min = tab[0];
			int indicdumin = 0;
			for (int kk=1; kk<tab.length; kk++) {
				if (min > tab[kk]) {
					min = tab[kk];
					indicdumin = kk;
				}
			}
			int angle = (indicdumin/tab.length)*1560;
			Roues.moteur_droit.rotateTo(angle);
			System.out.println(""+angle+"   "+tab.length);
		} catch (Throwable t) {
			t.printStackTrace();
			Delay.msDelay(10000);
			System.exit(0);
		}
	}
}