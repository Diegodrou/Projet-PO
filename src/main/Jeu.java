/** package principal */
package main;

import java.io.File;

import librairies.StdDraw;
import main.strctCarte.Defausse;
import main.strctCarte.Pioche;
import ressources.Affichage;
import ressources.AssociationTouches;
import ressources.Config;

public class Jeu {

	private Heros joueur;
	private SalleDeCombat salle;
	private boolean over;
	private Deck deck;
	private Pioche pioche;
	private Defausse defausse;

	public Jeu() throws Exception {
		this.joueur = new Heros(70, 3);
		this.salle = new SalleDeCombat(2);
		this.deck = new Deck();
		this.pioche = new Pioche();
		this.defausse = new Defausse();
		over = false;

	}

	public boolean isOver() {
		return over;
	}

	public void display() {
		StdDraw.clear();

		// Affichage du fond
		String pathBackground = "pictures" + File.separator + "background.jpg";
		Affichage.image(0, Config.X_MAX, 0, Config.Y_MAX, pathBackground);

		// Affichage du héros
		String pathHeros = "pictures" + File.separator + "Ironclad.png";
		Affichage.image(Config.X_MAX * 0.2 - 183, Config.X_MAX * 0.2 + 183, Config.Y_MAX * 0.5 - 130,
				Config.Y_MAX * 0.5 + 130, pathHeros);

		// Affichage de l'énergie et le nombre de carte de la pioche, de la défausse et
		// en l'exil
		// Affichage.texteGauche(40, Config.Y_MAX - 20, "Pioche : 10");
		// Affichage.texteGauche(40, Config.Y_MAX - 45, "Energie : 3/3");
		// Affichage.texteDroite(Config.X_MAX - 50, Config.Y_MAX - 20, "Defausse : 0");
		// Affichage.texteDroite(Config.X_MAX - 50, Config.Y_MAX - 45, "Exil : 0");

		// Affichage des stats du joueur
		affichageStatsJoueurModeGraphique();
		// Affichage des stats des monstres
		for (int i = 0; i < salle.getMonstres().length; i++) {
			Affichage.texteGauche(Config.X_MAX * 0.5 + 20 + i * 50, Config.Y_MAX * 0.5 - (130 + i * 50),
					salle.getMonstres()[i].toString());
		}

		// Affichage carte dans la main
		afficheCarteMainModeGraphique();

		// Affichage finir tour
		Affichage.texteDroite(Config.X_MAX - 50, Config.Y_MAX - 20, "Entrée - Finir tour");

		StdDraw.show(); // montre a l'ecran les changements demandés
	}

	public void initialDisplay() {
		AssociationTouches.init();
		Config.init();
		StdDraw.enableDoubleBuffering(); // rend l'affichage plus fluide: tout draw est mis en buffer et ne s'affiche
											// qu'au prochain StdDraw.show();
		display();
	}

	public void update() {
		// display();
		tourDuJoueur();
		// display();
		System.out.println("Tour des monstres");
		salle.performerActionSalle(joueur);
		affichageModeTexte();
		display();

		if (!joueur.alive() || !salle.isMonstersAlive()) {
			over = true;
		}

		// String toucheSuivante = AssociationTouches.trouveProchaineEntree(); //cette
		// fonction boucle jusqu'a la prochaine entree de l'utilisateur
		// if (toucheSuivante.equals("A")) {
		// //TODO: deplacer le curseur vers le haut
		// System.out.println("1");
		// display();
		// }
		// TODO: Ajouter les autres touches utiles avec la classe AssociationTouches
		// else {
		// System.out.println("Autre touche");
		// display();
		// }

	}

	/**
	 * Performe le tour du joueur
	 */
	public void tourDuJoueur() {
		salle.prepTourDeJoueur(joueur);
		display();// Affiche les donnees apres preparation du tour
		System.out.println("Choisit une carte");
		boolean tourDuJoueur = true;
		while (tourDuJoueur) {
			String toucheSuivante = AssociationTouches.trouveProchaineEntree();
			if (toucheSuivante.equals("1")) {
				joueCarte(0);
			} else if (toucheSuivante.equals("2")) {
				joueCarte(1);
			} else if (toucheSuivante.equals("3")) {
				joueCarte(2);
			} else if (toucheSuivante.equals("Entrée")) {
				tourDuJoueur = false;
				affichageModeTexte();
				display();
			} else {
				System.out.println("Autre touche");
			}
			System.out.println("Appuyer sur la touche Entrée pour finir le tour");

		}
		salle.actionsFinTourJoueur(joueur);
	}

	public void choisir_cible() {

		if (joueur.getCarteDeLaMain(joueur.getCarteChoisie()).getTypeDentiteApplicable() == "Heros") {
			salle.performerActionsJoueur(joueur);
		} else {
			System.out.println("Choisit une cible");
			String toucheSuivante = AssociationTouches.trouveProchaineEntree();
			if (toucheSuivante.equals("1")) {
				System.out.println("Vous avez choisi monstre 1");
				salle.setCibleDuJoueur(0);
				salle.performerActionsJoueur(joueur);

			} else if (toucheSuivante.equals("2")) {
				System.out.println("Vous avez choisi montre 2");
				salle.setCibleDuJoueur(1);
				salle.performerActionsJoueur(joueur);

			} else {
				System.out.println("Autre touche");
			}
		}

	}

	/**
	 * Joue la carte n+1 du joueur
	 * 1-regarde si le joueur a l'energie suiffisante pour utiliser la carte
	 * 2-s'il a l'energie suiffisante il choisit une cible et affiche le changment
	 * sinon rien se passe
	 * 
	 * @param n un entier representant l'index d'un tableau
	 */
	private void joueCarte(int n) {
		if (n >= 0 || n < 5) {
			if (joueur.getCarteDeLaMain(n).getCout() <= joueur.getEnergie()) {
				System.out.println("Vous avez choisie la carte 1 ");
				joueur.setCarteChoisie(n);
				choisir_cible();
				affichageModeTexte();
				display();
			} else {
				System.out.println("Pas assez d'energie");
			}
		}
	}

	/**
	 * Affiche le jeu dans le terminal
	 */
	public void affichageModeTexte() {
		afficheStatsJoueursModeTexte();
		affichageStatsSalleModeTexte();
		System.out.println("----------------------------");
	}

	/**
	 * Affiche les stats du joueur
	 */
	public void afficheStatsJoueursModeTexte() {
		System.out.println("-------------");
		System.out.println(joueur);
		System.out.println("-------------");
	}

	/**
	 * Affiche la salle
	 */
	public void affichageStatsSalleModeTexte() {
		System.out.println(salle);
	}

	/**
	 * Affiche les stats du joueur
	 */
	public void affichageStatsJoueurModeGraphique() {
		double initialStatutPosX = Config.X_MAX * 0.2;
		double initialStatutPosX2 = Config.X_MAX * 0.2 + 30;
		double initialStatuPosY = Config.Y_MAX * 0.5 + 130;
		double initialStatuPosY2 = Config.Y_MAX * 0.5 + 150;
		String pathStatutVul = "pictures" + File.separator + "statuts" + File.separator + "Icon_Vulnerable.png";
		Affichage.texteGauche(Config.X_MAX * 0.2 - 100, Config.Y_MAX * 0.5 - 130, joueur.toString());
		// Affichage vulnerabilité
		int pointVul = joueur.vulnerabilite.getPointStatut();
		if (pointVul > 0) {
			Affichage.image(initialStatutPosX, initialStatutPosX2, initialStatuPosY, initialStatuPosY2, pathStatutVul);
			Affichage.texteGauche(initialStatutPosX2, initialStatuPosY, "" + pointVul);
		}

	}

	// Affichage carte dans la main
	public void afficheCarteMainModeGraphique() {
		Carte[] main = joueur.getMain();
		for (int i = 0; i < main.length; i++) {
			if (main[i] != null) {
				Affichage.texteGauche(Config.X_MAX * 0.2 + i * 300, Config.Y_MAX * 0.1,
						(i + 1) + "-" + main[i].toString());
			}
		}
	}

}
