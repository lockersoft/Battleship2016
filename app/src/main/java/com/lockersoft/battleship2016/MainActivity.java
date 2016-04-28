package com.lockersoft.battleship2016;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import com.android.volley.*;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

interface ServerRequests{
  public void ProcessResponse( String command, String response );
}

public class MainActivity extends AppCompatActivity implements ServerRequests{

  String apiKey = "&appid=a11b9eebb90f64365d39a253a845c564";
  String weatherURL = "http://api.openweathermap.org/data/2.5/weather?lat=47.67&lon=-117.48";

  String loginUrl = "http://battlegameserver.com/api/v1/login";
  String getAllUsersUrl = "http://battlegameserver.com/api/v1/all_users.json";

  String username = "dave.jones@scc.spokane.edu";
  String password = "dljones42";

  Button loginButton;
  EditText edtUsername;
  EditText edtPassword;
  ServerRequest sr;

  @Override
  protected void onCreate( Bundle savedInstanceState ){
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_main );

    loginButton = (Button) findViewById( R.id.btnLogin );
    edtUsername = (EditText) findViewById( R.id.edtUsername );
    edtPassword = (EditText) findViewById( R.id.edtPassword );
    // VOLLEY
    RequestQueue queue = Volley.newRequestQueue( this );
    sr = new ServerRequest( this, "LOGIN", username, password, loginUrl, queue );

    //  sr.setCommand( "GetUsers" );
    //   sr.setUrl( getAllUsersUrl );
    //  sr.makeRequest( "GETUSERS" );
  }

  public void clickLogin( View v ){
    password = edtPassword.getText().toString();
    username = edtUsername.getText().toString();
    sr.setUsername( username );
    sr.setPassword( password );
    sr.makeRequest( "LOGIN" );
  }

  private void processLogin( String response ){
    // Parse into an JSON Object

    try{
      JSONObject jsonObject = new JSONObject( response );
      User user = new User();
      user.setId( jsonObject.getInt( "id" ) );
      user.setFirst_name( jsonObject.getString( "first_name" ) );
      user.setLast_name( jsonObject.getString( "last_name" ) );
      user.setOnline( jsonObject.getBoolean( "online" ) );
      Log.i( "BattleShip", user.toString() );
    } catch( JSONException e ){
      e.printStackTrace();
    }
  }

  @Override
  public void ProcessResponse( String command, String response ){
    switch( command ){
      case "LOGIN":
        Log.i( "BattleShip", "LOGIN --- " + response );
        processLogin( response );
        break;

      case "GETUSERS":
        Log.i( "BattleShip", "GETUSERS --- " + response );
        break;

      case "STARTGAME":
        Log.i( "BattleShip", "STARTGAME --- " + response );
        break;

      default:
        break;
    }
  }
}
