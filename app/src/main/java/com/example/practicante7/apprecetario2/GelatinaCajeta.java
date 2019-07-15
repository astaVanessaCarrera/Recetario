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

public class GelatinaCajeta extends AppCompatActivity {

    //the class header is called (TemplatePDF)
    //to add the title to the table of contents
    private String[] header = {"Ingredientes"};
    //the class is called and a variable is created, in order to use it
    private TemplatePDF templatePDF;
    //The Urls of the images that are displayed in the activity are assigned
    private String[] mImageUrls = new String[]{
            "https://pasteleriasmarisa.com.mx/dyn/wp-content/uploads/2015/07/Gelatina-Cajeta-1.jpg",
            "https://www.cocinavital.mx/wp-content/uploads/2018/05/flotatina-queso-cajeta.jpg",
            "https://i.pinimg.com/originals/12/2b/62/122b621deadbe629b5b13ebda3b99baa.jpg"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gelatina_cajeta);
        //region Template
        //with the declaration of the variable of the TemplatePDF class
        //you can fill the PDF with the recipe data here
        //of the corresponding activity
        templatePDF = new TemplatePDF(getApplicationContext());
        templatePDF.openDocument();
        templatePDF.addMetaData("Ingrendiente");  //Add the title to the table
        //Add the title of the recipe, as well as the subtitle (author) and the date on which it was generated
        templatePDF.addTitle("Receta de Gelatina de Cajeta", "Kiwilimón", "12/07/2019");
        //Declare a local variable with a small text with the description that follows the table
        String shorText = "Elementos de la receta";
        //Add the variable with the description to the document
        templatePDF.addParagraph(shorText);
        templatePDF.createTable(header, getIngs()); //Create the table
        //Declare another local variable where the text that will go inside the table of contents will come
        String longText = "1. Remoja la grenetina en 1/4 de agua fría por 10 minutos. Agrega 1/2 taza de agua caliente. \n" +
                "2. Agrega todos los ingredientes en la licuadora y licúa hasta que quede una mezcla homogénea. \n" +
                "3. Vacía en un molde de gelatina y mételo al refriguerador por 45 minutos o hasta que esté cuajada. \n" +
                "4. Desmolda la gelatina en un plato bonito. \n";

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
                startActivity(new Intent(GelatinaCajeta.this, VisorPDF.class)
                        .putExtra(VisorPDF.FILE_PATHGC, templatePDF.getPdfFile().getAbsolutePath()));
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
        rows.add(new String[]{"1/2 taza de cajeta quemada."});
        rows.add(new String[]{"1/2 lata de leche condensada (Lechera)."});
        rows.add(new String[]{"2 sobres de grenetina."});
        rows.add(new String[]{"1 taza de leche."});
        rows.add(new String[]{"1/2 taza de crema."});
        rows.add(new String[]{"1/2 taza de agua caliente."});
        rows.add(new String[]{"1/4 de taza de agua fría."});
        //Return the row with the contents of the table
        return rows;
    }
}
