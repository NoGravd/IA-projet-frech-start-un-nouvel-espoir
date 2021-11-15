package IA;

import Le_robot.Capteur;
import Le_robot.Carto;
import Le_robot.Memoire;
import Le_robot.Pince;
import Le_robot.Roues;

public class ProtocolesIR {
	
	
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
		Memoire.IncrementeNbBut();
	}
	
	public static void conflit_robotAdv (int dist) {
		//TODO : contourne le robot adv
		
	}
	
	public static void goPaletProche() {
		//TODO
	}
	
	public static void goTo (int[] zone) {
		pivoteVersZone(zone);
		int distance = calcDistanceZone(zone);
		Roues.rouleDist(distance);
	}
	
	public static void pivoteVersZone (int[] zone) {
		//TODO
	}
	
	public static int calcDistanceZone (int[] zone) {
		int[] position = Memoire.getPositionIR();
		double distX = Math.abs(zone[0] - position[0])*(3/6);
		double distY = Math.abs(zone[1] - position[1])*(2/4);
		if (zone[0]==position[0])
			return (int) distX;
		if (zone[1]==position[1])
			return (int) distY;
		//putain fo utiliser pytagor !
		return (int) Math.round(Math.sqrt(distX*distX+distY*distY));
	}
	
}