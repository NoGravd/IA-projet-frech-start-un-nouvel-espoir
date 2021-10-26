package Le_robot;



/**
Une qui detecte la nature, la distance et direction d'un obstacle a l'aide d'un 360° sur sois meme.
*/
public class Detection {
	
	/**
	Une instance qui permet de savoir dans qu'elle direction fais face le robot.
	*/
	private int directionFace = 0;
	/**
	Une instance qui permet de contenir 360 mesure de distance realiser par le capteur ultrason.
	*/
	private float[] distance360 = new float[360];
	
	/**
	Une instance qui permet de savoir le direction de l'obstacle.
	*/
	private int directionObstacle = 0;
	/**
	Une instance qui permet de savoir la distance de l'obstacle.
	*/
	@SuppressWarnings("unused")
	private float distanceObstacle = 0;
	/**
	Une instance qui permet de savoir si l'obstacle est un palet.
	*/
	private boolean obstacleEstPalet = false;
	
	
	/**
	Permet d'obtenir la direction en ° de l'obstacle.
	*/
	public int getDirectionObstacle() {
		return directionObstacle;
	}
	
	/**
	Permet de remettre a  0 le tableau des distances .
	*/
	private void clearTableauDistance() {
		distance360 = new float[360];
	}
	
	/**
	Permet d'avoir une direction toujours inferieur a  360°.
	*/
	private void actualiserDirectionFace() {
		if (directionFace >= 360)
			directionFace -= - 360;
	}
	
	/**
	Fait un tour sur soi meme et prend une mesure a chaque °.
	*/
	public void tourSurSoiMemeDetection() {
		clearTableauDistance();
		Capteur.demarrerCapteurUltraSon();
		for (int ii=0; ii<360; ii++) {
			Roues.mC.rotate(1);
			Capteur.distanceOb();
			distance360[ii] = Capteur.getDistanceOb();
		}
		Capteur.eteindreCapteurUltraSon();
		actualiserDirectionFace();
	}
	
	/**
	Se retounre vers l'obstacle le plus proche.
	*/
	public void tourneVersPlusProche () {
		tourSurSoiMemeDetection();
		float minimum = 3;
		int degre = 0;
		for (int ii=0; ii<360; ii++) {
			if (distance360 [ii]<minimum) {
				minimum = distance360 [ii];
				degre = ii;
			}
		}
		Roues.mC.rotate(degre);
	}
	
	/**
	Calcul si l'obstacle le plus proche est un palet ou non.
	*/
	public void directionDeLobstacle() {			//Changer le nom de la fonction
		float valeurPrec = distance360 [0];
		for (int ii=0; ii<360; ii++) {
			if (valeurPrec > distance360 [ii]) {
				directionObstacle = ii-1;
				distanceObstacle = distance360 [ii];
				if (obstacleEstPalet)
					obstacleEstPalet = true;
				else
					obstacleEstPalet = false;
			}
		}
	}
	
}