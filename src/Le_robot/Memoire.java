package Le_robot;

/**
Une classe où l'on stock tout se qui est a stocker.
@author NG
*/
public class Memoire {

	//pince
	/**
    Instance qui nous permet de savoir si le robot a un palet.
	 */
	private static boolean avoirPalet = false;
	/**
    Instance qui nous permet de savoir si le robot les pince ouverte.
	 */
	private static boolean etatPince = false;
	
	//carto
	/**
    Instance qui nous permet de savoir si le robot est dans une base.
	 */
	private static boolean etreBase = false;
	/**
    Instance qui nous permet de savoir si le robot est dans la base adverse(où l'on doit marquer).
	 */
	private static boolean etreBonneBase = false;
	/**
    Instance qui represente la base adverse (où l'on doit marquer).
	 */
	private static int laBonneBase;
	/**
    Instance qui represente notre base (que l'on doit defendre).
	 */
	private static int laMauvaiseBase;
	/**
    Instance qui represente la position certaine de notre robot.
	 */
	private static int[][] positionsCertaine;
	/**
    Instance qui represente une position precise de notre robot.
	 */
	private static int[] positionPrecise = new int[] {404,404};
	/**
    Instance qui represente la position precise des palets.
	 */
	private static int[][] positionPaletsPris;
	/**
    Instance qui represente la boussole.
	 */
	private static int boussole; // nord=0; est=1; sud=2; ouest=3
	/**
    Instance qui represente la superboussole.
	 */
	private static double superBoussole;
	/**
    Instance qui represente la derniere ligne.
	 */
	private static int lastLigne = 404;//ptt changer en un tab? a voir
	
	
	/**
    Permet d'actualiser les instances du robot selon sa position.
    @param position Le position du robot.
    @param pince l'etat de la pince.
	 */
	public static void constructeur (int [] position, boolean pince) {
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
	}
	
	
	
	//------Carto----------
	
	/**
    Permet de determiner la position des palets deja recuperer par notre robot.
	 */
	public static void catchPalet() {				
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
    Dire au robot si il est dans une base ou non.
    @param boul true si il est dans une base, false sinon 
	 */
	public static void setEtreBase(boolean boul) {
		etreBase = boul;
	}
	
	/**
    Donner au robot sa position certaine sur le terrain.
    @param position La position du robot
	 */
	public static void setPositionsCertaine (int[][] position) {
		positionsCertaine = position;
	}
	
	/**
    Donner au robot sa position precise sur le terrain.
    @param position La position du robot
	 */
	public static void setPositionPrecise (int[] position) {
		positionPrecise = position;
	}
	
	/**
    Donner au robot sa direction de regard.
    @param bouss L'angle dans lequel le robot est dirige
	 */
	public static void setBoussole (int bouss) {
		boussole = bouss;
	}
	
	/**
    Donner au robot sa direction de regard.
    @param superBouss L'angle dans lequel le robot est dirige
	 */
	public static void setSuperBoussole (double superBouss) {
		superBoussole = superBouss;
	}
	
	/**
    Dire au robot si il est dans la base adverse (où l'on doit marquer).
    @param boul true si dans la base adverse, false sinon
	 */
	public static void setEtreBonneBase (boolean boul) {
		etreBonneBase = boul;
	}
	
	/**
    Donner la couleur de la derniere ligne.
    @param coul l'ID de la couleur de la derniere colone
	 */
	public static void setLastLigne (int coul) {
		lastLigne = coul;
	}
	
	
	
	//-------Pince--------
	
	/**
    Donner l'information sur le mouvement de la pince.
    @param boul true si la pince est en mouvement, false sinon
	 */
	public static void mvmtPince (boolean boul) {
		etatPince = boul;
	}
	
	/**
    Donner l'information sur le mouvement de la pince.
    @param boul true si le robot est en possession d'un palet, false sinon
	 */
	public static void setAvoirPalet (boolean boul) {
		avoirPalet = boul;
	}
	
	
	//------Geteurs--------
	
	/**
    Retour l'etat de la pince.
    @return true si la pince est ouverte, false sinon
	 */
	public static boolean getEtatPince() {
		return etatPince;
	}
	
	/**
    Retour la possession d'un palet ou non.
    @return true si le robot possede un palet, false sinon
	 */
	public static boolean getAvoirPalet() {
		return avoirPalet;
	}
	
	/**
    Retour si le robot est dans une base ou non.
    @return true si le robot est dans une base, false sinon
	 */
	public static boolean getEtreBase() {
		return etreBase;
	}
	
	/**
    Retour la position certaine du robot.
    @return la position certaine du robot.
	 */
	public static int[][] getPositionsCertaine() {
		return positionsCertaine;
	}
	
	/**
	   Retour la position precise du robot.
	    @return la position precise du robot.
	 */
	public static int[] getPositionPrecise() {
		return positionPrecise;
	}
	
	/**
	   Retour la position precise des palets.
	    @return la position precise des palets.
	 */
	public static int[][] positionPaletsPris() {
		return positionPaletsPris;
	}
	
	/**
	   Retour la direction que fait face le robot.
	    @return la direction que fait face le robot.
	 */
	public static int getBoussole() {
		return boussole;
	}
	
	/**
	   Retour la direction que fait face le robot.
	    @return la direction que fait face le robot.
	 */
	public static double getSuperBoussole() {
		return superBoussole;
	}
	
	/**
    Retour si le robot est dans la base adverse(où l'on doit marquer).
    @return true si le robot est dans la base adverse, false sinon
	 */
	public static boolean getEreBonneBase() {
		return etreBonneBase;
	}
	
	/**
    Retour la couleur de la derniere ligne.
    @return la couleur de la derniere ligne
	 */
	public static int getLastLigne() {
		return lastLigne;
	}
	
	/**
    Retour l'instance qui represente la base adverse(où l'on doit marquer).
    @return l'instance qui represente la base adverse
	 */
	public static int getLaBonneBase() {
		return laBonneBase;
	}
	
	/**
    Retour l'instance qui represente notre base.
    @return l'instance qui represente notre base
	 */
	public static int getLaMauvaiseBase() {
		return laMauvaiseBase;
	}
	
}