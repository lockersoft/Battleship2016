package com.lockersoft.battleship2016;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Dave.Jones on 5/2/2016.
 * <p>
 * AppcompatActivity
 * BaseActivity
 * MyActivity
 */
public class BaseActivity extends AppCompatActivity{
  static User user;
  static Boolean loggedIn = false;
  static Integer gameId = -1;         // Set to negative to start
}
