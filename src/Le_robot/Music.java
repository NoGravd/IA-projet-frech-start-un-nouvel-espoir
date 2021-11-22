package Le_robot;

import lejos.hardware.Sound;

public class Music {
	//class qui fait de la Zic'mu
	//@author ${NG_le_musicos}
	//ne me jugez pas j'ai beaucoup trop de temps libre
	
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
	/**
    Instance qui represente la frequence de la note DO.
	 */
	static private final int DO = 262;
	/**
    Instance qui represente la frequence de la note RE.
	 */
	static private final int RE = 287;
	/**
    Instance qui represente la frequence de la note MI.
	 */
	static private final int MI = 320;
	/**
    Instance qui represente la frequence de la note FA.
	 */
	static private final int FA = 349;
	/**
    Instance qui represente la frequence de la note SOL.
	 */
	static private final int SOL = 392;
	/**
    Instance qui represente la frequence de la note LA.
	 */
	static private final int LA = 440;
	/**
    Instance qui represente la frequence de la note SI.
	 */
	static private final int SI = 494;
	
	//durees des notes (basees sur 1 sec) :
	/**
    Instance qui represente la durée d'une note noir.
	 */
	static private int T = 1000;//noire
	/**
    Instance qui represente la durée d'une note ronde.
	 */
	static private int T1 = T*4;//ronde
	/**
    Instance qui represente la durée d'une note blanche.
	 */
	static private int T2 = T*2;//blanche
	/**
    Instance qui represente la durée d'une note noir.
	 */
	static private int T3 = T;//noire
	/**
    Instance qui represente la durée d'un croche.
	 */
	static private int T4 = T/2;//croche
	/**
    Instance qui represente la durée d'un double croche.
	 */
	static private int T5 = T/4;//double-croche
	
	@SuppressWarnings("unused")
	/**
    Instance qui represente le tempo n1.
	 */
	static private int T1P = 6*T;
	/**
    Instance qui represente le tempo n2.
	 */
	static private int T2P = 3*T;
	/**
    Instance qui represente le tempo n3.
	 */
	static private int T3P = T3+(T/2);
	/**
    Instance qui represente le tempo n4.
	 */
	static private int T4P = T4+(T/4);
	/**
    Instance qui represente le tempo n5.
	 */
	@SuppressWarnings("unused")
	static private int T5P = T5+(T/8);
	
	
	//-----outils-------
	
	/**
    Methode qui calcul le tempo.
    @param temps en mili secondes.
	 */
	private static void tempo (int milisec) {
		 int T =(int) milisec;//noire
		
		 T1 = T*4;//ronde
		 T2 = T*2;//blanche
		 T3 = T;//noire
		 T4 = T/2;//croche
		 T5 = T/4;//double-croche
		
		 //points :
		 T1P = 6*T;
		 T2P = 3*T;
		 T3P = T3+(T/2);
		 T4P = T4+(T/4);
		 T5P = T5+(T/8);
	}
	
	/**
    Methode qui joue une note.
    @param La note a jouer.
    @param le temps de la note.
	 */
	private static void note (int note, int sec) throws InterruptedException {
		Sound.playTone(note,sec);//frequence note, duree sec
		silence(sec);//attend sec (pour que la note se joue entierement)
	}
	
	/**
    Methode qui permet de ne pas faire de sons pendant un certain temps.
    @param temps en seconde.
	 */
	private static void silence (int sec) throws InterruptedException {
		Thread.sleep(sec); // ne fait rien
	}
	
	/**
    Methode qui convertie des BPM en seconde.
    @param temps en BPM.
	 */
	private static int milisecFromBPM (int bpm) {
		return (int) (1000/Math.round(bpm/60));
	}
	
	
	//------music--------
	
	/**
    Methode qui permet de tester la fonctionalite de la musique.
	 */
	public static void test() throws InterruptedException {
		Sound.playTone(300,500);// frequence 300, duree 1/2 seconde
		Thread.sleep(500);// attend 1/2 seconde
		
		Sound.playTone(400,500);// frequence 400, duree 1/2 seconde
		Thread.sleep(1000);// attend 1 seconde
		
		Sound.playTone(500,1000);// frequence 500, duree 1 seconde
		Thread.sleep(1000);// attend 1 seconde
		}
	
	/**
    Methode qui permet de jouer hot cross buns.
	 */
	public static void Hot_Cross_Buns() throws InterruptedException {
		tempo (1000);
		
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
	
	/**
    Methode qui permet de jouer kalinka.
	 */
	public static void Kalinka() throws InterruptedException {
		for (int ii=0; ii<4; ii++) {//joue 4x
			tempo (1000/(ii+1));//accelere a chaque fois
			
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
	
	/**
    Methode qui permet de jouer Terminator main theme.
	 */
	public static void Terminator_main_theme() throws InterruptedException {
		tempo (300);
		for (int ii=0; ii<4; ii++) {//x4
			note (DO, T5);
			note (DO, T4);
			note (DO, T4);
			note (DO, T5);
			note (DO, T3);
			silence (T3);
		}
		
		note (SOL, T4);
		note (LA, T4);
		note (SI, T4+T2P);
		
		note (LA, T3);
		note (FA, T4);
		note (DO, T2P);
		note (RE, T3P);
		
		silence (T4);
		
		note (RE, T4);
		note (FA, T4);
		note (MI, T4+T2P);
		
		note (MI, T3);
		note (DO, T4);
		note (LA, T2P);
		note (SOL, T2P);
		
		silence (T4);
		
		note (SOL, T4);
		note (LA, T4);
		note (SI, T4+T2P);
		
		note (LA, T3);
		note (FA, T4);
		note (DO, T2P+T3P);
		
		silence (T5);
		
		note (DO, T2);
	}
	
	/**
    Methode qui permet de jouer gonna fly now.
	 */
	public static void Gonna_Fly_Now() throws InterruptedException {
		tempo (500);
		note (DO, T4);
		for (int ii=0; ii<6 ; ii++) {
			if (ii==2) {
				note (SOL, T4);
				note (DO, T5);
				note (DO, T5);
				
				note (DO, T4);
				note (FA, T4);
			} else if (ii==5) {
				note (FA, T4);
				note (DO, T5);
				note (DO, T5);
				note (DO, T3);
			} else {
					note (DO, T4);
					note (DO, T5);
					note (DO, T5);
			}
		}
		for (int yy=0; yy<4; yy++) {
			if (yy==2) {
				note (RE, T5);
				note (RE, T5);
				note (RE, T4);
			} else {
				note (RE, T4);
				note (RE, T5);
				note (RE, T5);
			}
		}
		note (RE, T5);
		note (RE, T5);
		note (RE, T3);
		
		note (MI, T5);
		note (SOL, T4P);
		note (FA, T2P);
		
		note (LA, T5);
		note (SI, T4P);
		note (RE, T2P);
		
		note (MI, T5);
		note (SOL, T4P);
		note (FA, T2P);
		
		note (LA, T5);
		note (SI, T4P);
		note (RE, T2P);
		
		silence (T3);
		
		note (RE, T5);
		note (DO, T5);
		note (RE, T4P);
		note (DO, T5);
		note (RE, T5);
		note (MI, T4P);
		note (MI, T3);
		
		
		note (SI, T4);//ajout
		note (SI, T3);//ajout
	}
	
	/**
    Methode qui permet de jouer Korobeiniki.
	 */
	public static void Korobeiniki() throws InterruptedException {
		tempo (milisecFromBPM(180));
		
		note (FA, T3);
		
		note (DO, T4);
		note (RE, T4);
		note (MI, T3);
		
		note (FA, T4);
		note (RE, T4);
		note (DO-20, T3);
		
		note (DO-20,T4);
		note (RE, T4);
		note (FA, T3);
		
		note (MI, T4);
		note (RE, T4);
		note (DO, T3);
		
		note (DO, T4);
		note (RE, T4);
		note (MI, T3);
		
		note (FA, T3);
		
		note (RE, T3);
		
		note (DO-20, T3);
		
		note (DO-20, T3);
		
		silence (T2);
		
		note (MI, T3);
		
		note (SOL, T4);
		note (SI, T4);
		note (SI, T4);
		
		silence (T4);
		
		note (LA, T4);
		note (SOL, T4);
		note (FA, T2);
		
		note (RE, T4);
		note (FA, T4);
		note (FA, T3);
		
		note (MI, T4);
		note (RE, T4);
		note (DO, T4);
		note (DO, T3);
		
		note (RE, T4);
		note(MI,T4);
		note(MI,T4);
		note (FA, T3);
		
		note (RE, T3);
		
		note (DO-20, T3);
		
		note (DO-20, T3);
	}
	
	/**
    Methode qui permet de jouer les gammes.
	 */
	public static void Gamme() throws InterruptedException {
		note (DO, T3);
		note (RE, T3);
		note (MI, T3);
		note (FA, T3);
		note (SOL, T3);
		note (LA, T3);
		note (SI, T3);
	}
	
	/**
    Methode qui permet de jouer la marseillaise.
	 */
	public static void Marseillaise() throws InterruptedException {
		tempo (milisecFromBPM(115));
		
		note (DO, T4P);
		note (DO, T5);
		
		note (FA, T3);
		note (FA, T3);
		
		note (SOL, T3);
		note (SOL, T3);
		
		note (SI+60, T3P);
		
		note (LA, T4);
		
		note (FA, T4P);
		note (FA, T5);
		
		note (LA, T4P);
		note (FA, T5);
		
		note (RE, T3);
		note (SI, T3*2);
		
		note (SOL, T4P);
		note (MI, T5);
		
		note (FA, T2);
		
		silence (T3);
		
		
		
		note (FA, T4P);
		note (SOL, T5);
		
		for (int ii=0; ii<3; ii++)
			note (LA, T3);
		
		note (SI, T4P);
		note (LA, T5);
		
		note (LA, T3);
		note (SOL, T3);
		
		silence (T3);
		
		
		note (SOL, T4P);
		note (LA, T5);
		
		for (int yy=0; yy<3; yy++)
			note (SI, T3);
		
		note (SI+60, T4P);
		note (SI, T5);
		
		note (LA, T2);
		
		silence (T3);
		
		
		
		note (SI+60, T4P);
		note (SI+60, T5);
		
		note (SI+60, T3);
		
		note (LA, T4P);
		note (FA, T5);
		
		note (SI, T3);
		
		note (SOL, T4P);
		note (LA, T5);
		
		note (DO, T2);
		
		silence (T4P);
		
		
		
		note (DO, T4P);
		
		note (DO, T4P);
		note (RE, T5);
		
		note (SOL, T2);
		
		note (SI, T3);
		
		note (SOL, T4P);
		note (RE, T5);
		
		note (FA, T2);
		
		note (MI, T2);
		
		note (RE, T3);
		
		note (FA, T4P);
		note (FA, T5);
		
		note (FA, T3);
		
		note (MI, T4P);
		note (FA, T5);
		
		note (SOL, T2+T3);
		
		silence (T4);
		
		
		
		note (SOL, T4);
		
		note (LA, T3P);
		
		note (LA, T4);
		
		note (LA, T4);
		note (LA, T4);
		note (SI, T4);
		note (SI+60, T4);
		
		note (SOL, T2+T3);
		
		note (LA, T4);
		note (SOL, T4);
		
		note (FA, T3P);
		
		note (FA, T4);
		
		note (FA, T4);
		note (LA, T4);
		note (SOL, T4);
		note (FA, T4);
		
		note (FA, T3);
		note (MI, T3);
		
		silence (T3+T4P);
		
		
		
		note (SI+60, T5);
		note (SI+60, T2);
		
		silence (T4);
		
		
		
		note (SI, T4);
		
		note (LA, T4P);
		note (FA, T5);
		
		note (SOL, T2);
		
		silence (T3+T4P);
		
		
		
		note (SI+60, T4);
		note (SI+60, T2);
		
		silence (T4);
		
		
		
		note (SI, T4);
		
		note (LA, T4P);
		note (FA, T5);
		
		note (SOL, T2);
		
		silence (T3);
		
		
		
		note (DO, T3);
		
		note (FA, T2);
		
		silence (T3);
		
		
		
		note (SOL, T3);
		
		note (LA, T1);
		
		note (SI, T2);
		
		note (SI+60, T3);
		
		note (SI+130, T3);
		
		note (SOL, T2);
		
		silence (T3);
		
		
		
		note (SI+130, T3);
		
		note (SI+60, T2);
		
		note (SI+60, T4P);
		note (LA, T5);
		
		note (SI, T4P);
		note (SOL, T5);
		
		note (FA, T1);
	}
	
	/**
    Methode qui permet de jouer The imperial march.
	 */
	public static void The_imperial_march() throws InterruptedException {
		tempo(milisecFromBPM(180));
		
		for (int ii=0; ii<3; ii++)
			note (RE, T3);
		
		note (DO-20, T4P);
		note (FA, T5);
		
		note (RE, T3);
		
		note (DO-22, T4P);
		note (FA, T5);
		
		note (RE, T3);
		
		for (int yy=0; yy<3; yy++)
			note (LA, T3);
		
		note (SI, T4P);
		note (FA, T5);
		
		note (DO, T3);
		
		note (DO-40, T4P);
		note (FA, T5);
		
		note (RE, T3);
		
		note (SI+125, T3);
		
		note (RE, T4P);
		note (RE, T5);
		
		note (SI+125, T3);
		
		note (SI+60, T4P);
		note (SI+60, T5);
		
		note (SI, T3);
		
		note (RE, T3);
		
		note (SOL, T3);
		
		note (SOL, T4P);
		note (FA, T5);
		
		note (FA, T3);
		
		note (DO-22, T3);
		
		note (DO, T3);
		
		note (DO-20, T4P);
		note (DO, T5);
		
		note (FA, T3);
		
		note (RE, T4P);
		note (FA, T5);
		
		note (LA, T2);
	}

	public static void Mcdo() throws InterruptedException {
		tempo(milisecFromBPM(180));
		
		note (FA,T4);
		note (SOL,T4P);
		note (LA,T4P);
		
		note (DO+20, T3);
		
		note (SI, T3);
	}
	
}