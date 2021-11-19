package Protocole;

import Le_robot.*;

public class Protocoles {
	//class qui regroupe des protocoles utilisés dans les algo
	
	
	public static void goMarquer() {
		double sBouss = Memoire.getSuperBoussole();
		int goal = Memoire.getLaBonneBase();
		Roues.pivote((int)(goal-sBouss));
		conflit_robotAdv((int) Capteur.getDistanceOb());
		Roues.SrouleDist(calcDistanceZone(new int[] {goal==1?5:0,Memoire.getPositionPrecise()[1]}));
		if (Carto.IsIt_mur((int) Capteur.getDistanceOb()) && Memoire.getEreBonneBase())
			marquer();
	}
	
	public static void marquer() {
		Pince.ouverture_music();
		Roues.recule();
		Memoire.IncrementeNbBut();
	}
	
	public static void conflit_robotAdv (int dist) {
		//TODO : contourne le robot adv
		
	}
	
	public static void goZone2Recherche() {
		int[][] zone2recherche = Memoire.getZone2Recherche();
		int[] position = Memoire.getPositionPrecise();
		int[] zoneProchest = zone2recherche [0];
		int distZoneP = 1000;
		for (int ii=0; ii<zone2recherche.length; ii++) {
			int distZone = Math.abs(position[0] - zone2recherche[ii][0]);
			distZone += Math.abs(position[1] - zone2recherche[ii][1]);
			if (distZone<distZoneP) {
				distZoneP=distZone;
				zoneProchest = zone2recherche[ii];
			}
		}
		goTo (zoneProchest);
	}
	
	public static void goTo (int[] zone) {
		pivoteVersZone(zone);
		int distance = calcDistanceZone(zone);
		Roues.rouleDist(distance);
	}
	
	public static void pivoteVersZone (int[] zone) {
		int[] position = Memoire.getPositionPrecise();
		int[] pointX = new int[] {zone[0],position[1]};
		int distOpose = calcDistanceZone(pointX);
		
		//distAdj
		int distAdj;
		double distX = Math.abs(zone[0] - position[0])*(3/6);
		double distY = Math.abs(zone[1] - position[1])*(2/4);
		if (zone[0]==position[0])
			distAdj = (int) distX;
		if (zone[1]==position[1])
			distAdj = (int) distY;
		//putain fo utiliser pytagor !
		else
			distAdj = (int) Math.round(Math.sqrt(distX*distX+distY*distY));
		int degre = (int) Math.round(distOpose / distAdj);
		Roues.pivote(degre);
	}
	
	public static int calcDistanceZone (int[] zone) {
		int[] position = Memoire.getPositionPrecise();
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