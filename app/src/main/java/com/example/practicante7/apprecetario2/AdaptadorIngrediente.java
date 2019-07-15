package com.example.practicante7.apprecetario2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import java.util.List;


public class AdaptadorIngrediente extends RecyclerView.Adapter <AdaptadorIngrediente.IngredientesViewHolder>{

    //region VARIABLES
    private List <Ingredientes> list;
    private  OnItemClick onItemClick1;
    //endregion

    //region Adapter ConstructorIngredients
    //The constructor is built to the AdapterIngredients to initialize the variables
    AdaptadorIngrediente(List<Ingredientes> list, OnItemClick onItemClick1) {
        this.list = list;
        this.onItemClick1 = onItemClick1;
    }
    //endregion
    @SuppressLint("InflateParams")
    @NonNull

    @Override

    // Create the onCreateViewHolder method where the list will be sent
    // that contains our images.
    public IngredientesViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new IngredientesViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list,null,false));
    }


    // It shows how to click on the image redirects to another activity
    @Override
    public void onBindViewHolder(@NonNull IngredientesViewHolder holder, int i) {
        final Ingredientes ingrediente = list.get(i);
        holder.imageBtn.setImageResource(ingrediente.getImage());
        holder.imageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick1.itemClick(ingrediente); //Used when refreshing the image / activity
            }
        });

    }

    @Override
    public int getItemCount()
    {
        return list.size(); // It is created to be adjustable to the resolution of the screen, when the list is displayed
    }
    class IngredientesViewHolder extends RecyclerView.ViewHolder {
        // Show the images of the ingredients in the activity
        ImageButton imageBtn;
        IngredientesViewHolder(View itemView)
        {
            super(itemView);
            imageBtn = itemView.findViewById(R.id.imageBtn);
        }
    }

   public interface OnItemClick {
        void itemClick(Ingredientes ingredientes);
   }
}