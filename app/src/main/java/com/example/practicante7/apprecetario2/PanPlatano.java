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

import java.util.ArrayList;

public class PanPlatano extends AppCompatActivity {

    //the class header is called (TemplatePDF)
    //to add the title to the table of contents
    private String[] header = {"Ingredientes"};
    //the class is called and a variable is created, in order to use it
    private TemplatePDF templatePDF;
    //The Urls of the images that are displayed in the activity are assigned
    private String[] mImageUrls = new String[]{
            "https://img.bekia.es/cocina/0000/276/1.jpg",
            "https://i.blogs.es/6dd7b8/panplatano/450_1000.jpg",
            "https://cdn2.cocinadelirante.com/sites/default/files/styles/gallerie/public/images/2018/09/pan-de-platano-con-avena.jpg"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pan_platano);
        //region Template
        //with the declaration of the variable of the TemplatePDF class
        //you can fill the PDF with the recipe data here
        //of the corresponding activity
        templatePDF = new TemplatePDF(getApplicationContext());
        templatePDF.openDocument();
        templatePDF.addMetaData("Ingrendiente");  //Add the title to the table
        //Add the title of the recipe, as well as the subtitle (author) and the date on which it was generated
        templatePDF.addTitle("Receta de Pan de Plátano", "Kiwilimón", "09/07/2019");
        //Declare a local variable with a small text with the description that follows the table
        String shorText = "Elementos de la receta";
        //Add the variable with the description to the document
        templatePDF.addParagraph(shorText);
        templatePDF.createTable(header, getIngs()); //Create the table
        //Declare another local variable where the text that will go inside the table of contents will come
        String longText = "1. Pre-caliente el horno a 180 grados centígrados. \n" +
                "2. Engrase un molde para hacer pan. \n" +
                "3. Mezcle en un recipiente hondo los plátanos, el azúcar, el aceite, la leche y los huevos. \n" +
                "4. Agregue la harina, la taza de nueces en trozos, el polvo para hornear y el bicarbonato de sodio. \n" +
                "5. Vierta la mezcla en el molde y hornee por 60 minutos o hasta que al insertar un palillo este salga limpio. \n" +
                "6. Retire del horno y deje enfriar por 10 minutos antes de retirar el molde. \n" +
                "7. Espolvorear con azúcar glass y nueces, agregar el caramelo y decorar con rojadas de plátano. \n" +
                "8. Servir. \n";
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
        FloatingActionButton pdfView = findViewById(R.id.pdfView);
        pdfView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(PanPlatano.this, VisorPDF.class)
                        .putExtra(VisorPDF.FILE_PATHPP, templatePDF.getPdfFile().getAbsolutePath()));
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
        rows.add(new String[]{"1 1/4 tazas de harina."});
        rows.add(new String[]{"1 taza de nuez en trozos."});
        rows.add(new String[]{"2 cucharadas de polvo para hornear."});
        rows.add(new String[]{"1/2 cucharada de bicarbonato."});
        rows.add(new String[]{"1 taza de plátano machacado."});
        rows.add(new String[]{"1/2 taza de azúcar."});
        rows.add(new String[]{"1/3 de taza de aceite."});
        rows.add(new String[]{"1/3 de taza de leche."});
        rows.add(new String[]{"2 huevos."});
        rows.add(new String[]{"azúcar glass para espolvorear."});
        rows.add(new String[]{"1 plátano en rodajas para decorar."});
        rows.add(new String[]{"100 grs de nuez en trozos."});
        rows.add(new String[]{"caramelos."});
        //Return the row with the contents of the table
        return rows;
    }
}
