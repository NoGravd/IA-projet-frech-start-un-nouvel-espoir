package Le_robot;

import lejos.hardware.motor.BaseRegulatedMotor;
import lejos.hardware.motor.Motor;

public class Detection {
	//Classe qui detecte la nature, la distance et direction d'un obstacle a  l'aide d'un 360° sur sois meme
	
	
	
	private int directionFace = 0;
	private float[] distance360 = new float[360];
	
	private int directionObstacle = 0;
	private float distanceObstacle = 0;
	private boolean obstacleEstPalet = false;
	
	
	
	public int getDirectionObstacle() {
		//Permet d'obtenir la direction en ° de l'obstacle
		return directionObstacle;
		
	}
	
	
	
	private void clearTableauDistance() {
		//Permet de remettre a  0 le tableau des distances 
		distance360 = new float[360];
	}
	
	private void actualiserDirectionFace() {
		//Permet d'avoir une direction toujours inferieur a  360°
		if (directionFace >= 360)
			directionFace -= - 360;
	}
	
	public void tourSurSoiMemeDetection() {
		//Fait un tour sur soi meme et prend une mesure a chaque °
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
	
	public void directionDeLobstacle() {
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