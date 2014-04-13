package unitesLib;

public class Unite {
	private String nom;
	private float val;
	
	public Unite(String pNom, float pVal){
		nom = pNom;
		val=pVal;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public float getVal() {
		return val;
	}

	public void setVal(float val) {
		this.val = val;
	}
	
	
}
