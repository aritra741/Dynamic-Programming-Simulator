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

public class Lcscontroller {
    @FXML
    private TextField str1,str2;
    @FXML
    private ScrollPane S1;

    @FXML
    private JFXSlider slide;
    @FXML
    private JFXToggleButton toggle;
    @FXML
    private ScrollPane S2;

    String s1,s2;
    int sp;
    static int H=0;
    int dp[][];
    int dp2[][];
    boolean vis[][];
    boolean vis2[][];
    List<Pane> lays = new ArrayList<>();
    final double width= 50, height= 50;
    int widtharr[]= new int[100];
    int widtharr2[]= new int[100];
    int total_node;
    int total_space;
    boolean simulating;

    SequentialTransition seqT= new SequentialTransition();
    SequentialTransition seqT2= new SequentialTransition();
    List<Nodex> L= new ArrayList<>();
    List<Line> Ln= new ArrayList<>();
    List<Pane> Vb = new ArrayList<>();
    int lv=-1000000;
    int l1,l2;
    double var,var2;
    double speed=50*20;
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
        s1 = str1.getText();
        System.out.println(s1);
    }
    private void ok2(){
        s2 = str2.getText();
        System.out.println(s2);
        l1 = s1.length();
        l2=s2.length();
        dp = new int[l1+l2+5][l1+l2+5];
        dp2 = new int[l1+l2+5][l1+l2+5];
        vis= new boolean[l1+l2+5][l1+l2+5];
        vis2= new boolean[l1+l2+5][l1+l2+5];
        lv =-1;
        int fans = func(0,0,0);
        //System.out.println(fans);
        sp =lv;
        total_node= (int)Math.pow( 2,sp );
        total_space= total_node*(int)width+(total_node+1)*50;
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
        flagsm = true;
        int ans;
        Pane pl= new Pane();
        Text txt= new Text("i= index of first string\n"+"j= index of second string\n" + "P= Longest common subsequence in that state");
        pl.getChildren().addAll(txt);
        pl.setLayoutX(10);
        pl.setLayoutY(10);
        lays.add(pl);
        ans = LCS(0,0, -100,0);
        Text  tx = new Text("\n\nLongest Common Subsequence is : " + ans+"\n");
        Pane fg = new Pane();
        tx.setFont(Font.font("Verdana", FontWeight.BOLD, 11));
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
        jb.setMaxWidth(250);
        jb.setMinWidth(250);
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
    private int func( int i, int j, int d)
    {
        if(i==l1 || j==l2)
        {
            return 0;
        }

        lv = Math.max(lv,d);
        if( vis2[i][j]== true )
            return dp2[i][j];

        if(s1.charAt(i)==s2.charAt(j))
        {
            dp2[i][j] = 1+func(i+1,j+1,d+1);
        }
        else
        {
            dp2[i][j] = Math.max(func(i,j+1,d+1),func(i+1,j,d+1));
        }

        vis2[i][j]= true;


        return dp2[i][j];

    }
    private Line check(int i, int j, int py ,double dx,double dy,int num,int d)
    {
        widtharr2[d]++;
        if( vis[i][j]== true ) {
            widtharr2[d+1]+=2;
        }
        double add;

        int tn= (int)Math.pow( 2,d );



        add= (total_space-tn*width)/(double)(tn+1);

        var2 = add;
        double totadd;

        totadd= add+(widtharr2[d]-1)*(width+add);
        if(i==l1 || j==l2)
        {
            return null;
        }


        Line line;
        var = totadd;
        line = new Line();
        System.out.println("dx : " + dx + " dy : " + dy);
        System.out.println("x : " + totadd+50 + " y : " + py+2*height+100);
        System.out.println(" eta -> "+ widtharr2[i]);
        line.setStartX(dx);
        line.setStartY(dy);
        line.setEndX(totadd+50+25);

        line.setEndY(py+50+100);
        Ln.add(line);
        return line;
    }
    private int LCS( int i, int j, int py ,int d)
    {
        widtharr[d]++;
        double add;
        int tn= (int)Math.pow( 2,d );


        add= (total_space-tn*width)/(double)(tn+1);

        double totadd;

        totadd= add+(widtharr[d]-1)*(width+add);

        if(i==l1 || j==l2)
        {
            return 0;
        }

        if( vis[i][j]== true )
        {
            Text  tx = new Text("\nEntering ("+i + "," + j+ ") state but it is visited!\n");
            Pane fg = new Pane();
            fg.getChildren().add(tx);
            fg.setLayoutX(10);
            fg.setLayoutY(ab+10);
            ab+=12;
            Vb.add(fg);
            widtharr[d+1]+=2;
            StackPane s= new StackPane();
            Nodex v= new Nodex( i,j,dp[i][j] );
            v.setFill(Color.PLUM);
            v.setStroke(Color.BLACK);
            Text text= new Text( "i= "+i+"\n"+"w= "+j+"\nVisited!" );
            text.setFill(Color.BLACK );
            s.getChildren().addAll(v,text);
            s.setLayoutX(totadd);
            s.setLayoutY(py+height+100);

            lays.add(s);

            Pane pl= new Pane();

            Button b= new Button();
            b.setStyle(" -fx-background-color: lightgrey; ");
            b.setText("P="+dp[i][j]);
            b.setMinWidth(width-10);
            pl.getChildren().addAll(b);
            pl.setLayoutX(totadd+10);
            pl.setLayoutY(py+height+160);
            lays.add(pl);

            return dp[i][j];
        }
        Text  tx = new Text("\nEntering ("+i + "," + j+ ") state\n");
        Pane fg = new Pane();
        fg.getChildren().add(tx);
        fg.setLayoutX(10);
        fg.setLayoutY(ab+10);
        ab+=12;
        Vb.add(fg);
        System.out.println(i+" "+j);
        Nodex p = new Nodex( i,j,dp[i][j] );
        p.setFill(Color.CORNFLOWERBLUE);
        p.setStroke(Color.BLACK);
        L.add(p);

        StackPane box= new StackPane();

        Text text= new Text( "i= "+ i +"\n"+"j= "+j );
        text.setFill(Color.BLACK);
        box.getChildren().addAll(p,text);
        box.setLayoutX(totadd);
        box.setLayoutY(py+height+100);

        Line line1,line2,line3,line4,line5,line6;
        AnchorPane box2= new AnchorPane();
        AnchorPane box3= new AnchorPane();
        AnchorPane box4= new AnchorPane();
        AnchorPane box6= new AnchorPane();
        AnchorPane box5= new AnchorPane();
        AnchorPane box7= new AnchorPane();
        box5.setLayoutX(0-50);
        box6.setLayoutX(0-50);
        box7.setLayoutX(0-50);
        lays.add(box);

        double xyz = py+100+50+50;

        double xy = (25.0/150.0)*var;

        if(s1.charAt(i)==s2.charAt(j))
        {
            line1 = check( i+1, j+1, py+100, totadd+75,xyz,1,d+1);
            box2.setLayoutX(0-50);

            if(line1!=null)
            {
                box2.getChildren().add(line1);
                lays.add(box2);
                line4 = new Line(line1.getEndX(),line1.getEndY(),line1.getStartX(),line1.getStartY());
                line4.setStroke(Color.YELLOW);
                box5.getChildren().add(line4);
            }

            dp[i][j] = 1+LCS(i+1,j+1,py+100,d+1);
            lays.add(box5);
        }
        else
        {
            line2 = check( i, j+1, py+100,totadd+75,xyz,2,d+1 );
            box3.setLayoutX(0-50);
            if(line2!=null)
            {
                box3.getChildren().add(line2);
                lays.add(box3);
                line5 = new Line(line2.getEndX(),line2.getEndY(),line2.getStartX(),line2.getStartY());
                line5.setStroke(Color.YELLOW);
                box6.getChildren().add(line5);
            }
            line3 = check( i+1, j, py+100,totadd+75,xyz,2,d+1 );
            box4.setLayoutX(0-50);
            int aa = LCS(i,j+1,py+100,d+1);
            lays.add(box6);
            if(line3!=null)
            {
                box4.getChildren().add(line3);
                lays.add(box4);
                line6 = new Line(line3.getEndX(),line3.getEndY(),line3.getStartX(),line3.getStartY());
                line6.setStroke(Color.YELLOW);
                box7.getChildren().add(line6);
            }

            int bb = LCS(i+1,j,py+100,d+1);
            lays.add(box7);
            dp[i][j] = Math.max(aa,bb);
        }
        Text  tx4 = new Text("\nReturning to ("+i + "," + j+ ") state\n");
        Pane fg4 = new Pane();
        fg4.getChildren().add(tx4);
        fg4.setLayoutX(10);
        fg4.setLayoutY(ab+10);
        ab+=12;
        Vb.add(fg4);

        Text  tx2 = new Text("\n LCS: " + dp[i][j]+"\n");
        Pane fg2 = new Pane();
        fg2.getChildren().add(tx2);
        fg2.setLayoutX(10);
        fg2.setLayoutY(ab+10);
        ab+=12;
        Vb.add(fg2);
        Text  tx5 = new Text("\nExiting From ("+i + "," + j+ ") state\n");
        Pane fg5 = new Pane();
        fg5.getChildren().add(tx5);
        fg5.setLayoutX(10);
        fg5.setLayoutY(ab+10);
        ab+=12;
        Vb.add(fg5);
        vis[i][j]= true;

        Pane pl= new Pane();

        Button b= new Button();
        b.setStyle(" -fx-background-color: lightgrey; ");
        b.setText("P="+dp[i][j]);
        b.setMinWidth(width-10);
        pl.getChildren().addAll(b);
        pl.setLayoutX(totadd+10);
        pl.setLayoutY(py+height+160);
        lays.add(pl);

        return dp[i][j];

    }
}
