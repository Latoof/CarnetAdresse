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

	private Carnet carnet;
	private FichePanel fichePanel;
	
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
		
		
		this.setSize(new Dimension(500, 400));
		this.setMinimumSize( new Dimension(500, 400) );
		this.setVisible(true);
		
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout( new BorderLayout() );
		
		JPanel paneList = new JPanel(new BorderLayout());
		paneList.setBackground(Color.RED);
		
		JPanel paneButton = new JPanel(new GridLayout(1, 2));
		JButton add = new JButton("Add");
		JButton edit = new JButton("edit");
		
		paneButton.add(add);
		paneButton.add(edit);
		
		paneList.add(genList(), BorderLayout.NORTH);
		paneList.add(paneButton, BorderLayout.SOUTH);
		
		fichePanel = new FichePanel( this.carnet.getFicheFromIndex(0) );
		fichePanel.setBackground(Color.BLUE);
		
		mainPanel.add(paneList, BorderLayout.WEST);
		mainPanel.add(fichePanel, BorderLayout.EAST);
		
		
		
//		mainPanel.add( genList() );
//		mainPanel.add( fichePanel );
		
		
		this.setContentPane(mainPanel);
		
		this.setVisible(true);
	}
	
	public JList genList() {
		
		Vector<String> v = new Vector<String>();
		
		
		for ( int i=0; i < this.carnet.getListeFiches().size(); i++ ) {
			v.add( this.carnet.getListeFiches().get(i).getNom() + " " + this.carnet.getListeFiches().get(i).getPrenoms().first().toString() );
		}
		
		JList list = new JList( v ) ;
		
		list.addListSelectionListener( new SelectionListener(this) );
		
		return list;
	}
	
	public void popFiche( int index ) {
		
		this.fichePanel.voirFiche( this.carnet.getFicheFromIndex( index ) );
		this.fichePanel.repaint();
		
	}
	
	
}
