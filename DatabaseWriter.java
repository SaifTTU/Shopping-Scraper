import java.io.*;  
import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class DatabaseWriter{
   DatabaseWriter(String name, float market, float walmart, float target){
      try {
         DatabaseReader dbr = new DatabaseReader(); //opening this to work with writer. its another object
         
         File file = new File("database.txt");
         Scanner myReader = new Scanner(file);

         FileWriter fr = new FileWriter(file, true);
         BufferedWriter br = new BufferedWriter(fr);
         
         
         
         if(dbr.SearchSpecific(name) == false){
               br.write(name+", Walmart, "+walmart+", Market, "+market+", Target, "+target+"\n");
            }
         else{
            System.out.println("Item already recorded");
         }   
            
         
         br.close();
         fr.close();
      
      }
      catch (IOException e){
         e.printStackTrace();
       
      }
   }
   
}
