package jsapi.electron

object process {

    private val module: dynamic = js("require('electron').process")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun crash(): Unit = 
        module.crash()

    fun hang(): Unit = 
        module.hang()

    fun setFdLimit(maxDescriptors: Int): Unit = 
        module.setFdLimit(maxDescriptors)

    fun getProcessMemoryInfo(): dynamic = 
        module.getProcessMemoryInfo()

    fun getSystemMemoryInfo(): dynamic = 
        module.getSystemMemoryInfo()

    // ~ Builders ------------------------------------------------------------------------------
}

