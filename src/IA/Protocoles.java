package IA;

import Le_robot.*;

public class Protocoles {
	//class qui regroupe des protocoles utilisés dans les algo
	
	
	public static void goMarquer() {
		double sBouss = Memoire.getSuperBoussole();
		int goal = Memoire.getLaBonneBase();
		Roues.pivote((int)(goal-sBouss));
		//TODO : si robot adv sur trajet
		conflit_robotAdv((int) Capteur.getDistanceOb());
		//TODO : roule
		if (Carto.IsIt_mur((int) Capteur.getDistanceOb()) && Memoire.getEreBonneBase())
			marquer();
	}
	
	public static void marquer() {
		Pince.oPince_music();
		//TODO : recule
		Memoire.setNbBut();
	}
	
	public static void conflit_robotAdv (int dist) {
		//TODO : contourne le robot adv
		
	}
	
	public static void goZoneRecherche() {
		//TODO : va dans zonerecherche + proche (pathFinding ma geule !)
		
		
	}
	
	
}