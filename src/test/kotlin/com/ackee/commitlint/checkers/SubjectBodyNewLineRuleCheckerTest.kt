package com.ackee.commitlint.checkers

import com.ackee.commitlint.Commit
import com.ackee.commitlint.CommitMessage
import com.google.common.truth.Truth
import org.junit.Test

internal class SubjectBodyNewLineRuleCheckerTest {

    @Test
    fun `should do nothing for single line commit msg`() {
        val commits = listOf(
            Commit(CommitMessage("Some subject", null, null), "sha1")
        )
        val checker = SubjectBodyNewLineRuleChecker()
        checker.check(commits)
        Truth.assertThat(checker.warnings + checker.failures)
            .isEmpty()
    }

    @Test
    fun `should do nothing for separated subject and body with newline`() {
        val commits = listOf(
            Commit(
                CommitMessage(
                    raw = """
                        Some subject
            
                        Some body
                    """.trimIndent(),
                    subject = null,
                    body = null
                ), "sha1"
            )
        )
        val checker = SubjectBodyNewLineRuleChecker()
        checker.check(commits)
        Truth.assertThat(checker.warnings + checker.failures)
            .isEmpty()
    }

    @Test
    fun `should warn about multiline msg`() {
        val commits = listOf(
            Commit(
                CommitMessage(
                    raw = """
                        Some subject
                        Some body
                    """.trimIndent(),
                    subject = null,
                    body = null
                ), "sha1"
            )
        )
        val checker = SubjectBodyNewLineRuleChecker()
        checker.check(commits)
        Truth.assertThat(checker.warnings )
            .containsKey("sha1")
    }
}