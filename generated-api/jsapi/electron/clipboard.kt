package jsapi.electron

object clipboard {

    private val module: dynamic = js("require('electron').clipboard")

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun readText(type: String?): String = 
        module.readText(type)

    fun writeText(text: String, type: String?): Unit = 
        module.writeText(text, type)

    fun readHTML(type: String?): String = 
        module.readHTML(type)

    fun writeHTML(markup: String, type: String?): Unit = 
        module.writeHTML(markup, type)

    fun readImage(type: String?): NativeImage = 
        module.readImage(type)

    fun writeImage(image: NativeImage, type: String?): Unit = 
        module.writeImage(image.instance, type)

    fun readRTF(type: String?): String = 
        module.readRTF(type)

    fun writeRTF(text: String, type: String?): Unit = 
        module.writeRTF(text, type)

    fun readBookmark(): dynamic = 
        module.readBookmark()

    fun writeBookmark(title: String, url: String, type: String?): Unit = 
        module.writeBookmark(title, url, type)

    fun readFindText(): String = 
        module.readFindText()

    fun writeFindText(text: String): Unit = 
        module.writeFindText(text)

    fun clear(type: String?): Unit = 
        module.clear(type)

    fun availableFormats(type: String?): Array<String> = 
        module.availableFormats(type)

    fun has(data: String, type: String?): Boolean = 
        module.has(data, type)

    fun read(data: String, type: String?): String = 
        module.read(data, type)

    fun write(data: WriteData.() -> Unit, type: String?): Unit = 
        module.write(data.let { WriteData().apply(it) }, type)

    // ~ Builders -------------------------------------------------------------------------------

    class WriteData(
        var text: String? = null, 
        var html: String? = null, 
        var image: NativeImage? = null, 
        var rtf: String? = null, 
        var bookmark: String? = null
    )

}

