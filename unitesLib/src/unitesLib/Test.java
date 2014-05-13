package unitesLib;

public class Test {
	public static void main(String[] args){
		Main main = new Main("src/unitesLib/conf.xml");
		System.out.println(main.convert((float) 1.3, "distance", "metre", "yard"));
		main.ajouterUnite("distance", "newUnit", "1.07324324;12");
		System.out.println(main.convert((float) 1, "distance", "metre", "bite"));
		System.out.println(main.convert((float) 1, "temperature", "farenheit", "celsius"));
		System.out.println(main.convert((float) 1, "temperature", "celsius", "farenheit"));
		System.out.println("bla");
		main.supprimerUnite("distance", "newUnit");
	}
}
