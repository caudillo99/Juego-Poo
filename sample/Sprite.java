package sample;

import javafx.geometry.Rectangle2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

import java.util.Random;

public class Sprite {

    private Operaciones operaciones;
    private Random random;
    private Image image;
    private double positionX;
    private double positionY;
    private double velocityX;
    private double velocityY;
    private double width;
    private double height;
    private int valor;
    public int num;

    public Sprite()
    {
        positionX = 0;
        positionY = 0;
        velocityX = 0;
        velocityY = 0;
        valor=0;
    }

    public void setImage(Image i)
    {
        image = i;
        width = image.getWidth();
        height = image.getHeight();
    }

    public double getPositionY(){return positionY;}

    public double getPositionX(){return positionX;}

    public void setImage(String filename)
    {
        Image i = new Image(filename);
        setImage(i);
    }

    public void setPosition(double x, double y)
    {
        positionX = x;
        positionY = y;
    }

    public void setVelocity(double x, double y)
    {
        velocityX = x;
        velocityY = y;
    }

    public void addVelocity(double x, double y)
    {
        velocityX += x;
        velocityY += y;
    }

    public void update(double time)
    {
        positionX += velocityX * time;
        positionY += velocityY * time;
    }

    public void render(GraphicsContext gc)
    {
        gc.drawImage( image, positionX, positionY );
    }

    public Rectangle2D getBoundary()
    {
        return new Rectangle2D(positionX,positionY,width,height);
    }

    public boolean intersects(Sprite s)
    {
        return s.getBoundary().intersects( this.getBoundary() );
    }

    public int respuesta(String answer[], int question_index, int score_value)
    {
        if (score_value <2 ) return Integer.parseInt(answer[question_index+3]);
        else return Integer.parseInt(answer[question_index]);
    }

    public String toString()
    {
        return " Position: [" + positionX + "," + positionY + "]"
                + " Velocity: [" + velocityX + "," + velocityY + "]";
    }
}
