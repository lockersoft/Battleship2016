package com.lockersoft.battleship2016;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

/**
 * Project: Battleship2016
 * Created by Dave.Jones on 5/2/2016.
 */
public class BattlePrefs extends BaseActivity {

  EditText edtFirstName;
  EditText edtLastName;

  @Override
  protected void onCreate( Bundle savedInstanceState ) {
    super.onCreate( savedInstanceState );
    setContentView( R.layout.battleprefs );
    edtFirstName = (EditText) findViewById( R.id.edtFirstName );
    edtLastName = (EditText) findViewById( R.id.edtLastName );
    edtFirstName.setText( user.getFirst_name() );
    edtLastName.setText( user.getLast_name() );
  }
}
