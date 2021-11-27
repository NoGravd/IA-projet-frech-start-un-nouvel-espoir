package Le_robot;

/**
 * <b>Repressente la boussole intern du robot (en degre, entre [0;359])</b><p>
 * <il>Exemples :</il>
 * <ul>0 = direction de depart du robot</ul><p>
 * <ul>90 = le robot et tourner de 90 degre sur la droit par rapport a sa position de depart</ul><p>
 * <ul>270 = idem mais sur la gauche</ul>
 * @param boussole : <i>int</i>
 * 
 * @author Noe GRAVRAND
 */
public class Boussole {
	/**
	 * Represente la valeur de la boussole interne du robot
	 */
	private int boussole;
	
	/**
	 * <b>Initialise la boussole, fixe le 0 sur la position actuelle du robot</b>
	 * @author Noe GRAVRAND
	 */
	public Boussole() {
		boussole = 0;
	}
	
	/**
	 * <b>Modifie les valeurs des boussoles car le robot et en train de pivoter de [int degre]</b>
	 * @param degre : <i>int</i> (degre de rotation du robot)
	 * 
	 * @author Noe GRAVRAND
	 */
	public void rotateDeg (int degre) {
		boussole += degre;
		if (boussole>=360)
			boussole -= 360;
		else if (boussole<=0)
			boussole += 360;
	}
	
	/**
	 * <b>Retrourne la valeur de la boussole</b>
	 * @return boussole : <i>int</i> (la valeur de la boussole)
	 * 
	 * @author Noe GRAVRAND
	 */
	public int get() {
		return boussole;
	}
	
	/**
	 * <b>Modifie la valeur de la boussole</b>
	 * @param angle : <i>int</i>
	 * 
	 * @author Hugo Apeloig
	 */
	public void set (int angle){
		boussole=0;
	}
}