package io.github.ackeecz.danger.commitlint.checkers

import io.github.ackeecz.danger.commitlint.Commit

internal class SubjectBodyNewLineRuleChecker : RuleChecker() {

    override fun check(commits: List<Commit>) {
        commits.forEach { commit ->
            val rawMessageLines = commit.message.raw.lines()
            if (rawMessageLines.size > 1 && rawMessageLines[1].trim().isNotEmpty()) {
                warnings[commit.sha] = "Please separate subject and body with a newline."
            }
        }
    }
}