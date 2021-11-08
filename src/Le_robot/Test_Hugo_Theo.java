package Le_robot;

import lejos.hardware.sensor.*;
import lejos.robotics.Color;
import lejos.utility.Delay;
import java.util.Arrays;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.port.*;
import lejos.hardware.port.Port;

public class Test_Hugo_Theo {
	public static void main(String[] args) {
		try {
		Port p1 = BrickFinder.getLocal().getPort("S1");
		EV3ColorSensor capteurCo = new EV3ColorSensor(p1);
		Port p3 = BrickFinder.getLocal().getPort("S2");
		EV3UltrasonicSensor capteurUS = new EV3UltrasonicSensor(p3);
		capteurUS.enable();
		capteurCo.setFloodlight(true);
		capteurCo.setFloodlight(Color.WHITE);
		//Roues.moteur_droit.forward();
		//Roues.moteur_gauche.forward();
		//float[] tab = new float[3];
		//capteurCo.getRGBMode().fetchSample(tab, 0);
		int i =capteurCo.getColorID();
		float [] tab = new float[1];
		capteurUS.getDistanceMode().fetchSample(tab, 0);
		System.out.println(tab[0]);
		int j=1;
		while(j>0) {
			capteurUS.getDistanceMode().fetchSample(tab, 0);
			System.out.println(tab[0]);
			Delay.msDelay(1000);
		}
		/*while(i!=6) {
			
			//if(i==3) break; //JAUNE
			//if(i==0) break; //ROUGE
			if(tab[0]<0.15) {
				Roues.stop();
				Roues.moteur_droit.rotate(390);
				Roues.moteur_droit.stop();
				Delay.msDelay(1000);
				Roues.moteur_droit.forward();
				Roues.moteur_gauche.forward();
			}
			capteurUS.getDistanceMode().fetchSample(tab, 0);
			i =capteurCo.getColorID();
			Delay.msDelay(40);
		}*/
		//Roues.stop();
		Delay.msDelay(2000);
		//boolean i = capteurCo.setFloodlight(1);
		capteurUS.disable();
		capteurCo.setFloodlight(false);
		Button.ENTER.waitForPress();//question nono : il va falloir le virer ? wesh
	} catch (Throwable t) {
		t.printStackTrace();
		Delay.msDelay(10000);
		System.exit(0);
	}
	}

}

