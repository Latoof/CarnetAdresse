import java.awt.FlowLayout;
import java.awt.GridLayout;


import javax.swing.*;

public class FichePanel extends JPanel {

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

	
	public FichePanel ( Fiche f ) {
		
		super();
		this.setSize(200, 600);
		
		JPanel panel = new JPanel();
		//FlowLayout vLayout = new FlowLayout();
		
		GridLayout gLayout = new GridLayout(5, 2);
		panel.setLayout(gLayout);
		
		
		this.entryNom = new JTextField();
		this.entryPrenom = new JTextField();

		this.entryTel = new JTextField();
		this.entryAdresse = new JTextField();

		this.entryMail = new JTextField();

		
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
		
		
		this.add(panel);
		
		if ( f != null ) {
			this.fiche = f;

			

	
		}
		else {
			
			
			
		}
		
		this.setVisible(true);

		
	}
	
	public void voirFiche( Fiche f ) {
		
		this.entryNom.setText( f.getNom() );
		this.entryPrenom.setText(  f.getPrenoms().first() );
	
		this.entryTel.setText(  f.getNumeroTel() );
		this.entryAdresse.setText( f.getAdresses().first().toString() );
	
		this.entryMail.setText(f.getEmails().first());
	
	}
	
	
}
