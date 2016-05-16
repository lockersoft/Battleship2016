package com.lockersoft.battleship2016;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.SocketHandler;

/**
 * Project: Battleship2016
 * Created by Dave.Jones on 5/3/2016.
 */
public class Game extends BaseActivity {

  TextView txtGameID;
  static Map<String, Integer> shipsMap = new HashMap<String, Integer>();
  static String[] shipsArray;
  static ArrayAdapter<String> shipsSpinnerArrayAdapter;
  static Spinner shipSpinner;
  static Spinner rowSpinner;
  static Spinner colSpinner;
  Button btnAddShip;

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.game );
    txtGameID = (TextView) findViewById( R.id.txtGameID );
    shipSpinner = (Spinner) findViewById( R.id.spinnerAddShips );
    rowSpinner = (Spinner) findViewById( R.id.spinnerAddRow );
    colSpinner = (Spinner) findViewById( R.id.spinnerAddCols );
    btnAddShip = (Button) findViewById( R.id.btnAddShip );

    shipSpinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected( AdapterView<?> parent, View view, int position, long id ) {
        Log.i( "BATTLESHIP", shipSpinner.getSelectedItem().toString() );
      }

      @Override
      public void onNothingSelected( AdapterView<?> parent ) {
        // sometimes you need nothing here
      }
    } );

    rowSpinner.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
      @Override
      public void onItemSelected( AdapterView<?> parent, View view, int position, long id ) {
        Log.i( "BATTLESHIP", rowSpinner.getSelectedItem().toString() );
      }

      @Override
      public void onNothingSelected( AdapterView<?> parent ) {
        // sometimes you need nothing here
      }
    } );

    txtGameID.setText( "ID: " + gameId );
    SetupGame();
  }

  /**
   * @param v
   */
  public void addShipOnClick( View v ) {
    String shipName = (String) shipSpinner.getSelectedItem();
    String col = (String) colSpinner.getSelectedItem();
    String row = (String) rowSpinner.getSelectedItem();
    String direction = "4";   // South

    // api/v1/game/:id/add_ship/:ship/:row/:col/:direction.json
    String addShip = addShipUrl + "game/" + gameId + "/add_ship/" + shipName + "/" + row + "/" + col + "/" + direction + ".json";
    Log.i( "BATTLESHIP", addShip );
//    sr.setUrl( addShip );
//    sr.makeRequest( "ADDSHIP" );
  }

  private void SetupGame() {
    // Get the ships and put into a spinner
    GetShips();
    // Get the directions and put into a spinner
    // Get the Locations and put into 2 spinners   A,B,C   1,2,3
  }

  public void GetShips() {
    sr.setUrl( getShipsUrl );
    sr.makeRequest( "GETSHIPS" );
  }

  public static void processGetShips( Context context, String response ) {
    Log.i( "BATTLESHIP", response );
    try {
      JSONObject ships = new JSONObject( response );
      Iterator iter = ships.keys();
      while( iter.hasNext() ) {
        String key = (String) iter.next();
        Integer value = ships.getInt( key );
        Log.i( "BATTLESHIP", key + ":" + value );
        shipsMap.put( key, value );
        int size = shipsMap.keySet().size();
        shipsArray = new String[ size ];
        shipsArray = shipsMap.keySet().toArray( new String[]{ "Select a Ship" } );

        shipsSpinnerArrayAdapter = new ArrayAdapter<String>( context, android.R.layout.simple_spinner_item, shipsArray );
        shipsSpinnerArrayAdapter.setDropDownViewResource( android.R.layout.simple_spinner_dropdown_item );
        shipSpinner.setAdapter( shipsSpinnerArrayAdapter );
//        shipSpinner.setSelection(1, true);

      }
    } catch( JSONException e ) {
      e.printStackTrace();
    }
  }
}
