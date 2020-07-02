package com.ackee.commitlint.checkers

import com.ackee.commitlint.Commit

class SubjectMultipleWordsRuleChecker : RuleChecker() {

    override fun check(commits: List<Commit>) {
        commits.forEach { commit ->
            val normalizedMessage = commit.message.raw.skipGitmojis()
            if (normalizedMessage.split(" ").size < 2) {
                warnings[commit.sha] = "Please use more than one word."
            }
        }
    }
}