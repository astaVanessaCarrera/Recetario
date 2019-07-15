package com.example.practicante7.apprecetario2;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class AgregarReceta extends AppCompatActivity {

    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar_receta);

        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //region PAN PLÁTANO
        ImageButton image1 = findViewById(R.id.bntImageP);
        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //In the onClick method the button / image of each specific recipe will be called
                //where when clicking, it will redirect to the next screen
                //in which the image of said dish will be shown
                // as well as the necessary ingredients and steps
                Intent intent = new Intent(AgregarReceta.this, PanPlatano.class);
                startActivity(intent);
            }
        });
        //endregion

        //region CREPA DE NUTELLA
        ImageButton image2 = findViewById(R.id.bntImageC);
        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //In the onClick method the button / image of each specific recipe will be called
                //where when clicking, it will redirect to the next screen
                //in which the image of said dish will be shown
                // as well as the necessary ingredients and steps
                Intent intent = new Intent(AgregarReceta.this, CrepaNutella.class);
                startActivity(intent);
            }
        });
        //endregion

        //region GELATINA CAJETA
            ImageButton image3 = findViewById(R.id.bntImageG);
            image3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //In the onClick method the button/image of each specific recipe will be called
                    //where when clicking, it will redirect to the next screen
                    //in which the image of said dish will be shown
                    // as well as the necessary ingredients and steps
                    Intent intent = new Intent(AgregarReceta.this, GelatinaCajeta.class);
                    startActivity(intent);
                }
            });
          //endregion

        //region botones FAB
        FloatingActionButton fab1 = findViewById(R.id.fab1);
        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "¿Agregar a tu recetario?", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton fab2 = findViewById(R.id.fab2);
        fab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "¿Deseas añadir esta receta a tu recetario?", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        FloatingActionButton fab3 = findViewById(R.id.fab3);
        fab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Receta añadida a tu recetario", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        //endregion

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
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        swipeRefreshLayout.setRefreshing(false);
                    }
                }, 2000); // You add the time to know how much you can run the activity
            }
        });
    }
    public boolean onOptionsItemSelected (MenuItem item){
        if (item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }
}

