package io.github.ackeecz.danger.commitlint.checkers

import io.github.ackeecz.danger.commitlint.Commit

internal abstract class RuleChecker {

    val failures: MutableMap<String, String> = mutableMapOf()
    val warnings: MutableMap<String, String> = mutableMapOf()

    abstract fun check(commits: List<Commit>)
}