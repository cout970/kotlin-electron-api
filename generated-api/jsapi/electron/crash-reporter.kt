package jsapi.electron

object crashReporter {

    private val module: dynamic = js("require('electron').crashReporter")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     * You are required to call this method before using any other crashReporter APIs 
     * and in each process (main/renderer) from which you want to collect crash 
     * reports. You can pass different options to crashReporter.start when calling 
     * from different processes.
     *
     * Note Child processes created via the child_process module will not have access 
     * to the Electron modules. Therefore, to collect crash reports from them, use 
     * process.crashReporter.start instead. Pass the same options as above along with 
     * an additional one called crashesDirectory that should point to a directory to 
     * store the crash reports temporarily. You can test this out by calling 
     * process.crash() to crash the child process.
     *
     * Note: To collect crash reports from child process in Windows, you need to add 
     * this extra code as well. This will start the process that will monitor and 
     * send the crash reports. Replace submitURL, productName and crashesDirectory 
     * with appropriate values.
     *
     *  | 
     *  |  const args = [
     *  |    `--reporter-url=${submitURL}`,
     *  |    `--application-name=${productName}`,
     *  |    `--crashes-directory=${crashesDirectory}`
     *  |  ]
     *  |  const env = {
     *  |    ELECTRON_INTERNAL_CRASH_SERVICE: 1
     *  |  }
     *  |  spawn(process.execPath, args, {
     *  |    env: env,
     *  |    detached: true
     *  |  })
     *  | 
     *
     * Note: On macOS, Electron uses a new crashpad client for crash collection and 
     * reporting. If you want to enable crash reporting, initializing crashpad from 
     * the main process using crashReporter.start is required regardless of which 
     * process you want to collect crashes from. Once initialized this way, the 
     * crashpad handler collects crashes from all processes. You still have to call 
     * crashReporter.start from the renderer or child process, otherwise crashes from 
     * them will get reported without companyName, productName or any of the extra 
     * information.
     */
    fun start(options: StartOptions): Unit = 
        module.start(options)

    /**
     */
    fun getLastCrashReport(): CrashReport = 
        module.getLastCrashReport()

    /**
     */
    fun getUploadedReports(): Array<CrashReport> = 
        module.getUploadedReports()

    /**
     * Note: This API can only be called from the main process.
     */
    fun getUploadToServer(): Boolean = 
        module.getUploadToServer()

    /**
     * This would normally be controlled by user preferences. This has no effect if 
     * called before start is called.
     *
     * Note: This API can only be called from the main process.
     */
    fun setUploadToServer(uploadToServer: Boolean): Unit = 
        module.setUploadToServer(uploadToServer)

    // ~ Builders ------------------------------------------------------------------------------

    class StartOptions(
        var companyName: String? = null,
        var submitURL: String,
        var productName: String? = null,
        var uploadToServer: Boolean? = null,
        var ignoreSystemCrashHandler: Boolean? = null,
        var extra: (StartExtra.() -> Unit)? = null
    )
    class StartExtra(
    )

}

