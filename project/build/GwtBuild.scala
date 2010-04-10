

import sbt._

class GwtBuild(info: ProjectInfo) extends DefaultWebProject(info) {
 
  val jetty7 = "org.eclipse.jetty" % "jetty-webapp" % "7.0.2.RC0" % "test"
 
  val jasper = "org.apache.tomcat" % "jasper" % "6.0.18"
 
  val squeryl = "org.squeryl" % "squeryl_2.8.0.Beta1-RC8" % "0.9.3"
  
  override def jettyPort = 1234
 
  val h2 = "com.h2database" % "h2" % "1.2.127" 
  
  lazy val infCopy = syncTask(webappPath, temporaryWarPath  / "WEB-INF") describedAs("Copying webapp filed")
    
  //prepareWebapp dependsOn(infCopy)    
   
/*   
  override def prepareWebappAction = {
    super.prepareWebappAction
	infCopy
	//gwt	
  }
*/
  
  override def webappUnmanaged =
    (temporaryWarPath / "salut" ***) 
	
  lazy val gwt = task {
             
    import Process._
   
    var srcp = sourcePath / "main" / "java"
    val cp = compileClasspath.getPaths.mkString(";") + ";" + srcp      
    val cmd = "java -Xmx512M -classpath " + cp + " com.google.gwt.dev.Compiler -logLevel INFO -war "+temporaryWarPath+" com.jlml.app1.Salut"  
    cmd ! log
   
    None
  }  describedAs("invoking GWT compiler")
  
  prepareWebapp dependsOn(gwt)
  
  gwt dependsOn(infCopy)
}