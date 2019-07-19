package com.example.practicante7.apprecetario2;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class SignUp extends AppCompatActivity implements View.OnClickListener {
    EditText txtEmail, txtUserN, txtPass;
    Button btnRegistrar;

    private String[] mImageUrls = new String[]{
            "https://gourmetdemexico.com.mx/wp-content/uploads/2017/01/field_image_shutterstock_413326144-965x667.jpg",
            "http://i2.esmas.com/buttons/2017/06/05/55776/istock-104704117.jpg",
            "https://www.baarty.com/articulos/wp-content/uploads/2019/01/restaurante-gourmet-3.jpg"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

//        Toolbar toolbar =  findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        ViewPager viewPager = findViewById(R.id.view_pager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(this, mImageUrls);
        viewPager.setAdapter(viewPagerAdapter);

        txtEmail = findViewById(R.id.txtEmail);
        txtUserN = findViewById(R.id.txtUserN);
        txtPass = findViewById(R.id.txtPass);
        btnRegistrar = findViewById(R.id.btnRegistrar);

        //region GO TO LOGIN
        TextView linkToLo = findViewById(R.id.link_to_login);
           linkToLo.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent mainIntent = new Intent(SignUp.this, Login.class);
                   startActivity(mainIntent);
               }
           });
        //endregion
        btnRegistrar.setOnClickListener(this);
        if (getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    public boolean onOptionsItemSelected (MenuItem item){
        if (item.getItemId()== android.R.id.home)
            finish();
        return super.onOptionsItemSelected(item);
    }

    //region User Registration
    public void onClick(View view) {
        //region VARIABLES
        final String email = txtEmail.getText().toString();
        final String username = txtUserN.getText().toString();
        final String password = txtPass.getText().toString();
        //endregion
        Response.Listener<String> respoListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    if(email.length() == 0){
                        Toast.makeText(getApplicationContext(),
                                        "¡Debes ingregsar un correo electrónico!", Toast.LENGTH_LONG).show();
                    }
                    if (username.length() ==0 ){
                       Toast.makeText(getApplicationContext(),
                               "¡Debes ingregar un nombre de usuario!", Toast.LENGTH_LONG).show();
                    }
                    if (password.length() == 0){
                        Toast.makeText(getApplicationContext(),
                                "¡Debes ingresar una contraseña!", Toast.LENGTH_LONG).show();
                    }
                    JSONObject jsonResponse = new JSONObject(response);
                    boolean success = jsonResponse.getBoolean("success");
                    if (success && email.length() != 0 && username.length() != 0 && password.length() !=0 ){
                        Toast.makeText(getApplicationContext(),
                                "¡Usuario registrado!", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(SignUp.this, Login.class);
                        SignUp.this.startActivity(intent);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(SignUp.this);
                        builder.setMessage("¡Error al registrarse!")
                                .setNegativeButton("Intentar de nuevo", null)
                                .create().show();
                    }
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        };
        RegisterRequest registerRequest = new RegisterRequest(email, username, password, respoListener);
        RequestQueue queue = Volley.newRequestQueue(SignUp.this);
           queue.add(registerRequest);
    }
}
