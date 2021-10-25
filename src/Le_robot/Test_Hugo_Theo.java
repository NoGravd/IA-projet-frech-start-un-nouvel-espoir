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
		capteurCo.setFloodlight(true);
		capteurCo.setFloodlight(Color.WHITE);
		//float[] tab = new float[3];
		//capteurCo.getRGBMode().fetchSample(tab, 0);
		int i =capteurCo.getColorID();
		Delay.msDelay(2000);
		//boolean i = capteurCo.setFloodlight(1);
		capteurCo.setFloodlight(false);
		System.out.print(i);
		Button.ENTER.waitForPress();//question nono : il va falloir le virer ? wesh
		} catch (Throwable t) {
			t.printStackTrace();
			Delay.msDelay(10000);
			System.exit(0);
		}
	}

}

