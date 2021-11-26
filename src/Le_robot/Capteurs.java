package Le_robot;
import lejos.hardware.sensor.*;
import lejos.robotics.Color;
import lejos.utility.Delay;
import java.util.Arrays;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.port.*;


/**
 * Contient toute les fonctions permettant d'utiliser les capteurs que posede le robot.
 * @param p1 : Port
 * @param p2 : Port
 * @param p3 : Port
 * @param capteurCo : EV3ColorSensor
 * @param capteurTa : EV3TouchSensor
 * @param capteurUS : EV3UltrasonicSensor
 * @param donneeSe : float[1] (distance mesurer par capteurUS)
 * @param donneeCo : float[1] (useless TODO)
 * @param donneeTa : float[1] (activation du capteurTa)
 * 
 * @author noegr
 * @author hugoapeloig
 * @author TheoJ5
*/
public class Capteurs {
	
	/**
	 * Contient le port necessaire pour appeler le ColorSensor.
	 */
	private Port p1;
	/**
	 * Contient le port necessaire pour appeler le TouchSensor.
	 */
	private Port p2;
	/**
	 * Contient le port necessaire pour appeler le UltrasonicSensor.
	 */
	private Port p3;
	

	
	/**
	 * Represente le capteur de couleur.
	 */
	public EV3ColorSensor capteurCo;
	
	/**
	 * Represente le capteur tactile.
	 */
	public EV3TouchSensor capteurTa;
	
	/**
	 * Represente le capteur ultrason.
	 */
	public EV3UltrasonicSensor capteurUS;
	
	
	
	//Tableaux de Float contennant les donnees des different capteur :
	
	/**
	 * Contient la dernier distance recupere par la fonction distanceOb().
	 */
	public float[] donneeSe = new float[1];
	
	private float[] donneeCo = new float[1];	//Pas utilisée pour le moment TODO
	
	/**
	 * Contient le dernier etat du capteur tactile recuperer par la fonction capteurTactileActive().
	 */
	private float[] donneeTa = new float[1];
	
	
	/**
	 * Constructeur de la class Capteurs
	 * @author hugoapeloig
	 */
	public Capteurs() {
		p1 = BrickFinder.getLocal().getPort("S1");
		capteurCo = new EV3ColorSensor(p1);
		p2 = BrickFinder.getLocal().getPort("S3");
		capteurTa = new EV3TouchSensor(p2);
		p3 = BrickFinder.getLocal().getPort("S2");
		capteurUS = new EV3UltrasonicSensor(p3);

	}
	
	
	//------------------UltraSon------------------
	

	/**
	 * Demarre le capteur d'Ultrason
	 * @author TheoJ5
	 */
	public void demarrerCapteurUltraSon() {
		capteurUS.enable();
	}
	
	/**
	 * Eteit le capteur d'Ultrason
	 * @author TheoJ5
	 */
	public void eteindreCapteurUltraSon() {
		capteurUS.disable();
	}
	
	
	/**
	 * Place dans l'instance donneeSe la distance de l'obstacle en face du robot
	 * @author TheoJ5
	 * @author noegr
	 */
	public void distanceOb() {
		capteurUS.enable();
		capteurUS.getDistanceMode().fetchSample(donneeSe, 0);
		capteurUS.disable();
		capteurUS.getDistanceMode();
	}
	
	/**
	 * Retourne la derniere distance mesurer par le robot.
	 * @return doneeSe[0] : float (la valeur de la derniere distance mesurer avec distanceOb())
	 * 
	 * @author TheoJ5
	 */
	public float getDistanceOb() {
		return donneeSe[0];
	}
	
	/**
	 * Fait un tour sur lui-meme afin de trouver l'obstacle le plus proche et se tourne dans la direction de celui ci
	 * @author noegr
	 * @author hugoapeloig
	 * @author TheoJ5
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
	 * Permet de determiner si le capteur tactile est activer ou non.
	 * @return boolean (true si le capteur est active, false sinon)
	 * @author TheoJ5
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
	
	/**
	 * Active le capteur de couleur
	 * @author hugoapeloig
	 * @author TheoJ5
	 */
	public void capteurCouleurActive() {
	capteurCo.setFloodlight(true);
	capteurCo.setFloodlight(Color.WHITE);
	}
	
	/**
	 * Permet de savoir qu'elle est la couleur detecte par le robot.
	 * @return donneCo[0] : float (ID de la couleur detecter)
	 * @author TheoJ5
	 */
	public float getCouleur() {			//Pas utile pour le moment
		return donneeCo [0];
	}
	
}