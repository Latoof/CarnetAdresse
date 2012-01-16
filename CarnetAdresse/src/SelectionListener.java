import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;


public class SelectionListener implements ListSelectionListener {

		private UserGUI gui;
		
		public SelectionListener( UserGUI g ) {
			this.gui = g;
		}

		@Override
		public void valueChanged(ListSelectionEvent arg0) {

			this.gui.popFiche( arg0.getLastIndex() );
			
		}
	
}
