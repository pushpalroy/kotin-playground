package util

import java.io.File

// Yes, there probably is a better way to do this!
fun occurrenceOfWordInFile(filePath: String, word: String): Int {
    return File(filePath).useLines { lines ->
        lines.map { line ->
            line.splitToSequence(" ", "-", ".", ",", ":", "/", "'", ignoreCase = true)
                .filter { it == word }
                .count()
        }.sum()
    }
}