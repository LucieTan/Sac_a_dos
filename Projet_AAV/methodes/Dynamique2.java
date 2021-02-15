package methodes;
import java.util.ArrayList;

import sacados.*;

public class Dynamique2 implements resolutionSac {
	
	/*
	 * Méthode Dynamique, nous permet de résoudre le 
	 * problème du Sac à Dos en obtenant la valeur optimal
	 * @param [in] sac : renseigne un objet de type SacADos
	 * @return le temps qu'à pris la méthode à résoudre le problème
	 */
	@Override
	public long resoudreprblm(SacADos sac) {
		ArrayList<Objet> objetsDispo = sac.getobjetdispo();
		float poids_Max = sac.getpoidsMax();
		
		long tDebut = System.currentTimeMillis();
		
		// Creation de la matrice
		
		float[][] Matrice = new float[objetsDispo.size()][(int)poids_Max + 1];

		for (int j = 0; j <= poids_Max; j++) {
			if (objetsDispo.get(0).getPoids() > j)
				Matrice[0][j] = 0;
			else
				Matrice[0][j] = objetsDispo.get(0).getValeur();
		}
		
		// Va parmettre de remplir le sac a dos avec la solution optimal
		
		for (int i = 1; i < objetsDispo.size(); i++) {
			for (int j = 0; j <= poids_Max; j++) {
				if (j >= objetsDispo.get(i).getPoids())
					Matrice[i][j] = Math.max(Matrice[i - 1][j],
							Matrice[i - 1][(int) (j- objetsDispo.get(i).getPoids())]
									+ objetsDispo.get(i).getValeur());
				else
					Matrice[i][j] = Matrice[i - 1][j];
			}
		}
		// Va permettre d'obtenir la solution optimal
		
		int i = objetsDispo.size() - 1;
		int j = (int)poids_Max;

		while (j >= 0 && Matrice[i][j] == Matrice[i][j - 1]) {
			j--;
		}
		// Va selectionner l'objet et va le mettre dans le sac 
		while (j > 0) {
			while (i > 0 && Matrice[i][j] == Matrice[i - 1][j])
				i--;
			j = (int) (j - objetsDispo.get(i).getPoids());
			if (j >= 0) {
				sac.ajtDsSac(objetsDispo.get(i));
			}
			i--;
		}
		long tFin = System.currentTimeMillis();
		return (tFin - tDebut);
	}
}


