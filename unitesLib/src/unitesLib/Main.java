package unitesLib;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main {
	public static LinkedList<Categorie> list = new LinkedList<Categorie>();
	private String chemin; 
	
	public Main(String pChemin){
		chemin = pChemin;
		Element racineElement;
	    
	    
	    //on prend le noeud racine
	    racineElement = getDocument().getDocumentElement();
	    
	    //on récupère toutes les catégories
	    NodeList cateList = racineElement.getElementsByTagName("cate");
	    
	    for(int i=0;i<cateList.getLength();i++){
	    	//pour chaque catégorie on ajoute a la liste du main
	    	list.add(new Categorie(cateList.item(i).getAttributes().getNamedItem("nom").getTextContent()));
	    	Element unit = (Element) cateList.item(i);
	    	//on récupère les unités de la catégorie en cours
	    	NodeList unitesList = unit.getElementsByTagName("unit");    	
	    	for(int j=0;j<unitesList.getLength();j++){
	    		//on ajoute chaque unite dans la catégorie
	    		if(unitesList.item(j).getTextContent().contains(";")){
	    			float coef, dec;
	    			String[] tab = unitesList.item(j).getTextContent().split(";");
	    			coef=Float.parseFloat(tab[0]);
	    			dec=Float.parseFloat(tab[1]);
	    			list.get(i).getList().add(new Unite(unitesList.item(j).getAttributes().getNamedItem("nom").getTextContent(), coef, dec));
	    		}
	    		else{
	    			list.get(i).getList().add(new Unite(unitesList.item(j).getAttributes().getNamedItem("nom").getTextContent(), Float.parseFloat(unitesList.item(j).getTextContent())));
	    		}
	    	}
	    }
	}
	
	//fonction permettant de convertir une unité vers une autre
	public float convert(float val, String cate, String from, String to){
		float valFrom=0, valTo=0, decal=0;
		/* context Main::convert(cate, from, to) pre
		 * self->forAll(c:Categorie | c = cate implies(c->forAll(u:Unite | c=from)))
		 * self->forAll(c:Categorie | c = cate implies(c->forAll(u:Unite | c=to)))
		 */
		for(int i =0; i<list.size();i++){//on cherche la catégorie
			if(list.get(i).getNom().equals(cate)){
				for (int j =0; j<list.get(i).getList().size();j++){//on cherche les unites d'origine et de sortie
					if(list.get(i).getList().get(j).getNom().equals(from)){
						valFrom=list.get(i).getList().get(j).getVal();
						decal=list.get(i).getList().get(j).getDecal();
					}
					if(list.get(i).getList().get(j).getNom().equals(to)){
						valTo=list.get(i).getList().get(j).getVal();
						decal=-list.get(i).getList().get(j).getDecal();
					}
				}
			}
		}
		if(valTo != 0 && valFrom != 0){
			if(decal>0)//dans les cas d'un passage en degre --> farenheit le decalage est positif
				return (val*valFrom/valTo+decal);
			return ((val+decal)*valFrom/valTo);
		}
		else 
			return 0;
	}
	
	/*cette fonction permet de retourner une string contenant l'etat de la memoire*/
	public String toXml(){
		String res="<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?><jocafconverter>";
		for(int i=0;i<list.size();i++){
			res+="<cate nom=\""+list.get(i).getNom()+"\">";
			for(int j=0;j<list.get(i).getList().size();j++){
				Unite unitCourant = list.get(i).getList().get(j);
				res+="<unit nom=\""+unitCourant.getNom()+"\">"+unitCourant.getVal();
				res+=";"+unitCourant.getDecal();
				res+="</unit>";
			}
			res+="</cate>";
		}
		res+="</jocafconverter>";
		return res;
	}
	
	/*Cette fonction permet d'enregistrer une string dans le fichier */
	public void saveStrXml(String xml){
		File fic = new File(chemin);
		try {
			Writer w = new FileWriter(fic);
			w.write(xml);
			w.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/*Cette fonction retourne une unite en fonction de son nom*/
	private Unite getUnit(String cate, String unite){
		Categorie categorie=searchCate(cate);
		if(categorie!=null)
			return categorie.getValUnit(unite);
		return null;
	}
	
	/*Cette fonction permet d'ajouter une categorie*/
	public void ajouterCate(String nomCate){
		if(searchCate(nomCate)!=null)
			list.add(new Categorie(nomCate));
	}
	
	/*Cette fonction permet de savoir si une catégorie existe*/
	private Categorie searchCate(String cate){
		for(int i=0;i<list.size();i++){
			if(list.get(i).getNom().equals(cate))
				return list.get(i);
		}
		return null;
	}
	
	/*Cette fonction permet de supprimer une categorie*/
	public void supCate(String nomCate){
		for(int i=0;i<list.size();i++){
			if(list.get(i).getNom().equals(nomCate))
				list.remove(i);
		}
			
	}
	
	/*Cette fonction permet d'ajouter une unite dans une categorie*/
	public void ajouterUnite(String categorie, String nomUnite, String valUnite){
		boolean cateExiste = searchCate(categorie)!=null;
		boolean doublon=getUnit(categorie, nomUnite)==null;
		
		if(!cateExiste)
			System.out.println("La categorie n'existe pas, l'unite ne peut donc pas etre ajoutee");
		//on ajoute l'unite au fichier XML
		if(!doublon && cateExiste){
			Unite u;
			if(valUnite.contains(";")){
				float coef, dec;
				String[] tab = valUnite.split(";");
				coef=Float.parseFloat(tab[0]);
				dec=Float.parseFloat(tab[1]);
				u = new Unite(nomUnite, coef, dec);
			}
			else
				u = new Unite(nomUnite, Float.parseFloat(valUnite));
			Categorie cate = searchCate(categorie);
			if(cate!=null)
				cate.getList().add(u);
		saveStrXml(toXml());
		}
		
		
	}
	
	/*Cette fonction permet de caster le fichier XML de configuration*/
	private Document getDocument(){
		Document documentXML = null;
	    
	    // on parse le fichier de configuration
	    try {
	    File file = new File(chemin);
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder;
		docBuilder = docBuilderFactory.newDocumentBuilder();
		documentXML = docBuilder.parse(file);
	    } 
		catch (SAXException e) {
			e.printStackTrace();
			System.exit(0);
		} 
		catch (IOException e) {
			e.printStackTrace();
			System.exit(0);
		}
		catch (ParserConfigurationException e) {
			e.printStackTrace();
			System.exit(0);
		}
	    return documentXML;
	}
	
	/*Cette fonction permet de supprimer une unite*/
	public void supprimerUnite(String categorie, String nomUnite){
		boolean supOk=false;
		for(int i=0;i<list.size();i++){
			if(list.get(i).getNom().equals(categorie)){
				LinkedList<Unite> listUnit = list.get(i).getList();
				for(int j=0;j<listUnit.size();j++){
					if(listUnit.get(j).getNom().equals(nomUnite)){
						listUnit.remove(j);
						supOk=true;
					}
				}
			}
		}
		if(!supOk)
			System.out.println("L'unite n'a pas pu etre supprimee.");
		saveStrXml(toXml());//sauveFicXml(docXml);
	}
}
