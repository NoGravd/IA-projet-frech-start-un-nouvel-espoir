package Le_robot;

import java.util.Arrays;

import Le_robot.Roues;
import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.Motor;
import lejos.utility.Delay;
	//SERT DE TEST MAIS CONCRETEMENT FONCTIONNERAIS COMME ROUES
public class FonctionsTests {
	/**
    Instance qui represente le moteur controler par la roue droite.
	 */
	static final BaseRegulatedMotor moteur_droit = Motor.A;//roue droite
	/**
    Instance qui represente le moteur controler par la roue gauche.
	 */
	static final BaseRegulatedMotor moteur_gauche = Motor.C;//roue gauche
	/**
    Instance qui represente la liste des moteurs a syncrhoniser avec le moteur A pour avancer et reculer.
	 */
	static final BaseRegulatedMotor[] l = new BaseRegulatedMotor[] {moteur_gauche};
	/**
    Instance qui represente la vitesse max du robot.
	 */
	static final int VITESSE_MAX = (int) moteur_gauche.getMaxSpeed();
	EV3Client ev = new EV3Client(new int[] {50,30},true);
	public void calibrageFaceAuPalais(){
		//Après avoir rouler une certaine distance en direction du palais
		//le robot parcours un angle de 60 degré et s'arrète immédiatement 
		//lorsuqe la différence entre son avant dernière valeur vue et la
		//dernière est trop grande (il détecte le palais dans ces 60 degrés)
		Capteur c = new Capteur();
		c.demarrerCapteurUltraSon();
		float[] tab = new float [1000000];
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
		if(bredouille) retourBredouille();
		else {
			recupererPalais();
			retourVictorieux();
		}
	}
	
	public void retourBredouille(int[] adresseDuPalaisQueAllaisChercher){
		//FAIRE UN PIVOTE POUR QUE L'ANGLE DE CARTO REVIENNE A ZERO
		ev.refreshAdressesInstantT();
		if(ev.finDePartie()) {
			Robot.launcheMusiqueFin(); //Checker si on a win ou non
		}
		else{
			int[] adresseRobot = ev.adresseLaPlusProche(adresseDuPalaisQueAllaisChercher);
		
			ev.refreshAvecLocalisation(adresseRobot);
			Robot.goTo(); //Une fonction qui regroupe tous et qui va au palais et le ramène
						  //retourBredouille est donc dans goTo();
		}
	}
	
	public void recupererPalais() {
		//ouvre les pinces, avance puis si capteurTouche == true
		//On ferme les pinces
	}
	
	public void retourVictorieux() {
		//FAIRE UN PIVOTE POUR QUE L'ANGLE DE CARTO REVIENNE A ZERO (en direction de l'arrivé)
		//Faire rouler jusqu'à la ligne d'arrivé, drop, puis demi tour et refresh
		//et nouveau tour dans la boucle while ev.finDePartie()
	
	}
	
	public void calibrageDemiTour() {
		//S'utilise lorsque le robot c'est arrété sur la ligne, après avoir posser le palais
		Capteur c = new Capteur();
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
	}
	
	
	
}
