package unitesLib;

public class Test {
	public static void main(String[] args){
		Main main = new Main("src/unitesLib/conf.xml");
		System.out.println(main.convert((float) 1.3, "distance", "metre", "yard"));
	}
}
