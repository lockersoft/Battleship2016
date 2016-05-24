package com.lockersoft.battleship2016;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.*;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

interface ServerRequests {
  void ProcessResponse( String command, String response );
}

public class MainActivity extends BaseActivity implements ServerRequests {

//  String apiKey = "&appid=a11b9eebb90f64365d39a253a845c564";
//  String weatherURL = "http://api.openweathermap.org/data/2.5/weather?lat=47.67&lon=-117.48";

  String username = "dave.jones@scc.spokane.edu";
  String password = "dljones42";

  Button loginButton, btnStartGame, startExistingGame;
  static Button userPreferences;
  EditText edtUsername, edtPassword, edtGameID;
  NetworkImageView imgAvatar;
  ListView listView;

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.activity_main );

    listView = (ListView) findViewById( R.id.listView );
    imgAvatar = (NetworkImageView) findViewById( R.id.imgAvatar );
    loginButton = (Button) findViewById( R.id.btnLogin );
    btnStartGame = (Button) findViewById( R.id.btnStartGame );
    startExistingGame = (Button) findViewById( R.id.btnStartExisting );
    edtUsername = (EditText) findViewById( R.id.edtUsername );
    edtPassword = (EditText) findViewById( R.id.edtPassword );
    edtGameID = (EditText) findViewById( R.id.edtGameID );
    userPreferences = (Button) findViewById( R.id.btnPreferences );
    if( userPreferences != null ) {
      userPreferences.setEnabled( false );
    }
    startExistingGame.setEnabled( false );
    btnStartGame.setEnabled( false );

    // VOLLEY
    RequestQueue queue = Volley.newRequestQueue( this );
    sr = new ServerRequest( this, "LOGIN", username, password, loginUrl, queue );

    // Defined Array values to show in ListView
    String[] values = new String[] {
            "Dave",
            "Joe",
            "Sally",
            "Sharon",
            "Karen",
            "Tom",
            "Lynn",
            "Scott"
    };

    // http://androidexample.com/Create_A_Simple_Listview_-_Android_Example/index.php?view=article_discription&aid=65&aaid=90
    // Define a new Adapter
    // First parameter - Context
    // Second parameter - Layout for the row
    // Third parameter - ID of the TextView to which the data is written
    // Fourth - the Array of data
    ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
            android.R.layout.simple_list_item_1, android.R.id.text1, values);

    // Assign adapter to ListView
    listView.setAdapter(adapter);

    // ListView Item Click Listener
    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

      @Override
      public void onItemClick(AdapterView<?> parent, View view,
                              int position, long id) {

        // ListView Clicked item index
        int itemPosition     = position;

        // ListView Clicked item value
        String  itemValue    = (String) listView.getItemAtPosition(position);

        // Show Alert
        Toast.makeText(getApplicationContext(),
                "Position :"+itemPosition+"  ListItem : " +itemValue , Toast.LENGTH_LONG)
                .show();
      }

    });

    //  sr.setCommand( "GetUsers" );
    //   sr.setUrl( getAllUsersUrl );
    //  sr.makeRequest( "GETUSERS" );
  }

  public void gotoPreferences( View v ) {
    if( loggedIn ) {
      startActivity( new Intent( this, BattlePrefs.class ) );
    }
  }

  public void getAllUsersOnClick( View v ){
    sr.setUrl( getAllUsersUrl );
    sr.makeRequest( "GETALLUSERS" );
  }

  public void clickLogin( View v ) {
    password = edtPassword.getText().toString();
    username = edtUsername.getText().toString();
    sr.setUsername( username );
    sr.setPassword( password );
    sr.makeRequest( "LOGIN" );
  }

  public void startGameClick( View v ) {
    sr.setUrl( startGameUrl );
    sr.makeRequest( "STARTGAME" );
  }

  public void startExistingGameClick( View v ) {
    gameId = Integer.valueOf( edtGameID.getText().toString() );
    startActivity( new Intent( this, Game.class ) );
  }

  public void processStartGame( String response ) {
    try {
      JSONObject jsonObject = new JSONObject( response );
      gameId = jsonObject.getInt( "game_id" );
      // Switch to Game view
      startActivity( new Intent( this, Game.class ) );
    } catch( JSONException e ) {
      e.printStackTrace();
    }
  }

  public void processLogin( String response ) {
    // Parse into an JSON Object

    try {
      JSONObject jsonObject = new JSONObject( response );
      user = new User();
      user.setId( jsonObject.getInt( "id" ) );
      user.setFirst_name( jsonObject.getString( "first_name" ) );
      user.setLast_name( jsonObject.getString( "last_name" ) );
      user.setOnline( jsonObject.getBoolean( "online" ) );
      user.setAvatar_image( jsonObject.getString( "avatar_image" ) );
      Log.i( "BattleShip", user.toString() );
      loggedIn = true;
      userPreferences.setEnabled( true );
      startExistingGame.setEnabled( true );
      btnStartGame.setEnabled( true );
      sr.getImage( imgAvatar, baseUrl + user.getAvatar_image() );
    } catch( JSONException e ) {
      e.printStackTrace();
    }
  }

  private void processGetAllUsers( String response ){
    // Parse it out, get strings, put into an ARRAY, associate with the adapter
  }

  @Override
  public void ProcessResponse( String command, String response ) {
    switch( command ) {
      case "LOGIN":
        Log.i( "BattleShip", "LOGIN --- " + response );
        processLogin( response );
        break;

      case "GETALLUSERS":
        processGetAllUsers( response );
        Log.i( "BattleShip", "GETALLUSERS --- " + response );
        break;

      case "GETUSERS":
        Log.i( "BattleShip", "GETUSERS --- " + response );
        break;

      case "STARTGAME":
        Log.i( "BattleShip", "STARTGAME --- " + response );
        processStartGame( response );
        break;

      case "GETSHIPS":
        Log.i( "BattleShip", "GETSHIPS --- " + response );
        Game.processGetShips( getApplicationContext(), response );
        break;

      default:
        break;
    }
  }

}
