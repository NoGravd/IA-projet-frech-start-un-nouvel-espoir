package Le_robot;

/**
 * <b>Centralise toutes les class du robot</b>
 * @param boussole : <i>Boussole</i>
 * @param capteurs : <i>Capteurs</i>
 * @param carto : <i>Carto</i>
 * @parap ev3Client : <i>EV3Client</i>
 * @param memoire : <i>Memoire</i>
 * @param pinces : <i>Pince</i>
 * @param roues : <i>Roues</i>
 * 
 * @author Noe GRAVRAND
 */
public class Central {
	
	public Boussole boussole;
	
	public Capteurs capteurs;
	
	public Carto carto;
	
	public EV3Client ev3Client;
	
	public Memoire memoire;
	
	public Pince pinces;
	
	public Roues roues;
	
	
	
	/**
	 * <b>Constructeur de <i>Central</i> (initialise toutes les class du robot)</b>
	 * @param adresseDemarrage : <i>int[]</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	public Central(int[] adresseDemarrage) {
		boussole = new Boussole();
		capteurs = new Capteurs();
		carto = new Carto();
		ev3Client = new EV3Client(adresseDemarrage);
		memoire = new Memoire();
		pinces = new Pince();
		roues = new Roues();
	}
	
	/**
	 * <b>Constructeur de <i>Central</i> (initialise toutes les class du robot)</b>
	 * @param adresseDemarrage : <i>int[]</i>
	 * @param pincesOuvertes : <i>boolean</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	public Central(int[] adresseDemarrage, boolean pincesOuvertes) {
		boussole = new Boussole();
		capteurs = new Capteurs();
		carto = new Carto();
		ev3Client = new EV3Client(adresseDemarrage);
		memoire = new Memoire();
		pinces = new Pince(pincesOuvertes);
		roues = new Roues();
	}
	
	/**
	 * <b>Constructeur de <i>Central</i> (initialise toutes les class du robot)</b>
	 * @param pincesOuvertes : <i>boolean</i>
	 * 
	 * @author Noe GRAVRAND
	 */
	public Central(boolean pincesOuvertes) {
		boussole = new Boussole();
		capteurs = new Capteurs();
		carto = new Carto();
		memoire = new Memoire();
		pinces = new Pince(pincesOuvertes);
		roues = new Roues();
	}
	
	/**
	 * <b>Constructeur de <i>Central</i> (initialise toutes les class du robot)</b>
	 * @author Noe GRAVRAND
	 */
	public Central() {
		boussole = new Boussole();
		capteurs = new Capteurs();
		carto = new Carto();
		memoire = new Memoire();
		pinces = new Pince();
		roues = new Roues();
	}
	
}