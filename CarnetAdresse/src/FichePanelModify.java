import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.*;

@SuppressWarnings("serial")
public class FichePanelModify extends JPanel implements ActionListener {

	UserGUI gui;
	
	
	Fiche fiche;
	
	JTextField entryNom;
	JTextField entryPrenom;
	JTextField entryTel;
	JTextField entryMail;
	
	JTextField entryNumero, entryCodePostal;
	JTextField entryNomRue, entryVille, entryPays;
	
	JButton boutonValider;
	JButton boutonAnnuler;

	
	public FichePanelModify ( UserGUI g, Fiche f ) {
		
		super();
		this.setSize(400, 600);
		
		this.gui = g;
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		
		this.entryNom = new JTextField();
		this.entryPrenom = new JTextField();

		this.entryTel = new JTextField();
		
		this.entryNumero = new JTextField();
		this.entryCodePostal = new JTextField();
		this.entryNomRue = new JTextField();
		this.entryVille = new JTextField();
		this.entryPays = new JTextField();
		
		this.entryMail = new JTextField();

		
		panel.add( new JLabel("Nom"), setGridBagConstraints(0, 0, 1, 1, 0, 0, gbc) );
		panel.add( entryNom, setGridBagConstraints(1, 0, 5, 1, 0, 0, gbc) );
		
		panel.add( new JLabel("Prenom"), setGridBagConstraints(0, 1, 1, 1, 0, 0, gbc) );
		panel.add( entryPrenom, setGridBagConstraints(1, 1, 5, 1, 0, 0, gbc) );
		
		panel.add( new JLabel("Adresse"), setGridBagConstraints(0, 2, 1, 1, 0, 0, gbc) );
		panel.add( entryNumero, setGridBagConstraints(1, 2, 1, 1, 0, 0, gbc) );
		panel.add( entryNomRue, setGridBagConstraints(2, 2, 1, 1, 0, 0, gbc) );
		panel.add( entryCodePostal, setGridBagConstraints(3, 2, 1, 1, 0, 0, gbc) );
		panel.add( entryVille, setGridBagConstraints(4, 2, 1, 1, 0, 0, gbc) );
		panel.add( entryPays, setGridBagConstraints(5, 2, 1, 1, 0, 0, gbc) );
		
		panel.add( new JLabel("Tel."), setGridBagConstraints(0, 3, 1, 1, 0, 0, gbc) );
		panel.add( entryTel, setGridBagConstraints(1, 3, 5, 1, 0, 0, gbc) );
		
		panel.add( new JLabel("eMail"), setGridBagConstraints(0, 4, 1, 1, 0, 0, gbc) );
		panel.add( entryMail, setGridBagConstraints(1, 4, 5, 1, 0, 0, gbc) );
		
		this.boutonValider = new JButton("Valider");
		this.boutonValider.setEnabled(false);
		panel.add( boutonValider, setGridBagConstraints(0, 5, 3, 1, 0, 0, gbc) );
		
		this.boutonAnnuler = new JButton("Annuler");
		this.boutonAnnuler.setEnabled(false);
		panel.add( boutonAnnuler, setGridBagConstraints(3, 5, 3, 1, 0, 0, gbc) );
		
		this.boutonValider.addActionListener(this);
		this.boutonAnnuler.addActionListener(this);

		this.add(panel);
		
		if ( f != null ) {
			this.fiche = f;

			this.afficherFicheEditeur(f);

		}
		else {
			
		}
		
		this.setVisible(true);

		
	}
	
	public void afficherFicheEditeur( Fiche f ) {
		
		this.fiche = f;
		
		this.entryNom.setText( f.getNom() );
		this.entryPrenom.setText(  f.getPrenoms().first() );
	
		this.entryTel.setText( f.getNumeroTel() );
				
		this.entryNumero.setText( String.valueOf( f.getAdresses().first().getNumero() ) );
		this.entryCodePostal.setText( String.valueOf( f.getAdresses().first().getCodePostal() ) );
		this.entryNomRue.setText( f.getAdresses().first().getNomRue() );
		this.entryVille.setText( f.getAdresses().first().getVille() );
		this.entryPays.setText( f.getAdresses().first().getPays() );
	
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
		
		this.fiche.setNumeroTel( this.entryTel.getText() );
		
		this.gui.visualiserFiche(this.fiche);
		this.repaint();
	}
	
	public GridBagConstraints setGridBagConstraints (int gx, int gy,int sx, int sy, int wx, int wy, GridBagConstraints gbc){
		
		gbc.gridx = gx;
		gbc.gridy = gy;
		gbc.gridwidth = sx;
		gbc.gridheight = sy;
		gbc.weightx = wx;
		gbc.weighty = wy;
		
		gbc.fill = GridBagConstraints.BOTH;
		gbc.fill = GridBagConstraints.HORIZONTAL;
		return gbc;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		if ( arg0.getSource() instanceof JButton ) {
			System.out.println("OK");
			
			if ( ((JButton) arg0.getSource()).equals(this.boutonValider) ) {
				this.validerChangements();
			}
			else if ( ((JButton) arg0.getSource()).equals(this.boutonAnnuler) ) {
				this.afficherFicheEditeur(this.fiche);
			}
		}
		
	}	
	
}
