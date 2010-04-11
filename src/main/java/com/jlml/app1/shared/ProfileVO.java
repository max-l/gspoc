package com.jlml.app1.shared;

import java.io.Serializable;


public class ProfileVO implements Serializable {

  public ProfileVO() {}

  public ProfileVO(long id, String nom, String prenom, String pseudo) {
    this.id = id;
    this.nom = nom;
    this.prenom = prenom;
    this.pseudo = pseudo;
  }

  public long id;
  public String nom;
  public String prenom;
  public String pseudo;
}
