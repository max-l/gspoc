package com.jlml.app1.server

import com.jlml.app1.client.GreetingService;
import com.jlml.app1.shared.FieldVerifier;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

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
}

