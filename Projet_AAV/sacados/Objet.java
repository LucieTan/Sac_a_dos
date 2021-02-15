package sacados;

public class Objet {
	private float poids;
	private float valeur;
	private float rapport; // densité de valeur
	private String nom;

	public Objet(String name, float p, float v) {
		this.poids = p;
		this.valeur = v;
		this.rapport = v / p;
		this.nom = name;
	}

	/*
	 * Méthode toString Cette dernière retourne les caractéristiques de l'objet
	 * courant
	 */
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append(nom + " pesant " + poids + "kg " + "et de valeur " + valeur + "\n");
		return str.toString();
	}

	/*
	 * Méthode qui retourne l'objet courant
	 */
	public Objet getObjets() {
		return this;
	}

	/*
	 * Méthode récupère le poids de l'objet
	 */
	public float getPoids() {
		return this.poids;
	}

	/*
	 * Retourne la valeur de l'objet
	 */
	public float getValeur() {
		return this.valeur;
	}

	/*
	 * Méthode qui retourne le nom de l'objet courant
	 */
	public String getNom() {
		return this.nom;
	}

	/*
	 * Méthode retournant le rapport entre la valeur et le poids de l'objet courant
	 */
	public float getRapport() {
		return this.rapport;
	}

}
