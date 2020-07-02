package com.ackee.commitlint.checkers

import com.ackee.commitlint.Rule

fun checkerForRule(rule: Rule): RuleChecker {
    return when (rule) {
        Rule.SUBJECT_LENGTH -> SubjectLengthRuleChecker()
        Rule.BODY_LENGTH -> BodyLengthRuleChecker()
        Rule.SUBJECT_BODY_NEW_LINE -> SubjectBodyNewLineRuleChecker()
        Rule.SUBJECT_CAPITALIZE -> SubjectCapitalizedRuleChecker()
        Rule.SUBJECT_MULTIPLE_WORDS -> SubjectMultipleWordsRuleChecker()
        Rule.SUBJECT_ENDS_WITH_PERIOD -> SubjectEndingWithPeriodRuleChecker()
    }
}