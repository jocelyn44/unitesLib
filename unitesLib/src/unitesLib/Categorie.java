package unitesLib;

import java.util.LinkedList;

public class Categorie {
	private String nom;
	public LinkedList<Unite> list = new LinkedList<Unite>();
	
	public Categorie(String pNom){
		nom = pNom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public LinkedList<Unite> getList() {
		return list;
	}

	public void setList(LinkedList<Unite> list) {
		this.list = list;
	}
}
