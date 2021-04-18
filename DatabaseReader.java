import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files

public class DatabaseReader{
   String price[][] = new String[3][100]; //[walmart,market,target][all of their prices]
   String name[] = new String[500];
   
   int i = 0;


   DatabaseReader(){
      try {
      File myObj = new File("database.txt");
      Scanner myReader = new Scanner(myObj);
      while (myReader.hasNextLine()) {
        String data = myReader.nextLine();
        //System.out.println(data);
        
        
        if(data.contains("Produce")){
         System.out.println("Reached Produce");
         }
        else if(data.contains("Drinks"))
         System.out.println("Reached Drinks");
        else if(data.contains("Meat"))
         System.out.println("Reached Meat");
        else if(data.contains("Etc."))
         System.out.println("Reached Etc.");
        else if(data.contains("Added"))
         System.out.println("Reached Added:");
         
        else{
            if(data.contains(",")){
               for(int k = 0; k<data.length(); k ++){ //gets name
                  if(data.charAt(k)==',')
                     k = data.length();
                  else{
                     if(name[i]==null)
                        name[i]=""+data.charAt(k);
                     else
                        name[i]=name[i]+data.charAt(k);
                  }
                     
               }
               int s = 0; //store number
               for(int k = 0; k<data.length(); k ++){  //once we have traversed every char in this line,
                  if(data.charAt(k)==',' && s<3){ //examining the commas, iterate if its commas 1 through 3
                     s++;
                     }
                  else if(data.charAt(k)==',' && s>=3&&s<5){ // if we are in between the 4rd and 5th comma
                     s++;
                     }
                  else if(data.charAt(k)==',' && s>=5){// we reached the 5th comma
                     s++;
                     }
                  if(data.charAt(k)!=','){   //once we reached the 6th comma
                     if(s==2||s==4||s==6){
                        if(price[0][i] == null)
                           price[0][i] = ""+ data.charAt(k); 
                        if(price[1][i] == null)
                           price[1][i] = ""+ data.charAt(k);
                        if(price[2][i] == null)
                           price[2][i] = ""+ data.charAt(k);
                     }
                     if(s==2){ //whatever comes after 2 commas is walmart price
                        price[0][i] = price[0][i] + data.charAt(k);
                     }
                     if(s==4){//likewise for market and target  
                        price[1][i] = price[1][i] + data.charAt(k);
                     }
                     if(s==6){//six commas down for target
                        price[2][i] = price[2][i] + data.charAt(k);
                     }
                  }
                     
               }

            }
            
        }
        i++;
        
        
         
      }
      myReader.close();
       } catch (FileNotFoundException e) {
         System.out.println("An error occurred.");
         e.printStackTrace();
       }
   
   }
   
   boolean SearchSpecific(String item){
      boolean found = false;
      for(int i = 0; i<name.length; i++){
         if(name[i]!=null)
            if(name[i].contains(item)){
               System.out.println("Found "+ item);
               System.out.println("Its price is | Walmart: $"+ price[0][i]+"| Market Street: $"+ price[1][i]+"| Target: $"+ price[2][i]);
               found = true;
            }
      }
      return found;
   
   }
   
   
   float getPriceW(String item){ //getting price for the 3 stores
      float Price = 0f;
      for(int i = 0; i<name.length; i++){
         if(name[i]!=null)
            if(name[i].contains(item)){
               Price = Float.valueOf(price[0][i]);
            }
      }
      return Price;
   }
   float getPriceM(String item){
      float Price = 0f;
      for(int i = 0; i<name.length; i++){
         if(name[i]!=null)
            if(name[i].contains(item)){
               Price = Float.valueOf(price[1][i]);
            }
      }
      return Price;
   }
   float getPriceT(String item){
      float Price = 0f;
      for(int i = 0; i<name.length; i++){
         if(name[i]!=null)
            if(name[i].contains(item)){
               Price = Float.valueOf(price[2][i]);
            }
      }
      return Price;
   }
   
}
