package Le_code;

import java.util.Arrays;

import lejos.hardware.Button;
import lejos.hardware.port.Port;
import lejos.hardware.sensor.EV3UltrasonicSensor;
import lejos.utility.Delay;

public class Test {

	public static void main(String[] args) {
		Port p3 = lejos.hardware.port.SensorPort.S3;
		EV3UltrasonicSensor capteurSe = new EV3UltrasonicSensor(p3);

		
		
		try {
			capteurSe.enable();
			int i = 0;
			float[] tab = new float [1000000];
			Roues test = new Roues();
			test.mA.rotateTo(1560, true);
			while (test.mA.isMoving()) {
				capteurSe.getDistanceMode().fetchSample(tab, i);
				i++;
			}
			capteurSe.disable();
			int j=0;
			while(tab[j]!=(float) 0) {
			j++;
			}
			tab = Arrays.copyOf(tab, j);
			float min = tab[0];
			int indicdumin = 0;
			int k=1; 
			while(k<tab.length) {
				if (min > tab[k]) {
					min = tab[k];
					indicdumin = k;
				}
				k++;
			}
			int angle = (indicdumin/tab.length)*1560;
			test.mA.rotateTo(angle);
			System.out.println(""+indicdumin+"   "+tab.length);
			Button.ENTER.waitForPress();
			
			} catch (Throwable t) {
				t.printStackTrace();
				Delay.msDelay(10000);
				System.exit(0);
			}
	}

}