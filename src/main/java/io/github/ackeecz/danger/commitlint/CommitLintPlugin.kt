package io.github.ackeecz.danger.commitlint

import io.github.ackeecz.danger.commitlint.checkers.checkerForRule
import systems.danger.kotlin.sdk.DangerPlugin

/**
 * danger-kotlin plugin to perform lint on a git commits. This plugin is opinionated
 * about how a great commit message should look and it checks rules from [this](https://chris.beams.io/posts/git-commit/)
 * list
 */
object CommitLintPlugin : DangerPlugin() {

    override val id = "commit-lint-plugin"

    fun check(commits: List<Commit>, rules: List<Rule> = Rule.values().toList()) {
        rules.forEach { rule ->
            checkRule(rule, commits)
        }
    }

    private fun checkRule(rule: Rule, commits: List<Commit>) {
        val checker = checkerForRule(rule)
        checker.check(commits)
        checker.warnings.forEach { (sha, message) ->
            context.warn("$sha $message")
        }
        checker.failures.forEach { (sha, message) ->
            context.fail("$sha $message")
        }
    }
}

enum class Rule {
    SUBJECT_LENGTH,
    BODY_LENGTH,
    SUBJECT_BODY_NEW_LINE,
    SUBJECT_CAPITALIZE,
    SUBJECT_MULTIPLE_WORDS,
    SUBJECT_ENDS_WITH_PERIOD
}