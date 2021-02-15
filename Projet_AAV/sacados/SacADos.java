package sacados;


import java.util.ArrayList;

import java.util.Scanner;

import methodes.Glouton;
import java.io.File;
import java.io.FileNotFoundException;

public class SacADos {
	private static float poids_Max; // poids max du sac
	private static float p_Total; // poids total du sac
	private static float val_Totale; // valeur totale compte tenu dans le sac
	private ArrayList<Objet> objets_Dispo; // Liste des objets possibles
	private ArrayList<Objet> objets_ds_Sac; // Objets ajoutés au sac

	/*
	 * Constructeur d'un sac à dos Ce dernier gènère un sac vide
	 */
	public SacADos() {
		objets_Dispo = new ArrayList<Objet>();
		objets_ds_Sac = new ArrayList<Objet>();
		this.poids_Max = 0;
		this.p_Total = 0;
	}

	/*
	 * Construction d'un sac à dos
	 * 
	 * @param [in] path: saisie du chemin pour accèder à un fichier
	 * 
	 * @param [in] p_max : initialise la valeur du poids max du sac à dos
	 */
	public SacADos(String path, float p_max) {
		objets_Dispo = new ArrayList<Objet>();
		objets_ds_Sac = new ArrayList<Objet>();
		this.poids_Max = p_max;
		this.p_Total = 0;
		this.val_Totale = 0;
		try {
			@SuppressWarnings("resource")
			Scanner data = new Scanner(new File(path));

			while (data.hasNext()) {
				String donnees = data.nextLine();
				String[] donneesdivisee = donnees.split(" ; ");
				this.objets_Dispo.add(new Objet(donneesdivisee[0], Float.parseFloat(donneesdivisee[1]), Float.parseFloat(donneesdivisee[2])));
			}

		} catch (FileNotFoundException introuve) {
			System.out.println("Le fichier n'a pas été trouvé");
		} catch (Exception erreur) {
			System.out.println(erreur.getMessage());
		}
	}
	
	/*
	 * Méthode qui énumére les objets disponibles restants
	 */
	public ArrayList<Objet> getobjetdispo(){
		return this.objets_Dispo;
		
	}
	
	/*
	 * Méthode toString qui affiche le poids final du sac ainsi que sa valeur finale
	 */
	public String toString() {
		StringBuilder s = new StringBuilder("Sac à Dos : \n");
		if (this.objets_ds_Sac.size() == 0) {
			s.append("Votre sac à dos est vide");
			return s.toString();
		}
		for (Objet objet : this.objets_ds_Sac) {
			s.append(" - " + objet.toString());
		}
		s.append("\nLe sac pèse " + this.p_Total + "kg et contient une valeur totale de " + this.val_Totale + '\n');
		return s.toString();
	}
	
	/*
	 * Méthode qui ajoute un objet au sac à dos
	 * @param [in] obj : objet que l'on souhaite ajouter dans le sac
	 */
	public void ajtDsSac(Objet obj) {
		this.objets_ds_Sac.add(obj);
		this.p_Total += obj.getPoids();
		this.val_Totale += obj.getValeur();		
	}
	
	/*
	 * Méthode récupère le poids max que peut avoir le sac
	 */
	public float getpoidsMax(){
		return this.poids_Max;
	}
	
	/*
	 * Méthode récupère la valeur totale des objets conservés dans le sac
	 */
	public float getvalT() {
		return this.val_Totale;
	}
	
	
	public float getPdsT() {
		return this.p_Total;
	}
	
	
}
