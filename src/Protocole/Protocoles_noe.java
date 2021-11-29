package Protocole;

import Le_robot.*;

/**
 * Une classe qui regroupe des protocoles utilisables dans les algorithmes IA
 * @param robot : <i>Central</i>
 * 
 * @author Noe GRAVRAND
 */
public class Protocoles_noe {
	
	Central robot;
	
	/**
	 * <b>Constructeur de la class Protocoles_noe</b>
	 * @parma leDavid : <i>Central</i>
	 * 
	 * @auteur Noe GRAVRAND
	 */
	public Protocoles_noe(Central leDavid) {
		robot = leDavid;
	}
	
	
	
	/**
	 * <b>Permet au robot d'aller jusqu a la zone adverse puis d'y déposer un palet</b>
	 * @return leDavid = <i>Central</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	public Central goMarquer() {
		double sBouss = robot.memoire.getSuperBoussole();
		int goal = robot.memoire.getLaBonneBase();
		Roues.pivote((int)(goal-sBouss));
		robot.carto.rotateDeg((int)(goal-sBouss), robot.memoire);
		conflit_robotAdv((int) robot.capteurs.getDistanceOb());
		int dist = calcDistanceZone(new int[] {goal==1?5:0,robot.memoire.getPositionPrecise()[1]});
		Roues.rouleDist(dist , robot.capteurs, robot.memoire, robot.carto);
		if (robot.memoire.getEreBonneBase())
			robot = marquer();
		return robot;
	}
	
	/**
	 * <b>Permet de lacher le palets et de reculer un fois le but marqué</b>
	 * Incremente le nombre de but marque.
	 * @return leDavid = <i>Central</i>
	 * 
	 * @autor Noe GRAVRAND
	 */
	public Central marquer() {
		robot.pinces.ouverture_music();
		robot.memoire.setEtatPince(true);
		Roues.recule();
		robot.memoire.IncrementeNbBut();
		robot.pinces.fermeture_music();
		robot.memoire.setEtatPince(false);
		return robot;
	}
	
	/**
	 * Effectue une action si le robot adverse est en conflit avec notre robot</b>
	 * <p>Attention : pas coder
	 * @param dist : <i>int</i>
	 * @return leDavid = <i>Central</i>
	 */
	public Central conflit_robotAdv (int dist) {
		//TODO : contourne le robot adv
		return robot;
	}
	
	/**
	 * <b>Permet au robot d'aller dans les zones de recherche</b>
	 * @return leDavid = <i>Central</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	public Central goZone2Recherche() {
		int[][] zone2recherche = robot.memoire.getZone2Recherche();
		int[] position = robot.memoire.getPositionPrecise();
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
		robot = goTo (zoneProchest);
		return robot;
	}
	
	/**
	 * <b>Permet au robot d'aller dans une zone</b>
	 * @param zone : <i>int[]</i>
	 * @return leDavid = <i>Central</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	public Central goTo (int[] zone) {
		pivoteVersZone(zone);
		int distance = calcDistanceZone(zone);
		Roues.rouleDist(distance, robot.capteurs, robot.memoire, robot.carto );
		return robot;
	}
	
	/**
	 * <b>Fait pivoter le robot vers une zone</b>
	 * @param zone : <i>int[]</i>
	 * @return leDavid = <i>Central</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	public Central pivoteVersZone (int[] zone) {
		int[] position = robot.memoire.getPositionPrecise();
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
		robot.carto.rotateDeg(degre, robot.memoire);
		return robot;
	}
	
	/**
	 * <b>Permet de calculer la distance jusqu a la zone objectif</b>
	 * @param zone : <i>int[]</i>
	 * @return zone : <i>int</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	public int calcDistanceZone (int[] zone) {
		int[] position = robot.memoire.getPositionPrecise();
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