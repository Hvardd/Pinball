import java.io.*;
import java.util.ArrayList;
import java.util.Collections;

public class Top10 implements Serializable {

	// ArrayList top10Liste;

	// Spiller test = new Spiller("Test",100);
	//Spiller test2 = new Spiller("Test2",50);
	// ArrayList<Spiller> liste = new ArrayList<>();
	ArrayList<Spiller> resultatListe = new ArrayList<>();
	ArrayList<Spiller> listen = new ArrayList<>();

	 

	public Top10() {
	}


	public void leggTilResultat(Spiller p){
		resultatListe.add(p);
	}

	public void sortObject() {
		Collections.sort(resultatListe, new ScoreComparator());
	}



	public void checkListSize() {

		sortObject();
		
		if (resultatListe.size() >= 10) {
			
			resultatListe.remove(0);
			// System.out.println(resultatListe.size());
		}

		// System.out.println(resultatListe.size());
		

	} 


	public void writeToFile() {

		try   
		 	  {
  
				FileOutputStream fileOut = new FileOutputStream("Top10.ser");
         		ObjectOutputStream out = new ObjectOutputStream(fileOut);

         		 FileInputStream fileIn = new FileInputStream("Top10.ser");
       	    	 ObjectInputStream in = new ObjectInputStream(fileIn);

       	    	 // liste2 =new ArrayList<>();
      	         /* listen=(ArrayList<Spiller>)in.readObject();

       	  		   for(int i=0;i<listen.size();i++) 
          	 	 	System.out.println(listen.get(i).getScore() + " " + listen.get(i).getNavn());
      				  
      				 //  System.out.println("Read successfull");
           			  in.close();
            		 fileIn.close(); */ 

         	   
         		checkListSize();
         	    out.writeObject(resultatListe);
        		out.close();
        		fileOut.close();
         		System.out.println("Serialized data is saved in Top10.ser ");
       		  }
        catch(IOException i)
              {
         		i.printStackTrace();
         	  }

     /*    catch (ClassNotFoundException c) {
         System.out.println("Employee class not found");
         c.printStackTrace();
         return; 
      }  */ 

       

       }

	public void readFromFile() {

	    try {
        	 FileInputStream fileIn = new FileInputStream("Top10.ser");
       	     ObjectInputStream in = new ObjectInputStream(fileIn);

       	     // liste2 =new ArrayList<>();
      	     listen=(ArrayList<Spiller>)in.readObject();

       	     for(int i=0;i<listen.size();i++){
          	     
          	 	 System.out.println(listen.get(i).getScore() + " " + listen.get(i).getNavn());
          	 
      		  } 
      		 //  System.out.println("Read successfull");
             in.close();
             fileIn.close();
      } catch (IOException i) {
         i.printStackTrace();
         return;
       } 
       	 catch (ClassNotFoundException c) {
         System.out.println("Employee class not found");
         c.printStackTrace();
         return; 
      }   
	}

	 
}