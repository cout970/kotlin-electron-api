@file:Suppress("UnsafeCastFromDynamic")
package jsapi.electron

object dialog {

    private val module: dynamic = js("require('electron').dialog")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     * The browserWindow argument allows the dialog to attach itself to a parent 
     * window, making it modal.
     *
     * The filters specifies an array of file types that can be displayed or selected 
     * when you want to limit the user to a specific type. For example:
     *
     *  | 
     *  | {
     *  |   filters: [
     *  |     {name: 'Images', extensions: ['jpg', 'png', 'gif']},
     *  |     {name: 'Movies', extensions: ['mkv', 'avi', 'mp4']},
     *  |     {name: 'Custom File Type', extensions: ['as']},
     *  |     {name: 'All Files', extensions: ['*']}
     *  |   ]
     *  | }
     *  | 
     *
     * The extensions array should contain extensions without wildcards or dots (e.g. 
     * 'png' is good but '.png' and '*.png' are bad). To show all files, use the '*' 
     * wildcard (no other wildcard is supported).
     *
     * If a callback is passed, the API call will be asynchronous and the result will 
     * be passed via callback(filenames)
     *
     * Note: On Windows and Linux an open dialog can not be both a file selector and 
     * a directory selector, so if you set properties to ['openFile', 
     * 'openDirectory'] on these platforms, a directory selector will be shown.
     */
    fun showOpenDialog(browserWindow: BrowserWindow?, options: ShowOpenDialogOptions.() -> Unit, callback: ((filePaths: Array<String>) -> Unit)?): Array<String> = 
        module.showOpenDialog(browserWindow?.instance, options.let { ShowOpenDialogOptions().apply(it) }, callback)

    /**
     * The browserWindow argument allows the dialog to attach itself to a parent 
     * window, making it modal.
     *
     * The filters specifies an array of file types that can be displayed, see 
     * dialog.showOpenDialog for an example.
     *
     * If a callback is passed, the API call will be asynchronous and the result will 
     * be passed via callback(filename)
     */
    fun showSaveDialog(browserWindow: BrowserWindow?, options: ShowSaveDialogOptions.() -> Unit, callback: ((filename: String) -> Unit)?): String = 
        module.showSaveDialog(browserWindow?.instance, options.let { ShowSaveDialogOptions().apply(it) }, callback)

    /**
     * Shows a message box, it will block the process until the message box is 
     * closed. It returns the index of the clicked button.
     *
     * The browserWindow argument allows the dialog to attach itself to a parent 
     * window, making it modal.
     *
     * If a callback is passed, the API call will be asynchronous and the result will 
     * be passed via callback(response).
     */
    fun showMessageBox(browserWindow: BrowserWindow?, options: ShowMessageBoxOptions, callback: ((response: Number, checkboxChecked: Boolean) -> Unit)?): Int = 
        module.showMessageBox(browserWindow?.instance, options, callback)

    /**
     * Displays a modal dialog that shows an error message.
     *
     * This API can be called safely before the ready event the app module emits, it 
     * is usually used to report errors in early stage of startup. If called before 
     * the app readyevent on Linux, the message will be emitted to stderr, and no GUI 
     * dialog will appear.
     */
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
        var filters: Array<FileFilter>? = null,
        var message: String? = null,
        var nameFieldLabel: String? = null,
        var showsTagField: Boolean? = null
    )

    class ShowMessageBoxOptions(
        var type: String? = null,
        var buttons: Array<String>? = null,
        var defaultId: Int? = null,
        var title: String? = null,
        var message: String,
        var detail: String? = null,
        var checkboxLabel: String? = null,
        var checkboxChecked: Boolean? = null,
        var icon: NativeImage? = null,
        var cancelId: Int? = null,
        var noLink: Boolean? = null
    )
}

