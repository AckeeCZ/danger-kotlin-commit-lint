package io.github.ackeecz.danger.commitlint.checkers

import com.google.common.truth.Truth
import io.github.ackeecz.danger.commitlint.CommitMessage
import org.junit.Test

internal class CommitMessageTest {

    @Test
    fun `should parse body and text`() {
        // arrange
        val message = """
            Add something

            Some commit message
        """.trimIndent()

        // act
        val toTest = CommitMessage.fromRawMessage(message)

        // assert
        Truth.assertThat(toTest.subject)
                .isEqualTo("Add something")
        Truth.assertThat(toTest.body)
                .isEqualTo("Some commit message")
    }

    @Test
    fun `should keep title multilined`() {
        // arrange
        val message = """
            Add something
            Some commit message
        """.trimIndent()

        // act
        val toTest = CommitMessage.fromRawMessage(message)

        // assert
        Truth.assertThat(toTest.subject)
                .isNull()
        Truth.assertThat(toTest.body)
                .isNull()
    }
}
