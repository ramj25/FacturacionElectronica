package com.example.asus.facturacionelectronica;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdapterRecycler extends RecyclerView.Adapter<AdapterRecycler.ViewHolder> {


    private LayoutInflater mInflater;
    private List<String> cuenta=new ArrayList<>();
    public boolean[] valsP1=new boolean[50];                   //MAXIMO 50 PRODUCTOS(?)
    public boolean[] valsP2=new boolean[50];
    public boolean[] valsP3=new boolean[50];



    AdapterRecycler(Context context, ArrayList<String> cuenta){



        this.mInflater=LayoutInflater.from(context);
        this.cuenta=cuenta;


    }






    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=mInflater.inflate(R.layout.fila_recycler,parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {





    }

    @Override
    public int getItemCount() {
        return cuenta.size();
    }




    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView txtP1;
        public TextView txtP2;
        public TextView txtP3;
        public TextView txtP4;
        public TextView txtP5;
        public Spinner spnP1;
        public Spinner spnP2;
        public Button btnP1;

        public double igv=1.18;



        String [] listaProductoServicio={"Producto","Servicio"};
        String [] listaIGV={"Gravado","Gratuito","Exonerado","Inafecto","Exportación"};




        public ViewHolder(final View itemView) {


            super(itemView);



            txtP1=itemView.findViewById(R.id.txtP1);
            txtP2=itemView.findViewById(R.id.txtP2);
            txtP3=itemView.findViewById(R.id.txtP3);
            txtP4=itemView.findViewById(R.id.txtP4);
            txtP5=itemView.findViewById(R.id.txtP5);
            spnP1=itemView.findViewById(R.id.spnP1);
            spnP2=itemView.findViewById(R.id.spnP2);
            btnP1=itemView.findViewById(R.id.btnP1);

            ArrayAdapter<String> adapterProductoServicio = new ArrayAdapter(itemView.getContext(), android.R.layout.simple_list_item_1,listaProductoServicio);

            spnP1.setAdapter(adapterProductoServicio);

            ArrayAdapter<String> adapterIGV= new ArrayAdapter(itemView.getContext(), android.R.layout.simple_list_item_1,listaIGV);

            spnP2.setAdapter(adapterIGV);




            txtP1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                    System.out.println("asd "+getAdapterPosition());


                    if(txtP1.getText().toString().matches("^[a-zA-Z0-9,.!? ]{1,99}$")){

                        System.out.println("aaaaaaaaaaaaaaaaaaaaaa");

                        valsP1[getAdapterPosition()]=true;

                        //LUZ VERDE



                    }
                    else{
                        //LUZ ROJA

                        System.out.println("bbbbbbbbbbbbbbbbbbbb");

                        valsP1[getAdapterPosition()]=false;
                    }



                }
                @Override
                public void afterTextChanged(Editable s) {

                }
            });




            txtP2.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


                    if(txtP2.getText().toString().matches("^([0-9]{1,6}\\.[0-9]{1,2})|(\\d{1,6})$")){

                        System.out.println("aaaaaaaaaaaaaaaaaaaaaa");

                        valsP2[getAdapterPosition()]=true;

                        //LUZ VERDE

                        if(valsP3[getAdapterPosition()]==true){
                            txtP4.setText(""+(Double.parseDouble(txtP2.getText().toString()))*(Double.parseDouble(txtP3.getText().toString())));

                            if(spnP2.getSelectedItemPosition()==0){
                                txtP5.setText(""+(Double.parseDouble(txtP4.getText().toString()))*igv);
                            }
                            else{
                                txtP5.setText(txtP4.getText());
                            }
                        }

                        else{
                            txtP4.setText("");
                            txtP5.setText("");
                        }



                    }
                    else{
                        //LUZ ROJA

                        System.out.println("bbbbbbbbbbbbbbbbbbbb");

                        valsP2[getAdapterPosition()]=false;

                        txtP4.setText("");
                        txtP5.setText("");
                    }




                }
                @Override
                public void afterTextChanged(Editable s) {

                }
            });



            txtP3.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {


                    if(txtP3.getText().toString().matches("^(\\d{1,6})$")){

                        System.out.println("aaaaaaaaaaaaaaaaaaaaaa");

                        valsP3[getAdapterPosition()]=true;

                        //LUZ VERDE

                        if(valsP2[getAdapterPosition()]==true){
                            txtP4.setText(""+(Double.parseDouble(txtP2.getText().toString()))*(Double.parseDouble(txtP3.getText().toString())));

                            if(spnP2.getSelectedItemPosition()==0){
                                txtP5.setText(""+(Double.parseDouble(txtP4.getText().toString()))*igv);
                            }
                            else{
                                txtP5.setText(txtP4.getText());
                            }
                        }
                        else{
                            txtP4.setText("");
                            txtP5.setText("");
                        }


                    }
                    else{
                        //LUZ ROJA

                        System.out.println("bbbbbbbbbbbbbbbbbbbb");

                        valsP3[getAdapterPosition()]=false;

                        txtP4.setText("");
                        txtP5.setText("");

                    }



                }
                @Override
                public void afterTextChanged(Editable s) {

                }
            });


            spnP2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                    if(valsP2[getAdapterPosition()]==true && valsP3[getAdapterPosition()]==true){

                        if(spnP2.getSelectedItemPosition()==0){
                            txtP5.setText(""+(Double.parseDouble(txtP4.getText().toString()))*igv);
                        }
                        else{
                            txtP5.setText(txtP4.getText());
                        }

                    }


                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });



            btnP1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    System.out.println("--------------");

                    for(boolean boo:valsP1){
                        System.out.println(boo);
                    }

                    System.out.println("--------------");



                    List<Boolean> aux1 = new ArrayList<>();
                    for(boolean b:valsP1){
                        aux1.add(b);
                    }


                    aux1.remove(getAdapterPosition());



                    valsP1 = new boolean[50];

                    for(int i=0;i<aux1.size()-1;i++){
                        valsP1[i]=aux1.get(i);
                    }

                    //valsP1=aux1.toArray(new boolean[50]);
                    // Arrays.copyOf(aux1.toArray(), aux1.size(), boolean[].class);



                    for(boolean boo:valsP1){
                        System.out.println(boo);
                    }





                    List<Boolean> aux2 = new ArrayList<>();
                    for(boolean b:valsP2){
                        aux2.add(b);
                    }


                    aux2.remove(getAdapterPosition());



                    valsP2 = new boolean[50];

                    for(int i=0;i<aux2.size()-1;i++){
                        valsP2[i]=aux2.get(i);
                    }






                    List<Boolean> aux3 = new ArrayList<>();
                    for(boolean b:valsP3){
                        aux3.add(b);
                    }


                    aux3.remove(getAdapterPosition());



                    valsP3 = new boolean[50];

                    for(int i=0;i<aux3.size()-1;i++){
                        valsP3[i]=aux3.get(i);
                    }





                    cuenta.remove(getAdapterPosition());

                    notifyItemRemoved(getAdapterPosition());


                }
            });




        }






    }

}
