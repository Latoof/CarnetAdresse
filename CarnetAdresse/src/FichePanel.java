import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

public class FichePanel extends JPanel implements ActionListener {

	Fiche fiche;
	
	JTextField entryNom;
	JTextField entryPrenom;
	JTextField entryTel;
	JTextField entryAdresse;
	JTextField entryMail;
	
	JButton boutonValider;
	JButton boutonAnnuler;

	
	public FichePanel ( Fiche f ) {
		
		super();
		this.setSize(400, 600);
		
		JPanel panel = new JPanel();
		//FlowLayout vLayout = new FlowLayout();
		
		GridLayout gLayout = new GridLayout(8, 2);
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
		
		this.boutonValider = new JButton("Valider");
		this.boutonValider.setEnabled(false);
		panel.add( boutonValider );
		
		this.boutonAnnuler = new JButton("Annuler");
		this.boutonAnnuler.setEnabled(false);
		panel.add( boutonAnnuler );
		
		this.boutonValider.addActionListener(this);
		this.boutonAnnuler.addActionListener(this);

		this.add(panel);
		
		if ( f != null ) {
			this.fiche = f;

			this.voirFiche(f);

	
		}
		else {
			
			
			
		}
		
		this.setVisible(true);

		
	}
	
	public void voirFiche( Fiche f ) {
		
		this.fiche = f;
		
		this.entryNom.setText( f.getNom() );
		this.entryPrenom.setText(  f.getPrenoms().first() );
	
		this.entryTel.setText( f.getNumeroTel() );
		this.entryAdresse.setText( "(Ici l'adresse de "+f.getPrenoms().first()+")" );
	
		this.entryMail.setText( f.getEmails().first() );
	
		this.boutonValider.setEnabled(true);
		this.boutonAnnuler.setEnabled(true);
		
	}

	public void validerChangements() {
		
		this.fiche.setNom( this.entryNom.getText() );
		
		this.fiche.getPrenoms().pollFirst();
		this.fiche.getPrenoms().add( this.entryPrenom.getText() );
		
		// this.fiche.getAdresses().pollFirst();
		// this.fiche.getAdresses().add( new Adresse( this.entryAdresse.getText( ) ) );
		
		this.fiche.getEmails().pollFirst();
		this.fiche.getEmails().add( this.entryMail.getText() );
		
	}
	
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if ( arg0.getSource() instanceof JButton ) {
			System.out.println("OK");
			
			if ( ((JButton) arg0.getSource()).equals(this.boutonValider) ) {
				this.validerChangements();
			}
			else if ( ((JButton) arg0.getSource()).equals(this.boutonAnnuler) ) {
				this.voirFiche(this.fiche);
			}
		}
		
	}
	
	
}
