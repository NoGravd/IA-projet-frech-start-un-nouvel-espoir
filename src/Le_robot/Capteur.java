package Le_robot;
import lejos.hardware.sensor.*;
import lejos.robotics.Color;
import lejos.utility.Delay;
import java.util.Arrays;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.port.*;


/**
Une classe qui contient toute les fonctions permettant d'utiliser les capteurs que posede le robot.
*/
public class Capteur {
	
	/**
    Instance qui contient le port necessaire pour appeler le ColorSensor.
	 */
	private Port p1;
	/**
    Instance qui contient le port necessaire pour appeler le TouchSensor.
	 */
	private Port p2 = lejos.hardware.port.SensorPort.S2;
	/**
    Instance qui contient le port necessaire pour appeler le UltrasonicSensor.
	 */
	private Port p3 = lejos.hardware.port.SensorPort.S3;
	

	
	/**
    Instance du capteur de couleur.
	 */
	public EV3ColorSensor capteurCo;
	/**
    Instance du capteur tactile.
	 */

	//Initialisation des instances des 3 capteurs (Ultrason,Couleur et tactile) :
//	public EV3ColorSensor capteurCo = new EV3ColorSensor(p1);

	public EV3TouchSensor capteurTa;
	/**
    Instance du capteur ultrason.
	 */
	public EV3UltrasonicSensor capteurUS;
	
	//Tableau de Float contennant les donnees des different capteur :
	/**
    Instance contenant la dernier distance recuperer par la fonction distanceOb().
	 */
	public float[] donneeSe = new float[1];

	
	private float[] donneeCo = new float[1];	//Pas utilisée pour le moment 
	
	/**
    Instance contenant le dernier etat du capteur tactile recuperer par la fonction capteurTactileActive().
	 */

//	private float[] donneeCo = new float[1];

	private float[] donneeTa = new float[1];
	
	public Capteur() {
		p1 = BrickFinder.getLocal().getPort("S1");
		capteurCo = new EV3ColorSensor(p1);
		p2 = BrickFinder.getLocal().getPort("S3");
		capteurTa = new EV3TouchSensor(p2);
		p3 = BrickFinder.getLocal().getPort("S2");
		capteurUS = new EV3UltrasonicSensor(p3);

	}
	
	
	//------------------UltraSon------------------
	

	/**
    Demarre le capteur d'Ultrason.
	 */
	public void demarrerCapteurUltraSon() {
		capteurUS.enable();
	}
	
	/**
    Eteit le capteur d'Ultrason.
	 */
	public void eteindreCapteurUltraSon() {
		capteurUS.disable();
	}
	
	
	/**
    Place dans l'instance donneeSe la distance de l'obstacle en face du robot.
	 */
	public void distanceOb() {
		

		capteurUS.enable();
		capteurUS.getDistanceMode().fetchSample(donneeSe, 0);
		capteurUS.disable();

//		capteurUS.enable();
		capteurUS.getDistanceMode(); //.fetchSample(donneeSe, 0);
//		capteurUS.disable();

	}
	
	/**
    Retourne la derniere distance mesurer par le robot.
    @return La valeur de la derniere distance mesurer avec distanceOb().
	 */
	public float getDistanceOb() {
		return donneeSe[0];
	}
	
	/**
    Fait un tour sur lui-meme afin de trouver l'obstacle le plus proche et se tourne dans la direction de celui-ci.
	 */
	public void sonnar() {
		try {
			demarrerCapteurUltraSon();
			float[] tab = new float [1000000];
			Roues.moteur_droit.rotateTo(1560, true);
			int ii=0;
			while (Roues.moteur_droit.isMoving()) {
				capteurUS.getDistanceMode().fetchSample(tab, ii);
				ii++;
			}
			eteindreCapteurUltraSon();
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
			System.out.println(""+indicdumin+"   "+tab.length);
			Button.ENTER.waitForPress();//question nono : il va falloir le virer ? wesh
		} catch (Throwable t) {
			t.printStackTrace();
			Delay.msDelay(10000);
			System.exit(0);
		}
	}
	
	
	
	//-------------------Tactile---------------------
	
	/**
    Permet de determiner si le capteur tactile est activer ou non.
    @return true si le capteur est active, false sinon.
	 */
	public boolean capteurTactileActive() {
		capteurTa.getTouchMode().fetchSample(donneeTa, 0);
		if (donneeTa[0] == 1){
			return true;
		} else {
			return false;
		}
	}
	
	
	
	//-------------------Couleur----------------------
	
	public void capteurCouleurActive() {
	capteurCo.setFloodlight(true);
	capteurCo.setFloodlight(Color.WHITE);
	}
	/**
    Place l'ID de la couleur detecter dans le tableau donneeCo.
	 */
//	public void couleurDetectee() {
//		
//		capteurCo.getColorID().fetchSample(donneeCo, 0);
//		
//	}
	
	/**
    Permet de savoir qu'elle est la couleur detecte par le robot.
    @return l'ID de la couleur detecter.
	 */
	public float getCouleur() {			//Pas utile pour le moment
		return donneeCo [0];
	}

//	public int couleurDetectee() {
//		//Donne ID de la couleur detecter par le capteur de couleur en mode RGB
////		capteurCo.getColorIDMode();
//		capteurCo.setFloodlight(false);
//		return capteurCo.getColorID();
//	}
//	
//	public float getCouleur() {
//		return donneeCo [0];
//	}
	
}