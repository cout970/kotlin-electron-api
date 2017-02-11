package jsapi.electron

object crashReporter {

    private val module: dynamic = js("require('electron').crashReporter")

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun start(options: StartOptions): Unit = 
        module.start(options)

    fun getLastCrashReport(): CrashReport = 
        module.getLastCrashReport()

    fun getUploadedReports(): Array<CrashReport> = 
        module.getUploadedReports()

    fun getUploadToServer(): Boolean = 
        module.getUploadToServer()

    fun setUploadToServer(uploadToServer: Boolean): Unit = 
        module.setUploadToServer(uploadToServer)

    // ~ Builders -------------------------------------------------------------------------------

    class StartOptions(
        var companyName: String? = null, 
        var submitURL: String, 
        var productName: String? = null, 
        var uploadToServer: Boolean? = null, 
        var ignoreSystemCrashHandler: Boolean? = null, 
        var extra: StartExtra? = null
    )

    class StartExtra(
    )

}

