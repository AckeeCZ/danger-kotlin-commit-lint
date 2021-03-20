package io.github.ackeecz.danger.commitlint.checkers

import io.github.ackeecz.danger.commitlint.Commit

internal class SubjectLengthRuleChecker : RuleChecker() {

    override fun check(commits: List<Commit>) {
        commits.filter { it.message.subject != null }
            .forEach { commit ->
                if (commit.message.subject?.length ?: 0 >= SUBJECT_LIMIT_SIZE) {
                    warnings[commit.sha] = "Please limit commit subject line to $SUBJECT_LIMIT_SIZE characters."
                }
            }
    }

    companion object {
        const val SUBJECT_LIMIT_SIZE = 50
    }
}