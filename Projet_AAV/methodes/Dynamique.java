package methodes;

import java.util.ArrayList;
import sacados.*;

public class Dynamique implements resolutionSac{
	/*
	 * M�thode qui permet de r�soudre le probl�me de sac � dos
	 * @param [in] sac : renseigne un objet de type SacADos
	 * @return retour le temps de l'ex�cution de la m�thode
	 */
	@Override
	public long resoudreprblm(SacADos sac) {
		ArrayList<Objet> objetsDispo = sac.getobjetdispo();
		float poids_Max = sac.getpoidsMax();
		int poidsMaxiConverti = conversionFloatInteger(poids_Max, objetsDispo);
		int coefMultiplicateur = coeffMultiplicateur(objetsDispo);
		long tDebut = System.currentTimeMillis();
		float[][] Matrice = new float[objetsDispo.size()][poidsMaxiConverti + 1];
		for (int p = 0; p <= poidsMaxiConverti; p++) {
			if (objetsDispo.get(0).getPoids() * coefMultiplicateur > p)
				Matrice[0][p] = 0;
			else
				Matrice[0][p] = objetsDispo.get(0).getValeur();
		}
		for (int i = 1; i < objetsDispo.size(); i++) {
			for (int p = 0; p <= poidsMaxiConverti; p++) {
				if (p >= objetsDispo.get(i).getPoids() * coefMultiplicateur)
					Matrice[i][p] = Math.max(Matrice[i - 1][p],
							Matrice[i - 1][(int) (p - objetsDispo.get(i).getPoids() * coefMultiplicateur)]
									+ objetsDispo.get(i).getValeur());
				else
					Matrice[i][p] = Matrice[i - 1][p];
			}
		}
		int n = objetsDispo.size() - 1;
		int p = poidsMaxiConverti;
		// On d�cale l'indice vers la gauche, tant que la valeur de l'ensemble
		// est au plus haut
		while (p >= 0 && Matrice[n][p] == Matrice[n][p - 1]) {
			p--;
		}
		while (p > 0) {
			while (n > 0 && Matrice[n][p] == Matrice[n - 1][p])
				n--;
			p = (int) (p - objetsDispo.get(n).getPoids() * coefMultiplicateur);
			if (p >= 0) {
				sac.ajtDsSac(objetsDispo.get(n));
			}
			n--;
		}
		long tFin = System.currentTimeMillis();
		return (tFin - tDebut);
	}

	/*
	 * M�thode qui nous renseigne sur le nombre de chiffre pr�cedent la virgule
	 * @param [in] n : variable d
	 */
	private static int nombreDeChiffreApr�sLaVirgule(float n) {
		String str = String.valueOf(n);
		int positionVirgule = str.indexOf(".") + 1;
		String partieDecimale = str.substring(positionVirgule, str.length());
		// On supprime les z�ros inutiles = qui se trouvent � la fin du nombre
		while (partieDecimale.endsWith("0"))
			partieDecimale = str.substring(positionVirgule++, str.length());
		return partieDecimale.length();
	}

	private int coeffMultiplicateur(ArrayList<Objet> objetsDispo) {
		int maximumActuel = 0; // Nombre de chiffre apr�s la virgule par d�faut
		for (Objet o : objetsDispo) {
			maximumActuel = Math.max(maximumActuel, nombreDeChiffreApr�sLaVirgule(o.getPoids()));
		}
		if (maximumActuel == 0)
			return 1;
		return (int) Math.pow(10, maximumActuel);
	}
	/*
	 * M�thode qui permet de convertir un float en integer (pour les tab)
	 * @param [in] poidsMaximal : poids max du sac
	 * @param[in] objetsDispo : liste des objets qui peuvent �tre gliss�s dans le sac 
	 */
	private int conversionFloatInteger(float poidsMaximal, ArrayList<Objet> objetsDispo) {
		return (int) poidsMaximal * coeffMultiplicateur(objetsDispo);
	}

}
