package jsapi.electron

object contentTracing {

    private val module: dynamic = js("require('electron').contentTracing")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun getCategories(callback: (categories: Array<String>) -> Unit): Unit = 
        module.getCategories(callback)

    fun startRecording(options: StartRecordingOptions, callback: () -> Unit): Unit = 
        module.startRecording(options, callback)

    fun stopRecording(resultFilePath: String, callback: (resultFilePath: String) -> Unit): Unit = 
        module.stopRecording(resultFilePath, callback)

    fun startMonitoring(options: StartMonitoringOptions, callback: () -> Unit): Unit = 
        module.startMonitoring(options, callback)

    fun stopMonitoring(callback: () -> Unit): Unit = 
        module.stopMonitoring(callback)

    fun captureMonitoringSnapshot(resultFilePath: String, callback: (resultFilePath: String) -> Unit): Unit = 
        module.captureMonitoringSnapshot(resultFilePath, callback)

    fun getTraceBufferUsage(callback: (value: Number, percentage: Number) -> Unit): Unit = 
        module.getTraceBufferUsage(callback)

    // ~ Builders ------------------------------------------------------------------------------

    class StartRecordingOptions(
        var categoryFilter: String,
        var traceOptions: String
    )

    class StartMonitoringOptions(
        var categoryFilter: String,
        var traceOptions: String
    )
}

