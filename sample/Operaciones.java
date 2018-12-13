package sample;

import javafx.animation.AnimationTimer;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Operaciones
{
    Random random;

    public void operations_easy(String string_op[]) throws FileNotFoundException
    {
        File file = new File("add_op_easy.txt");
        Scanner sc = new Scanner(file);
        int i = 0;
        while (sc.hasNextLine())
        {
            string_op[i] = sc.nextLine();
            i++;
        }
    }

    public void operations_answers_easy(String string_ans[]) throws FileNotFoundException
    {
        File file = new File("add_ans_easy.txt");
        Scanner sc = new Scanner(file);
            int i = 0;
            while (sc.hasNextLine())
            {
                string_ans[i] = sc.nextLine();
                i++;
            }
        }

        public void operations_medium(String string_op[]) throws FileNotFoundException
        {
            File file = new File("add_op_med.txt");
            Scanner sc = new Scanner(file);

            int i = 0;

            while (sc.hasNextLine())
            {
                string_op[i] = sc.nextLine();
                i++;
            }
        }

        public void operations_answers_medium(String string_ans[]) throws FileNotFoundException
        {
            File file = new File("add_ans_med.txt");
            Scanner sc = new Scanner(file);

            int i = 0;

            while (sc.hasNextLine())
            {
                string_ans[i] = sc.nextLine();
                i++;
            }
        }

        public void operations_hard(String string_op[]) throws FileNotFoundException
        {
            File file = new File("add_op_hard.txt");
            Scanner sc = new Scanner(file);

            int i = 0;

            while (sc.hasNextLine())
            {
                string_op[i] = sc.nextLine();
                i++;
            }
        }

        public void operations_answers_hard(String string_ans[]) throws FileNotFoundException
        {
            File file = new File("add_ans_hard.txt");
            Scanner sc = new Scanner(file);

            int i = 0;

            while (sc.hasNextLine())
            {
                string_ans[i] = sc.nextLine();
                i++;
            }
        }
    /**------------------------------------------------------------------------------------------------*/
    /**----------------------------------- OPERACIONES CON RESTA --------------------------------------*/
    /**------------------------------------------------------------------------------------------------*/

    public void operations_sub_easy(String string_op[]) throws FileNotFoundException
    {
        File file = new File("sub_op_easy.txt");
        Scanner sc = new Scanner(file);
        int i = 0;
        while (sc.hasNextLine())
        {
            string_op[i] = sc.nextLine();
            i++;
        }
    }

    public void operations_answers_sub_easy(String string_ans[]) throws FileNotFoundException
    {
        File file = new File("sub_ans_easy.txt");
        Scanner sc = new Scanner(file);
        int i = 0;
        while (sc.hasNextLine())
        {
            string_ans[i] = sc.nextLine();
            i++;
        }
    }

    public void operations_sub_medium(String string_op[]) throws FileNotFoundException
    {
        File file = new File("sub_op_med.txt");
        Scanner sc = new Scanner(file);

        int i = 0;

        while (sc.hasNextLine())
        {
            string_op[i] = sc.nextLine();
            i++;
        }
    }

    public void operations_answers_sub_medium(String string_ans[]) throws FileNotFoundException
    {
        File file = new File("sub_ans_med.txt");
        Scanner sc = new Scanner(file);

        int i = 0;

        while (sc.hasNextLine())
        {
            string_ans[i] = sc.nextLine();
            i++;
        }
    }

    public void operations_sub_hard(String string_op[]) throws FileNotFoundException
    {
        File file = new File("sub_op_hard.txt");
        Scanner sc = new Scanner(file);

        int i = 0;

        while (sc.hasNextLine())
        {
            string_op[i] = sc.nextLine();
            i++;
        }
    }

    public void operations_answers_sub_hard(String string_ans[]) throws FileNotFoundException
    {
        File file = new File("sub_ans_hard.txt");
        Scanner sc = new Scanner(file);

        int i = 0;

        while (sc.hasNextLine())
        {
            string_ans[i] = sc.nextLine();
            i++;
        }
    }


    /**------------------------------------------------------------------------------------------------*/
    /**----------------------------- OPERACIONES CON MULTIPLICACION -----------------------------------*/
    /**------------------------------------------------------------------------------------------------*/

    public void operations_mult_easy(String string_op[]) throws FileNotFoundException
    {
        File file = new File("mul_op_easy.txt");
        Scanner sc = new Scanner(file);
        int i = 0;
        while (sc.hasNextLine())
        {
            string_op[i] = sc.nextLine();
            i++;
        }
    }

    public void operations_answers_mult_easy(String string_ans[]) throws FileNotFoundException
    {
        File file = new File("mul_ans_easy.txt");
        Scanner sc = new Scanner(file);
        int i = 0;
        while (sc.hasNextLine())
        {
            string_ans[i] = sc.nextLine();
            i++;
        }
    }

    public void operations_mult_medium(String string_op[]) throws FileNotFoundException
    {
        File file = new File("mul_op_medium.txt");
        Scanner sc = new Scanner(file);

        int i = 0;

        while (sc.hasNextLine())
        {
            string_op[i] = sc.nextLine();
            i++;
        }
    }

    public void operations_answers_mult_medium(String string_ans[]) throws FileNotFoundException
    {
        File file = new File("mul_ans_medium.txt");
        Scanner sc = new Scanner(file);

        int i = 0;

        while (sc.hasNextLine())
        {
            string_ans[i] = sc.nextLine();
            i++;
        }
    }

    public void operations_mult_hard(String string_op[]) throws FileNotFoundException
    {
        File file = new File("mul_op_hard.txt");
        Scanner sc = new Scanner(file);

        int i = 0;

        while (sc.hasNextLine())
        {
            string_op[i] = sc.nextLine();
            i++;
        }
    }

    public void operations_answers_mult_hard(String string_ans[]) throws FileNotFoundException
    {
        File file = new File("mul_ans_hard.txt");
        Scanner sc = new Scanner(file);

        int i = 0;

        while (sc.hasNextLine())
        {
            string_ans[i] = sc.nextLine();
            i++;
        }
    }


    /**---------------------------------------------------------------------------------------*/
    /**-------------------------------- OPERACIONES CON DIVISION -----------------------------*/
    /**---------------------------------------------------------------------------------------*/
    public void operations_div_easy(String string_op[]) throws FileNotFoundException
    {
        File file = new File("div_op_easy.txt");
        Scanner sc = new Scanner(file);
        int i = 0;
        while (sc.hasNextLine())
        {
            string_op[i] = sc.nextLine();
            i++;
        }
    }

    public void operations_answers_div_easy(String string_ans[]) throws FileNotFoundException
    {
        File file = new File("div_ans_easy.txt");
        Scanner sc = new Scanner(file);
        int i = 0;
        while (sc.hasNextLine())
        {
            string_ans[i] = sc.nextLine();
            i++;
        }
    }

    public void operations_div_medium(String string_op[]) throws FileNotFoundException
    {
        File file = new File("div_op_med.txt");
        Scanner sc = new Scanner(file);

        int i = 0;

        while (sc.hasNextLine())
        {
            string_op[i] = sc.nextLine();
            i++;
        }
    }

    public void operations_answers_div_medium(String string_ans[]) throws FileNotFoundException
    {
        File file = new File("div_ans_med.txt");
        Scanner sc = new Scanner(file);

        int i = 0;

        while (sc.hasNextLine())
        {
            string_ans[i] = sc.nextLine();
            i++;
        }
    }

    public void operations_div_hard(String string_op[]) throws FileNotFoundException
    {
        File file = new File("div_op_hard.txt");
        Scanner sc = new Scanner(file);

        int i = 0;

        while (sc.hasNextLine())
        {
            string_op[i] = sc.nextLine();
            i++;
        }
    }

    public void operations_answers_div_hard(String string_ans[]) throws FileNotFoundException
    {
        File file = new File("div_ans_hard.txt");
        Scanner sc = new Scanner(file);

        int i = 0;

        while (sc.hasNextLine())
        {
            string_ans[i] = sc.nextLine();
            i++;
        }
    }



    /**---------------------------------------------------------------------------------------*/
    /**---------------------------------------------------------------------------------------*/
    /**---------------------------------------------------------------------------------------*/
    //Regresa la operacion en forma de cadena. Ej. 2+2
    public String operation(String string_op[],int num) { return string_op[num]; }
    //Regresa la respuesta de la operacion. Ej. 4
    public String answer(String string_ans[],int num) { return string_ans[num]; }

    public String[] clearString(String string_op[])
    {
        int i;
        for (i=0; i<20; i++)
        {
            string_op[i] = null;
        }
        return  string_op;
    }

    public String createWrongAnswer(String string_ans)
    {
        /**random.nextInt(max - min + 1) + min*/
        int num = Integer.parseInt(string_ans);
        num += random.nextInt(8-1+1)+1;
        String answer = Integer.toString(num);
        return answer;
    }

    //Devuelve un numero aleatorio y a partir de el
    //poder seleccionar una pregunta con su respuesta
    public int questionNumber()
    {
        random = new Random();
        int num = random.nextInt(20); // Genera numeros aleatorios del 0-20
        System.out.println(num); // Prueba para monitorear el numero de pregunta
        return  num;
    }

    public int respuesta(String answer[], int question_index, int score_value)
    {
        if (score_value ==2 || score_value == 4 )
        {
            if( Integer.parseInt(answer[question_index+1]) == Integer.parseInt(answer[question_index]) );
                return Integer.parseInt(answer[question_index]) - 4;
        }
        if (score_value == 1) return Integer.parseInt(answer[question_index]) - 2;
        else return Integer.parseInt(answer[question_index]) - 3;
    }

    public String respuesta_cadena(String answer[], int question_index, int score_value)
    {
        int aux;
        if (score_value >1 )
        {
            if(Integer.parseInt(answer[question_index+1] ) == Integer.parseInt(answer[question_index]));
            aux =  Integer.parseInt(answer[question_index])-3;
            return Integer.toString(aux);
        }
        else return (answer[question_index]);
    }

    public int respuesta2(String answer[], int question_index, int score_value)
    {
        if (score_value >3 )
        {
            if(Integer.parseInt(answer[question_index+1] ) == Integer.parseInt(answer[question_index]));
                return Integer.parseInt(answer[question_index])+3;
        }
        else return Integer.parseInt(answer[question_index]);
    }


}
