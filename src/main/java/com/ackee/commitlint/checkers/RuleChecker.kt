package com.ackee.commitlint.checkers

import com.ackee.commitlint.Commit

abstract class RuleChecker {
    val failures: MutableMap<String, String> = mutableMapOf()
    val warnings: MutableMap<String, String> = mutableMapOf()

    abstract fun check(commits: List<Commit>)
}