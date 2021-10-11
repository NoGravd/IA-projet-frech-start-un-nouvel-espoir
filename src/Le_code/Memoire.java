package Le_code;

public class Memoire {
	
	//pince
	private static boolean avoirPalet = false;
	private static boolean etatPince;
	
	//carto
	private static boolean etreBase = false;
	private static int[][] positionsCertaine;
	private static int[][] positionPrecise;
	private static int[][] positionPaletsPris;
	private static int boussole;
	private static double superBoussole;
	private static boolean bonneBase = false;
	
	
	public static void constructeur (int [][] position, boolean pince) {
		positionsCertaine = position;
		positionPrecise = position;
		etatPince = pince;
	}
	
	
	
	//------Carto----------
	
	public static void modifEtreBase(boolean boul) {
		etreBase = boul;
	}
	
	public static void catchPalet() {
		int[][] tmpr = new int[positionPaletsPris.length+1][2];
		int ii;
		for (ii=0; ii<tmpr.length-1; ii++) {
			tmpr [ii][0] = positionPaletsPris[ii][0];
			tmpr [ii][1] = positionPaletsPris[ii][1];
		}
		tmpr [ii+1] [0]= positionPrecise[0][0];
		tmpr [ii+1] [1]= positionPrecise[0][1];
	}
	
	public static void modifPositionCertaine (int[][] position) {
		positionsCertaine = position;
	}
	
	public static void modifPositionPrecise (int[][] position) {
		positionPrecise = position;
	}
	
	public static void modifBoussole (int bouss) {
		boussole = bouss;
	}
	
	public static void modifSuperBoussole (int superBouss) {
		superBoussole = superBouss;
	}
	
	public static void modifBonneBase (boolean boul) {
		bonneBase = boul;
	}
	
	
	//-------Pince--------
	
	public static void mvmtPince (boolean boul) {
		etatPince = boul;
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
	
	public static int[][] getPositionCertaine() {
		return positionsCertaine;
	}
	
	public static int[][] getPositionPrecise() {
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
	
	public static boolean getBonneBase() {
		return bonneBase;
	}
	
}