package Le_robot;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Arrays;

import lejos.hardware.Button;

/**
 * <b>Gere tout se qu'il y a gerer par rapport a la camera infrarouge</b>
 * @param adresseRobot : <i>int[]</i> (coordonnee du robot)
 * @param indiceAdresseRobot : <i>int</i> (TODO g pa compris)
 * @param adresseInstantT : <i>int[][]</i> (TODO idem)
 * @param nbPalais : <i>int</i> (nombre de palet sur le plateau)
 * @param autreJoueurMarque : <i>boolean</i> (TODO je C pa)
 * @param palaisALeurPlace : <i>int[][]</i> (coordonee de base des palets)
 * @param adresseDeDemarrage : <i>int[]</i> (coordonne de demarage du robot)
 * @author Hugo APELOIG, Theo JULLIAT
 */
public class EV3Client {
	
	/**
	 * Adresse du robot à l'instant du dernier Refresh (ou init)
	 */
	private int[]adresseRobot;
	/**
	 * Indice de l'adresse du robot au dernier refresh (ou init)
	 */
	private int indiceAdresseRobot; //
	/**
	 * Toute les adresse sous forme [x,y,x1,y1,x2,y2,etc.] (au dernier refresh)
	 */
	private int[][] adressesInstantT;
	/**
	 * nombre de palais sur la plateau au dernier refresh
	 */
	private int nbPalais;
	/**
	 * Dit si le robot adverse est reperable par la camera IR
	 */
	private boolean autreJoueurMarque;
	/**
	 * Tableau représentant les coordonnées des palets au début de la partie
	 */
	private int[] palaisALeurPlace;
	/**
	 * Une valeur FIXE qui est simplement l'adresse du démarrage
	 */
	private int[] adresseDeDemarrage;
	
	
	
	/**
	 * <b>Constructeur de la class EV3Client</b><p>
	 * Initialise en prenant une adresse de démarrage (50,30 pour en bas à gauche par exemple)
	 * @param adresseDemarrage
	 * @param autreJoueurMarque : si le robot avdverse est repérable par la camera IR
	 */
	public EV3Client(int[] adresseDemarrage, boolean autreJoueurMarque) {
		this(adresseDemarrage);
		this.autreJoueurMarque=autreJoueurMarque;
		if(autreJoueurMarque) nbPalais = (adressesInstantT.length)-2; //Si il y a un autre joueur, alors le nb de palais c'est le nb total -2
		else nbPalais = (adressesInstantT.length)-1; //sinon on enlève uniquement notre robot 
		palaisALeurPlace = palaisPossible(adressesInstantT);
	}
	
	/**
	 * <b>Constructeur de la class EV3Client</b><p>
	 * Initialise les adresses et l'indice du robot
	 * @param adresseDemarrage
	 */
	public EV3Client(int[] adresseDemarrage) {
		int[][] adresses = getAdresses();
		int[] ad = adresseLaPlusProcheTotale(adresseDemarrage, adresses);
		int[] adresseSimple = {ad[1],ad[2]}; //L'adresse la plus proche parmis toute celle du tableau
		adresseRobot = adresseSimple; 
		adresseDeDemarrage=adresseRobot;
		indiceAdresseRobot = ad[0]; //L'indice de cette même adresse
		adressesInstantT=adresses; //L'intégralité des adresses à l'instant T
	}
	
	
	
	/**
	 * <b>Rafraichie la liste des adresses (mais ne mets pas à jour l'adresse du robot)</b>
	 */
	public void refreshAdressesInstantT() {
		adressesInstantT=getAdresses();
	}
	
	/**
	 * <b>Rafraichie l'intégralité des attribut du Client</b>
	 * @param localisation : coordonnée du robot
	 */
	public void refreshAvecLocalisation(int[] localisation) {
		System.out.println("La suite :");
		int[][] adressesActuelles = getAdresses();
		for(int i=0; i<adressesActuelles.length; i++) {
			System.out.println(Arrays.toString(adressesActuelles[i]));
		}
		Button.ENTER.waitForPress();
		int[] ad = adresseLaPlusProcheTotale(localisation, adressesActuelles);
		int[] adresseSimple = {ad[1],ad[2]};
		adresseRobot = adresseSimple;
		indiceAdresseRobot = ad[0];
		adressesInstantT=adressesActuelles;
		if(autreJoueurMarque) nbPalais = (adressesInstantT.length)-2; 
		else nbPalais = (adressesInstantT.length)-1;
		palaisALeurPlace = palaisPossible(adressesActuelles);
	}
	
	/**
	 * <b>Utilise la fonction adresseLaPlusProche qui retourne une liste sous la forme [idc,x,y] qui est en réalité l'adresse l'indice puis le x et le y de l'adresse la plus proche donné en paramètre</b>
	 * @param adresseDonne
	 * @param adressesActuelles
	 * @return l'adresse la plus proche parmis la liste totale
	 */
	public int[] adresseLaPlusProche(int[] adresseDonne, int[][] adressesActuelles) {
		int x=adresseLaPlusProcheTotale(adresseDonne, adressesActuelles)[1];
		int y=adresseLaPlusProcheTotale(adresseDonne, adressesActuelles)[2];
		int[] adresse = {x,y};
		return adresse;
	}
	
	/**
	 * <b>Utilise la fonction adresseLaPlusProche qui retourne une liste sous la forme [idc,x,y] qui est en réalité l'adresse l'indice puis le x et le y de l'adresse la plus proche donné en paramètre</b>
	 * @param adresseDonnee : adress du robot
	 * @param adressesActuelles : toutes les autres adresses
	 * @return l'indice  de l'adresse la plus proche parmis la liste totale
	 */
	public int indiceAdresseLaPlusProche(int[] adresseDonnee, int[][] adressesActuelles) {
		return adresseLaPlusProcheTotale(adresseDonnee, adressesActuelles)[0];
	}
	
	/**
	 * <b>Prends en paramètre une adresse avec un X et un Y retourne à cette instant t, l'adresse la plus proche avec son indice indice dans la liste d'adresse (un int[] de length:3) de la forme : [idc,x,y]</b>
	 * @param adresseDonne
	 * @param adressesActuelles
	 * @return
	 */
	private int[] adresseLaPlusProcheTotale(int[] adresseDonne, int[][] adressesActuelles) {
		int x = adresseDonne[0];
		int y = adresseDonne[1];
		int xProche = adressesActuelles[0][0];
		int yProche = adressesActuelles[0][1];
		int xDif = Math.abs(x-xProche);
		int yDif = Math.abs(y-yProche);
		int dif =xDif+yDif;
		int indice =0;
		for(int i=0; i<adressesActuelles.length; i++) {
			xDif = Math.abs(x-adressesActuelles[i][0]);
			yDif = Math.abs(x-adressesActuelles[i][1]);
			if(dif>xDif+yDif) {
				dif = xDif+yDif;
				indice = i;
			}

		}
		int[] adresseVrai =new int[3];
		adresseVrai[0]=indice;
		adresseVrai[1]=adressesActuelles[indice][0];
		adresseVrai[2]=adressesActuelles[indice][1];
		return adresseVrai;
	}
	
	/**
	 * <b>Appelle la méthode <i>differenceAdresse</i> avec pour l'une des adresse l'adresse du robot</b>
	 * @param adresse
	 * @return vecteur différence du robot à l'adresse choisie
	 */
	public int[] differenceAuRobot(int[] adresse) {
		return differenceAdresse(adresseRobot,adresse);
	}
	
	/**
	 * Retourne le vecteur différence de l'adresse 1 à l'adresse 2
	 * @param adresse
	 * @param adresse2
	 * @return vecteur différence de l'adresse 1 à l'adresse 2
	 */
	public int[] differenceAdresse(int[] adresse, int[] adresse2){
		int xAdresse = adresse[0];
		int xAdresse2 = adresse2[0];
		int yAdresse = adresse[1];
		int yAdresse2 = adresse2[1];
		int[] difference = {xAdresse2-xAdresse,yAdresse2-yAdresse};
		return difference;
	}
	
	/**
	 * <b>Retourne, grâce à une liste des adresses théoriques des palais</b><p>
	 * Leur réel emplacement capter par la caméra <p>
	 * Par exemple : {50,90} sera peut être {43,82}
	 * @param adressesActuelles
	 * @return Tableau d'adresse des palais
	 */
	public int[] palaisPossible(int[][] adressesActuelles) {
		//
		int[] adressePalaisTheorique = {50,90,100,90,150,90,50,150,100,150,150,150,50,210,100,210,150,210};
		int[] adressePalaisPratique = new int[adressePalaisTheorique.length];
		for(int i=0; i<adressePalaisTheorique.length; i+=2) {
			int[] adresse = {adressePalaisTheorique[i],adressePalaisTheorique[i+1]};
			int[] adresseProche = adresseLaPlusProche(adresse, adressesActuelles);
			int[] diff = differenceAdresse(adresse, adresseProche);
			if(diff[0]<15 && diff[1]<15) { //S'assure que l'adresse la plus proche trouver soit suffisament proche pour être un palais
				adressePalaisPratique[i] = adressePalaisTheorique[i];
				adressePalaisPratique[i+1] = adressePalaisTheorique[i+1];
			}
		}
		return adressePalaisPratique;
	}
	
	/**
	 * <b>Dit si la partie est fini, selon la camera infrarouge</b>
	 * @return <b>true</b> si le serveur ne detecte plus de palais (si il ne trouvequ'une adresse si il y a qu'un robot marque ou deux si ils sont deux )
	 */
	public boolean finDePartie() { 
		return nbPalais==0;
	}
	
	
	
	/**
	 * <b>Retourne un <i>String</i> montrant l'adresse du robot et le nombre de palais sur le plateau</b>
	 * @return [ + adresse du robot + ] et le nb de palais : + nb de palais
	 */
	public String toString() {
		return "["+this.adresseRobot[0]+","+this.adresseRobot[1]+"] et le nb de palais :"+this.nbPalais;
	}
	
	
	
	//Geteurs :
	
	/**
	 * <b>Geteur de l'instance adresseRobot</b>
	 * @return l'adresse exacte du robot
	 */
	public int[] getAdresseRobot() {
		return adresseRobot;
	}
	
	/**
	 * <b>Geteur de l'instance nbPalais</b>
	 * @return le nb de palais
	 */
	public int getNbPalais() {
		return nbPalais;
	}
	
	/**
	 * <b>Geteur de l'instance indiceAdresseRobot</b>
	 * @return l'indice de l'adresse du robot dans l'intégralité des adresses
	 */
	public int getIndiceAdresseRobot() {
		return indiceAdresseRobot;
	}
	
	/**
	 * <b>Geteur de l'instance adressesInstantT</b>
	 * @return la liste de l'intégralité des adresses (au dernier refresh)
	 */
	public int[][] getAdressesInstantT(){
		return adressesInstantT;
	}
	
	/**
	 * <b>Geteur de l'instance palaisAleurPlace</b>
	 * @return l'adresse des palais sous forme {x,y,x1,y1,etc.}
	 */
	public int[] getPalaisALeurPlace() {
		return palaisALeurPlace;
	}
	
	/**
	 * <b>Geteur de l'instance adresseDemarrage</b>
	 * @return l'adresse initial du robot en début de round
	 */
	public int[] getAdresseDemarrage() {
		return adresseDeDemarrage;
	}
	
	/**
	 * <b>Geteur de toutes les adresses repérer par la camera IR</b>
	 * @return la liste contenant tout les objets vus par la caméra infrarouge
	 */
	private int[][] getAdresses() {
		int[][] adresses= {};
		try 
		{
			int port = 8888;
			// Create a socket to listen on the port.
			@SuppressWarnings("resource")
			DatagramSocket dsocket = new DatagramSocket(port);

			// Create a buffer to read datagrams into. If a
			// packet is larger than this buffer, the
			// excess will simply be discarded!
			byte[] buffer = new byte[2048];

			// Create a packet to receive data into the buffer
			DatagramPacket packet = new DatagramPacket(buffer, buffer.length);

			// Now loop forever, waiting to receive packets and printing them.

			// Wait to receive a datagram
			dsocket.receive(packet);

			// Convert the contents to a string, and display them
			String msg = new String(buffer, 0, packet.getLength());
			//System.out.println(packet.getAddress().getHostName() + ": "
			//    + msg);

			String[] palets = msg.split("\n");
			adresses= new int[palets.length][2];
			int idc;
			for (idc = 0; idc < adresses.length; idc++) {

				String[] coord = palets[idc].split(";");
				int x = Integer.parseInt(coord[1]);
				int y = Integer.parseInt(coord[2]);

				adresses[idc][0] = x ; 
				adresses[idc][1] = y;
			}


			// Reset the length of the packet before reusing it.
			packet.setLength(buffer.length);

		} 
		catch (Exception e) 
		{
			System.err.println(e);
		}
		return adresses;
	}
	
	/**
	 * <b>Geteur de l'indice du palet qui est le plus proche du robot</b>
	 * @return l'indice de l'adresse du palais le plus proche du robot (si au moment du refresh, le robot adverse est proche et marqué, cela peut confondre avec le robot
	 */
	public int getIndicePalaisLePlusProcheDuRobot() {
		int i=0; int idc=0;
		if(indiceAdresseRobot==i) {
			i++;
			idc=idc++;
		}
		@SuppressWarnings("unused")
		int[] dif =differenceAuRobot(adressesInstantT[i]);
		for(i=i+1; i<adressesInstantT.length; i++) {
			if(i==indiceAdresseRobot) {
				i++;
				idc++;
			}
			else {
				int x = adressesInstantT[i][0];
				int y = adressesInstantT[i][1];
				int xMinimum = adressesInstantT[idc][0];
				int yMinimum = adressesInstantT[idc][1];
				if(x+y<xMinimum+yMinimum) idc = i;
			}
		}
		return idc;
	}
	
	/**
	 * <b>Retourne l'angle dont le robot doit pivoter pour se mettre en direction du point de l'adresse donné</b>
	 * @param adresse
	 * @return l'angle dont le robot doit pivoter pour se mettre en direction du point de l'adresse donné
	 */
	public double getAngleRobotToAdresse(int[] adresse) {
		int[] dif = differenceAuRobot(adresse);
		if(dif[0]==0 && dif[1]==0) return 0;
		double angle = Math.atan(((double)dif[0])/((double)dif[1])); //Sur le plateau, x est la largeur et y la longueur
		angle=angle*57.2958; //Conversion radian à degré
		return angle;
	}
	
	/**
	 * <b>Retourne l'angle dont on doit pivoter pour se mettre en direction du point de l'adresse2 donné depuis l'adresse1 donné</b>
	 * @param adresse
	 * @param adresse2
	 * @return l'angle dont on doit pivoter pour se mettre en direction du point de l'adresse2 donné depuis l'adresse1 donné
	 */
	public  double getAngleAdresseToAdresse(int[] adresse, int[] adresse2) {
		int[] dif = differenceAdresse(adresse, adresse2);
		if(dif[0]==0 && dif[1]==0) return 0;
		double angle = Math.atan(((double)dif[0])/((double)dif[1]));
		angle=angle*57.2958;
		return angle;
	}
	
	/**
	 * <b>Retourne la longueur en cm du vecteur difference entre le robot et l'adresse donne</b>
	 * @param adresse
	 * @return la longueur en cm du vecteur difference entre le robot et l'adresse donne
	 */
	public int getDistanceRobotToAdresse(int[] adresse) {
		int[] dif = differenceAuRobot(adresse);
		int x = dif[0];
		int y = dif[1];
		int x2=x*x;
		int y2=y*y;
		int somme = x2+y2;
		double distanceD = Math.sqrt(somme);
		int distance = (int) distanceD;
		return distance;
	}
	
	/**
	 * <b>Retourne la longueur en cm entre l'adresse et l'adresse2</b>
	 * @param adresse
	 * @param adresse2
	 * @return la longueur en cm entre l'adresse et l'adresse2
	 */
	public int getDistanceAdresseToAdresse(int[] adresse, int[] adresse2) {
		int[] dif = differenceAdresse(adresse, adresse2);
		int x = dif[0];
		int y = dif[1];
		int x2=x*x;
		int y2=y*y;
		int somme = x2+y2;
		double distanceD = Math.sqrt(somme);
		int distance = (int) distanceD;
		return distance;
	}
	
}