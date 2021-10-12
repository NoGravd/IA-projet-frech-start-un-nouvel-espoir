package Le_code;

public class Carto {
	//class qui calculera les positions du david
	//@author NG
	
	//pas obligatoire mais pratik : (int arbitraires)
	private final int BLANC = 0;//x1 ou x5
	private final int NOIRE = 1;//x3 ou y2
	private final int ROUGE = 2;//y3
	private final int VERT = 3;//x2
	private final int JAUNE = 4;//y1
	private final int BLEU = 5;//x4
	private final int RIEN= 666;//ptt D-ja triee par class capteur mais a garder pour debug
	
	private final int NORD = 0;
	private final int EST = 1;
	private final int SUD = 2;
	private final int OUEST = 3;
	
	
	
	public void travLigne (int color) {
		int couleur = couleurDuInt(color);
		if (couleur == BLANC) {
			ligneBlanche();
		} else
			horsBase();
		if (couleur == NOIRE) {
			Memoire.setPositionCertaine (new int[][] {{1,1},{1,2},{2,0},{2,1},{2,2},{2,3},{3,0},{3,1},{3,2},{3,3},{4,1},{4,2}});
			calculPositionP (couleur);
		}
		if (couleur == ROUGE) {
			Memoire.setPositionCertaine (new int[][] {{1,2},{1,3},{2,2},{2,3},{3,2},{3,3},{4,2},{4,3},{5,2},{5,3}});
			calculPositionP (couleur);
		}
		if (couleur == VERT) {
			Memoire.setPositionCertaine (new int[][] {{1,1},{3,2},{3,3},{3,4},{2,1},{2,2},{2,3},{2,4}});
			calculPositionP (couleur);
		}
		if (couleur == JAUNE) {
			Memoire.setPositionCertaine (new int[][] {{1,0},{1,1},{2,0},{2,1},{3,0},{3,1},{4,0},{4,1},{5,0},{5,1}});
			calculPositionP (couleur);
		}
		if (couleur == BLEU) {
			Memoire.setPositionCertaine (new int[][] {{3,1},{3,2},{3,3},{3,4},{4,1},{4,2},{4,3},{4,4}});
			calculPositionP (couleur);
		}
		travLigneBouss(couleur);
		Memoire.setLastLigne(couleur);
	}
	
	
	
	private void calculPositionP (int couleur) {
		//TODO
	}
	
	
	
	public int couleurDuInt (int leInt) {
		//TODO
		return RIEN;
	}
	
	
	
	public void Mur (int dist) {
		//TODO
	}
	
	
	
	//boussole :
	
	private void travLigneBouss (int color) {
		//TODO
			
	}
	
	public void corrigeAngleMur (int color) {//couleur = Rouge/Jaune
		if (color!=ROUGE&&color!=JAUNE)
			return;
		//rotate de x; doit calculer x en fonction du mur (US)
		//info : la distance entre la ligne et le mur est de 50cm; US = ligne + 4cm; distance entre l'axe de rotation et la ligne + 9cm
		int x=0;
		//TODO
		
		Roues.pivote(x);
		if (color==ROUGE) {
			Memoire.setBoussole(NORD);
			Memoire.setSuperBoussole(NORD);
		} else {
			Memoire.setBoussole(SUD);
			Memoire.setSuperBoussole(SUD);
		}
	}
	
	public void inverseBouss() {
		switch(Memoire.getBoussole()) {
		case NORD : Memoire.setBoussole(SUD);
		case EST : Memoire.setBoussole(OUEST);
		case SUD : Memoire.setBoussole(NORD);
		case OUEST : Memoire.setBoussole(EST);
		}
	}
	
	public void corrigeAngleLignes() {
		//TODO
	}
	
	
	//bases :
	
	private void ligneBlanche() {
		//TODO changer positions ?
		if (Memoire.getEtreBase())
			horsBase();
		else {
			Memoire.setEtreBase(true);
			quelleBase();
		}
	}
	
	private void horsBase() {
		Memoire.setEtreBase(false);
		Memoire.setEtreBonneBase(false);
	}
	
	private void quelleBase() {
		boolean etreBonneBase;
		//TODO
		//------------------
		//vais faire system de point :
		//-positionCertaine : +1 par coordonnees adj; -1 par coordonnees adj base opposee 
		//-positionPrecise : idem mais x2
		//-lastLigne : idem positionCertaine (sert ptt a rien mais on C jamais)
		//-boussole : +1 si bonne; -1 si opposé
		//-superBoussole : parreil mais avec un angle de 178°
		//si pts>0 -> C OK
		int nbPoints = 0;
		
		
		
		//boussole :
		if (Memoire.getBoussole()==Memoire.getLaBonneBase())
			nbPoints++;
		else if (Memoire.getBoussole()==Memoire.getLaMauvaiseBase())
			nbPoints--;
		
		
		//verdicte :
		if (nbPoints>0)
			etreBonneBase = true;
		else
			etreBonneBase = false;
		//------fin system de point--------
		Memoire.setEtreBonneBase(etreBonneBase);
		Memoire.setBoussole(Memoire.getLaBonneBase());
		if (!etreBonneBase)
			inverseBouss();;
	}
	
}