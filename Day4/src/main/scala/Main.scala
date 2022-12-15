import scala.collection.:+
import scala.io.Source

object CampCleanUp extends App{

  val fileName = "/opt/AdventOfCode2022/Day4/src/main/scala/realInput.txt"
  val file = Source.fromFile(fileName)

  var counter: Int = 0
  for(line <- file.getLines()) {
    val array: Array[Int] = parseInput(line)
    if(checkForContainingSectors(array)) counter += 1
  }
  println(counter)
  file.close()




  /**
   * Returns parsed values in form [firstElfFrom, firstElfTo, secondElfFrom, secondElfTo]
   * @param input
   * @return
   */
  private def parseInput(input: String): Array[Int] = {
    var parsedValuesAsString = Array[Int]()
    val trimmedList: List[String] = input.split("-").map(_.trim).toList
    trimmedList.foreach(element => element.split(",").foreach(number => {parsedValuesAsString =   parsedValuesAsString :+ number.toInt}))
    parsedValuesAsString
  }

  private def checkForContainingSectors(sectorArray: Array[Int]): Boolean = {
    // first elf contains the second one
    if(sectorArray(0) <= sectorArray(2) && sectorArray(1) >= sectorArray(3)) true
    // second elf contains first one
    else if(sectorArray(0) >= sectorArray(2) && sectorArray(1) <= sectorArray(3)) true
    else false
  }





}

