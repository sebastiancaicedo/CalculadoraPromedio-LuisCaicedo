package com.calculadorapromedio.luiscaicedo.calculadorapromedio_luiscaicedo;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements OnClickListener {

    EditText etNota1;
    EditText etNota2;
    EditText etNota3;
    EditText etNota4;
    Button bCalcular;
    TextView tPromedio;

    EditText[] etsNotas= new EditText[4];

    float prom;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNota1= (EditText)findViewById(R.id.etnota1);
        etNota2= (EditText)findViewById(R.id.etnota2);
        etNota3= (EditText)findViewById(R.id.etnota3);
        etNota4= (EditText)findViewById(R.id.etnota4);
        bCalcular= (Button)findViewById(R.id.bcalcular);
        tPromedio= (TextView)findViewById(R.id.tpromedio);

        bCalcular.setOnClickListener(this);

        etsNotas[0]= etNota1;
        etsNotas[1]= etNota2;
        etsNotas[2]= etNota3;
        etsNotas[3]= etNota4;

    }

    @Override
    public void onClick(View view) {

        if(view == bCalcular){

            float notas[] = new float[4];
            boolean hayUnCampoVacio =false;
            boolean[] etEstaVacio= new boolean[4];

            //Obtenemos las notas de los editTexts
            for(int indice=0; indice < 4; indice++){
                if(!isEditTextVacio(etsNotas[indice])){

                    notas[indice]= Float.parseFloat(etsNotas[indice].getText().toString());
                }
                else
                {
                    if(!hayUnCampoVacio){
                        hayUnCampoVacio=true;
                    }
                    etEstaVacio[indice]= true;
                }
            }

            if(!hayUnCampoVacio) {

                prom = 0;
                //Sumamos y calculamos promedio
                for (int i = 0; i < 4; i++) {
                    prom = prom + notas[i];
                }
                prom = prom / notas.length;

            }
            else
            {
                float notaObjetivo= 3.8f;
                float valorNota= 0.25f;

                //Sumamos las notas que si dan
                float sumaNotasDadas=0;
                for (int i=0; i < 4;i++){

                    if(!etEstaVacio[i]){

                        sumaNotasDadas= sumaNotasDadas + notas[i];
                    }
                }

                float sumaNotasNoDadas = (notaObjetivo/valorNota)-sumaNotasDadas;
                int notasRestantes= getNumeroDeNotasVacias(etEstaVacio);

                for (int i=0; i< 4; i++){

                    if(etEstaVacio[i]){

                        etsNotas[i].setText(String.valueOf(sumaNotasNoDadas/notasRestantes));
                        etsNotas[i].setTextColor(Color.RED);
                    }
                }

                prom= 3.8f;

            }

            tPromedio.setText(String.valueOf(prom));
        }
    }

    /**
     * Verifica si el edit text pasado como parametro esta vacio
     * @param editText el edit text a verificar
     * @return true si está vacio, false si no está vacio
     */
    private boolean isEditTextVacio(EditText editText) {

        if (TextUtils.isEmpty(editText.getText())) {
            return true;
        }
        else {
            return false;
        }
    }

    private int getNumeroDeNotasVacias(boolean[] vector){
        int cont=0;
        for (int i = 0; i< 4; i++){
            if(vector[i]){
                cont++;
            }
        }
        return  cont;
    }

}
