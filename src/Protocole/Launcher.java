package Protocole;

public class Launcher {

	public static void main(String[] args) {
		LeDavid david = new LeDavid();
		@SuppressWarnings("unused")
		int[] nouvPosition = david.leSangDeSesMorts(david.adresseDemarrage);
	}

}
