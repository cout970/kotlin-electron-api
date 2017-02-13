package jsapi.electron

object dialog {

    private val module: dynamic = js("require('electron').dialog")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun showOpenDialog(browserWindow: BrowserWindow?, options: ShowOpenDialogOptions.() -> Unit, callback: ((filePaths: Array<String>) -> Unit)?): Array<String> = 
        module.showOpenDialog(browserWindow?.instance, options.let { ShowOpenDialogOptions().apply(it) }, callback)

    fun showSaveDialog(browserWindow: BrowserWindow?, options: ShowSaveDialogOptions.() -> Unit, callback: ((filename: String) -> Unit)?): String = 
        module.showSaveDialog(browserWindow?.instance, options.let { ShowSaveDialogOptions().apply(it) }, callback)

    fun showMessageBox(browserWindow: BrowserWindow?, options: ShowMessageBoxOptions, callback: ((response: Number) -> Unit)?): Int = 
        module.showMessageBox(browserWindow?.instance, options, callback)

    fun showErrorBox(title: String, content: String): Unit = 
        module.showErrorBox(title, content)

    // ~ Builders ------------------------------------------------------------------------------

    class ShowOpenDialogOptions(
        var title: String? = null,
        var defaultPath: String? = null,
        var buttonLabel: String? = null,
        var filters: Array<FileFilter>? = null,
        var properties: Array<String>? = null,
        var normalizeAccessKeys: Boolean? = null
    )

    class ShowSaveDialogOptions(
        var title: String? = null,
        var defaultPath: String? = null,
        var buttonLabel: String? = null,
        var filters: Array<FileFilter>? = null
    )

    class ShowMessageBoxOptions(
        var type: String? = null,
        var buttons: Array<String>? = null,
        var defaultId: Int? = null,
        var title: String? = null,
        var message: String,
        var detail: String? = null,
        var icon: NativeImage? = null,
        var cancelId: Int? = null,
        var noLink: Boolean? = null
    )
}

