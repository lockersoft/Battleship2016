package com.lockersoft.battleship2016;

import android.content.Intent;
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

public class MainActivity extends BaseActivity implements ServerRequests {

//  String apiKey = "&appid=a11b9eebb90f64365d39a253a845c564";
//  String weatherURL = "http://api.openweathermap.org/data/2.5/weather?lat=47.67&lon=-117.48";

  String username = "dave.jones@scc.spokane.edu";
  String password = "dljones42";

  Button loginButton;
  static Button userPreferences;
  EditText edtUsername;
  EditText edtPassword;

  @Override
  protected void onCreate( Bundle savedInstanceState ){
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_main );

    loginButton = (Button) findViewById( R.id.btnLogin );
    edtUsername = (EditText) findViewById( R.id.edtUsername );
    edtPassword = (EditText) findViewById( R.id.edtPassword );
    userPreferences = (Button) findViewById( R.id.btnPreferences );
    userPreferences.setEnabled( false );

    // VOLLEY
    RequestQueue queue = Volley.newRequestQueue( this );
    sr = new ServerRequest( this, "LOGIN", username, password, loginUrl, queue );

    //  sr.setCommand( "GetUsers" );
    //   sr.setUrl( getAllUsersUrl );
    //  sr.makeRequest( "GETUSERS" );
  }

  public void gotoPreferences( View v ){
    if( loggedIn ){
      startActivity( new Intent( this, BattlePrefs.class ) );
    }
  }

  public void clickLogin( View v ){
    password = edtPassword.getText().toString();
    username = edtUsername.getText().toString();
    sr.setUsername( username );
    sr.setPassword( password );
    sr.makeRequest( "LOGIN" );
  }

  public void startGameClick( View v ){
    sr.setUrl( startGameUrl );
    sr.makeRequest( "STARTGAME" );
  }

  public void processStartGame( String response ){
    try{
      JSONObject jsonObject = new JSONObject( response );
      gameId = jsonObject.getInt( "game_id" );
      // Switch to Game view
      startActivity( new Intent( this, Game.class ) );
    } catch( JSONException e ){
      e.printStackTrace();
    }
  }

  public void processLogin( String response ){
    // Parse into an JSON Object

    try{
      JSONObject jsonObject = new JSONObject( response );
      user = new User();
      user.setId( jsonObject.getInt( "id" ) );
      user.setFirst_name( jsonObject.getString( "first_name" ) );
      user.setLast_name( jsonObject.getString( "last_name" ) );
      user.setOnline( jsonObject.getBoolean( "online" ) );
      Log.i( "BattleShip", user.toString() );
      loggedIn = true;
      userPreferences.setEnabled( true );
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
        processStartGame( response );
        break;

      case "GETSHIPS" :
        Log.i( "BattleShip", "GETSHIPS --- " + response );
        Game.processGetShips( getApplicationContext(), response );
        break;

      default:
        break;
    }
  }

}
