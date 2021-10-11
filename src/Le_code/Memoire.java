package Le_code;

public class Memoire {
	private static boolean avoirPalet = false;
	private static boolean etreBase = false;
	private static boolean [][] positionCertaine;
	private static boolean [][] positionIncertaine;
	private static boolean etatPince;
	
	
	
	//------Carto----------
	
	public Memoire (boolean [][] position, boolean pince) {
		positionCertaine = position;
		positionIncertaine = position;
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
	
	
	
	//-------Pince--------
	
	public static void mvmtPince (boolean boul) {
		etatPince = boul;
	}
	
	public static boolean getEtatPince() {
		return etatPince;
	}
}
