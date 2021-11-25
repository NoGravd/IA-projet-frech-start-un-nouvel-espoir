package Le_robot;

public class Boussole {
	private int boussole;
	
	public Boussole () {
		boussole = 0;
	}
	
	/*
	  Modifie les valeurs des boussoles car le robot et en train de pivoter de [int degre].
	  @param degre Le degre de rotation du robot
	 */
	public void rotateDeg (int degre) {
		boussole += degre;
		if (boussole>=360)
			boussole -= 360;
		else if (boussole<=0)
			boussole += 360;
	}
	
	public int get() {
		return boussole;
	}
	
	public void set(int angle){
		boussole=0;
	}
}