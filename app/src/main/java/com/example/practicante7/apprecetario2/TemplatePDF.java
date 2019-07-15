package com.example.practicante7.apprecetario2;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
//Crear la clase TemplatePDF para el desarrollo del documento
class TemplatePDF {

    //Declar variables a utilizar
    private Context context;
    private File pdfFile; //Declarar el archivo
    private Document document; //Documento
    private Paragraph paragraph;
    //Declarar el tipo, tamaño y forma de letra a usar en el documento
    private Font fTitle = new Font(Font.FontFamily.TIMES_ROMAN, 20, Font.BOLD);
    private Font fSubTitle = new Font(Font.FontFamily.TIMES_ROMAN, 15, Font.BOLD);
    private Font fText = new Font(Font.FontFamily.TIMES_ROMAN, 12, Font.BOLD);
    TemplatePDF(Context context) {
        this.context = context;
    }

    //region Método que abre el documento PDF
    //Crear el método para abrir el documento
    //en donde se instanciará el archcivo a visualizar
    void openDocument(){

        try {
            createFile();
            document = new Document(PageSize.A4);
            PdfWriter pdfWriter = PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();
        }catch (Exception e){
            Log.e("openDocument", e.toString());
        }
    }
    //endregion

    //region CrerarArchivo
    private void createFile() throws IOException {
        File folder = new File(context.getCacheDir(), "PDF");

        //Verificar que el archivo esté en la carpeta existente
        if (!folder.exists())
            folder.mkdirs();
        //Llamar al archivo PDF ya después de que verificó su existencia
        pdfFile = new File(folder, "TemplatePDF.pdf");
        pdfFile.createNewFile();
    }
    //endregion

    //region Nombre del documento
    void addMetaData(String titulo){
        //Añadir el título al documento
        document.addTitle(titulo);
    }
    //endregion

    //region Cerrar el documento
    void closeDocument(){
         document.close();
    }
    //endregion

    //region Método con los títulos, subtítulos y la fecha del documento
    void addTitle(String titulo, String subTitulo, String date){
        //Se pone un try para añadir ahí
        //los formatos de los párrafos
        //del título, subtítulo y la fecha
        try {
        paragraph = new Paragraph();
        addChildP(new Paragraph(titulo, fTitle));
        addChildP(new Paragraph(subTitulo, fSubTitle));
        addChildP(new Paragraph("Generado:" + date, fTitle));
        //Por cuestión de estética se añade un espacio entre cada párrafo
        paragraph.setSpacingAfter(30);
        document.add(paragraph);
        }catch (Exception e){
            Log.e("addTitle", e.toString());
        }
    }
    //endregion

    //region Cuerpo de documento
    private void addChildP(Paragraph childParagrapgh){
        //Forma en la que está acomodado el texto del documento
        //Estará alineado en el centro
        childParagrapgh.setAlignment(Element.ALIGN_CENTER);
        paragraph.add(childParagrapgh);
    }
    //endregion

    //region Agregar el texto
    void addParagraph(String text){
        //Añadir el try para poder agregar el texto al documento
        try {
            //Se manda llamar la variable que contendrá el desarrollo del documento
        paragraph = new Paragraph(text, fText);
        //Se agregan espacios para que tenga cuerpo y no verse empalmado
        paragraph.setSpacingAfter(10);
        paragraph.setSpacingBefore(10);
        document.add(paragraph);
        }catch (Exception e){
            Log.e("addParagraph", e.toString());
        }
    }
    //endregion

    //region Método en donde se crea la tabla de contenido
    //Dentro del método irá el encabezado que contendrá el título de la tabla
    //al igual que el Array en donde vendrá la lista de los ingredientes
    void createTable(String[] hearder, ArrayList<String[]> ings) {
        //dentro del try vendrá el texto del documento
        try {
        paragraph = new Paragraph();
        paragraph.setFont(fText);
        //Se crea la tabla de contenido
        PdfPTable pdfPTable = new PdfPTable(hearder.length);
        //le damos el porcentaje de ancho que tendrá la tabla
        pdfPTable.setWidthPercentage(60);
        //generamos las celdas que contendrá la tabla
            //y la inicializamos
        PdfPCell pdfPCell;
        int mIndexC = 0;
        //le damos la condición al while
        while (mIndexC < hearder.length){
            //indicamos el texto (subtitle) que irá en la tabla de contenido
            pdfPCell = new PdfPCell(new Phrase(hearder [mIndexC ++], fSubTitle));
            //le asignamos la posición en cómo irá la tabla de contenido
            pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
            //le agregamos color a la parte donde irá el título de la tabla
            pdfPCell.setBackgroundColor(BaseColor.LIGHT_GRAY);
            //Añadimos la tabla de contenido al documento PDF
            pdfPTable.addCell(pdfPCell);
        }
        //En estos ciclos aninados de indican el cómo se llenará la tabla de contenido
        for (int indexR = 0; indexR < ings.size(); indexR++){
            String[] row = ings.get(indexR);
            for (mIndexC = 0; mIndexC < hearder.length; mIndexC ++){
                pdfPCell = new PdfPCell(new Phrase(row [mIndexC]));
                pdfPCell.setHorizontalAlignment(Element.ALIGN_CENTER);
                pdfPCell.setFixedHeight(30);
                pdfPTable.addCell(pdfPCell);
            }
        }
        //El texto anterior al momento de crear la tabla y las celdad de contenido
            //se añadirán al texto en general, el cual se agregará a la tabla de contienido
            //añadida al documento PDF
        paragraph.add(pdfPTable);
        document.add(paragraph);
        }catch (Exception e){
            Log.e("createTable", e.toString());
        }
    }
    //endregion

    File getPdfFile() {
        return pdfFile;
    } //retorna el archivo en PDF

    //TODO: Este código no se utilizará
    //    public void viewPDF(Activity activity){
//        if (pdfFile.exists()){
//            Uri uri = Uri.fromFile(pdfFile);
//            Intent intent= new Intent(Intent.ACTION_VIEW);
//            intent.setDataAndType(uri, "application/pdf");
//            try {
//                activity.startActivity(intent);
//            } catch (ActivityNotFoundException e){
//                activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=com.adobe.reader&hl=es")));
//                Toast.makeText(activity.getApplicationContext(), "No cuentas con una app para visualizar archivos PDF", Toast.LENGTH_LONG)
//                        .show();
//            }
//        }
//        else {
//            Toast.makeText(activity.getApplicationContext(), "No se encontró el archivo PDF", Toast.LENGTH_LONG).show();
//        }
//    }
}
