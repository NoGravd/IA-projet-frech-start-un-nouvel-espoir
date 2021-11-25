package Le_robot;

/**
 * Repressente la boussole intern du robot (en degre, in [0;359])
 * Exemples :
 * 0 = direction de depart du robot
 * 90 = le robot et tourner de 90 degre sur la droit par rapport a sa position de depart
 * 270 = idem mais sur la gauche
 * @author noegr
 */
public class Boussole {
	private int boussole;
	
	/**
	 * Initialise la boussole, fixe le 0 sur la position actuelle du robot
	 * @author noegr
	 */
	public Boussole() {
		boussole = 0;
	}
	
	/**
	 * Modifie les valeurs des boussoles car le robot et en train de pivoter de [int degre].
	 * @param degre Le degre de rotation du robot
	 * @author noegr
	 */
	public void rotateDeg (int degre) {
		boussole += degre;
		if (boussole>=360)
			boussole -= 360;
		else if (boussole<=0)
			boussole += 360;
	}
	
	/**
	 * retrourne la valeur de la boussole
	 * @return int (la valeur de la boussole)
	 * @author noegr
	 */
	public int get() {
		return boussole;
	}
	
	/**
	 * modifie la valeur de la boussole
	 * @param int (angle)
	 */
	public void set (int angle){
		boussole=0;
	}
}