package learning.streaming.twitter

import org.apache.spark.streaming.twitter.TwitterUtils
import org.apache.spark.streaming.{Seconds, StreamingContext}
import org.apache.spark.{Logging, SparkConf}

/**
 *
 * @author Rahul Agrawal
 *         Date: 5/18/2015
 */
object ExampleStreaming extends Logging {

  def main(args: Array[String]) {

    val sparkConf = new SparkConf().setAppName("streaming-twitter-example").setMaster("local[2]")
    val streamingContext = new StreamingContext(sparkConf, Seconds(5))

    Authenticator.init()

    val tweets = TwitterUtils.createStream(streamingContext, None)

    val statuses = tweets.map(status => status.getText)
    statuses.print()
    streamingContext.start()
    streamingContext.awaitTermination()
  }


}
