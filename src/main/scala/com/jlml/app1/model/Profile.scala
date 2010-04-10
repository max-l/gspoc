package com.jlml.app1.model

import org.squeryl.KeyedEntity
import com.jlml.app1.shared.ProfileVO


trait CruzObject extends KeyedEntity[Long] {
  val id: Long = 0
}


class Profile(var nom: String, var prenom: String, var pseudonyme: String) extends CruzObject {

  def vo =
    new ProfileVO(id, nom, prenom, pseudonyme)  
}