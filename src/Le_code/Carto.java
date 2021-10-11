package Le_code;

public class Carto {
	//class qui calculera les positions du david
	//@author NG
	
	private int[][] positionCertaine;
	private int[][] positionIncertaine;
	private boolean estDansBase=false;
	private boolean bonneBase=false;
	
	private int boussole;
	private int superBoussole;
	
	private final int BLANC = 0;
	private final int NOIRE = 1;
	private final int ROUGE = 2;
	private final int VERT = 3;
	private final int JAUNE = 4;
	private final int BLEU = 5;
	
	
	
	public Carto (int[][] positionDeDepart) {
		positionCertaine = positionDeDepart;
	}
	
	
	
	
	
	public void travLigne (int color) {
		//TODO : C la ou i ora tout le croustillant de la class
		int couleur = couleurDuInt(color);
		if (couleur == BLANC) {
			ligneBlanche();
			return;
		} else
			horsBase();
		//... autres couleurs
	}
	
	
	
	
	private int couleurDuInt (int leInt) {
		//TODO
		return 0;
	}
	
	
	//bases :
	
	private void ligneBlanche() {
		//TODO changer positions ?
		if (estDansBase)
			horsBase();
		else {
			estDansBase=true;
			quelleBase();
		}
	}
	
	private void horsBase() {
		estDansBase=false;
		bonneBase=false;
	}
	
	private void quelleBase() {
		//TODO
	}
	//xxx
	
	
	//--------Geteurs---------
	
	public int[][] getPositionC() {
		return positionCertaine;
	}
	
	public int[][] getPositionI() {
		return positionIncertaine;
	}
	
	public int getBoussole() {
		return boussole;
	}
	
	public int getSuperBoussole() {
		return superBoussole;
	}
	
	public boolean estDansBonneBase() {
		return bonneBase;
	}
	
}