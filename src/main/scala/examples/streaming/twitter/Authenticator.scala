package examples.streaming.twitter

import org.apache.spark.Logging

import scala.io.Source

/**
 *
 * @author Rahul Agrawal
 *         Date: 5/16/2015
 */
@deprecated
object Authenticator extends Logging {

  val propertiesFile: String = "/twitter.properties"


  def main(args: Array[String]): Unit = {
    init()
  }

  def init() = {
    val properties = getAuthPropertiesFromFile()
    setAuthProperties(properties)
  }

  val function1 = (x: String) => {
    val keyValue = x.split("=")
    (keyValue(0), keyValue(1))
  }

  def getAuthPropertiesFromFile(): Map[String, String] = {
    val lines = Source.fromURL(getClass.getResource(propertiesFile)).getLines()
    val keyValue = lines.filter(line => line.split("=").size == 2).map(function1(_))
    keyValue.toMap
  }

  def setAuthProperties(keyValues: Map[String, String]) = {
    keyValues.foreach {
                        case (key, value) =>
                          logInfo(s"Key=$key : value=$value")
                          System.setProperty(key, value)
                      }
  }
}
