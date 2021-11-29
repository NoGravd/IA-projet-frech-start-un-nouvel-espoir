package IA;

import Le_robot.Central;
import Le_robot.Roues;
import Protocole.Protocoles_noe;

/**
<b>Contient l'aglorithmes Forest_Gump qui pourra etre utilisee lors du tournois</b><p>
Forest Gump : il est simple mais rapide
@author Noe GRAVRAND
*/
public class Forest_Gump {
	
	/**
	 * <b>Strategie la plus basic consiste a chercher un palet puis l'emporter dans la base adverse et tous cela en boucle</b>
	 * @author Noe GRAVRAND
	 */
	public static void main(String[] args) throws InterruptedException {
		Central forest_Gump = new Central();
		Protocoles_noe protocoles = new Protocoles_noe(forest_Gump);
		while (1<2) {//boucle infini tkt frero
			protocoles.goZone2Recherche();
			forest_Gump.capteurs.sonnar();
			Roues.avanceTQpalet(forest_Gump.capteurs, forest_Gump.pinces);
			protocoles.goMarquer();
		}
	}

}