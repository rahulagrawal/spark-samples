package streaming.twitter

import java.io.File

import scala.io.Source
import scala.sys.process._


/**
 *
 * @author Rahul Agrawal
 *         Date: 5/16/2015
 */
object TutorialHelper {


  /** Returns the Spark URL */
  def getSparkUrl(): String = {
    val file = new File("/root/spark-ec2/cluster-url")
    if (file.exists) {
      val url = Source.fromFile(file.toString).getLines.toSeq.head
      url
    } else if (new File("../local").exists) {
      "local[4]"
    } else {
      throw new Exception("Could not find " + file)
    }
  }

  /** Returns the HDFS URL */
  def getCheckpointDirectory(): String = {
    try {
      val name: String = Seq("bash", "-c", "curl -s http://169.254.169.254/latest/meta-data/hostname") !!;
      // see the documentation of process builder for this

      println("Hostname = " + name)
      "hdfs://" + name.trim + ":9000/checkpoint/"
    } catch {
      case e: Exception => {
        "./checkpoint/"
      }
    }
  }


}
