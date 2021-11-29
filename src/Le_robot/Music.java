package Le_robot;

import lejos.hardware.Sound;

/**
 * <b>Contient des methodes static qui font faire de la musique au robot<b>s
 * @author Noe GRAVRAND
 */
public class Music {
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
	 * Represente la frequence de la note DO.
	 */
	static private final int DO = 262;
	/**
	 * Represente la frequence de la note RE.
	 */
	static private final int RE = 287;
	/**
	 * Represente la frequence de la note MI.
	 */
	static private final int MI = 320;
	/**
	 * Represente la frequence de la note FA.
	 */
	static private final int FA = 349;
	/**
	 * Represente la frequence de la note SOL.
	 */
	static private final int SOL = 392;
	/**
	 * Represente la frequence de la note LA.
	 */
	static private final int LA = 440;
	/**
	 * Represente la frequence de la note SI.
	 */
	static private final int SI = 494;
	
	//durees des notes (basees sur 1 sec) :
	/**
	 * Represente la durée d'une note noir.
	 */
	static private int T = 1000;//noire
	/**
	 * Represente la durée d'une note ronde.
	 */
	static private int T1 = T*4;//ronde
	/**
	 * Represente la durée d'une note blanche.
	 */
	static private int T2 = T*2;//blanche
	/**
	 * Represente la durée d'une note noir.
	 */
	static private int T3 = T;//noire
	/**
	 * Represente la durée d'un croche.
	 */
	static private int T4 = T/2;//croche
	/**
	 * Represente la durée d'un double croche.
	 */
	static private int T5 = T/4;//double-croche
	
	@SuppressWarnings("unused")
	/**
    Represente le tempo n1.
	 */
	static private int T1P = 6*T;
	/**
	 * Represente le tempo n2.
	 */
	static private int T2P = 3*T;
	/**
	 * Represente le tempo n3.
	 */
	static private int T3P = T3+(T/2);
	/**
	 * Represente le tempo n4.
	 */
	static private int T4P = T4+(T/4);
	/**
	 * Represente le tempo n5.
	 */
	@SuppressWarnings("unused")
	static private int T5P = T5+(T/8);
	
	
	//-----outils-------
	
	/**
	 * <b>Calcul le tempo</b>
	 * @param milisec : <i>int</i>
	 * 
	 * @author Noe GRAVRAND
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
	 * <b>Joue une note</b>
	 * @param note : <i>int</i>
	 * @param sec : <i>int</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	private static void note (int note, int sec) throws InterruptedException {
		Sound.playTone(note,sec);//frequence note, duree sec
		silence(sec);//attend sec (pour que la note se joue entierement)
	}
	
	/**
	* <b>Permet de ne pas faire de sons pendant un certain temps</b>
	 * @param sec : <i>int</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	private static void silence (int sec) throws InterruptedException {
		Thread.sleep(sec); // ne fait rien
	}
	
	/**
	 * <b>Convertie des BPM en secondes</b>
	 * @param bpm : <i>int</i> (nombre de batement par minute)
	 * 
	 * @author Noe GRAVRAND
	 */
	private static int milisecFromBPM (int bpm) {
		return (int) (1000/Math.round(bpm/60));
	}
	
	
	//------music--------
	
	/**
	 * <b>Permet de tester la fonctionalite de la musique</b>
	 * 
	 * @author Noe GRAVRAND
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
	 * <b>Permet de jouer Hot cross buns</b>
	 * @author Noe GRAVRAND
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
	 * <b>Permet de jouer la Kalinka</b>
	 * @author Noe GRAVRAND
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
	 * <b>Permet de jouer Terminator main theme.</b>
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
	 * <b>Permet de jouer gonna fly now</b>
	 * @author Noe GRAVRAND
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
	 * Permet de jouer Korobeiniki </b>
	 * @author Noe GRAVRAND
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
	 * <b>Permet de jouer une gammes de notes</b>
	 * @author Noe GRAVRAND
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
	 * <b>Permet de jouer la marseillaise</b>
	 * @author Noe GRAVRAND
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
	 * Permet de jouer The imperial march</b>
	 * @author Noe GRAVRAND
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

	/**
	 * <b>Permet de jouer le jingle de Mcdo</b>
	 * @author Noe GRAVRAND
	 */
	public static void Mcdo() throws InterruptedException {
		tempo(milisecFromBPM(180));
		
		note (FA,T4);
		note (SOL,T4P);
		note (LA,T4P);
		
		note (DO+20, T3);
		
		note (SI, T3);
	}
	
}