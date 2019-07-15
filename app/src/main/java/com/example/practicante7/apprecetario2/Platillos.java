package com.example.practicante7.apprecetario2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

//Create this activity in order to observe a "gallery"
// with the dishes to be displayed and so the user can access another activity
// in order to observe the recipe step by step of the dish to choose
public class Platillos extends AppCompatActivity {

      SwipeRefreshLayout swipeRefreshLayout; //The Swipe is declared globally
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_platillos);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab1 = findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(Platillos.this, AgregarReceta.class);
                startActivity(intent);
            }
        });

        if (getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        swipeRefreshLayout = findViewById(R.id.swipe); //The view in the activity is sought
        //Add the fortmate to choose (in this case color),
        // it can be from one or how many colors to choose
        swipeRefreshLayout.setColorSchemeResources(R.color.colorRosa);
        //Adding the setOnRefreshListener method will help when it was selected
        //will invoke the onRefresh method
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //region PolloFH activity
                //Declare locally the variable that will be sent to
                //all the button inside the activity
                ImageButton image1 = findViewById(R.id.bntImage1);
                image1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //In the onClick method the button / image of each specific recipe will be called
                        //where when clicking, it will redirect to the next screen
                        //in which the image of said dish will be shown
                        // as well as the necessary ingredients and steps
                        Intent intent = new Intent(Platillos.this, PolloFH.class);
                        startActivity(intent);
                    }
                });
                //endregion
                //region Iniciamos la actividad CamaronesCoco
                //Declaring locally the variable that will be sent to
                //call the button within the activity
                ImageButton image3 = findViewById(R.id.bntImage3);
                image3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //The onClick method will call the button / image of each specific recipe
                        //where clicking will redirect to the next screen
                        //in which the image of said dish will be displayed
                        // like the ingredients and necessary steps
                        Intent intent = new Intent(Platillos.this, CamaronesCoco.class);
//  TODO:              onItemClick1.itemClick(ingrediente); //Se utiliza al momento de refrescar la imagen/actividad
                        startActivity(intent);
                    }
                });
                //endregion
                //region Iniciamos la actividad CamaronesDiabla
                //Declarar de manera local la variable que mandará a
                //llamar el botón dentro de la actividad
                ImageButton image4 = findViewById(R.id.bntImage4);
                image4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //The onClick method will call the button / image of each specific recipe
                        //where clicking will redirect to the next screen
                        //in which the image of said dish will be displayed
                        // like the ingredients and necessary steps
                        Intent intent = new Intent(Platillos.this, CamaronesDiabla.class);
                        startActivity(intent);
                    }
                });
                //endregion
                //region Iniciamos la actividad PayCalabaza
                //Declaring locally the variable that will be sent to
                //call the button within the activity
                ImageButton image5 = findViewById(R.id.bntImage5);
                image5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //The onClick method will call the button / image of each specific recipe
                        //where clicking will redirect to the next screen
                        //in which the image of said dish will be displayed
                        // like the ingredients and necessary steps
                        Intent intent = new Intent(Platillos.this, PayCalabaza.class);
                        startActivity(intent);
                    }
                });
                //endregion
                //region Iniciamos la actividad CremaBrocoli
                //Declaring locally the variable that will be sent to
                //call the button within the activity
                ImageButton image6 = findViewById(R.id.bntImage6);
                image6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //The onClick method will call the button / image of each specific recipe
                        //where clicking will redirect to the next screen
                        //in which the image of said dish will be displayed
                        // like the ingredients and necessary steps
                        Intent intent = new Intent(Platillos.this, CremaBrocoli.class);
                        startActivity(intent);
                    }
                });
                //endregion

                //Adding the postDelayed in the Handler, makes this
                // be added to the message queue, to be executed
                // after the specified time elapses
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 3000); // You add the time to know how much you can run the activity
            }
        });
    }
    public boolean onOptionsItemSelected (MenuItem item){
        if (item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

}

