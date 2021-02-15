package methodes;

import java.util.ArrayList;

import sacados.*;

public class Glouton implements resolutionSac {
	/*
	 * Méthode Gloutonne, nous permettant de résoudre le 
	 * problème du Sac à Dos
	 * @param [in] sac : renseigne un objet de type SacADos
	 * @return le temps qu'à pris la méthode à résoudre le prblm
	 */
	@Override
	public long resoudreprblm(SacADos sac) {
		ArrayList<Objet> objetsDispo = sac.getobjetdispo();
		float pdsMax = sac.getpoidsMax();
		long timeDebut = System.currentTimeMillis();
		quickSort(objetsDispo, 0, objetsDispo.size());
		for (Objet obj : objetsDispo) {
			if (sac.getPdsT() + obj.getPoids() <= pdsMax) {
				sac.ajtDsSac(obj);
			}
		}
		long timeArret = System.currentTimeMillis();
		return (timeArret - timeDebut);
	}

	/*
	 * Méthode permettant d'intervertir deux objets 
	 * @param [in] obj : liste des objets possibles pr le prblm
	 * @param [in] d : indice du début de la liste
	 * @param [in] f : indice de la fin de la liste
	 */
	public void echanger(ArrayList<Objet> obj, int d, int f) {
		Objet tmp = obj.get(d);
		obj.set(d, obj.get(f));
		obj.set(f, tmp);
	}
	/*
	 * Méthode quicksort, ce dernier nous permet de trier la liste de manière décroissante
	 * Appel récursif de cette méthode
	 * @param [in] obj : liste des objets possibles pr le prblm
	 * @param [in] d : début de la liste
	 * @param [in] f : fin de la liste
	 */
	public void quickSort(ArrayList<Objet> obj, int d, int f) {
		for (Objet elem : obj) {
			elem.getRapport();	
		}
		int i = d + 1;
		int pivot = choixPivot(obj, d, f);
		if (d < f) {
			echanger(obj, d, pivot);
			pivot = d;
			for (int j = d + 1; j <= f - 1; ++j) {
				if (obj.get(j).getRapport() > obj.get(pivot).getRapport()) {
					echanger(obj, j, i);
					i += 1;
				}
			}
			echanger(obj, pivot, i - 1);
			pivot = i - 1;
			quickSort(obj, d, pivot - 1);
			quickSort(obj, pivot + 1, f);
		}
	}

	/*
	 * Méthode nous permettant de sélectionner le pivot du tableau
	 * @param [in] obj : renseigne un liste d'objet
	 * @param [in] d,f : indices du début et de la fin de la liste
	 * @return indice du pivot
	 */
	public int choixPivot(ArrayList<Objet> obj, int d, int f) {return (d + f) / 2;}
}
