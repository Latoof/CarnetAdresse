import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.util.TreeSet;

import com.thoughtworks.xstream.XStream;

/***
 * Text a caractere completement inutile. \o/
 * 
 */
public class Fiche {

	String 			nom, numeroTel;
	TreeSet<String>	prenoms;
	TreeSet<Adresse> adresses;
	TreeSet<String>	emails;
	
	public Fiche(String filename) {
		
		this.prenoms = new TreeSet<String>();
		this.adresses = new TreeSet<Adresse>();
		this.emails =  new TreeSet<String>();
		
		try {
			BufferedReader in = new BufferedReader ( new FileReader(filename) );
			
			
		} catch (FileNotFoundException e) {
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

	}
	
	/**
	 * Ajoute une adresse email.
	 * @param email
	 */
	public void addEmail( String email ) {
		this.emails.add(email);
	}
	
	public void addAdresse(Adresse adresse){
		this.adresses.add(adresse);
	}

	@Override
	public String toString() {
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
	
	/*
	@Override
	public int compareTo(Fiche arg0) {
		// TODO Auto-generated method stub
		
		if()
		
		return 0;
	}
	*/

	
	
	
	
}
