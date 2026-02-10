package com.rmworld.lintchecks

class IssueRegistry : com.android.tools.lint.client.api.IssueRegistry() {
    override val issues = kotlin.collections.listOf(
        SystemOutPrintLnDetector.Companion.SYSTEM_OUT_PRINTLN_USAGE,
        SystemOutPrintLnDetector.Companion.KOTLIN_IO_PRINTLN_USAGE
    )

    override val api: Int
        get() = com.android.tools.lint.detector.api.CURRENT_API

    override val minApi: Int
        get() = com.android.tools.lint.detector.api.CURRENT_API

    override val vendor: com.android.tools.lint.client.api.Vendor =
        com.android.tools.lint.client.api.Vendor(
            vendorName = "rmworld",
            feedbackUrl = "https://github.com/jeninjoseph/MultiModuleRickyMorty/issues",
        )

}