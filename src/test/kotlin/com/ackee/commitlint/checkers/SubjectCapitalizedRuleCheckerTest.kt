package com.ackee.commitlint.checkers

import com.ackee.commitlint.Commit
import com.ackee.commitlint.CommitMessage
import com.google.common.truth.Truth
import org.junit.Test

class SubjectCapitalizedRuleCheckerTest {

    private val lowerCasedMsg = "add bug"
    private val upperCasedMsg = "Add bug"
    private val upperCasedMsgGitmoji = "🐛 Add bug"
    private val lowerCasedMsgGitmoji = "🐛 add bug"

    @Test
    fun `should warn about lower cased msg`() {
        val commits = listOf(Commit(CommitMessage(lowerCasedMsg, null, null), "sha1"))
        val checker = SubjectCapitalizedRuleChecker()
        checker.check(commits)
        Truth.assertThat(checker.warnings)
            .containsKey("sha1")
    }

    @Test
    fun `should warn about lower cased msg with gitmoji`() {
        val commits = listOf(Commit(CommitMessage(lowerCasedMsgGitmoji, null, null), "sha1"))
        val checker = SubjectCapitalizedRuleChecker()
        checker.check(commits)
        Truth.assertThat(checker.warnings)
            .containsKey("sha1")
    }

    @Test
    fun `should not warn about upper cased msg with gitmoji`() {
        val commits = listOf(Commit(CommitMessage(upperCasedMsg, null, null), "sha1"))
        val checker = SubjectCapitalizedRuleChecker()
        checker.check(commits)
        Truth.assertThat(checker.warnings)
            .isEmpty()
    }

    @Test
    fun `should not warn about upper cased msg`() {
        val commits = listOf(Commit(CommitMessage(upperCasedMsgGitmoji, null, null), "sha1"))
        val checker = SubjectCapitalizedRuleChecker()
        checker.check(commits)
        Truth.assertThat(checker.warnings)
            .isEmpty()
    }
}