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

public class UserGUI extends JFrame{

	private Carnet 				carnet;
	private FichePanel 			fichePanel;
	private FichePanelModify 	fichePanelModify;
	
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
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout( new BorderLayout());
		
		ListePanel listePanel = new ListePanel(this, this.carnet);
		
		
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
	
	
}
