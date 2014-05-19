package unitesLib;

public class Test {
	public static void main(String[] args){
		Main main = new Main("src/unitesLib/conf.xml");
		System.out.println(main.convertJoli((float) 1.3, "distance", "metre", "yard"));
		main.ajouterUnite("distance", "newUnit", "1.07324324;12");
		System.out.println(main.convertJoli((float) 1, "distance", "metre", "kilometre"));
		System.out.println(main.convertJoli((float) 1, "distance", "metre", "newUnit"));
		System.out.println(main.convertJoli((float) 1, "temperature", "farenheit", "celsius"));
		System.out.println(main.convertJoli((float) 1, "temperature", "celsius", "farenheit"));
		System.out.println(main.convertJoli((float) 1, "distance", "metre", "inconnu"));
		System.out.println(main.convertJoli((float) 1, "distance", "inconnu", "kilometre"));
		main.supprimerUnite("distance", "newUnit");
	}
}
