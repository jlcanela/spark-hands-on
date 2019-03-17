
case class AccessLog(time: String, ip: String, uri: String, cookieId: String)

object AccessLog {
	val uris = List("/", "/list", "/update", "/detail")
	def uri = uris(fabricator.Fabricator.alphaNumeric.randomInt(0, uris.length))
	def ip = fabricator.Fabricator.internet.ip
	def cookieId = fabricator.Fabricator.alphaNumeric.randomString("0123456789abcdef", 10)
	def generate(date: String, time: String)(): AccessLog = AccessLog(time, ip, uri, cookieId)
}


object SimpleApp {

  def main(args: Array[String]) {
  }
  
  def exec() {

	import org.apache.spark.sql.SparkSession

    val spark = SparkSession.builder.appName("Simple Application").master("local[*]").getOrCreate()

	import spark.implicits._

    import org.joda.time._
	val startDate = new LocalDate("2019-01-01")

	val MAX : Int = 3600 * 24
	val items = 1000
	def genTimes(nb: Int): List[String] = List.range(0, MAX, MAX / nb).take(nb).map { time => "12:34:56.789"
		val h1 = fabricator.Fabricator.alphaNumeric.randomString("01", 1)
		val h2 = fabricator.Fabricator.alphaNumeric.randomString("0123456789", 1)
		val m1 = fabricator.Fabricator.alphaNumeric.randomString("012345", 1)
		val m2 = fabricator.Fabricator.alphaNumeric.randomString("0123456789", 1)
		val s1 = fabricator.Fabricator.alphaNumeric.randomString("012345", 1)
		val s2 = fabricator.Fabricator.alphaNumeric.randomString("0123456789", 1)
		val ms = fabricator.Fabricator.alphaNumeric.randomString("0123456789", 3)
		/*val hh = time / (3600 * 1000)
		val mm = (time - (hh * 3600 * 1000) ) / (60 * 1000)
		val ss = (time - (hh * 3600 * 1000 + mm * 60 * 1000) ) /.1000
		val ms = (time - (hh * 3600 * 1000 + mm * 60 * 1000 + ss * 1000))
		s"$hh:$mm:$ss:$ms"*/
		s"$h1$h2:$m1$m2:$s1$s2.$ms"
	}

	(0 to 30) foreach { 
		day => {
			val date = startDate.plusDays(day).toString
		    val accesses = genTimes(1000).map { time => AccessLog.generate(date, time) }
			accesses.toDF.coalesce(1).write.mode(org.apache.spark.sql.SaveMode.Overwrite).parquet(s"accesslog/day=$date")
		}
	}

	println("SimpleApp")
    
    spark.stop()
  }
}

