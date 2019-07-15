package com.example.practicante7.apprecetario2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class PolloFH extends AppCompatActivity {
    //the class header is called (TemplatePDF)
    //to add the title to the table of contents
    private String[] header = {"Ingredientes"};
    //the class is called and a variable is created, in order to use it
    private TemplatePDF templatePDF;
    //The Urls of the images that are displayed in the activity are assigned
    private String[] mImageUrls = new String[]{
            "https://d1doqjmisr497k.cloudfront.net/-/media/ducroses2018/recipes/800/pollo_con_esparragos_a_las_finas_hierbas_800.ashx?vd=20180709T162447Z&hash=68972A53C5998401450865479627D629CF926BE2",
            "http://i2.esmas.com/2014/06/06/656229/muslitos-de-pollo-en-ajo-y-cebolla-613x342.jpg",
            "http://revistalabarra.com/guia/files/classifieds/479-589.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pollo_fh);

    //region Template
        //with the declaration of the variable of the TemplatePDF class
        //you can fill the PDF with the recipe data here
        //of the corresponding activity
        templatePDF = new TemplatePDF(getApplicationContext());
        templatePDF.openDocument();
        templatePDF.addMetaData("Ingrendiente"); //Add the title to the table
        //Add the title of the recipe, as well as the subtitle (author) and the date on which it was generated
        templatePDF.addTitle("Receta de Pollo a las Finas Hierbas", "Kiwilimón", "30/01/2019");
        //Declare a local variable with a small text with the description that follows the table
        String shorText = "Elementos de la receta";
        //Add the variable with the description to the document
        templatePDF.addParagraph(shorText);
        templatePDF.createTable(header,getIngs()); //Create the table
        //Declare another local variable where the text that will go inside the table of contents will come
        String longText = "1. Colocar en una bolsa resellable el vinagre, el romero, la mostaza, ajo en polvo y el aceite de olivo. \n" +
                "2. Sazonar las pechugas de pollo y colocar en la marinada. Sellar la bolsa y agitar. \n" +
                "3. Marinar por al menos 3 horas o hasta 1 día. \n" +
                "4. Calentar un sartén, cuando esté caliente colocar las pechugas de pollo y cocinar aproximadamente 4 minutos de cada lado o hasta que estén bien cocidas.";
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
                startActivity(new Intent(PolloFH.this, VisorPDF.class)
                        .putExtra(VisorPDF.FILE_PATHPFH, templatePDF.getPdfFile().getAbsolutePath()));
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
        rows.add(new String[]{"4 pechugas de pollo sin hueso y sin piel."});
        rows.add(new String[]{"2 cucharadas de vinagre balsámico."});
        rows.add(new String[]{"2 cucharadas de romero fresco."});
        rows.add(new String[]{"2 cucharadas de mostaza con miel y vinagre Extra Special® ."});
        rows.add(new String[]{"1 cucharada de ajo en trozo McCormick® ."});
        rows.add(new String[]{"1/4 de taza de aceite de olivo extra virgen Extra Special®."});
        rows.add(new String[]{"sal y pimienta al gusto."});
        rows.add(new String[]{"bolsas de plástico ziplock."});
        //Return the row with the contents of the table
        return rows;
}
}
