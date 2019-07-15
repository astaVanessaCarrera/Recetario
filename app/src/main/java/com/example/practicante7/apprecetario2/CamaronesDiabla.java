package com.example.practicante7.apprecetario2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class CamaronesDiabla extends AppCompatActivity {
    //region VARIABLES
    //the class header is called (TemplatePDF)
    //to add the title to the table of contents
    private String[] header = {"Ingredientes"};
    //the class is called and a variable is created, in order to use it
    private TemplatePDF templatePDF;
    //The Urls of the images that are displayed in the activity are assigned
    private String[] mImageUrls = new String[]{
            "http://static.azteca.com/crop/crop.php?width=765&height=&img=http://static.azteca.com/imagenes/2013/27/a-1763095.jpg&coordinates=50,50",
            "https://www.superama.com.mx/views/micrositio/recetas/images/verano/camarones-diabla/Web_fotoreceta.jpg",
            "https://www.irecetasmexicanas.com/wp-content/uploads/2016/02/camarones-a-la-diabla-e1455165358188.jpg"};
    //endregion
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camarones_diabla);

    //region TemplatePDF
        //with the declaration of the variable of the TemplatePDF class
        //you can fill the PDF with the recipe data here
        //of the corresponding activity
        templatePDF = new TemplatePDF(getApplicationContext());
        templatePDF.openDocument();
        templatePDF.addMetaData("Ingrendiente");  //Add the title to the table
        //Add the title of the recipe, as well as the subtitle (author) and the date on which it was generated
        templatePDF.addTitle("Receta de Camarones a la Diabla", "Kiwilimón", "12/01/2019");
        //Declare a local variable with a small text with the description that follows the table
        String shorText = "Elementos de la receta";
        //Add the variable with the description to the document
        templatePDF.addParagraph(shorText);
        templatePDF.createTable(header,getIngs()); //Create the table
        //Declare another local variable where the text that will go inside the table of contents will come
        String longText = "1. Hierve agua en una ollita a fuego medio. Retira del fuego y remoja los chiles guajillos y los de árbol durante 5 minutos o hasta que se ablanden.\n" +
                "2. Licúa los chiles con el ajo, la cebolla, el chile chipotle y un poco del líquido de remojo de los chiles. \n" +
                "3. En una ollita a fuego medio, calienta la mantequilla y el aceite. Fríe la cebolla hasta que esté transparente y agrega el jitomate. \n" +
                "4. Añade la salsa y cocina alrededor de 10 minutos hasta que espese. Sazona con sal y pimienta. \n" +
                "5. En una sartén profunda a fuego medio, fríe los camarones con el aceite de oliva y sazona con sal y pimienta. Agrega la salsa y mezcla muy bien. Cocina 5 minutos más y termina con cilantro picado.  \n";
        //Add the text to the document
        templatePDF.addParagraph(longText);
        templatePDF.closeDocument();
    //endregion

        ViewPager viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, mImageUrls);
        viewPager.setAdapter(viewPagerAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //Add a floating button to the activity to be part of the design
        //said button will guide the activity where the PDF document is located
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CamaronesDiabla.this, VisorPDF.class)
                        .putExtra(VisorPDF.FILE_PATHCD, templatePDF.getPdfFile().getAbsolutePath()));
            }
        });
    }

    public boolean onOptionsItemSelected (MenuItem item){
        if (item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
    //Create the ArrayList in done will be filled with the content
    //that will go inside the table where it will show the Eng. necessary for the recipe
    private ArrayList<String[]> getIngs(){
        ArrayList<String[]>rows = new ArrayList<>();
        rows.add(new String[] {"8 piezas de chile guajillo desvenado."});
        rows.add(new String[] {"3 piezas de chile de árbol."});
        rows.add(new String[] {"2 dientes de ajo."});
        rows.add(new String[] {"1/4 de pieza de cebolla"});
        rows.add(new String[] {"2 piezas de chile chipotle adobado."});
        rows.add(new String[]{"2 cucharadas de Aceite de Oliva Extra Especial®."});
        rows.add(new String[]{"2 cucharadas de mantequilla."});
        rows.add(new String[]{"1/4 de pieza de cebolla finamente picada."});
        rows.add(new String[]{"2 piezas de jitomate finamente picado."});
        rows.add(new String[]{"1 pizca de sal Great Value®."});
        rows.add(new String[]{"1 pizca de pimienta."});
        rows.add(new String[]{"2 cucharadas de Aceite de Oliva Extra Especial®."});
        rows.add(new String[]{"4 tazas de camarón mediano limpio."});
        rows.add(new String[]{"1/2 cucharadita de sal Great Value®."});
        rows.add(new String[]{"1/4 de cucharadita de pimienta."});
        rows.add(new String[]{"2 cucharadas de cilantro finamente picado."});
        //Return the row with the contents of the table
        return rows;
}
}