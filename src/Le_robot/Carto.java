package Le_robot;


/**
Une classe qui calculera les positions du david.
@author NG
*/

import lejos.robotics.Color;


public class Carto {
	
	//pas obligatoire mais pratik : (int arbitraires)
	/**
    Instance qui represente l'ID de la couleur blanche.
	 */
	private final static int BLANC = 0;//x1 ou x5
	/**
    Instance qui represente l'ID de la couleur noire.
	 */
	private final static int NOIRE = 1;//x3 ou y2
	/**
    Instance qui represente l'ID de la couleur rouge.
	 */
	private final static int ROUGE = 2;//y3
	/**
    Instance qui represente l'ID de la couleur vert.
	 */
	private final static int VERT = 3;//x2
	/**
    Instance qui represente l'ID de la couleur jaune.
	 */
	private final static int JAUNE = 4;//y1
	/**
    Instance qui represente l'ID de la couleur bleu.
	 */
	private final static int BLEU = 5;//x4
	/**
    Instance qui represente une erreur.
	 */
	private final static int RIEN= 404;//ptt D-ja triee par class Capteur mais a garder pour debug
	
	/**
    Instance qui represente la position NORD.
	 */
	private final static int NORD = 0;
	/**
    Instance qui represente la position EST.
	 */
	private final static int EST = 1;
	/**
    Instance qui represente la position SUD.
	 */
	private final static int SUD = 2;
	/**
    Instance qui represente la position OUEST.
	 */
	private final static int OUEST = 3;
	
	
	/**
    Traite tout ce qu'il y a a traiter dans la Carto quand le robot traverse une ligne.
    @param color ID d'une couleur.
	 */
	public static void travLigne (int color) {
		int couleur = couleurDuInt(color);
		if (couleur == BLANC)
			ligneBlanche();
		else
			horsBase();
		calculPositionC(couleur);
		calculPositionP(couleur);
//		travLigneBouss(couleur);//en faite C useless
		Memoire.setLastLigne(couleur);
	}
	
	private static void calculPositionC (int couleur) {
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
	}
	
	/**
    Modifie la positionPresice dans la memoire en fonction de la ligne que le robot vient de traverser, est appele par travLigne.
    @param couleur ID d'une couleur.
	 */
	private static void calculPositionP (int couleur) {
		//modifie la positionPresice dans la memoire en fonction de la ligne que le robot vient de traverser
		//est appele par travLigne
		
		if (IllogiquePositionP(couleur))
			return;
		int[] newPosition = new int[2];
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
		Memoire.setLastPositionP(Memoire.getPositionPrecise());
		Memoire.setPositionPrecise(newPosition);
	}
	
	private static boolean IllogiquePositionP (int couleur) {
		//si la PositionP est illogique, la réévalu en fonction des deux dernieres lignes
		int[] positionP = Memoire.getPositionPrecise();
		boolean boul = false;
		if (couleur==ROUGE) 
			if (positionP[1]==1||positionP[1]==2)
				boul = true;
		if (couleur==VERT)
			if (positionP[0]!=2&&positionP[0]!=3)
				boul = true;
		if (couleur==JAUNE)
			if (positionP[1]!=1&&positionP[1]!=2)
				boul = true;
		if (couleur==BLEU)
			if (positionP[1]!=4&&positionP[1]!=5)
				boul = true;
		if (couleur==NOIRE)
			if (Memoire.getEtreBase() || (positionP[0]==1 && positionP[1]==0)|| (positionP[0]==1 && positionP[1]==3)|| (positionP[0]==4 && positionP[1]==0)|| (positionP[0]==4 && positionP[1]==3))
				boul = true;
		if (couleur==BLANC)
			if (positionP[0]==2 || positionP[0]==3)
				boul = true;
		
		
		
		if (boul) {
			//TODO
			
			
			
			return true;
		}
		return false;
	}
	
	/**
    Traite les couleurs en int donner par le sensor.
    @param leInt ID d'une couleur.
	 */
	public static int couleurDuInt (int leInt) {
		//traite les couleurs en int donner par le sensor
		
		//version 1 :
		/**
		switch (leInt) {
		case 0 : return ROUGE;
		case 3 : return JAUNE;
		case 7 : return NOIRE;//x : marche pa
		case 6 : return BLANC;
		case 1 : return VERT;//? : bof
		case 8 : return BLEU;//x : marche pa
		default : return RIEN;
		}
		*/
		
		//version 2 :
//		/**
		 switch (leInt) {
		case Color.RED : return ROUGE;
		case Color.YELLOW : return JAUNE;
		case Color.BLACK : return NOIRE;
		case Color.WHITE : return BLANC;
		case Color.GREEN : return VERT;
		case Color.BLUE : return BLEU;
		default : return RIEN;
		}
//		 */
	}
	
	
	
	public static boolean IsIt_mur (int dist) {
		boolean boul = false;
		//TODO
		if (boul)
			Mur(dist);
		return boul;
	}
	
	private static void Mur (int dist) {
		//TODO
	}
	
	
	
	//boussole :
	
	@SuppressWarnings("unused")
	private static void travLigneBouss (int color) {
			//bah en fait je crois qu'il ne se passe rien ici
			//je c pa
			//le nono se remet a parler tout seul !!
	}
	
	/**
	   Rotate de x; doit calculer x en fonction du mur (US).
	   @param color ID d'une couleur.
	 */
	public void corrigeAngleMur (int color) {//couleur = Rouge/Jaune
		if (color!=ROUGE&&color!=JAUNE)
			return;
		//info : la distance entre la ligne et le mur est de 50cm; US = ligne + 4cm; distance entre l'axe de rotation et la ligne + 9cm
		int xx=0;
		//TODO
		
		Roues.pivote(xx);
		if (color==ROUGE) {
			Memoire.setBoussole(NORD);
			Memoire.setSuperBoussole(NORD);
		} else {
			Memoire.setBoussole(SUD);
			Memoire.setSuperBoussole(SUD);
		}
	}
	
	/**
	   Inverse les valeurs de la boussole.
	 */
	public static void inverseBouss() {
		
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
	
	/**
	   La superBoussole corrige la valeur de la boussole, est appele par rotateDeg.
	 */
	private static void superBoussCorrige() {
	
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
	
	/**
	  Modifie les valeurs des boussoles car le robot et en train de pivoter de [int degre].
	  @param degre Le degre de rotation du robot
	 */
	public static void rotateDeg (int degre) {
		
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
	
	/**
	  Dit à la memoire ou le robot se trouve sachant qu'il vient de traverser un ligne blanche, est appele par travLigne.
	 */
	private static void ligneBlanche() {
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
		calculPositionP(BLANC);
	}
	
	/**
	  Dit a la memoire que le robot est hors des base, est appele par travLigne et ligneBlanche.
	 */
	private static void horsBase() {
		
		Memoire.setEtreBase(false);
		Memoire.setEtreBonneBase(false);
	}
	
	/**
	  Determine si le robot est dans la bonne base et donne son rslt dans la Memoire, est appele par ligneBlanche.
	 */
	private static void quelleBase() {
		boolean etreBonneBase;
		//------------------
		//vais faire system de point :
		//-positionCertaine : +1 par coordonnees adj; -1 par coordonnees adj base opposee 
		//-positionPrecise : idem mais x2
		//-lastLigne : idem positionCertaine (sert ptt a rien mais on C jamais)
		//-boussole : +1 si bonne; -1 si opposé
		//-superBoussole : parreil mais avec un angle de 178°
		//si pts>Nos Attentes -> C OK
		//------------------
		int Nos_Attentes = 1;//a modifier  si le robot a tendance a se tromper
		int nbPoints = 0;
		
		//positionsCertaine :
		int[][] positionsC = Memoire.getPositionsCertaine();
		int scorePC=0;
		if (Memoire.getLaBonneBase()==1) {//bonne base a l'EST
			for (int ii=0; ii<positionsC.length; ii++) {
				if (positionsC[ii][0]==5)
					scorePC=1;
				else if (positionsC[ii][0]==2) 
					scorePC=-1;
			}
		} else { //bonne base a l'OUEST
			for (int ii=0; ii<positionsC.length; ii++) {
				if (positionsC[ii][0]==2)
					scorePC=1;
				else if (positionsC[ii][0]==5) 
					scorePC=-1;
			}
		}
		if (scorePC==-1)
			nbPoints--;
		if (scorePC==1)
			nbPoints++;
		
		
		
		//positionPrecise :
		int[] positionsP = Memoire.getPositionPrecise();
		int scorePP=0;
		if (Memoire.getLaBonneBase()==1) {//bonne base a l'EST
			for (int ii=0; ii<positionsP.length; ii++) {
				if (positionsP[0]==5)
					scorePP=1;
				else if (positionsP[0]==2) 
					scorePP=-1;
			}
		} else { //bonne base a l'OUEST
			for (int ii=0; ii<positionsP.length; ii++) {
				if (positionsP[0]==2)
					scorePP=1;
				else if (positionsP[0]==5) 
					scorePP=-1;
			}
		}
		if (scorePP==-1)
			nbPoints--;
		if (scorePP==1)
			nbPoints++;
		
		
		//lastLigne :
		int ligneAdj = Memoire.getLaBonneBase()==1 ? BLEU : VERT; //ligneAdj a la base que l'on souhaite / 1==EST
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
		if (nbPoints>=Nos_Attentes)
			etreBonneBase = true;
		else
			etreBonneBase = false;
		//------fin system de point--------
		Memoire.setEtreBonneBase(etreBonneBase);
		baseBouss(false);
	}
	
	/**
	  Modifie les valeurs de la boussole dans la memoire sachant que le robot rentre dans une base, est appele par ligneBlanche et quelleBase.
	  @param sortir Si le robot est sortie ou non de la base
	 */
	private static void baseBouss (boolean sortir) {
	
		if (!sortir) {
			Memoire.setBoussole(Memoire.getLaBonneBase());
			if (!Memoire.getEreBonneBase())
				inverseBouss();
		}
		if (sortir) {
			Memoire.setBoussole(Memoire.getLaMauvaiseBase());
			if (Memoire.getEreBonneBase())
				inverseBouss();
			Memoire.setEtreBase(false);
			Memoire.setEtreBonneBase(false);
		}
	}
	
}