package com.example.asus.facturacionelectronica;

import com.example.asus.facturacionelectronica.R;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity{

    AdapterRecycler adapter;
    AdapterRecycler.ViewHolder holder;

    public static double dolar=3.25;
    public static double euro=3.75;

    String [] listaTipoDocumento={"FACTURA ELECTRÓNICA"};
    String [] listaTipoOperacion={"VENTA INTERNA","ANTICIPO O DEDUCCIÓN DE ANTICIPO EN VENTA INTERNA","EXPORTACIÓN"};
    String [] listaMoneda={"Soles","Dólares","Euros"};
    String [] listaSerie={"FFF1"};
    String [] listaDetraccion={"NO","SI"};

  /*  static final int DATE_DIALOG_ID = 0;
    public TextView txtFecha;
    public int mYear;
    public int mMonth;
    public int mDay;        */

  boolean valNumeroReferencial = false;
  boolean valNotas = true;

  ArrayList<String> cue=new ArrayList<String>();


  public RecyclerView rec1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TO DO

        //CARGAR CAMPOS COMO TIPO DOCUMENTO, TIPO OPERACION, MONEDA, SERIE DESDE JSON? O DEJARLO EN DURO?

        //LOS INDICES DE LOS CAMPOS (EJ. MONEDA) SON LOS APROPIADOS? EJ. PARA NUBEFACT 0 = SOLES?

        //EL NUMERO DE DOCUMENTO TIENE QUE SER UNICO O SECUENCIAL?

        //SACAR EL TIPO DE CAMBIO DE UN SERVICIO WEB (NO INCLUIRLO EN FORMULARIO) (VER SI SE UTILIZA PARA ALGO O ES SOLO REFERENCIAL)

        //OPCION PARA EXTRAER DATOS DEL CLIENTE CON SERVICIOS POR MEDIO DE RUC?

        //BOTON VOLVER EN PANTALLA DE CLIENTE?

        //INCLUIR CATEGORIA DE PRODUCTO? (NO APARECE EN EL JSON NI EN OTRO LADO) (SOLO SERVIRIA PARA ENCONTRAR MAS RAPIDO UN PROD EN EL CATALOGO?)

        //INCLUIR OPCION PARA GUARDAR UNIDADES DE MEDIDA? (SE PUEDE INCLUIR SIMPLEMENTE EN EL NOMBRE DEL PROD) (EJ. UN KILO DE COMPOST)

        //IMPLEMENTAR DETALLE DE LUCES ROJAS Y VERDES?

        //IMPLEMENTAR EL CODIGO PRODUCTO DE LA SUNAT (OBLIGATORIO DESDE EL 2019?)

        //IMPL SOLUCIONES EN CASO NO HAYA CONEXION INTERNET



        //IMPORTANTE:

        //NO OLVIDAR DEJAR DE INCLUIR LOS DATOS DE UNA FILA DEL RECYCLER EN EL CUADRO DEL FINAL AL PRESIONAR EL BOTON ELIMINAR REGISTRO Y BORRAR VALS( VER SI INTERVIENE EN GETADAPTERPOSTION DE VALS)

        //EN LA VALIDACION EN LA PARTE DE PRODUCTOS HACER UN FOR DE LOS 3 VALS EN LOS QUE TODOS DEBEN SER TRUE TENIENDO EN CUENTA !QUE SOLO SE INCLUYAN LOS INDICES [] QUE SEAN UN GETADAPTEPOSITION!
        // (SOLO EL NUMERO DE PRODUCTOS QUE SE HAYAN CREADO, NO LOS 50 DEL ARRAY) (TENER EN CUENTA POSIBLES "HUECOS" DESPUES DE USAR EL BOTON ELIMINAR

        ////NO OLVIDAR FUNCIONES BOTON CLIENTE (BASE DE DATOS, ETC)

        //SOLUCIONAR PROBLEMA: LOS PRODUCTOS DESAPARECEN CUANDO SE CAMBIA LA ORIENTACION DE LA PANTALLA






        //EJECUTAR ESTE CODIGO + EL COD DE TOTAL + VALIDACIONES CAMPOS + FUTURO CODIGO CUADRO PRECIOS EN LOS DISTINTOS EVENTOS DE LOS TEXTFIELDS Y SPINNERS:

        /*
        for(int i=0;i<=cue.size();i++){

          double precio=  Double.parseDouble(rec1.findViewHolderForAdapterPosition(i).itemView.findViewById(R.id.txtP2).toString());

          int cantidad=  Integer.parseInt(rec1.findViewHolderForAdapterPosition(i).itemView.findViewById(R.id.txtP3).toString());

          TextView t=rec1.findViewHolderForAdapterPosition(i).itemView.findViewById(R.id.txtP4);

          t.setText(precio*cantidad+"");

       */


        Button btnCliente=findViewById(R.id.btnCliente);
        Button btnProductos=findViewById(R.id.btnProductos);
        Button btnCrear=findViewById(R.id.btnCrear);
     //   Button btnFecha=findViewById(R.id.btnFecha);
        Spinner cboTipoDocumento=findViewById(R.id.cboTipoDocumento);
        Spinner cboSerie=findViewById(R.id.cboSerie);
        Spinner cboCliente=findViewById(R.id.cboCliente);

        Spinner cboMoneda=findViewById(R.id.cboMoneda);
        Spinner cboDetraccion=findViewById(R.id.cboDetraccion);
        Spinner cboTipoOperacion=findViewById(R.id.cboTipoOperacion);
        final TextView txtNumeroReferencial=findViewById(R.id.txtNumeroReferencial);
        // final TextView txtTipoCambio=findViewById(R.id.txtTipoCambio);
        final TextView txtNotas=findViewById(R.id.txtNotas);
      /*  txtFecha=findViewById(R.id.txtFecha);    */

        TextView txtGravado=findViewById(R.id.txtGravado);
        TextView txtExonerado=findViewById(R.id.txtExonerado);
        TextView txtInafecto=findViewById(R.id.txtInafecto);
        TextView txtFinal=findViewById(R.id.txtFinal);
        TextView txtIGV=findViewById(R.id.txtIGV);
        TextView txtGratuito=findViewById(R.id.txtGratuito);



        rec1=findViewById(R.id.rec1);
        rec1.setItemViewCacheSize(50);
        rec1.setLayoutManager(new LinearLayoutManager(this));
        adapter=new AdapterRecycler(this,cue);
        rec1.setAdapter(adapter);




        ArrayAdapter<String>adapterTipoDocumento = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaTipoDocumento);
        ArrayAdapter<String>adapterTipoOperacion = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaTipoOperacion);
        ArrayAdapter<String>adapterMoneda = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaMoneda);
        ArrayAdapter<String>adapterSerie = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaSerie);
        ArrayAdapter<String>adapterDetraccion = new ArrayAdapter(this, android.R.layout.simple_list_item_1,listaDetraccion);

        cboTipoDocumento.setAdapter(adapterTipoDocumento);
        cboTipoOperacion.setAdapter(adapterTipoOperacion);
        cboMoneda.setAdapter(adapterMoneda);
        cboSerie.setAdapter(adapterSerie);
        cboDetraccion.setAdapter(adapterDetraccion);






        btnProductos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                cue.add("");
                adapter.notifyDataSetChanged();




            }
        });





        btnCliente.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {

                System.out.println(adapter.getItemCount());

                Intent i = new Intent(getApplicationContext(),ActivityCliente.class);
                startActivity(i);


                for(int in=0;in<4;in++){
                    System.out.println("----------");


                    System.out.println(adapter.valsP1[in]);
                    System.out.println(adapter.valsP2[in]);
                    System.out.println(adapter.valsP3[in]);


                }




            }
        });






/*        btnFecha.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                showDialog(DATE_DIALOG_ID);

            }
        });

        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        actualizarFecha();    */

        txtNumeroReferencial.addTextChangedListener(new TextWatcher(){


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(txtNumeroReferencial.getText().toString().matches("^[1-9][0-9]{0,7}$")){

                    valNumeroReferencial=true;
                    //LUZ VERDE
                    System.out.println("aaaaaaaaaaaaaaaaaaaaaa");

                }
                else{
                    valNumeroReferencial=false;
                    //LUZ ROJA
                    System.out.println("bbbbbbbbbbbbbbbbbb");
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


        txtNotas.addTextChangedListener(new TextWatcher(){


            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if(txtNotas.getText().toString().matches("^[a-zA-Z0-9,.!? ]{0,999}$")){

                    System.out.println("aaaaaaaaaaaaaaaaaaaaaa");

                    valNotas=true;
                    //LUZ VERDE

                }
                else{
                    //LUZ ROJA
                    valNotas=false;
                    System.out.println("bbbbbbbbbbbbbbbbbbbb");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });



/*
        txtTipoCambio.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(txtTipoCambio.getText().toString().matches("^[1-9]{1}[.]{2}[0-9]{3}[0-9]{4}[0-9]{5}$")){
                    System.out.println("aaaaaaaaaaa");
                }
                else{
                    System.out.println("bbbbbbbbbbbb");
                }

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

*/


    }




    public void llenarCuadro(){


        for(int c=0;c<adapter.getItemCount();c++){

            if(rec1.findViewHolderForAdapterPosition(c).itemView.findViewById(R.id.txtP5).toString()!=""){


            }


        }


    }
















    /*

    private void actualizarFecha() {
        this.txtFecha.setText(
                new StringBuilder()

                        .append(mMonth + 1).append("-")
                        .append(mDay).append("-")
                        .append(mYear).append(" "));
    }

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                    mYear = year;
                    mMonth = monthOfYear;
                    mDay = dayOfMonth;
                    actualizarFecha();
                }


            };

    @Override
    protected Dialog onCreateDialog(int id){
        switch (id) {
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                        mDateSetListener,
                        mYear, mMonth, mDay);
        }
        return null;
    }

*/


}
