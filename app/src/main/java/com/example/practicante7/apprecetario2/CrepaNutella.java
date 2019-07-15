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

public class CrepaNutella extends AppCompatActivity {

    //the class header is called (TemplatePDF)
    //to add the title to the table of contents
    private String[] header = {"Ingredientes"};
    //the class is called and a variable is created, in order to use it
    private TemplatePDF templatePDF;
    //The Urls of the images that are displayed in the activity are assigned
    private String[] mImageUrls = new String[]{
            "https://media-cdn.tripadvisor.com/media/photo-s/08/29/79/00/margarita.jpg",
            "https://www.gtoviaja.com/wp-content/uploads/2013/12/crepas10.jpg",
            "http://annaspasteleria.com/wp-content/uploads/2015/03/13-459-post/IMG_7029editweb.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crepa_nutella);
        //region Template
        //with the declaration of the variable of the TemplatePDF class
        //you can fill the PDF with the recipe data here
        //of the corresponding activity
        templatePDF = new TemplatePDF(getApplicationContext());
        templatePDF.openDocument();
        templatePDF.addMetaData("Ingrendiente");  //Add the title to the table
        //Add the title of the recipe, as well as the subtitle (author) and the date on which it was generated
        templatePDF.addTitle("Receta de Crepas de Nutella con Fresas", "Kiwilimón", "10/07/2019");
        //Declare a local variable with a small text with the description that follows the table
        String shorText = "Elementos de la receta";
        //Add the variable with the description to the document
        templatePDF.addParagraph(shorText);
        templatePDF.createTable(header, getIngs()); //Create the table
        //Declare another local variable where the text that will go inside the table of contents will come
        String longText = "1. Se ponen todos los ingredientes en la licuadora y se licuan. \n" +
                "2. Se calienta un sartén redondo pequeño con unas gotitas de aceite (se puede distribuir por el sartén con una servilleta para que no quede grasoso). \n" +
                "3. Con una cuchara honda se sirve un poco de la mezcla en el sartén, asegurando que cubra todo el sartén y se deja cocinar unos 45 segundos hasta que este doradita por debajo. \n" +
                "4. Se repite lo mismo hasta hacer todas las crepas. Se acomodan en un platón engrasado dobladas en cuatro. \n" +
                "5. Se pone la nutella y fresas en la crepa. \n";
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
                startActivity(new Intent(CrepaNutella.this, VisorPDF.class)
                        .putExtra(VisorPDF.FILE_PATHCN, templatePDF.getPdfFile().getAbsolutePath()));
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
        rows.add(new String[]{"1 1/2 tazas de leche."});
        rows.add(new String[]{"1 taza de harina."});
        rows.add(new String[]{"2 huevos."});
        rows.add(new String[]{"1 cucharada de aceite."});
        rows.add(new String[]{"1/2 de cucharadita de sal."});
        rows.add(new String[]{"2 cucharaditas de azúcar."});
        rows.add(new String[]{"nutella."});
        rows.add(new String[]{"fresas partidas."});
        //Return the row with the contents of the table
        return rows;
    }
}
