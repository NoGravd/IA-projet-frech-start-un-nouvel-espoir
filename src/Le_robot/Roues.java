package Le_robot;

import java.util.Arrays;


import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;

/**
* Permet de deplacer le robot
* @author noegr
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
	static final BaseRegulatedMotor[] l = new BaseRegulatedMotor[] {moteur_gauche};
	/**
	 * Represente la vitesse max du robot.
	 */
	static final int VITESSE_MAX = (int) moteur_gauche.getMaxSpeed();
	
	
	
	/**
	 * Avance tant qu'il detecte un pallet.
	 * @author noegr
	 */
	public static void avanceTQpalet(Capteurs c, Pince pince) {
		Pince.ouverture_mobile();
		c.demarrerCapteurUltraSon();
		moteur_droit.forward();
		moteur_gauche.forward();
		while (c.getDistanceOb()>1) { //tant qu'il ne fonce pas dans 1 mur
			while (!c.capteurTactileActive())
				Delay.msDelay(1);
			stop();
			pince.fermeture(c);
		}
		stop();
		c.eteindreCapteurUltraSon();
	}
	
	/**
	 * Arrete tous les moteurs.
	 * @author noegr
	 */
	public static void stop() {
		moteur_droit.synchronizeWith(l);
		moteur_droit.startSynchronization();
		moteur_gauche.stop();
		moteur_droit.stop();
		moteur_droit.endSynchronization();
 	}
 	
	
	
	/**
	 * Demarre le moteur et recule petit à petit (aceleration).
	 * @author noegr
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
		if (Memoire.getEtatPince())//si les pince sont ouvertes
			Memoire.setAvoirPalet(false);//alors on a pas/plus de palet
	}
	
	
	
	/**
    Pivote de l'angle fourni en parametre.
    Pivote toujours sur la droite
    @param degre : degre de rotation voulu
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
    Fonction en chantier.
	 */
	public static void pivoteUS (int degre,Capteurs c) {
		c.demarrerCapteurUltraSon();
		float[] tab = new float [1000000];
		double degreD = degre*4.333;
		int degre2 = (int) Math.round(degreD);
		try {
			moteur_droit.rotateTo((-degre2)/2, true);
			moteur_gauche.rotateTo(degre2/2, true);
			int ii=0;
			while (moteur_gauche.isMoving()) {
				c.capteurUS.getDistanceMode().fetchSample(tab, ii);
				ii++;//juste pour que le bot calclul
			}
			c.eteindreCapteurUltraSon();
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
	
	/**
  Pivote de maniere a faire un demi tour, en partant soit a gauche, soit a droite (determiner de maniere aleatoire.
	 */
	public static void demi_tour() {
		pivote (180);
	}
	
	
	
	
	//-----Avec capteurs :
	
	/**
    Permet de savoir si le robot est sur une ligne de couleur ou si il y a un obstacle a moin 10 cm.
    @return True si sur une ligne de couleur ou si il y a un obsctacle a moin de 10 cm, false sinon.
	 */
	private static boolean capteursCaptent(Capteurs c) {
		int color = (int) c.getCouleur();
		if (Carto.couleurDuInt(color)!=404)
			Carto.travLigne(color);
		if (c.getDistanceOb()<0.1)
			return true;
		return false;
	}
	
	/**
    Permet d'accelerer de maniere moin brute.
	 */
	public static void demare(Capteurs c) {
		int nAcc = 200; //definition du nb de marches d'accélération
		for (int i=0; i<nAcc; i++) {
			moteur_droit.setSpeed(VITESSE_MAX/nAcc*i);//change la vitesse
			moteur_gauche.setSpeed(VITESSE_MAX/nAcc*i);
			moteur_droit.forward();//lance le moteur 
			moteur_gauche.forward();
			if (capteursCaptent(c)) {stop(); return;}
			Delay.msDelay(1);// attend 3ms
			if (capteursCaptent(c)) {stop(); return;}
		}
	}
	
	/**
    Roule pendant un temps x
    @param temps en mili seconde
	 */
	public static void rouleTemps (int milisec, Capteurs c) {
		moteur_droit.setSpeed(VITESSE_MAX);
		moteur_gauche.setSpeed(VITESSE_MAX);
		moteur_droit.forward();
		moteur_gauche.forward();
		for (int ii=0; ii<milisec; ii+=3) {
			if (capteursCaptent(c)) {
				stop();
				return;
			}
			Delay.msDelay(3);
		}
	}
	
	/**
    Roule pendant une distance x
    @param distance en metre.
	 */
	public static void rouleDist (int centimetre, Capteurs c) {
		double tourDeRoue = 2.8*Math.PI;//cm
		double tourDeRoueParMiliSec = 0.234;//23,4 tour toute les 10s
		double distParMiliSec = tourDeRoueParMiliSec * tourDeRoue;
		int milisec = (int) Math.round(centimetre / distParMiliSec);
		rouleTemps(milisec,c);
	}
	
	

}