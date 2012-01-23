public class Application {

	public static void main(String[] args) {

		/* Exemples */
		
		Carnet carnet = new Carnet();
		
		Fiche fich1 = new Fiche("Ouairy", "Maxime", "0123456789", new Adresse(14, "rue de la maraîchère", "Nantes", 44000, "France"));
		fich1.addEmail("maxime.ouairy@etu.univ-nantes.com");
		
		Fiche fich2 = new Fiche("Lenogue", "Matthieu", "Pierre", "Michel", "0241693721", new Adresse(25, "rue de la houssinière", "Nantes", 44000, "France"));
		fich2.addEmail("matthieu.lenogue@etu.univ-nantes.com");
		
		Fiche fich3 = new Fiche("Gautier", "Quentin", "0123456789", new Adresse(14, "rue de la pointe", "Nantes", 44000, "France"));
		fich3.addEmail("quentin.gautier@etu.univ-nantes.com");
		fich3.addEmail("q.gautier@gmail.com");

		carnet.addFiche( fich1 );
		carnet.addFiche( fich2 );
		carnet.addFiche( fich3 );

		
		UserGUI f = new UserGUI( carnet );
	}

}
