import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

public class FichePanel extends JPanel {

	Fiche fiche;
	
	JLabel entryNom;
	JLabel entryPrenom, entryPrenom2, entryPrenom3;
	JLabel entryTel;
	JLabel entryAdresse;
	JLabel entryMail;
	
	public FichePanel ( Fiche f ) {
		
		super();
		JPanel panel = new JPanel(new GridBagLayout());
		
		this.setBackground(Color.MAGENTA);
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		this.entryNom = new JLabel();
		this.entryPrenom = new JLabel();
		this.entryPrenom2 = new JLabel();
		this.entryPrenom3 = new JLabel();

		this.entryTel = new JLabel();
		this.entryAdresse = new JLabel();

		this.entryMail = new JLabel();
		
		panel.add( new JLabel("Nom"), setGridBagConstraints(0, 0, 1, 1, 0, 0, gbc) );
		panel.add( entryNom, setGridBagConstraints(1, 0, 1, 1, 0, 0, gbc) );
		
		panel.add( new JLabel("Prenom"), setGridBagConstraints(0, 1, 1, 3, 0, 0, gbc) );
		panel.add( entryPrenom, setGridBagConstraints(1, 1, 1, 1, 0, 0, gbc) );
		panel.add( entryPrenom2, setGridBagConstraints(1, 2, 1, 1, 0, 0, gbc) );
		panel.add( entryPrenom3, setGridBagConstraints(1, 3, 1, 1, 0, 0, gbc) );

		panel.add( new JLabel("Adresse"), setGridBagConstraints(0, 4, 1, 1, 0, 0, gbc) );
		panel.add( entryAdresse, setGridBagConstraints(1, 4, 1, 1, 0, 0, gbc) );


		panel.add( new JLabel("Tel."), setGridBagConstraints(0, 5, 1, 1, 0, 0, gbc) );
		panel.add( entryTel, setGridBagConstraints(1, 5, 1, 1, 0, 0, gbc) );
		
		panel.add( new JLabel("eMail"), setGridBagConstraints(0, 6, 1, 1, 0, 0, gbc) );
		panel.add( entryMail, setGridBagConstraints(1, 6, 1, 1, 0, 0, gbc) );
		
		
		
				
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
		this.entryAdresse.setText( String.valueOf( f.getAdresses().first().getNumero() ) 
				+ " " + f.getAdresses().first().getNomRue() 
				+ " " + String.valueOf( f.getAdresses().first().getCodePostal() ) 
				+ " " + f.getAdresses().first().getVille()
				+ " " + f.getAdresses().first().getPays() );
		
		 
	
		this.entryMail.setText( f.getEmails().first() );
	
	}
	
	public GridBagConstraints setGridBagConstraints (int gx, int gy,int sx, int sy, int wx, int wy, GridBagConstraints gbc){
		
		gbc.gridx = gx;
		gbc.gridy = gy;
		gbc.gridwidth = sx;
		gbc.gridheight = sy;
		gbc.weightx = wx;
		gbc.weighty = wy;
		
		gbc.fill = GridBagConstraints.BOTH;
		
		return gbc;
	}
	
}
