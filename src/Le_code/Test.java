package Le_code;

import java.util.Arrays;

import lejos.hardware.Button;
//import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.utility.Delay;

public class Test {

	public static void main(String[] args) {
		EV3UltrasonicSensor capteurUS = Capteur.capteurUS;
		
		
		
		try {
			capteurUS.enable();
			float[] tab = new float [1000000];
			Roues.mA.rotateTo(1560, true);
			int ii = 0;
			while (Roues.mA.isMoving()) {
				capteurUS.getDistanceMode().fetchSample(tab, ii);
				ii++;
			}
			capteurUS.disable();
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
			Button.ENTER.waitForPress();
			
			} catch (Throwable t) {
				t.printStackTrace();
				Delay.msDelay(10000);
				System.exit(0);
			}
	}

}