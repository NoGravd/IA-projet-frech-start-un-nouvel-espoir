package Protocole;

import Le_robot.*;
import lejos.hardware.Button;
import lejos.robotics.Color;
import lejos.utility.Delay;

/**
 * <b>IA troll qui empêche les adversaires de buter</b><p>
 * Attention ne marche pas contre des codes en dur
 * @author Hugo APELOIG
 */
public class LeChienDeDavid {
	/*L'objectif n'est pas de marqué, mais d'empecher
	 * l'adversaire de marqué, voir le faire marquer contre
	 * son camps
	 */
	@SuppressWarnings("unused")
	private boolean avecInfra = false;
	protected int[] adresseDemarrage;
	protected int[] adresseArrivee; 
	private Capteurs c;
	float[] detections = new float[1];
	boolean detecte = detections[0]==1;
	@SuppressWarnings("unused")
	private Pince pince = new Pince(false);
	@SuppressWarnings("unused")
	private Boussole boussole = new Boussole();
	private boolean aDroiteDuPlateau;
	private int emplacement;
	private int emplacementAdverse;
	EV3Client ev;
	
	public LeChienDeDavid() {
		//Constructeur qui demande les infos de placement
		//(de soi et de l'adversaire) et démarre le capteur
		initialiseCapteurC();
		System.out.println("Vous etes a droite ou a gauche du plateau ?");
		aDroiteDuPlateau = true;
		Button.waitForAnyPress();
		int p = Button.readButtons();
		if(p==Button.ID_LEFT) aDroiteDuPlateau = false;
		
		System.out.println("Voes êtes à droite, au centre ou a gauche ?");
		Button.waitForAnyPress();
		p = Button.readButtons();
		if(p==Button.ID_LEFT) emplacement=0;
		else if(p==Button.ID_DOWN) emplacement=1;
		else if (p==Button.ID_RIGHT) emplacement=2;
		
		System.out.println("L'adversaire est à droite, au centre ou a gauche ?");
		Button.waitForAnyPress();
		p = Button.readButtons();
		if(p==Button.ID_LEFT) emplacementAdverse=0;
		else if(p==Button.ID_DOWN) emplacementAdverse=1;
		else if (p==Button.ID_RIGHT) emplacementAdverse=2;
		
	
		System.out.println("");
		System.out.println("");
		System.out.println("WOOF !");
		Button.ENTER.waitForPress();
	}
	
	public LeChienDeDavid(boolean brutus) {
		//Si brutus est true cela signifie que l'on
		//fait tourner le code brut
		this();
		if(!brutus) {
			initialiseClient();
			avecInfra =true;
		}
	}
	
	public void initialiseClient() {
		//initialise le client (de la même manière que LeDavid
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
		ev = new EV3Client(adresseDemarrage, marque);
		System.out.println("");
		System.out.println("");
		System.out.println("OK MAN !");
		Button.ENTER.waitForPress();
	}
	public void attaque() {
		//L'objectif est de faire boucler se code jusqu'à la
		//fin de la partie :
		//	-Le robot trouve le robot adverse
		//	-il va devant lui (en faisant un arc de cercle)
		//	-Il s'arrète devant le robot adverse et tant qui'
		//		il est devant, il avance tous doucement
		//	-Si il perd le robot adverse de vue, il retourne
		//		à la ligne de départ et recherche à nouveau l'
		//		adversaire
		
		trouveAdversaire();
		//Va devant lui (à coder)
		try {
			attendDeManiereAgressive();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		retourEnSurveillance();
		Roues.rouleDist_aveugle(5);
		
	}
	
	public boolean detecter() {
		//Test si un autre capteur ultra son est detecté
		
		c.capteurUS.getListenMode().fetchSample(detections, 0);
		detecte = detections[0]==1;
		return detecte;
	}
	
	public void retourEnSurveillance() {
		//Recul jusqu'à la ligne blanche
		int[] ancientSpeed = new int[2];
		ancientSpeed[0] = Roues.moteur_droit.getSpeed();
		ancientSpeed[1] = Roues.moteur_gauche.getSpeed();
		reculeChienTantQueNonLigneBlanche(c);
		Roues.moteur_droit.setSpeed(ancientSpeed[0]);
		Roues.moteur_gauche.setSpeed(ancientSpeed[1]);
	}
	
	public static void reculeChienTantQueNonLigneBlanche(Capteurs c) {
		int nAcc = 25; //definition du nb de marches d'accélération
		// int maxSpeed = 400; //vitesse max = 100xVbatterie
		for (int i=0; i<nAcc; i++) {
		if(c.getCouleur()==Color.WHITE) Roues.stop();
		Roues.moteur_droit.setSpeed(Roues.VITESSE_MAX/nAcc*i);//change la vitesse
		Roues.moteur_gauche.setSpeed(Roues.VITESSE_MAX/nAcc*i);
		Roues.moteur_droit.backward();//lance le moteur 
		Roues.moteur_gauche.backward();
		Delay.msDelay(1);// attend 1ms
		}
		Roues.moteur_droit.backward();//lance le moteur 
		Roues.moteur_gauche.backward();
		if(c.getCouleur()==Color.WHITE) Roues.stop();
	}


	
	public void attendDeManiereAgressive() throws InterruptedException {
		//Une fois en face du robot adverse, il avance tous doucement
		//et vérifie que celui-ci soit toujours devant
		int[] ancientSpeed = new int[2];
		ancientSpeed[0] = Roues.moteur_droit.getSpeed();
		ancientSpeed[1] = Roues.moteur_gauche.getSpeed();
		Roues.moteur_droit.setSpeed(1);		
		Roues.moteur_gauche.setSpeed(1);
		avanceChien();
		boolean trouve=detecter();
		int agressivite = 1;
		while(trouve) {
			Music.note(500, 250/agressivite);
			agressivite++;
			System.out.println("WOUF ! WOUF!");
			trouve=detecter();
		}
		Roues.stop();
		Roues.moteur_droit.setSpeed(ancientSpeed[0]);
		Roues.moteur_gauche.setSpeed(ancientSpeed[1]);
		
	}
	private void avanceChien() {
		// TODO 
	}

	public void trouveAdversaire() {
		//Pivote d'un angle de 60 degré (30 degré à droite et 30 à gauche)
		//Durant son pivot, detecte l'adversaire
		int ancientSpeed = Roues.moteur_droit.getSpeed();
		boolean trouve=detecter();
		Roues.pivote(30);
		while(!trouve) {
			Roues.moteur_droit.setSpeed((ancientSpeed*2)/3);
			Roues.moteur_droit.rotate(380,true);
			while (Roues.moteur_droit.isMoving() && !trouve) {
				trouve=detecter();
				if(trouve) Roues.moteur_droit.stop();
				System.out.println(trouve);
			}
			if(trouve) Roues.moteur_droit.stop();
			if(!trouve) {
				Roues.moteur_droit.setSpeed((ancientSpeed*2)/3);
				Roues.moteur_droit.rotate(-380,true);
				while (Roues.moteur_droit.isMoving() && !trouve) {
					trouve=detecter();
					if(trouve) Roues.moteur_droit.stop();
					System.out.println(trouve);
				}
				if(trouve) Roues.moteur_droit.stop();
			}
		}
		Roues.moteur_droit.setSpeed(ancientSpeed);
	}
	public void miseEnPlace() {
		//Lancer lors avant l'attaque, une fois le constructeur fait
		//le robot se place de manière à être en face du robot adverse sur
		//le plateau
		Boolean enPlace=false;
		while(!enPlace) {
			if(emplacement==emplacementAdverse) {
				enPlace=true;
			}
			else if(emplacement==1){
				if(emplacementAdverse==0) {
					Roues.pivote(90);
					Roues.rouleDist_aveugle(50);
					Roues.pivote(-90);
					enPlace=true;
				}
				else {
					Roues.pivote(-90);
					Roues.rouleDist_aveugle(50);
					Roues.pivote(90);
					enPlace=true;
				}
			}
			else if(emplacement==0) {
				if(emplacementAdverse==1) {
					Roues.pivote(90);
					Roues.rouleDist_aveugle(50);
					Roues.pivote(-90);
					enPlace=true;
				}
				else {
					Roues.pivote(90);
					Roues.rouleDist_aveugle(100);
					Roues.pivote(-90);
					enPlace=true;
				}
			}
			else if(emplacement==2) {
				if(emplacementAdverse==1) {
					Roues.pivote(-90);
					Roues.rouleDist_aveugle(50);
					Roues.pivote(90);
					enPlace=true;
				}
				else {
					Roues.pivote(-90);
					Roues.rouleDist_aveugle(100);
					Roues.pivote(90);
					enPlace=true;
				}
			}
		}
	}
	
	public void initialiseCapteurC() {
		//Même que david
		c = new Capteurs();
		c.capteurCouleurActive();
		c.capteurTactileActive();
//		c.demarrerCapteurUltraSon().fetchSample(detections, 0);//TODO réparer
		System.out.print("Yo, je suis ready !");
		Button.ENTER.waitForPress();
		
	}
	
	public void attaqueBrutus() {
		//Comme attque mais en brut (utile simplement pour tester si il n'y 
		//a pas d'erreur
		Roues.rouleDist_aveugle(5);
		boolean aDroite = trouveAdversaireBrutus();
		deplacementBrutus(aDroite);
		try {
			attendDeManiereAgressiveBrutus();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		retourEnSurveillanceBrutus();
	}
	
	
	public void retourEnSurveillanceBrutus() {
		//même que retourEnSurveillance
		int[] ancientSpeed = new int[2];
		ancientSpeed[0] = Roues.moteur_droit.getSpeed();
		ancientSpeed[1] = Roues.moteur_gauche.getSpeed();
		reculeChienTantQueNonLigneBlanche(c);
		Roues.moteur_droit.setSpeed(ancientSpeed[0]);
		Roues.moteur_gauche.setSpeed(ancientSpeed[1]);
	}
	
	public void deplacementBrutus(boolean aDroite) {
		//Le robot se deplace de 25 centimetre du côté
		//ou le robot adverse a été trouvé
		if(aDroite) 
			Roues.pivote(60);
		else
			Roues.pivote(-60);
		Roues.rouleDist_aveugle(25);
		if(aDroite) 
			Roues.pivote(-90);
		else
			Roues.pivote(90);
	}
	public void attendDeManiereAgressiveBrutus() throws InterruptedException {
		//Attend, mais brut donc ne dure qu'un temps avant de 
		//rescanner
		int[] ancientSpeed = new int[2];
		ancientSpeed[0] = Roues.moteur_droit.getSpeed();
		ancientSpeed[1] = Roues.moteur_gauche.getSpeed();
		Roues.moteur_droit.setSpeed(1);		
		Roues.moteur_gauche.setSpeed(1);
		avanceChien();
		int agressivite = 1;
		while(agressivite<10) {
			Music.note(500, 250/agressivite);
			agressivite++;
			System.out.println("WOUF ! WOUF!");
		}
		Roues.stop();
		Roues.moteur_droit.setSpeed(ancientSpeed[0]);
		Roues.moteur_gauche.setSpeed(ancientSpeed[1]);
		
	}
	public boolean trouveAdversaireBrutus() {
		//Même que trouveAdversaire
		boolean aDroite = true;
		int ancientSpeed = Roues.moteur_droit.getSpeed();
		boolean trouve=detecter();
		Roues.pivote(30);
		while(!trouve) {
			Roues.moteur_droit.setSpeed((ancientSpeed*2)/3);
			Roues.moteur_droit.rotate(190,false);	
			Roues.moteur_droit.rotate(190,true);
			while (Roues.moteur_droit.isMoving() && !trouve) {
				trouve=detecter();
				if(trouve) aDroite=false;
				System.out.println(trouve);
			}
			if(trouve) Roues.moteur_droit.stop();
			if(!trouve) {
				Roues.moteur_droit.setSpeed((ancientSpeed*2)/3);
				Roues.moteur_droit.rotate(-190,false);	
				Roues.moteur_droit.rotate(-190,true);
				while (Roues.moteur_droit.isMoving() && !trouve) {
					trouve=detecter();
					if(trouve) aDroite=true;
					System.out.println(trouve);
				}
				if(trouve) Roues.moteur_droit.stop();
			}
		}
		Roues.moteur_droit.setSpeed(ancientSpeed);
		return aDroite;
	}
	
	
	public void attaqueAvecOeuilDeDieu() {
		//Une version de l'attaque utilisant l'infraRouge
		trouveAdversaireAvecOeuilDeDieu();
		//Va devant lui 
		try {
			attendDeManiereAgressiveAvecOeuilDeDieu();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		retourEnSurveillanceAvecOeuilDeDieu();
		Roues.rouleDist_aveugle(5);
		
	}
	
	public boolean detecterAvecOeuilDeDieu() {
		c.capteurUS.getListenMode().fetchSample(detections, 0);
		detecte = detections[0]==1;
		return detecte;
	}
	
	public void retourEnSurveillanceAvecOeuilDeDieu() {
		int[] ancientSpeed = new int[2];
		ancientSpeed[0] = Roues.moteur_droit.getSpeed();
		ancientSpeed[1] = Roues.moteur_gauche.getSpeed();
		reculeChienTantQueNonLigneBlanche(c);
		Roues.moteur_droit.setSpeed(ancientSpeed[0]);
		Roues.moteur_gauche.setSpeed(ancientSpeed[1]);
	}
	
	public void attendDeManiereAgressiveAvecOeuilDeDieu() throws InterruptedException {
		int[] ancientSpeed = new int[2];
		ancientSpeed[0] = Roues.moteur_droit.getSpeed();
		ancientSpeed[1] = Roues.moteur_gauche.getSpeed();
		Roues.moteur_droit.setSpeed(1);		
		Roues.moteur_gauche.setSpeed(1);
		avanceChien();
		boolean trouve=detecter();
		int agressivite = 1;
		while(trouve) {
			Music.note(500, 250/agressivite);
			agressivite++;
			System.out.println("WOUF ! WOUF!");
			trouve=detecter();
		}
		Roues.stop();
		Roues.moteur_droit.setSpeed(ancientSpeed[0]);
		Roues.moteur_gauche.setSpeed(ancientSpeed[1]);
		
	}
	public void trouveAdversaireAvecOeuilDeDieu() {
		//On refresh deux fois avec un interval et on compare les deux tab de donnés
		//On test pour chcune des adresses de tab2 si sont equivalente existe dans tab1
		//On prends ensuite l'adresse qui n'existe pas dans tab1
		//On se deplace de son x et dès qu'on est en face on le MASSACRE !!!
	
	}
}
