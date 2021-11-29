package Protocole;

/**
 * <b>Lance l'execution de la class LeDavid</b>
 * @author Hugo APELOIG, Theo JULLIAT
 */
public class Launcher {

	public static void main(String[] args) {
		LeDavid david = new LeDavid();
		@SuppressWarnings("unused")
		int[] nouvPosition = david.leSangDeSesMorts(david.adresseDemarrage);
	}

}
