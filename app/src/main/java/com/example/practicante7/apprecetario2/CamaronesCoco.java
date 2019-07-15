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

public class CamaronesCoco extends AppCompatActivity {
    //region VARIABLES
    //the class header is called (TemplatePDF)
    //to add the title to the table of contents
    private String[] header = {"Ingredientes"};
    //the class is called and a variable is created, in order to use it
    private TemplatePDF templatePDF;
    //The Urls of the images that are displayed in the activity are assigned
    private String[] mImageUrls = new String[]{
            "https://www.vanidades.com/wp-content/uploads/2018/06/Arroz-con-camarones-y-coco-1024x825.jpg",
            "https://islabonitaeurobanan.files.wordpress.com/2012/07/coco-fruta-tropical.jpg",
            "https://marcobeteta.com/wp-content/uploads/camaronesalcoco.jpg"};
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camarones_coco);

        //region templatePDF
        //with the declaration of the variable of the TemplatePDF class
        //you can fill the PDF with the recipe data here
        //of the corresponding activity
        templatePDF = new TemplatePDF(getApplicationContext());
        templatePDF.openDocument();
        templatePDF.addMetaData("Ingrendiente"); //Add the title to the table
        //Add the title of the recipe, as well as the subtitle (author) and the date on which it was generated
        templatePDF.addTitle("Receta de Camarones al Coco", "Kiwilimón", "22/01/2019");
        //Declare a local variable with a small text with the description that follows the table
        String shorText = "Elementos de la receta";
        //Add the variable with the description to the document
        templatePDF.addParagraph(shorText);
        templatePDF.createTable(header, getIngs()); //Create the table
        //Declare another local variable where the text that will go inside the table of contents will come
        String longText = "1. Prepara una charola para hornear con papel encerado. \n" +
                "2. Mezcla el pan molido, el coco rallado, la ralladura de limón, sal y pimienta en un recipiente hondo. \n" +
                "3. En otro recipiente bate los huevos con un tenedor. \n" +
                "4. Pasa cada camarón primero por los huevos y luego por la mezcla de empanizado y coloca en la charola para hornear. \n" +
                "5. Este paso lo puedes hacer un par de horas antes, cubrir los camarones y refrigerar hasta el momento de freír. \n" +
                "6. Caliente el aceite para freir en un sartén hondo y grande y fríe los camarones hasta que estén ligeramente dorados y crujientes (aprox. 4 minutos).\n" +
                "7. Retira del aceite y coloca en un plato con toallas de papel absorbentes. +" +
                "8. Coloca los camarones en un platón para servir y acompaña del aderezo de tamarindo.";
        //Add the text to the document
        templatePDF.addParagraph(longText);
        templatePDF.closeDocument();
//endregion

        ViewPager viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, mImageUrls);
        viewPager.setAdapter(viewPagerAdapter);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //Add a floating button to the activity to be part of the design
        //said button will guide the activity where the PDF document is located
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CamaronesCoco.this, VisorPDF.class)
                        //.putExtra calls the route
                        .putExtra(VisorPDF.FILE_PATHCC, templatePDF.getPdfFile().getAbsolutePath()));
            }
        });
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    //Create the ArrayList in done will be filled with the content
    //that will go inside the table where it will show the Eng. necessary for the recipe
    private ArrayList<String[]> getIngs() {
        ArrayList<String[]> rows = new ArrayList<>();
        rows.add(new String[]{"1/2 taza de coco rallado."});
        rows.add(new String[]{"3/4 de taza de pan molido."});
        rows.add(new String[]{"2 cucharaditas de ralladura de limón."});
        rows.add(new String[]{"1 cucharada de sal."});
        rows.add(new String[]{"1/4 de cucharada de pimienta negra."});
        rows.add(new String[]{"2 huevos."});
        rows.add(new String[]{"18 piezas de camarón grande pelado, desvenado y limpio."});
        rows.add(new String[]{"Aceite Vegetal Great Value®."});
        rows.add(new String[]{"papeles encerado."});
        rows.add(new String[]{"aderezo de tamarindo."});
        //Return the row with the contents of the table
        return rows;
    }
}
