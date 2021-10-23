package Le_robot;
import lejos.hardware.sensor.*;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

import java.util.Arrays;

import lejos.hardware.Button;
import lejos.hardware.port.*;


/**
Une classe qui contient toute les fonctions permettant d'utiliser les capteurs que posede le robot.
*/
public class Capteur {
	
	/**
    Instance qui contient le port necessaire pour appeler le ColorSensor.
	 */
	private static Port p1 = lejos.hardware.port.SensorPort.S1;
	/**
    Instance qui contient le port necessaire pour appeler le TouchSensor.
	 */
	private static Port p2 = lejos.hardware.port.SensorPort.S2;
	/**
    Instance qui contient le port necessaire pour appeler le UltrasonicSensor.
	 */
	private static Port p3 = lejos.hardware.port.SensorPort.S3;
	
	
	/**
    Instance du capteur de couleur.
	 */
	private static EV3ColorSensor capteurCo = new EV3ColorSensor(p1);
	/**
    Instance du capteur tactile.
	 */
	public static EV3TouchSensor capteurTa = new EV3TouchSensor(p2);
	/**
    Instance du capteur ultrason.
	 */
	public static EV3UltrasonicSensor capteurUS = new EV3UltrasonicSensor(p3);
	
	//Tableau de Float contennant les donnees des different capteur :
	/**
    Instance contenant la dernier distance recuperer par la fonction distanceOb().
	 */
	public static float[] donneeSe = new float[1];
	
	private static float[] donneeCo = new float[1];	//Pas utilisée pour le moment 
	
	/**
    Instance contenant le dernier etat du capteur tactile recuperer par la fonction capteurTactileActive().
	 */
	private static float[] donneeTa = new float[1];
	
	
	
	//------------------UltraSon------------------
	
	/**
    Demarre le capteur d'Ultrason.
	 */
	public static void demarrerCapteurUltraSon() {
		capteurUS.enable();
	}
	
	/**
    Eteit le capteur d'Ultrason.
	 */
	public static void eteindreCapteurUltraSon() {
		capteurUS.disable();
	}
	
	
	/**
    Place dans l'instance donneeSe la distance de l'obstacle en face du robot.
	 */
	public static void distanceOb() {
		
		capteurUS.enable();
		capteurUS.getDistanceMode().fetchSample(donneeSe, 0);
		capteurUS.disable();
	}
	
	/**
    Retourne la derniere distance mesurer par le robot.
    @return La valeur de la derniere distance mesurer avec distanceOb().
	 */
	public static float getDistanceOb() {
		return donneeSe[0];
	}
	
	/**
    Fait un tour sur lui-meme afin de trouver l'obstacle le plus proche et se tourne dans la direction de celui-ci.
	 */
	public void sonnar() {
		try {
			demarrerCapteurUltraSon();
			float[] tab = new float [1000000];
			Roues.mA.rotateTo(1560, true);
			int ii=0;
			while (Roues.mA.isMoving()) {
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
			Roues.mA.rotateTo(angle);
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
	static public boolean capteurTactileActive() {
		capteurTa.getTouchMode().fetchSample(donneeTa, 0);
		if (donneeTa[0] == 1){
			return true;
		} else {
			return false;
		}
	}
	
	
	
	//-------------------Couleur----------------------
	
	/**
    Permet de savoir qu'elle est la couleur detecte par le robot.
    @return l'ID de la couleur detecter.
	 */
	public int couleurDetectee() {
		//Donne ID de la couleur detecter par le capteur de couleur en mode RGB
		return capteurCo.getColorID();
	}
	
	public float getCouleur() {			//Pas utile pour le moment
		return donneeCo [0];
	}
	
}