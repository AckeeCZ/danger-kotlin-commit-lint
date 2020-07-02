package com.ackee.commitlint.checkers

import com.ackee.commitlint.Commit
import com.ackee.commitlint.CommitMessage
import com.google.common.truth.Truth
import org.junit.Test

internal class SubjectEndingWithPeriodRuleCheckerTest {

    @Test
    fun `should warn about title with period`() {
        val commits = listOf(
            Commit(CommitMessage("", "Title with period.", null), "sha1")
        )
        val checker = SubjectEndingWithPeriodRuleChecker()
        checker.check(commits)
        Truth.assertThat(checker.warnings)
            .containsKey("sha1")
    }

    @Test
    fun `should do nothing about title without period`() {
        val commits = listOf(
            Commit(CommitMessage("", "Title without period", null), "sha1")
        )
        val checker = SubjectEndingWithPeriodRuleChecker()
        checker.check(commits)
        Truth.assertThat(checker.warnings + checker.failures)
            .isEmpty()
    }
}

