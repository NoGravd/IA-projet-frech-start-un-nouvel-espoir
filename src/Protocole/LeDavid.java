package Protocole;
import Le_robot.*;
import lejos.hardware.Button;

public class LeDavid {
	protected int[] adresseDemarrage;
	protected int[] adresseArrivee; 
	private Capteur c;
	private EV3Client ev;
	private Pince pince = new Pince();
	private Boussole boussole = new Boussole();
	
	public LeDavid() {
		initialiseCapteurC();
		initialiseClient();
		//initialiseBoussole();
	}
	public void initialiseCapteurC() {
		c = new Capteur();
		c.capteurCouleurActive();
		c.capteurTactileActive();
		c.demarrerCapteurUltraSon();
		System.out.print("Yo, je suis ready !");
		Button.ENTER.waitForPress();
		
	}
	public void initialiseClient() {
		System.out.println("Vous etes a droite ou a gauche ?");
		boolean droitePlateau = true;
		Button.waitForAnyPress();
		int p = Button.readButtons();
		if(p==Button.ID_LEFT) droitePlateau = false;
		
		System.out.println("Vous etes à droite, au centre ou a gauche ?");
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
		
		boolean marque=false;
		System.out.println("L'adversaire est-il marque");
		System.out.println("Gauche pour true (n'importe pour else)");
		Button.waitForAnyPress();
		p = Button.readButtons();
		if(p==Button.ID_LEFT) marque = true;
		else marque=false;
		//ev = new EV3Client(new int[]{x,y}, false);
		
		System.out.println("");
		System.out.println("");
		System.out.println("OK MAN !");
		Button.ENTER.waitForPress();
	}
	
	public int[] leSangDeSesMorts(int[] emplacement) {
		//boussole.getEmplacement();
		ev.refreshAvecLocalisation(emplacement);
		int idcPalaisProche = ev.getIndicePalaisLePlusProcheDuRobot();
		int[] adressePalaisProche = ev.getAdressesInstantT()[idcPalaisProche];
		int angle = (int) ev.getAngleRobotToAdresse(adressePalaisProche);
		Roues.pivote(angle);
		boussole.rotateDeg(angle);
		int distance = ev.getDistanceRobotToAdresse(adressePalaisProche)-35;
		Roues.rouleDist(distance, c); //Vérif facteur rouledist et capteurscapte
		return calibrageFaceAuPalais(adressePalaisProche);
		
		
	}
	
	public void retourVictorieux(int[] adresseDuPalaisQueAllaisChercher) {
		ev.refreshAvecLocalisation(adresseDuPalaisQueAllaisChercher);
		int angle = (int) ev.getAngleRobotToAdresse(adresseArrivee);
		Roues.pivote(angle);
		boussole.rotateDeg(angle);
		int distance = ev.getDistanceRobotToAdresse(adresseArrivee)-35;
		Roues.rouleDist(distance, c);	
		deposePalais();
	}
	
	public void deposePalais() {
		Pince.ouverture_music();
		Roues.recule();
		pince.fermeture(c);
		calibrageDemiTour();
	}
	public int[] calibrageFaceAuPalais(int[] adresseDuPalaisQueAllaisChercher){
		//Après avoir rouler une certaine distance en direction du palais
		//le robot parcours un angle de 60 degré et s'arrète immédiatement 
		//lorsuqe la différence entre son avant dernière valeur vue et la
		//dernière est trop grande (il détecte le palais dans ces 60 degrés)
		float[] tab = new float [2];
		Roues.moteur_droit.rotateTo(-130, false); //1560 = un tour complet
		Roues.moteur_droit.rotateTo(260,true);
		int ii=0;
		boolean bredouille = true;
		while (Roues.moteur_droit.isMoving()) {
			c.capteurUS.getDistanceMode().fetchSample(tab, ii);
			if(tab[ii-1]>2*tab[ii]) { //2* à verifier car infinity et tester
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
		Roues.rouleDist(35, c);
		pince.fermeture(c);
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
			//Robot.goTo(); //Une fonction qui regroupe tous et qui va au palais et le ramène
						  //retourBredouille est donc dans goTo();
		}
	}
	public void calibrageDemiTour() {
		//S'utilise lorsque le robot c'est arrété sur la ligne, après avoir posser le palais
		c.demarrerCapteurUltraSon();
		float[] tab = new float [1000000];
		Roues.moteur_droit.rotateTo(260,true);
		int ii=0;
		while (Roues.moteur_droit.isMoving()) {
			c.capteurUS.getDistanceMode().fetchSample(tab, ii);
			if(tab[ii]<tab[ii-1]) break; 
			ii++;
		}
		Roues.stop();
		Roues.moteur_droit.rotateTo(-260,true);
		int i=0;
		while (Roues.moteur_droit.isMoving()) {
			c.capteurUS.getDistanceMode().fetchSample(tab, i);
			if(tab[i]<tab[ii-1]) break; 
			i++;
		}
		Roues.stop();
		Roues.demi_tour();
		Roues.stop();
	}
	//public void initialiseBoussole() {}
}
