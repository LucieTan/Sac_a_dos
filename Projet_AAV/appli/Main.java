package appli;

import sacados.*;

import java.util.Scanner;

import methodes.*;

public class Main {
	
/*
 * Méthode qui est appelé dans le main
 * Cette dernière échange avec les différentes classes développées 
 */
	private static void sacResolution() {
		Scanner in = new Scanner(System.in);

		String path;
		float poidsM;
		String methode;

		System.out.println("===============================");
		System.out.println("---  PROBLEME DU SAC A DOS  ---");
		System.out.println("===============================");

		do {
			System.out.println("\nVeuillez saisir le chemin pour accèder au fichier texte : ");
			path = in.nextLine();
		} while (!path.contains(".txt"));

		do {
			System.out.println("Renseignez le poids max du sac :");
			poidsM = in.nextFloat();
		} while (poidsM <= 0);

		SacADos backpack = new SacADos(path, poidsM);
		do {
			methode = in.nextLine();
			methode = methode.toUpperCase();
			switch (methode) {
			case "GLOUTON": {
				Glouton m = new Glouton();
				long exec = m.resoudreprblm(backpack);
				System.out.println(backpack.toString());
				System.out.println("Le temps d'exécution est de "+ exec +"ms");
				break;
			}
			case "DYNAMIQUE": {
				Dynamique2 m1 = new Dynamique2();
				long exec1 = m1.resoudreprblm(backpack);
				System.out.println(backpack.toString());
				System.out.println("Le temps d'exécution est de "+ exec1 +"ms");
				break;
			}
			case "PSE": {
				PSE m2= new PSE();
				long exec2 =m2.resoudreprblm(backpack);
				System.out.println(backpack.toString());
				System.out.println("Le temps d'exécution est de "+ exec2 +"ms");
				break;


			}
			case "" :{
				System.out.println("\nVeuillez rentrer l'un des intitulés ci-dessous");
				System.out.println("\nOptions :");
				System.out.println("1. Glouton");
				System.out.println("2. Dynamique");
				System.out.println("3. PSE");
				break;
			}
			default: {
				System.out.println("Cette méthode n'existe pas !");
			}
			}
		} while (!(methode.equals("Glouton") || methode.equals("Dynamique") || methode.equals("PSE")));
		in.close();
	}

	public static void main(String[] args) {
		sacResolution();
		
	}

}
