package sample;

import com.jfoenix.controls.JFXSlider;
import com.jfoenix.controls.JFXToggleButton;
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
import javafx.scene.Group;
import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.animation.RotateTransition;
import javafx.animation.ScaleTransition;
import javafx.animation.SequentialTransition;
import javafx.animation.TranslateTransition;
import javafx.util.Duration;

public class Mcmcontroller {
    @FXML
    private TextField dimensions;
    @FXML
    private TextArea elements;
    @FXML
    private ScrollPane S1;
    @FXML
    private JFXSlider slide;
    @FXML
    private JFXToggleButton toggle;
    @FXML
    private ScrollPane S2;

    int total_item,ar[];
    String st[];
    static int H=0;
    int dp[][];
    boolean vis[][];
    int dp2[][];
    boolean vis2[][];
    int ndar[];
    List<Pane> lays = new ArrayList<>();
    final double width= 50, height= 50;
    int widtharr[]= new int[100];
    int widtharr2[]= new int[100];
    int total_node;
    int total_space;
    double speed=50*20;
    boolean simulating;

    SequentialTransition seqT= new SequentialTransition();
    SequentialTransition seqT2= new SequentialTransition();

    List<Nodex> L= new ArrayList<>();
    List<Line> Ln= new ArrayList<>();
    List<Pane> Vb = new ArrayList<>();
    double var,var2;
    int sp;
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
            total_item = Integer.parseInt(dimensions.getText());
            System.out.println(total_item);
            ar = new int[total_item+5];
            ndar = new int[total_item+5];
            st = new String[total_item+5];
            dp = new int[total_item+5][total_item+5];
            vis= new boolean[total_item+5][total_item+5];
            dp2 = new int[total_item+5][total_item+5];
            vis2= new boolean[total_item+5][total_item+5];
            sp =total_item-1;
            total_node= (int)Math.pow( 2,sp );
            total_space= total_node*(int)width+(total_node+1)*50;
        }catch(NumberFormatException e){
            System.out.println("Error! The input should be a Number. . . ");
        }
    }
    private void ok2() {
        st = elements.getText().split("\n");
        for(int i=0;i<total_item;i++){
            try{
                ar[i] = Integer.parseInt(st[i]);
                System.out.println(ar[i]);
            }catch(NumberFormatException e){
                System.out.println("Error! The input should be Number. . . ");
            }
        }
        sp=-1;
        int fans = func(0,total_item-1,0);
        total_space= ndar[sp]*(int)width+(ndar[sp]+1)*50;
        System.out.println(fans);
    }
    public class Nodex extends Rectangle
    {
        int i, w, p;
        Nodex( int i, int w, int p )
        {
            super( 0,0,50,50 );
            System.out.println("Object Created");
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

        Pane pl= new Pane();
        Text txt= new Text("i= Starting index\n"+"j= Ending index\n" + "P= Minimum operation");
        pl.getChildren().addAll(txt);
        pl.setLayoutX(10);
        pl.setLayoutY(10);
        lays.add(pl);
        int ans= MCM(0,total_item-1, -100,0,-100,0);
        Text  tx = new Text("\n\nMinimum operations : " + ans+"\n");
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
    private int func( int start, int end, int d)
    {
        if( vis2[start][end]== true )
            return dp2[start][end];
        sp = Math.max(sp,d);

        ndar[d]++;
        if(end-start==1)
            return 0;

        int min_ = 10000000,left,right;

        for(int i=start+1;i<end;i++)
        {
            left = func(start,i,d+1);
            right = func(i,end,d+1);
            min_ = Math.min(min_,left+(ar[start]*ar[i]*ar[end])+right);
        }

        vis2[start][end]= true;
        dp2[start][end]= min_;


        return dp2[start][end];

    }
    private Line check(int start, int end, int py ,double dx,double dy,int num,int d)
    {
      widtharr2[d]++;

        if( vis[start][end]== true ) {
            widtharr2[d+1]+=2*(end-start-1);
        }
        double add;
        int tn= ndar[d];


        add= (total_space-tn*width)/(double)(tn+1);

        double totadd;

        totadd= add+(widtharr2[d]-1)*(width+add);

        var2 = add;

        Line line;
        var = totadd;
        line = new Line();
        System.out.println("dx : " + dx + " dy : " + dy);
        System.out.println("x : " + totadd+50 + " y : " + py+2*height+100);
        System.out.println(" eta -> "+ widtharr2[d]);
        line.setStartX(dx);
        line.setStartY(dy);
        line.setEndX(totadd+50+25);

        line.setEndY(py+50+100);
        Ln.add(line);
        return line;
    }
    private int MCM( int start, int end, int py ,double dx,double dy,int d)
    {
        widtharr[d]++;

        double add;
        int tn= ndar[d];


        add= (total_space-tn*width)/(double)(tn+1);

        double totadd;

        totadd= add+(widtharr[d]-1)*(width+add);

        if( vis[start][end]== true )
        {
            Text  tx = new Text("\nEntering ("+start + "," + end+ ") state but it is visited!\n");
            Pane fg = new Pane();
            fg.getChildren().add(tx);
            fg.setLayoutX(10);
            fg.setLayoutY(ab+10);
            ab+=12;
            Vb.add(fg);

            widtharr[d+1]+=2*(end-start-1);
            StackPane s= new StackPane();
            Nodex v= new Nodex( start,end,dp[start][end] );
            v.setFill(Color.PLUM);
            v.setStroke(Color.BLACK);
            Text text= new Text( "i= "+start+"\n"+"w= "+end+"\nVisited!" );
            text.setFill(Color.BLACK );
            s.getChildren().addAll(v,text);
            s.setLayoutX(totadd);
            s.setLayoutY(py+height+100);

            lays.add(s);

            Pane pl= new Pane();

            Button b= new Button();
            b.setStyle(" -fx-background-color: lightgrey; ");
            b.setText("P="+dp[start][end]);
            b.setMinWidth(width-10);
            pl.getChildren().addAll(b);
            pl.setLayoutX(totadd+10);
            pl.setLayoutY(py+height+160);
            lays.add(pl);

            return dp[start][end];
        }

        Text  tx9 = new Text("\nEntering ("+start + "," + end+ ") state\n");
        Pane fg9 = new Pane();
        fg9.getChildren().add(tx9);
        fg9.setLayoutX(10);
        fg9.setLayoutY(ab+10);
        ab+=12;
        Vb.add(fg9);

        System.out.println(start+" "+end);
        Nodex p = new Nodex( start,end,dp[start][end] );
        p.setFill(Color.CORNFLOWERBLUE);
        p.setStroke(Color.BLACK);
        L.add(p);

        StackPane box= new StackPane();

        Text text= new Text( "i= "+ start +"\n"+"j= "+end );
        text.setFill(Color.WHITE );
        box.getChildren().addAll(p,text);
        box.setLayoutX(totadd);
        box.setLayoutY(py+height+100);

        lays.add(box);

        Line line1,line2,line3,line4;
        AnchorPane box2= new AnchorPane();
        AnchorPane box3= new AnchorPane();
        AnchorPane box4= new AnchorPane();
        AnchorPane box5= new AnchorPane();
        box4.setLayoutX(0-50);
        box5.setLayoutX(0-50);
        if(end-start==1)
        {
            Text  tx2 = new Text("\nExiting From ("+start + "," + end+ ") state\n");
            Pane fg2 = new Pane();
            fg2.getChildren().add(tx2);
            fg2.setLayoutX(10);
            fg2.setLayoutY(ab+10);
            ab+=12;
            Vb.add(fg2);
            Pane pl= new Pane();
            Button b= new Button();
            b.setStyle(" -fx-background-color: lightgrey; ");
            b.setText("P="+dp[start][end]);
            b.setMinWidth(width-10);
            pl.getChildren().addAll(b);
            pl.setLayoutX(totadd+10);
            pl.setLayoutY(py+height+160);
            lays.add(pl);
            return 0;
        }
        int min_ = 10000000,left,right;

        //ekhan theke
        double xyz = py+100+50+50;
        for(int i=start+1;i<end;i++)
        {
            line1 = check( start, i, py+100, totadd+75,xyz,1,d+1);
            box2.setLayoutX(0-50);
            if(line1!=null)
            {
                box2.getChildren().add(line1);
                line3 = new Line(line1.getEndX(),line1.getEndY(),line1.getStartX(),line1.getStartY());
                line3.setStroke(Color.YELLOW);
                box4.getChildren().add(line3);
            }
        }
        lays.add(box2);
        int[] br = new int[end+2];
        for(int i=start+1;i<end;i++)
        {
            left = MCM(start,i,py+100,totadd+75,xyz ,d+1);
            br[i]=left;
        }
        lays.add(box4);
        for(int i=start+1;i<end;i++)
        {
            box3.setLayoutX(0-50);
            line2 = check( i, end, py+100,totadd+75,xyz,2,d+1 );
            if(line2!=null)
            {
                box3.getChildren().add(line2);
                line4 = new Line(line2.getEndX(),line2.getEndY(),line2.getStartX(),line2.getStartY());
                line4.setStroke(Color.YELLOW);
                box5.getChildren().add(line4);
            }
        }
        lays.add(box3);
        for(int i=start+1;i<end;i++)
        {
            right = MCM(i,end,py+100,totadd+75,xyz ,d+1);
            min_ = Math.min(min_,br[i]+(ar[start]*ar[i]*ar[end])+right);
        }
        Text  tx2 = new Text("\nExiting From ("+start + "," + end+ ") state\n");
        Pane fg2 = new Pane();
        fg2.getChildren().add(tx2);
        fg2.setLayoutX(10);
        fg2.setLayoutY(ab+10);
        ab+=12;
        Vb.add(fg2);
        lays.add(box5);

        //ekhane porjnto


       /* for(int i=start+1;i<end;i++)
         {
            double xyz = py+100+50+50;
            line1 = check( start, i, py+100, totadd+75,xyz,1,d+1);
            box2.setLayoutX(0-50);
            box3.setLayoutX(0-50);
            double xy = (25.0/150.0)*var;
            if(line1!=null)
            {
                //lays.add(line1);
                box2.getChildren().add(line1);
                //lays.add(box2);
            }
            line2 = check( i, end, py+100,totadd+75,xyz,2,d+1 );


            left = MCM(start,i,py+100,totadd+75,xyz ,d+1);
            if(line2!=null)
            {
                box3.getChildren().add(line2);
                //lays.add(box3);
            }
            right = MCM(i,end,py+100,totadd+75,xyz ,d+1);
            min_ = Math.min(min_,left+(ar[start]*ar[i]*ar[end])+right);
        }
        lays.add(box2);
        lays.add(box3);*/

        vis[start][end]= true;
        dp[start][end]= min_;

        Pane pl= new Pane();

        Button b= new Button();
        b.setStyle(" -fx-background-color: lightgrey; ");
        b.setText("P="+dp[start][end]);
        b.setMinWidth(width-10);
        pl.getChildren().addAll(b);
        pl.setLayoutX(totadd+10);
        pl.setLayoutY(py+height+160);
        lays.add(pl);

        return dp[start][end];

    }
}
