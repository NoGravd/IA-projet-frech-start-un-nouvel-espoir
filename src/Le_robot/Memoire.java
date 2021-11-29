package Le_robot;

/**
<b>Stocke tout ce qu'il a a stocker</b>
@param nbBut : <i>int</i>
@param avoirPalet : <i>boolean</i>
@param etatPince : <i>boolean</i>
@param etreBase : <i>boolean</i>
@param etreBonneBase : <i>boolean</i>
@param laBonneBase : <i>int</i>
@param laMauvaiseBase : <i>int</i>
@param positionsCertaine : <i>int[][]</i>
@param positionPrecise : <i>int[]</i>
@param positionPaletPris : <i>int[][]</i>
@param boussole : <i>int</i>
@param superBoussole : <i>double</i>
@param lastLigne : <i>int</i>
@param lastPositionP : <i>int[]</i>
@param zone2recherche : <i>int[][]</i>
@param grilleIR : <i>int[][]</i>
@param positionIR : <i>int[]</i>
@param positionPaletsIR : <i>int[][]</i>
@param positionAdvIR : <i>int[]</i>
@param nbPointsIR : <i>int</i>

@author Noe GRAVRAND
*/
public class Memoire {
	
	/**
	 * Compteur du nombre de but marque.
	 */
	private int nbBut = 0;
	/**
	 * <b>Donne le nombre de but marquer</b>
	 * @return nbBut : <i>int</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	public int getNbBut() {return nbBut;}
	/**
	 * <b>Increment le nombre de but de 1</b>
	 * @author Noe GRAVRAND
	 */
	public void IncrementeNbBut() {nbBut++;}
	
	
	//----------pince----------------
	
	/**
	 * <b>Dit si le robot a un palet</b>
	 */
	private boolean avoirPalet = false;
	/**
	 * <b>Modifie l'instance avoirPalet</b>
	 * @author Noe GRAVRAND
	 */
	public void setAvoirPalet (boolean boul) {avoirPalet = boul;}
	/**
	 * <b>Dit si le robot possede un palet ou non</b>
	 * @return <b>true</b> si le robot possede un palet, <b>false</b> sinon
	 * 
	 * @author Noe GRAVRAND
	 */
	public boolean getAvoirPalet() {return avoirPalet;}
	
	/**
	 * <b>Dit si le robot a les pince ouvertes</b>
	 */
	private boolean etatPince = false;
	/**
	 * <b>Modifie l'instance etatPince</b>
	 * @author Noe GRAVRAND
	 */
	public void setEtatPince (boolean boul) {etatPince = boul;}
	/**
	 * <b>Dit si les pinces sont ouvertes ou non</b>
	 * @return <b>true</b> si la pince du robot est ouverte, <b>false</b> sinon
	 * 
	 * @author Noe GRAVRAND
	 */
	public boolean getEtatPince() {return etatPince;}
	
	
	
	//------------carto--------------
	
	/**
	 * Dit si le robot est dans une base ou non
	 */
	private boolean etreBase = false;
	/**
	<b>Modifie l'instance etreBase<b>
	@author Noe GRAVRAND
	 */
	public void setEtreBase(boolean boul) {etreBase = boul;}
	/**
	 * <b>Dit si le robot est dans une base ou non</b>
	 * @return <b>true</b> si le robot est dans une base, <b>false</b> sinon
	 * 
	 * @author Noe GRAVRAND
	 */
	public boolean getEtreBase() {return etreBase;}
	
	/**
	 * Dit si le robot est dans la base adverse (là où l'on doit marquer)
	 */
	private boolean etreBonneBase = false;
	/**
	 * <b>Modifie l'instance etreBonneBase</b>
	 * @author Noe GRAVRAND
	 */
	public void setEtreBonneBase (boolean boul) {etreBonneBase = boul;}
	/**
	 * <b>Dit si le robot est dans la base adverse ou non</b>
	 * @return <b>true</b> si le robot est dans une base, <b>false</b> sinon
	 * 
	 * @author Noe GRAVRAND
	 */
	public boolean getEreBonneBase() {return etreBonneBase;}
	
	/**
	 * Represente la direction de la base adverse (où l'on doit marquer)<p>
	 * <ul>1 = EST</ul>
	 * <ul>3 = OUEST</ul>
	 */
	private int laBonneBase;
	/**
	 * <b>Donne la direction de la base adverse</b>
	 * @return laBonneBase : <i>int</i><p>
	 * <ul>1 = EST</ul><ul>3 = OUEST</ul>
	 * 
	 * @author Noe GRAVRAND
	 */
	public int getLaBonneBase() {return laBonneBase;}
	
	/**
	 * <b>Represente la direction de notre base (que l'on doit defendre)</b>
	 * <ul>1 = EST</ul>
	 * <ul>3 = OUEST</ul>
	 */
	private int laMauvaiseBase;
	/**
	 * <b>Donne la direction de notre base</b>
	 * @return laMauvaiseBase : <i>int</i><p>
	 * <ul>1 = EST</ul><ul>3 = OUEST</ul>
	 * 
	 * @author Noe GRAVRAND
	 */
	public int getLaMauvaiseBase() {return laMauvaiseBase;}
	
	/**
	 * Represente l'ensemble de positions certaines de notre robot
	 */
	private int[][] positionsCertaine;
	/**
	 * <b>Modifie l'ensemble de positions certaines du robot</b>
	 * @author Noe GRAVRAND
	 */
	public void setPositionsCertaine (int[][] position) {positionsCertaine = position;}
	/**
	 * <b>Donne l'ensemble de positions certaines du robot</b>
	 * @return positionsCertaine : <i>int[][]</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	public int[][] getPositionsCertaine() {return positionsCertaine;}
	
	/**
	 * Represente la position precise de notre robot
	 */
	private int[] positionPrecise = new int[] {404,404};
	/**
	 * <b>Modifie la position precise du robot</b>
	 * @author Noe GRAVRAND
	 */
	public void setPositionPrecise (int[] position) {positionPrecise = position;}
	/**
	 * <b>Donne la position precise du robot</b>
	 * @return positionPrecise : <i>int[]</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	public int[] getPositionPrecise() {return positionPrecise;}
	
	/**
	 * Represente l'ensemble des positions où des palet ont été pris
	 */
	private int[][] positionPaletsPris;
	/**
	 * <b>Ajoute la position d'un palet pris dans la mémoire du robot</b>
	 * @author Noe GRAVRAND
	 */
	public int[][] newPositionPaletsPris() {return positionPaletsPris;}
	/**
	 * <b>Donne l'ensemble des positions où des palets ont été pris</b>
	 * @return positionPaletsPris : <i>int[][]</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	public int[][] getPositionPaletsPris() {return positionPaletsPris;}
	
	/**
	 * Represente la boussole interne du robot
	 * <ul>0 = NORD</ul>
	 * <ul>1 = EST</ul>
	 * <ul>2 = SUD</ul>
	 * <ul>3 = OUEST</ul>
	 */
	private int boussole; // nord=0; est=1; sud=2; ouest=3
	/**
	 * <b>Modifie la boussole interne du robot</b>
	 * @author Noe GRAVRAND
	 */
	public void setBoussole (int bouss) {boussole = bouss;}
	/**
	 * <b>Donne la direction de regard du robot</b>
	 * @return boussole : <i>int</il><p>
	 * <ul>0 = NORD</ul>
	 * <ul>1 = EST</ul>
	 * <ul>2 = SUD</ul>
	 * <ul>3 = OUEST</ul>
	 * 
	 * @author Noe GRAVRAND
	 */
	public int getBoussole() {return boussole;}
	
	/**
	 * Represente la superboussole du robot, symbolisé de manière continu (en degrès toujours compris entre [0;3560[ )<p>
	 * <ul>0 = NORD</ul>
	 * <ul>90 = EST</ul>
	 * <ul>180 = SUD</ul>
	 * <ul>270 = OUEST</ul>
	 */
	private double superBoussole;
	/**
	 * <b>Modifie la superboussole du robot</b>
	 * @author Noe GRAVRAND
	 */
	public void setSuperBoussole (double superBouss) {superBoussole = superBouss;}
	/**
	 * <b>Donne la direction de regard du robot, en dergès</b>
	 * @return superBoussole : <i>double</i><p>
	 * <ul>0 = NORD</ul>
	 * <ul>90 = EST</ul>
	 * <ul>180 = SUD</ul>
	 * <ul>270 = OUEST</ul>
	 * 
	 * @author Noe GRAVRAND
	 */
	public double getSuperBoussole() {return superBoussole;}
	
	/**
	 * Represente la couleur de la derniere ligne traversé<p>
	 * <ul>0 = BLANC</ul>
	 * <ul>1 = NOIR</ul>
	 * <ul>2 = ROUGE</ul>
	 * <ul>3 = VERT</ul>
	 * <ul>4 = JAUNE</ul>
	 * <ul>5 = BLEU</ul>
	 * <ul>404 = RIEN</ul>
	 */
	private int lastLigne = 404;//ptt changer en un tab? a voir
	/**
	 * <b>Modifier l'instance lastLigne</b>
	 * @author Noe GRAVRAND
	 */
	public void setLastLigne (int coul) {lastLigne = coul;}
	/**
	 * <b>Retourne la couleur de la derniere ligne traversé</b>
	 * @return lastLigne : <i>int</i><p>
	 * <ul>0 = BLANC</ul>
	 * <ul>1 = NOIR</ul>
	 * <ul>2 = ROUGE</ul>
	 * <ul>3 = VERT</ul>
	 * <ul>4 = JAUNE</ul>
	 * <ul>5 = BLEU</ul>
	 * <ul>404 = RIEN</ul>
	 * 
	 * @author NOE GRAVRAND
	 */
	public int getLastLigne() {return lastLigne;}
	
	/**
	 * Represente la position precise precédente, du robot
	 */
	private int[] lastPositionP;
	/**
	 * <b>Modifie l'instance lastPositionP</b>
	 * @author Noe GRAVRAND
	 */
	public void setLastPositionP (int[] position) {lastPositionP = position;}
	/**
	 * <b>Retourne la position precise précedente, du robot</b>
	* @return lastPositionP : <i>int[]</i>
	* 
	* @author Noe GRAVRAND
	 */
	public int[] getLastPositionP() {return lastPositionP;}
	
	/**
	 * Represente l'ensemble des zones où le robot n'a pas déjà récupéré de palets
	 */
	private int[][] zone2Recherche = initialiseZone2Recherche();
	/**
	 * <b>Inialise les differentes de zone de recherche</b>
	 * @author Noe GRAVRAND
	 */
	private int[][] initialiseZone2Recherche() {
		int[][] rslt = new int [16][2];
		for (int xx=1; xx<5; xx++) {
			for (int yy=0; yy<4; yy++) {
				rslt[xx+yy-1][0] = xx;
				rslt[xx+yy-1][1] = yy;
			}
		}
		return rslt;
	}
	/**
	 * <b>Retourne l'ensembles des zone où le robot n'a pas déjà récupéré de palets
	 * @return zone2Recherche : <i>int[][]</i>
	 * 
	 *  @author Noe GRAVRAND
	 */
	public int[][] getZone2Recherche() {return zone2Recherche;}
	
	
	
	//-------IR-----------
	/**
	 * Represente un tableau où est stoker les coordonnee de chaque entité reconnu par la camera IR
	 */
	private int[][] grilleIR;
	/**
	 * <b>Initialise l'instance grilleIR</b>
	 * @param robotAdv : <i>boolean</i><p>
	 * <b>true</b> si robot adverse, <b>false</b> sinon
	 * 
	 * @author Noe GRAVRAND
	 */
	private int[][] initialiseGrilleIR (boolean robotAdv) {
		if (robotAdv)
			return new int [11][2];
		else
			return new int [10][2];
	}
	/**
	 * <b>Parametre l'instance grilleIR</b>
	 * @param grille : <i>int[][]</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	public void setGrilleIR (int[][] grille) {grilleIR = grille;} 
	/**
	 * <b>Retourne l'instance grilleIR</b>
	 * @return grilleIR : <i>int[][]</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	public int[][] getGrilleIR() {return grilleIR;}
	
	/**
	 * Represente dans la position du robot donne par la camera IR
	 */
	private int[] positionIR;
	/**
	 * <b>Modifie l'instance positionIR</b>
	 * @param position : <i>int[]</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	public void setPositionIR (int[] position) {positionIR = position;}
	/**
	 * <b>Retourne l'instance positionIR</b>
	 * @return positionIR : <i>int[]</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	public int[] getPositionIR() {return positionIR;}
	
	/**
	 * Represente dans la position des palets donné par la camera IR
	 */
	private int[][] positionsPaletsIR;
	/**
	 * <b>Modifie l'instance positionsPaletsIR</b>
	 * @param positions : <i>int[][]</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	public void setPositionsPaletsIR (int[][] positions) {positionsPaletsIR = positions;}
	/**
	 * <b>Retourne l'instance positionsPaletsIR</b>
	 * @return positionsPaletsIR : <i>int[][]</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	public int[][] getPositionsPaletsIR() {return positionsPaletsIR;}
	
	/**
	 * Represente dans la position de l'adversaire
	 */
	private int[] positionAdvIR;
	/**
	 * <b>Modifie l'instance positionAdvIR</b>
	 * @param position : <i>int[]</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	public void setPositionAdvIR (int[] position) {positionAdvIR = position;}
	/**
	 * <b>Retourne l'instance positionAdvIR</b>
	 * @return positionAdvIR : <i>int[]</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	public int[] getPositionAdvIR() {return positionAdvIR;}
	
	/**
	 * Represente dans le nombre de point reperer par la camera IR
	 */
	private int nbPointsIR=0;
	/**
	 * <b>Modifie l'instance nbPointsIR</b>
	 * @param nbPointsIR : <i>int</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	public void setNbPointsIR (int nbPt) {nbPointsIR = nbPt;}
	/**
	 <b>Retourne l'instance nbPointsIR</b>
	 * @return nbPointsIR : <i>int</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	public int getnbPoinntsIR() {return nbPointsIR;}
	
	
	
	/**
	 * <b>Constructeur de la class Memoire</b>
	 * @param position : <i>int[]</i> (position de depart du robot)
	 * @param pince : <i>boolean</i> (etat des pinces : <b>true</b> = ouverte; <b>false</b> = fermé)
	 * @param robotAdvIR : <i>boolean</i> (dit si le robot adverse est reperable par la camera IR)
	 * 
	 * @author Noe GRAVRAND
	 */
	public Memoire (int[] position, boolean pince, boolean robotAdvIR) {
		positionsCertaine [0] = position;
		positionPrecise = position;
		etatPince = pince;
		boolean positionOUEST = (position [0] ==1);//robot commence cote gauche
		if (positionOUEST) {
			boussole = 1;//EST
			superBoussole = 1;//EST
			laBonneBase = 1; //EST
			laMauvaiseBase = 3; //OUEST
		} else {
			boussole = 3;//OUEST
			superBoussole = 3;//OUEST
			laBonneBase = 3; //OUEST
			laMauvaiseBase = 1;//EST
		}
		initialiseGrilleIR(robotAdvIR);
	}
	
	/**
	 * <b>Constructeur vide de la class Memoire</b><p>
	 * n'initialise rien
	 * 
	 * @author Noe GRAVRAND
	 */
	public Memoire() {
	}
	
	
	
		//------Carto----------
	
	/**
	 * <b>Traite l'information : le robot vient d'attraper un palet</b><p>
	 * Il augmente la taille du tableau de l'instance positionPaletsPris de 1 et y ajoute la position actuelle du robot<p>
	 * Puis appel la méthode zoneVu
	 * @author Noe GRARVAND
	 */
	public void catchPalet() {
		int[][] tmpr = new int[positionPaletsPris.length+1][2];
		int ii;
		for (ii=0; ii<tmpr.length-1; ii++) {
			tmpr [ii] [0] = positionPaletsPris [ii] [0];
			tmpr [ii] [1] = positionPaletsPris [ii] [1];
		}
		tmpr [ii+1] [0]= positionPrecise [0];
		tmpr [ii+1] [1]= positionPrecise [1];
		zoneVu (positionPrecise);
	}
	
	/**
	 * <b>Traite l'information : le robot a attraper un palet dans tel zone</b><p>
	 * Supprime la-dite zone de l'instance zone2recherche et diminu la taille du tableau de cette même instance
	 * @param position : <i>int[]</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	private void zoneVu (int[] position) {
		if (zone2Recherche.length==0)
			return;
		int[][] tmpr = new int [zone2Recherche.length][2];
		boolean boul=false;
		for (int ii=0; ii<tmpr.length; ii++) {
			if (zone2Recherche[ii]!=position) {
				tmpr [ii][0] = zone2Recherche [ii][0];
				tmpr [ii][1] = zone2Recherche [ii][1];
			} else
				boul=true;
		}
		if (boul) {
			int[][] rslt = new int [tmpr.length-1][2];
			for (int ii=0; ii<rslt.length; ii++) {
				tmpr [ii][0] = zone2Recherche [ii][0];
				tmpr [ii][1] = zone2Recherche [ii][1];
			}
			zone2Recherche = rslt;
		}
	}
	
}