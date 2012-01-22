import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.TreeSet;

import com.thoughtworks.xstream.XStream;

public class Fiche {

	final static String default_email = "N/A";
	
	String 				nom, numeroTel;
	TreeSet<String>		prenoms;
	TreeSet<Adresse> 	adresses;
	TreeSet<String>		emails;
	
	public Fiche(String filename) {
		
		this.prenoms = new TreeSet<String>();
		this.adresses = new TreeSet<Adresse>();
		this.emails =  new TreeSet<String>();
		
		try {
			BufferedReader in = new BufferedReader ( new FileReader(filename) );
			
		}
		catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		} 
		this.fromXMLFile(filename);
		
	}
		
	public Fiche(String nom,String prenom1, String numeroTel, Adresse adresse) {
		
		this.nom = nom;
		this.numeroTel = numeroTel;
		this.prenoms = new TreeSet<String>();
		this.prenoms.add(prenom1);
		this.adresses = new TreeSet<Adresse>();
		this.adresses.add(adresse);
		this.emails =  new TreeSet<String>();
		this.addEmail("exemple@mail.fr");

	}
	
	public Fiche(String nom, String prenom1, String prenom2, String numeroTel, Adresse adresse) {
		
		this.nom = nom;
		this.numeroTel = numeroTel;
		this.prenoms = new TreeSet<String>();
		this.prenoms.add(prenom1);
		this.prenoms.add(prenom2);
		this.adresses = new TreeSet<Adresse>();
		this.adresses.add(adresse);
		this.emails =  new TreeSet<String>();
		this.addEmail(default_email);

	}
	
	
	public Fiche(String nom, String prenom1, String prenom2, String prenom3, String numeroTel, Adresse adresse) {
		
		this.nom = nom;
		this.numeroTel = numeroTel;
		this.prenoms = new TreeSet<String>();
		this.prenoms.add(prenom1);
		this.prenoms.add(prenom2);
		this.prenoms.add(prenom3);
		this.adresses = new TreeSet<Adresse>();
		this.adresses.add(adresse);
		this.emails =  new TreeSet<String>();
		this.addEmail(default_email);

	}
	
	/**
	 * Ajoute une adresse email.
	 * @param email
	 */
	public void addEmail( String email ) {
		
		if ( this.emails.size() == 1 && this.emails.first().equals(default_email)) {
			this.emails.pollFirst();
		}
		
		this.emails.add(email);
		
	}
	
	public void addAdresse(Adresse adresse){
		this.adresses.add(adresse);
	}

	public String toStringDBG() {
		return "Fiche [nom=" + nom + ", numeroTel=" + numeroTel + ", prenoms="
				+ prenoms + ", adresse=" + adresses + "]";
	}

	public boolean equals(Object o) {
		
		Fiche f = (Fiche) o;
				
		return (
				( this.nom.equals(f.nom) )
				& ( this.prenoms.equals( f.prenoms ) )
				& ( this.adresses.equals( f.adresses ) )
				& ( this.emails.equals( f.emails ) )
				);
		
	}
	
	public String toXML() {
		
		
		XStream xstream = new XStream();
		String str = xstream.toXML(this);
		
		return str;
	}
	
	public void fromXML(String str) {
		
		XStream xstream = new XStream();

		xstream.fromXML(str);
	}
	
	public void toXMLFile( String filename ) {
		
		FileOutputStream stream = null;
		try {
			stream = new FileOutputStream(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		XStream xstream = new XStream();
		xstream.toXML(this, stream);
		
	}
	
	public void fromXMLFile( String filename ) {
		
		FileInputStream stream = null;
		try {
			stream = new FileInputStream(filename);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		XStream xstream = new XStream();

		xstream.fromXML(stream, this);
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getNumeroTel() {
		return numeroTel;
	}

	public void setNumeroTel(String numeroTel) {
		this.numeroTel = numeroTel;
	}

	public TreeSet<String> getPrenoms() {
		return prenoms;
	}

	public void setPrenoms(TreeSet<String> prenoms) {
		this.prenoms = prenoms;
	}

	public TreeSet<Adresse> getAdresses() {
		return adresses;
	}

	public void setAdresses(TreeSet<Adresse> adresses) {
		this.adresses = adresses;
	}

	public TreeSet<String> getEmails() {
		return emails;
	}

	public void setEmails(TreeSet<String> emails) {
		this.emails = emails;
	}
	
	public String toString() {
		return (this.prenoms.first()+" "+this.nom);
	}
	
	
}
