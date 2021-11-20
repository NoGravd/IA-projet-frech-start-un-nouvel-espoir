package IA;

import Protocole.*;

/**
Une classe qui contient toute les aglorithmes qui pouront etre utilisee lors du tournois.
*/
public class Algo1 {
	
	/**
    Strategie la plus basic consiste a chercher un palet puis l'emporter dans la base
    adverse et tous cela en boucle.
    @param un tableau de String
	 */
	public static void Forest_Gump(String[] args) throws InterruptedException {
		Starter.Starter1(null);
		while (1<2) {//boucle infini tkt frero
			ProtocolesIR.goPaletProche();
			ProtocolesIR.goMarquer();
		}
	}

}