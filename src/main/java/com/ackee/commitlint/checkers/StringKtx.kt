package com.ackee.commitlint.checkers

fun String.skipGitmojis(): String {
    return dropWhile { char -> !char.isLetter() || char.isWhitespace() }
}