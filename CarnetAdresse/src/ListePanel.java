import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class ListePanel extends JPanel implements ActionListener, ListSelectionListener {

	private UserGUI gui;
	private JList liste;
	private DefaultListModel listeM;
	private Carnet carnet;
	private JButton bNouveau;
	private JButton bSupprimer;
	private JButton bComparer;

	
	
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
	
	public void actionPerformed(ActionEvent arg0) {
		
		if ( arg0.getSource() instanceof JButton ) {
			
			if ( ((JButton) arg0.getSource()).equals(this.bNouveau) ) {
				
				Fiche nouvelleFiche = new Fiche("nNom", "n_Prenom", "0240667799", new Adresse(12,"rue du paradis", "AuroVille", 65489, "Inde"));
				this.carnet.addFiche( nouvelleFiche );
				this.listeM.addElement(nouvelleFiche);

			}
			else if ( ((JButton) arg0.getSource()).equals(this.bSupprimer) ) {
				
				int nbSelected = liste.getSelectedIndices().length;
				if ( nbSelected == 1 ) {
				
					Fiche ficheASupprimer = (Fiche) this.liste.getSelectedValue();
					
					this.carnet.delFiche( ficheASupprimer);
					listeM.removeElement( ficheASupprimer );
					//System.out.println("Deletion de "+index);
					
					
				}
				else if ( nbSelected > 1 ) {
					
					Object[] fichesASupprimer = this.liste.getSelectedValues();
					
					for ( int i = 0; i < nbSelected; i++ ) {
						
						Fiche ficheASupprimer = (Fiche) fichesASupprimer[i];
						
						this.carnet.delFiche( ficheASupprimer);
						listeM.removeElement( ficheASupprimer );
						
					}
					
				}
				
			}
			else if ( ((JButton) arg0.getSource()).equals(this.bComparer) ) {
				
				if ( this.liste.getSelectedIndices().length == 2 ) {
					
					Object[] fichesSelectionnees = this.liste.getSelectedValues();
					
					Fiche f1 = (Fiche) fichesSelectionnees[0];
					Fiche f2 = (Fiche) fichesSelectionnees[1];
	
					String txtResultat = ( f1.equals(f2) ? "Les deux fiches sont identiques !" : "Les deux fiches sont differentes !");

					JOptionPane op = new JOptionPane();
					JOptionPane.showInputDialog(this.gui, new JLabel(txtResultat), txtResultat, JOptionPane.INFORMATION_MESSAGE);
				}
				
				
			}
			
		}
		
	}	
	
	public JList getListe() {
		return this.liste;
	}
	
	public void genList() {
		
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
