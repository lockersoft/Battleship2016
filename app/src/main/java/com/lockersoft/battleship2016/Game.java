package com.lockersoft.battleship2016;

import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Dave.Jones on 5/3/2016.
 */
public class Game extends BaseActivity{

  TextView txtGameID;


  @Override
  protected void onCreate( Bundle savedInstanceState ){
    super.onCreate( savedInstanceState );
    setContentView( R.layout.game );
    txtGameID = (TextView) findViewById( R.id.txtGameID );

    txtGameID.setText( "ID: " + gameId );
  }
}
