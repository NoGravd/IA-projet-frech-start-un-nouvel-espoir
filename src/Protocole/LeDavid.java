package Protocole;
import Le_robot.*;
import lejos.hardware.Button;

/**
 * Centralise toute les class du robot, et fait des methode utilisable
 * @param adresseDemarrage : <i>int[]</i> (coordonnees de depart du robot)
 * @param adresseArrivee : <i>int[]</i> (coordonnees... TODO)
 * 
 * @author Hugo Apeloig, Theo Julliat
 */
public class LeDavid {
	protected int[] adresseDemarrage;
	protected int[] adresseArrivee; 
	private Capteurs capteurs;
	private EV3Client ev;
	private Pince pince = new Pince();
	private Boussole boussole = new Boussole();
	
	
	
	/**
	 * <b>Initialise tous ce qu'il y a a initialiser : capteur, ev3client</b>
	 * @author Hugo Apeloig, Theo Julliat
	 */
	public LeDavid() {
		initialiseCapteurC();
		initialiseClient();
	}
	
	/**
	 * <b>Initialise tout les capteurs et attend la press du bouton pour terminer</b>
	 * @author Hugo Apeloig, Theo Julliat
	 */
	public void initialiseCapteurC() {
		capteurs = new Capteurs();
		capteurs.capteurCouleurActive();
		capteurs.capteurTactileActive();
		capteurs.demarrerCapteurUltraSon();
		System.out.print("Yo, je suis ready !");
		Button.ENTER.waitForPress();
		
	}
	
	/**
	 * <b>Initialise ev3client :</b><p>
	 * Demande via ecran et boutons quel est l'emplacement du robot se trouve ainsi que celui du robot adverse</b>
	 * @author Hugo Apeloig, Theo Julliat
	 */
	public void initialiseClient() {
		System.out.println("Vous etes a droite ou a gauche ?");
		boolean droitePlateau = true;
		Button.waitForAnyPress();
		int p = Button.readButtons();
		if(p==Button.ID_LEFT) droitePlateau = false;
		
		System.out.println("Vous etes � droite, au centre ou a gauche ?");
		int y = 0; int x = 0; int xArrive=125; int yArrive=0;
		Button.waitForAnyPress();
		p = Button.readButtons();
		if(p==Button.ID_LEFT) x = 150;
		if(p==Button.ID_DOWN) x = 100;
		else if (p==Button.ID_RIGHT) x = 50;
		if(droitePlateau) {
			y=30;
			yArrive=270;
		}
		else {
			y= 270;
			yArrive=30;
		}
		adresseDemarrage = new int[2];
		adresseDemarrage[0] = x;
		adresseDemarrage[1] = y;
		adresseArrivee = new int[2];
		adresseArrivee[0] = xArrive;
		adresseArrivee[1] = yArrive;
	
		@SuppressWarnings("unused")
		boolean marque=false;
		System.out.println("L'adversaire est-il marque");
		System.out.println("Gauche pour true (n'importe pour else)");
		Button.waitForAnyPress();
		p = Button.readButtons();
		if(p==Button.ID_LEFT) 
			marque = true;
		else 
			marque=false;
		ev = new EV3Client(new int[]{x,y}, false);
		
		System.out.println("");
		System.out.println("");
		System.out.println("OK MAN !");
		Button.ENTER.waitForPress();
	}
	
	
	
	/**
	 * TODO
	 * @param emplacement : <i>int[]</i>
	 * @return <i>int[]</i> : TODO
	 * 
	 * @author Hugo Apeloig, Theo Julliat
	 */
	public int[] leSangDeSesMorts(int[] emplacement) {
		//boussole.getEmplacement();
		ev.refreshAvecLocalisation(emplacement);
		int idcPalaisProche = ev.getIndicePalaisLePlusProcheDuRobot();
		int[] adressePalaisProche = ev.getAdressesInstantT()[idcPalaisProche];
		int angle = (int) ev.getAngleRobotToAdresse(adressePalaisProche);
		Roues.pivote(angle);
		boussole.rotateDeg(angle);
		int distance = ev.getDistanceRobotToAdresse(adressePalaisProche)-35;
		Roues.rouleDist_onlyBlanc(distance, capteurs);//V�rif facteur rouledist et capteurscapte
		return calibrageFaceAuPalais(adressePalaisProche);
		
		
	}
	
	/**
	 * <il><b>Se deplace jusqu a la base adv :</b></il>
	 * <ul>Tourne sur lui meme direction base adv</ul>
	 * <ul>Avance tout droit jusque ligne Blanche</ul>
	 * @param adresseDuPalaisQueAllaisChercher : <i>int[]</i>
	 * 
	 * @author Hugo Apeloig, Theo Julliat
	 */
	public void retourVictorieux(int[] adresseDuPalaisQueAllaisChercher) {
		ev.refreshAvecLocalisation(adresseDuPalaisQueAllaisChercher);
		int angle = (int) ev.getAngleRobotToAdresse(adresseArrivee);
		Roues.pivote(angle);
		boussole.rotateDeg(angle);
		int distance = ev.getDistanceRobotToAdresse(adresseArrivee)-35;
		Roues.rouleDist_onlyBlanc(distance, capteurs);//TODO : que faire si robot adv sur trajectoire ?
		deposePalais();
	}
	
	/**
	 * <b>Ouvre les pince, se recule, referme les pince, fait un demi tour</b>
	 * @author Hugo Apeloig, Theo Julliat
	 */
	public void deposePalais() {
		pince.ouverture_music();
		Roues.recule();
		pince.fermeture();
		calibrageDemiTour();
	}
	
	/**
	 * TODO
	 * @param adresseDuPalaisQueAllaisChercher : <i>int[]</i>
	 * @return <i>int[]</i> : TODO
	 * 
	 * @author Hugo Apeloig, Theo Julliat
	 */
	public int[] calibrageFaceAuPalais(int[] adresseDuPalaisQueAllaisChercher){
		//Apr�s avoir rouler une certaine distance en direction du palais
		//le robot parcours un angle de 60 degr� et s'arr�te imm�diatement 
		//lorsuqe la diff�rence entre son avant derni�re valeur vue et la
		//derni�re est trop grande (il d�tecte le palais dans ces 60 degr�s)
		float[] tab = new float [2];
		Roues.moteur_droit.rotateTo(-130, false); //1560 = un tour complet
		Roues.moteur_droit.rotateTo(260,true);
		int ii=0;
		boolean bredouille = true;
		while (Roues.moteur_droit.isMoving()) {
			capteurs.capteurUS.getDistanceMode().fetchSample(tab, ii);
			if(tab[ii-1]>2*tab[ii]) { //2* � verifier car infinity et tester
				bredouille=false;
				break; 
			}
			ii++;
		}
		if(bredouille) return retourBredouille(adresseDuPalaisQueAllaisChercher);
		else {
			recupererPalais();
			retourVictorieux(adresseDuPalaisQueAllaisChercher);
			return adresseArrivee;
		}
	}
	
	public void recupererPalais() {
		pince.ouverture();
		Roues.rouleDist_onlyBlanc(35, capteurs);
		pince.fermeture();
		Roues.pivote(-boussole.get());
		boussole.set(0);
	}
	public int[] retourBredouille(int[] adresseDuPalaisQueAllaisChercher){
		//FAIRE UN PIVOTE POUR QUE L'ANGLE DE CARTO REVIENNE A ZERO
		ev.refreshAdressesInstantT();
		if(ev.finDePartie()) {
			return ev.getAdresseRobot();
			//Robot.launcheMusiqueFin(); //Checker si on a win ou non
		}
		else{
			Roues.moteur_droit.rotateTo(-130, false);
			Roues.pivote(-boussole.get());
			boussole.set(0);
			int[] adresseRobot = ev.adresseLaPlusProche(adresseDuPalaisQueAllaisChercher);
			ev.refreshAvecLocalisation(adresseRobot);
			return ev.getAdresseRobot();
			//Robot.goTo(); //Une fonction qui regroupe tous et qui va au palais et le ram�ne
						  //retourBredouille est donc dans goTo();
		}
	}
	public void calibrageDemiTour() {
		//S'utilise lorsque le robot c'est arr�t� sur la ligne, apr�s avoir posser le palais
		capteurs.demarrerCapteurUltraSon();
		float[] tab = new float [1000000];
		Roues.moteur_droit.rotateTo(260,true);
		int ii=0;
		while (Roues.moteur_droit.isMoving()) {
			capteurs.capteurUS.getDistanceMode().fetchSample(tab, ii);
			if(tab[ii]<tab[ii-1]) break; 
			ii++;
		}
		Roues.stop();
		Roues.moteur_droit.rotateTo(-260,true);
		int i=0;
		while (Roues.moteur_droit.isMoving()) {
			capteurs.capteurUS.getDistanceMode().fetchSample(tab, i);
			if(tab[i]<tab[ii-1]) break; 
			i++;
		}
		Roues.stop();
		Roues.demi_tour();
		Roues.stop();
	}
	
}