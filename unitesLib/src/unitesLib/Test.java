package unitesLib;

public class Test {
	public static void main(String[] args){
		Main main = new Main("src/unitesLib/conf.xml");
		main.convertJoli((float) 1.3, "distance", "metre", "yard");
		main.ajouterUnite("distance", "newUnit", "1.07324324;12");
		main.convertJoli((float) 1, "temperature", "fahreneit", "celsius");
		main.convertJoli((float) 1, "temperature", "kelvin", "celsius");
		main.convertJoli((float) 1, "distance", "metre", "kilometre");
		main.convertJoli((float) 1, "distance", "metre", "newUnit");
		main.convertJoli((float) 1, "temperature", "fahreneit", "celsius");
		main.convertJoli((float) 1, "temperature", "celsius", "fahreneit");
		main.convertJoli((float) 1, "distance", "metre", "inconnu");
		main.convertJoli((float) 1, "distance", "inconnu", "kilometre");
		main.convertJoli((float) 1, "distance", "metre", "yard");
		main.convertJoli((float) 1, "temperature", "celsius", "kelvin");
		main.convertJoli((float) 1, "temperature", "kelvin", "fahreneit");
		main.convertJoli((float) 1, "temperature", "fahreneit", "kelvin");
		main.supprimerUnite("distance", "newUnit");
	}
}
