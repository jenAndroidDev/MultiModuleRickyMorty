package com.rmworld.lintchecks

import com.android.tools.lint.detector.api.Category.Companion.MESSAGES
import com.android.tools.lint.detector.api.Detector
import com.android.tools.lint.detector.api.Implementation
import com.android.tools.lint.detector.api.Issue
import com.android.tools.lint.detector.api.JavaContext
import com.android.tools.lint.detector.api.LintFix
import com.android.tools.lint.detector.api.Scope.Companion.JAVA_FILE_SCOPE
import com.intellij.psi.PsiMethod
import org.jetbrains.uast.UCallExpression
import org.jetbrains.uast.UExpression
import org.jetbrains.uast.UQualifiedReferenceExpression
import com.android.tools.lint.detector.api.Severity.ERROR

class SystemOutPrintLnDetector: Detector(), Detector.UastScanner {

    companion object {
        @JvmField
        val SYSTEM_OUT_PRINTLN_USAGE: Issue = Issue.create(
            id = "SystemOutPrintLnUsage",
            briefDescription = "Usage of System.out.println/print",
            explanation = "System.out.println/print should not be used in production code. Use Timber or Custom Logger instead.",
            category = MESSAGES,
            priority = 5,
            severity = ERROR,
            implementation = Implementation(SystemOutPrintLnDetector::class.java, JAVA_FILE_SCOPE)
        )

        @JvmField
        val KOTLIN_IO_PRINTLN_USAGE: Issue = Issue.create(
            id = "KotlinIOPrintLnUsage",
            briefDescription = "Usage of kotlin.io.println",
            explanation = "kotlin.io.println should not be used in production code. Use Timber or Custom Logger Instead.",
            category = MESSAGES,
            priority = 5,
            severity = ERROR,
            implementation = Implementation(SystemOutPrintLnDetector::class.java, JAVA_FILE_SCOPE)
        )
    }

    override fun getApplicableMethodNames(): List<String>? {
        return listOf("print","println")
    }

    override fun visitMethodCall(context: JavaContext, node: UCallExpression, method: PsiMethod) {
        val evaluator = context.evaluator

        if (evaluator.isMemberInClass(method, "java.io.PrintStream")){
            context.report(
                issue = SYSTEM_OUT_PRINTLN_USAGE,
                scope = node,
                location = context.getLocation(node),
                message = "Using 'System.out.${method.name}' instead of Custom Logger or Timber Logger",
                quickfixData = createQuickFix(node)
            )
        }
        if (evaluator.isMemberInClass(method, "kotlin.io.ConsoleKt")) {
            context.report(
                issue = KOTLIN_IO_PRINTLN_USAGE,
                scope = node,
                location = context.getLocation(node),
                message = "Using 'kotlin.io.println' instead of Custom or Timber Logger.",
                quickfixData = createQuickFix(node)
            )
        }

    }

    private fun createQuickFix(node: UCallExpression): LintFix {
        val arguments = node.valueArguments
        val message = if (arguments.isNotEmpty()) arguments[0].asSourceString() else "\"\""

        val fixSource = "Log.d(\"TAG\", $message)"

        return fix()
            .group()
            .add(
                fix()
                    .replace()
                    .text(node.sourcePsi?.text)
                    .shortenNames()
                    .reformat(true)
                    .with(fixSource)
                    .build()
            )
            .build()
    }
}
