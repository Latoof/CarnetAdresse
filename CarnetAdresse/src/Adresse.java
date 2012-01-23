@SuppressWarnings("rawtypes")
public class Adresse implements Comparable {
	
	private int numero, codePostal;
	private String nomRue, ville, pays;
	
	public Adresse(int numero, String nomRue, String ville, int codePostal,	String pays) {
		this.numero = numero;
		this.codePostal = codePostal;
		this.nomRue = nomRue;
		this.ville = ville;
		this.pays = pays;
	}
	
	
	@Override
	public String toString() {
		return "Adresse [numero=" + numero + ", codePostal=" + codePostal
				+ ", nomRue=" + nomRue + ", ville=" + ville + ", pays=" + pays
				+ "]";

	}
	
		
	public int getNumero() {
		return numero;
	}


	public void setNumero(int numero) {
		this.numero = numero;
	}


	public int getCodePostal() {
		return codePostal;
	}


	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}


	public String getNomRue() {
		return nomRue;
	}


	public void setNomRue(String nomRue) {
		this.nomRue = nomRue;
	}


	public String getVille() {
		return ville;
	}


	public void setVille(String ville) {
		this.ville = ville;
	}


	public String getPays() {
		return pays;
	}


	public void setPays(String pays) {
		this.pays = pays;
	}


	@Override
	public int compareTo(Object o) {
		
		Adresse a = (Adresse) o;
		
		int res = 0;
		
		if ( res == 0 ) {
			res = this.pays.hashCode() - a.pays.hashCode();
		}
		if ( res == 0 ) {
			res = this.ville.hashCode() - a.ville.hashCode();
		}
		if ( res == 0 ) {
			res = this.nomRue.hashCode() - a.nomRue.hashCode();
		}
		if ( res == 0 ) {
			res = this.codePostal - a.codePostal;
		}
		if ( res == 0 ) {
			res = this.numero - a.numero;
		}

		return res;
	}
	
}
