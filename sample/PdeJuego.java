package sample;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.image.ImageView;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

public class PdeJuego extends Pane{

    private int puntos=0;
    private boolean suma;
    private boolean resta;
    private boolean multiplicacion;
    private boolean division;
    private int dificultad;

    private String nJ1="";
    private String nJ2="";
    public final int question_index = 0;
    private final Random random = new Random();

    public String pointsText;

    private int aux = 0;
    private int aux2 = 0;
    public String resultado[] = new String[5];



    /**====================================================*/
    /**=======ARRAYS CON LAS PREGUNTAS Y RESPUESTAS========*/
    /**====================================================*/
    public final String string_op[] = new String[20];      //suma
    public final String string_ans[] = new String[20];     //respuesta suma

    public final String string_op_sub[] = new String[20];  //resta
    public final String string_ans_sub[]  = new String[20]; //respuesta resta

    public final String string_op_mul[] = new String[20];   //multiplicacion
    public final String string_ans_mul[] = new String[20]; //respuesta multiplicacion

    public final String string_op_div[] = new String[20];  //division
    public final String string_ans_div[] = new String[20]; //respuesta division
    /**====================================================*/
    Operaciones operaciones;
    int bandera=1;

    Stage prin = new Stage();
    Stage Jugador = new Stage();
    Stage opc = new Stage();
    String winner1 = new String();
    String winner2 = new String();


    Jugador ganador=new Jugador();

    public int getPuntos(){return puntos;}
    public String getnJ1(){return nJ1;}
    public String getnJ2(){return nJ2;}

/**----------------------------- INICIA FUNCION PRINCIPAL ----------------------------------------*/
    public void principal(Stage primaryStage, Image mario, String resultado[], String string_ans[], int question_index, String pointsText){
        try{
            prin.initStyle(StageStyle.TRANSPARENT);
        }catch (IllegalStateException e){}

        /** DECLARACION DE DATOS */
        Group root = new Group();
        ImageView image = new ImageView(new Image("./HojasP/Fondo2.png"));
        root.getChildren().add(image);
        image.setFitHeight(606);
        image.setFitWidth(1000);
        Scene scene = new Scene( root );
        prin.setScene( scene );
        Canvas canvas = new Canvas( 1000, 606 );
        root.getChildren().add( canvas );

        ArrayList<String> input = new ArrayList<String>();

        GraphicsContext op = canvas.getGraphicsContext2D();
        GraphicsContext SJ1 = canvas.getGraphicsContext2D();
        GraphicsContext SJ2 = canvas.getGraphicsContext2D();

        Font theFont = Font.font( "Super Mario 256", FontWeight.BOLD, 36 );
        op.setFont( theFont );
        op.setFill( Color.ORANGERED);
        op.setStroke( Color.BLACK );
        op.setLineWidth(1);


        Sprite J1 = new Sprite();
        J1.setImage("./sample/Mario (1).png");
        J1.setPosition(425, 503);

        Sprite J2=new Sprite();
        J2.setImage("./sample/Mario (2)R.png");
        J2.setPosition(484, 510);


        ArrayList<Sprite> Cuadros = new ArrayList<Sprite>();
        Sprite Cuadro1 = new Sprite();
        Cuadro1.setImage(new Image("./HojasP/Cuadro.png"));
        Cuadro1.setPosition(10,450);

        Sprite Cuadro2 = new Sprite();
        Cuadro2.setImage(new Image("./HojasP/Cuadro.png"));
        Cuadro2.setPosition(70,450);

        Sprite Cuadro3 = new Sprite();
        Cuadro3.setImage(new Image("./HojasP/Cuadro.png"));
        Cuadro3.setPosition(880,450);

        Sprite Cuadro4 = new Sprite();
        Cuadro4.setImage(new Image("./HojasP/Cuadro.png"));
        Cuadro4.setPosition(950,450);

        Sprite Coin1 = new Sprite();
        Coin1.setImage(new Image("./HojasP/Coin.png"));
        Coin1.setPosition(209,150);

        Sprite Coin2 = new Sprite();
        Coin2.setImage(new Image("./HojasP/Coin.png"));
        Coin2.setPosition(734,150);


        Cuadros.add(Cuadro1);
        Cuadros.add(Cuadro2);
        Cuadros.add(Cuadro3);
        Cuadros.add(Cuadro4);



        LongValue lastNanoTime = new LongValue( System.nanoTime() );

        IntValue score1 = new IntValue(0);
        IntValue score2 = new IntValue(0);
        IntValue num_pregunta = new IntValue(0);
        IntValue aux = new IntValue(0);

        /**------------------------------------------------------*/
        /**Operaciones y resultados que van aparecer en pantalla**/
        /**------------------------------------------------------*/
        scene.setOnKeyPressed(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        if ( !input.contains(code) )
                            input.add( code );
                    }
                });

        scene.setOnKeyReleased(
                new EventHandler<KeyEvent>()
                {
                    public void handle(KeyEvent e)
                    {
                        String code = e.getCode().toString();
                        input.remove( code );
                    }
                });


        new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                String operacion = new String();
                int flag = 0;
                // calculate time since last update.
                double elapsedTime = (currentNanoTime - lastNanoTime.value) / 1000000000.0;
                lastNanoTime.value = currentNanoTime;


                // game logic
                J1.setVelocity(0,0);
                J2.setVelocity(0,0);

                if (input.contains("A") && J1.getPositionX()>=0) {
                    J1.addVelocity(-200, 0);
                    J1.setImage("./sample/Mario (1).png");
                }
                if (input.contains("D") && J1.getPositionX()<=425) {
                    J1.addVelocity(200, 0);
                    J1.setImage("./sample/Mario (1)R.png");
                }
                if (input.contains("W") && J1.getPositionY() >= 400)
                    J1.addVelocity(0, -200);

                if (input.contains("S") && J1.getPositionY() <= 500) {
                    if(J1.getPositionY() >= 500);
                    else J1.addVelocity(0, 200);
                }



                if (input.contains("LEFT") && J2.getPositionX()>=484) {
                    J2.addVelocity(-200, 0);
                    J2.setImage("./sample/Mario (2).png");
                }
                if (input.contains("RIGHT") && J2.getPositionX()<=959) {
                    J2.addVelocity(200, 0);
                    J2.setImage("./sample/Mario (2)R.png");
                }
                if (input.contains("UP") && J2.getPositionY() >= 400)
                    J2.addVelocity(0, -200);

                if (input.contains("DOWN") && J2.getPositionY() <= 510) {
                    if(J2.getPositionY() >= 510);
                    else J2.addVelocity(0, 200);
                }




                J1.update(elapsedTime);
                J2.update(elapsedTime);



                // collision detection
                Iterator<Sprite> cuadroIter = Cuadros.iterator();
                int respuestaCorrecta = 0;



                while ( cuadroIter.hasNext() )
                {

                    Sprite cuadro1 = cuadroIter.next();
                    Sprite cuadro2 = cuadroIter.next();
                    Sprite cuadro3 = cuadroIter.next();
                    Sprite cuadro4 = cuadroIter.next();
                    /** SUMA */
                    if (suma ==  true)
                    {
                        if (dificultad == 1)
                        {
                            respuestaCorrecta = Integer.parseInt(string_ans[aux.value]); /**Respuesta correcta final*/
                            if (aux.value == 0 || aux.value == 1 || aux.value == 3 || aux.value == 7)
                            {
                                cuadro1.num = respuestaCorrecta;
                                cuadro2.num = operaciones.respuesta(string_ans,0,aux.value+1);
                                cuadro3.num = respuestaCorrecta;
                                cuadro4.num = operaciones.respuesta(string_ans,0,aux.value+1);
                            }
                            else
                            {
                                cuadro1.num = operaciones.respuesta(string_ans,0,aux.value+1);
                                cuadro2.num = respuestaCorrecta;
                                cuadro3.num = operaciones.respuesta(string_ans,0,aux.value+1);
                                cuadro4.num = respuestaCorrecta;
                            }   operacion = string_op[num_pregunta.value];
                        }
                        if (dificultad == 2)
                        {
                            respuestaCorrecta = Integer.parseInt(string_ans[aux.value]); /**Respuesta correcta final*/
                            if (aux.value == 0 || aux.value == 1 || aux.value == 3 || aux.value == 7)
                            {
                                cuadro1.num = respuestaCorrecta;
                                cuadro2.num = operaciones.respuesta(string_ans,0,aux.value+1);
                                cuadro3.num = respuestaCorrecta;
                                cuadro4.num = operaciones.respuesta(string_ans,0,aux.value+1);
                            }
                            else
                            {
                                cuadro1.num = operaciones.respuesta(string_ans,0,aux.value+1);
                                cuadro2.num = respuestaCorrecta;
                                cuadro3.num = operaciones.respuesta(string_ans,0,aux.value+1);
                                cuadro4.num = respuestaCorrecta;
                            }   operacion = string_op[num_pregunta.value];
                        }
                        if (dificultad == 3)
                        {
                            respuestaCorrecta = Integer.parseInt(string_ans[aux.value]); /**Respuesta correcta final*/
                            if (aux.value == 0 || aux.value == 1 || aux.value == 3 || aux.value == 7)
                            {
                                cuadro1.num = respuestaCorrecta;
                                cuadro2.num = operaciones.respuesta(string_ans,0,aux.value+1);
                                cuadro3.num = respuestaCorrecta;
                                cuadro4.num = operaciones.respuesta(string_ans,0,aux.value+1);
                            }
                            else
                            {
                                cuadro1.num = operaciones.respuesta(string_ans,0,aux.value+1);
                                cuadro2.num = respuestaCorrecta;
                                cuadro3.num = operaciones.respuesta(string_ans,0,aux.value+1);
                                cuadro4.num = respuestaCorrecta;
                            }   operacion = string_op[num_pregunta.value];
                        }
                        respuestaCorrecta = Integer.parseInt(string_ans[aux.value]); /**Respuesta correcta final*/
                        resultado[0] = Integer.toString(cuadro1.num);
                        resultado[1] = Integer.toString(cuadro2.num);
                        resultado[2] = Integer.toString(cuadro3.num);
                        resultado[3] = Integer.toString(cuadro4.num);

                        System.out.println("CUADRO#1: "+cuadro1.num);
                        System.out.println("CUADRO#2: "+cuadro2.num);
                        System.out.println("CUADRO#3: "+cuadro3.num);
                        System.out.println("CUADRO#4: "+cuadro4.num);
                    }

                    /** RESTA */
                    if (resta == true)
                    {
                        if(dificultad == 1)
                        {
                            respuestaCorrecta = Integer.parseInt(string_ans_sub[aux.value]); /**Respuesta correcta final*/
                            if (aux.value == 0 || aux.value == 1 || aux.value == 3 || aux.value == 7)
                            {
                                cuadro1.num = respuestaCorrecta;
                                cuadro2.num = operaciones.respuesta(string_ans_sub,0,aux.value+1);
                                cuadro3.num = respuestaCorrecta;
                                cuadro4.num = operaciones.respuesta(string_ans_sub,0,aux.value+1);
                            }
                            else
                            {
                                cuadro1.num = operaciones.respuesta(string_ans_sub,0,aux.value+1);
                                cuadro2.num = respuestaCorrecta;
                                cuadro3.num = operaciones.respuesta(string_ans_sub,0,aux.value+1);
                                cuadro4.num = respuestaCorrecta;
                            }   operacion = string_op_sub[num_pregunta.value];
                        }
                        if(dificultad == 2)
                        {
                            respuestaCorrecta = Integer.parseInt(string_ans_sub[aux.value]); /**Respuesta correcta final*/
                            if (aux.value == 0 || aux.value == 1 || aux.value == 3 || aux.value == 7)
                            {
                                cuadro1.num = respuestaCorrecta;
                                cuadro2.num = operaciones.respuesta(string_ans_sub,0,aux.value+1);
                                cuadro3.num = respuestaCorrecta;
                                cuadro4.num = operaciones.respuesta(string_ans_sub,0,aux.value+1);
                            }
                            else
                            {
                                cuadro1.num = operaciones.respuesta(string_ans_sub,0,aux.value+1);
                                cuadro2.num = respuestaCorrecta;
                                cuadro3.num = operaciones.respuesta(string_ans_sub,0,aux.value+1);
                                cuadro4.num = respuestaCorrecta;
                            }   operacion = string_op_sub[num_pregunta.value];
                        }
                        if(dificultad == 3)
                        {
                            respuestaCorrecta = Integer.parseInt(string_ans_sub[aux.value]); /**Respuesta correcta final*/
                            if (aux.value == 0 || aux.value == 1 || aux.value == 3 || aux.value == 7)
                            {
                                cuadro1.num = respuestaCorrecta;
                                cuadro2.num = operaciones.respuesta(string_ans_sub,0,aux.value+1);
                                cuadro3.num = respuestaCorrecta;
                                cuadro4.num = operaciones.respuesta(string_ans_sub,0,aux.value+1);
                            }
                            else
                            {
                                cuadro1.num = operaciones.respuesta(string_ans_sub,0,aux.value+1);
                                cuadro2.num = respuestaCorrecta;
                                cuadro3.num = operaciones.respuesta(string_ans_sub,0,aux.value+1);
                                cuadro4.num = respuestaCorrecta;
                            }   operacion = string_op_sub[num_pregunta.value];
                        }
                        respuestaCorrecta = Integer.parseInt(string_ans_sub[aux.value]); /**Respuesta correcta final*/
                        resultado[0] = Integer.toString(cuadro1.num);
                        resultado[1] = Integer.toString(cuadro2.num);
                        resultado[2] = Integer.toString(cuadro3.num);
                        resultado[3] = Integer.toString(cuadro4.num);

                        System.out.println("CUADRO#1: "+cuadro1.num);
                        System.out.println("CUADRO#2: "+cuadro2.num);
                        System.out.println("CUADRO#3: "+cuadro3.num);
                        System.out.println("CUADRO#4: "+cuadro4.num);
                    }
                    /**===============================================================================================*/
                    /** DIVISION */
                    if (division == true)
                    {
                        if(dificultad == 1)
                        {
                            respuestaCorrecta = Integer.parseInt(string_ans_div[aux.value]); /**Respuesta correcta final*/
                            if (aux.value == 0 || aux.value == 1 || aux.value == 3 || aux.value == 7)
                            {
                                cuadro1.num = respuestaCorrecta;
                                cuadro2.num = operaciones.respuesta(string_ans_div,0,aux.value+1);
                                cuadro3.num = respuestaCorrecta;
                                cuadro4.num = operaciones.respuesta(string_ans_div,0,aux.value+1);
                            }
                            else
                            {
                                cuadro1.num = operaciones.respuesta(string_ans_div,0,aux.value+1);
                                cuadro2.num = respuestaCorrecta;
                                cuadro3.num = operaciones.respuesta(string_ans_div,0,aux.value+1);
                                cuadro4.num = respuestaCorrecta;
                            }   operacion = string_op_div[num_pregunta.value];
                        }// FIN DIFICULTAD FACIL

                        if(dificultad == 2)
                        {
                            respuestaCorrecta = Integer.parseInt(string_ans_div[aux.value]); /**Respuesta correcta final*/
                            if (aux.value == 0 || aux.value == 1 || aux.value == 3 || aux.value == 7)
                            {
                                cuadro1.num = respuestaCorrecta;
                                cuadro2.num = operaciones.respuesta(string_ans_div,0,aux.value+1);
                                cuadro3.num = respuestaCorrecta;
                                cuadro4.num = operaciones.respuesta(string_ans_div,0,aux.value+1);
                            }
                            else
                            {
                                cuadro1.num = operaciones.respuesta(string_ans_div,0,aux.value+1);
                                cuadro2.num = respuestaCorrecta;
                                cuadro3.num = operaciones.respuesta(string_ans_div,0,aux.value+1);
                                cuadro4.num = respuestaCorrecta;
                            }   operacion = string_op_div[num_pregunta.value];
                        }// FIN DIFICULTAD MEDIA

                        if(dificultad == 3)
                        {
                            respuestaCorrecta = Integer.parseInt(string_ans_div[aux.value]); /**Respuesta correcta final*/
                            if (aux.value == 0 || aux.value == 1 || aux.value == 3 || aux.value == 7)
                            {
                                cuadro1.num = respuestaCorrecta;
                                cuadro2.num = operaciones.respuesta(string_ans_div,0,aux.value+1);
                                cuadro3.num = respuestaCorrecta;
                                cuadro4.num = operaciones.respuesta(string_ans_div,0,aux.value+1);
                            }
                            else
                            {
                                cuadro1.num = operaciones.respuesta(string_ans_div,0,aux.value+1);
                                cuadro2.num = respuestaCorrecta;
                                cuadro3.num = operaciones.respuesta(string_ans_div,0,aux.value+1);
                                cuadro4.num = respuestaCorrecta;
                            }   operacion = string_op_div[num_pregunta.value];
                        }// FIN DE DIFICULTAD DIFICIL

                        respuestaCorrecta = Integer.parseInt(string_ans_div[aux.value]); /**Respuesta correcta final*/
                        resultado[0] = Integer.toString(cuadro1.num);
                        resultado[1] = Integer.toString(cuadro2.num);
                        resultado[2] = Integer.toString(cuadro3.num);
                        resultado[3] = Integer.toString(cuadro4.num);

                        System.out.println("CUADRO#1: "+cuadro1.num);
                        System.out.println("CUADRO#2: "+cuadro2.num);
                        System.out.println("CUADRO#3: "+cuadro3.num);
                        System.out.println("CUADRO#4: "+cuadro4.num);
                    }// fin de division

                    /** MULTIPLICACION */
                    /**===============================================================================================*/
                    if (multiplicacion == true)
                    {
                        if(dificultad == 1)
                        {
                            respuestaCorrecta = Integer.parseInt(string_ans_mul[aux.value]); /**Respuesta correcta final*/
                            if (aux.value == 0 || aux.value == 1 || aux.value == 3 || aux.value == 7)
                            {
                                cuadro1.num = respuestaCorrecta;
                                cuadro2.num = operaciones.respuesta(string_ans_mul,0,aux.value+1);
                                cuadro3.num = respuestaCorrecta;
                                cuadro4.num = operaciones.respuesta(string_ans_mul,0,aux.value+1);
                            }
                            else
                            {
                                cuadro1.num = operaciones.respuesta(string_ans_mul,0,aux.value+1);
                                cuadro2.num = respuestaCorrecta;
                                cuadro3.num = operaciones.respuesta(string_ans_mul,0,aux.value+1);
                                cuadro4.num = respuestaCorrecta;
                            }   operacion = string_op_mul[num_pregunta.value];
                        }// Fin de dificultad FACIL

                        if(dificultad == 2)
                        {
                            respuestaCorrecta = Integer.parseInt(string_ans_mul[aux.value]); /**Respuesta correcta final*/
                            if (aux.value == 0 || aux.value == 1 || aux.value == 3 || aux.value == 7)
                            {
                                cuadro1.num = respuestaCorrecta;
                                cuadro2.num = operaciones.respuesta(string_ans_mul,0,aux.value+1);
                                cuadro3.num = respuestaCorrecta;
                                cuadro4.num = operaciones.respuesta(string_ans_mul,0,aux.value+1);
                            }
                            else
                            {
                                cuadro1.num = operaciones.respuesta(string_ans_mul,0,aux.value+1);
                                cuadro2.num = respuestaCorrecta;
                                cuadro3.num = operaciones.respuesta(string_ans_mul,0,aux.value+1);
                                cuadro4.num = respuestaCorrecta;
                            }   operacion = string_op_mul[num_pregunta.value];
                        }// FIN DE DIFICULTAD MEDIA

                        if(dificultad == 3)
                        {
                            respuestaCorrecta = Integer.parseInt(string_ans_mul[aux.value]); /**Respuesta correcta final*/
                            if (aux.value == 0 || aux.value == 1 || aux.value == 3 || aux.value == 7)
                            {
                                cuadro1.num = respuestaCorrecta;
                                cuadro2.num = operaciones.respuesta(string_ans_mul,0,aux.value+1);
                                cuadro3.num = respuestaCorrecta;
                                cuadro4.num = operaciones.respuesta(string_ans_mul,0,aux.value+1);
                            }
                            else
                            {
                                cuadro1.num = operaciones.respuesta(string_ans_mul,0,aux.value+1);
                                cuadro2.num = respuestaCorrecta;
                                cuadro3.num = operaciones.respuesta(string_ans_mul,0,aux.value+1);
                                cuadro4.num = respuestaCorrecta;
                            }   operacion = string_op_mul[num_pregunta.value];
                        }// FIN DE DIFICULTAD DIFICIL

                        resultado[0] = Integer.toString(cuadro1.num);
                        resultado[1] = Integer.toString(cuadro2.num);
                        resultado[2] = Integer.toString(cuadro3.num);
                        resultado[3] = Integer.toString(cuadro4.num);

                        System.out.println("CUADRO#1: "+cuadro1.num);
                        System.out.println("CUADRO#2: "+cuadro2.num);
                        System.out.println("CUADRO#3: "+cuadro3.num);
                        System.out.println("CUADRO#4: "+cuadro4.num);

                    }// fin de multiplicacion


                    if ( J1.intersects(cuadro1))
                    {
                        System.out.println("RESPUESTA CORRECTA: "+ respuestaCorrecta+"\n");
                        if(cuadro1.num == respuestaCorrecta)
                        {
                            score1.value++;
                            num_pregunta.value++;
                            aux.value++;
                            System.out.println("score1: "+score1.value);

                            J1.setPosition(425, 500);
                            J2.setPosition(484, 510);
                            //cuadro1.num = operaciones.respuesta(string_ans,num_pregunta.value,aux.value);
                            //resultado[0] = Integer.toString(cuadro1.num);
                            System.out.println("CUADRO#1: "+cuadro1.num);
                            /**---------------------MONITOREO------------------------*/


                            System.out.println("numero de pregunta = "+num_pregunta.value);
                            System.out.println("aux = "+aux.value);
                        }
                        else
                        {
                            score1.value--;
                            J1.setPosition(425, 500);
                            //resultado[0] = Integer.toString(cuadro1.num);
                            System.out.println("CUADRO#1: "+cuadro1.num);

                            /**---------------------MONITOREO------------------------*/
                            num_pregunta.value++;
                            aux.value++;
                            System.out.println("numero de pregunta = "+num_pregunta.value);
                            System.out.println("aux = "+aux.value);
                        }
                    }

                    if(J1.intersects(cuadro2))
                    {
                        System.out.println("RESPUESTA CORRECTA: "+ respuestaCorrecta+"\n");
                        if(cuadro2.num == respuestaCorrecta)
                        {
                            score1.value++;
                            System.out.println("Respuesta correcta = " + respuestaCorrecta);
                            J1.setPosition(425, 500);
                            J2.setPosition(484, 510);
                            System.out.println("CUADRO#2: "+cuadro2.num);

                            /**---------------------MONITOREO------------------------*/
                            num_pregunta.value++;
                            aux.value++;
                            System.out.println("numero de pregunta = "+num_pregunta.value);
                            System.out.println("aux = "+aux.value);
                        }
                        else
                        {
                            score1.value--;
                            J1.setPosition(425, 500);
                            System.out.println("CUADRO#2: "+cuadro2.num);

                            /**---------------------MONITOREO------------------------*/
                            num_pregunta.value++;
                            aux.value++;
                            System.out.println("numero de pregunta = "+num_pregunta.value);
                            System.out.println("aux = "+aux.value);
                        }

                    }
                    if(J2.intersects(cuadro3)){
                        System.out.println("RESPUESTA CORRECTA: "+ respuestaCorrecta+"\n");
                        if(cuadro3.num == respuestaCorrecta)
                        {
                            score2.value++;
                            J2.setPosition(484, 510);
                            System.out.println("CUADRO#3: "+cuadro3.num);

                            /**---------------------MONITOREO------------------------*/
                            num_pregunta.value++;
                            aux.value++;
                            System.out.println("numero de pregunta = "+num_pregunta.value);
                            System.out.println("aux = "+aux.value);
                        }
                        else {
                                score2.value--;

                                J2.setPosition(484, 510);
                                cuadro3.num = operaciones.respuesta(string_ans,num_pregunta.value,score2.value+2);
                                System.out.println("CUADRO#3: "+cuadro3.num);

                            /**---------------------MONITOREO------------------------*/
                            num_pregunta.value++;
                            aux.value++;
                            System.out.println("numero de pregunta = "+num_pregunta.value);
                            System.out.println("aux = "+aux.value);
                        }

                    }
                    if(J2.intersects(cuadro4))
                    {
                        System.out.println("RESPUESTA CORRECTA: "+ respuestaCorrecta+"\n");
                        if(cuadro4.num == respuestaCorrecta)
                        {
                            score2.value++;
                            J2.setPosition(484, 510);
                            System.out.println("CUADRO#4: "+cuadro4.num);

                            /**---------------------MONITOREO------------------------*/
                            num_pregunta.value++;
                            aux.value++;
                            System.out.println("numero de pregunta = "+num_pregunta.value);
                            System.out.println("aux = "+aux.value);
                        }
                        else {
                            score2.value--;

                            J2.setPosition(484, 510);
                            System.out.println("CUADRO#4: "+cuadro4.num);

                            /**---------------------MONITOREO------------------------*/
                            num_pregunta.value++;
                            aux.value++;
                            System.out.println("numero de pregunta = "+num_pregunta.value);
                            System.out.println("aux = "+aux.value);
                        }

                    }

                    if (score1.value==puntos || score2.value==puntos){

                        if(score1.value==puntos)
                        {
                            ganador = new Jugador(nJ1,elapsedTime,puntos,dificultad);
                            SJ1.fillText(nJ1,415,300);
                            SJ1.strokeText(nJ1,415,300);
                        }


                        else if(score2.value==puntos)
                        {
                            ganador=new Jugador(nJ2,elapsedTime,puntos,dificultad);
                            SJ2.fillText(nJ2,415,300);
                            SJ2.strokeText(nJ2,415,300);
                        }


                        ganador.escritura(ganador);


                        /** Se hace un reset al juego*/
                        suma=false;
                        resta=false;
                        multiplicacion=false;
                        division=false;

                        nJ1="";
                        nJ2="";

                        score1.value=0;
                        score2.value=0;

                        prin.close();
                        primaryStage.show();

                    }
                }

                // render

                op.clearRect(0, 0, 1000,30);
                SJ1.clearRect(0, 30, 500,576);
                SJ2.clearRect(500, 30, 500,576);

                Cuadro1.render( SJ1 );
                Cuadro2.render( SJ1 );

                Cuadro3.render( SJ2 );
                Cuadro4.render( SJ2 );

                J1.render( SJ1 );
                J2.render( SJ2 );

                Coin1.render( SJ1 );
                Coin2.render( SJ2 );

                op.fillText( operacion, 415, 300 );
                op.strokeText( operacion, 415, 300 );

                /**-------------------------------------------*/
                /**Impresion de la operaciones en la pantallas*/
                /**-------------------------------------------*/

                /**Respuesta #1*/
                SJ1.fillText(resultado[0],10,430);  //posibles respuestas en los bloques (imagen)
                SJ1.strokeText( resultado[0], 10, 430 );
                /**Respuesta #2 ERRONEA*/
                SJ1.fillText(resultado[1], 70, 430);
                SJ1.strokeText( resultado[1], 70, 430 );
                /**Respuesta #3*/
                SJ2.fillText(resultado[2],880,435);  //posibles respuestas en los bloques (imagen)
                SJ2.strokeText(resultado[2], 880, 435 );
                /**Respuesta #4*/
                SJ2.fillText(resultado[3],940,435);  //posibles respuestas en los bloques (imagen)
                SJ2.strokeText(resultado[3], 940, 435 );

                if(nJ1.length()<6) {
                    SJ1.fillText(nJ1, 199, 150);
                    SJ1.strokeText(nJ1, 199, 150);
                }
                else if(nJ1.length()<10){
                    SJ1.fillText(nJ1, 159, 150);
                    SJ1.strokeText(nJ1, 159, 150);
                }
                else{
                    SJ1.fillText(nJ1, 89, 150);
                    SJ1.strokeText(nJ1, 89, 150);
                }
                SJ1.fillText(Integer.toString(score1.value),250,200); /**Relleno del caracter*/
                SJ1.strokeText(Integer.toString(score1.value),250,200); /**Contorno del caracter*/

                //SJ2.fillText(Integer.toString(score2.value),910,435);  //posibles respuestas en los bloques (imagen)
                //SJ2.strokeText(Integer.toString(score2.value), 910, 435 );

                if(nJ2.length()<6) {
                    SJ2.fillText(nJ2, 724,150);
                    SJ2.strokeText(nJ2, 724,150);
                }
                else if(nJ2.length()<10){
                    SJ2.fillText(nJ2, 694,150);
                    SJ2.strokeText(nJ2, 694,150);
                }
                else{
                    SJ2.fillText(nJ2, 614,150);
                    SJ2.strokeText(nJ2, 614,150);
                }
                SJ2.fillText(Integer.toString(score2.value),780,190);
                SJ2.strokeText(Integer.toString(score2.value),780,190);

            }   /** end of handdle method */
        }.start(); /**FIN ANIMATION TIMER*/

        prin.show();
        /*salida.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                prin.close();
                primaryStage.show();
            }
        });*/

    }

    public void selecJugadores(Stage primaryStage){
        Jugador.initStyle(StageStyle.TRANSPARENT);

        Jugador ganadores[] = new Jugador[10];

        GridPane plano= new GridPane();
        plano.setId("Juego");
        Scene scene = new Scene(plano, 1000, 606);
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

        Button salida = new Button("salida");

        plano.setAlignment(Pos.TOP_LEFT);//selecciono desde donde empieza el plano
        plano.setHgap(5);//genera un espacio entre columnas y renglones
        plano.setVgap(10);

        Jugador.setScene(scene);


        plano.setPadding(new Insets(10,10,10,10));

        plano.add(salida,5,5);

        Jugador.show();

        salida.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                ganador.lectura();
                Jugador.close();
            }
        });
    }

    public void opciones(Stage primaryStage, ImageView mario){
        /**==================================================*/
        /**==================================================*/
        /**==================================================*/
        //question_index = 0;
        /**==================================================*/
        /**==================================================*/
        /**==================================================*/

        //Arregla este problema
        if(bandera==1){
            opc.initStyle(StageStyle.TRANSPARENT);
            bandera=0;
        }
        //ERROR: Exception in thread "JavaFX Application Thread" java.lang.IllegalStateException: Cannot set style once stage has been set visible

        GridPane plano= new GridPane();

        //Son las cajas para ver que Operaciones elijio elijio
        CheckBox VSuma = new CheckBox("Suma");
        VSuma.setSelected(false);

        CheckBox VResta = new CheckBox("Resta");
        VResta.setSelected(false);

        CheckBox VMultiplicacion = new CheckBox("Multiplicación");
        VMultiplicacion.setSelected(false);

        CheckBox VDivision = new CheckBox("Division");
        VDivision.setSelected(false);

        CheckBox VFacil = new CheckBox("Facil");
        VFacil.setSelected(false);

        CheckBox VNormal = new CheckBox("Normal");
        VNormal.setSelected(false);

        CheckBox VDificil = new CheckBox("Dificl");
        VDificil.setSelected(false);

        Button salida = new Button("salida");

        Label Operaciones = new Label("Operaciones:");
        Label Nombres = new Label("Nombre de los Jugadores:");
        Label J1Nom= new Label("Jugador 1:");
        Label J2Nom= new Label("Jugador 2:");
        Label LPuntos =new Label("Puntos a ganar:");
        Label LDificultad = new Label("Dificultad");

        TextField inJugador1 = new TextField();
        inJugador1.setFont(new Font("Super Mario 256",12));

        TextField inJugador2 =new TextField();
        inJugador2.setFont(new Font("Super Mario 256",12));

        final ChoiceBox<Integer> Puntos = new ChoiceBox<>(FXCollections.observableArrayList(3, 5, 7));

        Scene scene = new Scene(plano, 1000, 606);

        plano.setId("Juego");
        Operaciones.setId("Operaciones");
        scene.getStylesheets().addAll(this.getClass().getResource("style.css").toExternalForm());

        plano.setAlignment(Pos.TOP_CENTER);//selecciono desde donde empieza el plano
        plano.setHgap(5);//genera un espacio entre columnas y renglones
        plano.setVgap(10);

        Operaciones.setFont(new Font("Super Mario 256", 23));
        Operaciones.setTextFill(Color.DARKBLUE);

        VSuma.setFont(new Font("Super Mario 256", 15));
        VSuma.setTextFill(Color.PINK);

        VResta.setFont(new Font("Super Mario 256", 15));
        VResta.setTextFill(Color.RED);

        VMultiplicacion.setFont(new Font("Super Mario 256", 15));
        VMultiplicacion.setTextFill(Color.DARKGREEN);

        VDivision.setFont(new Font("Super Mario 256", 15));
        VDivision.setTextFill(Color.YELLOW);


        Nombres.setFont(new Font("Super Mario 256", 23));
        Nombres.setTextFill(Color.DARKGREEN);

        J1Nom.setFont(new Font("Super Mario 256", 15));
        J1Nom.setTextFill(Color.RED);

        J2Nom.setFont(new Font("Super Mario 256", 15));
        J2Nom.setTextFill(Color.BLUE);

        LPuntos.setFont(new Font("Super Mario 256", 23));
        LPuntos.setTextFill(Color.PURPLE);

        LDificultad.setFont(new Font("Super Mario 256",23));
        LDificultad.setTextFill(Color.CHOCOLATE);

        VFacil.setFont(new Font("Super Mario 256",15));
        VFacil.setTextFill(Color.GREENYELLOW);

        VNormal.setFont(new Font("Super Mario 256",15));
        VNormal.setTextFill(Color.YELLOW);

        VDificil.setFont(new Font("Super Mario 256",15));
        VDificil.setTextFill(Color.DARKRED);

        mario.setFitHeight(200);//selecciono tamaño de la foto
        mario.setFitWidth(100);

        opc.setScene(scene);
        plano.setPadding(new Insets(10,10,10,10));
        salida.getStyleClass().add("button-red");

        plano.add(salida,6,0);
        plano.add(Operaciones,0,3);
        plano.add(VSuma,0,4);
        plano.add(VResta,0,5);
        plano.add(VMultiplicacion,0,6);
        plano.add(VDivision,0,7);
        plano.add(Nombres,13,3);
        plano.add(J1Nom,13,4);
        plano.add(inJugador1,13,5);
        plano.add(J2Nom,13,6);
        plano.add(inJugador2,13,7);
        plano.add(LPuntos,0,9);
        plano.add(Puntos,0,10);
        plano.add(LDificultad,13,9);
        plano.add(VFacil,13,10);
        plano.add(VNormal,13,11);
        plano.add(VDificil,13,12);
        plano.add(mario,6,13);

        GridPane.setHalignment(mario, HPos.RIGHT);
        GridPane.setHalignment(salida, HPos.CENTER);
        GridPane.setHalignment(Puntos, HPos.CENTER);

        opc.show();

        salida.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if((!VSuma.isSelected() && !VResta.isSelected() && !VMultiplicacion.isSelected() && !VDivision.isSelected()) || Puntos.getValue()==null || (!VFacil.isSelected() && !VNormal.isSelected() && !VDificil.isSelected()) || inJugador1.getCharacters()=="" || inJugador2.getCharacters()=="" ){
                    System.out.println("Ingresa datos prro!!");
                    JOptionPane.showMessageDialog(null, "Completa los datos");
                }
                // Las operaciones que el usuario va resolver
                else {
                    if (VSuma.isSelected()) suma = true;
                    if (VResta.isSelected()) resta = true;
                    if (VMultiplicacion.isSelected()) multiplicacion = true;
                    if (VDivision.isSelected()) division = true;

                    puntos = Puntos.getValue();

                    //Dificultad que el usuario va ingresar
                    if(VFacil.isSelected())dificultad=1;
                    else if(VNormal.isSelected())dificultad=2;
                    else if(VDificil.isSelected())dificultad=3;

                    nJ1=inJugador1.getCharacters().toString();
                    nJ2=inJugador2.getCharacters().toString();

                    opc.close();
                    primaryStage.show();
                }
                /**======================= OPERACIONES CON SUMA =======================*/
                if(suma == true)
                {
                    /**DIFICULTAD FACIL*/
                    if(dificultad == 1)
                    {
                        try {
                            System.out.println("SUMA FACIL");
                            operaciones.operations_easy(string_op);             /**MODIFICA EL ARREGLO CON LAS OPERACIONES*/
                            operaciones.operations_answers_easy(string_ans);    /**MODIFICA EL ARREGLO CON LAS RESPUESTAS*/
                            pointsText = string_op[question_index]; /** Operacion a resolver*/
                            /**======================= RESULTADOS =======================*/
                            resultado[0] = Integer.toString(operaciones.respuesta(string_ans,question_index,0));
                            resultado[1] = Integer.toString(operaciones.respuesta(string_ans,question_index,4));
                            resultado[2] = Integer.toString(operaciones.respuesta(string_ans,question_index,0));
                            resultado[3] = Integer.toString(operaciones.respuesta(string_ans,question_index,4));

                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    /**DIFICULTAD MEDIA*/
                    if(dificultad == 2)
                    {
                        try {
                            System.out.println("SUMA MEDIA");
                            operaciones.operations_medium(string_op);           /**MODIFICA EL ARREGLO CON LAS OPERACIONES*/
                            operaciones.operations_answers_medium(string_ans);  /**MODIFICA EL ARREGLO CON LAS RESPUESTAS*/
                            pointsText = string_op[question_index];
                            /**======================= RESULTADOS =======================*/
                            resultado[0] = Integer.toString(operaciones.respuesta(string_ans,question_index,0));
                            resultado[1] = Integer.toString(operaciones.respuesta(string_ans,question_index,4));
                            resultado[2] = Integer.toString(operaciones.respuesta(string_ans,question_index,0));
                            resultado[3] = Integer.toString(operaciones.respuesta(string_ans,question_index,4));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    /**DIFICULTAD DIFICIL*/
                    if(dificultad == 3)
                    {
                        try {
                            System.out.println("SUMA DIFICIL");
                            operaciones.operations_hard(string_op);
                            operaciones.operations_answers_hard(string_ans);
                            pointsText = string_op[question_index];
                            /**======================= RESULTADOS =======================*/
                            resultado[0] = Integer.toString(operaciones.respuesta(string_ans,question_index,0));
                            resultado[1] = Integer.toString(operaciones.respuesta(string_ans,question_index,4));
                            resultado[2] = Integer.toString(operaciones.respuesta(string_ans,question_index,0));
                            resultado[3] = Integer.toString(operaciones.respuesta(string_ans,question_index,4));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }

                }
                /**=======================OPERACIONES CON RESTA=======================*/
                if(resta == true)
                {
                    /**DIFICULTAD FACIL*/
                    if(dificultad == 1)
                    {
                        try {
                            System.out.println("RESTA FACIL");
                            operaciones.operations_sub_easy(string_op_sub);
                            operaciones.operations_answers_sub_easy(string_ans_sub);
                            pointsText = string_op_sub[question_index];
                            /**======================= RESULTADOS =======================*/
                            resultado[0] = Integer.toString(operaciones.respuesta(string_ans_sub,question_index,0));
                            resultado[1] = Integer.toString(operaciones.respuesta(string_ans_sub,question_index,4));
                            resultado[2] = Integer.toString(operaciones.respuesta(string_ans_sub,question_index,0));
                            resultado[3] = Integer.toString(operaciones.respuesta(string_ans_sub,question_index,4));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    /**DIFICULTAD MEDIA*/
                    if(dificultad == 2)
                    {
                        try {
                            System.out.println("RESTA MEDIA");
                            operaciones.operations_sub_medium(string_op_sub);
                            operaciones.operations_answers_sub_medium(string_ans_sub);
                            pointsText = string_op_sub[question_index];
                            /**======================= RESULTADOS =======================*/
                            resultado[0] = Integer.toString(operaciones.respuesta(string_ans_sub,question_index,0));
                            resultado[1] = Integer.toString(operaciones.respuesta(string_ans_sub,question_index,4));
                            resultado[2] = Integer.toString(operaciones.respuesta(string_ans_sub,question_index,0));
                            resultado[3] = Integer.toString(operaciones.respuesta(string_ans_sub,question_index,4));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    /**DIFICULTAD DIFICIL*/
                    if(dificultad == 3)
                    {
                        try {
                            System.out.println("RESTA DIFICIL");
                            operaciones.operations_sub_hard(string_op_sub);
                            operaciones.operations_answers_sub_hard(string_ans_sub);
                            pointsText = string_op_sub[question_index];
                            /**======================= RESULTADOS =======================*/
                            resultado[0] = Integer.toString(operaciones.respuesta(string_ans_sub,question_index,0));
                            resultado[1] = Integer.toString(operaciones.respuesta(string_ans_sub,question_index,4));
                            resultado[2] = Integer.toString(operaciones.respuesta(string_ans_sub,question_index,0));
                            resultado[3] = Integer.toString(operaciones.respuesta(string_ans_sub,question_index,4));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
                /**======================= OPERACIONES CON MULTIPLICACION =======================*/
                if(multiplicacion == true)
                {
                    /**DIFICULTAD FACIL*/
                    if(dificultad == 1)
                    {
                        try {
                            System.out.println("MULTIPLICACION FACIL");
                            operaciones.operations_mult_easy(string_op_mul);
                            operaciones.operations_answers_mult_easy(string_ans_mul);
                            pointsText = string_op_mul[question_index];
                            /**======================= RESULTADOS =======================*/
                            resultado[0] = Integer.toString(operaciones.respuesta(string_ans_mul,question_index,0));
                            resultado[1] = Integer.toString(operaciones.respuesta(string_ans_mul,question_index,4));
                            resultado[2] = Integer.toString(operaciones.respuesta(string_ans_mul,question_index,0));
                            resultado[3] = Integer.toString(operaciones.respuesta(string_ans_mul,question_index,4));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    /**DIFICULTAD MEDIA*/
                    if(dificultad == 2)
                    {
                        try {
                            System.out.println("MULTIPLICACION MEDIA");
                            operaciones.operations_mult_medium(string_op_mul);
                            operaciones.operations_answers_mult_medium(string_ans_mul);
                            pointsText = string_op_mul[question_index];
                            /**======================= RESULTADOS =======================*/
                            resultado[0] = Integer.toString(operaciones.respuesta(string_ans_mul,question_index,0));
                            resultado[1] = Integer.toString(operaciones.respuesta(string_ans_mul,question_index,4));
                            resultado[2] = Integer.toString(operaciones.respuesta(string_ans_mul,question_index,0));
                            resultado[3] = Integer.toString(operaciones.respuesta(string_ans_mul,question_index,4));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    /**DIFICULTAD DIFICIL*/
                    if(dificultad == 3)
                    {
                        try {
                            System.out.println("MULTIPLICACION DIFICIL");
                            operaciones.operations_mult_hard(string_op_mul);
                            operaciones.operations_answers_mult_hard(string_ans_mul);
                            pointsText = string_op_mul[question_index];
                            /**======================= RESULTADOS =======================*/
                            resultado[0] = Integer.toString(operaciones.respuesta(string_ans_mul,question_index,0));
                            resultado[1] = Integer.toString(operaciones.respuesta(string_ans_mul,question_index,4));
                            resultado[2] = Integer.toString(operaciones.respuesta(string_ans_mul,question_index,0));
                            resultado[3] = Integer.toString(operaciones.respuesta(string_ans_mul,question_index,4));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
                /**======================= OPERACIONES CON DIVISION =======================*/
                if(division == true)
                {
                    /**DIFICULTAD FACIL*/
                    if(dificultad == 1)
                    {
                        try {
                            System.out.println("DIVISIOIN FACIL");
                            operaciones.operations_div_easy(string_op_div);
                            operaciones.operations_answers_div_easy(string_ans_div);
                            pointsText = string_op_div[question_index];
                            /**======================= RESULTADOS =======================*/
                            resultado[0] = Integer.toString(operaciones.respuesta(string_ans_div,question_index,0));
                            resultado[1] = Integer.toString(operaciones.respuesta(string_ans_div,question_index,4));
                            resultado[2] = Integer.toString(operaciones.respuesta(string_ans_div,question_index,0));
                            resultado[3] = Integer.toString(operaciones.respuesta(string_ans_div,question_index,4));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    /**DIFICULTAD MEDIA*/
                    if(dificultad == 2)
                    {
                        try {
                            System.out.println("DIVISIOIN MEDIA");
                            operaciones.operations_div_medium(string_op_div);
                            operaciones.operations_answers_div_medium(string_ans_div);
                            pointsText = string_op_div[question_index];
                            /**======================= RESULTADOS =======================*/
                            resultado[0] = Integer.toString(operaciones.respuesta(string_ans_div,question_index,0));
                            resultado[1] = Integer.toString(operaciones.respuesta(string_ans_div,question_index,4));
                            resultado[2] = Integer.toString(operaciones.respuesta(string_ans_div,question_index,0));
                            resultado[3] = Integer.toString(operaciones.respuesta(string_ans_div,question_index,4));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                    /**DIFICULTAD DIFICIL*/
                    if(dificultad == 3)
                    {
                        try {
                            System.out.println("DIVISIOIN DIFICIL");
                            operaciones.operations_div_hard(string_op_div);
                            operaciones.operations_answers_div_hard(string_ans_div);
                            pointsText = string_op_div[question_index];
                            /**======================= RESULTADOS =======================*/
                            resultado[0] = Integer.toString(operaciones.respuesta(string_ans_div,question_index,0));
                            resultado[1] = Integer.toString(operaciones.respuesta(string_ans_div,question_index,4));
                            resultado[2] = Integer.toString(operaciones.respuesta(string_ans_div,question_index,0));
                            resultado[3] = Integer.toString(operaciones.respuesta(string_ans_div,question_index,4));
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        });



        VFacil.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(VNormal.isSelected()){
                    VNormal.setSelected(false);
                }
                else if(VDificil.isSelected()){
                    VDificil.setSelected(false);
                }
                /**Limpia la cadena para poder ingresar
                 * nuevo valores dependiendo si cambia de dificultad*/
                operaciones = new Operaciones();
                operaciones.clearString(string_op);
                operaciones.clearString(string_op_sub);
                operaciones.clearString(string_op_mul);
                operaciones.clearString(string_op_div);

                operaciones.clearString(string_ans);
                operaciones.clearString(string_ans_sub);
                operaciones.clearString(string_ans_mul);
                operaciones.clearString(string_ans_div);
                try {
                    operaciones.operations_easy(string_op);
                    operaciones.operations_answers_easy(string_ans);

                    operaciones.operations_sub_easy(string_op_sub);
                    operaciones.operations_answers_sub_easy(string_ans_sub);

                    operaciones.operations_mult_easy(string_op_mul);
                    operaciones.operations_answers_mult_easy(string_ans_mul);

                    operaciones.operations_div_easy(string_op_div);
                    operaciones.operations_answers_div_easy(string_ans_div);

                } catch (FileNotFoundException e) {
                    System.out.println("Algo salio mal"+e.getCause()+e.getMessage());
                    e.printStackTrace();
                }
                System.out.println("Facil: "+string_op[0]+"="+string_ans[0]);
                System.out.println("Facil: "+string_op_sub[0]+"="+string_ans_sub[0]);
                System.out.println("Facil: "+string_op_mul[0]+"="+string_ans_mul[0]);
                System.out.println("Facil: "+string_op_div[0]+"="+string_ans_div[0]);

            }
        });

        VNormal.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(VFacil.isSelected()){
                    VFacil.setSelected(false);
                }
                else if(VDificil.isSelected()){
                    VDificil.setSelected(false);
                }
                operaciones = new Operaciones();
                operaciones.clearString(string_op);
                operaciones.clearString(string_op_sub);
                operaciones.clearString(string_op_mul);
                operaciones.clearString(string_op_div);

                operaciones.clearString(string_ans);
                operaciones.clearString(string_ans_sub);
                operaciones.clearString(string_ans_mul);
                operaciones.clearString(string_ans_div);
                try {
                    operaciones.operations_medium(string_op);
                    operaciones.operations_answers_medium(string_ans);

                    operaciones.operations_sub_medium(string_op_sub);
                    operaciones.operations_answers_sub_medium(string_ans_sub);

                    operaciones.operations_mult_medium(string_op_mul);
                    operaciones.operations_answers_mult_medium(string_ans_mul);

                    operaciones.operations_div_medium(string_op_div);
                    operaciones.operations_answers_div_medium(string_ans_div);

                } catch (FileNotFoundException e) {
                    System.out.println("Algo salio mal"+e.getCause()+e.getMessage());
                    e.printStackTrace();
                }
                System.out.println("Normal: "+string_op[0]+"="+string_ans[0]);
                System.out.println("Normal: "+string_op_sub[0]+"="+string_ans_sub[0]);
                System.out.println("Normal: "+string_op_mul[0]+"="+string_ans_mul[0]);
                System.out.println("Normal: "+string_op_div[0]+"="+string_ans_div[0]);
            }
        });

        VDificil.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(VFacil.isSelected()){
                    VFacil.setSelected(false);
                }
                else if(VNormal.isSelected()){
                    VNormal.setSelected(false);
                }
                operaciones = new Operaciones();
                operaciones.clearString(string_op);
                operaciones.clearString(string_op_sub);
                operaciones.clearString(string_op_mul);
                operaciones.clearString(string_op_div);

                operaciones.clearString(string_ans);
                operaciones.clearString(string_ans_sub);
                operaciones.clearString(string_ans_mul);
                operaciones.clearString(string_ans_div);
                try {
                    operaciones.operations_hard(string_op);
                    operaciones.operations_answers_hard(string_ans);

                    operaciones.operations_sub_hard(string_op_sub);
                    operaciones.operations_answers_sub_hard(string_ans_sub);

                    operaciones.operations_mult_hard(string_op_mul);
                    operaciones.operations_answers_mult_hard(string_ans_mul);

                    operaciones.operations_div_hard(string_op_div);
                    operaciones.operations_answers_div_hard(string_ans_div);

                } catch (FileNotFoundException e) {
                    System.out.println("Algo salio mal"+e.getCause()+e.getMessage());
                    e.printStackTrace();
                }
                System.out.println("Dificil: "+string_op[0]+"="+string_ans[0]);
                System.out.println("Dificil: "+string_op_sub[0]+"="+string_ans_sub[0]);
                System.out.println("Dificil: "+string_op_mul[0]+"="+string_ans_mul[0]);
                System.out.println("Dificil: "+string_op_div[0]+"="+string_ans_div[0]);

                System.out.println(string_op[0]+"="+string_ans[0]);
            }
        });

    }


}