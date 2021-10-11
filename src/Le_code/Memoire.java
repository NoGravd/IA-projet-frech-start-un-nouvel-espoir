package Le_code;

public class Memoire {
	private static boolean avoirPalet = false;
	private static boolean etreBase = false;
	private static int[][] positionsCertaine;
	private static int[][] positionPrecis;
	private static boolean etatPince;
	
	private static int[][] positionPaletsPris;
	
	
	
	//------Carto----------
	
	public Memoire (int [][] position, boolean pince) {
		positionsCertaine = position;
		positionPrecis = position;
		etatPince = pince;
	}
	
	public static void lignePasBlanche() {
		etreBase = false;
	}
	
	public static void ligneBlanche() {
		if (etreBase)
			etreBase = false;
		else
			etreBase = true;
	}
	
	public static void catchPalet() {
		int[][] tmpr = new int[positionPaletsPris.length+1][2];
		int ii;
		for (ii=0; ii<tmpr.length-1; ii++) {
			tmpr [ii][0] = positionPaletsPris[ii][0];
			tmpr [ii][1] = positionPaletsPris[ii][1];
		}
		tmpr [ii+1] [0]= positionPrecis[0][0];
		tmpr [ii+1] [1]= positionPrecis[0][1];
	}
	
	
	//-------Pince--------
	
	public static void mvmtPince (boolean boul) {
		etatPince = boul;
	}
	
	public static boolean getEtatPince() {
		return etatPince;
	}
	
	
}