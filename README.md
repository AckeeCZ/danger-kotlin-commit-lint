# Commit Lint plugin for danger-kotlin

This is a plugin for [danger-kotlin](https://github.com/danger/kotlin) that checks if commit messages align with 
recommendations specified eg. [here](chris.beams.io/posts/git-commit/)

## Usage

There is a single method `CommitLintPlugin.check` that will perform validations on the commits.

Due to a current limitation of danger-kotlin when creating plugins we don't have access to commits 
directly from Danger. For that reason you need to pass messages manually.

```kotlin
CommitLintPlugin.check(commits = git.commits.map { gitCommit ->
    Commit(CommitMessage.fromRawMessage(gitCommit.message), gitCommit.sha ?: "")
})
```

### Rules specification

You can enable only some rules by passing them to the `check` function. By default, there are these rules:

| Name | Description
|------|------------|
| SUBJECT_LENGTH | Length of the subject is less than 50 characters |
| BODY_LENGTH | Length of the body is less than 72 characters |
| SUBJECT_BODY_NEW_LINE | Subject and body is separated with newline |
| SUBJECT_CAPITALIZE | Subject should start with capital letter |
| SUBJECT_MULTIPLE_WORDS | Subject have more than one word |
| SUBJECT_ENDS_WITH_PERIOD | Subject does not ends with a period |


If you want to disable some rules you can do

```kotlin
CommitLintPlugin.check(commits, Rule.values().toList() - Rule.SUBJECT_CAPITALIZE)
```

### GitMoji first

[GitMoji](https://gitmoji.carloscuesta.me/) characters are skipped in the beginning for `SUBJECT_CAPITALIZE` and `SUBJECT_MULTIPLE_WORDS` rules.

## Installation
TODO