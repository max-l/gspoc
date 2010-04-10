package com.jlml.app1.model

import org.squeryl.adapters.H2Adapter
import org.squeryl.{SessionFactory, Session, Schema}
import java.sql.SQLException

object Cruz extends Schema {

  val profils = table[Profile]

  override def drop = super.drop 
}


object SchemaRebuild {

  def createH2TestConnection = {
    Session.create(
      java.sql.DriverManager.getConnection("jdbc:h2:~/test", "sa", ""),
      new H2Adapter
    )
  }
  
  def main(args : Array[String]) : Unit = {
    Class.forName("org.h2.Driver");

    import org.squeryl.PrimitiveTypeMode._

    SessionFactory.concreteFactory = Some(()=>
      Session.create(java.sql.DriverManager.getConnection("jdbc:h2:~/test", "sa", ""), new H2Adapter)
    )

    transaction {
      try {
        Cruz.drop // we normally *NEVER* do this !!
      }
      catch {
        case e:SQLException => println(" schema does not yet exist :" + e.getMessage)
      }
      Cruz.create
    }

    import Cruz._

    transaction {

      profils.insert(new Profile("Antonio", "Le gros", "El'Gros"))
      profils.insert(new Profile("Bertha", "La grosse", "LaGrosse"))
    }
  }
}