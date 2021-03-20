package io.github.ackeecz.danger.commitlint.checkers

import io.github.ackeecz.danger.commitlint.Commit

internal class SubjectEndingWithPeriodRuleChecker : RuleChecker() {

    override fun check(commits: List<Commit>) {
        commits.forEach { commit ->
            if (commit.message.subject?.endsWith(".") == true) {
                warnings[commit.sha] = "Please do not end the subject line with a period."
            }
        }
    }
}