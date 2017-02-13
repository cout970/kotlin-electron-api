package jsapi.electron

object process {

    private val module: dynamic = js("require('electron').process")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     * Causes the main thread of the current process crash.
     */
    fun crash(): Unit = 
        module.crash()

    /**
     * Causes the main thread of the current process hang.
     */
    fun hang(): Unit = 
        module.hang()

    /**
     * Sets the file descriptor soft limit to maxDescriptors or the OS hard limit, whichever is lower for the current process.
     */
    fun setFdLimit(maxDescriptors: Int): Unit = 
        module.setFdLimit(maxDescriptors)

    /**
     *  . workingSetSize Integer - The amount of memory currently pinned to actual physical RAM.
     *  . peakWorkingSetSize Integer - The maximum amount of memory that has ever been pinned to actual physical RAM.
     *  . privateBytes Integer - The amount of memory not shared by other processes, such as JS heap or HTML content.
     *  . sharedBytes Integer - The amount of memory shared between processes, typically memory consumed by the Electron code itself
     *
     */
    fun getProcessMemoryInfo(workingSetSize: Int, peakWorkingSetSize: Int, privateBytes: Int, sharedBytes: Int): dynamic = 
        module.getProcessMemoryInfo(workingSetSize, peakWorkingSetSize, privateBytes, sharedBytes)

    /**
     *  . total Integer - The total amount of physical memory in Kilobytes available to the system.
     *  . free Integer - The total amount of memory not being used by applications or disk cache.
     *  . swapTotal Integer - The total amount of swap memory in Kilobytes available to the system. WindowsLinux
     *  . swapFree Integer - The free amount of swap memory in Kilobytes available to the system. WindowsLinux
     *
     */
    fun getSystemMemoryInfo(total: Int, free: Int, swapTotal: Int, swapFree: Int): dynamic = 
        module.getSystemMemoryInfo(total, free, swapTotal, swapFree)

    // ~ Builders ------------------------------------------------------------------------------
}

