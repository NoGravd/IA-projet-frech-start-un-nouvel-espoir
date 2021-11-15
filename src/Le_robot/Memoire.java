package Le_robot;

/**
Une classe où l'on stock tout se qui est a stocker.
@author NG
*/
public class Memoire {
	
	
	private static int nbBut = 0;
	public static int getNbBut() {return nbBut;}
	public static void IncrementeNbBut() {nbBut++;}
	
	
	//----------pince----------------
	
	/**
    Instance qui nous permet de savoir si le robot a un palet.
	 */
	private static boolean avoirPalet = false;
	public static void setAvoirPalet (boolean boul) {avoirPalet = boul;}
	public static boolean getAvoirPalet() {return avoirPalet;}
	
	/**
    Instance qui nous permet de savoir si le robot les pince ouverte.
	 */
	private static boolean etatPince = false;
	public static void setEtatPince (boolean boul) {etatPince = boul;}
	public static boolean getEtatPince() {return etatPince;}
	
	
	
	//------------carto--------------
	
	/**
    Instance qui nous permet de savoir si le robot est dans une base.
	 */
	private static boolean etreBase = false;
	public static void setEtreBase(boolean boul) {etreBase = boul;}
	public static boolean getEtreBase() {return etreBase;}
	
	/**
    Instance qui nous permet de savoir si le robot est dans la base adverse(où l'on doit marquer).
	 */
	private static boolean etreBonneBase = false;
	public static void setEtreBonneBase (boolean boul) {etreBonneBase = boul;}
	public static boolean getEreBonneBase() {return etreBonneBase;}
	
	/**
    Instance qui represente la base adverse (où l'on doit marquer).
	 */
	private static int laBonneBase;
	public static int getLaBonneBase() {return laBonneBase;}
	
	/**
    Instance qui represente notre base (que l'on doit defendre).
	 */
	private static int laMauvaiseBase;
	public static int getLaMauvaiseBase() {return laMauvaiseBase;}
	
	/**
    Instance qui represente la position certaine de notre robot.
	 */
	private static int[][] positionsCertaine;
	public static void setPositionsCertaine (int[][] position) {positionsCertaine = position;}
	public static int[][] getPositionsCertaine() {return positionsCertaine;}
	
	/**
    Instance qui represente une position precise de notre robot.
	 */
	private static int[] positionPrecise = new int[] {404,404};
	public static void setPositionPrecise (int[] position) {positionPrecise = position;}
	public static int[] getPositionPrecise() {return positionPrecise;}
	
	/**
    Instance qui represente la position precise des palets.
	 */
	private static int[][] positionPaletsPris;
	public static int[][] newPositionPaletsPris() {return positionPaletsPris;}
	public static int[][] getPositionPaletsPris() {return positionPaletsPris;}
	
	/**
    Instance qui represente la boussole.
	 */
	private static int boussole; // nord=0; est=1; sud=2; ouest=3
	public static void setBoussole (int bouss) {boussole = bouss;}
	public static int getBoussole() {return boussole;}
	
	/**
    Instance qui represente la superboussole.
	 */
	private static double superBoussole;
	public static void setSuperBoussole (double superBouss) {superBoussole = superBouss;}
	public static double getSuperBoussole() {return superBoussole;}
	
	/**
    Instance qui represente la derniere ligne.
	 */
	private static int lastLigne = 404;//ptt changer en un tab? a voir
	public static void setLastLigne (int coul) {lastLigne = coul;}
	public static int getLastLigne() {return lastLigne;}
	
	private static int[] lastPositionP;
	public  static void setLastPositionP (int[] position) {lastPositionP = position;}
	public static int[] getLastPositionP() {return lastPositionP;}
	
	private static int[][] zone2Recherche = initialiseZone2Recherche();
	private static int[][] initialiseZone2Recherche() {
		int[][] rslt = new int [16][2];
		for (int xx=1; xx<5; xx++) {
			for (int yy=0; yy<4; yy++) {
				rslt[xx+yy-1][0] = xx;
				rslt[xx+yy-1][1] = yy;
			}
		}
		return rslt;
	}
	public static int[][] getZone2Recherche() {return zone2Recherche;}
	
	
	
	//-------IR-----------
	private static int[][] grilleIR;
	private static int[][] initialiseGrilleIR (boolean robotAdv) {
		if (robotAdv)
			return new int [11][2];
		else
			return new int [10][2];
	}
	public static void setGrilleIR (int[][] grille) {grilleIR = grille;} 
	public static int[][] getGrilleIR() {return grilleIR;}
	
	private static int[] positionIR;
	public static void setPositionIR (int[] position) {positionIR = position;}
	public static int[] getPositionIR() {return positionIR;}
	
	private static int[][] positionsPaletsIR;
	public static void setPositionsPaletsIR (int[][] positions) {positionsPaletsIR = positions;}
	public static int[][] getPositionsPaletsIR() {return positionsPaletsIR;}
	
	private static int[] positionAdvIR;
	public static void setPositionAdvIR (int[] position) {positionAdvIR = position;}
	public static int[] getPositionAdvIR() {return positionAdvIR;}
	
	private static int nbPointsIR=0;
	public static void setNbPointsIR (int nbPt) {nbPointsIR = nbPt;}
	public static int getnbPoinntsIR() {return nbPointsIR;}
	
	
	/**
    Permet d'actualiser les instances du robot selon sa position.
    @param position Le position du robot.
    @param pince l'etat de la pince.
	 */
	public static void constructeur (int [] position, boolean pince, boolean robotAdvIR) {
		positionsCertaine [0] = position;
		positionPrecise = position;
		etatPince = pince;
		boolean positionOUEST = (position [0] ==1);//robot commence cote gauche
		if (positionOUEST) {
			boussole = 1;//EST
			superBoussole = 1;//EST
			laBonneBase = 1; //EST
			laMauvaiseBase = 3; //OUEST
		} else {
			boussole = 3;//OUEST
			superBoussole = 3;//OUEST
			laBonneBase = 3; //OUEST
			laMauvaiseBase = 1;//EST
		}
		initialiseGrilleIR(robotAdvIR);
	}
	
	
	
		//------Carto----------
	
/**
   Permet de determiner la position des palets deja recuperer par notre robot.
	 */
	public static void catchPalet() {
		//a changer TODO : position segmentaire + setZonerecherche
		
		int[][] tmpr = new int[positionPaletsPris.length+1][2];
		int ii;
		for (ii=0; ii<tmpr.length-1; ii++) {
			tmpr [ii] [0] = positionPaletsPris [ii] [0];
			tmpr [ii] [1] = positionPaletsPris [ii] [1];
		}
		tmpr [ii+1] [0]= positionPrecise [0];
		tmpr [ii+1] [1]= positionPrecise [1];
	}
	
	public static void zoneVu (int[] position) {
		if (zone2Recherche.length==0)
			return;
		int[][] tmpr = new int [zone2Recherche.length][2];
		boolean boul=false;
		for (int ii=0; ii<tmpr.length; ii++) {
			if (zone2Recherche[ii]!=position) {
				tmpr [ii][0] = zone2Recherche [ii][0];
				tmpr [ii][1] = zone2Recherche [ii][1];
			} else
				boul=true;
		}
		if (boul) {
			int[][] rslt = new int [tmpr.length-1][2];
			for (int ii=0; ii<rslt.length; ii++) {
				tmpr [ii][0] = zone2Recherche [ii][0];
				tmpr [ii][1] = zone2Recherche [ii][1];
			}
			zone2Recherche = rslt;
		}
	}
	
}