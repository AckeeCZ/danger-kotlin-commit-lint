package com.ackee.commitlint.checkers

import com.ackee.commitlint.Commit

class SubjectCapitalizedRuleChecker : RuleChecker() {

    override fun check(commits: List<Commit>) {
        return commits.forEach {
            val firstAsciiLetter = it.message.raw.skipGitmojis().firstOrNull()
            if (firstAsciiLetter != null && firstAsciiLetter.isLowerCase()) {
                warnings[it.sha] = "Please start subject with capital letter."
            }
        }
    }
}