package Le_code;
import lejos.hardware.sensor.*;
import lejos.robotics.SampleProvider;
import lejos.hardware.port.*;



public class Capteur {
	
	//Trouver les ports correspondant au bon capteur;
	
	//private Port p1 = lejos.hardware.port.SensorPort.S1;
	private static Port p2 = lejos.hardware.port.SensorPort.S2;
	private static Port p3 = lejos.hardware.port.SensorPort.S3;
//	private Port p4 = lejos.hardware.port.SensorPort.S4;
	
	//Initialisation des instances des 3 capteur (Ultrason,Couleur et tactil)
	
	public static EV3UltrasonicSensor capteurUS = new EV3UltrasonicSensor(p3);
	//private EV3ColorUSnsor capteurCo = new EV3ColorUSnsor(p1);
	public static EV3TouchSensor capteurTa = new EV3TouchSensor(p2);
	
	
	//Tableau de Float contennant les données des different capteur
	
	public float[] donneeSe = new float[1];
	//private float[] donneeCo = new float[];
	private float[] donneeTa = new float[1];
	
	
	
	public void demarrerLeCapteurUltraSon() {
		
		capteurUS.enable();
		
	}
	
	public void eteindreLeCapteurUltraSon() {
			
		capteurUS.disable();
		
	}
	
	
	
	//Affecte la distance de l'obstacle le plus proche en metre dans le tableau
	public void distanceOb() {
		
		//Retourne une String pour voir qu'elle resultat cela nous donne
		// SampleProvider ?
		
		//System.out.println("=== Capteur ultrason ==");
		
		//System.out.println("getName = "+capteurUS.getName());
		//System.out.println("getDistanceMode = "+capteurUS.getDistanceMode());
		
		capteurUS.enable();
		capteurUS.getDistanceMode(); //.fetchSample(donneeSe, 0);
		capteurUS.disable();
		
	}
	
	//Permet de savoir si le capteur Tactile est activee (il detect qlqch)
	public boolean capteurTactileActive() {
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

	//Donne l'ID de la couleur détecter par le capteur de couleur
	/*
	public int couleurDetectee(){
		//Une fonction qui est capable de donner la couleur en mode RGB
		return capteurCo.getColorID();
	}
	*/
	
	
	
	public float getDistanceOb() {
		return donneeSe[0];
	}
	
}
