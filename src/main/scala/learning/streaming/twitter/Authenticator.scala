package learning.streaming.twitter

import org.apache.spark.Logging

import scala.io.Source

/**
 *
 * @author Rahul Agrawal
 *         Date: 5/16/2015
 */
object Authenticator extends Logging {

  val propertiesFile: String = "/twitter.properties"


  def main(args: Array[String]) {

    val properties = getAuthPropertiesFromFile()
    setAuthProperties(properties)

  }

  private def foo(s: String): (String, String) = {
    val pair: Array[String] = s.split("=")
    (pair(0), pair(1))
  }

  def getAuthPropertiesFromFile(): Map[String, String] = {
    val lines = Source.fromURL(getClass.getResource(propertiesFile)).getLines()
    val keyValue = lines.filter(line => line.split("=").size == 2).map(x => foo(x))
    keyValue.toMap
  }

  def setAuthProperties(propMap: Map[String, String]) = {
    propMap.foreach {
                      case (key, value) =>
                        logInfo(s"Key=$key : value=$value")
                        System.setProperty(key, value)
                    }
  }


  /** Configures the Oauth Credentials for accessing Twitter */
  /*def configureTwitterCredentials(apiKey: String, apiSecret: String, accessToken: String, accessTokenSecret: String) {
    val configs = new mutable.HashMap[String, String] ++= Seq("apiKey" -> apiKey,
                                                              "apiSecret" -> apiSecret,
                                                              "accessToken" -> accessToken,
                                                              "accessTokenSecret" -> accessTokenSecret)
    configs.foreach { case (key, value) =>
      if (value.trim.isEmpty) {
        throw new Exception("Error setting authentication - value for " + key + " not set")
      }
      val fullKey = "twitter4j.oauth." + key.replace("api", "consumer")
      System.setProperty(fullKey, value.trim)
      println("\tSystem Property " + fullKey + " set as [" + value.trim + "]")
                    }
    println()
  }*/

}
