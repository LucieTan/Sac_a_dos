package methodes;

import java.util.ArrayList;

import sacados.*;

public class Arbre {
	private ArrayList<Objet> objet_dispo;
	private ArrayList<Objet> sac_optimal; // liste avec la solution potentielle voire optimale
	private Arbre d, g;
	private float pds;
	private int profondeur; // la colonne de l'arbre
	private static float borneinferieure, bornesup; // borneinferieure : meilleure solution trouvée à l'instant
	// bornesup : val max que pourra avoir la combinaison finale à partir d'un noeud

	public Arbre() {
	}
	/*
	 * Constructeur prenant en paramètre un objet de type Arbre
	 * initialise les attributs de notre classe
	 * 
	 */
	public Arbre(Arbre g, Arbre d) {
		this.objet_dispo = new ArrayList<Objet>();
		this.d = d;
		this.g = g;
	}
	
	public Arbre(ArrayList<Objet> obj) {
		this.objet_dispo =new ArrayList<Objet>();
		for(Objet objets : obj) {
			this.getObjet().add(objets);
		}
	}
	
	public Arbre(Arbre g, Arbre d , ArrayList<Objet> obj) {
		this(g,d);
		for(Objet objets : obj) {
			this.getObjet().add(objets);
		}
		
	}
	
	public void addAG(Arbre left) {
		this.g =left;
	}
	
	public void addAD(Arbre right) {
		this.d =right;
	}
	
	public void addObject( ArrayList<Objet> objts) {
		for(int i = 0; i<objts.size();++i) {
			this.getObjet().set(i, objts.get(i));
		}
	}
	
	public ArrayList<Objet> getObjet(){
		return this.objet_dispo;
	}
	
	public Arbre getAG() {
		return this.g;
	}
	
	public Arbre getAD() {
		return this.d;
	}
	public float getVal() {
		float valeur =0;
		for(int i=0; i<this.getObjet().size(); ++i) {
			valeur = valeur + this.getObjet().get(i).getValeur();		}
		return valeur;
	}
	
	public float getWeight() {
		float weight =0;
		for(int i=0; i<this.getObjet().size(); ++i) {
			weight = weight + this.getObjet().get(i).getPoids();		}
		return weight;
	}
	
	/*public void ajtBranche(SacADos sac, Arbre a, int p) {
		profondeur = p;
		if(profondeur<sac.getobjetdispo().size()) {
			Arbre left = new Arbre(a.getObjet());
			
			
		}
		
	}*/
	/*
	 * Méthode qui permet de créer l'arbre
	 */
	public void creation(ArrayList<Objet> objet) {
		this.objet_dispo =objet;
		// creation des branches gauches
		if (profondeur< objet.size()) {
			g = new Arbre();
			if(this.objet_dispo != null) {
				for(Objet arbre : this.objet_dispo) {
					g.sac_optimal.add(arbre);
				}
			}
			if(profondeur < objet.size()) {
				g.sac_optimal.add(objet.get(profondeur));
				profondeur++;
				g.creation(objet);
				profondeur--;
			}
		}
		if (profondeur<objet.size()) {
			d= new Arbre();
			d.sac_optimal = this.objet_dispo;
			profondeur++;
			d.creation(objet);
			profondeur--;
			
			
		}
		System.out.println(this.sac_optimal);
		
	}
	

	
	
}
