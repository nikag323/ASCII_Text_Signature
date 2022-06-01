package signature

class Frame(private val name: String, private val status: String) {

    private val hBorder = "8"
    private val vBorder = "88"
    private val leftBorderSpaces = 2
    private val rightBorderSpaces = 2
//    val hBlankLines = 3
    private val nameWordSpaces = 10
    private val statusWordSpaces = 5 //error in test
    private val fontFileRoman = "C:\\1\\roman.txt"
    private val fontFileMedium = "C:\\1\\medium.txt"

    fun print() {

        val fontForName = Font(fontFileRoman)
        val fontForStatus = Font(fontFileMedium)

        val asciiName = makeAsciiText(fontForName, name, nameWordSpaces)
        val asciiStatus = makeAsciiText(fontForStatus, status, statusWordSpaces)
        val nameLength = asciiName[0].length
        val statusLength = asciiStatus[0].length

        var spacesBeforeName = 0
        var spacesAfterName = 0
        var spacesBeforeStatus = 0
        var spacesAfterStatus = 0

        if (nameLength < statusLength) {
            spacesBeforeName = (statusLength - nameLength) / 2
            spacesAfterName = (statusLength - nameLength) / 2 + (statusLength - nameLength) % 2
        } else {
            spacesBeforeStatus = (nameLength - statusLength) / 2
            spacesAfterStatus = (nameLength - statusLength) / 2 + (nameLength - statusLength) % 2
        }

        val textBeforeName = vBorder + " ".repeat(leftBorderSpaces) + " ".repeat(spacesBeforeName)
        val textAfterName = " ".repeat(spacesAfterName) + " ".repeat(rightBorderSpaces) + vBorder
        val textBeforeStatus = vBorder + " ".repeat(leftBorderSpaces) + " ".repeat(spacesBeforeStatus)
        val textAfterStatus = " ".repeat(spacesAfterStatus) + " ".repeat(rightBorderSpaces) + vBorder

        val hBorder = "$vBorder${hBorder.repeat(leftBorderSpaces + spacesBeforeName + nameLength + spacesAfterName + rightBorderSpaces)}$vBorder"
//        val blankLine = "$vBorder${" ".repeat(leftBorderSpaces + spacesBeforeName + nameLength + spacesAfterName + rightBorderSpaces)}$vBorder"

        println(hBorder)
        for (scratch in asciiName)
            println("$textBeforeName$scratch$textAfterName")
        //blank lines
//        for (i in 1..hBlankLines) println(blankLine)
        for (scratch in asciiStatus)
            println("$textBeforeStatus$scratch$textAfterStatus")
        println(hBorder)
    }


    private fun makeAsciiText(font: Font, text: String, wordSpace: Int): MutableList<String> {
        val asciiText = mutableListOf<String>()
        for (line in 0 until font.fontHeight) {
            var scratch = ""
            for (ch in text) {
                scratch += if (ch == ' ') {
                    " ".repeat(wordSpace)
                } else {
                    font.getScratch(ch, line)
                }
            }
//            if (scratch.isNotBlank())
                asciiText.add(scratch)
        }
        return asciiText
    }


}
