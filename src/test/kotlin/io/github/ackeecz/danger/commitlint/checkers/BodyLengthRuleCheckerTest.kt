package io.github.ackeecz.danger.commitlint.checkers

import com.google.common.truth.Truth
import io.github.ackeecz.danger.commitlint.Commit
import io.github.ackeecz.danger.commitlint.CommitMessage
import org.junit.Test


internal class BodyLengthRuleCheckerTest {

    private val longBody = "Body that is way too long to be marked by our glorious checker as too long body"
    private val multilineBodyWithNoLongLines = """
        First line Fox Fox Fox
        Second line Fox Fox Fox
        Third line Fox Fox Fox
    """.trimIndent()
    private val multilineBodyWithLongLine = """
        First line Fox Fox Fox
        Second line Fox Fox Fox Second line Fox Fox Fox Second line Fox Fox Fox Second line Fox Fox Fox
        Third line Fox Fox Fox
    """.trimIndent()
    private val shortBody = "Body that is shortie"

    @Test
    fun `should do nothing when body not defined`() {
        val commits = listOf(
            Commit(CommitMessage("something", null, null), "sha1")
        )
        val checker = BodyLengthRuleChecker()
        checker.check(commits)
        Truth.assertThat(checker.warnings + checker.failures)
            .isEmpty()
    }

    @Test
    fun `should warn about too long body`() {
        val commits = listOf(
            Commit(
                CommitMessage(
                    "something",
                    null,
                    longBody
                ), "sha1"
            )
        )
        val checker = BodyLengthRuleChecker()
        checker.check(commits)
        Truth.assertThat(checker.warnings)
            .containsKey("sha1")
    }

    @Test
    fun `should warn about multilined body with some long lines`() {
        val commits = listOf(
            Commit(
                CommitMessage(
                    "something",
                    null,
                    multilineBodyWithLongLine
                ), "sha1"
            )
        )
        val checker = BodyLengthRuleChecker()
        checker.check(commits)
        Truth.assertThat(checker.warnings)
            .containsKey("sha1")
    }


    @Test
    fun `should do nothing about short body`() {
        val commits = listOf(
            Commit(
                CommitMessage(
                    "something",
                    null,
                    shortBody
                ), "sha1"
            )
        )
        val checker = BodyLengthRuleChecker()
        checker.check(commits)
        Truth.assertThat(checker.warnings)
            .isEmpty()
    }

    @Test
    fun `should warn multiple commit with long bodies`() {
        val commits = listOf(
            Commit(
                CommitMessage(
                    "something",
                    null,
                    longBody
                ), "sha1"
            ),
            Commit(
                CommitMessage(
                    "something",
                    null,
                    longBody
                ), "sha2"
            )
        )
        val checker = BodyLengthRuleChecker()
        checker.check(commits)
        Truth.assertThat(checker.warnings)
            .containsKey("sha1")
        Truth.assertThat(checker.warnings)
            .containsKey("sha2")
    }

    @Test
    fun `should do nothing about short multi lined body`() {
        val commits = listOf(
            Commit(
                CommitMessage(
                    "something",
                    null,
                    multilineBodyWithNoLongLines
                ), "sha1"
            )
        )
        val checker = BodyLengthRuleChecker()
        checker.check(commits)
        Truth.assertThat(checker.warnings)
            .isEmpty()
    }
}