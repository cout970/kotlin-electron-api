package jsapi.electron

object nativeImage {

    private val module: dynamic = js("require('electron').nativeImage")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     * Creates an empty NativeImage instance.
     */
    fun createEmpty(): NativeImage = 
        module.createEmpty()

    /**
     * Creates a new NativeImage instance from a file located at path. This method 
     * returns an empty image if the path does not exist, cannot be read, or is not a 
     * valid image.
     *
     *  | 
     *  | const nativeImage = require('electron').nativeImage
     *  | 
     *  | let image = nativeImage.createFromPath('/Users/somebody/images/icon.png')
     *  | console.log(image)
     *  | 
     */
    fun createFromPath(path: String): NativeImage = 
        module.createFromPath(path)

    /**
     * Creates a new NativeImage instance from buffer.
     */
    fun createFromBuffer(buffer: dynamic, options: (CreateFromBufferOptions.() -> Unit)?): NativeImage = 
        module.createFromBuffer(buffer.instance, options?.let { CreateFromBufferOptions().apply(it) })

    /**
     * Creates a new NativeImage instance from dataURL.
     */
    fun createFromDataURL(dataURL: String): Unit = 
        module.createFromDataURL(dataURL)

    // ~ Builders ------------------------------------------------------------------------------

    class CreateFromBufferOptions(
        var width: Int? = null,
        var height: Int? = null,
        var scaleFactor: Double? = null
    )
}

class NativeImage() {

    val instance: dynamic

    init {
        val _constructor = js("require('electron').NativeImage")
        instance = js("new _constructor(_)")
    }

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     */
    fun toPNG(): dynamic = 
        instance.toPNG()

    /**
     */
    fun toJPEG(quality: Int): dynamic = 
        instance.toJPEG(quality)

    /**
     */
    fun toBitmap(): dynamic = 
        instance.toBitmap()

    /**
     */
    fun toDataURL(): String = 
        instance.toDataURL()

    /**
     * The difference between getBitmap() and toBitmap() is, getBitmap() does not 
     * copy the bitmap data, so you have to use the returned Buffer immediately in 
     * current event loop tick, otherwise the data might be changed or destroyed.
     */
    fun getBitmap(): dynamic = 
        instance.getBitmap()

    /**
     * Notice that the returned pointer is a weak pointer to the underlying native 
     * image instead of a copy, so you must ensure that the associated nativeImage 
     * instance is kept around.
     */
    fun getNativeHandle(): dynamic = 
        instance.getNativeHandle()

    /**
     */
    fun isEmpty(): Boolean = 
        instance.isEmpty()

    /**
     *  . width Integer
     *
     *  . height Integer
     *
     */
    fun getSize(width: Int, height: Int): dynamic = 
        instance.getSize(width, height)

    /**
     * Marks the image as a template image.
     */
    fun setTemplateImage(option: Boolean): Unit = 
        instance.setTemplateImage(option)

    /**
     */
    fun isTemplateImage(): Boolean = 
        instance.isTemplateImage()

    /**
     */
    fun crop(rect: CropRect): NativeImage = 
        instance.crop(rect)

    /**
     * If only the height or the width are specified then the current aspect ratio 
     * will be preserved in the resized image.
     */
    fun resize(options: ResizeOptions.() -> Unit): NativeImage = 
        instance.resize(options.let { ResizeOptions().apply(it) })

    /**
     */
    fun getAspectRatio(): Float = 
        instance.getAspectRatio()

    // ~ Companion -----------------------------------------------------------------------------

    companion object { 

        private val module: dynamic = js("require('electron').NativeImage")

    }

    // ~ Builders ------------------------------------------------------------------------------

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

