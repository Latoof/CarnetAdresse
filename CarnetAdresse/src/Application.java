public class Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Carnet carnet = new Carnet();
		
		Fiche fich1 = new Fiche("Ouairy", "Maxime", "0123456789", new Adresse(14, "rue de la fouine en puissance", "Nantes", 44000, "France"));
		fich1.addEmail("aaa@bidon.dot.com");
		
		Fiche fich2 = new Fiche("Lenogue", "Matthieu", "Pierre", "Michel", "0241693721", new Adresse(25, "rue de la houssiniere", "Nantes", 44000, "France"));
		fich2.addEmail("aca@bidon.dot.com");
		
		Fiche fich3 = new Fiche("Gautier", "Chouquette", "Quentin", "0123456789", new Adresse(14, "rue de la fistiniere", "Nantes", 44000, "France"));
		
		fich3.addEmail("aba@bidon.dot.com");
		fich3.addEmail("trol@lil.ol");
		fich3.addEmail("kikoo@vendee.com");

		carnet.addFiche( fich1 );
		carnet.addFiche( fich2 );
		carnet.addFiche( fich3 );

//		System.out.println(fich1);
//		System.out.println(fich2);
//		System.out.println(fich3);
		
		UserGUI f = new UserGUI( carnet );
		//FicheFrame ff = new FicheFrame(fich1);
	}

}
