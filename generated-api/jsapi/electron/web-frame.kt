package jsapi.electron

object webFrame {

    private val module: dynamic = js("require('electron').webFrame")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun setZoomFactor(factor: Number): Unit = 
        module.setZoomFactor(factor)

    fun getZoomFactor(): Number = 
        module.getZoomFactor()

    fun setZoomLevel(level: Number): Unit = 
        module.setZoomLevel(level)

    fun getZoomLevel(): Number = 
        module.getZoomLevel()

    fun setZoomLevelLimits(minimumLevel: Number, maximumLevel: Number): Unit = 
        module.setZoomLevelLimits(minimumLevel, maximumLevel)

    fun setVisualZoomLevelLimits(minimumLevel: Number, maximumLevel: Number): Unit = 
        module.setVisualZoomLevelLimits(minimumLevel, maximumLevel)

    fun setLayoutZoomLevelLimits(minimumLevel: Number, maximumLevel: Number): Unit = 
        module.setLayoutZoomLevelLimits(minimumLevel, maximumLevel)

    fun setSpellCheckProvider(language: String, autoCorrectWord: Boolean, provider: SetSpellCheckProviderProvider): Unit = 
        module.setSpellCheckProvider(language, autoCorrectWord, provider)

    fun registerURLSchemeAsSecure(scheme: String): Unit = 
        module.registerURLSchemeAsSecure(scheme)

    fun registerURLSchemeAsBypassingCSP(scheme: String): Unit = 
        module.registerURLSchemeAsBypassingCSP(scheme)

    fun registerURLSchemeAsPrivileged(scheme: String, options: (RegisterURLSchemeAsPrivilegedOptions.() -> Unit)?): Unit = 
        module.registerURLSchemeAsPrivileged(scheme, options?.let { RegisterURLSchemeAsPrivilegedOptions().apply(it) })

    fun insertText(text: String): Unit = 
        module.insertText(text)

    fun executeJavaScript(code: String, userGesture: Boolean?, callback: ((result: Any) -> Unit)?): Unit = 
        module.executeJavaScript(code, userGesture, callback)

    fun getResourceUsage(): dynamic = 
        module.getResourceUsage()

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

