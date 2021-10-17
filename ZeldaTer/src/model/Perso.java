package model;

public class Perso {

	int x,y;
	char nom;
	boolean controledByUser;
	int health;
	
	public Perso(int x, int y, char nom, boolean controledByUser, int health) {
		this.x = x;
		this.y = y;
		this.nom = nom;
		this.controledByUser = controledByUser;
		this.health = health;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public char getName() {
		return nom;
	}

	public void setName(char nom) {
		this.nom = nom;
	}

	public boolean isControledByUser() {
		return controledByUser;
	}

	public void setControledByUser(boolean controledByUser) {
		this.controledByUser = controledByUser;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	@Override
	public String toString() {
		return "Perso [nom=" + nom + ", health=" + health + "]";
	}
	
	
}
