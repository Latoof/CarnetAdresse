import java.util.LinkedList;
import java.util.List;


public class Carnet {

	private LinkedList<Fiche> fiches;
	
	public Carnet() {
		
		this.fiches = new LinkedList<Fiche>();
		
	}
	
	public LinkedList<Fiche> getListeFiches() {
		return this.fiches;
	}
	
	public void addFiche( Fiche f ) {
		this.fiches.add( f );
	}
	
	public Fiche getFicheFromIndex( int index ) {
		return this.fiches.get( index );
	}
	
}
