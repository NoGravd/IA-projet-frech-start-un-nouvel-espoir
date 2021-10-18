package Le_robot;

public class Memoire {
	//class ou on stock tout se qui est a stocker
	//@author NG
	
	//pince
	private static boolean avoirPalet = false;
	private static boolean etatPince = false;
	
	//carto
	private static boolean etreBase = false;
	private static boolean etreBonneBase = false;
	private static int laBonneBase;
	private static int laMauvaiseBase;
	private static int[][] positionsCertaine;
	private static int[] positionPrecise = new int[] {404,404};
	private static int[][] positionPaletsPris;
	private static int boussole; // nord=0; est=1; sud=2; ouest=3
	private static double superBoussole;
	private static int lastLigne = 404;//ptt changer en un tab? a voir
	
	
	
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
	
	
	
	public static void setEtreBase(boolean boul) {
		etreBase = boul;
	}
	
	public static void setPositionsCertaine (int[][] position) {
		positionsCertaine = position;
	}
	
	public static void setPositionPrecise (int[] position) {
		positionPrecise = position;
	}
	
	public static void setBoussole (int bouss) {
		boussole = bouss;
	}
	
	public static void setSuperBoussole (double superBouss) {
		superBoussole = superBouss;
	}
	
	public static void setEtreBonneBase (boolean boul) {
		etreBonneBase = boul;
	}
	
	public static void setLastLigne (int coul) {
		lastLigne = coul;
	}
	
	
	//-------Pince--------
	
	public static void mvmtPince (boolean boul) {
		etatPince = boul;
	}
	
	
	
	public static void setAvoirPalet (boolean boul) {
		avoirPalet = boul;
	}
	
	
	
	//------Geteurs--------
	
	public static boolean getEtatPince() {
		return etatPince;
	}
	
	public static boolean getAvoirPalet() {
		return avoirPalet;
	}
	
	public static boolean getEtreBase() {
		return etreBase;
	}
	
	public static int[][] getPositionsCertaine() {
		return positionsCertaine;
	}
	
	public static int[] getPositionPrecise() {
		return positionPrecise;
	}
	
	public static int[][] positionPaletsPris() {
		return positionPaletsPris;
	}
	
	public static int getBoussole() {
		return boussole;
	}
	
	public static double getSuperBoussole() {
		return superBoussole;
	}
	
	public static boolean getEreBonneBase() {
		return etreBonneBase;
	}
	
	public static int getLastLigne() {
		return lastLigne;
	}
	
	public static int getLaBonneBase() {
		return laBonneBase;
	}
	
	public static int getLaMauvaiseBase() {
		return laMauvaiseBase;
	}
	
}