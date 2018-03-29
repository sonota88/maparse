object Maparse {

  def parseOptionals(optionalArgs: Array[String]): Map[String, String] = {
    var opts: Map[String, String] = Map()

    optionalArgs.foreach{ arg =>
      val idx = arg.indexOf("=")

      if (idx < 0) {
        opts = opts + ( arg -> "true" )
      } else {
        val k = arg.substring(0, idx)
        val v = arg.substring(idx + 1)
        opts = opts + (k -> v)
      }
    }

    opts
  }

  def parseArgs(args: Array[String], names: String*): Map[String, String] = {
    var opts: Map[String, String] = Map()

    if (args.length == names.length) {
      // no optional arguments
    } else if (args.length > names.length) {
      opts = parseOptionals(args.drop(names.length))
    } else {
      throw new IllegalArgumentException()
    }

    names.zipWithIndex.foreach{ case(name, i) =>
      opts = opts + (name -> args(i))
    }

    opts
  }

  def main(args: Array[String]) = {
    val opts = parseArgs(args, "arg1", "arg2")
    println(opts)
  }

}
