package Le_robot;
import lejos.hardware.sensor.*;
import lejos.robotics.SampleProvider;
import lejos.utility.Delay;

import java.util.Arrays;

import lejos.hardware.Button;
import lejos.hardware.port.*;



public class Capteur {
	
	//Trouver les ports correspondant au bon capteur :
	private static Port p1 = lejos.hardware.port.SensorPort.S1;
	private static Port p2 = lejos.hardware.port.SensorPort.S2;
	private static Port p3 = lejos.hardware.port.SensorPort.S3;
	
	//Initialisation des instances des 3 capteurs (Ultrason,Couleur et tactile) :
	private static EV3ColorSensor capteurCo = new EV3ColorSensor(p1);
	public static EV3TouchSensor capteurTa = new EV3TouchSensor(p2);
	public static EV3UltrasonicSensor capteurUS = new EV3UltrasonicSensor(p3);
	
	//Tableau de Float contennant les donnees des different capteur :
	public static float[] donneeSe = new float[1];
	private static float[] donneeCo = new float[1];
	private static float[] donneeTa = new float[1];
	
	
	
	//------------------UltraSon------------------
	
	public static void demarrerCapteurUltraSon() {
		capteurUS.enable();
	}
	
	public static void eteindreCapteurUltraSon() {
		capteurUS.disable();
	}
	
	
	
	public static void distanceOb() {
		//Affecte la distance de l'obstacle le plus proche en metre dans le tableau
		//Retourne une String pour voir qu'elle resultat cela nous donne
		// SampleProvider ?
		
		//System.out.println("=== Capteur ultrason ==");
		
		//System.out.println("getName = "+capteurUS.getName());
		//System.out.println("getDistanceMode = "+capteurUS.getDistanceMode());
		
		capteurUS.enable();
		capteurUS.getDistanceMode(); //.fetchSample(donneeSe, 0);
		capteurUS.disable();
	}
	
	public static float getDistanceOb() {
		return donneeSe[0];
	}
	
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
	
	static public boolean capteurTactileActive() {
		//Permet de savoir si le capteur Tactile est activee (il detect qlqch)
		capteurTa.getTouchMode().fetchSample(donneeTa, 0);
		if (donneeTa[0] == 1){
			System.out.print("T");
			return true;
		} else {
			System.out.print("F");
			return false;
		}
//		System.out.println(capteurTa.getTouchMode().fetchSample(donneeTa, 0));
	}
	
	
	
	//-------------------Couleur----------------------
	
	public int couleurDetectee() {
		//Donne ID de la couleur detecter par le capteur de couleur en mode RGB
		return capteurCo.getColorID();
	}
	
	public float getCouleur() {
		return donneeCo [0];
	}
	
}