package Le_code;
import lejos.hardware.sensor.*;
import lejos.robotics.SampleProvider;
import lejos.hardware.port.*;



public class Capteur {
	
	//Trouver les ports correspondant au bon capteur;
	
	private Port p1 = lejos.hardware.port.SensorPort.S1;
	private Port p2 = lejos.hardware.port.SensorPort.S2;
	private Port p3 = lejos.hardware.port.SensorPort.S3;
//	private Port p4 = lejos.hardware.port.SensorPort.S4;
	
	//Initialisation des instances des 3 capteur (Ultrason,Couleur et tactil)
	
	private EV3UltrasonicSensor capteurSe = new EV3UltrasonicSensor(p3);
	private EV3ColorSensor capteurCo = new EV3ColorSensor(p1);
	private EV3TouchSensor capteurTa = new EV3TouchSensor(p2);
	
	
	//Tableau de Float contennant les données des différent capteur
	
	public float[] donneeSe = new float[1];
	//private float[] donneeCo = new float[];
	private float[] donneeTa = new float[1];
	
	//Allume le capteur UltraSon
	
	public void demarrerLeCapteurUltraSon(){
		
		capteurSe.enable();
		
	}
	
	//Eteindre le capteur UltraSon
	
	public void eteindreLeCapteurUltraSon(){
			
		capteurSe.disable();
		
	}
	
	//Affecte la distance de l'obstacle le plus proche en metre dans le tableau
	
	public void distanceOb(){
		
		//Retourne une String pour voir qu'elle resultat cela nous donne
		// SampleProvider ?
		
		//System.out.println("=== Capteur ultrason ==");
		
		//System.out.println("getName = "+capteurSe.getName());
		//System.out.println("getDistanceMode = "+capteurSe.getDistanceMode());
		
		capteurSe.enable();
		capteurSe.getDistanceMode(); //.fetchSample(donneeSe, 0);
		capteurSe.disable();
		
	}
	
	//Donne la distance de l'obstacle le plus proche (en Metre)
	
	public float getDistanceOb(){
		
		return donneeSe[0];
		
	}
	
	//Permet de savoir si le capteur Tactile est activee (il detect qlqch)
	
	public boolean capteurTactileActif(){
		
		capteurTa.getTouchMode().fetchSample(donneeTa, 0);
		
		if(donneeTa[0] == 1){
			System.out.print("T");
			return true;
			
		}else{
			System.out.print("F");
			return false;
			
		}
//		System.out.println(capteurTa.getTouchMode().fetchSample(donneeTa, 0));
	}

	//Donner l'ID de la couleur détecter par le capteur de couleur
	
	public int couleurDetectee(){
		
		//Une fonction qui est capable de donner la couleur en mode RGB
		
		return capteurCo.getColorID();
		
	}
	
}
