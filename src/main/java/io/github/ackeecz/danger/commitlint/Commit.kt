package io.github.ackeecz.danger.commitlint

data class Commit(val message: CommitMessage, val sha: String)

data class CommitMessage(
    val raw: String,
    val subject: String?,
    val body: String?
) {

    companion object {

        fun fromRawMessage(message: String): CommitMessage {
            // title and body separated by new line
            return if (message.lines().size > 1 && message.lines()[1].trim().isEmpty()) {
                CommitMessage(
                    raw = message,
                    subject = message.lines().first(),
                    body = message.lines().drop(1).dropWhile { it.trim().isEmpty() }.joinToString(separator = "\n")
                )
            } else {
                CommitMessage(
                    raw = message,
                    subject = null,
                    body = null
                )
            }
        }
    }
}
