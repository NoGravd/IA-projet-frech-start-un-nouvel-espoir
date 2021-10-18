package IA;

import java.util.Arrays;

import Le_robot.*;

public class Algo1 {

	public static void main(String[] args) {
		//TODO
		//Au tout début 
		demarrerCapteurUltraSon();
		float[] tab = new float [1000000];
		
		//Dans le while
		capteurUS.getDistanceMode().fetchSample(tab, i);
		
		//Après
		eteindreCapteurUltraSon();
		int jj=0;
		while(tab[jj]!=(float) 0) {
			jj++;
		}
		tab = Arrays.copyOf(tab, jj);
		float min = tab[0];
		int indicdumin = 0;
		for (int kk=1; kk<tab.length; kk++) {
			if (min > tab[kk]) {
				min = tab[kk];
				indicdumin = kk;
			}
		}
		int angle = (indicdumin/tab.length)*1560;
		Roues.mA.rotateTo(angle);
	}

}