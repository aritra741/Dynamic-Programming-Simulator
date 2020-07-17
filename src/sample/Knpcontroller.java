package sample;
import java.io.IOException;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXScrollPane;
import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleButton;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.geometry.Insets;
import javafx.stage.*;
//import java.awt.*;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.*;
import sample.Main;
//import java.awt.event.ActionEvent;
import java.sql.SQLOutput;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

import javax.swing.*;


public class Knpcontroller {
    @FXML
    private Label Lbb;
    @FXML
    private TextField knpitm;
    @FXML
    private TextArea wg;
    @FXML
    private TextArea cst;
    @FXML
    private TextField mxwg;
    @FXML
    private ScrollPane S1;
    @FXML
    private JFXSlider slide;
    @FXML
    private JFXToggleButton toggle;

    int total_item,mx_wgt;
    int weight[],cost[];
    String st[],st2[];
    static int H=0;
    int dp[][];
    boolean vis[][];
    List<Pane> lays = new ArrayList<>();
    final double width= 50, height= 50;
    int widtharr[];
    int widtharr2[];
    int total_node;
    int total_space;
    double speed= 50*20;
    SequentialTransition seqT= new SequentialTransition();
    SequentialTransition seqT2= new SequentialTransition();
   // List<Nodex> L= new ArrayList<>();
    List<Line> Ln= new ArrayList<>();
    List<Pane> Vb = new ArrayList<>();
    double var,var2;
    int sp;
    boolean simulating;
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
            total_item = Integer.parseInt(knpitm.getText());
            System.out.println(total_item);
            weight = new int[total_item+5];
            cost = new int[total_item+5];
            st = new String[total_item+5];
            st2 = new String[total_item+5];
            widtharr = new int[total_item+5];
            widtharr2 = new int[total_item+5];
            sp =total_item-1;
            total_node= (int)Math.pow( 2,sp );
            total_space= total_node*(int)width+(total_node+1)*50;
        }catch(NumberFormatException e){
            System.out.println("Error! The input should be a Number. . . ");
        }
    }

    private void ok2() {
        try{
            mx_wgt = Integer.parseInt(mxwg.getText());
            System.out.println(mx_wgt);

            dp = new int[total_item+5][mx_wgt+5];
            vis= new boolean[total_item+5][mx_wgt+5];
        }catch(NumberFormatException e){
            System.out.println("Error! The input should be a Number. . . ");
        }
    }

    private void ok3() {
        st = wg.getText().split("\n");
        st2 = cst.getText().split("\n");
        for(int i=0;i<total_item;i++){
            try{
                weight[i] = Integer.parseInt(st[i]);
                cost[i] = Integer.parseInt(st2[i]);
                System.out.println(cost[i]);
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
        Pane pl= new Pane();
        Text txt= new Text("i= index\n"+"w= weight at current state\n"+"P= Profit");
        pl.getChildren().addAll(txt);
        pl.setLayoutX(10);
        pl.setLayoutY(10);
        lays.add(pl);
        System.out.println("Recursion");
        int ans= Knpsck(0,0, -100,0,-100);
        Text  tx = new Text("\n\nProfit is : " + ans+"\n");
        Pane fg = new Pane();
        tx.setFont(Font.font("Verdana", FontWeight.BOLD, 14));
        fg.getChildren().add(tx);
        fg.setLayoutX(10);
        fg.setLayoutY(ab+10);
        ab+=12;
        Vb.add(fg);
        System.out.println(ans);
        Pane Root = new Pane();
        Root.getChildren().addAll(lays);
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

    private Line check(int i, int w, int py ,double dx,double dy,int num)
    {
        widtharr2[i]++;
        double add;
        int tn= (int)Math.pow( 2,i );

        System.out.println(total_item);

        add= (total_space-tn*width)/(double)(tn+1);

        var2 = add;
        double totadd;

        totadd= add+(widtharr2[i]-1)*(width+add);
        if( w>mx_wgt )
            return null;
        if( i>=total_item )
            return null;

        if( vis[i][w]== true ) {
            widtharr2[i+1]+=2;
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
    private int Knpsck( int i, int w, int py ,double dx,double dy)
    {
        widtharr[i]++;
        int tn= (int)Math.pow( 2,i );
        double add= (total_space-tn*width)/(double)(tn+1);
        double totadd;
        totadd= add+(widtharr[i]-1)*(width+add);
        System.out.println(i+" "+w);
        if( w>mx_wgt )
            return 0;
        if( i>=total_item )
            return 0;
        if( vis[i][w]== true )
        {
            Text  tx2 = new Text("\n Entering ("+i + "," + w+ ") starts but it is visited!\n");
            Pane fg2 = new Pane();
            fg2.getChildren().add(tx2);
            fg2.setLayoutX(10);
            fg2.setLayoutY(ab+10);
            ab+=12;
            Vb.add(fg2);
            Text  tx3 = new Text("\nprofit is " + dp[i][w] +"\n");
            Pane fg3 = new Pane();
            fg3.getChildren().add(tx3);
            fg3.setLayoutX(10);
            fg3.setLayoutY(ab+10);
            ab+=12;
            StackPane s= new StackPane();
            Nodex v= new Nodex( i,w,dp[i][w] );
            v.setFill(Color.PLUM);
            v.setStroke(Color.BLACK);
            Text text= new Text( "i= "+i+"\n"+"w= "+w+"\nVisited!" );
            text.setFill(Color.BLACK );
            s.getChildren().addAll(v,text);
            s.setLayoutX(totadd);
            s.setLayoutY(py+height+100);

            lays.add(s);

            Pane pl= new Pane();

            Button b= new Button();
            b.setStyle(" -fx-background-color: lightgrey; ");
            b.setText("P="+dp[i][w]);
            b.setMinWidth(width-10);
            pl.getChildren().addAll(b);
            pl.setLayoutX(totadd);
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
       // L.add(p);

        StackPane box= new StackPane();
        Text text= new Text( "i= "+i+"\n"+"w= "+w );
        text.setFill(Color.BLACK );
        box.getChildren().addAll(p,text);
        box.setLayoutX(totadd);
        box.setLayoutY(py+height+100);

        Line line1,line2;
        AnchorPane box2= new AnchorPane();
        AnchorPane box3= new AnchorPane();
        lays.add(box);
        AnchorPane box4= new AnchorPane();
        AnchorPane box5= new AnchorPane();

        double xyz = py+100+50+50;
        line1 = check( i+1, w+weight[i], py+100, totadd+75,xyz,1);
        box2.setLayoutX(0-50);
        box3.setLayoutX(0-50);
        box4.setLayoutX(0-50);
        box5.setLayoutX(0-50);
        double xy = (25.0/150.0)*var;

        line2 = check( i+1, w, py+100,totadd+75,xyz,2 );

        Line line3,line4;
        if(line1!=null)
        {

            box2.getChildren().add(line1);
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
        lays.add(box2);
        int q1= cost[i]+Knpsck( i+1, w+weight[i], py+100 ,totadd+75,xyz);
        lays.add(box4);
        lays.add(box3);
        int q2= Knpsck( i+1, w, py+100,totadd+75,xyz );
        dp[i][w]= Math.max( q1,q2 );
        Text  tx4 = new Text("\nReturning to ("+i + "," + w+ ") state\n");
        Pane fg4 = new Pane();
        fg4.getChildren().add(tx4);
        fg4.setLayoutX(10);
        fg4.setLayoutY(ab+10);
        ab+=12;
        Vb.add(fg4);
        Text  tx2 = new Text("\n Profit is " + dp[i][w]+"\n");
        Pane fg2 = new Pane();
        fg2.getChildren().add(tx2);
        fg2.setLayoutX(10);
        fg2.setLayoutY(ab+10);
        ab+=12;
        Vb.add(fg2);
        Text  tx3 = new Text("\nExiting ("+i + "," + w+ ") state\n");
        Pane fg3 = new Pane();
        fg3.getChildren().add(tx3);
        fg3.setLayoutX(10);
        fg3.setLayoutY(ab+10);
        ab+=12;
        Vb.add(fg3);
        lays.add(box5);
        vis[i][w]= true;

        Pane pl= new Pane();

        Button b= new Button();
        b.setStyle(" -fx-background-color: lightgrey; ");
        b.setText("P="+dp[i][w]);
        b.setMinWidth(width-10);
        pl.getChildren().addAll(b);
        pl.setLayoutX(totadd+10);
        pl.setLayoutY(py+height+160);

        lays.add(pl);

        return dp[i][w];
    }


}
