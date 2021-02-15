package methodes;

import java.util.ArrayList;

import sacados.*;

public class PSE implements resolutionSac {
	
	/*
	 * Méthode PSE, nous permet de résoudre le 
	 * problème du Sac à Dos
	 * @param [in] sac : renseigne un objet de type SacADos
	 * @return le temps qu'à pris la méthode à résoudre le prblm
	 */
	@Override
	public long resoudreprblm(SacADos sac) {
		ABR racine = new ABR();
		PSE.createBranche(sac, racine, 0);
		ArrayList<ABR> value = new ArrayList<ABR>();
		long tDebut = System.currentTimeMillis();
		PSE.getTree(value, racine, 0, sac.getobjetdispo().size());
		float e = (float) 0;
		int i = 0; // indice de l'arbre
		for (int j = 0; j < value.size(); ++j) {
			if ((e / (value.get(j).getNoeudVal())) < 1) {
				i = j;
				e = (float) value.get(j).getNoeudVal();
			}
		}
		for (int k = 0; k < value.get(i).getObjet().size(); ++k) {
			sac.ajtDsSac(value.get(i).getObjet().get(k));
		}
		long tFin = System.currentTimeMillis();
		return (tFin - tDebut);

	}

	/*
	 * Methode qui nous permet de d'ajouter des branches a notre arbre
	 * @param [in] sac : variable de type SacADos, où l'on retrouve liste des objets
	 * qui sont suceptibles de rentrer dans le sac
	 * @param [in] a : l'arbre auquel on ajoute les diverses branches de possibilité
	 * @param [in] lengthTree : indice qui renseigne notre position sur l'arbre
	 */
	public static void createBranche(SacADos sac, ABR a, int lengthTree) {
		if (sac.getobjetdispo().size() > lengthTree) {
			ABR left = new ABR(a.getObjet());
			if ((left.getNoeudWeight()
					+ (sac.getobjetdispo().get(lengthTree).getPoids()) / (sac.getpoidsMax())) <= 1.0) {
				left.getObjet().add(sac.getobjetdispo().get(lengthTree));
				a.setBg(left);
				PSE.createBranche(sac, a.getBg(), lengthTree + 1);
			}
			ABR right = new ABR(a.getObjet());
			a.setBd(right);
			PSE.createBranche(sac, a.getBd(), lengthTree + 1);
		}
	}

	/*
	 * Méthode récursif qui nous permet de mettre dans solutionOpti tous les chemins
	 * dont l'indice est maximal
	 */
	public static void getTree(ArrayList<ABR> solutionOpti, ABR a, int lenghT, int maxbranch) {
		if (a.getBg() != null) {
			PSE.getTree(solutionOpti, a.getBg(), lenghT + 1, maxbranch);
		} else if (a.getBd() != null) {
			PSE.getTree(solutionOpti, a.getBd(), lenghT + 1, maxbranch);

		} else if (lenghT == maxbranch) {
			solutionOpti.add(a);

		}
	}

}
