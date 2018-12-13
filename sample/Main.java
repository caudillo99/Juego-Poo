package sample;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import javafx.event.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException
    {
        //String song = "src/Audio/Super Mario.wav";
//        Media audio = new Media(new File(song).toURI().toString());
  //      MediaPlayer mediaPlayer = new MediaPlayer(audio);


        primaryStage.initStyle(StageStyle.TRANSPARENT);
        PdeJuego pantallas = new PdeJuego();
        Image image = new Image(getClass().getResourceAsStream("Maker.png"));
        ImageView mario = new ImageView(image);
        Image spriteMario = new Image(getClass().getResourceAsStream("mario-sprite.png"));
        GridPane plano= new GridPane();// esto genera un plano invisible para guiarnos mas facil mente por la aplicacion

        if(pantallas.opc.isShowing()||pantallas.prin.isShowing()||pantallas.Jugador.isShowing())
            primaryStage.close();

        //espacio donde se genera el fondo de pantalla
        plano.setId("pane");
        Scene scene = new Scene(plano, 1000, 600);//genera la ventana con la cuadrigula en el.
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());//llama al codigo en css y ahi se modifica el fondo para ponerlo en la ventana

        Image logo= new Image(new FileInputStream("C:\\Users\\caudi\\Documents\\juego actualizado\\out\\production\\juego actualizado\\HojasP\\logo.png"));//selecciona el logo de la aplicacion
        ImageView logoView=new ImageView(logo);//Convierte la imagen a un tipo nodo para poder controlarla mas facilmente
        plano.setPadding(new Insets(10,10,10,10));//este es el espacio que hay entre las orillas de la ventana y el plano

        logoView.setFitHeight(80);//selecciono tamaño de la foto
        logoView.setFitWidth(500);
        plano.setAlignment(Pos.TOP_LEFT);//selecciono desde donde empieza el plano
        plano.setHgap(5);//genera un espacio entre columnas y renglones
        plano.setVgap(10);
        primaryStage.setScene(scene);

        //botones en el juego
        Button start = new Button("Iniciar");
        start.getStyleClass().add("button-yellow");
        GridPane.setConstraints(start,1,1);
        start.setPrefSize(80,50);

        Button selectPlayer = new Button("Seleccion de jugadores");
        selectPlayer.getStyleClass().add("button-blue");
        GridPane.setConstraints(selectPlayer,1,2);

        Button options = new Button("Opciones");
        options.getStyleClass().add("button-red");
        GridPane.setConstraints(options,1,3);
        options.setPrefSize(80,50);

        Button exit = new Button("Salir");
        exit.getStyleClass().add("button-green");
        GridPane.setConstraints(exit,1,4);
        exit.setPrefSize(80,50);

        Label adv = new Label("Falta ingreso de datos.");
        adv.setFont(new Font("Super Mario 256", 12));
        adv.setTextFill(Color.RED);
        adv.setVisible(false);


        //agrega los elementos al plano en las coordenadas deseadas
        plano.add(logoView,16,10);
        plano.add(start, 5, 29);
        plano.add(options, 10, 29);
        plano.add(exit, 16, 29);
        plano.add(adv,18,32);
        GridPane.setHalignment(logoView, HPos.CENTER);

        DropShadow shadow = new DropShadow();



        //Agrega una sombra cuando el curso esta encima del boton
        start.addEventHandler(MouseEvent.MOUSE_ENTERED,new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                start.setEffect(shadow);
            }
        });



        //Quita la sombra cuando ya no esta el mouse encima del boton
        start.addEventHandler(MouseEvent.MOUSE_EXITED,
                new EventHandler<MouseEvent>() {
                    @Override public void handle(MouseEvent e) {
                        start.setEffect(null);
                    }
                });

        start.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (pantallas.getPuntos()==0 || pantallas.getnJ1()=="" || pantallas.getnJ2()=="") {
                    adv.setVisible(true);
                }
                else{
                    adv.setVisible(false);
                    primaryStage.close();
                    pantallas.principal(primaryStage, spriteMario, pantallas.resultado, pantallas.string_ans
                    ,pantallas.question_index, pantallas.pointsText);
                }
            }
        });

        //Agrega una sombra cuando el curso esta encima del boton
        selectPlayer.addEventHandler(MouseEvent.MOUSE_ENTERED,new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                selectPlayer.setEffect(shadow);
            }
        });

        //Quita la sombra cuando ya no esta el mouse encima del boton
        selectPlayer.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                selectPlayer.setEffect(null);
            }
        });

        selectPlayer.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                pantallas.selecJugadores(primaryStage);
                primaryStage.close();
            }
        });

        //Agrega una sombra cuando el curso esta encima del boton
        options.addEventHandler(MouseEvent.MOUSE_ENTERED,new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                options.setEffect(shadow);
            }
        });

        //Quita la sombra cuando ya no esta el mouse encima del boton
        options.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                options.setEffect(null);
            }
        });

        options.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event){
                pantallas.opciones(primaryStage, mario);
                adv.setVisible(false);
                primaryStage.close();
            }
        });

        //Agrega una sombra cuando el curso esta encima del boton
        exit.addEventHandler(MouseEvent.MOUSE_ENTERED,new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                exit.setEffect(shadow);
            }
        });

        //Quita la sombra cuando ya no esta el mouse encima del boton
        exit.addEventHandler(MouseEvent.MOUSE_EXITED, new EventHandler<MouseEvent>() {
            @Override public void handle(MouseEvent e) {
                exit.setEffect(null);
            }
        });




        //accion que pasara al momento de presionar el primer boton
        exit.addEventHandler(MouseEvent.MOUSE_CLICKED,new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent e) {
                System.exit(1);
            }
        });

        primaryStage.show();
        primaryStage.setOnShown(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {

            }
        });

        primaryStage.setTitle("Mario Math");

        MusicPlayer cancion = new MusicPlayer("Super Mario");
        cancion.run();

    }


    public static void main(String[] args)
    {

        launch(args);
    }
}// fin del main
