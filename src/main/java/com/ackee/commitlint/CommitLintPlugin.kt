package com.ackee.commitlint

import com.ackee.commitlint.checkers.checkerForRule
import systems.danger.kotlin.sdk.DangerPlugin

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