import org.apache.spark.sql.SparkSession

/**
  * Created by VEDPRAKASH on 11/13/2017.
  */
object SparkSessionExamples {

  def main(args: Array[String]) {

    val sparkSession = SparkSession.builder.
      master("local")
      .appName("spark session example")
      .getOrCreate()

    val df = sparkSession.read.option("header","true").csv("src/main/resources/Cricket_Node.csv")
    df.show()

  }

}
