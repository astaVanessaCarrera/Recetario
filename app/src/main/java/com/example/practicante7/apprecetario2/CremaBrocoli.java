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

public class CremaBrocoli extends AppCompatActivity {
    //region
    //the class header is called (TemplatePDF)
    //to add the title to the table of contents
    private String[] header = {"Ingredientes"};
    //the class is called and a variable is created, in order to use it
    private TemplatePDF templatePDF;
    //The Urls of the images that are displayed in the activity are assigned
    private String[] mImageUrls = new String[]{
            "https://cdn.tudespensa.com/rep/a97f/imagenes/114674/29/crema-de-brocoli-y-manzanajpg.jpg",
            "https://okdiario.com/img/recetas/2017/06/09/crema-de-brocoli.jpg",
            "https://bonduelle.es/media/zoo/images/brocoli_d56901396a7addabfc9cb6645367b0c0.jpg"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crema_brocoli);

        //region Template
        //with the declaration of the variable of the TemplatePDF class
        //you can fill the PDF with the recipe data here
        //of the corresponding activity
        templatePDF = new TemplatePDF(getApplicationContext());
        templatePDF.openDocument();
        templatePDF.addMetaData("Ingrendiente");  //Add the title to the table
        //Add the title of the recipe, as well as the subtitle (author) and the date on which it was generated
        templatePDF.addTitle("Receta de Crema de Brocoli", "Kiwilimón", "18/01/2019");
        //Declare a local variable with a small text with the description that follows the table
        String shorText = "Elementos de la receta";
        //Add the variable with the description to the document
        templatePDF.addParagraph(shorText);
        templatePDF.createTable(header, getIngs()); //Create the table
        //Declare another local variable where the text that will go inside the table of contents will come
        String longText = "1. En una olla grande derrita la mantequilla a fuego medio. Dore en ella la cebolla por 10 minutos. \n" +
                "2. Agregue el brócoli y continúe cocinando por 1 minuto. \n" +
                "3. Agregue harina, sal y pimienta y mezcle con una cuchara de madera. Agregue de poco en poco el caldo de pollo y continúe cocinando hasta que hierva. \n" +
                "4. Baje el fuego, cubra la olla y cocine a fuego bajo por 10 minutos o hasta que el brócoli esté bien cocido. \n" +
                "5. De bache en bache licue la sopa en una licuadora sin la tapa central para que el vapor pueda salir y no explote la licuadora. \n" +
                "6. Regrese la sopa a la olla y agregue la crema. Caliente (no hierva) la sopa y sirva. \n";
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
                startActivity(new Intent(CremaBrocoli.this, VisorPDF.class)
                        .putExtra(VisorPDF.FILE_PATHCB, templatePDF.getPdfFile().getAbsolutePath()));
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
        rows.add(new String[]{"3 tazas de brócoli."});
        rows.add(new String[]{"2 cucharadas de mantequilla."});
        rows.add(new String[]{"1 cebolla finamente picada."});
        rows.add(new String[]{"3 cucharadas de harina."});
        rows.add(new String[]{"2 tazas de caldo de pollo hecho en casa o de lata."});
        rows.add(new String[]{"1 taza de crema."});
        rows.add(new String[]{"pimienta al gusto."});
        rows.add(new String[]{"sal al gusto."});
        //Return the row with the contents of the table
        return rows;
    }
}
