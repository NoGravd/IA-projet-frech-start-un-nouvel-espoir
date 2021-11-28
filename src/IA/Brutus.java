package IA;

import Le_robot.*;

/**
 * <b> Contient l'algorithme Brutus a utiliser lors de la competition</b>
 * @author noegr
 */
public class Brutus {
	
	/**
	 * <b>Strategie qui recupere un maximum de palet en brut</b><p>
	 * Attention, le robot doit commencer sur le cote gauche
	 * @author Noe GRAVRAND
	 */
	public static void main(String[] args) {
		Central brutus = new Central(new int[] {0});
		int distCase= 50;
		brutus.pinces.ouverture_mobile();
		Roues.rouleDist_aveugle(distCase);
		brutus.pinces.fermeture_music();
		Roues.pivote(90);
		Roues.rouleDist_aveugle(distCase*2+20);
		Roues.rouleDist_aveugle(distCase*3);
		for (int ii=0; ii<2; ii++) {
			brutus.pinces.ouverture_music();
			Roues.rouleDist_aveugle(-distCase);
			Roues.pivote(-90);
			Roues.rouleDist_aveugle(ii==0?20:distCase);
			brutus.pinces.fermeture_music();
			Roues.pivote(90);
			Roues.rouleDist_aveugle(distCase);
		}
		brutus.pinces.ouverture_music();
		Roues.rouleDist_aveugle(-distCase);
		Roues.pivote(180);
		Roues.rouleDist_aveugle(distCase);
		brutus.pinces.fermeture_music();
		Roues.pivote(180);
		Roues.rouleDist_aveugle(distCase);
	}

}
