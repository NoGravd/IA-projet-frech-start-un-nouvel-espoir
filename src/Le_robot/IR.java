package Le_robot;

public class IR {
	
	
	public static void traiteDonnees (String donnees) {
		String donneesOmega = donnees;
		int[][] grille = Memoire.getGrilleIR();
		for (int ii=0; ii<grille.length; ii++) {
			String donneeAlpha = donneesOmega.substring(1+ii>=10?2:1, donneesOmega.indexOf("\n"));
			int XX = Integer.parseInt((donneeAlpha.substring(0, donneeAlpha.indexOf(";"))));
			donneeAlpha = donneeAlpha.substring(donneeAlpha.indexOf(";"));
			int YY = Integer.parseInt((donneeAlpha.substring(0, donneeAlpha.indexOf(";"))));
			grille[ii][0] = XX;
			grille[ii][1] = YY;
			donneesOmega = donnees.substring(donneesOmega.indexOf("\n"));
		}
		Memoire.setGrilleIR(grille);
	}
	
}