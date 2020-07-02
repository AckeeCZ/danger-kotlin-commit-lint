package com.ackee.commitlint.checkers

import com.ackee.commitlint.Commit

class BodyLengthRuleChecker : RuleChecker() {

    companion object {
        const val BODY_LIMIT_SIZE = 72
    }

    override fun check(commits: List<Commit>) {
        commits.filter { it.message.body != null }
            .forEach { commit ->
                if (commit.message.body?.length ?: 0 >= BODY_LIMIT_SIZE) {
                    warnings[commit.sha] = "Please limit commit body line to $BODY_LIMIT_SIZE characters."
                }
            }
    }
}