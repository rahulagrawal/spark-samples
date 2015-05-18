package learning.streaming.twitter

import learning.SparkUtil
import org.apache.spark.Logging
import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}

/**
 *
 * @author Rahul Agrawal
 *         Date: 5/18/2015
 */
object ExampleStreaming extends Logging {

  var runLocal = false;

  def main(args: Array[String]) {
    if (args.length >= 1 && args(0).trim.equalsIgnoreCase("runLocal")) {
      runLocal = true
    }
    val sparkConf = SparkUtil.getSparkConf("twitter-example", runLocal)
    val streamingContext = new StreamingContext(sparkConf, Seconds(5))

    Authenticator.init()

    val tweets = TwitterUtils.createStream(streamingContext, None)

    val statuses = tweets.map(status => status.getText) // entire message
    statuses.foreachRDD(x => logInfo(x.toDebugString))
    statuses.print()

    val words = statuses.flatMap(status => status.split(" "))
    val hashtags = words.filter(word => word.startsWith("#"))

    hashtags.print()

    streamingContext.start()
    streamingContext.awaitTermination()
  }


}
