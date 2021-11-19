package Le_robot;

import lejos.hardware.sensor.*;
import lejos.robotics.chassis.Chassis;
import lejos.robotics.chassis.Wheel;
import lejos.robotics.chassis.WheeledChassis;
import lejos.robotics.navigation.MovePilot;
import lejos.utility.Delay;

import java.util.ArrayList;
import java.util.Arrays;
import lejos.hardware.Button;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.*;

public class Brouillon {


	//------------------------------------------------------------------------------------------------------------Chassie + Roues

	Wheel wheel1 = WheeledChassis.modelWheel(Motor.A, 81.6).offset(-70);
	Wheel wheel2 = WheeledChassis.modelWheel(Motor.C, 81.6).offset(70);
	Chassis chassis = new WheeledChassis(new Wheel[] { wheel1, wheel2 }, WheeledChassis.TYPE_DIFFERENTIAL);
	MovePilot pilot = new MovePilot(chassis);

	//------------------------------------------------------------------------------------------------------------Pince

	BaseRegulatedMotor moteurPince = Motor.B;

	//------------------------------------------------------------------------------------------------------------Capteur

	private static Port p1 = lejos.hardware.port.SensorPort.S1;
	private static Port p2 = lejos.hardware.port.SensorPort.S2;
	private static Port p3 = lejos.hardware.port.SensorPort.S3;

	private static EV3ColorSensor capteurCo = new EV3ColorSensor(p1);
	public static EV3TouchSensor capteurTa = new EV3TouchSensor(p2);
	public static EV3UltrasonicSensor capteurUS = new EV3UltrasonicSensor(p3);

	//------------------------------------------------------------------------------------------------------------Test de pilote

	public void avanceDe(double cm){

		pilot.travel(cm);

	}

	//------------------------------------------------------------------------------------------------------------CapteurUltraSon


	public static void demarrerCapteurUltraSon() {
		capteurUS.enable();
	}

	public static void eteindreCapteurUltraSon() {
		capteurUS.disable();
	}


	public double sonnarV2(int angleDeScan) {
		//Retourne la distance de l'obstacle


		demarrerCapteurUltraSon();

		ArrayList<Float> tabDistance = new ArrayList<Float>();
		float[] distanceActuel = new float[1];

		pilot.rotate(angleDeScan,true);

		//Recuperation des distance
		int i=0;
		while (pilot.isMoving()) {
			capteurUS.getDistanceMode().fetchSample(distanceActuel, 0);
			tabDistance.add(distanceActuel[0]);
			i++;
		}
		eteindreCapteurUltraSon();

		//Enlever les donnees inferieur a 0.3 (pas les palets)

		for (int j=1; j<tabDistance.size(); j++) {

			if (0.3 > tabDistance.get(j)) {
				tabDistance.remove(j);
			}
		}


		float min = tabDistance.get(0);
		int indicdumin = 0;

		//Recuperation de l'indice du membre le plus petit
		for (int j=1; j<tabDistance.size(); j++) {

			if (min > tabDistance.get(j)) {

				min = tabDistance.get(j);
				indicdumin = j;

			}

		}

		//Calcul de l'angle de rotation
		double angle = ((double)indicdumin/(double)tabDistance.size())*angleDeScan;
		pilot.rotate(angle);

		//Affichage pour voir ce qu il fait
		System.out.println("Angle de scan = "+angleDeScan);
		System.out.println("Indice min = "+indicdumin+"  Taille du tableau =  "+tabDistance.size());
		System.out.println("Angle de rotation = "+angle+"Distance obstacle = "+tabDistance.get(indicdumin));
		Button.ENTER.waitForPress();		// A enlever si on test avec des fonctions après

		return tabDistance.get(indicdumin);
	}


	public void prendreLePalet(double distance){

		moteurPince.rotate(20);
		pilot.travel(distance);
		moteurPince.rotate(-20);

	}


}
