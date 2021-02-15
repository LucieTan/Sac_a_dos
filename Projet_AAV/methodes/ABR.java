package methodes;

import java.util.ArrayList;
import sacados.*;

public class ABR {
	private ArrayList<Objet> objet; // liste des objets qui sont susceptible de rentrer dans le sac
	private ABR d, g;


	/*
	 * Constructeur vide
	 */
	public ABR() {
		this.objet = new ArrayList<Objet>();
	}

	public ABR(ABR Bd, ABR Bg) {
		this();
		this.d = Bd;
		this.g = Bg;
	}

	/*
	 * Constructeur qui initialise les attributs de notre classe ABR
	 * ici il initialise une liste d'éléments de type Objet
	 */
	public ABR(ArrayList<Objet> obj) {
		this.objet = new ArrayList<Objet>();
		for (int i = 0; i < obj.size(); ++i) {
			this.getObjet().add(obj.get(i));
		}
	}

	/*
	 * Construction qui nous permet d'ajouter des objets ds nos sous branches
	 */
	public ABR(ArrayList<Objet> objet, ABR Bd, ABR Bg) {
		this(Bd, Bg);
		for (int i = 0; i < objet.size(); ++i) {
			this.getObjet().add(objet.get(i));
		}

	}

	/*
	 * Liste des objets de la branche courante
	 */
	public void setObjet(ArrayList<Objet> obj) {
		/*
		 * for(Objet objet : obj){ this.getObjet().set(objet, obj.get(objet)); } a voir
		 * si c'est possible
		 */
		for (int i = 0; i < obj.size(); ++i) {
			this.getObjet().set(i, obj.get(i));
		}
	}
	
	public float getNoeudWeight() {
		float weight = 0;
		for (int j =0 ; j< this.getObjet().size(); ++j) {
			weight = this.getObjet().get(j).getPoids() ;
		}
		return weight;
	}
	
	public float getNoeudVal() {
		float value = 0;
		for (int j =0 ; j< this.getObjet().size(); ++j) {
			value = this.getObjet().get(j).getPoids() ;
		}
		return value;
	}

	/*
	 * retourne l'arbre
	 */
	public ABR getRoot() {
		return this;
	}

	public ABR getBd() {
		return this.d;
	}

	public ABR getBg() {
		return this.g;
	}

	/*
	 * Méthode qui nous permet de récupérer la liste d'objet courant
	 */
	public ArrayList<Objet> getObjet() {
		return this.objet;
	}

	/*
	 * Branche droite
	 */
	public void setBd(ABR Bd) {
		this.d = Bd;
	}

	public void setBg(ABR Bg) {
		this.g = Bg;
	}
	
	

}