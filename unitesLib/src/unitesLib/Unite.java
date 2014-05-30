package unitesLib;

public class Unite {
	private String nom;
	private double coef; //coefficient multiplicateur
	private double decal; // coefficient de decallage

	//constructeur sans décalage
	public Unite(String pNom, double pVal){
		nom = pNom;
		coef=pVal;
		decal=0;
	}
	
	//constructeur avec décallage
	public Unite(String pNom, double pVal, double pDecal){
		nom = pNom;
		coef=pVal;
		decal=pDecal;
	}
	
	public double getDecal() {
		return decal;
	}

	public void setDecal(double decal) {
		this.decal = decal;
	}
	
	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public double getVal() {
		return coef;
	}

	public void setVal(double val) {
		this.coef = val;
	}
}
