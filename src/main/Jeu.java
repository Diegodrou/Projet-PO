/** package principal */
package main;

import java.io.File;

import librairies.StdDraw;
import ressources.Affichage;
import ressources.AssociationTouches;
import ressources.Config;

public class Jeu {

	private Heros joueur;
	private SalleDeCombat salle;
	private boolean over;

	public Jeu() throws Exception {
		this.joueur = new Heros(70, 3);
		this.salle = new SalleDeCombat(2);
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
		Affichage.image(Config.X_MAX*0.2 - 183, Config.X_MAX*0.2 + 183, Config.Y_MAX*0.5 - 130, Config.Y_MAX*0.5 + 130, pathHeros);

		// Affichage de l'énergie et le nombre de carte de la pioche, de la défausse et en l'exil
		Affichage.texteGauche(40, Config.Y_MAX - 20, "Pioche : 10");
		Affichage.texteGauche(40, Config.Y_MAX - 45, "Energie : 3/3");
		Affichage.texteDroite(Config.X_MAX - 50, Config.Y_MAX - 20, "Defausse : 0");
		Affichage.texteDroite(Config.X_MAX - 50, Config.Y_MAX - 45, "Exil : 0");

		StdDraw.show(); //montre a l'ecran les changements demandés
	}

	public void initialDisplay() {
		AssociationTouches.init();
		Config.init();
		StdDraw.enableDoubleBuffering(); // rend l'affichage plus fluide: tout draw est mis en buffer et ne s'affiche qu'au prochain StdDraw.show();
		display();
	}

	public void update() {
		tourDuJoueur();
		System.out.println("Tour des monstres");
		salle.performerActionSalle(joueur);
		affichageModeTexte();
		
		
		if(!joueur.alive() || !salle.isMonstersAlive()){
			over = true;
		}
		

		
		//String toucheSuivante = AssociationTouches.trouveProchaineEntree(); //cette fonction boucle jusqu'a la prochaine entree de l'utilisateur
		//if (toucheSuivante.equals("A")) { 
		//	//TODO: deplacer le curseur vers le haut
		//	System.out.println("1");
		//	display();
		//	}
		//TODO: Ajouter les autres touches utiles avec la classe AssociationTouches
		//else {
		//	System.out.println("Autre touche");
		//	display();
		//}
		
	}

	/**
	 * Performe le tour du joueur
	 */
	public void tourDuJoueur(){
		salle.prepTourDeJoueur(joueur);
		System.out.println("Choisit une carte");
		boolean tourDuJoueur = true;
		while(tourDuJoueur){
			String toucheSuivante = AssociationTouches.trouveProchaineEntree();
			if (toucheSuivante.equals("1")){
				if (joueur.getCarteDeLaMain(0).getCout()<= joueur.getEnergie()){
					System.out.println("Vous avez choisie la carte 1 ");
					joueur.setCarteChoisie(0);
					choisir_cible();
					affichageModeTexte();
				}
				else{
					System.out.println("Pas assez d'energie");
				}
				
			}
			else if(toucheSuivante.equals("2")){
				if (joueur.getCarteDeLaMain(1).getCout()<= joueur.getEnergie()){
					System.out.println("Vous avez choisie la carte 2 ");
					joueur.setCarteChoisie(1);
					choisir_cible();
					affichageModeTexte();
				}
				else{
					System.out.println("Pas assez d'energie");
				}
			}
			else if(toucheSuivante.equals("Entrée")){
				tourDuJoueur = false;
			}

			else{
				System.out.println("Autre touche");
			}
			System.out.println("Appuyer sur la touche Entrée pour finir le tour");

		}
		
		
	}

	public void choisir_cible(){
		
		if (joueur.getCarteDeLaMain(joueur.getCarteChoisie()).getTypeDentiteApplicable() =="Heros" ){
			salle.performerActionsJoueur(joueur);
		}
		else{
			System.out.println("Choisit une cible");
			String toucheSuivante = AssociationTouches.trouveProchaineEntree();
			if (toucheSuivante.equals("1")){
				System.out.println("Vous avez choisi monstre 1");
				salle.setCibleDuJoueur(0);
				salle.performerActionsJoueur(joueur);
				
			}
			else if(toucheSuivante.equals("2")){
				System.out.println("Vous avez choisi montre 2");
				salle.setCibleDuJoueur(1);
				salle.performerActionsJoueur(joueur);
				
			}
			else{
				System.out.println("Autre touche");
			}
		}
		
	}

	public void affichageModeTexte(){
		afficheStatsJoueursModeTexte();
		affichageStatsSalleModeTexte();
		System.out.println("----------------------------");
	}

	public void afficheStatsJoueursModeTexte(){
		System.out.println("-------------");
		System.out.println(joueur);
		System.out.println("-------------");
	}

	public void affichageStatsSalleModeTexte(){
		System.out.println(salle);
	}

	public void affichageCarteMainsModeTexte(){

	}
}

