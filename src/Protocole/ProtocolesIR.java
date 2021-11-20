package Protocole;

import Le_robot.*;

/**
Une classe qui regroupe des protocoles (qui utilise la camera) utilis�s dans les algorithmes utilisee par le robot.
 */
public class ProtocolesIR {
	
	/**
	Une methode qui permet au robot d'aller jusqu a la zone de but adverse.
	 */
	public static void goMarquer() {
		double sBouss = Memoire.getSuperBoussole();
		int goal = Memoire.getLaBonneBase();
		Roues.pivote(((goal-sBouss)>180)? ((int)(goal-sBouss-360)) : ((int)(goal-sBouss)));
		conflit_robotAdv((int) Capteur.getDistanceOb());
		Roues.SrouleDist(calcDistanceZone(new int[] {goal==1?5:0,Memoire.getPositionIR()[1]}));
		if (Carto.IsIt_mur((int) Capteur.getDistanceOb()) && Memoire.getEreBonneBase())
			marquer();
	}
	
	/**
	Une methode qui permet de lacher le palets et de reculer un fois le but marquer.
	Incremente le nombre de but marque.
	 */
	public static void marquer() {
		Pince.ouverture_music();
		Roues.recule();
		Memoire.IncrementeNbBut();
	}
	
	/**
	Une methode qui effectue une action si le robot adverse est en conflit avec notre robot.
	@param distance du robot adverse.
	 */
	public static void conflit_robotAdv (int dist) {
		//TODO : contourne le robot adv
		
	}
	
	/**
	Une methode qui au robot d'aller vers le palet le plus proche.
	 */
	public static void goPaletProche() {
		//TODO
	}
	
	/**
	Une methode qui permet au robot d'aller dans une zone.
	@param La zone dans lequel le robot doit se rendre.
	 */
	public static void goTo (int[] zone) {
		pivoteVersZone(zone);
		int distance = calcDistanceZone(zone);
		Roues.SrouleDist(distance);
	}
	
	
	/**
	Une methode qui fait pivoter le robot vers une zone.
	@param La zone dans lequel le robot doit se pivoter.
	 */
	public static void pivoteVersZone (int[] zone) {
		int[] position = Memoire.getPositionIR();
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
	
	/**
	Une methode qui permet de calculer la distance jusqu a la zone objectif.
	@param La zone dans lequel le robot doit se rendre.
	 */
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