package Protocole;

import java.util.*;
import Le_robot.*;
import lejos.hardware.Button;
import lejos.utility.Delay;

/**
 * <b>IA qui utilise la camera infrarouge</b>
 * @author Hugo APELOIG, Théo JULLIAT
 */
public class LeDavid {
	
	protected int[] adresseDemarrage; //adresse du robot au début du round
	public int[] adresseArrivee; //adresse de dépôt des palais
	private Capteurs c; 
	private EV3Client ev;
	private Pince pince = new Pince(false);
	private Boussole boussole = new Boussole();
	private boolean aDroiteDuPlateau; 
	
	
	
	/**
	 * <b>Constructeur de la class LeDavid</b><p>
	 * Initialise les capteurs et l'ev3client
	 */
	public LeDavid() {
		//Constructeur
		initialiseCapteurC();
		initialiseClient();
	}
	
	public void initialiseCapteurC() {
		//Démarre les capteurs et attend qu'on lui dise que c'est bon
		c = new Capteurs();
		c.capteurCouleurActive();
		c.capteurTactileActive();
		c.demarrerCapteurUltraSon();
		System.out.print("Yo, je suis ready !");
		Button.ENTER.waitForPress();
		
	}
	
	public void initialiseClient() {
		//Creer le client et demande des infos pour savoir
		//ou on se trouve
		System.out.println("Vous etes a droite ou a gauche ?");
		aDroiteDuPlateau = true;
		Button.waitForAnyPress();
		int p = Button.readButtons();
		if(p==Button.ID_LEFT) aDroiteDuPlateau = false;
		
		System.out.println("Vous etes à droite, au centre ou a gauche ?");
		int y = 0; int x = 0; int xArrive=125; int yArrive=0;
		Button.waitForAnyPress();
		p = Button.readButtons();
		if(p==Button.ID_LEFT) x = 150;
		if(p==Button.ID_DOWN) x = 100;
		else if (p==Button.ID_RIGHT) x = 50;
		if(aDroiteDuPlateau) {
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
		
		
		System.out.println("");
		System.out.println("");
		System.out.println("Prêt pour créer client");
		Button.ENTER.waitForPress();
		try {
			Music.Mcdo();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		ev = new EV3Client(adresseDemarrage, marque);
		System.out.println("");
		System.out.println("");
		System.out.println("OK MAN !");
		Button.ENTER.waitForPress();
	}
	
	public void brutusPremierPalais() {
		//Va chercher le premier palais en brut puis calcul à 
		//l'aide de l'infrarouge l'angle et la distance à l'
		//arrivé
		//Prends en compte de quel côté du plateau on se trouve
		
		if(aDroiteDuPlateau) {
			pince.ouverture();
			Roues.rouleDist_aveugle(61);
			pince.fermeture();
			ev.refreshAvecLocalisation(new int[]{50,90});
			int[][] adressesInstant = ev.getAdressesInstantT();
			int[] adresse = ev.adresseLaPlusProche(new int[]{50,90}, adressesInstant);
			int angle = (int) ev.getAngleAdresseToAdresse(adresse, adresseArrivee);
			int distance = ev.getDistanceAdresseToAdresse(adresse, adresseArrivee);
			Roues.pivote(angle);
			Roues.rouleDist_aveugle(distance);
		}
		else {
			pince.ouverture();
			Roues.rouleDist_aveugle(61);
			pince.fermeture();
			ev.refreshAvecLocalisation(new int[]{150,270});
			int[] adresse = ev.adresseLaPlusProche(new int[]{150,270}, ev.getAdressesInstantT());
			int angle = (int) ev.getAngleAdresseToAdresse(adresse, adresseArrivee);
			int distance = ev.getDistanceAdresseToAdresse(adresse, adresseArrivee);
			Roues.pivote(angle);
			Roues.rouleDist_aveugle(distance);
		}
		deposePalais();
		boussole.set(0);
	}
	
	
	
	public int[] leSangDeSesMorts(int[] emplacement) {
		//Prends le palais le plus proche donné par le serveur
		//Calcul l'angle et pivote puis avance de la longueur 
		// -35 centimètre car permettera  de tester si le palais 
		//est bien là

		ev.refreshAvecLocalisation(emplacement); //Refresh le serveur ainsi que ses instances en placant le robot à la bonne adresse etc
		int idcPalaisProche = ev.getIndicePalaisLePlusProcheDuRobot();
		int[] adressePalaisProche = ev.getAdressesInstantT()[idcPalaisProche];
		int angle = (int) ev.getAngleRobotToAdresse(adressePalaisProche);
		Roues.pivote(angle);
		boussole.rotateDeg(angle);
		int distance = ev.getDistanceRobotToAdresse(adressePalaisProche)-35; //-35 pour le test d'après
		Roues.rouleDist_aveugle(distance); //Vérif facteur rouledist et capteurscapte
		return calibrageFaceAuPalais(adressePalaisProche);
		
		
	}
	
	public void retourVictorieux(int[] adresseDuPalaisQueAllaisChercher) {
		//Une fois le palais recupere, retourne à la ligne d'arrive, depose
		//le palais et ensuite verifie si la partie est finis ou non
		
		Roues.pivote(180); //Se replace en direction de l'arrivee
		int distance = ev.getDistanceAdresseToAdresse(adresseDuPalaisQueAllaisChercher,adresseArrivee);
		Roues.rouleDist_onlyBlanc(distance,c); //Roule + verif si ligne blanche
		deposePalais(); 
		ev.refreshAvecLocalisation(adresseDuPalaisQueAllaisChercher); //refresh serveur et attribut de ev
		if(ev.finDePartie()) //Si plus de palais se stop
			ev.getAdresseRobot();
		else	
			leSangDeSesMorts(adresseArrivee); //Sinon va chercher le palais le plus proche
	}
	
	public void deposePalais() {
		//S'utilise une fois arrive à la ligne blanche
		//Ouvre les pince, recule, ferme les pinces (quand le moteur des pinces ne bug pas)
		//Puis se recalibre dos au mur
		
		pince.ouverture();
		Roues.recule();
		pince.fermeture();
		calibrageDemiTour();
	}
	
	public int[] calibrageFaceAuPalais(int[] adresseDuPalaisQueAllaisChercher){
		//Après avoir rouler une certaine distance en direction du palais
		//le robot parcours un angle de 60 degré et s'arrète immédiatement 
		//lorsque la différence entre son avant dernière valeur vue et la
		//dernière est trop grande (il détecte le palais dans ces 60 degrés)
		@SuppressWarnings({ "rawtypes", "unchecked" })
		ArrayList <Float> tab = new ArrayList();
		float[] tab2 = new float[1];
		Roues.pivote(30);
		Delay.msDelay(250);
		int ii=0;
		c.capteurUS.getDistanceMode().fetchSample(tab2, 0);
		tab.add(tab2[0]);
		int ancientSpeed = Roues.moteur_droit.getSpeed();
		Roues.moteur_droit.setSpeed((ancientSpeed*2)/3);
		Roues.moteur_droit.rotate(380,true);
		ii=1;
		boolean bredouille = true;
		while (Roues.moteur_droit.isMoving()) {
			c.capteurUS.getDistanceMode().fetchSample(tab2, 0);
			tab.add(tab2[0]);
			float infinity = Float.POSITIVE_INFINITY;
			if(tab.get(ii-1)-tab.get(ii)>0.20 || (tab.get(ii-1)==infinity && tab.get(ii)!=infinity)) { //2* à verifier car infinity et tester
				bredouille=false;
				Roues.moteur_droit.stop();
			}
			ii++;
		}
		if(bredouille) {
			Roues.pivote(30); //30 pour se recalibrer comme il est venu 
			return retourBredouille(adresseDuPalaisQueAllaisChercher);
		}
		else {
			recupererPalais();
			retourVictorieux(adresseDuPalaisQueAllaisChercher);
			return adresseArrivee;
		}
	}
	
	public void recupererPalais() {
		//Une fois le palais detecte il ouvre ses pinces
		//avance de 35 ou s'arrète avant si touche
		//ferme ses pince puis active le retour a l'arrivee
		pince.ouverture();
		Roues.rouleDistAvecGestionTouche(35,c);
		pince.fermeture();
	}
	
	public int[] retourBredouille(int[] adresseDuPalaisQueAllaisChercher){
		//Dans la situation ou le palais n'a pas ete trouve (il peut 
		//s'agir d'un palais saisi par l'adversaire ou tout simplement
		//que la caméra avait selectionné un adversaire
		//Il refresh en disant que le palais se trouve a l'adresse la 
		//plus proche de celle de l'ancien palais
		
		ev.refreshAvecLocalisation(adresseDuPalaisQueAllaisChercher);
		if(ev.finDePartie()) { //Si c'est finis
			return ev.getAdresseRobot();
		}
		else{
			return leSangDeSesMorts(adresseDuPalaisQueAllaisChercher);
		}
	}
	
	public void calibrageDemiTour() {
		//S'utilise lorsque le robot c'est arrété sur la ligne, après avoir posser le palais
		//Le robot fait demi-tour en se calibrant à l'aide du mur
		
		@SuppressWarnings({ "rawtypes", "unchecked" })
		ArrayList <Float> tab = new ArrayList();
		c.demarrerCapteurUltraSon();
		float[] tab2 = new float [1];
		Roues.moteur_droit.rotate(260,true);
		int ii=0;
		c.capteurUS.getDistanceMode().fetchSample(tab2, 0);
		tab.add(tab2[0]);
		ii++;
		while (Roues.moteur_droit.isMoving()) {
			c.capteurUS.getDistanceMode().fetchSample(tab2, 0);
			tab.add(tab2[0]);
			if(tab.get(ii-1)<tab.get(ii)) break;
			ii++;
		}
		Roues.stop();
		Roues.moteur_droit.rotateTo(-260,true);
		int i=0;
		c.capteurUS.getDistanceMode().fetchSample(tab2, 0);
		tab.add(tab2[0]);
		i++;
		while (Roues.moteur_droit.isMoving()) {
			c.capteurUS.getDistanceMode().fetchSample(tab2, 0);
			tab.add(tab2[0]);
			if(tab.get(i-1)<tab.get(i)) break; 
			i++;
		}
		Roues.stop();
		Roues.demi_tour();
		Roues.stop();
	}
}
