import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JPopupMenu;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;

import abbot.finder.ComponentNotFoundException;
import abbot.finder.MultipleComponentsFoundException;
import abbot.finder.matchers.ClassMatcher;
import abbot.finder.matchers.JMenuMatcher;
import abbot.tester.JButtonTester;
import abbot.tester.JListLocation;
import abbot.tester.JListTester;
import abbot.tester.JMenuItemTester;
import junit.extensions.abbot.ComponentTestFixture;
import junit.extensions.abbot.TestHelper;


public class UserGUITest extends ComponentTestFixture  {

	UserGUI gui;
	Carnet carnetTest;
	Fiche f1;
	Fiche f2;
	Fiche f3;
	
	
	public UserGUITest( String name ) {
		super(name);

	}
	
    @BeforeClass
    public static void setUpClass() throws Exception {
    	
    }
    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
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

		if ( this.gui == null ) {
			this.gui = new UserGUI( this.carnetTest );
		}

    }
    
    @After
    public void tearDown() {
    	System.out.println("Finised");
    }

	/**
	 * Teste si la selection d'une liste implique bien le changement de la vue de droite.
	 */
	public void testOnListSelection() {

		
		//Component list =findListePanel();
		
		Component liste = null;
		try {
			liste = getFinder().find(new ClassMatcher(JList.class));
		} catch (ComponentNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MultipleComponentsFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JListTester jt = new JListTester();
		
		jt.actionSelectRow( liste, new JListLocation(1));
		//System.out.println("Val ="+(Fiche)(((JList) list).getSelectedValue()));
		
		/* On verifie que la ligne selectionnee correspond bien a l'objet (Fiche) concerne */
		assertEquals("Erreur entre la liste et son modele",(Fiche)(((JList) liste).getSelectedValue()), f2);
		
		
		Component fichePanel = null;
		try {
			fichePanel = getFinder().find(new ClassMatcher(FichePanel.class));
		} catch (ComponentNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MultipleComponentsFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/* Plus loin : On verifie que la vue dans le panel de droite affiche bien la bonne fiche */
		assertEquals("Erreur entre la selection et la Fiche affichee",(Fiche)(((FichePanel) fichePanel).getFicheCourante()), f2);

	}
	
	/**
	 * Teste si le clic sur le bouton "nouvelle fiche" déclenche bien l'apparition concrete d'une fiche vide
	 */
	public void testClicBoutonNouvelleFiche() {
		


		
		Component listePanel = null;
		try {
			listePanel = getFinder().find(new ClassMatcher(ListePanel.class));
		} catch (ComponentNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MultipleComponentsFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if ( listePanel != null) {
			
			int nbFichesBefore = this.carnetTest.getNbFiches();

		
			JButtonTester buttonTester = new JButtonTester();
			buttonTester.actionClick( ((ListePanel)listePanel).getbNouveau() );
			
			
			assertEquals("Erreur lors de l'ajout d'une fiche", this.gui.getCarnet().getNbFiches(), nbFichesBefore+1);
		}
		
		
		
	}
	
	/**
	 * Teste si le clic sur le bouton "supprimer fiche" déclenche bien la disparition de la Fiche qui était sélectionnée
	 */
	public void testClicBoutonSupprimer() {
		

		Component liste = null;
		try {
			liste = getFinder().find(new ClassMatcher(JList.class));
		} catch (ComponentNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MultipleComponentsFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JListTester jt = new JListTester();
		
		jt.actionSelectRow( liste, new JListLocation(1));
		
		
		Component listePanel = null;
		try {
			listePanel = getFinder().find(new ClassMatcher(ListePanel.class));
		} catch (ComponentNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MultipleComponentsFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if ( listePanel != null) {
			
			Fiche ficheASupprimer = (Fiche) ((ListePanel)listePanel).getListe().getSelectedValue();
			int nbFichesBefore = this.gui.getCarnet().getNbFiches();

			// Le carnet contient bien la fiche a supprimer
			assertTrue( this.gui.getCarnet().contains(ficheASupprimer) );
		
			JButtonTester buttonTester = new JButtonTester();
			buttonTester.actionClick( ((ListePanel)listePanel).getbSupprimer() );
			
			// Le carnet ne contient plus la fiche a supprimer
			assertFalse( this.gui.getCarnet().contains(ficheASupprimer) );

			/* Le clic sur "Supprimer" est impossible s'il n'y aucun elts dans la liste */
			assertEquals("Erreur lors de la suppression d'une fiche", this.gui.getCarnet().getNbFiches(), nbFichesBefore-1);
		}
		
		
		
	}
	
	/**
	 * Teste si l'action "nouveau carnet" détruit bien toutes les fichies et crée une Fiche vide.
	 */
	public void testMenuNouveauCarnet() {
		

			
        JPopupMenu popup = new JPopupMenu();
        popup.add(this.gui.getMenuNouveauCarnet());
        popup.setVisible(true);
			JMenuItemTester menuItemTester = new JMenuItemTester();
			
			menuItemTester.actionClick( this.gui.getMenuNouveauCarnet() );
			
			/* Le clic sur "Supprimer" est impossible s'il n'y aucun elts dans la liste */
			assertEquals("Erreur de la creation d'un nouveau carnet : "+this.gui.getCarnet().getNbFiches(), 1, this.gui.getCarnet().getNbFiches());
		
		
		
		
	}
	
	public static void main(String[] args) {
		 TestHelper.runTests(args, UserGUITest.class);
	}
	
}
