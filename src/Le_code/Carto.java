package Le_code;

public class Carto {
	//class qui calculera les positions du david
	//@author NG
	
	private int[][] positionCertaine;
	private int[][] positionPrecis;
	private boolean estDansBase=false;
	private boolean bonneBase=false;
	
	private int boussole;
	private int superBoussole;
	
	//pas necessaire mais pratik : (int arbitraires)
	private final int BLANC = 0;//x1 ou x5
	private final int NOIRE = 1;//x3 ou y2
	private final int ROUGE = 2;//y3
	private final int VERT = 3;//x2
	private final int JAUNE = 4;//y1
	private final int BLEU = 5;//x4
	private final int RIEN= 666;//ptt D-ja triee par class capteur mais a garder pour debug
	
	
	
	public Carto (int[][] positionDeDepart) {
		positionCertaine = positionDeDepart;
	}
	
	
	
	
	
	public void travLigne (int color) {
		int couleur = couleurDuInt(color);
		if (couleur == BLANC) {
			ligneBlanche();
		} else
			horsBase();
		if (couleur == NOIRE) {
			positionCertaine = new int[][] {{1,1},{1,2},{2,0},{2,1},{2,2},{2,3},{3,0},{3,1},{3,2},{3,3},{4,1},{4,2}};
			calculPositionIc (couleur);
		}
		if (couleur == ROUGE) {
			positionCertaine = new int[][] {{1,2},{1,3},{2,2},{2,3},{3,2},{3,3},{4,2},{4,3},{5,2},{5,3}};
			calculPositionIc (couleur);
		}
		if (couleur == VERT) {
			positionCertaine = new int[][] {{1,1},{3,2},{3,3},{3,4},{2,1},{2,2},{2,3},{2,4}};
			calculPositionIc (couleur);
		}
		if (couleur == JAUNE) {
			positionCertaine = new int[][] {{1,0},{1,1},{2,0},{2,1},{3,0},{3,1},{4,0},{4,1},{5,0},{5,1}};
			calculPositionIc (couleur);
		}
		if (couleur == BLEU) {
			positionCertaine = new int[][] {{3,1},{3,2},{3,3},{3,4},{4,1},{4,2},{4,3},{4,4}};
			calculPositionIc (couleur);
		}
	}
	
	
	private void calculPositionIc(int couleur) {
		//TODO
	}
	
	
	
	private int couleurDuInt (int leInt) {
		//TODO
		return RIEN;
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
	
	public int[][] getPositionP() {
		return positionPrecis;
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