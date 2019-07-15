package com.example.practicante7.apprecetario2;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

//Create the RegisterRequest class to store the
//variables and the URL of the Register where the data is stored
public class RegisterRequest extends StringRequest{

    //region VARIABLES
    private static final String REGISTER_REQUEST_URL = "http://localhost/REGISTER_RECIPE.php";
    private Map<String, String> params;
    //endregion

    public RegisterRequest (String email, String username, String password, Response.Listener<String> listener){
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        //Variables are created to be able to pass them as parameters
        //in this case the email, user and password will be used
        params = new HashMap<>();
        params.put("email", email);
        params.put("username", username);
        params.put("password", password);

    }
    public Map<String, String> getParams(){
        return params;
    }
}

