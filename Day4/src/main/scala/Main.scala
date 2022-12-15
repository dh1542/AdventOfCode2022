import scala.collection.:+
import scala.io.Source

object CampCleanUp extends App{

  val fileName = "/opt/AdventOfCode2022/Day4/src/main/scala/realInput.txt"
  val file = Source.fromFile(fileName)

  private var counter: Int = 0
  for(line <- file.getLines()) {
    val array: Array[Int] = parseInput(line)
    val isOverlapping: Boolean = checkForOverlappingSectors(array)
    if(isOverlapping) counter += 1
    println(isOverlapping)

  }
  println(counter)
  file.close()

  /**
   * Returns parsed values in form [firstElfFrom, firstElfTo, secondElfFrom, secondElfTo]
   * @param input
   * @return
   */
  private def parseInput(input: String): Array[Int] = {
    var parsedValuesAsIntegers = Array[Int]()
    val trimmedList: List[String] = input.split("-").map(_.trim).toList
    trimmedList.foreach(element => element.split(",").foreach(number => {parsedValuesAsIntegers =   parsedValuesAsIntegers :+ number.toInt}))
    parsedValuesAsIntegers
  }

  private def checkForContainingSectors(sectorArray: Array[Int]): Boolean = {
    // first elf contains the second one
    if(sectorArray(0) <= sectorArray(2) && sectorArray(1) >= sectorArray(3)) true
    // second elf contains first one
    else if(sectorArray(0) >= sectorArray(2) && sectorArray(1) <= sectorArray(3)) true
    else false
  }

  private def checkForOverlappingSectors(sectorArray: Array[Int]): Boolean = {
    println(sectorArray.mkString("Array(", ", ", ")"))
    if(sectorArray(0) >= sectorArray(2) && sectorArray(0) <= sectorArray(3)) true
    else if(sectorArray(1) >= sectorArray(2) && sectorArray(1) <= sectorArray(3)) true
    else if(checkForContainingSectors(sectorArray)) true
    else false
  }






}

