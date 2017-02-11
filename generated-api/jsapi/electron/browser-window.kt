package jsapi.electron

class BrowserWindow(options: (Options.() -> Unit)?) {

    private val module: dynamic = js("require('electron').BrowserWindow")

    val instance: dynamic

    init {
        val _constructor = js("require('electron').BrowserWindow")
        val _options = options?.let { Options().apply(it) }
        instance = js("new _constructor(_options)")
    }

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun getAllWindows(): Array<BrowserWindow> = 
        module.getAllWindows()

    fun getFocusedWindow(): BrowserWindow = 
        module.getFocusedWindow()

    fun fromWebContents(webContents: WebContents): BrowserWindow = 
        module.fromWebContents(webContents.instance)

    fun fromId(id: Int): BrowserWindow = 
        module.fromId(id)

    fun addDevToolsExtension(path: String): Unit = 
        module.addDevToolsExtension(path)

    fun removeDevToolsExtension(name: String): Unit = 
        module.removeDevToolsExtension(name)

    fun getDevToolsExtensions(): dynamic = 
        module.getDevToolsExtensions()

    fun destroy(): Unit = 
        instance.destroy()

    fun close(): Unit = 
        instance.close()

    fun focus(): Unit = 
        instance.focus()

    fun blur(): Unit = 
        instance.blur()

    fun isFocused(): Boolean = 
        instance.isFocused()

    fun isDestroyed(): Boolean = 
        instance.isDestroyed()

    fun show(): Unit = 
        instance.show()

    fun showInactive(): Unit = 
        instance.showInactive()

    fun hide(): Unit = 
        instance.hide()

    fun isVisible(): Boolean = 
        instance.isVisible()

    fun isModal(): Boolean = 
        instance.isModal()

    fun maximize(): Unit = 
        instance.maximize()

    fun unmaximize(): Unit = 
        instance.unmaximize()

    fun isMaximized(): Boolean = 
        instance.isMaximized()

    fun minimize(): Unit = 
        instance.minimize()

    fun restore(): Unit = 
        instance.restore()

    fun isMinimized(): Boolean = 
        instance.isMinimized()

    fun setFullScreen(flag: Boolean): Unit = 
        instance.setFullScreen(flag)

    fun isFullScreen(): Boolean = 
        instance.isFullScreen()

    fun setAspectRatio(aspectRatio: Float, extraSize: SetAspectRatioExtraSize?): Unit = 
        instance.setAspectRatio(aspectRatio, extraSize)

    fun previewFile(path: String, displayName: String?): Unit = 
        instance.previewFile(path, displayName)

    fun closeFilePreview(): Unit = 
        instance.closeFilePreview()

    fun setBounds(bounds: Rectangle, animate: Boolean?): Unit = 
        instance.setBounds(bounds.instance, animate)

    fun getBounds(): Rectangle = 
        instance.getBounds()

    fun setContentBounds(bounds: Rectangle, animate: Boolean?): Unit = 
        instance.setContentBounds(bounds.instance, animate)

    fun getContentBounds(): Rectangle = 
        instance.getContentBounds()

    fun setSize(width: Int, height: Int, animate: Boolean?): Unit = 
        instance.setSize(width, height, animate)

    fun getSize(): Array<Int> = 
        instance.getSize()

    fun setContentSize(width: Int, height: Int, animate: Boolean?): Unit = 
        instance.setContentSize(width, height, animate)

    fun getContentSize(): Array<Int> = 
        instance.getContentSize()

    fun setMinimumSize(width: Int, height: Int): Unit = 
        instance.setMinimumSize(width, height)

    fun getMinimumSize(): Array<Int> = 
        instance.getMinimumSize()

    fun setMaximumSize(width: Int, height: Int): Unit = 
        instance.setMaximumSize(width, height)

    fun getMaximumSize(): Array<Int> = 
        instance.getMaximumSize()

    fun setResizable(resizable: Boolean): Unit = 
        instance.setResizable(resizable)

    fun isResizable(): Boolean = 
        instance.isResizable()

    fun setMovable(movable: Boolean): Unit = 
        instance.setMovable(movable)

    fun isMovable(): Boolean = 
        instance.isMovable()

    fun setMinimizable(minimizable: Boolean): Unit = 
        instance.setMinimizable(minimizable)

    fun isMinimizable(): Boolean = 
        instance.isMinimizable()

    fun setMaximizable(maximizable: Boolean): Unit = 
        instance.setMaximizable(maximizable)

    fun isMaximizable(): Boolean = 
        instance.isMaximizable()

    fun setFullScreenable(fullscreenable: Boolean): Unit = 
        instance.setFullScreenable(fullscreenable)

    fun isFullScreenable(): Boolean = 
        instance.isFullScreenable()

    fun setClosable(closable: Boolean): Unit = 
        instance.setClosable(closable)

    fun isClosable(): Boolean = 
        instance.isClosable()

    fun setAlwaysOnTop(flag: Boolean, level: String?, relativeLevel: Int?): Unit = 
        instance.setAlwaysOnTop(flag, level, relativeLevel)

    fun isAlwaysOnTop(): Boolean = 
        instance.isAlwaysOnTop()

    fun center(): Unit = 
        instance.center()

    fun setPosition(x: Int, y: Int, animate: Boolean?): Unit = 
        instance.setPosition(x, y, animate)

    fun getPosition(): Array<Int> = 
        instance.getPosition()

    fun setTitle(title: String): Unit = 
        instance.setTitle(title)

    fun getTitle(): String = 
        instance.getTitle()

    fun setSheetOffset(offsetY: Float, offsetX: Float?): Unit = 
        instance.setSheetOffset(offsetY, offsetX)

    fun flashFrame(flag: Boolean): Unit = 
        instance.flashFrame(flag)

    fun setSkipTaskbar(skip: Boolean): Unit = 
        instance.setSkipTaskbar(skip)

    fun setKiosk(flag: Boolean): Unit = 
        instance.setKiosk(flag)

    fun isKiosk(): Boolean = 
        instance.isKiosk()

    fun getNativeWindowHandle(): dynamic = 
        instance.getNativeWindowHandle()

    fun hookWindowMessage(message: Int, callback: () -> Unit): Unit = 
        instance.hookWindowMessage(message, callback)

    fun isWindowMessageHooked(message: Int): Boolean = 
        instance.isWindowMessageHooked(message)

    fun unhookWindowMessage(message: Int): Unit = 
        instance.unhookWindowMessage(message)

    fun unhookAllWindowMessages(): Unit = 
        instance.unhookAllWindowMessages()

    fun setRepresentedFilename(filename: String): Unit = 
        instance.setRepresentedFilename(filename)

    fun getRepresentedFilename(): String = 
        instance.getRepresentedFilename()

    fun setDocumentEdited(edited: Boolean): Unit = 
        instance.setDocumentEdited(edited)

    fun isDocumentEdited(): Boolean = 
        instance.isDocumentEdited()

    fun focusOnWebView(): Unit = 
        instance.focusOnWebView()

    fun blurWebView(): Unit = 
        instance.blurWebView()

    fun capturePage(rect: Rectangle?, callback: (image: NativeImage) -> Unit): Unit = 
        instance.capturePage(rect?.instance, callback)

    fun loadURL(url: String, options: (LoadURLOptions.() -> Unit)?): Unit = 
        instance.loadURL(url, options?.let { LoadURLOptions().apply(it) })

    fun reload(): Unit = 
        instance.reload()

    fun setMenu(menu: Menu): Unit = 
        instance.setMenu(menu.instance)

    fun setProgressBar(progress: Double, options: SetProgressBarOptions?): Unit = 
        instance.setProgressBar(progress, options)

    fun setOverlayIcon(overlay: NativeImage, description: String): Unit = 
        instance.setOverlayIcon(overlay.instance, description)

    fun setHasShadow(hasShadow: Boolean): Unit = 
        instance.setHasShadow(hasShadow)

    fun hasShadow(): Boolean = 
        instance.hasShadow()

    fun setThumbarButtons(buttons: Array<ThumbarButton>): Boolean = 
        instance.setThumbarButtons(buttons)

    fun setThumbnailClip(region: Rectangle): Unit = 
        instance.setThumbnailClip(region.instance)

    fun setThumbnailToolTip(toolTip: String): Unit = 
        instance.setThumbnailToolTip(toolTip)

    fun setAppDetails(options: SetAppDetailsOptions.() -> Unit): Unit = 
        instance.setAppDetails(options.let { SetAppDetailsOptions().apply(it) })

    fun showDefinitionForSelection(): Unit = 
        instance.showDefinitionForSelection()

    fun setIcon(icon: NativeImage): Unit = 
        instance.setIcon(icon.instance)

    fun setAutoHideMenuBar(hide: Boolean): Unit = 
        instance.setAutoHideMenuBar(hide)

    fun isMenuBarAutoHide(): Boolean = 
        instance.isMenuBarAutoHide()

    fun setMenuBarVisibility(visible: Boolean): Unit = 
        instance.setMenuBarVisibility(visible)

    fun isMenuBarVisible(): Boolean = 
        instance.isMenuBarVisible()

    fun setVisibleOnAllWorkspaces(visible: Boolean): Unit = 
        instance.setVisibleOnAllWorkspaces(visible)

    fun isVisibleOnAllWorkspaces(): Boolean = 
        instance.isVisibleOnAllWorkspaces()

    fun setIgnoreMouseEvents(ignore: Boolean): Unit = 
        instance.setIgnoreMouseEvents(ignore)

    fun setContentProtection(enable: Boolean): Unit = 
        instance.setContentProtection(enable)

    fun setFocusable(focusable: Boolean): Unit = 
        instance.setFocusable(focusable)

    fun setParentWindow(parent: BrowserWindow): Unit = 
        instance.setParentWindow(parent.instance)

    fun getParentWindow(): BrowserWindow = 
        instance.getParentWindow()

    fun getChildWindows(): Array<BrowserWindow> = 
        instance.getChildWindows()

    fun setAutoHideCursor(autoHide: Boolean): Unit = 
        instance.setAutoHideCursor(autoHide)

    fun setVibrancy(type: String): Unit = 
        instance.setVibrancy(type)

    // ~ Builders -------------------------------------------------------------------------------

    class Options(
        var width: Int? = null, 
        var height: Int? = null, 
        var x: Int? = null, 
        var y: Int? = null, 
        var useContentSize: Boolean? = null, 
        var center: Boolean? = null, 
        var minWidth: Int? = null, 
        var minHeight: Int? = null, 
        var maxWidth: Int? = null, 
        var maxHeight: Int? = null, 
        var resizable: Boolean? = null, 
        var movable: Boolean? = null, 
        var minimizable: Boolean? = null, 
        var maximizable: Boolean? = null, 
        var closable: Boolean? = null, 
        var focusable: Boolean? = null, 
        var alwaysOnTop: Boolean? = null, 
        var fullscreen: Boolean? = null, 
        var fullscreenable: Boolean? = null, 
        var skipTaskbar: Boolean? = null, 
        var kiosk: Boolean? = null, 
        var title: String? = null, 
        var icon: dynamic? = null, 
        var show: Boolean? = null, 
        var frame: Boolean? = null, 
        var parent: BrowserWindow? = null, 
        var modal: Boolean? = null, 
        var acceptFirstMouse: Boolean? = null, 
        var disableAutoHideCursor: Boolean? = null, 
        var autoHideMenuBar: Boolean? = null, 
        var enableLargerThanScreen: Boolean? = null, 
        var backgroundColor: String? = null, 
        var hasShadow: Boolean? = null, 
        var darkTheme: Boolean? = null, 
        var transparent: Boolean? = null, 
        var type: String? = null, 
        var titleBarStyle: String? = null, 
        var thickFrame: Boolean? = null, 
        var vibrancy: String? = null, 
        var zoomToPageWidth: Boolean? = null, 
        var webPreferences: WebPreferences? = null
    )

    class WebPreferences(
        var devTools: Boolean? = null, 
        var nodeIntegration: Boolean? = null, 
        var preload: String? = null, 
        var session: Session? = null, 
        var partition: String? = null, 
        var zoomFactor: Float? = null, 
        var javascript: Boolean? = null, 
        var webSecurity: Boolean? = null, 
        var allowRunningInsecureContent: Boolean? = null, 
        var images: Boolean? = null, 
        var textAreasAreResizable: Boolean? = null, 
        var webgl: Boolean? = null, 
        var webaudio: Boolean? = null, 
        var plugins: Boolean? = null, 
        var experimentalFeatures: Boolean? = null, 
        var experimentalCanvasFeatures: Boolean? = null, 
        var scrollBounce: Boolean? = null, 
        var blinkFeatures: String? = null, 
        var disableBlinkFeatures: String? = null, 
        var defaultFontFamily: DefaultFontFamily? = null, 
        var defaultFontSize: Int? = null, 
        var defaultMonospaceFontSize: Int? = null, 
        var minimumFontSize: Int? = null, 
        var defaultEncoding: String? = null, 
        var backgroundThrottling: Boolean? = null, 
        var offscreen: Boolean? = null, 
        var sandbox: Boolean? = null, 
        var contextIsolation: Boolean? = null
    )

    class DefaultFontFamily(
        var standard: String? = null, 
        var serif: String? = null, 
        var sansSerif: String? = null, 
        var monospace: String? = null, 
        var cursive: String? = null, 
        var fantasy: String? = null
    )

    class SetAspectRatioExtraSize(
        var width: Int, 
        var height: Int
    )

    class LoadURLOptions(
        var httpReferrer: String? = null, 
        var userAgent: String? = null, 
        var extraHeaders: String? = null, 
        var postData: Array<dynamic>? = null
    )

    class SetProgressBarOptions(
        var mode: String
    )

    class SetAppDetailsOptions(
        var appId: String? = null, 
        var appIconPath: String? = null, 
        var appIconIndex: Int? = null, 
        var relaunchCommand: String? = null, 
        var relaunchDisplayName: String? = null
    )

}

