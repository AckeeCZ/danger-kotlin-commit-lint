package io.github.ackeecz.danger.commitlint.checkers

import io.github.ackeecz.danger.commitlint.Commit

internal class BodyLengthRuleChecker : RuleChecker() {

    override fun check(commits: List<Commit>) {
        commits.filter { it.message.body != null }
            .forEach { commit ->
                if (commit.message.body?.lines()?.any { line -> line.length >= BODY_LIMIT_SIZE } == true) {
                    warnings[commit.sha] = "Please limit commit body line to $BODY_LIMIT_SIZE characters."
                }
            }
    }

    companion object {
        const val BODY_LIMIT_SIZE = 72
    }
}