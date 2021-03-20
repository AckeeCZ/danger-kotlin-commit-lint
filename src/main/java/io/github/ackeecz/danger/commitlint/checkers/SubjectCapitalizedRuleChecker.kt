package io.github.ackeecz.danger.commitlint.checkers

import io.github.ackeecz.danger.commitlint.Commit

internal class SubjectCapitalizedRuleChecker : RuleChecker() {

    override fun check(commits: List<Commit>) {
        return commits.forEach {
            val firstAsciiLetter = it.message.raw.skipGitmojis().firstOrNull()
            if (firstAsciiLetter != null && firstAsciiLetter.isLowerCase()) {
                warnings[it.sha] = "Please start subject with capital letter."
            }
        }
    }
}