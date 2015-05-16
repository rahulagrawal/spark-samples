package streaming.twitter

import scala.collection.mutable

/**
 *
 * @author Rahul Agrawal
 *         Date: 5/16/2015
 */
object Authenticator {

  /** Configures the Oauth Credentials for accessing Twitter */
  def configureTwitterCredentials(apiKey: String, apiSecret: String, accessToken: String, accessTokenSecret: String) {
    val configs = new mutable.HashMap[String, String] ++= Seq("apiKey" -> apiKey,
                                                              "apiSecret" -> apiSecret,
                                                              "accessToken" -> accessToken,
                                                              "accessTokenSecret" -> accessTokenSecret)
    //println(s"Configuring Twitter OAuth for $configs")
    configs.foreach { case (key, value) =>
      if (value.trim.isEmpty) {
        throw new Exception("Error setting authentication - value for " + key + " not set")
      }
      val fullKey = "twitter4j.oauth." + key.replace("api", "consumer")
      System.setProperty(fullKey, value.trim)
      println("\tSystem Property " + fullKey + " set as [" + value.trim + "]")
                    }
    println()
  }

}
