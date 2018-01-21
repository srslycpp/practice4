package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

//import java.awt.*;
//import java.awt.Dimension;

public class Main extends Application {
    int ilosc =8;
    int counter;
    int lastIndex;
    int imageid;
    List<Button> buttonList = new ArrayList<>();
    int num [] = losowe(ilosc);
    int num2 [] = losowe(ilosc);

    @Override
    public void start(Stage primaryStage) throws Exception{
//        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Memory");
        FlowPane flow = new FlowPane();
        flow.setHgap(10);
        flow.setVgap(10);

        for(int i=1; i<=ilosc;i++){
            Button button = new Button();
            System.out.println("num: "+num[i-1]);
            imageid = num[i-1];
            button.setPrefHeight(120);
            button.setPrefWidth(120);
            button.setId(String.valueOf(imageid));
            buttonList.add(button);
            button.setOnAction(event -> {
                counter++;
                Button clickedButton = (Button) event.getSource();
                Image image = new Image(getClass().getResourceAsStream("/"+clickedButton.getId()+".png"));

                System.out.println("Imageid: "+imageid);
                System.out.println("buttonid: "+button.getId());
                clickedButton.setGraphic(new ImageView(image));
                if(counter %2==0){
                    if(buttonList.get(lastIndex).getId().equals(clickedButton.getId())){
                        clickedButton.setDisable(true);
                        buttonList.get(lastIndex).setDisable(true);
                    }
                    else {
                         buttonList.get(lastIndex).setGraphic(null);
                         counter =1;
                    }
                }
                lastIndex = buttonList.indexOf(clickedButton);
                System.out.println("Last Index: "+ lastIndex);
            });
            flow.getChildren().addAll(button);
        }
        for(int i=1; i<=ilosc;i++){
            Button button = new Button();

            System.out.println("num2: "+num2[i-1]);
            imageid = num2[i-1];
            button.setPrefHeight(120);
            button.setPrefWidth(120);
            button.setId(String.valueOf(imageid));
            buttonList.add(button);
            button.setOnAction(event -> {
                counter++;
                Button clickedButton = (Button) event.getSource();
                Image image = new Image(getClass().getResourceAsStream("/"+clickedButton.getId()+".png"));

                System.out.println("Imageid: "+imageid);
                System.out.println("buttonid: "+button.getId());
                clickedButton.setGraphic(new ImageView(image));
                if(counter %2==0){
                    if(buttonList.get(lastIndex).getId().equals(clickedButton.getId())){
                        clickedButton.setDisable(true);
                        buttonList.get(lastIndex).setDisable(true);
                    }
                    else {
                        buttonList.get(lastIndex).setGraphic(null);

                        counter =1;
                    }
                }
                lastIndex = buttonList.indexOf(clickedButton);
                System.out.println("Last Index: "+ lastIndex);
            });
            flow.getChildren().addAll(button);
        }
        if(ilosc==8) {
            primaryStage.setScene(new Scene(flow, 510, 510)); 
            primaryStage.show();
        }
        if(ilosc==12) {
            primaryStage.setScene(new Scene(flow, 510, 380));
            primaryStage.show();
        }
    }


    public int[]losowe(int ilosc){
        int tab[] = new int[ilosc];
        int i =0;
        do{
            int liczba =(int)(Math.random()*ilosc)+1;
            if(czyWylosowana(liczba,tab,i)){
                tab[i]=liczba;
                i++;
            }
        }while(i<ilosc);
        return tab;
    }
    public boolean czyWylosowana(int liczba, int []tab, int ilosc) {

        int i = 0;
        do{
            if (tab[i] == liczba)
                return false;
            i++;
        }while(i<ilosc);
        return true;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
