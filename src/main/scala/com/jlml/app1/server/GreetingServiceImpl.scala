package com.jlml.app1.server

import com.jlml.app1.client.GreetingService
import com.jlml.app1.shared.{ProfileVO, FieldVerifier}
import java.util.ArrayList
import com.jlml.app1.model.Cruz;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.squeryl.PrimitiveTypeMode._
import Cruz._


class GreetingServiceImpl extends RemoteServiceServlet with GreetingService {

  def greetServer(input: String): String = {
    
	// Verify that the input is valid. 
    if (!FieldVerifier.isValidName(input)) {
      // If the input is not valid, throw an IllegalArgumentException back to
      // the client.
      throw new IllegalArgumentException("Name must be at least 4 characters long")
    }

    val serverInfo = getServletContext.getServerInfo
	
    val userAgent = getThreadLocalRequest.getHeader("User-Agent")
    
	"qqqq////////////Hello, " + input + "!<br><br>I am running " + serverInfo + ".<br><br>It looks like you are using:<br>" + userAgent
  }

  def greetServer2(input: String): ArrayList[ProfileVO] = transaction {

    val r = new ArrayList[ProfileVO]
    
    for(p <-profils.where(_.pseudonyme <> "Zozo"))
      r.add(p.vo)

    r
  }
}

