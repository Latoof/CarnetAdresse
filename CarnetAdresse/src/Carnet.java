import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.LinkedList;

import com.thoughtworks.xstream.XStream;

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
	
	/* Enregistre les donnees du Carnet directement dans un fichier, au format XML */
	public int toXML( String filename ) {
		
		FileOutputStream stream;
		try {
			stream = new FileOutputStream(filename);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return -1;
		}
		
		XStream xs = new XStream();
		xs.toXML(this,stream);
		
		return 0;
		
	}
	
	public boolean contains( Fiche f ) {
		return this.fiches.contains(f);
	}
	
	/* Charge les donnees du Carnet depuis un fichier */
	public int fromXML( File file ) {
				
		XStream xs = new XStream();
		xs.fromXML(file, this);
		
		return 0;
		
	}
	
	public int getNbFiches() {
		return this.fiches.size();
	}
}
