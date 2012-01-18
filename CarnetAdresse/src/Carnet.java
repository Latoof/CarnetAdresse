import java.util.LinkedList;

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
	
	public void delFiche( Fiche f ) {
		
		if ( this.fiches.indexOf( f ) != -1 ) {
			this.fiches.remove( f );
		}
		
	}
	
	public void delFiche( int index ) {
		
		if ( index != -1 ) {
			this.fiches.remove( index );
		}
		
	}
	
	public Fiche getFicheFromIndex( int index ) {
		return this.fiches.get( index );
	}
	
}
