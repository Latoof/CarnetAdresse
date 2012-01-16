import javax.swing.*;

import java.awt.FlowLayout;
import java.awt.event.*;
import java.util.Vector;


public class UserGUI extends JFrame{

	private Carnet carnet;
	private FichePanel fichePanel;
	
	public UserGUI ( Carnet c ) {
		
		super("Carnet d'adresse");
		
		this.carnet = c;
		
		WindowListener l = new WindowAdapter() {
			public void windowClosing(WindowEvent e){
			System.exit(0);
			}
		};
		this.addWindowListener( l );
		
		this.setSize(400, 600);
		this.setVisible(true);
		
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout( new FlowLayout() );
		
		fichePanel = new FichePanel( this.carnet.getFicheFromIndex(0) );
		
		
		mainPanel.add( genList() );
		mainPanel.add( fichePanel );
	
		/*
		JTextField nom = new JTextField("Nom");
		JTextField prenom = new JTextField("Prénom");
		JTextField adresse = new JTextField("Adresse");
		JTextField mail = new JTextField("Mail");
		
		mainPanel.add(new JLabel("Nom"));
		mainPanel.add(nom);
		mainPanel.add(prenom);
		mainPanel.add(adresse);
		mainPanel.add(mail);
		*/
		
		
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
