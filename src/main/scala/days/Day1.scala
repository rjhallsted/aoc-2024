import scala.io.Source
import java.net.URL

object Day1 extends Day {

  def day: Int = 1
  def main(input: String): Unit = {
    val res: Int = input.split("\n").map(getNumber).sum
    println(res)
  }

  def findNums(line: String): (Int, Int) = {
    val textNums = Map(
        "one" -> 1,
        "two" -> 2,
        "three" -> 3,
        "four" -> 4,
        "five" -> 5,
        "six" -> 6,
        "seven" ->7,
        "eight" -> 8,
        "nine" -> 9
    )

    var lead: Option[Int] = None
    var trailing: Option[Int] = None
    for (i <- Range(0, line.length)) {
        var num: Option[Int] = None
        if line.charAt(i).isDigit then {
            num = Some(line.charAt(i) - '0')
        } else {
            textNums.foreach((key, n) => {
                if line.substring(i).startsWith(key) then
                    num = Some(n)
            })
        }
        if num.nonEmpty then {
            if lead.isEmpty then
                lead = num
            else
                trailing = num
        }
    }

    if lead.nonEmpty && trailing.isEmpty then
        trailing = lead

    lead = lead.orElse({
        println(s"no lead on line '${line}'")
        None
    })

    trailing = trailing.orElse({
        println(s"no trailing on line '${line}'")
        None
    })



    (lead.get, trailing.get)
  }

  def getNumber(line: String): Int = {
    val (leading, trailing) = findNums(line)

    (leading * 10) + trailing
  }
}
