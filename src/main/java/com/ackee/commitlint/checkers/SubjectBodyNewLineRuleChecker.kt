package com.ackee.commitlint.checkers

import com.ackee.commitlint.Commit

class SubjectBodyNewLineRuleChecker : RuleChecker() {

    override fun check(commits: List<Commit>) {
        commits.forEach { commit ->
            val rawMessageLines = commit.message.raw.lines()
            if (rawMessageLines.size > 1 && rawMessageLines[1].trim().isNotEmpty()) {
                warnings[commit.sha] = "Please separate subject and body with a newline."
            }
        }
    }
}