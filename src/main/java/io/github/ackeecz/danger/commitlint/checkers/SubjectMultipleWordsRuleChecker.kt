package io.github.ackeecz.danger.commitlint.checkers

import io.github.ackeecz.danger.commitlint.Commit

internal class SubjectMultipleWordsRuleChecker : RuleChecker() {

    override fun check(commits: List<Commit>) {
        commits.forEach { commit ->
            val normalizedMessage = commit.message.raw.skipGitmojis()
            if (normalizedMessage.split(" ").size < 2) {
                warnings[commit.sha] = "Please use more than one word."
            }
        }
    }
}