package learning

import org.apache.spark.SparkConf

/**
 *
 * @author Rahul Agrawal
 *         Date: 5/18/2015
 */
object SparkUtil {

  def getSparkConf(appName: String, isLocal: Boolean = false): SparkConf = {
    val sparkConf = new SparkConf().setAppName(appName)
    if (isLocal) {
      sparkConf.setMaster("local[*]")
    } else {
      sparkConf
    }
  }

}
