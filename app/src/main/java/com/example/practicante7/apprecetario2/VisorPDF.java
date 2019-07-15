package com.example.practicante7.apprecetario2;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.github.barteksc.pdfviewer.PDFView;
import com.itextpdf.text.DocumentException;

import java.io.File;
import java.util.ArrayList;

//Crear la clase/actividad para la visualización de los archivos PDF
public class VisorPDF extends AppCompatActivity {

    //Crear las rutas de para los archivos que se van a visualizar
    public static final String FILE_PATHCB = "FILE_PATHCB";
    public static final String FILE_PATHPC = "FILE_PATHPC";
    public static final String FILE_PATHCC = "FILE_PATHCC";
    public static final String FILE_PATHCD = "FILE_PATHCD";
    public static final String FILE_PATHPFH = "FILE_PATHPFH";
    public static final String FILE_PATHPP = "FILE_PATHPP";
    public static final String FILE_PATHCN = "FILE_PATHCN";
    public static final String FILE_PATHGC = "FILE_PATHGC";

    //TODO: Varibles declaradas que no se utilizarán
//    private File file;
//   public TemplatePDF templatePDF;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visor_pdf);
        //Se crea una variable para la visualización del PDF
        PDFView pdfView = findViewById(R.id.pdfView);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

     //region PDF Crema de Brocoli
        if (getIntent().getExtras()!= null
                //Mostrar la ruta del documento para visualizar el PDF
                && getIntent().getExtras().containsKey(FILE_PATHCB)){
            //Crear el PDF desde el archivo, dándole la ruta
            pdfView.fromFile(new File(getIntent().getExtras().getString(FILE_PATHCB)))
                    .enableSwipe(true) //Poder deslizar la pantalla al conetener el documento
                    .swipeHorizontal(false)  //Visualizar la ruta verticalmente
                    .enableDoubletap(true)   //Dar la opción de aumentar la pantalla (Documento) dándole doble toque
                    .enableAntialiasing(true)
                    .load();
        }
      //endregion
     //region PDF Pay de Calabaza
        if (getIntent().getExtras()!= null
                //Mostrar la ruta del documento para visualizar el PDF
                && getIntent().getExtras().containsKey(FILE_PATHPC)){
            //Crear el PDF desde el archivo, dándole la ruta
            pdfView.fromFile(new File(getIntent().getExtras().getString(FILE_PATHPC)))
                    .enableSwipe(true)  //Poder deslizar la pantalla al conetener el documento
                    .swipeHorizontal(false)  //Visualizar la ruta verticalmente
                    .enableDoubletap(true)  //Dar la opción de aumentar la pantalla (Documento) dándole doble toque
                    .enableAntialiasing(true)
                    .load();
        }
      //endregion
     //region PDF Camarones al Coco
        if (getIntent().getExtras()!= null
                //Mostrar la ruta del documento para visualizar el PDF
                && getIntent().getExtras().containsKey(FILE_PATHCC)){
            //Crear el PDF desde el archivo, dándole la ruta
            pdfView.fromFile(new File(getIntent().getExtras().getString(FILE_PATHCC)))
                    .enableSwipe(true)  //Poder deslizar la pantalla al conetener el documento
                    .swipeHorizontal(false)  //Visualizar la ruta verticalmente
                    .enableDoubletap(true)  //Dar la opción de aumentar la pantalla (Documento) dándole doble toque
                    .enableAntialiasing(true)
                    .load();
        }
     //endregion
     //region PDF Camarones a la Diabla
        if (getIntent().getExtras()!= null
                //Mostrar la ruta del documento para visualizar el PDF
                && getIntent().getExtras().containsKey(FILE_PATHCD)){
            //Crear el PDF desde el archivo, dándole la ruta
            pdfView.fromFile(new File(getIntent().getExtras().getString(FILE_PATHCD)))
                    .enableSwipe(true)  //Poder deslizar la pantalla al conetener el documento
                    .swipeHorizontal(false)  //Visualizar la ruta verticalmente
                    .enableDoubletap(true)  //Dar la opción de aumentar la pantalla (Documento) dándole doble toque
                    .enableAntialiasing(true)
                    .load();
        }
     //endregion
     //region PDF Pollo a las Finas Hierbas
        if (getIntent().getExtras()!= null
                //Mostrar la ruta del documento para visualizar el PDF
                && getIntent().getExtras().containsKey(FILE_PATHPFH)){
            //Crear el PDF desde el archivo, dándole la ruta
            pdfView.fromFile(new File(getIntent().getExtras().getString(FILE_PATHPFH)))
                    .enableSwipe(true)  //Poder deslizar la pantalla al contener el documento
                    .swipeHorizontal(false)  //Visualizar la ruta verticalmente
                    .enableDoubletap(true)  //Dar la opción de aumentar la pantalla (Documento) dándole doble toque
                    .enableAntialiasing(true)
                    .load();
        }
     //endregion
        // region PDF Pan de plátano
        if (getIntent().getExtras()!= null
                //Mostrar la ruta del documento para visualizar el PDF
                && getIntent().getExtras().containsKey(FILE_PATHPP)){
            //Crear el PDF desde el archivo, dándole la ruta
            pdfView.fromFile(new File(getIntent().getExtras().getString(FILE_PATHPP)))
                    .enableSwipe(true)  //Poder deslizar la pantalla al contener el documento
                    .swipeHorizontal(false)  //Visualizar la ruta verticalmente
                    .enableDoubletap(true)  //Dar la opción de aumentar la pantalla (Documento) dándole doble toque
                    .enableAntialiasing(true)
                    .load();
        }
        //endregion
        //region PDF Crepa de Nutella
        if (getIntent().getExtras()!= null
                //Mostrar la ruta del documento para visualizar el PDF
                && getIntent().getExtras().containsKey(FILE_PATHCN)){
            //Crear el PDF desde el archivo, dándole la ruta
            pdfView.fromFile(new File(getIntent().getExtras().getString(FILE_PATHCN)))
                    .enableSwipe(true)  //Poder deslizar la pantalla al contener el documento
                    .swipeHorizontal(false)  //Visualizar la ruta verticalmente
                    .enableDoubletap(true)  //Dar la opción de aumentar la pantalla (Documento) dándole doble toque
                    .enableAntialiasing(true)
                    .load();
        }
        //endregion
        //region PDF Gelatina de Cajeta
        if (getIntent().getExtras()!= null
                //Show the document path to view the PDF
            && getIntent().getExtras().containsKey(FILE_PATHGC)){
            //Create the PDF from the file, giving it the path
            pdfView.fromFile(new File(getIntent().getExtras().getString(FILE_PATHGC)))
                    .enableSwipe(true) //To be ebale to slide screen when containing the document
                    .swipeHorizontal(false) //Visualize the route vertically
                    .enableDoubletap(true) //Give the option to increase the screen (Document) giving double touch
                    .enableAntialiasing(true)
                    .load();
        }
        //endregion
    }
    public boolean onOptionsItemSelected (MenuItem item){
        if (item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}
