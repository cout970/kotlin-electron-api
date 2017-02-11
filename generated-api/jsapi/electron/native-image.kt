package jsapi.electron

object nativeImage {

    private val module: dynamic = js("require('electron').nativeImage")

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun createEmpty(): NativeImage = 
        module.createEmpty()

    fun createFromPath(path: String): NativeImage = 
        module.createFromPath(path)

    fun createFromBuffer(buffer: dynamic, options: (CreateFromBufferOptions.() -> Unit)?): NativeImage = 
        module.createFromBuffer(buffer.instance, options?.let { CreateFromBufferOptions().apply(it) })

    fun createFromDataURL(dataURL: String): Unit = 
        module.createFromDataURL(dataURL)

    // ~ Builders -------------------------------------------------------------------------------

    class CreateFromBufferOptions(
        var width: Int? = null, 
        var height: Int? = null, 
        var scaleFactor: Double? = null
    )

}

class NativeImage() {

    private val module: dynamic = js("require('electron').NativeImage")

    val instance: dynamic

    init {
        val _constructor = js("require('electron').NativeImage")
        instance = js("new _constructor(_)")
    }

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    fun toPNG(): dynamic = 
        instance.toPNG()

    fun toJPEG(quality: Int): dynamic = 
        instance.toJPEG(quality)

    fun toBitmap(): dynamic = 
        instance.toBitmap()

    fun toDataURL(): String = 
        instance.toDataURL()

    fun getBitmap(): dynamic = 
        instance.getBitmap()

    fun getNativeHandle(): dynamic = 
        instance.getNativeHandle()

    fun isEmpty(): Boolean = 
        instance.isEmpty()

    fun getSize(): dynamic = 
        instance.getSize()

    fun setTemplateImage(option: Boolean): Unit = 
        instance.setTemplateImage(option)

    fun isTemplateImage(): Boolean = 
        instance.isTemplateImage()

    fun crop(rect: CropRect): NativeImage = 
        instance.crop(rect)

    fun resize(options: ResizeOptions.() -> Unit): NativeImage = 
        instance.resize(options.let { ResizeOptions().apply(it) })

    fun getAspectRatio(): Float = 
        instance.getAspectRatio()

    // ~ Builders -------------------------------------------------------------------------------

    class CropRect(
        var x: Int, 
        var y: Int, 
        var width: Int, 
        var height: Int
    )

    class ResizeOptions(
        var width: Int? = null, 
        var height: Int? = null, 
        var quality: String? = null
    )

}

