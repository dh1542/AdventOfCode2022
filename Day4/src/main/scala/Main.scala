import scala.collection.:+
import scala.io.Source

object CampCleanUp extends App{

  val fileName = "/opt/AdventOfCode2022/Day4/src/main/scala/realInput.txt"

  val file = Source.fromFile(fileName)
  for(line <- file.getLines()) {
    val array: Array[Int] = parseInput(line)
    array.foreach(n => print(n + " "))
    println()
  }
  file.close()




  /**
   * Returns parsed values in form [firstElfFrom, firstElfTo, secondElfFrom, secondElfTo]
   * @param input
   * @return
   */
  def parseInput(input: String): Array[Int] = {
    var parsedValuesAsString = Array[Int]()
    val trimmedList: List[String] = input.split("-").map(_.trim).toList
    trimmedList.foreach(element => element.split(",").foreach(number => {parsedValuesAsString =   parsedValuesAsString :+ number.toInt}))
    parsedValuesAsString
  }




}

