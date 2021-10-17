package game;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.Scanner;

import model.Perso;
import util.Utilitaires;

public class Game {

	int sizeX;
	int sizeY;

	int nbMonstre;
	
	char[][] tabMap;

	ArrayList<Perso> alPersos = new ArrayList<Perso>();


	boolean gameOn = true;

	//objectif : automatiser la création des monstres
	//ajout d'une property dans le fichier .properties pour le nb de monstre
	//creer le nombre de monstre correspondant
	//position de chaque monstre est random
	
	
	
	
	public Game() {
		readConfigTab();
		tabMap = new char[sizeX][sizeY];
		createPerso();
		Utilitaires.fillTab(tabMap, alPersos);
	}
	
	public void readConfigTab() {
		FileReader reader;
		Properties p=new Properties(); 
		try {
			reader = new FileReader("./config.properties");
			p.load(reader);
		} catch (IOException e1) {
			e1.printStackTrace();
		}    
		sizeX = Integer.parseInt(p.getProperty("tailleTabX"));  
		sizeY = Integer.parseInt(p.getProperty("tailleTabY"));
		nbMonstre = Integer.parseInt(p.getProperty("nbMonstres"));
	}
	
	public void game() {
		do {
			Utilitaires.printTab(tabMap);
			Utilitaires.printPersos(alPersos);
			move();
		} while (gameOn);
		System.out.println("Game Over");
	}

	private void createPerso() {
		alPersos.add(new Perso((int)(1 + (Math.random() * (sizeX - 2))),
				(int)(1 + (Math.random() * (sizeY - 2))),
				'X', true, 10));
		
		for(int i = 0; i<nbMonstre; ) {
			int x = (int)(1 + (Math.random() * (sizeX - 2)));
			int y = (int)(1 + (Math.random() * (sizeY - 2)));
			
			if(!(tabMap[x][y]=='M'||tabMap[x][y]=='X')) {
				alPersos.add(new Perso(x, y, 'M', false, 10));	
				i++;
			}
		}
	}

	private void move() {

		for (Perso perso : alPersos) {
			if (perso.isControledByUser()) {
				System.out.println("Please give direction" + "z : up, q : left, s : down, d : right. ");
				handleMouvement(Utilitaires.giveString(), perso);
			} else {
				handleMouvement(Utilitaires.randomForMouv(), perso);
			}
		}
	}

	private void handleMouvement(String mouvement, Perso p) {
		switch (mouvement) {
		case "z":
			if (tabMap[p.getX()][p.getY() - 1] == '.') {
				tabMap[p.getX()][p.getY()] = '.';
				p.setY(p.getY() - 1);
				tabMap[p.getX()][p.getY()] = p.getName();
			} else {
				isGameOver(p, p.getX(), p.getY() - 1);
			}

			break;
		case "s":

			if (tabMap[p.getX()][p.getY() + 1] == '.') {
				tabMap[p.getX()][p.getY()] = '.';
				p.setY(p.getY() + 1);
				tabMap[p.getX()][p.getY()] = p.getName();
			} else {
				isGameOver(p, p.getX(), p.getY() + 1);
			}
			break;
		case "q":

			if (tabMap[p.getX() - 1][p.getY()] == '.') {
				tabMap[p.getX()][p.getY()] = '.';
				p.setX(p.getX() - 1);
				tabMap[p.getX()][p.getY()] = p.getName();
			} else {
				isGameOver(p, p.getX() - 1, p.getY());
			}
			break;
		case "d":

			if (tabMap[p.getX() + 1][p.getY()] == '.') {
				tabMap[p.getX()][p.getY()] = '.';
				p.setX(p.getX() + 1);
				tabMap[p.getX()][p.getY()] = p.getName();
			} else {
				isGameOver(p, p.getX() + 1, p.getY());
			}
			break;
		default:
			System.out.println("Erreur de saisie.");
		}
	}

	public void isGameOver(Perso perso, int x, int y) {
		if (perso.isControledByUser()&&(tabMap[x][y] == 'M')) {
			perso.setHealth(perso.getHealth()-1);
		} else {
			if(tabMap[x][y] == 'X') {
				for (Perso perso2 : alPersos) {
					if(perso2.isControledByUser()) {
						perso2.setHealth(perso2.getHealth()-1);
					}
				}
			}
		}
		for (Perso perso2 : alPersos) {
			if(perso2.isControledByUser()&&perso2.getHealth()<1) {
				gameOn = false;
			}
		}
		
	}

}
