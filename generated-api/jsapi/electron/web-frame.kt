package jsapi.electron

object webFrame {

    private val module: dynamic = js("require('electron').webFrame")

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun setZoomFactor(factor: Float): Unit = 
        module.setZoomFactor(factor)

    fun getZoomFactor(): Float = 
        module.getZoomFactor()

    fun setZoomLevel(level: Float): Unit = 
        module.setZoomLevel(level)

    fun getZoomLevel(): Float = 
        module.getZoomLevel()

    fun setZoomLevelLimits(minimumLevel: Float, maximumLevel: Float): Unit = 
        module.setZoomLevelLimits(minimumLevel, maximumLevel)

    fun setVisualZoomLevelLimits(minimumLevel: Float, maximumLevel: Float): Unit = 
        module.setVisualZoomLevelLimits(minimumLevel, maximumLevel)

    fun setLayoutZoomLevelLimits(minimumLevel: Float, maximumLevel: Float): Unit = 
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

    // ~ Builders -------------------------------------------------------------------------------

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

