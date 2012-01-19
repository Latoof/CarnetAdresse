import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.*;
import java.util.Vector;

/**
 * 
 * Limites : On ne visualisera/modifiera que les premiers elements des listes multiples (email, prenoms, etc)
 * @author Latoof
 *
 */

public class UserGUI extends JFrame implements ActionListener {

	private Carnet 				carnet;
	
	private ListePanel			listePanel;
	private FichePanel 			fichePanel;
	private FichePanelModify 	fichePanelModify;
	
	private JMenuItem itemNouveau;
	private JMenuItem itemOuvrir;
	private JMenuItem itemEnregistrer;
	
	public UserGUI ( Carnet c ) {
		
		super("Carnet d'adresse");
		
		this.carnet = c;
		
		//Nous permet d'activer le mécanisme de fermeture de la fenetre
		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e){
			System.exit(0);
			}
		};
		
		this.addWindowListener( l );
		
		
		this.setSize(new Dimension(800, 600));
		this.setMinimumSize( new Dimension(550, 425) );
		this.setVisible(true);
		
		
		JMenuBar menuBar = new JMenuBar();
			JMenu menuFichier = new JMenu("Fichier");
				itemNouveau = new JMenuItem("Nouveau carnet");
				itemNouveau.setAccelerator(KeyStroke.getKeyStroke(
				        KeyEvent.VK_1, ActionEvent.ALT_MASK));
				itemNouveau.getAccessibleContext().setAccessibleDescription(
				        "This doesn't really do anything");
				itemOuvrir = new JMenuItem("Ouvrir fichier");
				itemOuvrir.setAccelerator(KeyStroke.getKeyStroke(
				        KeyEvent.VK_2, ActionEvent.ALT_MASK));
				itemOuvrir.getAccessibleContext().setAccessibleDescription(
				        "This doesn't really do anything");
				itemEnregistrer = new JMenuItem("Enregistrer carnet");
				itemEnregistrer.setAccelerator(KeyStroke.getKeyStroke(
				        KeyEvent.VK_3, ActionEvent.ALT_MASK));
				itemEnregistrer.getAccessibleContext().setAccessibleDescription(
				        "This doesn't really do anything");
			
				itemNouveau.addActionListener(this);
				itemOuvrir.addActionListener(this);
				itemEnregistrer.addActionListener(this);

		
				menuFichier.add(itemNouveau);
				menuFichier.addSeparator();
				menuFichier.add(itemOuvrir);
				menuFichier.addSeparator();
				menuFichier.add(itemEnregistrer);
			menuBar.add(menuFichier);
		this.setJMenuBar(menuBar);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout( new BorderLayout());
		
		listePanel = new ListePanel(this, this.carnet);
		
		
		JPanel rightPanel = new JPanel(new GridLayout(2, 1));
		
		fichePanel = new FichePanel( this.carnet.getFicheFromIndex(0) );
		//fichePanel.setBackground(Color.BLUE);
		
		fichePanelModify = new FichePanelModify( this.carnet.getFicheFromIndex(0) );
		fichePanelModify.setBackground(Color.GREEN);
		
		rightPanel.add(fichePanel);
		rightPanel.add(fichePanelModify);
		
		mainPanel.add(listePanel, BorderLayout.WEST);
		mainPanel.add(rightPanel, BorderLayout.CENTER);		
		
		this.setContentPane(mainPanel);
		
		this.setVisible(true);
	}
	

	
	public void popFiche( Fiche f ) {
		
		this.fichePanel.voirFiche( f );
		this.fichePanel.repaint();
		
	}



	@Override
	public void actionPerformed(ActionEvent arg0) {

		if ( arg0.getSource() instanceof JMenuItem ) {
			System.out.println("OK");
			
			if ( ((JMenuItem) arg0.getSource()).equals(this.itemOuvrir) ) {
				
				JFrame fr = new JFrame();
				JFileChooser fc = new JFileChooser();
				fc.setApproveButtonText("Ouvrirmm");

				fr.add(fc);
				fr.pack();
				fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//fr.setVisible(true);
				   int returnVal = fc.showOpenDialog(fr);
				    if(returnVal == JFileChooser.APPROVE_OPTION) {

				    	this.carnet.fromXML( fc.getSelectedFile() );
				    	this.listePanel.genList();
				    	
				    }
				
			}
			else if ( ((JMenuItem) arg0.getSource()).equals(this.itemEnregistrer) ) {
				JFrame fr = new JFrame();
				JFileChooser fc = new JFileChooser();
				//fc.setDialogType(JFileChooser.SAVE_DIALOG);
				fc.setApproveButtonText("Enregistrer");
				fr.add(fc);
				fr.pack();
				fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//fr.setVisible(true);
				   int returnVal = fc.showOpenDialog(fr);
				    if(returnVal == JFileChooser.APPROVE_OPTION) {

				    	this.carnet.toXML( fc.getSelectedFile().getAbsolutePath() );

				    }
			}
		
		}
	}
	
	
}
