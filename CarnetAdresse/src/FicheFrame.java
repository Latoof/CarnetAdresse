import java.awt.FlowLayout;
import java.awt.GridLayout;


import javax.swing.*;

public class FicheFrame extends JFrame {

	/*
	String 			nom, numeroTel;
	TreeSet<String>	prenoms;
	TreeSet<Adresse> adresses;
	TreeSet<String>	emails;
	*/
	Fiche fiche;
	
	JTextField entryNom;
	JTextField entryPrenom;
	JTextField entryTel;
	JTextField entryAdresse;
	JTextField entryMail;

	
	public FicheFrame ( Fiche f ) {
		
		super();
		this.setSize(200, 600);
		
		if ( f != null ) {
			this.fiche = f;
		}
		else {
			//this.fiche = new Fiche(dq);
		}
		
		this.setTitle( f.getNom() + " " + f.getPrenoms().first() );

		JPanel panel = new JPanel();
		//FlowLayout vLayout = new FlowLayout();
		
		GridLayout gLayout = new GridLayout(5, 2);
		panel.setLayout(gLayout);
		
		
		this.entryNom = new JTextField( this.fiche.getNom() );
		this.entryPrenom = new JTextField( this.fiche.getPrenoms().first() );

		this.entryTel = new JTextField( this.fiche.getNumeroTel() );
		this.entryAdresse = new JTextField( this.fiche.getAdresses().first().toString() );

		this.entryMail = new JTextField( this.fiche.getEmails().first() );

		
		panel.add( new JLabel("Nom") );
		panel.add( entryNom );
		
		panel.add( new JLabel("Prenom") );
		panel.add( entryPrenom );
		
		panel.add( new JLabel("Adresse") );
		panel.add( entryAdresse );
		
		panel.add( new JLabel("Tel.") );
		panel.add( entryTel );
		
		panel.add( new JLabel("eMail") );
		panel.add( entryMail );
		
		
		this.setContentPane(panel);
		this.setVisible(true);
		
	}
	
	
	
	
}
