package com.example.practicante7.apprecetario2;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.ShareActionProvider;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import java.util.ArrayList;

public class PayCalabaza extends AppCompatActivity {
    //the class header is called (TemplatePDF)
    //to add the title to the table of contents
    private String[] header = {"Ingredientes"};
    //the class is called and a variable is created, in order to use it
    private TemplatePDF templatePDF;
    //The Urls of the images that are displayed in the activity are assigned
    private String[] imageUrls = new String[]{
            "https://www.womenshealthlatam.com/wp-content/uploads/2018/07/Receta-de-pay-de-calabaza.jpg",
            "http://www.recetasdepostres.mx/wp-content/uploads/2013/10/Pay-de-calabaza-590x300.jpg",
            "https://cdn2.cocinadelirante.com/sites/default/files/styles/gallerie/public/images/2016/09/paycalabaza.jpg"
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_calabaza);

        //region Template
        //with the declaration of the variable of the TemplatePDF class
        //you can fill the PDF with the recipe data here
        //of the corresponding activity
        templatePDF = new TemplatePDF(getApplicationContext());
        templatePDF.openDocument();
        templatePDF.addMetaData("Ingrendiente"); //Add the title to the table
        //Add the title of the recipe, as well as the subtitle (author) and the date on which it was generated
        templatePDF.addTitle("Receta de Pay de Calabaza", "Kiwilimón", "15/01/2019");
        //Declare a local variable with a small text with the description that follows the table
        String shorText = "Elementos de la receta";
        //Add the variable with the description to the document
        templatePDF.addParagraph(shorText);
        templatePDF.createTable(header,getIngs()); //Create the table
        //Declare another local variable where the text that will go inside the table of contents will come
        String longText = "1. Pre caliente el horno a 180 grados centígrados. \n" +
                "2. Haga el relleno batiendo en una batidora el queso crema por 3-5 minutos. Agregue la calabaza y bata hasta que este incorporada. \n" +
                "3. Agregue el azúcar y la sal y continue batiendo. Agregue los huevos, la crema, y la mantequilla y continue batiendo. \n" +
                "4. Por último agregue la vainilla, la canela, y el jengibre y bata por 5 minutos más. \n" +
                "5. Vierta el relleno en la base para pay y hornee por 50 minutos o hasta que al insertar un palillo este salga limpio. Retire del horno. \n";
        //Add the text to the document
        templatePDF.addParagraph(longText);
        templatePDF.closeDocument();
        //endregion

        ViewPager viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, imageUrls);
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
                startActivity(new Intent(PayCalabaza.this, VisorPDF.class)
                        .putExtra(VisorPDF.FILE_PATHPC, templatePDF.getPdfFile().getAbsolutePath()));
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
        rows.add(new String[]{"1 paquete de queso crema (225 gramos c/u)."});
        rows.add(new String[]{"1 puré de calabaza."});
        rows.add(new String[]{"1 taza de azúcar."});
        rows.add(new String[]{"1 huevo."});
        rows.add(new String[]{"2 huevos."});
        rows.add(new String[]{"1 taza de crema half & half."});
        rows.add(new String[]{"1/4 de taza de mantequilla sin sal derretida."});
        rows.add(new String[]{"1/2 cucharadita de sal."});
        rows.add(new String[]{"1 cucharada de esencia de vainilla."});
        rows.add(new String[]{"1/2 cucharada de canela en polvo."});
        rows.add(new String[]{"1/2 cucharada de jengibre en polvo."});
        rows.add(new String[]{"1 base para pay pre hecho."});
        rows.add(new String[]{"cremas batidas para acompañar."});
        //Return the row with the contents of the table
        return rows;
    }
}