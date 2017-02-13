package jsapi.electron

object webFrame {

    private val module: dynamic = js("require('electron').webFrame")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     * Changes the zoom factor to the specified factor. Zoom factor is zoom percent divided by 100, so 300% = 3.0.
     */
    fun setZoomFactor(factor: Number): Unit = 
        module.setZoomFactor(factor)

    /**
     */
    fun getZoomFactor(): Number = 
        module.getZoomFactor()

    /**
     * Changes the zoom level to the specified level. The original size is 0 and each increment above or below represents zooming 20% larger or smaller to default limits of 300% and 50% of original size, respectively.
     */
    fun setZoomLevel(level: Number): Unit = 
        module.setZoomLevel(level)

    /**
     */
    fun getZoomLevel(): Number = 
        module.getZoomLevel()

    /**
     * Deprecated: Call setVisualZoomLevelLimits instead to set the visual zoom level limits. This method will be removed in Electron 2.0.
     */
    fun setZoomLevelLimits(minimumLevel: Number, maximumLevel: Number): Unit = 
        module.setZoomLevelLimits(minimumLevel, maximumLevel)

    /**
     * Sets the maximum and minimum pinch-to-zoom level.
     */
    fun setVisualZoomLevelLimits(minimumLevel: Number, maximumLevel: Number): Unit = 
        module.setVisualZoomLevelLimits(minimumLevel, maximumLevel)

    /**
     * Sets the maximum and minimum layout-based (i.e. non-visual) zoom level.
     */
    fun setLayoutZoomLevelLimits(minimumLevel: Number, maximumLevel: Number): Unit = 
        module.setLayoutZoomLevelLimits(minimumLevel, maximumLevel)

    /**
     * Sets a provider for spell checking in input fields and text areas.
     *
     * The provider must be an object that has a spellCheck method that returns whether the word passed is correctly spelled.
     *
     * An example of using node-spellchecker as provider:
     *
     */
    fun setSpellCheckProvider(language: String, autoCorrectWord: Boolean, provider: SetSpellCheckProviderProvider): Unit = 
        module.setSpellCheckProvider(language, autoCorrectWord, provider)

    /**
     * Registers the scheme as secure scheme.
     *
     * Secure schemes do not trigger mixed content warnings. For example, https and data are secure schemes because they cannot be corrupted by active network attackers.
     */
    fun registerURLSchemeAsSecure(scheme: String): Unit = 
        module.registerURLSchemeAsSecure(scheme)

    /**
     * Resources will be loaded from this scheme regardless of the current page's Content Security Policy.
     */
    fun registerURLSchemeAsBypassingCSP(scheme: String): Unit = 
        module.registerURLSchemeAsBypassingCSP(scheme)

    /**
     * Registers the scheme as secure, bypasses content security policy for resources, allows registering ServiceWorker and supports fetch API.
     *
     * Specify an option with the value of false to omit it from the registration. An example of registering a privileged scheme, without bypassing Content Security Policy:
     *
     */
    fun registerURLSchemeAsPrivileged(scheme: String, options: (RegisterURLSchemeAsPrivilegedOptions.() -> Unit)?): Unit = 
        module.registerURLSchemeAsPrivileged(scheme, options?.let { RegisterURLSchemeAsPrivilegedOptions().apply(it) })

    /**
     * Inserts text to the focused element.
     */
    fun insertText(text: String): Unit = 
        module.insertText(text)

    /**
     * Evaluates code in page.
     *
     * In the browser window some HTML APIs like requestFullScreen can only be invoked by a gesture from the user. Setting userGesture to true will remove this limitation.
     */
    fun executeJavaScript(code: String, userGesture: Boolean?, callback: ((result: Any) -> Unit)?): Unit = 
        module.executeJavaScript(code, userGesture, callback)

    /**
     *  . imagesMemoryUsageDetails
     *  . cssStyleSheetsMemoryUsageDetails
     *  . xslStyleSheetsMemoryUsageDetails
     *  . fontsMemoryUsageDetails
     *  . otherMemoryUsageDetails
     *
     * This will generate:
     *
     */
    fun getResourceUsage(images: MemoryUsageDetails, cssStyleSheets: MemoryUsageDetails, xslStyleSheets: MemoryUsageDetails, fonts: MemoryUsageDetails, other: MemoryUsageDetails): dynamic = 
        module.getResourceUsage(images.instance, cssStyleSheets.instance, xslStyleSheets.instance, fonts.instance, other.instance)

    /**
     * Attempts to free memory that is no longer being used (like images from a previous navigation).
     *
     * Note that blindly calling this method probably makes Electron slower since it will have to refill these emptied caches, you should only call it if an event in your app has occurred that makes you think your page is actually using less memory (i.e. you have navigated from a super heavy page to a mostly empty one, and intend to stay there).
     */
    fun clearCache(): Unit = 
        module.clearCache()

    // ~ Builders ------------------------------------------------------------------------------

    class SetSpellCheckProviderProvider(
        var spellCheck: (text: String) -> Unit
    )

    class RegisterURLSchemeAsPrivilegedOptions(
        var secure: Boolean? = null,
        var bypassCSP: Boolean? = null,
        var allowServiceWorkers: Boolean? = null,
        var supportFetchAPI: Boolean? = null,
        var corsEnabled: Boolean? = null
    )
}

