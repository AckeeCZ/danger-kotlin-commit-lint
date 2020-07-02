package com.ackee.commitlint.checkers

import com.ackee.commitlint.Commit

class SubjectEndingWithPeriodRuleChecker : RuleChecker() {

    override fun check(commits: List<Commit>) {
        commits.forEach { commit ->
             if(commit.message.subject?.endsWith(".") == true ) {
                 warnings[commit.sha] = "Please do not end the subject line with a period."
             }
        }
    }
}