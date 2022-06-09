package com.example.asus.facturacionelectronica;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class ActivityCliente extends AppCompatActivity {

    String [] listaTipoDocumentoCliente={"6 - RUC", "1 - DNI", "4  - CARNET DE EXTRANJERIA", "7 - PASAPORTE", "0 - SIN RUC (EXPORTACIÓN)"};

    boolean valNumeroCliente=false;
    boolean valNombreCliente=false;
    boolean valDireccionCliente=false;
    boolean valEmailCliente=true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente);


        final TextView txtNumeroCliente=findViewById(R.id.txtNumeroCliente);
        final TextView txtNombreCliente=findViewById(R.id.txtNombreCliente);
        final TextView txtDireccionCliente=findViewById(R.id.txtDireccionCliente);
        final TextView txtEmailCliente=findViewById(R.id.txtEmailCliente);
        Spinner cboTipoCliente=findViewById(R.id.cboTipoCliente);
        Button btnRegistrarCliente=findViewById(R.id.btnRegistrarCliente);


        ArrayAdapter<String> adapterTipoCliente = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaTipoDocumentoCliente);

        cboTipoCliente.setAdapter(adapterTipoCliente);





        txtNumeroCliente.addTextChangedListener(new TextWatcher(){


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(txtNumeroCliente.getText().toString().matches("[0-9]{1,15}$")){

                    valNumeroCliente=true;
                    //LUZ VERDE


                }
                else{
                    valNumeroCliente=false;
                    //LUZ ROJA

                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        txtNombreCliente.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if(txtNombreCliente.getText().toString().matches("^[a-zA-Z0-9,.!? ]{1,99}$")){

                    valNombreCliente=true;
                    //LUZ VERDE
                    System.out.println("aaaaaaaaaaaaaaaaaa");


                }
                else{
                    valNombreCliente=false;
                    //LUZ ROJA
                    System.out.println("bbbbbbbbbbbbbbbbbbb");
                }



            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        txtDireccionCliente.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if(txtDireccionCliente.getText().toString().matches("^[a-zA-Z0-9,.!? ]{1,99}$")){

                    valDireccionCliente=true;
                    //LUZ VERDE
                    System.out.println("aaaaaaaaaaaaaaaaaa");


                }
                else{
                    valDireccionCliente=false;
                    //LUZ ROJA
                    System.out.println("bbbbbbbbbbbbbbbbbbb");
                }





            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        txtEmailCliente.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {


                if(txtEmailCliente.getText().toString().matches("(^$|^.*@.*\\..*$)")){

                    valEmailCliente=true;
                    //LUZ VERDE
                    System.out.println("aaaaaaaaaaaaaaaaaa");


                }
                else{
                    valEmailCliente=false;
                    //LUZ ROJA
                    System.out.println("bbbbbbbbbbbbbbbbbbb");
                }




            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });





        btnRegistrarCliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(valDireccionCliente==true && valEmailCliente==true && valNombreCliente==true && valNumeroCliente==true){

                    //SI TODAS LAS CONDICIONES SE CUMPLEN ACA AGREGAR UN REGISTRO A LA BASE DE DATOS (INCLURILE UN ID AUTOGENERADO), Y VOLVER A LA PANTALLA PRINCIPAL
                    //PASANDO DICHO ID PARA QUE EN LA OTRA PANTALLA SE MUESTREN LOS DATOS DEL NUEVO REGISTRO

                    System.out.println("aaaaaaaaaaaaa");

                }
                else{

                    //SI NO SE CUMPLE ALGUNA CONDICION ACA MOSTRAR UN POPUP DICIENDO LO QUE FALTA

                    System.out.println("bbbbbbbbbbbbbbbb");
                }

            }
        });




    }
}
