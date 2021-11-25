package Le_robot;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import lejos.hardware.Button;

public class EV3Client {
	private int[]adresseRobot; //Adresse du robot à l'instant du dernier Refresh (ou init)
	private int indiceAdresseRobot; //indice de l'adresse du robot au dernier refresh (ou init)
	private int[][] adressesInstantT; //Toute les adresse sous forme [x,y,x1,y1,x2,y2,etc.] (au dernier refresh)
	private int nbPalais; //nombre de palais sur la plateau au dernier refresh
	private boolean autreJoueurMarque;
	private int[] palaisALeurPlace;
	private int[] adresseDeDemarrage; //Une valeur FIXE qui est simplement l'adresse du démarrage
	
	public EV3Client(int[] adresseDemarrage, boolean autreJoueurMarque) {
		//Initialise en prenant une adresse de démarrage (50,30 pour en bas à gauche par exemple)
		this(adresseDemarrage);
		this.autreJoueurMarque=autreJoueurMarque;
		if(autreJoueurMarque) nbPalais = (adressesInstantT.length)-2; //Si il y a un autre joueur, alors le nb de palais c'est le nb total -2
		else nbPalais = (adressesInstantT.length)-1; //sinon on enlève uniquement notre robot 
		palaisALeurPlace = palaisPossible();
	}
	public EV3Client(int[] adresseDemarrage) {
		//Initialise les adresses et l'indice du robot
		int[] ad = adresseLaPlusProcheTotale(adresseDemarrage);
		int[] adresseSimple = {ad[1],ad[2]}; //L'adresse la plus proche parmis toute celle du tableau
		adresseRobot = adresseSimple; 
		adresseDeDemarrage=adresseRobot;
		indiceAdresseRobot = ad[0]; //L'indice de cette même adresse
		adressesInstantT=getAdresses(); //L'intégralité des adresses à l'instant T
	}
	
	public int[] getAdresseRobot() {
		//retourne l'adresse exacte du robot
		return adresseRobot;
	}
	
	public int getNbPalais() {
		return nbPalais;
	}
	public int getIndiceAdresseRobot() {
		//retourne l'indice de l'adresse du robot dans l'intégralité des adresses
		return indiceAdresseRobot;
	}
	
	public int[][] getAdressesInstantT(){
		//retourne la liste de l'intégralité des adresses (au dernier refresh)
		return adressesInstantT;
	}
	
	public int[] getPalaisALeurPlace() {
		return palaisALeurPlace;
	}
	
	public int[] getAdresseDemarrage() {
		return adresseDeDemarrage;
	}
	
	public void refreshAdressesInstantT() {
		//refresh la liste des adresses (mais ne mets pas à jour l'adresse du robot)
		adressesInstantT=getAdresses();
	}
	
	public void refreshAvecLocalisation(int[] localisation) {
		//refresh l'intégralité des attribut du Client
		int[] ad = adresseLaPlusProcheTotale(localisation);
		int[] adresseSimple = {ad[1],ad[2]};
		adresseRobot = adresseSimple;
		indiceAdresseRobot = ad[0];
		adressesInstantT=getAdresses();
		if(autreJoueurMarque) nbPalais = (adressesInstantT.length)-2; 
		else nbPalais = (adressesInstantT.length)-1;
		palaisALeurPlace = palaisPossible();
	}
  
  private int[][] getAdresses() {
	  //Retourne la liste contenant tout les objets vus par la caméra infrarouge
	  int[][] adresses= {};
	  try 
	    {
	      int port = 8888;

	      // Create a socket to listen on the port.
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
	        int idc=0;
	        for (int i = 0; i < palets.length; i++) {
	        	
	        	String[] coord = palets[i].split(";");
	        	int x = Integer.parseInt(coord[1]);
	        	int y = Integer.parseInt(coord[2]);
	        
	        	adresses[idc][0] = x-13 ; //Le -13 permet de calibrer au centre du robot
	        	adresses[idc][1] = y;
	        }
	        

	        // Reset the length of the packet before reusing it.
	        packet.setLength(buffer.length);
	      return adresses;
	     
	    } 
	    catch (Exception e) 
	    {
	      System.err.println(e);
	    }
	return adresses;
  }
  
  public int[] adresseLaPlusProche(int[] adresseDonne) {
	  //Utilise la fonction adresseLaPlusProche qui retourne une liste sous la forme
	  //[idc,x,y] qui est en réalité l'adresse l'indice puis le x et le y de l'adresse
	  //la plus proche donné en paramètre
	  //RETOURNE l'adresse la plus proche parmis la liste totale
	  int x=adresseLaPlusProcheTotale(adresseDonne)[1];
	  int y=adresseLaPlusProcheTotale(adresseDonne)[2];
	  int[] adresse = {x,y};
	  return adresse;
  }
  
  public int indiceAdresseLaPlusProche(int[] adresseDonne) {
	  //Utilise la fonction adresseLaPlusProche qui retourne une liste sous la forme
	  //[idc,x,y] qui est en réalité l'adresse l'indice puis le x et le y de l'adresse
	  //la plus proche donné en paramètre
	  //RETOURNE l'indice  de l'adresse la plus proche parmis la liste totale
	  return adresseLaPlusProcheTotale(adresseDonne)[0];
  }
  private int[] adresseLaPlusProcheTotale(int[] adresseDonne) {
	  //Prends en paramètre une adresse avec un X et un Y
	  //retourne à cette instant t, l'adresse la plus proche avec son indice 
	  //indice dans la liste d'adresse (un int[] de length:3) de la forme :
	  // [idc,x,y]
	  int[][] adressesActuelles=getAdresses();
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
	  return adressesActuelles[indice];
  }
  
  public int getIndicePalaisLePlusProcheDuRobot() {
	  int i=0; int idc=0;
	  if(indiceAdresseRobot==i) {
		  i++;
		  idc=idc++;
	  }
	  int[] dif =differenceAuRobot(adressesInstantT[i]);
	  for(i=i+1; i<adressesInstantT.length; i++) {
		  if(i==indiceAdresseRobot) i++;
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
  public int[] differenceAuRobot(int[] adresse) {
	  //Retourne le vecteur différence du robot à l'adresse choisie
	  return differenceAdresse(adresseRobot,adresse);
  }
  public int[] differenceAdresse(int[] adresse, int[] adresse2){
	  //Retourne le vecteur différence de l'adresse 1 à l'adresse 2
	  int xAdresse = adresse[0];
	  int xAdresse2 = adresse2[0];
	  int yAdresse = adresse[1];
	  int yAdresse2 = adresse2[1];
	  int[] difference = {xAdresse2-xAdresse,yAdresse2-yAdresse};
	  return difference;
  }
  public double getAngleRobotToAdresse(int[] adresse) {
	  //Retourne l'angle dont le robot doit pivoter pour se mettre en direction 
	  //du point de l'adresse donné
	  int[] dif = differenceAuRobot(adresse);
	  double angle = Math.atan((double)dif[0]/(double)dif[1]);
	  return angle;
  }
  public int getDistanceRobotToAdresse(int[] adresse) {
	  int[] dif = differenceAuRobot(adresse);
	  int x = adresse[0];
	  int y = adresse[1];
	  double distanceD = Math.sqrt((x*x)+(y*y));
	  int distance = (int) distanceD;
	  return distance;
  }
  public int[] palaisPossible() {
		int[] adressePalaisTheorique = {50,90,100,90,150,90,50,150,100,150,150,150,50,210,100,210,150,210};
		int nbPalais = getNbPalais();
		int[] adressePalaisPratique = new int[nbPalais*2];
		for(int i=0; i<adressePalaisTheorique.length; i+=2) {
			int[] adresse = {adressePalaisTheorique[i],adressePalaisTheorique[i+1]};
			int[] adresseProche = adresseLaPlusProche(adresse);
			int[] diff = differenceAdresse(adresse, adresseProche);
			if(diff[0]<10 && diff[1]<10) {
				adressePalaisPratique[i] = adressePalaisTheorique[i];
				adressePalaisPratique[i+1] = adressePalaisTheorique[i+1];
			}
		}
		return adressePalaisPratique;
	}
  public boolean finDePartie() { 
	  //Pour lancer le code final on lance tous les scanner, le client, etc
	  //On fait un énorme while(finDePartie) 
	  //A chaque fois que l'on dépose un palais, on refresh après avoir fait
	  //demi-tour. Et on reboucle dans le while
	  //Quand on sors du while on va au centre et on lance la musique
	  return nbPalais==0;
  }
  public String toString() {
	  return "["+this.adresseRobot[0]+","+this.adresseRobot[1]+"] et le nb de palais :"+this.nbPalais;
  }
}
           
