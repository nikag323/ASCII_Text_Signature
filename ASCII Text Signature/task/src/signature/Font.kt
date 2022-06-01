package signature

import java.io.File

class Font(val fileName: String) {
    var fontHeight: Int = 0
    var symNumber: Int = 0
    var symbols: MutableMap<Char, Symbol>

    init {
        val lines = File(this.fileName).readLines()
        var idx = 0
        val (height, number) = lines[idx].split(" ")
        fontHeight = height.toInt()
        symNumber = number.toInt()
        symbols = mutableMapOf<Char, Symbol>()

        repeat(symNumber) {
            val (sym, fontWidth) = lines[++idx].split(" ")
            val scratches = mutableListOf<String>()
            repeat(fontHeight) {
                scratches.add(lines[++idx])
            }
            symbols.put(sym[0], Symbol(sym[0], fontHeight, fontWidth.toInt(), scratches))
        }
    }

    fun getScratch(sym: Char, line: Int): String? {
        return symbols[sym]?.scratches?.get(line)
    }
}

data class Symbol(val key: Char, val symbolHeight: Int, val symbolWidth: Int, val scratches: MutableList<String>) {

    fun getScratch(line: Int): String {
        return scratches[line]
    }
}
