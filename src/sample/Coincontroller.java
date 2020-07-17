package sample;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleButton;
import javafx.fxml.FXML;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;

import javafx.scene.control.ScrollPane;
import javafx.geometry.Insets;
import javafx.stage.*;
//import java.awt.*;
import java.io.IOException;
import java.util.*;
import sample.Main;
//import java.awt.event.ActionEvent;
import java.sql.SQLOutput;
import javafx.scene.shape.Line;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;


public class Coincontroller {
    @FXML
    private TextField total_coins;
    @FXML
    private TextField amount;
    @FXML
    private TextArea coin_value;
    @FXML
    private ScrollPane S1;

    @FXML
    private JFXSlider slide;
    @FXML
    private JFXToggleButton toggle;
    @FXML
    private ScrollPane S2;

    int total_item;
    int cost[];
    int make;
    String st[];
    int widtharr[];
    int dp[][];
    boolean vis[][];
    int dp2[][];
    boolean vis2[][];
    int widtharr2[];
    boolean simulating;

    List<Pane> lays = new ArrayList<>();
    final double width= 50, height= 50;
    int total_node;
    int total_space,mn=1000000;
    SequentialTransition seqT= new SequentialTransition();
    SequentialTransition seqT2= new SequentialTransition();
    List<Nodex> L= new ArrayList<>();
    List<Line> Ln= new ArrayList<>();
    List<Pane> Vb = new ArrayList<>();
    double var,var2;
    int sp;
    double speed=50*20;

    //boolean simulating;
    boolean flagtg=false,flagsm=false;
    Stage det;
    int ab =0;
    @FXML
    private void details() {
        if (toggle.isSelected() == true) {
            flagtg = true;
            if (flagsm == true) {
                det.show();
            }
            //System.out.println("ha");
        } else {
            flagtg = false;
            if (flagsm == true) {
                det.close();
            }
            //System.out.println("na");
        }
    }
    @FXML
    private void Play()
    {
        if( simulating==false )
        {
            simulating= true;
            seqT.play();
            seqT2.play();
        }
    }

    @FXML
    private void Pause()
    {
        if(simulating==true)
        {
            simulating= false;
            seqT.pause();
            seqT2.pause();
        }
    }

    private void ok(){
        try{
            total_item = Integer.parseInt(total_coins.getText());
            System.out.println(total_item);
            cost = new int[total_item+5];

            st = new String[total_item+5];

        }catch(NumberFormatException e){
            System.out.println("Error! The input should be a Number. . . ");
        }
    }

    private void ok2() {
        try{
            make = Integer.parseInt(amount.getText());
            System.out.println(make);
            vis= new boolean[total_item+2][make+2];
            dp = new int[total_item+2][make+2];
            vis2= new boolean[total_item+2][make+2];
            dp2 = new int[total_item+2][make+2];
        }catch(NumberFormatException e){
            System.out.println("Error! The input should be a Number. . . ");
        }
    }
    private void ok3() {
        st = coin_value.getText().split("\n");
        for(int i=0;i<total_item;i++){
            try{
                cost[i] = Integer.parseInt(st[i]);
                System.out.println(cost[i]);
                if(cost[i]<mn){
                    mn = cost[i];
                }
            }catch(NumberFormatException e){
                System.out.println("Error! The input should be Number. . . ");
            }
        }
    }
    public class Nodex extends Rectangle
    {
        int i, w, p;
        Nodex( int i, int w, int p )
        {
            super( 0,0,50,50 );
            this.i= i;
            this.w= w;
            this.p= p;
        }

    }

    @FXML
    private void ok4() {
        S1.setStyle( "-fx-background: #DBD9D1;\n" +
                "   -fx-border-color: WHITE;" );
        ok();
        ok2();
        ok3();
        flagsm = true;
        sp =-1;
        int fans = func(0,0,0);
        widtharr  = new int[sp+5];
        widtharr2 = new int[sp+5];
        total_node= (int)Math.pow( 2,sp );
        total_space= total_node*(int)width+(total_node+1)*50;
        Pane pl= new Pane();
        Text txt= new Text("i= index\n"+"$= Money made till that state\n" + "W= The number of ways you can make that amount of money");
        pl.getChildren().addAll(txt);
        pl.setLayoutX(10);
        pl.setLayoutY(10);
        lays.add(pl);
        int ans= Coinchange(0,0, -100,0,-100,0);
        Text  tx = new Text("\n\nNumber of ways : " + ans+"\n");
        Pane fg = new Pane();
        tx.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        fg.getChildren().add(tx);
        fg.setLayoutX(10);
        fg.setLayoutY(ab+10);
        ab+=12;
        Vb.add(fg);
        System.out.println(ans);
        System.out.println(sp);
        Pane Root = new Pane();
        Root.getChildren().addAll(lays);
       // Root.setPadding( new Insets( 10,10,10,10 ) );
        Pane Root00 = new Pane();
        Root00.getChildren().addAll(Vb);
        Root00.setPadding( new Insets( 10,10,10,10 ) );
        ScrollPane jb = new ScrollPane();
        Pane Root2 = new Pane();
        jb.setMinHeight(550);
        jb.setMaxHeight(550);
        jb.setMinWidth(250);
        jb.setMaxWidth(250);
        Root2.getChildren().addAll(jb);
        Root2.setPadding( new Insets( 10,10,10,10 ) );
        try {
            det = new Stage();
            det.setScene(new Scene(Root2, 250, 550));
            if(flagtg==true)
            {
                det.show();
            }
        }catch(Exception e){
            System.out.println("ERROR");
        }
        double abc = lays.size()*speed/Vb.size();
        for( Pane x: lays )
        {
            FadeTransition fade = new FadeTransition(Duration.millis(speed),x);
            fade.setFromValue(0.0f);
            fade.setToValue(1.f);
            x.setVisible(true);
            seqT.getChildren().addAll(fade);
        }

        for( Pane x: Vb )
        {
            FadeTransition fade = new FadeTransition(Duration.millis((int)abc),x);
            fade.setFromValue(0.0f);
            fade.setToValue(1.f);
            x.setVisible(true);
            seqT2.getChildren().addAll(fade);
        }
        S1.setContent(Root);
        seqT.play();

        try{
            jb.setContent(Root00);
        }catch(NullPointerException e){

        }
        seqT2.play();
        simulating= true;
    }
    @FXML
    private void click()
    {
        ActionEvent e= new ActionEvent();

        clicked(e);
    }

    @FXML
    private void back()
    {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root);
        Main.AStage.setScene(scene);
    }

    private void clicked( ActionEvent e1 )
    {
        speed= slide.getValue();
        System.out.println("SPEED "+speed);
        speed= 100-speed+1;

        speed*=20.0;
    }
    private int func( int i, int w, int d ) {

        if (w > make) {
            return 0;
        }
        if (i >= total_item)
            return 0;
        sp = Math.max(sp,d);
        if (vis2[i][w] == true) {
            return dp2[i][w];
        }
        if(w==make)
        {
            return 1;
        }
        int ret1 =0  ,ret2=0;
        ret1 = func(i,w+cost[i],d+1);
        ret2 = func(i+1,w, d+1);

        vis2[i][w]= true;
        dp2[i][w]= ret1+ret2;
        return dp2[i][w];
    }

    private Line check(int i, int w, int py ,double dx,double dy,int num,int d)
    {
        double add;
        widtharr2[d]++;
        int tn= (int)Math.pow( 2,d );

        add= (total_space-tn*width)/(double)(tn+1);

        double totadd;

        totadd= add+(widtharr2[d]-1)*(width+add);

        var2 = add;
        var = totadd;
        if(w>make)
        {
            return null;
        }
        if(i>=total_item)
            return null;

       if( vis[i][w]== true ) {
           widtharr2[d+1]+=2;
        }

        Line line;
        var = totadd;
        line = new Line();

        line.setStartX(dx);
        line.setStartY(dy);
        line.setEndX(totadd+50+25);

        line.setEndY(py+50+100);
        Ln.add(line);
        return line;
    }
    private int Coinchange( int i, int w, int py,double dx,double dy,int d )
    {
        double add;
        widtharr[d]++;
        int tn= (int)Math.pow( 2,d );


        add= (total_space-tn*width)/(double)(tn+1);

        double totadd;

        totadd= add+(widtharr[d]-1)*(width+add);

        if(w>make)
        {
            return 0;
        }
        if(i>=total_item)
            return 0;
        if( vis[i][w]== true )
        {
            Text  tx = new Text("\nEntering ("+i + "," + w+ ") state but it is visited!\n");
            Pane fg = new Pane();
            fg.getChildren().add(tx);
            fg.setLayoutX(10);
            fg.setLayoutY(ab+10);
            ab+=12;
            Vb.add(fg);
            widtharr[d+1]+=2;
            StackPane s= new StackPane();
            Nodex v= new Nodex( i,w,dp[i][w] );
            v.setFill(Color.PLUM);
            v.setStroke(Color.BLACK);
            Text text= new Text( "i= "+(int)(i+1)+"\n"+"$"+w+"\nVisited!" );
            text.setFill(Color.BLACK );
            s.getChildren().addAll(v,text);
            s.setLayoutX(totadd);
            s.setLayoutY(py+height+100);

            lays.add(s);

            Pane pl= new Pane();
            Button b= new Button();
            b.setStyle(" -fx-background-color: lightgrey; ");
            b.setText("W="+dp[i][w]);
            b.setMinWidth(width-10);
            pl.getChildren().addAll(b);
            pl.setLayoutX(totadd+10);
            pl.setLayoutY(py+height+160);
            lays.add(pl);

            return dp[i][w];
        }
        Text  tx = new Text("\nEntering ("+i + "," + w+ ") state\n");
        Pane fg = new Pane();
        fg.getChildren().add(tx);
        fg.setLayoutX(10);
        fg.setLayoutY(ab+10);
        ab+=12;
        Vb.add(fg);

        Nodex p = new Nodex( i,w,dp[i][w] );
        p.setFill(Color.CORNFLOWERBLUE);
        p.setStroke(Color.BLACK);
        L.add(p);

        StackPane box= new StackPane();

        Text text= new Text( "i= "+i+"\n"+"$"+w );
        text.setFill(Color.BLACK );
        box.getChildren().addAll(p,text);
        box.setLayoutX(totadd);
        box.setLayoutY(py+height+100);

        Line line1,line2,line3,line4;
        AnchorPane box2= new AnchorPane();
        AnchorPane box3= new AnchorPane();
        AnchorPane box4= new AnchorPane();
        AnchorPane box5= new AnchorPane();
        lays.add(box);
        if(w==make)
        {
            Text  tx2 = new Text("\nEntering ("+i + "," + w+ ") state\n");
            Pane fg2 = new Pane();
            fg2.getChildren().add(tx2);
            fg2.setLayoutX(10);
            fg2.setLayoutY(ab+10);
            ab+=12;
            Vb.add(fg2);
            Pane pl= new Pane();
            Button b= new Button();
            b.setStyle(" -fx-background-color: lightgrey; ");
            b.setText("W="+dp[i][w]);
            b.setMinWidth(width-10);
            pl.getChildren().addAll(b);
            pl.setLayoutX(totadd+10);
            pl.setLayoutY(py+height+160);
            lays.add(pl);
            return 1;
        }
        double xyz = py+100+50+50;
        line1 = check( i, w+cost[i], py+100, totadd+75,xyz,1,d+1);
        box2.setLayoutX(0-50);
        box3.setLayoutX(0-50);
        box4.setLayoutX(0-50);
        box5.setLayoutX(0-50);
        double xy = (25.0/150.0)*var;
        line2 = check( i+1, w, py+100,totadd+75,xyz,2,d +1);
        if(line1!=null)
        {
            box2.getChildren().add(line1);
            lays.add(box2);
            line3 = new Line(line1.getEndX(),line1.getEndY(),line1.getStartX(),line1.getStartY());
            line3.setStroke(Color.YELLOW);
            box4.getChildren().add(line3);
        }
        if(line2!=null)
        {
            box3.getChildren().add(line2);
            line4 = new Line(line2.getEndX(),line2.getEndY(),line2.getStartX(),line2.getStartY());
            line4.setStroke(Color.YELLOW);
            box5.getChildren().add(line4);
        }

        int ret1 =0  ,ret2=0;
        ret1 = Coinchange(i,w+cost[i], py+100,totadd+75,xyz,d+1);
        lays.add(box4);
        if(line2!=null)
        {
            lays.add(box3);
        }
        ret2 = Coinchange(i+1,w, py+100,totadd+75,xyz,d+1);
        Text  tx4 = new Text("\nReturning to ("+i + "," + w+ ") state\n");
        Pane fg4 = new Pane();
        fg4.getChildren().add(tx4);
        fg4.setLayoutX(10);
        fg4.setLayoutY(ab+10);
        ab+=12;
        Vb.add(fg4);

        dp[i][w]= ret1+ret2;

        Text  tx2 = new Text("\n Number of ways " + dp[i][w]+"\n");
        Pane fg2 = new Pane();
        fg2.getChildren().add(tx2);
        fg2.setLayoutX(10);
        fg2.setLayoutY(ab+10);
        ab+=12;
        Vb.add(fg2);
        Text  tx5 = new Text("\nExiting From ("+i + "," + w+ ") state\n");
        Pane fg5 = new Pane();
        fg5.getChildren().add(tx5);
        fg5.setLayoutX(10);
        fg5.setLayoutY(ab+10);
        ab+=12;
        Vb.add(fg5);
        lays.add(box5);
        vis[i][w]= true;

        Pane pl= new Pane();
        Button b= new Button();
        b.setStyle(" -fx-background-color: lightgrey; ");
        b.setText("W="+dp[i][w]);
        b.setMinWidth(width-10);
        pl.getChildren().addAll(b);
        pl.setLayoutX(totadd+10);
        pl.setLayoutY(py+height+160);
        lays.add(pl);

        return dp[i][w];
    }

}
