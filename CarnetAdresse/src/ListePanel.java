import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;


public class ListePanel extends JPanel {

	private JList liste;
	
	public ListePanel( Carnet c ) {
		
		this.setLayout(new BorderLayout());
		this.setBackground(Color.RED);

		
		Vector<String> v = new Vector<String>();
		
		
		for ( int i=0; i < c.getListeFiches().size(); i++ ) {
			v.add( c.getListeFiches().get(i).getNom() + " " + c.getListeFiches().get(i).getPrenoms().first().toString() );
		}
		
		this.liste = new JList( v ) ;
		
		JPanel paneButton = new JPanel(new GridLayout(1, 2));
		JButton add = new JButton("Add");
		JButton edit = new JButton("edit");
		
		paneButton.add(add);
		paneButton.add(edit);
		
		this.add(new JScrollPane(this.liste), BorderLayout.CENTER);
		this.add(paneButton, BorderLayout.SOUTH);
	
		
		
	}
	
	public JList getListe() {
		return this.liste;
	}
	
}
