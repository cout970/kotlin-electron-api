package jsapi.electron

class BrowserWindow(options: (Options.() -> Unit)?) {

    val instance: dynamic

    init {
        val _constructor = js("require('electron').BrowserWindow")
        val _options = options?.let { Options().apply(it) }
        instance = js("new _constructor(_options)")
    }

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Properties ----------------------------------------------------------------------------

    val webContents: WebContents get() = instance.webContents
    val id: Int get() = instance.id

    // ~ Methods -------------------------------------------------------------------------------

    /**
     * Force closing the window, the unload and beforeunload event won't be emitted for the web page, and close event will also not be emitted for this window, but it guarantees the closed event will be emitted.
     */
    fun destroy(): Unit = 
        instance.destroy()

    /**
     * Try to close the window. This has the same effect as a user manually clicking the close button of the window. The web page may cancel the close though. See the close event.
     */
    fun close(): Unit = 
        instance.close()

    /**
     * Focuses on the window.
     */
    fun focus(): Unit = 
        instance.focus()

    /**
     * Removes focus from the window.
     */
    fun blur(): Unit = 
        instance.blur()

    /**
     */
    fun isFocused(): Boolean = 
        instance.isFocused()

    /**
     */
    fun isDestroyed(): Boolean = 
        instance.isDestroyed()

    /**
     * Shows and gives focus to the window.
     */
    fun show(): Unit = 
        instance.show()

    /**
     * Shows the window but doesn't focus on it.
     */
    fun showInactive(): Unit = 
        instance.showInactive()

    /**
     * Hides the window.
     */
    fun hide(): Unit = 
        instance.hide()

    /**
     */
    fun isVisible(): Boolean = 
        instance.isVisible()

    /**
     */
    fun isModal(): Boolean = 
        instance.isModal()

    /**
     * Maximizes the window.
     */
    fun maximize(): Unit = 
        instance.maximize()

    /**
     * Unmaximizes the window.
     */
    fun unmaximize(): Unit = 
        instance.unmaximize()

    /**
     */
    fun isMaximized(): Boolean = 
        instance.isMaximized()

    /**
     * Minimizes the window. On some platforms the minimized window will be shown in the Dock.
     */
    fun minimize(): Unit = 
        instance.minimize()

    /**
     * Restores the window from minimized state to its previous state.
     */
    fun restore(): Unit = 
        instance.restore()

    /**
     */
    fun isMinimized(): Boolean = 
        instance.isMinimized()

    /**
     * Sets whether the window should be in fullscreen mode.
     */
    fun setFullScreen(flag: Boolean): Unit = 
        instance.setFullScreen(flag)

    /**
     */
    fun isFullScreen(): Boolean = 
        instance.isFullScreen()

    /**
     * This will make a window maintain an aspect ratio. The extra size allows a developer to have space, specified in pixels, not included within the aspect ratio calculations. This API already takes into account the difference between a window's size and its content size.
     *
     * Consider a normal window with an HD video player and associated controls. Perhaps there are 15 pixels of controls on the left edge, 25 pixels of controls on the right edge and 50 pixels of controls below the player. In order to maintain a 16:9 aspect ratio (standard aspect ratio for HD @1920x1080) within the player itself we would call this function with arguments of 16/9 and [ 40, 50 ]. The second argument doesn't care where the extra width and height are within the content view--only that they exist. Just sum any extra width and height areas you have within the overall content view.
     */
    fun setAspectRatio(aspectRatio: Float, extraSize: SetAspectRatioExtraSize?): Unit = 
        instance.setAspectRatio(aspectRatio, extraSize)

    /**
     * Uses Quick Look to preview a file at a given path.
     */
    fun previewFile(path: String, displayName: String?): Unit = 
        instance.previewFile(path, displayName)

    /**
     * Closes the currently open Quick Look panel.
     */
    fun closeFilePreview(): Unit = 
        instance.closeFilePreview()

    /**
     * Resizes and moves the window to the supplied bounds
     */
    fun setBounds(bounds: Rectangle, animate: Boolean?): Unit = 
        instance.setBounds(bounds.instance, animate)

    /**
     */
    fun getBounds(): Rectangle = 
        instance.getBounds()

    /**
     * Resizes and moves the window's client area (e.g. the web page) to the supplied bounds.
     */
    fun setContentBounds(bounds: Rectangle, animate: Boolean?): Unit = 
        instance.setContentBounds(bounds.instance, animate)

    /**
     */
    fun getContentBounds(): Rectangle = 
        instance.getContentBounds()

    /**
     * Resizes the window to width and height.
     */
    fun setSize(width: Int, height: Int, animate: Boolean?): Unit = 
        instance.setSize(width, height, animate)

    /**
     */
    fun getSize(): Array<Int> = 
        instance.getSize()

    /**
     * Resizes the window's client area (e.g. the web page) to width and height.
     */
    fun setContentSize(width: Int, height: Int, animate: Boolean?): Unit = 
        instance.setContentSize(width, height, animate)

    /**
     */
    fun getContentSize(): Array<Int> = 
        instance.getContentSize()

    /**
     * Sets the minimum size of window to width and height.
     */
    fun setMinimumSize(width: Int, height: Int): Unit = 
        instance.setMinimumSize(width, height)

    /**
     */
    fun getMinimumSize(): Array<Int> = 
        instance.getMinimumSize()

    /**
     * Sets the maximum size of window to width and height.
     */
    fun setMaximumSize(width: Int, height: Int): Unit = 
        instance.setMaximumSize(width, height)

    /**
     */
    fun getMaximumSize(): Array<Int> = 
        instance.getMaximumSize()

    /**
     * Sets whether the window can be manually resized by user.
     */
    fun setResizable(resizable: Boolean): Unit = 
        instance.setResizable(resizable)

    /**
     */
    fun isResizable(): Boolean = 
        instance.isResizable()

    /**
     * Sets whether the window can be moved by user. On Linux does nothing.
     */
    fun setMovable(movable: Boolean): Unit = 
        instance.setMovable(movable)

    /**
     * On Linux always returns true.
     */
    fun isMovable(): Boolean = 
        instance.isMovable()

    /**
     * Sets whether the window can be manually minimized by user. On Linux does nothing.
     */
    fun setMinimizable(minimizable: Boolean): Unit = 
        instance.setMinimizable(minimizable)

    /**
     * On Linux always returns true.
     */
    fun isMinimizable(): Boolean = 
        instance.isMinimizable()

    /**
     * Sets whether the window can be manually maximized by user. On Linux does nothing.
     */
    fun setMaximizable(maximizable: Boolean): Unit = 
        instance.setMaximizable(maximizable)

    /**
     * On Linux always returns true.
     */
    fun isMaximizable(): Boolean = 
        instance.isMaximizable()

    /**
     * Sets whether the maximize/zoom window button toggles fullscreen mode or maximizes the window.
     */
    fun setFullScreenable(fullscreenable: Boolean): Unit = 
        instance.setFullScreenable(fullscreenable)

    /**
     */
    fun isFullScreenable(): Boolean = 
        instance.isFullScreenable()

    /**
     * Sets whether the window can be manually closed by user. On Linux does nothing.
     */
    fun setClosable(closable: Boolean): Unit = 
        instance.setClosable(closable)

    /**
     * On Linux always returns true.
     */
    fun isClosable(): Boolean = 
        instance.isClosable()

    /**
     * Sets whether the window should show always on top of other windows. After setting this, the window is still a normal window, not a toolbox window which can not be focused on.
     */
    fun setAlwaysOnTop(flag: Boolean, level: String?, relativeLevel: Int?): Unit = 
        instance.setAlwaysOnTop(flag, level, relativeLevel)

    /**
     */
    fun isAlwaysOnTop(): Boolean = 
        instance.isAlwaysOnTop()

    /**
     * Moves window to the center of the screen.
     */
    fun center(): Unit = 
        instance.center()

    /**
     * Moves window to x and y.
     */
    fun setPosition(x: Int, y: Int, animate: Boolean?): Unit = 
        instance.setPosition(x, y, animate)

    /**
     */
    fun getPosition(): Array<Int> = 
        instance.getPosition()

    /**
     * Changes the title of native window to title.
     */
    fun setTitle(title: String): Unit = 
        instance.setTitle(title)

    /**
     * Note: The title of web page can be different from the title of the native window.
     */
    fun getTitle(): String = 
        instance.getTitle()

    /**
     * Changes the attachment point for sheets on macOS. By default, sheets are attached just below the window frame, but you may want to display them beneath a HTML-rendered toolbar. For example:
     *
     */
    fun setSheetOffset(offsetY: Float, offsetX: Float?): Unit = 
        instance.setSheetOffset(offsetY, offsetX)

    /**
     * Starts or stops flashing the window to attract user's attention.
     */
    fun flashFrame(flag: Boolean): Unit = 
        instance.flashFrame(flag)

    /**
     * Makes the window not show in the taskbar.
     */
    fun setSkipTaskbar(skip: Boolean): Unit = 
        instance.setSkipTaskbar(skip)

    /**
     * Enters or leaves the kiosk mode.
     */
    fun setKiosk(flag: Boolean): Unit = 
        instance.setKiosk(flag)

    /**
     */
    fun isKiosk(): Boolean = 
        instance.isKiosk()

    /**
     * The native type of the handle is HWND on Windows, NSView* on macOS, and Window (unsigned long) on Linux.
     */
    fun getNativeWindowHandle(): dynamic = 
        instance.getNativeWindowHandle()

    /**
     * Hooks a windows message. The callback is called when the message is received in the WndProc.
     */
    fun hookWindowMessage(message: Int, callback: () -> Unit): Unit = 
        instance.hookWindowMessage(message, callback)

    /**
     */
    fun isWindowMessageHooked(message: Int): Boolean = 
        instance.isWindowMessageHooked(message)

    /**
     * Unhook the window message.
     */
    fun unhookWindowMessage(message: Int): Unit = 
        instance.unhookWindowMessage(message)

    /**
     * Unhooks all of the window messages.
     */
    fun unhookAllWindowMessages(): Unit = 
        instance.unhookAllWindowMessages()

    /**
     * Sets the pathname of the file the window represents, and the icon of the file will show in window's title bar.
     */
    fun setRepresentedFilename(filename: String): Unit = 
        instance.setRepresentedFilename(filename)

    /**
     */
    fun getRepresentedFilename(): String = 
        instance.getRepresentedFilename()

    /**
     * Specifies whether the window’s document has been edited, and the icon in title bar will become gray when set to true.
     */
    fun setDocumentEdited(edited: Boolean): Unit = 
        instance.setDocumentEdited(edited)

    /**
     */
    fun isDocumentEdited(): Boolean = 
        instance.isDocumentEdited()

    /**
     * 
     */
    fun focusOnWebView(): Unit = 
        instance.focusOnWebView()

    /**
     * 
     */
    fun blurWebView(): Unit = 
        instance.blurWebView()

    /**
     * Same as webContents.capturePage([rect, ]callback).
     */
    fun capturePage(rect: Rectangle?, callback: (image: NativeImage) -> Unit): Unit = 
        instance.capturePage(rect?.instance, callback)

    /**
     * Same as webContents.loadURL(url[, options]).
     *
     * The url can be a remote address (e.g. http://) or a path to a local HTML file using the file:// protocol.
     *
     * To ensure that file URLs are properly formatted, it is recommended to use Node's url.format method:
     *
     * You can load a URL using a POST request with URL-encoded data by doing the following:
     *
     */
    fun loadURL(url: String, options: (LoadURLOptions.() -> Unit)?): Unit = 
        instance.loadURL(url, options?.let { LoadURLOptions().apply(it) })

    /**
     * Same as webContents.reload.
     */
    fun reload(): Unit = 
        instance.reload()

    /**
     * Sets the menu as the window's menu bar, setting it to null will remove the menu bar.
     */
    fun setMenu(menu: Menu): Unit = 
        instance.setMenu(menu.instance)

    /**
     * Sets progress value in progress bar. Valid range is [0, 1.0].
     *
     * Remove progress bar when progress < 0; Change to indeterminate mode when progress > 1.
     *
     * On Linux platform, only supports Unity desktop environment, you need to specify the *.desktop file name to desktopName field in package.json. By default, it will assume app.getName().desktop.
     *
     * On Windows, a mode can be passed. Accepted values are none, normal, indeterminate, error, and paused. If you call setProgressBar without a mode set (but with a value within the valid range), normal will be assumed.
     */
    fun setProgressBar(progress: Double, options: SetProgressBarOptions?): Unit = 
        instance.setProgressBar(progress, options)

    /**
     * Sets a 16 x 16 pixel overlay onto the current taskbar icon, usually used to convey some sort of application status or to passively notify the user.
     */
    fun setOverlayIcon(overlay: NativeImage, description: String): Unit = 
        instance.setOverlayIcon(overlay.instance, description)

    /**
     * Sets whether the window should have a shadow. On Windows and Linux does nothing.
     */
    fun setHasShadow(hasShadow: Boolean): Unit = 
        instance.setHasShadow(hasShadow)

    /**
     * On Windows and Linux always returns true.
     */
    fun hasShadow(): Boolean = 
        instance.hasShadow()

    /**
     * Add a thumbnail toolbar with a specified set of buttons to the thumbnail image of a window in a taskbar button layout. Returns a Boolean object indicates whether the thumbnail has been added successfully.
     *
     * The number of buttons in thumbnail toolbar should be no greater than 7 due to the limited room. Once you setup the thumbnail toolbar, the toolbar cannot be removed due to the platform's limitation. But you can call the API with an empty array to clean the buttons.
     *
     * The buttons is an array of Button objects:
     *
     *  . Button Object
     *     . iconNativeImage - The icon showing in thumbnail toolbar.
     *     . click Function
     *     . tooltip String (optional) - The text of the button's tooltip.
     *     . flags String[] (optional) - Control specific states and behaviors of the button. By default, it is ['enabled'].
     *
     *
     * The flags is an array that can include following Strings:
     *
     *  . enabled - The button is active and available to the user.
     *  . disabled - The button is disabled. It is present, but has a visual state indicating it will not respond to user action.
     *  . dismissonclick - When the button is clicked, the thumbnail window closes immediately.
     *  . nobackground - Do not draw a button border, use only the image.
     *  . hidden - The button is not shown to the user.
     *  . noninteractive - The button is enabled but not interactive; no pressed button state is drawn. This value is intended for instances where the button is used in a notification.
     *
     */
    fun setThumbarButtons(buttons: Array<ThumbarButton>): Boolean = 
        instance.setThumbarButtons(buttons)

    /**
     * Sets the region of the window to show as the thumbnail image displayed when hovering over the window in the taskbar. You can reset the thumbnail to be the entire window by specifying an empty region: {x: 0, y: 0, width: 0, height: 0}.
     */
    fun setThumbnailClip(region: Rectangle): Unit = 
        instance.setThumbnailClip(region.instance)

    /**
     * Sets the toolTip that is displayed when hovering over the window thumbnail in the taskbar.
     */
    fun setThumbnailToolTip(toolTip: String): Unit = 
        instance.setThumbnailToolTip(toolTip)

    /**
     * Sets the properties for the window's taskbar button.
     *
     * Note:relaunchCommand and relaunchDisplayName must always be set together. If one of those properties is not set, then neither will be used.
     */
    fun setAppDetails(options: SetAppDetailsOptions.() -> Unit): Unit = 
        instance.setAppDetails(options.let { SetAppDetailsOptions().apply(it) })

    /**
     * Same as webContents.showDefinitionForSelection().
     */
    fun showDefinitionForSelection(): Unit = 
        instance.showDefinitionForSelection()

    /**
     * Changes window icon.
     */
    fun setIcon(icon: NativeImage): Unit = 
        instance.setIcon(icon.instance)

    /**
     * Sets whether the window menu bar should hide itself automatically. Once set the menu bar will only show when users press the single Alt key.
     *
     * If the menu bar is already visible, calling setAutoHideMenuBar(true) won't hide it immediately.
     */
    fun setAutoHideMenuBar(hide: Boolean): Unit = 
        instance.setAutoHideMenuBar(hide)

    /**
     */
    fun isMenuBarAutoHide(): Boolean = 
        instance.isMenuBarAutoHide()

    /**
     * Sets whether the menu bar should be visible. If the menu bar is auto-hide, users can still bring up the menu bar by pressing the single Alt key.
     */
    fun setMenuBarVisibility(visible: Boolean): Unit = 
        instance.setMenuBarVisibility(visible)

    /**
     */
    fun isMenuBarVisible(): Boolean = 
        instance.isMenuBarVisible()

    /**
     * Sets whether the window should be visible on all workspaces.
     *
     * Note: This API does nothing on Windows.
     */
    fun setVisibleOnAllWorkspaces(visible: Boolean): Unit = 
        instance.setVisibleOnAllWorkspaces(visible)

    /**
     * Note: This API always returns false on Windows.
     */
    fun isVisibleOnAllWorkspaces(): Boolean = 
        instance.isVisibleOnAllWorkspaces()

    /**
     * Makes the window ignore all mouse events.
     *
     * All mouse events happened in this window will be passed to the window below this window, but if this window has focus, it will still receive keyboard events.
     */
    fun setIgnoreMouseEvents(ignore: Boolean): Unit = 
        instance.setIgnoreMouseEvents(ignore)

    /**
     * Prevents the window contents from being captured by other apps.
     *
     * On macOS it sets the NSWindow's sharingType to NSWindowSharingNone. On Windows it calls SetWindowDisplayAffinity with WDA_MONITOR.
     */
    fun setContentProtection(enable: Boolean): Unit = 
        instance.setContentProtection(enable)

    /**
     * Changes whether the window can be focused.
     */
    fun setFocusable(focusable: Boolean): Unit = 
        instance.setFocusable(focusable)

    /**
     * Sets parent as current window's parent window, passing null will turn current window into a top-level window.
     */
    fun setParentWindow(parent: BrowserWindow): Unit = 
        instance.setParentWindow(parent.instance)

    /**
     */
    fun getParentWindow(): BrowserWindow = 
        instance.getParentWindow()

    /**
     */
    fun getChildWindows(): Array<BrowserWindow> = 
        instance.getChildWindows()

    /**
     * Controls whether to hide cursor when typing.
     */
    fun setAutoHideCursor(autoHide: Boolean): Unit = 
        instance.setAutoHideCursor(autoHide)

    /**
     * Adds a vibrancy effect to the browser window. Passing null or an empty string will remove the vibrancy effect on the window.
     */
    fun setVibrancy(type: String): Unit = 
        instance.setVibrancy(type)

    // ~ Companion -----------------------------------------------------------------------------

    companion object { 

        private val module: dynamic = js("require('electron').BrowserWindow")

        // ~ Methods -------------------------------------------------------------------------------

    /**
     */
        fun getAllWindows(): Array<BrowserWindow> = 
            module.getAllWindows()

    /**
     */
        fun getFocusedWindow(): BrowserWindow = 
            module.getFocusedWindow()

    /**
     */
        fun fromWebContents(webContents: WebContents): BrowserWindow = 
            module.fromWebContents(webContents.instance)

    /**
     */
        fun fromId(id: Int): BrowserWindow = 
            module.fromId(id)

    /**
         * Adds DevTools extension located at path, and returns extension's name.
         *
         * The extension will be remembered so you only need to call this API once, this API is not for programming use. If you try to add an extension that has already been loaded, this method will not return and instead log a warning to the console.
         *
         * The method will also not return if the extension's manifest is missing or incomplete.
         *
         * Note: This API cannot be called before the ready event of the app module is emitted.
     */
        fun addDevToolsExtension(path: String): Unit = 
            module.addDevToolsExtension(path)

    /**
         * Remove a DevTools extension by name.
         *
         * Note: This API cannot be called before the ready event of the app module is emitted.
     */
        fun removeDevToolsExtension(name: String): Unit = 
            module.removeDevToolsExtension(name)

    /**
         * To check if a DevTools extension is installed you can run the following:
         *
         * Note: This API cannot be called before the ready event of the app module is emitted.
     */
        fun getDevToolsExtensions(): dynamic = 
            module.getDevToolsExtensions()
    }

    // ~ Builders ------------------------------------------------------------------------------

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
        var webPreferences: (WebPreferences.() -> Unit)? = null
    )
    class WebPreferences(
        var devTools: Boolean? = null,
        var nodeIntegration: Boolean? = null,
        var preload: String? = null,
        var session: Session? = null,
        var partition: String? = null,
        var zoomFactor: Number? = null,
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
        var defaultFontFamily: (DefaultFontFamily.() -> Unit)? = null,
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

