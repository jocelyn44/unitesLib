package unitesLib;

public class Unite {
	private String nom;
	private float coef; //coefficient multiplicateur
	private float decal; // coefficient de decallage

	public Unite(String pNom, float pVal){
		nom = pNom;
		coef=pVal;
		decal=0;
	}
	
	public Unite(String pNom, float pVal, float pDecal){
		nom = pNom;
		coef=pVal;
		decal=pDecal;
	}
	
	public float getDecal() {
		return decal;
	}

	public void setDecal(float decal) {
		this.decal = decal;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public float getVal() {
		return coef;
	}

	public void setVal(float val) {
		this.coef = val;
	}
}
