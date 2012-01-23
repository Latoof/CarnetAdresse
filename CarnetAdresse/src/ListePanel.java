import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


@SuppressWarnings("serial")
public class ListePanel extends JPanel implements ActionListener, ListSelectionListener {

	private UserGUI gui;
	@SuppressWarnings("rawtypes")
	private JList liste;
	@SuppressWarnings("rawtypes")
	private DefaultListModel listeM;
	private Carnet carnet;
	private JButton bNouveau;
	private JButton bSupprimer;
	private JButton bComparer;

	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ListePanel( UserGUI g, Carnet c ) {
		
		this.gui = g;
		this.carnet = c;
		this.listeM = new DefaultListModel();
		this.liste = new JList( listeM ) ;

		this.setLayout(new BorderLayout());
		this.setBackground(Color.RED);

		this.genList();
		
		
		JPanel paneButton = new JPanel(new GridLayout(1, 2));
		this.bNouveau = new JButton("Nouveau");
		this.bSupprimer = new JButton("Supprimer");
		this.bComparer = new JButton("Comparer");
		
		bNouveau.addActionListener(this);
		bSupprimer.addActionListener(this);
		bComparer.addActionListener(this);
		
		paneButton.add(bNouveau);
		paneButton.add(bSupprimer);
		paneButton.add(bComparer);
		
		this.add(new JScrollPane(this.liste), BorderLayout.CENTER);
		this.add(paneButton, BorderLayout.SOUTH);
	
		this.updateButtons();
		this.liste.addListSelectionListener( this );

		
	}
	
	@SuppressWarnings("unchecked")
	public void actionPerformed(ActionEvent arg0) {
	/* On a decide de regrouper dans cette methode tout ce qui concerne le clic sur les bouttons */
		
		if ( arg0.getSource() instanceof JButton ) {
			
			if ( ((JButton) arg0.getSource()).equals(this.bNouveau) ) {
				
				/* On a convenu d'une "Fiche par defaut" generee automatiquement */
				Fiche nouvelleFiche = new Fiche("nNom", "n_Prenom", "0240667799", new Adresse(12,"rue du paradis", "AuroVille", 65489, "Inde"));
				this.carnet.addFiche( nouvelleFiche );
				this.listeM.addElement(nouvelleFiche);

			}
			else if ( ((JButton) arg0.getSource()).equals(this.bSupprimer) ) {
				
				int nbSelected = liste.getSelectedIndices().length;
				
				/* Si une seule Fiche selectionnee, se contente de recuperer la valeur courante */
				if ( nbSelected == 1 ) {
				
					Fiche ficheASupprimer = (Fiche) this.liste.getSelectedValue();
					
					this.carnet.delFiche( ficheASupprimer);
					listeM.removeElement( ficheASupprimer );				
					
				}
				
				/* Le processus est un peu different lorsque plusieurs fiches sont selectionnees */
				else if ( nbSelected > 1 ) {
					
					@SuppressWarnings("deprecation") //JRE7
					Object[] fichesASupprimer = this.liste.getSelectedValues();
					
					/* Pour chaque Fiche selectionnee. */
					for ( int i = 0; i < nbSelected; i++ ) {
						
						Fiche ficheASupprimer = (Fiche) fichesASupprimer[i];
						
						this.carnet.delFiche( ficheASupprimer);
						listeM.removeElement( ficheASupprimer );
						
					}
					
				}
				
			}
			else if ( ((JButton) arg0.getSource()).equals(this.bComparer) ) {
				
				if ( this.liste.getSelectedIndices().length == 2 ) {
					
					@SuppressWarnings("deprecation")
					Object[] fichesSelectionnees = this.liste.getSelectedValues();
					
					Fiche f1 = (Fiche) fichesSelectionnees[0];
					Fiche f2 = (Fiche) fichesSelectionnees[1];
	
					String txtResultat = ( f1.equals(f2) ? "Les deux fiches sont identiques !" : "Les deux fiches sont differentes !");

					JOptionPane.showInputDialog(this.gui, new JLabel(txtResultat), txtResultat, JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			}
			
		}
		
	}	
	
	@SuppressWarnings("rawtypes")
	public JList getListe() {
		return this.liste;
	}
	
	@SuppressWarnings("unchecked")
	public void genList() {
		/* Met en place la liste des Fiches en fonction du Carnet en attribut */
		
		this.listeM.removeAllElements();
		
		for ( int i=0; i < this.carnet.getListeFiches().size(); i++ ) {
			this.listeM.addElement( this.carnet.getFicheFromIndex(i) );
		}
		
		this.repaint();
	}
	
	public void genList( Carnet c ) {
		
		this.carnet = c;
		this.genList();

	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		this.updateButtons();
		
		if (liste.getSelectedIndex() != -1) {
			this.gui.visualiserFiche( (Fiche) this.liste.getSelectedValue() );
			
		}
		
	}
	
	/*
	 * Pour griser les boutons lorsque leur fonction n'a pas de sens.
	 */
	public void updateButtons() {
		if ( this.liste.getSelectedIndices().length > 0 ) {
			
			this.bSupprimer.setEnabled(true);
			
			if ( this.liste.getSelectedIndices().length == 2 ) {
				this.bComparer.setEnabled(true);
			}
			else {
				this.bComparer.setEnabled(false);
			}
			
		}
		else {
			this.bSupprimer.setEnabled(false);
			this.bComparer.setEnabled(false);
		}
	}

	public JButton getbNouveau() {
		return bNouveau;
	}

	public JButton getbSupprimer() {
		return bSupprimer;
	}

	public JButton getbComparer() {
		return bComparer;
	}


}
