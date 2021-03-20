package io.github.ackeecz.danger.commitlint.checkers

import com.google.common.truth.Truth
import io.github.ackeecz.danger.commitlint.Commit
import io.github.ackeecz.danger.commitlint.CommitMessage
import org.junit.Test


internal class TitleLengthRuleCheckerTest {

    private val shortTitle = "Title that is shortie"
    private val longTitle = "Title that is way too long to be marked by our glorious checker as too long title"

    @Test
    fun `should do nothing when title not defined`() {
        val commits = listOf(
            Commit(CommitMessage("something", null, null), "sha1")
        )
        val checker = SubjectLengthRuleChecker()
        checker.check(commits)
        Truth.assertThat(checker.warnings + checker.failures)
            .isEmpty()
    }

    @Test
    fun `should warn about too long title`() {
        val commits = listOf(
            Commit(
                CommitMessage(
                    "something",
                    longTitle,
                    null
                ), "sha1"
            )
        )
        val checker = SubjectLengthRuleChecker()
        checker.check(commits)
        Truth.assertThat(checker.warnings)
            .containsKey("sha1")
    }

    @Test
    fun `should do nothing about short title`() {
        val commits = listOf(
            Commit(
                CommitMessage(
                    "something",
                    shortTitle,
                    null
                ), "sha1"
            )
        )
        val checker = SubjectLengthRuleChecker()
        checker.check(commits)
        Truth.assertThat(checker.warnings)
            .isEmpty()
    }

    @Test
    fun `should warn multiple commit with long titles`() {
        val commits = listOf(
            Commit(
                CommitMessage(
                    "something",
                    longTitle,
                    null
                ), "sha1"
            ),
            Commit(
                CommitMessage(
                    "something",
                    longTitle,
                    null
                ), "sha2"
            )
        )
        val checker = SubjectLengthRuleChecker()
        checker.check(commits)
        Truth.assertThat(checker.warnings)
            .containsKey("sha1")
        Truth.assertThat(checker.warnings)
            .containsKey("sha2")
    }
}