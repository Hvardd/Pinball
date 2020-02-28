public class Test {
	public static void main(String[] args) {

		Top10 test = new Top10();
		 
		Spiller nySpiller = new Spiller("Håvard",250);
		Spiller testen = new Spiller("George",100);





		/* for(int i=0; i<list.size(); i++) {

		} */ 

		
	    test.leggTilResultat(nySpiller);
	    test.writeToFile();
	     
	    test.readFromFile();
	}
}