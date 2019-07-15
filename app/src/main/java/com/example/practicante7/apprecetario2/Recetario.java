package com.example.practicante7.apprecetario2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import java.util.Random;

import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class Recetario extends AppCompatActivity {

    //regionViews
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    //endregion

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recetario);

        swipeRefreshLayout = findViewById(R.id.swipe);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new ATask().execute();
            }
        });

        //Stylize the timer for what the activity loads to look "attractive to the eye"
        swipeRefreshLayout.setColorSchemeResources(R.color.colorRosa);
        //region recyclerView
        recyclerView = findViewById(R.id.RecyclerID);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setHasFixedSize(true);

        new ATask().execute();
        //endregion
    }

    //region llenar ingredientes
    public List<Ingredientes> llenarIngredientes() {
        ArrayList<Ingredientes> list;  //Declare the ArrayList
        list = new ArrayList<>();       //Initialize the list

        //Fill the list with the images to show of the main ingredients
        list.add(new Ingredientes(R.drawable.arroz));
        list.add(new Ingredientes(R.drawable.brocoli));
        list.add(new Ingredientes(R.drawable.calabaza));
        list.add(new Ingredientes(R.drawable.camaron));     //to call the ArrayList <Ingredients>
        list.add(new Ingredientes(R.drawable.res));
        list.add(new Ingredientes(R.drawable.pollo));
        list.add(new Ingredientes(R.drawable.cerdo));
        list.add(new Ingredientes(R.drawable.coco));

        return list;
    }
    //endregion

    //region Clase AsynTask
    //Declare the class with which the asynchronous tasks of the activity will be fulfilled
    @SuppressLint("StaticFieldLeak")
    class ATask extends AsyncTask<Void, Void, List<Ingredientes>> {
        @Override
        //Execute the part of the swipeRefresh when starting the activity
        protected void onPreExecute() {
            swipeRefreshLayout.setRefreshing(true);
        }

        @Override
        //Load the swipe, have a counter and when it stops
        // can load the list of images
        protected List<Ingredientes> doInBackground(Void... strings) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //Return the Array with the list of images at the end of the Swipe time cycle
            return llenarIngredientes();
        }

        @Override
        protected void onPostExecute(List<Ingredientes> s) {
            //Stop the refresh of the activity after
            //finish your cycle of time
            swipeRefreshLayout.setRefreshing(false);

            recyclerView.setAdapter(new AdaptadorIngrediente(s, new AdaptadorIngrediente.OnItemClick() {
                @Override
                public void itemClick(Ingredientes ingredientes) {
                    Intent intent = new Intent(Recetario.this, Platillos.class);
                    startActivity(intent);
                }
            }));
        }
    }
    //endregion
}
