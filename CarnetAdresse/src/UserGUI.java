import javax.swing.*;

import java.awt.event.*;
import java.util.Vector;


public class UserGUI extends JFrame{

	private Carnet carnet;
	
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
		
		mainPanel.add( genList() );
	
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
		
		new FicheFrame( this.carnet.getFicheFromIndex( index ) );
		
	}
	
	
}
