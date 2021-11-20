package Le_robot;

/**
Une classe où l'on stock tout se qui est a stocker.
@author NG
*/
public class Memoire {
	
	/**
    Instance de compter le nombre de but marquer.
	 */
	private static int nbBut = 0;
	/**
    Methode qui nous permet de savoir le nombre de but marquer.
    @return Le nombre de but marquer.
	 */
	public static int getNbBut() {return nbBut;}
	/**
    Methode qui increment le nombre de but par 1.
	 */
	public static void IncrementeNbBut() {nbBut++;}
	
	
	//----------pince----------------
	
	/**
    Instance qui nous permet de savoir si le robot a un palet.
	 */
	private static boolean avoirPalet = false;
	/**
    Methode qui nous permet de modifier l'instance avoirPalet
	 */
	public static void setAvoirPalet (boolean boul) {avoirPalet = boul;}
	/**
    Methode qui nous permet de connaitre si l on possede un palet ou non
    @return True si le robot possede un palet, False sinon
	 */
	public static boolean getAvoirPalet() {return avoirPalet;}
	
	/**
    Instance qui nous permet de savoir si le robot les pince ouverte.
	 */
	private static boolean etatPince = false;
	/**
    Methode qui nous permet de modifier l'instance etatPince
	 */
	public static void setEtatPince (boolean boul) {etatPince = boul;}
	/**
    Methode qui nous permet de connaitre si la pince est ouverte ou non
    @return True si la pince du robot est ouverte, False sinon
	 */
	public static boolean getEtatPince() {return etatPince;}
	
	
	
	//------------carto--------------
	
	/**
    Instance qui nous permet de savoir si le robot est dans une base.
	 */
	private static boolean etreBase = false;
	/**
    Methode qui nous permet de modifier l'instance etreBase
	 */
	public static void setEtreBase(boolean boul) {etreBase = boul;}
	/**
    Methode qui nous permet de connaitre si le robot est dans une base.
    @return True si le robot est dans une base, False sinon
	 */
	public static boolean getEtreBase() {return etreBase;}
	
	/**
    Instance qui nous permet de savoir si le robot est dans la base adverse(où l'on doit marquer).
	 */
	private static boolean etreBonneBase = false;
	/**
    Methode qui nous permet de modifier l'instance etreBonneBase.
	 */
	public static void setEtreBonneBase (boolean boul) {etreBonneBase = boul;}
	/**
    Methode qui nous permet de connaitre si le robot est dans la base adverse.
    @return True si le robot est dans une base, False sinon
	 */
	public static boolean getEreBonneBase() {return etreBonneBase;}
	
	/**
    Instance qui represente la base adverse (où l'on doit marquer).
	 */
	private static int laBonneBase;
	/**
    Methode qui nous retourne si la base est la base adverse.
    @return 0 si on est dans la mauvaise base et 1 sinon
	 */
	public static int getLaBonneBase() {return laBonneBase;}
	
	/**
    Instance qui represente notre base (que l'on doit defendre).
	 */
	private static int laMauvaiseBase;
	/**
    Methode qui nous retourne si la base est notre base.
    @return 0 si on est dans la mauvaise base et 1 sinon
	 */
	public static int getLaMauvaiseBase() {return laMauvaiseBase;}
	
	/**
    Instance qui represente la position certaine de notre robot.
	 */
	private static int[][] positionsCertaine;
	/**
    Methode qui nous permet de modifier la position certaine du robot dans sa carte interne.
	 */
	public static void setPositionsCertaine (int[][] position) {positionsCertaine = position;}
	/**
    Methode qui nous retourne la position certaine du robot.
    @return la position certaine du robot.
	 */
	public static int[][] getPositionsCertaine() {return positionsCertaine;}
	
	/**
    Instance qui represente une position precise de notre robot.
	 */
	private static int[] positionPrecise = new int[] {404,404};
	/**
    Methode qui nous permet de modifier la position precise du robot dans sa carte interne.
	 */
	public static void setPositionPrecise (int[] position) {positionPrecise = position;}
	/**
    Methode qui nous retourne la position precise du robot.
    @return la position precise du robot.
	 */
	public static int[] getPositionPrecise() {return positionPrecise;}
	
	/**
    Instance qui represente la position des palets.
	 */
	private static int[][] positionPaletsPris;
	/**
    Methode qui nous permet d'ajouter la position d'un palet pris dans sa carte interne.
	 */
	public static int[][] newPositionPaletsPris() {return positionPaletsPris;}
	/**
    Methode qui nous retourne la position des palets pris.
    @return la position d'un palet pris dans sa carte interne.
	 */
	public static int[][] getPositionPaletsPris() {return positionPaletsPris;}
	
	/**
    Instance qui represente la boussole.
	 */
	private static int boussole; // nord=0; est=1; sud=2; ouest=3
	/**
    Methode qui nous permet de modifier la boussole du robot.
	 */
	public static void setBoussole (int bouss) {boussole = bouss;}
	/**
    Methode qui nous retourne la direction de regard du robot.
    @return la direction de regard du robo
	 */
	public static int getBoussole() {return boussole;}
	
	/**
    Instance qui represente la superboussole.
	 */
	private static double superBoussole;
	/**
    Methode qui nous permet de modifier la superboussole du robot.
	 */
	public static void setSuperBoussole (double superBouss) {superBoussole = superBouss;}
	/**
    Methode qui nous retourne la direction de regard du robot.
    @return la direction de regard du robot.
	 */
	public static double getSuperBoussole() {return superBoussole;}
	
	/**
    Instance qui represente la derniere ligne.
	 */
	private static int lastLigne = 404;//ptt changer en un tab? a voir
	/**
    Methode qui nous permet de modifier la derniere ligne.
	 */
	public static void setLastLigne (int coul) {lastLigne = coul;}
	/**
    Methode qui nous retourne la derniere ligne.
    @return la derniere ligne.
	 */
	public static int getLastLigne() {return lastLigne;}
	
	/**
    Instance qui represente la dernier position du dernier palet recuperer.
	 */
	private static int[] lastPositionP;
	/**
    Methode qui nous permet de modifier la dernier position du dernier palet recuperer.
	 */
	public  static void setLastPositionP (int[] position) {lastPositionP = position;}
	/**
    Methode qui retourne la dernier position du dernier palet recuperer.
    @return la dernier position du dernier palet recuperer.
	 */
	public static int[] getLastPositionP() {return lastPositionP;}
	
	/**
    Instance qui represente dans la carte interne de plusieurs zone de recherche.
	 */
	private static int[][] zone2Recherche = initialiseZone2Recherche();
	/**
    Inialise les differentes de zone de recherche.
    @return un tableau contenant les differentes de zone de recherche.
	 */
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
	/**
    Methode qui nous retourne les differentes de zone de recherche.
    @return les differentes de zone de recherche.
	 */
	public static int[][] getZone2Recherche() {return zone2Recherche;}
	
	
	
	//-------IR-----------
	/**
    Instance qui represente dans la grille representer par la camera.
	 */
	private static int[][] grilleIR;
	/**
    Initialise la grille representer par la camera.
      @param True si robot adverse, false sinon
	 */
	private static int[][] initialiseGrilleIR (boolean robotAdv) {
		if (robotAdv)
			return new int [11][2];
		else
			return new int [10][2];
	}
	/**
    Parametre la grille representer par la camera.
      @param Le grille qui represente ce que voit la camera
	 */
	public static void setGrilleIR (int[][] grille) {grilleIR = grille;} 
	/**
    Methode qui nous retourne la grille representer par la camera
    @return la grille representer par la camera
	 */
	public static int[][] getGrilleIR() {return grilleIR;}
	
	/**
    Instance qui represente dans la position du  robot dans la grille representer par la camera.
	 */
	private static int[] positionIR;
	/**
    Methode qui nous permet de modifier la position du  robot dans la grille representer par la camera.
      @param Le position du robot.
	 */
	public static void setPositionIR (int[] position) {positionIR = position;}
	/**
    Methode qui nous retourne la position du  robot dans la grille representer par la camera.
    @return la position du robot dans la grille representer par la camera.
	 */
	public static int[] getPositionIR() {return positionIR;}
	
	/**
    Instance qui represente dans la position des palets dans la grille representer par la camera.
	 */
	private static int[][] positionsPaletsIR;
	/**
    Methode qui nous permet de modifier la position des palets dans la grille representer par la camera.
      @param Position des palets detecter par la camera
	 */
	public static void setPositionsPaletsIR (int[][] positions) {positionsPaletsIR = positions;}
	/**
    Methode qui nous retourne la position des palets dans la grille representer par la camera.
    @return la position des palets dans la grille representer par la camera.
	 */
	public static int[][] getPositionsPaletsIR() {return positionsPaletsIR;}
	
	/**
    Instance qui represente dans la position de l'adversaire dans la grille representer par la camera.
	 */
	private static int[] positionAdvIR;
	/**
    Methode qui nous permet de modifier la position de l'adversaire dans la grille representer par la camera.
      @param position de l'adversaire
	 */
	public static void setPositionAdvIR (int[] position) {positionAdvIR = position;}
	/**
    Methode qui nous retourne la position de l'adversaire dans la grille representer par la camera.
    @return la position des palets de l'adversaire dans la grille representer par la camera.
	 */
	public static int[] getPositionAdvIR() {return positionAdvIR;}
	
	/**
    Instance qui represente dans le nombre de point reperer par la camera.
	 */
	private static int nbPointsIR=0;
	/**
    Methode qui nous permet de modifier la position de point reperer par la camera.
    @param nombre de points
	 */
	public static void setNbPointsIR (int nbPt) {nbPointsIR = nbPt;}
	/**
    Methode qui nous retourne la position de point reperer par la camera.
    @return la position des palets de point reperer par la camera.
	 */
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
	
	/**
    Methode qui nous retourne la position des zones deja visitee.
    @param la position des zones deja visitee.
	 */
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