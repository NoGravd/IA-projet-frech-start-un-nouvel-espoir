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
	
	
	
	//frequences des notes :
	static final int DO = 262;
	static final int RE = 287;
	static final int MI = 320;
	static final int FA = 349;
	static final int SOL = 392;
	static final int LA = 440;
	static final int SI = 494;
	
	//durees des notes (basees sur 1 sec) :
	static int T1 = 1000;
	static int T2 = T1/2;
	static int T3 = T2/2;
	static int T4 = T3/2;
	static int T5 = T4/2;
	
	static int T1P = T1+(T1/4);
	static int T2P = T2+(T2/4);
	static int T3P = T3+(T3/4);
	static int T4P = T4+(T4/4);
	static int T5P = T5+(T5/4);
	
	
	//-----outils-------
	
	private static void temps (int sec) {
		T1 = 1000*sec;
		T2 = T1/2;
		T3 = T2/2;
		T4 = T3/2;
		
		T1P = T1+(T1/4);
		T2P = T2+(T2/4);
		T3P = T3+(T3/4);
		T4P = T4+(T4/4);
		T5P = T5+(T5/4);
	}
	
	private static void note (int note, int sec) throws InterruptedException {
		Sound.playTone(note,sec);//frequence note, duree sec
		Thread.sleep(sec);//attend sec (pour que la note se joue entierement)
	}
	
	private static void silence (int sec) throws InterruptedException {
		Thread.sleep(sec);
	}
	
	
	
	//------music--------
	
	public static void test() throws InterruptedException {
		Sound.playTone(300,500);// frequence 300, duree 1/2 seconde
		Thread.sleep(500);// attend 1/2 seconde
		
		Sound.playTone(400,500);// frequence 400, duree 1/2 seconde
		Thread.sleep(1000);// attend 1 seconde
		
		Sound.playTone(500,1000);// frequence 500, duree 1 seconde
		Thread.sleep(1000);// attend 1 seconde
		}
	
	public static void Hot_Cross_Buns() throws InterruptedException {
		temps(1);
		
		note (SI, T3);
		note (LA, T3);
		note (SOL, T2);
		
		note (SI, T3);
		note (LA, T3);
		note (SOL, T2);
		
		for (int yy=0; yy<4; yy++)
			note (SOL, T4);
		
		for (int yy=0; yy<4; yy++)
			note (LA, T4);
		
		note (SI, T3);
		note (LA, T3);
		note (SOL, T2);
	}
	
	public static void Kalinka() throws InterruptedException {
		for (int ii=0; ii<4; ii++) {//joue 4x
			temps(1/(ii+1));//accelere a chaque fois
			
			note (FA, T3);
			note (MI, T3);
			
			for (int yy=0; yy<2; yy++) {//x2
				note (DO, T4);
				note (RE, T4);
				note (MI, T3);
			}
			
			note (FA, T4);
			note (MI, T4);
			note (RE, T3);
			
			note (FA, T4);
			note (FA, T4);
			note (MI, T4P);
			
			note (MI, T5);
			note (DO, T4);
			note (RE, T4);
			note (MI, T3);
			
			note (DO, T4);
			note (RE, T4);
			note (MI, T3);
			
			note (RE, T4);
			note (DO, T4);
			note (RE, T3P);
			
			silence (T2);
		}
	}
	
}