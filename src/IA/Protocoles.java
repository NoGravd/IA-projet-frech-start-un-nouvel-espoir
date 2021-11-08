package IA;

import Le_robot.*;

public class Protocoles {
	//class qui regroupe des protocoles utiliser dans les algo
	
	
	public static void goMarquer() {
		double sBouss = Memoire.getSuperBoussole();
		int goal = Memoire.getLaBonneBase();
		Roues.pivote((int)(goal-sBouss));
		//TODO : si robot adv sur trajet
		//TODO : roule
		if (Carto.IsIt_Mur((int) Capteur.getDistanceOb()) && Memoire.getEreBonneBase())
			marquer();
	}
	
	public static void marquer() {
		Pince.oPince_music();
		//TODO : recule
		Memoire.setNbBut();
	}
	
	
	
}
