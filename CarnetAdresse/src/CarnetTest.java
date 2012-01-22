
import junit.framework.TestCase;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.JUnitCore;



public class CarnetTest extends TestCase {

	Carnet carnetTest;
	Fiche f1;
	Fiche f2;
	Fiche f3;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		
		this.f1 = new Fiche("Gautier", "Chouquette", "Quentin", "0123456789", new Adresse(14, "rue de la fistiniere", "Nantes", 44000, "France"));
		this.f1.addEmail("test@email.fr");

		this.f2 = new Fiche("AAA", "Chouquette", "Quentin", "0123456789", new Adresse(14, "rue de la fdfs", "Nantes", 44000, "France"));
		this.f2.addEmail("test2@email.fr");
		
		this.f3 = new Fiche("BBB", "Chouquette", "Quentin", "0123456789", new Adresse(14, "rue de la chose", "Nantes", 44000, "France"));
		this.f3.addEmail("test3@email.fr");
		
		this.carnetTest = new Carnet();
		this.carnetTest.addFiche(f1);
		this.carnetTest.addFiche(f2);
		this.carnetTest.addFiche(f3);
	}
	
	@Test
	public void testSuppressionFiche() {
		
		assertTrue( this.carnetTest.contains(f3) );
		this.carnetTest.delFiche(f3);
		assertFalse( "Erreur suppresion fiche", this.carnetTest.contains(f3) );
		
	}

	@Test
	public void testAjoutFiche() {
		
		int nbFichesBefore = this.carnetTest.getNbFiches();
		
		Fiche nouvelleFiche = new Fiche("BBB", "Test", "Quentin", "0123456789", new Adresse(14, "rue de la fdfs", "Nantes", 44000, "France"));
		nouvelleFiche.addEmail("test@freemobile.fr");
		
		this.carnetTest.addFiche( nouvelleFiche );
		
		assertEquals( this.carnetTest.getNbFiches(), nbFichesBefore+1);
		
	}
	
	@After
	public void tearDown() throws Exception {
	}

	public static void main(String[] args) {
		JUnitCore.runClasses(CarnetTest.class);
	}
}
