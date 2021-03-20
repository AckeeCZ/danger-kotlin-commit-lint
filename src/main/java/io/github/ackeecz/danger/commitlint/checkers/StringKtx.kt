package io.github.ackeecz.danger.commitlint.checkers

internal fun String.skipGitmojis(): String {
    return dropWhile { char -> !char.isLetter() || char.isWhitespace() }
}