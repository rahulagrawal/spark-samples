package examples.streaming

import org.apache.log4j.{Level, Logger}
import org.apache.spark.Logging

/**
 *
 * @author Rahul Agrawal
 *         Date: 5/16/2015
 */
object LogUtil extends Logging {

  /** Set reasonable logging levels for learning.streaming if the user has not configured log4j. */
  def setStreamingLogLevels() {
    val log4jInitialized = Logger.getRootLogger.getAllAppenders.hasMoreElements
    if (!log4jInitialized) {
      // We first log something to initialize Spark's default logging, then we override the
      // logging level.
      logInfo("Setting log level to [WARN] for streaming example. To override add a custom log4j.properties to the classpath.")
      Logger.getRootLogger.setLevel(Level.WARN)
    }
  }

  def setStreamingLogLevels(level: Level) {
    val log4jInitialized = Logger.getRootLogger.getAllAppenders.hasMoreElements
    if (!log4jInitialized) {
      // We first log something to initialize Spark's default logging, then we override the
      // logging level.
      logInfo(s"Setting log level to $level for streaming example. To override add a custom log4j.properties to the classpath.")
      Logger.getRootLogger.setLevel(level)
    }
  }

}
