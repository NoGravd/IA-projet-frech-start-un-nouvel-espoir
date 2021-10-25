package Le_robot;

import lejos.hardware.Sound;

public class Music {
	//class qui fait de la Zic'mu
	//@author NG le musicos
	
//	DO	
//	262
//	 	 
//	RE	
//	287
//	25	whole
//	MI	
//	320
//	33	whole
//	FA	
//	349
//	29	half
//	SOL	
//	392
//	43	whole
//	LA	
//	440
//	48	whole
//	SI	
//	494
//	54	whole
	
	
	public static void test() throws InterruptedException {
		Sound.playTone(300,500);// frequence 300, duree 1/2 seconde
		Thread.sleep(500);// attend 1/2 seconde
		
		Sound.playTone(400,500);// frequence 400, duree 1/2 seconde
		Thread.sleep(1000);// attend 1 seconde
		
		Sound.playTone(500,1000);// frequence 500, duree 1 seconde
		Thread.sleep(1000);// attend 1 seconde
		}
}
