import org.apache.spark.sql.SparkSession

/**
  * Created by VEDPRAKASH on 11/13/2017.
  */
object DatasetVsDataFrame {

  case class Cricket_Node(id:Int,name:String,age:String,location:String,specialization:String)

  def main(args: Array[String]) {

    val sparkSession = SparkSession.builder.
      master("local")
      .appName("example")
      .getOrCreate()

    val sparkContext = sparkSession.sparkContext
    import sparkSession.implicits._


    //read data from text file

    val df = sparkSession.read.option("header","true").option("inferSchema","true").csv("src/main/resources/Cricket_Node.csv")
    val ds = sparkSession.read.option("header","true").option("inferSchema","true").csv("src/main/resources/Cricket_Node.csv").as[Cricket_Node]


    val selectedDF = df.select("name")

    val selectedDS = ds.map(_.name)

    println(selectedDF.queryExecution.optimizedPlan.numberedTreeString)

    println(selectedDS.queryExecution.optimizedPlan.numberedTreeString)


  }

}
