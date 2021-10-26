package Le_robot;

//import java.util.Arrays;
//
//import lejos.hardware.Button;
//import lejos.hardware.Sound;
//import lejos.hardware.sensor.EV3UltrasonicSensor;
//import lejos.utility.Delay;

public class Test_Nono {

	public static void main(String[] args) throws InterruptedException {
		Music.Korobeiniki();
		for (int ii=0; ii<4; ii++)
			Roues.demi_tour();
		Music.Marseillaise();
		Music.Kalinka();
	}

}