package jsapi.electron

object app {

    private val module: dynamic = js("require('electron').app")

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun quit(): Unit = 
        module.quit()

    fun exit(exitCode: Int?): Unit = 
        module.exit(exitCode)

    fun relaunch(options: (RelaunchOptions.() -> Unit)?): Unit = 
        module.relaunch(options?.let { RelaunchOptions().apply(it) })

    fun isReady(): Boolean = 
        module.isReady()

    fun focus(): Unit = 
        module.focus()

    fun hide(): Unit = 
        module.hide()

    fun show(): Unit = 
        module.show()

    fun getAppPath(): String = 
        module.getAppPath()

    fun getPath(name: String): String = 
        module.getPath(name)

    fun getFileIcon(path: String, options: GetFileIconOptions?, callback: (error: Error, icon: NativeImage) -> Unit): Unit = 
        module.getFileIcon(path, options, callback)

    fun setPath(name: String, path: String): Unit = 
        module.setPath(name, path)

    fun getVersion(): String = 
        module.getVersion()

    fun getName(): String = 
        module.getName()

    fun setName(name: String): Unit = 
        module.setName(name)

    fun getLocale(): String = 
        module.getLocale()

    fun addRecentDocument(path: String): Unit = 
        module.addRecentDocument(path)

    fun clearRecentDocuments(): Unit = 
        module.clearRecentDocuments()

    fun setAsDefaultProtocolClient(protocol: String, path: String?, args: Array<String>?): Boolean = 
        module.setAsDefaultProtocolClient(protocol, path, args)

    fun removeAsDefaultProtocolClient(protocol: String, path: String?, args: Array<String>?): Boolean = 
        module.removeAsDefaultProtocolClient(protocol, path, args)

    fun isDefaultProtocolClient(protocol: String, path: String?, args: Array<String>?): Boolean = 
        module.isDefaultProtocolClient(protocol, path, args)

    fun setUserTasks(tasks: Array<Task>): Unit = 
        module.setUserTasks(tasks)

    fun getJumpListSettings(): dynamic = 
        module.getJumpListSettings()

    fun setJumpList(categories: Array<JumpListCategory>): Unit = 
        module.setJumpList(categories)

    fun makeSingleInstance(callback: (argv: Array<String>, workingDirectory: String) -> Unit): Unit = 
        module.makeSingleInstance(callback)

    fun releaseSingleInstance(): Unit = 
        module.releaseSingleInstance()

    fun setUserActivity(type: String, userInfo: SetUserActivityUserInfo.() -> Unit, webpageURL: String?): Unit = 
        module.setUserActivity(type, userInfo.let { SetUserActivityUserInfo().apply(it) }, webpageURL)

    fun getCurrentActivityType(): String = 
        module.getCurrentActivityType()

    fun setAppUserModelId(id: String): Unit = 
        module.setAppUserModelId(id)

    fun importCertificate(options: ImportCertificateOptions, callback: (result: Int) -> Unit): Unit = 
        module.importCertificate(options, callback)

    fun disableHardwareAcceleration(): Unit = 
        module.disableHardwareAcceleration()

    fun setBadgeCount(count: Int): Boolean = 
        module.setBadgeCount(count)

    fun getBadgeCount(): Int = 
        module.getBadgeCount()

    fun isUnityRunning(): Boolean = 
        module.isUnityRunning()

    fun getLoginItemSettings(options: (GetLoginItemSettingsOptions.() -> Unit)?): Unit = 
        module.getLoginItemSettings(options?.let { GetLoginItemSettingsOptions().apply(it) })

    fun setLoginItemSettings(settings: SetLoginItemSettingsSettings.() -> Unit): Unit = 
        module.setLoginItemSettings(settings.let { SetLoginItemSettingsSettings().apply(it) })

    fun isAccessibilitySupportEnabled(): Boolean = 
        module.isAccessibilitySupportEnabled()

    fun setAboutPanelOptions(options: SetAboutPanelOptionsOptions.() -> Unit): Unit = 
        module.setAboutPanelOptions(options.let { SetAboutPanelOptionsOptions().apply(it) })

    // ~ Builders -------------------------------------------------------------------------------

    class RelaunchOptions(
        var args: Array<String>? = null, 
        var execPath: String? = null
    )

    class GetFileIconOptions(
        var size: String
    )

    class SetUserActivityUserInfo(
    )

    class ImportCertificateOptions(
        var certificate: String, 
        var password: String
    )

    class GetLoginItemSettingsOptions(
        var path: String? = null, 
        var args: Array<String>? = null
    )

    class SetLoginItemSettingsSettings(
        var openAtLogin: Boolean? = null, 
        var openAsHidden: Boolean? = null, 
        var path: String? = null, 
        var args: Array<String>? = null
    )

    class SetAboutPanelOptionsOptions(
        var applicationName: String? = null, 
        var applicationVersion: String? = null, 
        var copyright: String? = null, 
        var credits: String? = null, 
        var version: String? = null
    )

}

