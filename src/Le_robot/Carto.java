package Le_robot;

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
	private final int RIEN= 404;//ptt D-ja triee par class Capteur mais a garder pour debug
	
	private final int NORD = 0;
	private final int EST = 1;
	private final int SUD = 2;
	private final int OUEST = 3;
	
	
	
	public void travLigne (int color) {
		//traite tout ce qu'il y a a traiter dans la Carto quand le robot traverse une ligne
		int couleur = couleurDuInt(color);
		if (couleur == BLANC) {
			ligneBlanche();
		} else
			horsBase();
		if (couleur == NOIRE)
			Memoire.setPositionsCertaine (new int[][] {{1,1},{1,2},{2,0},{2,1},{2,2},{2,3},{3,0},{3,1},{3,2},{3,3},{4,1},{4,2}});
		if (couleur == ROUGE)
			Memoire.setPositionsCertaine (new int[][] {{1,2},{1,3},{2,2},{2,3},{3,2},{3,3},{4,2},{4,3},{5,2},{5,3}});
		if (couleur == VERT)
			Memoire.setPositionsCertaine (new int[][] {{1,1},{3,2},{3,3},{3,4},{2,1},{2,2},{2,3},{2,4}});
		if (couleur == JAUNE)
			Memoire.setPositionsCertaine (new int[][] {{1,0},{1,1},{2,0},{2,1},{3,0},{3,1},{4,0},{4,1},{5,0},{5,1}});
		if (couleur == BLEU)
			Memoire.setPositionsCertaine (new int[][] {{3,1},{3,2},{3,3},{3,4},{4,1},{4,2},{4,3},{4,4}});
		calculPositionP (couleur);
		travLigneBouss(couleur);
		Memoire.setLastLigne(couleur);
	}
	
	
	
	private void calculPositionP (int couleur) {
		//modifie la positionPresice dans la memoire en fonction de la ligne que le robot vient de traverser
		//est appele par travLigne
		int[] newPosition = new int[1];
		if (Memoire.getBoussole() == NORD) {
			newPosition [0] = Memoire.getPositionPrecise() [0];
			newPosition [1] = Memoire.getPositionPrecise() [1] + 1;
		} else if (Memoire.getBoussole() == SUD) {
			newPosition [0] = Memoire.getPositionPrecise() [0];
			newPosition [1] = Memoire.getPositionPrecise() [1] - 1;
		} else if (Memoire.getBoussole() == EST) {
			newPosition [0] = Memoire.getPositionPrecise() [0] + 1;
			newPosition [1] = Memoire.getPositionPrecise() [1];
		} else if (Memoire.getBoussole() == OUEST) {
			newPosition [0] = Memoire.getPositionPrecise() [0] - 1;
			newPosition [1] = Memoire.getPositionPrecise() [1];
		}
		Memoire.setPositionPrecise(newPosition);
	}
	
	
	
	public int couleurDuInt (int leInt) {
		//traite les couleurs en int donner par le sensor
		switch (leInt) {
		case 0 : return ROUGE;
		case 3 : return JAUNE;
		case 7 : return NOIRE;//x TODO : marche pa
		case 6 : return BLANC;
		case 1 : return VERT;//?
		case 8 : return BLEU;//x TODO : marche pa
		default : return RIEN;
		}
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
		//inverse les valeurs de la boussole
		switch(Memoire.getBoussole()) {
		case NORD : Memoire.setBoussole(SUD);
		case EST : Memoire.setBoussole(OUEST);
		case SUD : Memoire.setBoussole(NORD);
		case OUEST : Memoire.setBoussole(EST);
		}
		rotateDeg (180);//superBoussole
	}
	
	public void corrigeAngleLignes() {
		//TODO
		//il faut :
		//-avoir travese 2 lignes diffs
		//-avoir mesurer la distance entre ces 2 traversees
		//-ne pas avoir tourne
		//ici on calculera l'angle entre les 2 lignes et le robot
		
	}
	
	
	
	//superBoussole :
	
	private void superBoussCorrige() {
		//la superBoussole corrige la valeur de la boussole
		//est appele par rotateDeg
		double sb = Memoire.getSuperBoussole(); //pour la visibilite et par flemme de tout recopier 100x
		if (sb>45 && sb<=135)
			Memoire.setBoussole(EST);
		else if (sb>135 && sb<=225)
			Memoire.setBoussole(SUD);
		else if (sb>225 && sb<=315)
			Memoire.setBoussole(OUEST);
		else
			Memoire.setBoussole(NORD);
	}
	
	public void rotateDeg (int degre) {
		//modifie les valeurs des boussoles car le robot et en train de pivoter de [int degre]
		double sb = Memoire.getSuperBoussole();
		sb += degre;
		if (sb>=360)
			sb -= 360;
		else if (sb<=0)
			sb += 360;
		Memoire.setSuperBoussole(sb);
		superBoussCorrige();
	}
	
	
	
	//bases :
	
	private void ligneBlanche() {
		//dit à la memoire ou le robot se trouve sachant qu'il vient de traverser un ligne blanche
		//est appele par travLigne
		if (Memoire.getEtreBase()) {//si on sort de la base
			boolean feuBonneBase = Memoire.getEreBonneBase();
			horsBase();
			int xHorsBonneBase = Memoire.getLaBonneBase() == OUEST ? 1 : 4;
			int xHorsMauvaiseBase = Memoire.getLaMauvaiseBase() == OUEST ? 1 : 4;
			int xBase = feuBonneBase ? xHorsBonneBase : xHorsMauvaiseBase;
			Memoire.setPositionsCertaine(new int[][] {{xBase,0},{xBase,1},{xBase,2},{xBase,3}});
			baseBouss(true);
		} else {//si on rentre dans la base
			Memoire.setEtreBase(true);
			quelleBase();
			if (Memoire.getEreBonneBase()) {//si on est dans la bonne base
				int xBonneBase = Memoire.getLaBonneBase() == OUEST ? 0 : 5;
				Memoire.setPositionsCertaine(new int[][] {{xBonneBase,0},{xBonneBase,1},{xBonneBase,2},{xBonneBase,3}});
			} else {//si on est dans la mauvaise base
				int xMauvaiseBase = Memoire.getLaMauvaiseBase() == OUEST ? 0 : 5;
				Memoire.setPositionsCertaine(new int[][] {{xMauvaiseBase,0},{xMauvaiseBase,1},{xMauvaiseBase,2},{xMauvaiseBase,3}});
			}
		}
	}
	
	private void horsBase() {
		//dit a la memoire que le robot est hors des base
		//est appele par travLigne et ligneBlanche
		Memoire.setEtreBase(false);
		Memoire.setEtreBonneBase(false);
	}
	
	private void quelleBase() {
		//determine si le robot est dans la bonne base et donne son rslt dans la Memoire
		//est appele par ligneBlanche
		boolean etreBonneBase;
		//------------------
		//vais faire system de point :
		//-positionCertaine : +1 par coordonnees adj; -1 par coordonnees adj base opposee 
		//-positionPrecise : idem mais x2
		//-lastLigne : idem positionCertaine (sert ptt a rien mais on C jamais)
		//-boussole : +1 si bonne; -1 si opposé
		//-superBoussole : parreil mais avec un angle de 178°
		//si pts>XX -> C OK
		int xx = 1;//a modifier en fonction de rslts des test + lui trouver un nom TODO
		int nbPoints = 0;
		//TODO
		
		//positionCertaine :
		
		
		
		//positionPrecise :
		
		
		
		//lastLigne :
		int ligneAdj = Memoire.getLaBonneBase()==1 ? BLEU : VERT;
		if (Memoire.getLastLigne() == ligneAdj)
			nbPoints++;
		else if (Memoire.getLastLigne() != NOIRE && Memoire.getLastLigne() != ROUGE && Memoire.getLastLigne() != JAUNE)
			nbPoints--;
		
		
		//boussole :
		if (Memoire.getBoussole()==Memoire.getLaBonneBase())
			nbPoints++;
		else if (Memoire.getBoussole()==Memoire.getLaMauvaiseBase())
			nbPoints--;
		
		
		//verdicte :
		if (nbPoints>=xx)
			etreBonneBase = true;
		else
			etreBonneBase = false;
		//------fin system de point--------
		Memoire.setEtreBonneBase(etreBonneBase);
		baseBouss(false);
	}
	
	private void baseBouss (boolean sortir) {
		//modifie les valeurs de la boussole dans la memoire sachant que le robot rentre dans une base
		//est appele par ligneBlanche et quelleBase
		if (!sortir) {
			Memoire.setBoussole(Memoire.getLaBonneBase());
			if (!Memoire.getEreBonneBase())
				inverseBouss();
		}
		if (sortir) {
			Memoire.setBoussole(Memoire.getLaMauvaiseBase());
			if (Memoire.getEreBonneBase())//TODO : NON ca C fo
				inverseBouss();
		}
	}
	
}