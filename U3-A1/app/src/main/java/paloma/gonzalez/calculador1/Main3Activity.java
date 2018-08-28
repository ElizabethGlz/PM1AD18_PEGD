package paloma.gonzalez.calculador1;

import android.app.Activity;
import android.os.Bundle;
import android.view.TextureView;
import android.view.View;
import android.widget.TextView;

import com.eg.myapplication1.R;

import java.util.ArrayList;
import java.util.Stack;

public class Main3Activity extends Activity {
    String operacion="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }
    public void uno(View view){
        final TextView tv=(TextView)(findViewById(R.id.txtEntrada));
        String contiene=tv.getText().toString();
        tv.setText(contiene+"1");

    }
    public void dos(View view){
        final TextView tv=(TextView)(findViewById(R.id.txtEntrada));
        String contiene=tv.getText().toString();
        tv.setText(contiene+"2");

    }
    public void tres(View view){
        final TextView tv=(TextView)(findViewById(R.id.txtEntrada));
        String contiene=tv.getText().toString();
        tv.setText(contiene+"3");

    }
    public void cuatro(View view){
        final TextView tv=(TextView)(findViewById(R.id.txtEntrada));
        String contiene=tv.getText().toString();
        tv.setText(contiene+"4");


    }
    public void cinco (View view){
        final TextView tv=(TextView)(findViewById(R.id.txtEntrada));
        String contiene=tv.getText().toString();
        tv.setText(contiene+"5");


    }
    public void seis(View view){
        final TextView tv=(TextView)(findViewById(R.id.txtEntrada));
        String contiene=tv.getText().toString();
        tv.setText(contiene+"6");


    }
    public void siete(View view){
        final TextView tv=(TextView)(findViewById(R.id.txtEntrada));
        String contiene=tv.getText().toString();
        tv.setText(contiene+"7");


    }
    public void ocho(View view){
        final TextView tv=(TextView)(findViewById(R.id.txtEntrada));
        String contiene=tv.getText().toString();
        tv.setText(contiene+"8");


    }
    public void nueve(View view){
        final TextView tv=(TextView)(findViewById(R.id.txtEntrada));
        String contiene=tv.getText().toString();
        tv.setText(contiene+"9");


    }
    public void cero(View view){
        final TextView tv=(TextView)(findViewById(R.id.txtEntrada));
        String contiene=tv.getText().toString();
        tv.setText(contiene+"0");

    }
    public  void igual(View view){
        final TextView tv=(TextView)(findViewById
                (R.id.txtEntrada));
        ConvertirPostfijo cp=new ConvertirPostfijo
                (tv.getText().toString());
        String postfija=cp.postfix;
        EvaluarPostijo ep=new EvaluarPostijo(postfija);
        tv.setText(ep.resultado);

    }
    public void mas(View view){
        final TextView tv=(TextView)(findViewById(R.id.txtEntrada));
        String contiene=tv.getText().toString();
        tv.setText(contiene+"+");

    }
    public void menos(View view){
        final TextView tv=(TextView)(findViewById(R.id.txtEntrada));
        String contiene=tv.getText().toString();
        tv.setText(contiene+"-");

    }
    public void por(View view){
        final TextView tv=(TextView)(findViewById(R.id.txtEntrada));
        String contiene=tv.getText().toString();
        tv.setText(contiene+"*");

    }
    public void entre(View view){
        final TextView tv=(TextView)(findViewById(R.id.txtEntrada));
        String contiene=tv.getText().toString();
        tv.setText(contiene+"/");

    }
    public void borrar(View view){
        final TextView tvB=(TextView)(findViewById(R.id.txtEntrada));

        tvB.setText("");
    }

    public class ConvertirPostfijo {
        //Depurar expresión algebraica
        public String infix ;
        public String postfix;
        public ConvertirPostfijo(String exp){
            //Depurar la expresion algebraica
            String expr = depurar(exp);
            String[] arrayInfix = expr.split(" ");

            //Declaración de las pilas
            Stack < String > E = new Stack < String > ();
//Pila entrada
            Stack < String > P = new Stack < String > ();
//Pila temporal para operadores
            Stack < String > S = new Stack < String > ();
//Pila salida

            //Añadir la array a la Pila de entrada (E)
            for (int i = arrayInfix.length - 1; i >= 0; i--)
            {
                E.push(arrayInfix[i]);
            }

            try {
                //Algoritmo Infijo a Postfijo
                while (!E.isEmpty()) {
                    switch (pref(E.peek())){
                        case 1:
                            P.push(E.pop());
                            break;
                        case 3:
                        case 4:
                            while(pref(P.peek()) >= pref
                                    (E.peek())) {
                                S.push(P.pop());
                            }
                            P.push(E.pop());
                            break;
                        case 2:
                            while(!P.peek().equals("(")) {
                                S.push(P.pop());
                            }
                            P.pop();
                            E.pop();
                            break;
                        default:
                            S.push(E.pop());
                    }
                }

                //Eliminacion de `impurezas´ en la expresiones algebraicas
                infix = expr.replace(" ", "");
                postfix = S.toString().replaceAll("[\\]\\[,]", "");


            }catch(Exception ex){

            }
        }
        private  String depurar(String s) {
            s = s.replaceAll("\\s+", ""); //Elimina espacios en blanco
            s = "(" + s + ")";
            String simbols = "+-*/()";
            String str = "";

            //Deja espacios entre operadores
            for (int i = 0; i < s.length(); i++) {
                if (simbols.contains("" + s.charAt(i))) {
                    str += " " + s.charAt(i) + " ";
                }else str += s.charAt(i);
            }
            return str.replaceAll("\\s+", " ").trim();
        }

        //Jerarquia de los operadores
        private  int pref(String op) {
            int prf = 99;
            if (op.equals("^")) prf = 5;
            if (op.equals("*") || op.equals("/")) prf = 4;
            if (op.equals("+") || op.equals("-")) prf = 3;
            if (op.equals(")")) prf = 2;
            if (op.equals("(")) prf = 1;
            return prf;
        }
    }
    public class EvaluarPostijo {
        public String resultado="";
        public EvaluarPostijo(String expr){
            //Entrada (Expresión en Postfija)
            String[] post = expr.split(" ");

            //Declaración de las pilas
            Stack < String > E = new Stack < String > ();
//Pila entrada
            Stack < String > P = new Stack < String > ();
//Pila de operandos

            //Añadir post (array) a la Pila de entrada (E)
            for (int i = post.length - 1; i >= 0; i--) {
                E.push(post[i]);
            }

            //Algoritmo de Evaluación Postfija
            String operadores = "+-*/%";
            while (!E.isEmpty()) {
                if (operadores.contains("" + E.peek())) {
                    P.push(evaluar(E.pop(), P.pop(), P.pop
                            ()) + "");
                }else {
                    P.push(E.pop());
                }
            }

            resultado=P.peek();

        }
        private  int evaluar(String op, String n2, String
                n1) {
            int num1 = Integer.parseInt(n1);
            int num2 = Integer.parseInt(n2);
            if (op.equals("+")) return (num1 + num2);
            if (op.equals("-")) return (num1 - num2);
            if (op.equals("*")) return (num1 * num2);
            if (op.equals("/")) return (num1 / num2);
            if (op.equals("%")) return (num1 % num2);
            return 0;
        }
    }


}

