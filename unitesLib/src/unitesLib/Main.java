package unitesLib;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;



import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Main {
	private static LinkedList<Categorie> list = new LinkedList<Categorie>();
	
	public Main(String chemin){
		Element racineElement;
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
	    
	    racineElement = documentXML.getDocumentElement(); 
	    NodeList cateList = racineElement.getElementsByTagName("cate");
	    
	    for(int i=0;i<cateList.getLength();i++){
	    	list.add(new Categorie(cateList.item(i).getAttributes().getNamedItem("nom").getTextContent()));
	    	Element unit = (Element) cateList.item(i);
	    	NodeList unitesList = unit.getElementsByTagName("unit");    	
	    	for(int j=0;j<unitesList.getLength();j++){
	    		list.get(i).ajouterUnit(unitesList.item(j).getAttributes().getNamedItem("nom").getTextContent(), Float.parseFloat(unitesList.item(j).getTextContent()));
	    		System.out.println(unitesList.item(j).getAttributes().getNamedItem("nom").getTextContent()+" "+ Float.parseFloat(unitesList.item(j).getTextContent()));
	    	}
	    }
	}
	
	public float convert(float val, String cate, String from, String to){
		float valFrom=0, valTo=0;
		for(int i =0; i<list.size();i++){
			if(list.get(i).nom.equals(cate)){
				for (int j =0; j<list.size();j++){
					if(list.get(i).list.get(j).getNom().equals(from))
						valFrom=list.get(i).list.get(j).getVal();
					if(list.get(i).list.get(j).getNom().equals(to))
						valTo=list.get(i).list.get(j).getVal();
				}
			}
		}
		return (val*valFrom/valTo);
	}
}
