package com.lockersoft.battleship2016;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * Created by Dave.Jones on 5/2/2016.
 * <p>
 * AppcompatActivity
 * BaseActivity
 * MyActivity
 */


public class BaseActivity extends AppCompatActivity  {
  static User user;
  static Boolean loggedIn = false;
  static Integer gameId = -1;         // Set to negative to start
  static ServerRequest sr;

  static String getShipsUrl = "http://battlegameserver.com/api/v1/available_ships.json";
  static String loginUrl = "http://battlegameserver.com/api/v1/login";
  static String getAllUsersUrl = "http://battlegameserver.com/api/v1/all_users.json";
  static String startGameUrl = "http://battlegameserver.com/api/v1/challenge_computer.json";
  static String addShipUrl = "http://battlegameserver.com/api/v1/";
}
