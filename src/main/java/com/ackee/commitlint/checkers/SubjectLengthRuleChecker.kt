package com.ackee.commitlint.checkers

import com.ackee.commitlint.Commit

class SubjectLengthRuleChecker : RuleChecker() {

    companion object {
        const val SUBJECT_LIMIT_SIZE = 50
    }

    override fun check(commits: List<Commit>) {
        commits.filter { it.message.subject != null }
            .forEach { commit ->
                if (commit.message.subject?.length ?: 0 >= SUBJECT_LIMIT_SIZE) {
                    warnings[commit.sha] = "Please limit commit subject line to $SUBJECT_LIMIT_SIZE characters."
                }
            }
    }
}