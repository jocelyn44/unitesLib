package unitesLib;

import java.util.LinkedList;

public class Categorie {
	String nom;
	LinkedList<Unite> list = new LinkedList<Unite>();
	
	public Categorie(String pNom){
		nom = pNom;
	}
	
	public void ajouterUnit(String nom, float val){
		list.add(new Unite(nom,val));
	}
}
