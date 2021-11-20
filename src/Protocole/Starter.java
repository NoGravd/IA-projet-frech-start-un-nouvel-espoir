package Protocole;

/**
Une classe qui decrit les strategies pour le 1er palet
 */
public class Starter {
	
	
	//============================================================================================================INSTANCE
	
	/**
	Une instance qui represente la position de notre robot.
	1 = gauche
	2 = centre
	3 = droite
	 */
	private int positionDeNotreRobot;
	
	/**
	Une instance qui represente la position du robot adverse.
	1 = gauche
	2 = centre
	3 = droite
	 */
	private int positionDuRobotAdverse;
	
	//============================================================================================================CONSTRUCTEUR
	
	/**
	Un construceur qui initalise la position des deux robot au centre
	 */
	public Starter(){		
		
		positionDeNotreRobot = 2;
		positionDuRobotAdverse = 2;
		
	}
	
	/**
	Un construceur qui initalise la position des deux robot.
	@param position de notre robot
	@parem position du robot adverse.
	 */
	public Starter(int notreRobot,int positionAdversaire){
		
		positionDeNotreRobot = notreRobot;
		positionDuRobotAdverse = positionAdversaire;
		
	}
	
	//============================================================================================================METHODE
	
	/**
	Une methode qui permet de recuperer le palet devant nous et de ce decaler a gauche pour ensuite aller marquer.
	 */
	public void marquerGauche(){	
		//A faire	
	}
	
	/**
	Une methode qui permet de recuperer le palet devant nous et de ce decaler a gauche pour ensuite aller marquer.
	 */
	public void marquerDroite(){	
		//A faire
	}
	
	
	//protocole de début de partie (premier palet)
	/**
	Une méthode qui decrit protocole de début de partie (premier palet).
	 */
	public static void Starter1 (String[] args) throws InterruptedException {
		// TODO
		Initiateur_de_sensor.initialiseTout();
		
		
		if((positionDeNotreRobot == 1) && (positionDuRobotAdverse == 1)){ //si les deux robots commence a gauche
			
			
			
		}else if ((positionDeNotreRobot == 2) && (positionDuRobotAdverse == 2)){ //si les deux robots commence au centre
			
			
			
		}else if ((positionDeNotreRobot == 3) && (positionDuRobotAdverse ==3)){ //si les deux robots commence a droite
			
			
			
		}else{		//les deux robot commence a des position differente
			
			
			if((positionDeNotreRobot == 1) && (positionDuRobotAdverse == 2)){ //si notre robot commence a gauche et le leurs au centre
				
				
				
			}else if((positionDeNotreRobot == 3) && (positionDuRobotAdverse == 2)){ //si notre robot commence a droite et le leurs au centre
				
				
				
			}else if((positionDeNotreRobot == 1) && (positionDuRobotAdverse == 3)){ //si notre robot commence a gauche et le leurs au droite
				
				
				
			}else if((positionDeNotreRobot == 2) && (positionDuRobotAdverse == 3)){ //si notre robot commence au centre et le leurs au droite
				
				
				
			}else if((positionDeNotreRobot == 2) && (positionDuRobotAdverse == 1)){ //si notre robot commence au centre et le leurs a gauche
				
				
				
			}else if((positionDeNotreRobot == 1) && (positionDuRobotAdverse == 3)){ //si notre robot commence a gauche et le leurs a droite
				
				
				
			}else{
				
				
				
			}
			
		}

	}

}
