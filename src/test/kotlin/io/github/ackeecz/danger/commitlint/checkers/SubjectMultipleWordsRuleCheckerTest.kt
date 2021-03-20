package io.github.ackeecz.danger.commitlint.checkers

import com.google.common.truth.Truth
import io.github.ackeecz.danger.commitlint.Commit
import io.github.ackeecz.danger.commitlint.CommitMessage
import org.junit.Test

internal class SubjectMultipleWordsRuleCheckerTest {

    private val singleWordedMessage = "WIP"
    private val singleWordedMessageWithGitmoji = "🐛 WIP"
    private val multipleWordedMessage = "Add bug"
    private val multipleWordedMessageWithGitmoji = "🐛 Add bug"

    @Test
    fun `should do nothing for multiple worded message`() {
        val commits = listOf(
            Commit(CommitMessage(multipleWordedMessage, null, null), "sha1")
        )
        val checker = SubjectMultipleWordsRuleChecker()
        checker.check(commits)
        Truth.assertThat(checker.warnings + checker.failures)
            .isEmpty()
    }

    @Test
    fun `should do nothing for multiple worded message with gitmoji`() {
        val commits = listOf(
            Commit(CommitMessage(multipleWordedMessageWithGitmoji, null, null), "sha1")
        )
        val checker = SubjectMultipleWordsRuleChecker()
        checker.check(commits)
        Truth.assertThat(checker.warnings + checker.failures)
            .isEmpty()
    }

    @Test
    fun `should warn for single worded message`() {
        val commits = listOf(
            Commit(CommitMessage(singleWordedMessage, null, null), "sha1")
        )
        val checker = SubjectMultipleWordsRuleChecker()
        checker.check(commits)
        Truth.assertThat(checker.warnings)
            .containsKey("sha1")
    }

    @Test
    fun `should warn for single worded message with gitmoji`() {
        val commits = listOf(
            Commit(CommitMessage(singleWordedMessageWithGitmoji, null, null), "sha1")
        )
        val checker = SubjectMultipleWordsRuleChecker()
        checker.check(commits)
        Truth.assertThat(checker.warnings )
            .containsKey("sha1")
    }
}