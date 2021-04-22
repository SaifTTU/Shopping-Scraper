import java.util.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

import javafx.event.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

import java.io.FileInputStream; 
import java.io.FileNotFoundException; 


import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import javafx.scene.image.Image;  
import javafx.scene.image.ImageView; 
import javafx.scene.text.Text;
import javafx.scene.layout.Pane;


import javafx.beans.value.*;
import javafx.geometry.Pos;



public class Grocery_UI_App extends Application {


    public String comIt[] = new String[]{"Bananas",
                                          "Oranges",
                                          "Lemons",
                                          "WaterMellons",
                                          "Guava",
                                          "Bread",
                                          "Milk",
                                          "Eggs",
                                          "Cheese",
                                          "Beef"}; //common Items
    public Text storeP[] = new Text[]{new Text("$0.0"),new Text("$0.0"),new Text("$0.0")}; //the total store prices
    
    public DatabaseReader dbr = new DatabaseReader();
    
    float msTot = 0f, 
          wTot = 0f, 
          tTot = 0f; //totals
    
    StackPane itHold[] = new StackPane[10]; //item holder. holds the common 10 items
    
    gList groceryList = new gList(); //holds the current elements we're wanting to buy

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException { //starts fx application
        
        Pane root = new Pane(); //everything goes on top of this pane

        StackPane holder = new StackPane();
        Canvas canvas = new Canvas(800,  400);
        
        
        Rectangle search = new Rectangle(70, 30); //no longer used. Replaced with compare button
        Rectangle empty = new Rectangle(100, 30);
        Rectangle recomend = new Rectangle(70, 30);
        
        
        
        VBox vbox1 = new VBox(5);
        VBox vbox2 = new VBox(5); //second column
        VBox recoL = new VBox(5); //recomended list
        
        vbox2.getChildren().add(search);
        
        //Creating an image (Bananas)
        Image image = new Image(new FileInputStream("Bananas.jpeg"));  
      
        //Setting the image view 1 
        ImageView imageView1 = new ImageView(image); 
        
        imageView1.setFitHeight(300); 
        imageView1.setFitWidth(160);         
      
        //Setting the preserve ratio of the image view 
        imageView1.setPreserveRatio(true);
        
        recoL.getChildren().add( imageView1 );
        
        Text rText = new Text("  Recomended: ");
        rText.setFill( Color.web("0x800000",1.0) );
        rText.setStyle("-fx-font: 14 arial;");
        
        recoL.getChildren().add( rText ); //adding "Recomended" text above buttons
        
        Image title = new Image(new FileInputStream("Title.png"));  //title logo
        
        ImageView imageView2 = new ImageView(title); //second imageView for the title
        imageView2.setFitHeight(300); 
        imageView2.setFitWidth(160);         
        imageView2.setPreserveRatio(true);
        
        vbox1.getChildren().add( imageView2 );
        
        
        Rectangle recomended[] = new Rectangle[10]; //creating a top ten list
        
        for(int i = 0; i<10; i++){ //loop which loads the recomended list from the arrays
            
            recomended[i] = new Rectangle(150, 25);
            recomended[i].setFill(Color.WHITE);
            
            itHold[i] = new StackPane(); //item holders (stack panes) get initialized
            itHold[i].getChildren().add( recomended[i] ); //adds rectangle goes over stack pane 
            
            itHold[i].getChildren().add( new Text(comIt[i])); //adds text over rectangle in stack pane 
            
            recoL.getChildren().add( itHold[i] ); //add item Holders to the spot where the recomended list is
        }
        
        

        search.setFill(Color.WHITE);
        empty.setFill(Color.web("0x33CCFF",1.0)); //empty rectangle, made to create space, set to same color as background
        
        recomend.setFill(Color.WHITE); //color a rectangle white (possibly dont use this anymore)
        
        HBox hbox1 = new HBox(5); //create a horizontal box holder with a spacing of 5 pixels apart from each other
        
        
        
        
        /*---------------------------------------
         All of this is just to add a functional list box on the page - current issue is sometimes the boxes dont let you click on them.
        -----------------------------------------*/
        
          final Label status       = new Label(); //is inside the interactible gui, informs user of the last action took
          
          final Label changeReport = new Label(); //updates this status when a change is made
          
          TextField textField = new TextField(); //used to type in in our new items (grocery list)
          
          HBox hbox = new HBox(); //creating a horizontal box
          
          hbox.getChildren().add(textField); //adding the text field to it
          
          ListView<String> listView = new ListView<>(); //big list to appear in descending order
          
          initListView(listView); //loads the list with items
      
          listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>(){ //lets to click on items
            @Override public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
              changeReport.setText("Selection changed from '" + oldValue + "' to '" + newValue + "'"); //informs when change made
            }
          });
           
          Button addBtn = new Button("Click to add to List"); //lets you add to list
              addBtn.setOnAction(action -> {
                  if(textField.getText()!=null){
                     String I = textField.getText(); //whatever the user typed, I will be
                     status.setText(I+" Added."); //setting status
                     groceryList.gl.add(new gItem(I));
                     listView.getItems().add(I);
                     for(int i = 0; i < groceryList.gl.size(); i++) {
                     System.out.println((i+1)+". "+groceryList.gl.get(i).name);
                     }
                     System.out.print("\n");
                     
                  }
              });
             hbox.getChildren().add(addBtn);
            
          Button removeButton = new Button("Remove Selected");//lets you remove from a list (so also interacts with list view)
          removeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
              int selectedIdx = listView.getSelectionModel().getSelectedIndex();
              if (selectedIdx != -1) {
                String itemToRemove = listView.getSelectionModel().getSelectedItem();
       
                int newSelectedIdx =
                  (selectedIdx == listView.getItems().size() - 1)
                     ? selectedIdx - 1
                     : selectedIdx;
       
                listView.getItems().remove(selectedIdx);
                status.setText("Removed " + itemToRemove);
                listView.getSelectionModel().select(newSelectedIdx);
              }
            }
          });
          Button resetButton = new Button("Reset List"); //resets to the default items, 
          resetButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent event) {
              
              //initListView(listView); //change - make this a separate button that says "use recomended
              emptyListView(listView); //empties list
              
              status.setText("List Reset");
            }
          });
          
          
          HBox controls = new HBox(10);
          controls.setAlignment(Pos.CENTER);
          controls.getChildren().addAll(removeButton, resetButton);
       
          VBox layout = new VBox(10);
          layout.setAlignment(Pos.CENTER);
          layout.setStyle("-fx-padding: 10; -fx-background-color: #00D8FF;");
          layout.getChildren().setAll(
            listView, 
            controls,
            status,
            changeReport,
            resetButton,
            removeButton,
            addBtn,
            textField
          );
          layout.setPrefWidth(210);
              
              
         hbox1.getChildren().add(layout);
         

        /*---------------------------------------
         End of the really long ugly code
        -----------------------------------------*/
        
        
        
        /*-------------------------------------------
         Creating Labels for the rest of the stores:
        ---------------------------------------------*/
        Text mS = new Text("Market Street");
        Text wM = new Text("Walmart");
        Text tG = new Text("Target");
        
        
        Rectangle mark = new Rectangle(300, 25);
        Rectangle wal = new Rectangle(300, 25);
        Rectangle targ = new Rectangle(300, 25);
        
        mark.setFill(Color.WHITE);
        wal.setFill(Color.WHITE);
        targ.setFill(Color.WHITE);
        
        StackPane sHld[] = new StackPane[3]; // store holders - have to create a stack pane to drop the text on top of
        
        for(int i=0;i<sHld.length;i++) //initializing the 3 (stackpane) holders
         sHld[i] = new StackPane();
        
        sHld[0].getChildren().addAll(mark,storeP[0]);
        sHld[1].getChildren().addAll(wal,storeP[1]); //giving them each the contents (texts) of their store
        sHld[2].getChildren().addAll(targ,storeP[2]);
        
        
        Button compare = new Button("Compare");
        
        vbox2.getChildren().addAll(compare, mS,sHld[0], wM,sHld[1], tG,sHld[2]); //adding everything to the second column, in order
        /*-------------------------------------------
         End of Final Store Price List Code
        ---------------------------------------------*/
        
        
        /*-------------------------------------------
         Compare Button Implementation
        ---------------------------------------------*/
         
         compare.setOnAction(action -> {
                  float currentMarket = 0.0f;
                  float currentWalmart = 0.0f;
                  float currentTarget = 0.0f;
                  String currentName = "";
                  
                  if(textField.getText()!=null){
                     sHld[0].getChildren().remove(storeP[0]); //remove old prices
                     sHld[1].getChildren().remove(storeP[1]);
                     
                     for (int i=0;i<listView.getItems().size();i++){
                          currentName = listView.getItems().get(i);
                          System.out.println((i+1)+"."+currentName);
                          
                          if(dbr.SearchSpecific(currentName)==false){ //if there is no database entry
                              currentMarket = new MarketStreet(listView.getItems().get(i)).itemPrice;
                              currentWalmart = new WalmartCrawler(listView.getItems().get(i)).getLowestPrice().getPrice();
                          }
                          else{
                              currentMarket = dbr.getPriceM(currentName);
                              currentWalmart = dbr.getPriceW(currentName);
                          }
                          msTot += currentMarket;
                          wTot += currentWalmart;
                          
                          DatabaseWriter dbw = new DatabaseWriter(currentName,currentWalmart,currentMarket,currentTarget);
                          
                          //sHld[0].getChildren().addAll(mark,storeP[0]);
                     }
                     storeP[0] = new Text( "$"+String.valueOf(msTot) ); //market street gets 
                     storeP[1] = new Text( "$"+String.valueOf(wTot) );
                     
                     sHld[0].getChildren().add(storeP[0]);
                     sHld[1].getChildren().add(storeP[1]);
                  }
          });
        
        /*-------------------------------------------
         End of Compare Button
        ---------------------------------------------*/
        
        vbox1.getChildren().add(hbox1);
        
        hbox1.getChildren().add(vbox2);
        
        hbox1.getChildren().add(empty);
        
        hbox1.getChildren().add(recoL);
        
        
        
        holder.getChildren().add(vbox1);
        
        
                
        
        
        holder.getChildren().add(canvas);
        root.getChildren().add(holder);

        holder.setStyle("-fx-background-color: #33CCFF");
        Scene scene = new Scene(root, 800, 660);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        
        
        launch(args);
    }
    
    void refreshItems(){
    
    
    }
    
    //----- From Github ---
    private void initListView(ListView<String> listView) {
      listView.getItems().setAll(comIt[0], comIt[1], comIt[2], comIt[3],comIt[4]);
    }
    
    //----- Used that one to make this empty list. Where it says initList(listView) you can switch it with this.
    private void emptyListView(ListView<String> listView) {
      listView.getItems().setAll();
    }
    
    //---------------------
}

class gList{ //grocery List
List<gItem> gl = new ArrayList<>();
}

class gItem{ //grocery item
   gItem(String item){
      name = item;
   }
   int price;
   String name;
   String store;
}
