import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.*;

/**
 * 
 * Limites : On ne visualisera/modifiera que les premiers elements des listes multiples (email, prenoms, etc)
 * @author Latoof
 *
 */

@SuppressWarnings("serial")
public class UserGUI extends JFrame implements ActionListener {

	private Carnet 				carnet;
	
	private ListePanel			listePanel;
	private FichePanel 			fichePanel;
	private FichePanelModify 	fichePanelModify;
	
	private JMenuItem itemNouveau;
	private JMenuItem itemOuvrir;
	private JMenuItem itemEnregistrer;
	private JMenuItem itemQuitter;
	
	public UserGUI ( Carnet c ) {
		
		super("Carnet d'adresse");
		
		this.carnet = c;
		
		// Permet d'activer le mecanisme de fermeture de la fenetre
		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e){
			System.exit(0);
			}
		};
		
		this.addWindowListener( l );
		
		
		this.setSize(new Dimension(800, 600));
		this.setMinimumSize( new Dimension(640, 425) );
		this.setVisible(true);
		
		
		JMenuBar menuBar = new JMenuBar();
			JMenu menuFichier = new JMenu("Fichier");
				itemNouveau = new JMenuItem("Nouveau carnet");
				itemNouveau.setAccelerator(KeyStroke.getKeyStroke(
				        KeyEvent.VK_1, ActionEvent.ALT_MASK));
				itemNouveau.getAccessibleContext().setAccessibleDescription(
				        "Cree un nouveau Carnet. Les donnees actuelles sont perdues.");
				itemOuvrir = new JMenuItem("Ouvrir fichier");
				itemOuvrir.setAccelerator(KeyStroke.getKeyStroke(
				        KeyEvent.VK_2, ActionEvent.ALT_MASK));
				itemOuvrir.getAccessibleContext().setAccessibleDescription(
				        "Ouvre un Carnet a partir d'un fichier");
				itemEnregistrer = new JMenuItem("Enregistrer carnet");
				itemEnregistrer.setAccelerator(KeyStroke.getKeyStroke(
				        KeyEvent.VK_3, ActionEvent.ALT_MASK));
				itemEnregistrer.getAccessibleContext().setAccessibleDescription(
				        "Enregistre le Carnet dans un fichier");
				itemQuitter = new JMenuItem("Quitter l'application");
				itemQuitter.setAccelerator(KeyStroke.getKeyStroke(
				        KeyEvent.VK_4, ActionEvent.ALT_MASK));
				itemQuitter.getAccessibleContext().setAccessibleDescription(
				        "Quitte l'application. Les données non-enregistrees sont perdues");
			
				itemNouveau.addActionListener(this);
				itemOuvrir.addActionListener(this);
				itemEnregistrer.addActionListener(this);
				itemQuitter.addActionListener(this);
		
				menuFichier.add(itemNouveau);
				menuFichier.addSeparator();
				menuFichier.add(itemOuvrir);
				menuFichier.addSeparator();
				menuFichier.add(itemEnregistrer);
				menuFichier.addSeparator();
				menuFichier.add(itemQuitter);

			menuBar.add(menuFichier);
		this.setJMenuBar(menuBar);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout( new BorderLayout());
		
		/* Contiendra la liste des Fiches */
		listePanel = new ListePanel(this, this.carnet);
		
		
		JPanel rightPanel = new JPanel(new GridLayout(2, 1));
		
		/* Contient la vue d'une Fiche */
		fichePanel = new FichePanel( this.carnet.getFicheFromIndex(0) );
		
		/* Contient l'éditeur de Fiche */
		fichePanelModify = new FichePanelModify( this, this.carnet.getFicheFromIndex(0) );
		
		rightPanel.add(fichePanel);
		rightPanel.add(fichePanelModify);
		
		mainPanel.add(listePanel, BorderLayout.WEST);
		mainPanel.add(rightPanel, BorderLayout.CENTER);		
		
		this.setContentPane(mainPanel);
		
		this.setVisible(true);
	}
	

	
	public Carnet getCarnet() {
		return carnet;
	}



	public void visualiserFiche( Fiche f ) {
		
		this.fichePanel.afficherFicheVisualiseur( f );
		this.fichePanelModify.afficherFicheEditeur( f );
		this.fichePanel.repaint();
		
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {

		if ( arg0.getSource() instanceof JMenuItem ) {
			System.out.println("OK");
			
			if ( ((JMenuItem) arg0.getSource()).equals(this.itemNouveau) ) {
				
				this.carnet = new Carnet();
				Fiche nouvelleFiche = new Fiche("Doe", "John", "0123456789", new Adresse(00,"", "", 00000, ""));
				this.carnet.addFiche( nouvelleFiche );
				this.listePanel.genList( this.carnet );
				this.visualiserFiche( nouvelleFiche );
				
			}
			
			else if ( ((JMenuItem) arg0.getSource()).equals(this.itemOuvrir) ) {
				
				JFrame fr = new JFrame();
				JFileChooser fc = new JFileChooser();
				fc.setApproveButtonText("Ouvrir");

				fr.add(fc);
				fr.pack();
				fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				   int returnVal = fc.showOpenDialog(fr);
				    if(returnVal == JFileChooser.APPROVE_OPTION) {

				    	this.carnet.fromXML( fc.getSelectedFile() );
				    	this.listePanel.genList();
				    	
				    }
				
			}
			else if ( ((JMenuItem) arg0.getSource()).equals(this.itemEnregistrer) ) {
				JFrame fr = new JFrame();
				JFileChooser fc = new JFileChooser();
				fc.setApproveButtonText("Enregistrer");
				fr.add(fc);
				fr.pack();
				fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				   int returnVal = fc.showOpenDialog(fr);
				    if(returnVal == JFileChooser.APPROVE_OPTION) {

				    	this.carnet.toXML( fc.getSelectedFile().getAbsolutePath() );

				    }
			}
			
			else if ( ((JMenuItem) arg0.getSource()).equals(this.itemQuitter) ) {
				
				this.dispose();
				
			}
		
		}
	}
	
	public JMenuItem getMenuNouveauCarnet() {
		return this.itemNouveau;
	}
	
}
