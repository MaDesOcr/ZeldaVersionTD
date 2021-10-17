package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import model.Perso;

public class Utilitaires {

	/*static HashMap<Integer, String> keyRandomMouvement =
			new HashMap<Integer, String>();
	
	public static void initializeMapRadom() {
		keyRandomMouvement.put(1, "z");
		keyRandomMouvement.put(2, "s");
		keyRandomMouvement.put(3, "q");
		keyRandomMouvement.put(4, "d");
			
	}*/
	
	
	
	
	public static String giveString() {
		Scanner sc = new Scanner(System.in);
		String s = sc.nextLine();
		return s;
	}
	
	public static String randomForMouv() {
		int i = 
				1 + (int)(Math.random() * ((4 - 1) + 1));
		if(i==1) return "z";
		else if(i==2) return "s";
		else if (i==3) return "q";
		else return "d";
 
	}
	
	public static void printTab(char[][] map) {
		for(int i = 0; i<map.length; i++) {
			for(int j = 0; j<map[i].length; j++) {
				System.out.print(map[j][i]);
			}
			System.out.println();
		}
	}
	
	public static void fillTab(char[][] map, ArrayList<Perso> alPersos) {
		for(int i = 0; i<map.length; i++) {
			for(int j = 0; j<map[i].length; j++) {
				map[i][j]='.';
				map[0][j]='1';
				map[map.length-1][j]='1';
			}
			map[i][0]='1';
			map[i][map[i].length-1]='1';
		}
		
		for (Perso perso : alPersos) {
			map[perso.getX()][perso.getY()]=perso.getName();
		}
	}

	public static void printPersos(ArrayList<Perso> alPersos) {
		for (Perso perso : alPersos) {
			if(perso.isControledByUser()) {
				System.out.println(perso.toString());
			}
		}
		
	}
}
