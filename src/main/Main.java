package main;

import java.io.IOException;

import librairies.StdDraw;

public class Main {
	// Test git

	public static void main(String[] args) throws IOException {

		try {
			Jeu jeu = new Jeu();
			jeu.initialDisplay();
			StdDraw.show(); // StdDraw est utilise en mode buffer pour fluidifier
			// l'affichage: utiliser
			// StdDraw.show() pour afficher ce qui est dans le buffer
			while (!jeu.isOver()) {
				jeu.update();
				// jeu.display();
			}
			System.out.println("Finis");
			StdDraw.frame.setVisible(false);
			StdDraw.frame.dispose();

		} catch (Exception e) {
			System.out.println("Erreur lors de l'execution du jeu : " + e.getMessage());
		}

	}
}
