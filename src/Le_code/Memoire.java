package Le_code;

public class Memoire {
	private static boolean avoirPalet = false;
	private static boolean etreBase = false;
	private static boolean [][] positionSur;
	private static boolean [][] positionPasSur;
	private static boolean etatPince;
	
	
	
	public Memoire (boolean [][] position, boolean pince) {
		positionSur = position;
		positionPasSur = position;
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
	
	public static void mvmtPince (boolean boul) {
		etatPince = boul;
	}
	
	public static boolean getEtatPince() {
		return etatPince;
	}
}
