package Le_robot;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.robotics.Color;
import lejos.utility.Delay;

/**
* <b>Permet de deplacer le robot</b>
* @param moteur_droit : <i>BaseRegulatedMotor</i> (moteur droit/A)
* @param moteur_gauche : <i>BaseRegulatedMotor</i> (moteur gauche/C)
* @param liste : <i>BaseRegulatedMotor[]<i> (liste contenant moteur_gauche)
* @param VITESSE_MAX : <i>int</i> (vitesse maximal des moteurs)
* 
* @author Noe GRAVRAND
*/
public class Roues {
	
	/**
	 * Represente le moteur controleur par la roue droite.
	 */
	public static final BaseRegulatedMotor moteur_droit = Motor.A;//roue droite 
	/**
	* Represente le moteur controler par la roue gauche.
	 */
	public static final BaseRegulatedMotor moteur_gauche = Motor.C;//roue gauche 
	/**
	 * Represente la liste des moteurs a syncrhoniser avec le moteur A pour avancer et reculer.
	 */
	static final BaseRegulatedMotor[] liste = new BaseRegulatedMotor[] {moteur_gauche};
	/**
	 * Represente la vitesse max du robot.
	 */
	static final int VITESSE_MAX = (int) moteur_gauche.getMaxSpeed();
	
	
	
	/**
	 * <b>Avance tant qu'il detecte un pallet</b>
	 * @param capteurs : <i>Capteurs</i>
	 * @param pince : <i>Pince</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	public static void avanceTQpalet(Capteurs capteurs, Pince pince) {
		pince.ouverture_mobile();
		capteurs.demarrerCapteurUltraSon();
		moteur_droit.forward();
		moteur_gauche.forward();
		while (capteurs.getDistanceOb()>1) { //tant qu'il ne fonce pas dans 1 mur
			while (!capteurs.capteurTactileActive())
				Delay.msDelay(1);
			stop();
			pince.fermeture();
		}
		stop();
		capteurs.eteindreCapteurUltraSon();
	}
	
	/**
	 * <b>Arrete tous les moteurs</b>
	 * @author Noe GRAVRAND
	 */
	public static void stop() {
		moteur_droit.synchronizeWith(liste);
		moteur_droit.startSynchronization();
		moteur_gauche.stop();
		moteur_droit.stop();
		moteur_droit.endSynchronization();
 	}
 	
	/**
	 * <b>Demarre le moteur et recule petit a petit (aceleration)</b>
	 * @author Noe GRAVRAND
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
			Delay.msDelay(1);// attend 1ms
		}
		stop();
//		if (Memoire.getEtatPince())//si les pince sont ouvertes
//			Memoire.setAvoirPalet(false);//alors on a pas/plus de palet
	}
	
	/**
	 * <b>Pivote de l'angle fourni en parametre</b><p>
	 * Pivote toujours sur la droite
	 * @param degre : <i>int</i> (degre de rotation voulu)
	 * @author Noe GRAVRAND
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
		} catch (Throwable t) {
			t.printStackTrace();
			Delay.msDelay(10000);
			System.exit(0);
		}
	}
	
	/**
	 * <b>Pivote de maniere a faire un demi tour, a droite</b>
	 * @author Noe GRAVRAND
	 */
	public static void demi_tour() {
		pivote (180);
	}
	
	
	
	//-----Avec capteurs :
	
	/**
	 * <b>Permet de savoir si le robot est sur une ligne de couleur ou si il y a un obstacle a moin 10 cm</b>
	 * @param capteurs : <i>Capteurs</i>
	 * @return <i>boolean</i> : <b>true</b> si sur une ligne de couleur ou si il y a un obsctacle a moin de 10 cm, <b>false</b> sinon.
	 * @author Noe GRAVRAND
	 */
	private static boolean capteursCaptent(Capteurs capteurs, Memoire memoire, Carto carto) {
		int color = (int) capteurs.getCouleur();
		if (carto.couleurDuInt(color, memoire)!=404)
			carto.travLigne(color, memoire);
		if (capteurs.getDistanceOb()<0.1)
			return true;
		return false;
	}
	
	/**
	 * <b>Permet d'accelerer (0 -> VITESSE_MAX), tout en surveillant les rslts des capteurs</b>
	 * @author Noe GRAVRAND
	 */
	public static void demare(Capteurs capteurs, Memoire memoire, Carto carto) {
		int nAcc = 200; //definition du nb de marches d'accélération
		for (int i=0; i<nAcc; i++) {
			moteur_droit.setSpeed(VITESSE_MAX/nAcc*i);//change la vitesse
			moteur_gauche.setSpeed(VITESSE_MAX/nAcc*i);
			moteur_droit.forward();//lance le moteur 
			moteur_gauche.forward();
			if (capteursCaptent(capteurs, memoire, carto)) {stop(); return;}
			Delay.msDelay(1);// attend 1ms
			if (capteursCaptent(capteurs, memoire, carto)) {stop(); return;}
		}
	}
	
	/**
	 * <b>Roule (VITESSE_MAX) pendant un temps x milisec, tout en surveillant les rslts des capteurs</b>
	 * @param milisec : <i>int</i> (temps de deplacement)
	 * @param capteurs : <i>Capteurs</i>
	 * @author Noe GRAVRAND
	 */
	public static void rouleTemps (int milisec, Capteurs capteurs, Memoire memoire, Carto carto) {
		moteur_droit.setSpeed(VITESSE_MAX);
		moteur_gauche.setSpeed(VITESSE_MAX);
		moteur_droit.forward();
		moteur_gauche.forward();
		for (int ii=0; ii<milisec; ii+=3) {
			if (capteursCaptent(capteurs, memoire, carto)) {
				stop();
				return;
			}
			Delay.msDelay(3);
		}
	}
	
	/**
	 * <b>Roule pendant une distance x centimetre, tout en surveillant les rslts des capteurs</b>
	 * @param centimetre : <i>int</i> (distance a parcourir)
	 * @param capteurs : <i>Capteurs</i>
	 * @author Noe GRAVRAND
	 */
	public static void rouleDist (int centimetre, Capteurs capteurs, Memoire memoire, Carto carto) {
		double tourDeRoue = 2.8*Math.PI;//cm
		double tourDeRoueParMiliSec = 0.234;//23,4 tour toute les 10s
		double distParMiliSec = tourDeRoueParMiliSec * tourDeRoue;
		int milisec = (int) Math.round(centimetre / distParMiliSec);
		rouleTemps(milisec, capteurs, memoire, carto);
	}
	
	
	
	//-------Juste lignes blanches
	
	/**
	 * <b>Permet de savoir si le robot est sur une ligne blanche ou si il y a un obstacle a moin 10 cm</b>
	 * @param capteurs : <i>Capteurs</i>
	 * @return <i>boolean</i> : <b>true</b> si sur une ligne blanche ou si il y a un obsctacle a moin de 15 cm, <b>false</b> sinon.
	 * @author Noe GRAVRAND
	 */
	private static boolean capteursCaptent_onlyBlanc (Capteurs capteurs) {
		int color = (int) capteurs.getCouleur();
		if (capteurs.getDistanceOb()<0.15 || color==Color.WHITE)
			return true;
		return false;
	}
	
	/**
	 * <b>Permet d'accelerer (0 -> VITESSE_MAX), tout en surveillant les rslts des capteurs (ne prend en compte que la couleur blanche)</b>
	 * @author Noe GRAVRAND
	 */
	public static void demare_onlyBlanc (Capteurs capteurs) {
		int nAcc = 200; //definition du nb de marches d'accélération
		for (int i=0; i<nAcc; i++) {
			moteur_droit.setSpeed(VITESSE_MAX/nAcc*i);//change la vitesse
			moteur_gauche.setSpeed(VITESSE_MAX/nAcc*i);
			moteur_droit.forward();//lance le moteur 
			moteur_gauche.forward();
			if (capteursCaptent_onlyBlanc(capteurs)) {stop(); return;}
			Delay.msDelay(1);// attend 1ms
			if (capteursCaptent_onlyBlanc(capteurs)) {stop(); return;}
		}
	}
	
	/**
	 * <b>Roule (VITESSE_MAX) pendant un temps x milisec, tout en surveillant les rslts des capteurs (ne prend en compte que la couleur blanche)</b>
	 * @param milisec : <i>int</i> (temps de deplacement)
	 * @param capteurs : <i>Capteurs</i>
	 * @author Noe GRAVRAND
	 */
	public static void rouleTemps_onlyBlanc (int milisec, Capteurs capteurs) {
		moteur_droit.setSpeed(VITESSE_MAX);
		moteur_gauche.setSpeed(VITESSE_MAX);
		moteur_droit.forward();
		moteur_gauche.forward();
		for (int ii=0; ii<milisec; ii+=3) {
			if (capteursCaptent_onlyBlanc(capteurs)) {
				stop();
				return;
			}
			Delay.msDelay(3);
		}
	}
	
	/**
	 * <b>Roule pendant une distance x centimetre, tout en surveillant les rslts des capteurs (ne prend en compte que la couleur blanche)</b>
	 * @param centimetre : <i>int</i> (distance a parcourir)
	 * @param capteurs : <i>Capteurs</i>
	 * @author Noe GRAVRAND
	 */
	public static void rouleDist_onlyBlanc (int centimetre, Capteurs capteurs) {
		double tourDeRoue = 2.8*Math.PI;//cm
		double tourDeRoueParMiliSec = 0.234;//23,4 tour toute les 10s
		double distParMiliSec = tourDeRoueParMiliSec * tourDeRoue;
		int milisec = (int) Math.round(centimetre / distParMiliSec);
		rouleTemps_onlyBlanc(milisec, capteurs);
	}
	
	
	
	//-------Aveugle
	
	/**
	 * <b>Roule pendant une distance x centimetre, ne prend pas en compte les capteurs</b>
	 * @param centimetre : <i>int</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	public static void rouleDist_aveugle(int centimetre) {
		double tourDeRoue = 2.8*Math.PI;//cm
		double tourDeRoueParMiliSec = 0.234;//23,4 tour toute les 10s
		double distParMiliSec = tourDeRoueParMiliSec * tourDeRoue;
		int milisec = (int) Math.round(centimetre / distParMiliSec);
		rouleTemps_aveugle(milisec);
	}
	
	/**
	 * <b>Roule (VITESSE_MAX) pendant un temps x milisec, ne prend pas en compte les capteurs</b>
	 * @param milisec : <i>int</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	public static void rouleTemps_aveugle(int milisec) {
		moteur_droit.setSpeed(VITESSE_MAX);
		moteur_gauche.setSpeed(VITESSE_MAX);
		moteur_droit.forward();
		moteur_gauche.forward();
		for (int ii=0; ii<milisec; ii+=3) {
			Delay.msDelay(3);
		}
	}

}