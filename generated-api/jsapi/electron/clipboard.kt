package jsapi.electron

object clipboard {

    private val module: dynamic = js("require('electron').clipboard")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     */
    fun readText(type: String?): String = 
        module.readText(type)

    /**
     * Writes the text into the clipboard as plain text.
     */
    fun writeText(text: String, type: String?): Unit = 
        module.writeText(text, type)

    /**
     */
    fun readHTML(type: String?): String = 
        module.readHTML(type)

    /**
     * Writes markup to the clipboard.
     */
    fun writeHTML(markup: String, type: String?): Unit = 
        module.writeHTML(markup, type)

    /**
     */
    fun readImage(type: String?): NativeImage = 
        module.readImage(type)

    /**
     * Writes image to the clipboard.
     */
    fun writeImage(image: NativeImage, type: String?): Unit = 
        module.writeImage(image.instance, type)

    /**
     */
    fun readRTF(type: String?): String = 
        module.readRTF(type)

    /**
     * Writes the text into the clipboard in RTF.
     */
    fun writeRTF(text: String, type: String?): Unit = 
        module.writeRTF(text, type)

    /**
     *  . title String
     *  . url String
     *
     */
    fun readBookmark(title: String, url: String): dynamic = 
        module.readBookmark(title, url)

    /**
     * Writes the title and url into the clipboard as a bookmark.
     *
     * Note: Most apps on Windows don't support pasting bookmarks into them so you 
     * can use clipboard.write to write both a bookmark and fallback text to the 
     * clipboard.
     *
     *  | 
     *  | clipboard.write({
     *  |   text: 'http://electron.atom.io',
     *  |   bookmark: 'Electron Homepage'
     *  | })
     *  | 
     */
    fun writeBookmark(title: String, url: String, type: String?): Unit = 
        module.writeBookmark(title, url, type)

    /**
     */
    fun readFindText(): String = 
        module.readFindText()

    /**
     * Writes the text into the find pasteboard as plain text. This method uses 
     * synchronous IPC when called from the renderer process.
     */
    fun writeFindText(text: String): Unit = 
        module.writeFindText(text)

    /**
     * Clears the clipboard content.
     */
    fun clear(type: String?): Unit = 
        module.clear(type)

    /**
     */
    fun availableFormats(type: String?): Array<String> = 
        module.availableFormats(type)

    /**
     *  | 
     *  | const {clipboard} = require('electron')
     *  | console.log(clipboard.has('<p>selection</p>'))
     *  | 
     */
    fun has(data: String, type: String?): Boolean = 
        module.has(data, type)

    /**
     */
    fun read(data: String, type: String?): String = 
        module.read(data, type)

    /**
     *  | 
     *  | const {clipboard} = require('electron')
     *  | clipboard.write({text: 'test', html: '<b>test</b>'})
     *  | 
     *
     * Writes data to the clipboard.
     */
    fun write(data: WriteData.() -> Unit, type: String?): Unit = 
        module.write(data.let { WriteData().apply(it) }, type)

    // ~ Builders ------------------------------------------------------------------------------

    class WriteData(
        var text: String? = null,
        var html: String? = null,
        var image: NativeImage? = null,
        var rtf: String? = null,
        var bookmark: String? = null
    )
}

