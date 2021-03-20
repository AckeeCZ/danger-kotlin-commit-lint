[ ![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.ackeecz/danger-kotlin-commit-lint/badge.svg)](https://maven-badges.herokuapp.com/maven-central/io.github.ackeecz/danger-kotlin-commit-lint)

# danger-kotlin commit lint plugin

This is a plugin for [danger-kotlin](https://github.com/danger/kotlin) that checks if commit messages align with
recommendations specified eg. [here](https://chris.beams.io/posts/git-commit/)

## Installation

Put

```kotlin
@file:DependsOn("io.github.ackeecz:danger-kotlin-commit-lint:x.y.z")
```

to the top of your Dangerfile

## Usage

There is a single method `CommitLintPlugin.check` that will perform validations on the commits.

Due to a design of danger-kotlin plugin SDK it does not have access to commits directly and for that reason you need to
pass messages to the plugin manually.

```kotlin
register CommitLintPlugin

    danger(args) {
        CommitLintPlugin.check(commits = git.commits.map { gitCommit ->
            Commit(CommitMessage.fromRawMessage(gitCommit.message), gitCommit.sha ?: "")
        })
    }
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

[GitMoji](https://gitmoji.carloscuesta.me/) characters are skipped in the beginning for `SUBJECT_CAPITALIZE`
and `SUBJECT_MULTIPLE_WORDS` rules.