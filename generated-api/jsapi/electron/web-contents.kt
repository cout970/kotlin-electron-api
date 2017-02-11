package jsapi.electron

object webContents {

    private val module: dynamic = js("require('electron').webContents")

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun getAllWebContents(): Array<WebContents> = 
        module.getAllWebContents()

    fun getFocusedWebContents(): WebContents = 
        module.getFocusedWebContents()

    fun fromId(id: Int): WebContents = 
        module.fromId(id)

    // ~ Builders -------------------------------------------------------------------------------

}

class WebContents() {

    private val module: dynamic = js("require('electron').WebContents")

    val instance: dynamic

    init {
        val _constructor = js("require('electron').WebContents")
        instance = js("new _constructor(_)")
    }

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun loadURL(url: String, options: (LoadURLOptions.() -> Unit)?): Unit = 
        instance.loadURL(url, options?.let { LoadURLOptions().apply(it) })

    fun downloadURL(url: String): Unit = 
        instance.downloadURL(url)

    fun getURL(): String = 
        instance.getURL()

    fun getTitle(): String = 
        instance.getTitle()

    fun isDestroyed(): Boolean = 
        instance.isDestroyed()

    fun isFocused(): Boolean = 
        instance.isFocused()

    fun isLoading(): Boolean = 
        instance.isLoading()

    fun isLoadingMainFrame(): Boolean = 
        instance.isLoadingMainFrame()

    fun isWaitingForResponse(): Boolean = 
        instance.isWaitingForResponse()

    fun stop(): Unit = 
        instance.stop()

    fun reload(): Unit = 
        instance.reload()

    fun reloadIgnoringCache(): Unit = 
        instance.reloadIgnoringCache()

    fun canGoBack(): Boolean = 
        instance.canGoBack()

    fun canGoForward(): Boolean = 
        instance.canGoForward()

    fun canGoToOffset(offset: Int): Boolean = 
        instance.canGoToOffset(offset)

    fun clearHistory(): Unit = 
        instance.clearHistory()

    fun goBack(): Unit = 
        instance.goBack()

    fun goForward(): Unit = 
        instance.goForward()

    fun goToIndex(index: Int): Unit = 
        instance.goToIndex(index)

    fun goToOffset(offset: Int): Unit = 
        instance.goToOffset(offset)

    fun isCrashed(): Boolean = 
        instance.isCrashed()

    fun setUserAgent(userAgent: String): Unit = 
        instance.setUserAgent(userAgent)

    fun getUserAgent(): String = 
        instance.getUserAgent()

    fun insertCSS(css: String): Unit = 
        instance.insertCSS(css)

    fun executeJavaScript(code: String, userGesture: Boolean?, callback: ((result: Any) -> Unit)?): dynamic = 
        instance.executeJavaScript(code, userGesture, callback)

    fun setAudioMuted(muted: Boolean): Unit = 
        instance.setAudioMuted(muted)

    fun isAudioMuted(): Boolean = 
        instance.isAudioMuted()

    fun setZoomFactor(factor: Float): Unit = 
        instance.setZoomFactor(factor)

    fun getZoomFactor(callback: (zoomFactor: Float) -> Unit): Unit = 
        instance.getZoomFactor(callback)

    fun setZoomLevel(level: Float): Unit = 
        instance.setZoomLevel(level)

    fun getZoomLevel(callback: (zoomLevel: Float) -> Unit): Unit = 
        instance.getZoomLevel(callback)

    fun setZoomLevelLimits(minimumLevel: Float, maximumLevel: Float): Unit = 
        instance.setZoomLevelLimits(minimumLevel, maximumLevel)

    fun setVisualZoomLevelLimits(minimumLevel: Float, maximumLevel: Float): Unit = 
        instance.setVisualZoomLevelLimits(minimumLevel, maximumLevel)

    fun setLayoutZoomLevelLimits(minimumLevel: Float, maximumLevel: Float): Unit = 
        instance.setLayoutZoomLevelLimits(minimumLevel, maximumLevel)

    fun undo(): Unit = 
        instance.undo()

    fun redo(): Unit = 
        instance.redo()

    fun cut(): Unit = 
        instance.cut()

    fun copy(): Unit = 
        instance.copy()

    fun copyImageAt(x: Int, y: Int): Unit = 
        instance.copyImageAt(x, y)

    fun paste(): Unit = 
        instance.paste()

    fun pasteAndMatchStyle(): Unit = 
        instance.pasteAndMatchStyle()

    fun delete(): Unit = 
        instance.delete()

    fun selectAll(): Unit = 
        instance.selectAll()

    fun unselect(): Unit = 
        instance.unselect()

    fun replace(text: String): Unit = 
        instance.replace(text)

    fun replaceMisspelling(text: String): Unit = 
        instance.replaceMisspelling(text)

    fun insertText(text: String): Unit = 
        instance.insertText(text)

    fun findInPage(text: String, options: (FindInPageOptions.() -> Unit)?): Unit = 
        instance.findInPage(text, options?.let { FindInPageOptions().apply(it) })

    fun stopFindInPage(action: String): Unit = 
        instance.stopFindInPage(action)

    fun capturePage(rect: Rectangle?, callback: (image: NativeImage) -> Unit): Unit = 
        instance.capturePage(rect?.instance, callback)

    fun hasServiceWorker(callback: (hasWorker: Boolean) -> Unit): Unit = 
        instance.hasServiceWorker(callback)

    fun unregisterServiceWorker(callback: (success: Boolean) -> Unit): Unit = 
        instance.unregisterServiceWorker(callback)

    fun print(options: PrintOptions?): Unit = 
        instance.print(options)

    fun printToPDF(options: PrintToPDFOptions.() -> Unit, callback: (error: Error, data: dynamic) -> Unit): Unit = 
        instance.printToPDF(options.let { PrintToPDFOptions().apply(it) }, callback)

    fun addWorkSpace(path: String): Unit = 
        instance.addWorkSpace(path)

    fun removeWorkSpace(path: String): Unit = 
        instance.removeWorkSpace(path)

    fun openDevTools(options: OpenDevToolsOptions?): Unit = 
        instance.openDevTools(options)

    fun closeDevTools(): Unit = 
        instance.closeDevTools()

    fun isDevToolsOpened(): Boolean = 
        instance.isDevToolsOpened()

    fun isDevToolsFocused(): Boolean = 
        instance.isDevToolsFocused()

    fun toggleDevTools(): Unit = 
        instance.toggleDevTools()

    fun inspectElement(x: Int, y: Int): Unit = 
        instance.inspectElement(x, y)

    fun inspectServiceWorker(): Unit = 
        instance.inspectServiceWorker()

    fun send(channel: String, args: Array<dynamic>): Unit = 
        instance.send(channel, args)

    fun enableDeviceEmulation(parameters: EnableDeviceEmulationParameters): Unit = 
        instance.enableDeviceEmulation(parameters)

    fun disableDeviceEmulation(): Unit = 
        instance.disableDeviceEmulation()

    fun sendInputEvent(event: SendInputEventEvent): Unit = 
        instance.sendInputEvent(event)

    fun beginFrameSubscription(onlyDirty: Boolean?, callback: (frameBuffer: dynamic, dirtyRect: Rectangle) -> Unit): Unit = 
        instance.beginFrameSubscription(onlyDirty, callback)

    fun endFrameSubscription(): Unit = 
        instance.endFrameSubscription()

    fun startDrag(item: StartDragItem): Unit = 
        instance.startDrag(item)

    fun savePage(fullPath: String, saveType: String, callback: (error: Error) -> Unit): Boolean = 
        instance.savePage(fullPath, saveType, callback)

    fun showDefinitionForSelection(): Unit = 
        instance.showDefinitionForSelection()

    fun setSize(): Unit = 
        instance.setSize()

    fun isOffscreen(): Boolean = 
        instance.isOffscreen()

    fun startPainting(): Unit = 
        instance.startPainting()

    fun stopPainting(): Unit = 
        instance.stopPainting()

    fun isPainting(): Boolean = 
        instance.isPainting()

    fun setFrameRate(fps: Int): Unit = 
        instance.setFrameRate(fps)

    fun getFrameRate(): Int = 
        instance.getFrameRate()

    fun invalidate(): Unit = 
        instance.invalidate()

    // ~ Builders -------------------------------------------------------------------------------

    class LoadURLOptions(
        var httpReferrer: String? = null, 
        var userAgent: String? = null, 
        var extraHeaders: String? = null, 
        var postData: Array<dynamic>? = null
    )

    class FindInPageOptions(
        var forward: Boolean? = null, 
        var findNext: Boolean? = null, 
        var matchCase: Boolean? = null, 
        var wordStart: Boolean? = null, 
        var medialCapitalAsWordStart: Boolean? = null
    )

    class PrintOptions(
        var silent: Boolean, 
        var printBackground: Boolean
    )

    class PrintToPDFOptions(
        var marginsType: Int? = null, 
        var pageSize: String? = null, 
        var printBackground: Boolean? = null, 
        var printSelectionOnly: Boolean? = null, 
        var landscape: Boolean? = null
    )

    class OpenDevToolsOptions(
        var mode: String
    )

    class EnableDeviceEmulationParameters(
        var screenPosition: String, 
        var screenSize: EnableDeviceEmulationScreenSize, 
        var viewPosition: EnableDeviceEmulationViewPosition, 
        var deviceScaleFactor: Int, 
        var viewSize: EnableDeviceEmulationViewSize, 
        var fitToView: Boolean, 
        var offset: EnableDeviceEmulationOffset, 
        var scale: Float
    )

    class EnableDeviceEmulationScreenSize(
        var width: Int, 
        var height: Int
    )

    class EnableDeviceEmulationViewPosition(
        var x: Int, 
        var y: Int
    )

    class EnableDeviceEmulationViewSize(
        var width: Int, 
        var height: Int
    )

    class EnableDeviceEmulationOffset(
        var x: Float, 
        var y: Float
    )

    class SendInputEventEvent(
        var type: String, 
        var modifiers: Array<String>
    )

    class StartDragItem(
        var file: String, 
        var icon: NativeImage
    )

}

