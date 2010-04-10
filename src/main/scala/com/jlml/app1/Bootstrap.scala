package com.jlml.app1

import javax.servlet.{ServletContextEvent, ServletContext, ServletContextListener}
import org.squeryl._
import adapters.H2Adapter

class Bootstrap extends ServletContextListener {
  
	def contextInitialized(contextEvent: ServletContextEvent) = {

		val context = contextEvent.getServletContext()
		//context.setAttribute("TEST", "TEST_VALUE");

    Class.forName("org.h2.Driver")
    
    SessionFactory.concreteFactory = Some(()=>
      Session.create(java.sql.DriverManager.getConnection("jdbc:h2:~/test", "sa", ""), new H2Adapter)
    )
	}
  
	def contextDestroyed(contextEvent: ServletContextEvent) = {}
}
