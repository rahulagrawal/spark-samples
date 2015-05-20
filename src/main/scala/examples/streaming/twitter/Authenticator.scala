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
}
