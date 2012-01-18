import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
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
		
		bNouveau.addActionListener(this);
		bSupprimer.addActionListener(this);
		
		paneButton.add(bNouveau);
		paneButton.add(bSupprimer);
		
		
		
		this.add(new JScrollPane(this.liste), BorderLayout.CENTER);
		this.add(paneButton, BorderLayout.SOUTH);
	
		this.liste.addListSelectionListener( this );

		
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		if ( arg0.getSource() instanceof JButton ) {
			System.out.println("OK");
			
			if ( ((JButton) arg0.getSource()).equals(this.bNouveau) ) {
				Fiche nouvelleFiche = new Fiche("nNom", "n_Prenom", "0240667799", new Adresse(12,"rue du paradis", "AuroVille", 65489, "Inde"));
				this.carnet.addFiche( nouvelleFiche );
				this.listeM.addElement(nouvelleFiche);
				System.out.println("New");

			}
			else if ( ((JButton) arg0.getSource()).equals(this.bSupprimer) ) {
				int index = liste.getSelectedIndex();
				
				this.carnet.delFiche( (Fiche) listeM.getElementAt(index) );
				listeM.removeElementAt(index);
				System.out.println("Deletion de "+index);
			}
			
			//this.genList();
		}
		
	}	
	
	public JList getListe() {
		return this.liste;
	}
	
	public void genList() {
		Vector<String> v = new Vector<String>();
		
		
		for ( int i=0; i < this.carnet.getListeFiches().size(); i++ ) {
			//v.add( this.carnet.getListeFiches().get(i).getNom() + " " + this.carnet.getListeFiches().get(i).getPrenoms().first().toString() );
			this.listeM.addElement( this.carnet.getFicheFromIndex(i) );
		}
		
		//this.liste = new JList( this.carnet.getListeFiches().toArray() ) ;
		this.repaint();
		System.out.println("Repainted "+this.carnet.getListeFiches().size()+" elements");
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		this.gui.popFiche( (Fiche) listeM.getElementAt(e.getLastIndex()) );
		
	}


}
