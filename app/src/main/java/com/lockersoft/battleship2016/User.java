package com.lockersoft.battleship2016;

/**
 * Project: Battleship2016
 * Created by Dave.Jones on 4/28/2016.
 */
public class User {
  Integer id, level, coins, battles_won, battles_lost, battles_tied, experience_points;
  String first_name, last_name, avatar_name, email, avatar_image;
  Boolean available, online, gaming;

  public User() {
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", level=" + level +
        ", coins=" + coins +
        ", battles_won=" + battles_won +
        ", battles_lost=" + battles_lost +
        ", battles_tied=" + battles_tied +
        ", experience_points=" + experience_points +
        ", first_name='" + first_name + '\'' +
        ", last_name='" + last_name + '\'' +
        ", avatar_name='" + avatar_name + '\'' +
        ", email='" + email + '\'' +
        ", avatar_image='" + avatar_image + '\'' +
        ", available=" + available +
        ", online=" + online +
        ", gaming=" + gaming +
        '}';
  }

  public Integer getId() {
    return id;
  }

  public void setId( Integer id ) {
    this.id = id;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel( Integer level ) {
    this.level = level;
  }

  public Integer getCoins() {
    return coins;
  }

  public void setCoins( Integer coins ) {
    this.coins = coins;
  }

  public Integer getBattles_won() {
    return battles_won;
  }

  public void setBattles_won( Integer battles_won ) {
    this.battles_won = battles_won;
  }

  public Integer getBattles_lost() {
    return battles_lost;
  }

  public void setBattles_lost( Integer battles_lost ) {
    this.battles_lost = battles_lost;
  }

  public Integer getBattles_tied() {
    return battles_tied;
  }

  public void setBattles_tied( Integer battles_tied ) {
    this.battles_tied = battles_tied;
  }

  public Integer getExperience_points() {
    return experience_points;
  }

  public void setExperience_points( Integer experience_points ) {
    this.experience_points = experience_points;
  }

  public String getFirst_name() {
    return first_name;
  }

  public void setFirst_name( String first_name ) {
    this.first_name = first_name;
  }

  public String getLast_name() {
    return last_name;
  }

  public void setLast_name( String last_name ) {
    this.last_name = last_name;
  }

  public String getAvatar_name() {
    return avatar_name;
  }

  public void setAvatar_name( String avatar_name ) {
    this.avatar_name = avatar_name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail( String email ) {
    this.email = email;
  }

  public String getAvatar_image() {
    return avatar_image;
  }

  public void setAvatar_image( String avatar_image ) {
    this.avatar_image = avatar_image;
  }

  public Boolean getAvailable() {
    return available;
  }

  public void setAvailable( Boolean available ) {
    this.available = available;
  }

  public Boolean getOnline() {
    return online;
  }

  public void setOnline( Boolean online ) {
    this.online = online;
  }

  public Boolean getGaming() {
    return gaming;
  }

  public void setGaming( Boolean gaming ) {
    this.gaming = gaming;
  }
}
