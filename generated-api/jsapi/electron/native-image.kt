@file:Suppress("UnsafeCastFromDynamic")
package jsapi.electron

object nativeImage {

    private val module: dynamic = js("require('electron').nativeImage")

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     * Creates an empty NativeImage instance.
     * 
     * @return 
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
     * 
     * @return 
     */
    fun createFromPath(path: String): NativeImage = 
        module.createFromPath(path)

    /**
     * Creates a new NativeImage instance from buffer.
     * 
     * @return 
     */
    fun createFromBuffer(buffer: dynamic, options: (CreateFromBufferOptions.() -> Unit)?): NativeImage = 
        module.createFromBuffer(buffer, options?.let { CreateFromBufferOptions().apply(it) })

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

class NativeImage constructor(val instance: dynamic, z: Unit) {

    constructor() : this(Unit.let {
        val _constructor = js("require('electron').NativeImage")
        js("new _constructor()")
    }, z = Unit)

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     * @return A Buffer that contains the image's PNG encoded data.
     */
    fun toPNG(): dynamic = 
        instance.toPNG()

    /**
     * @return A Buffer that contains the image's JPEG encoded data.
     */
    fun toJPEG(quality: Int): dynamic = 
        instance.toJPEG(quality)

    /**
     * @return A Buffer that contains a copy of the image's raw bitmap pixel data.
     */
    fun toBitmap(): dynamic = 
        instance.toBitmap()

    /**
     * @return The data URL of the image.
     */
    fun toDataURL(): String = 
        instance.toDataURL()

    /**
     * The difference between getBitmap() and toBitmap() is, getBitmap() does not 
     * copy the bitmap data, so you have to use the returned Buffer immediately in 
     * current event loop tick, otherwise the data might be changed or destroyed.
     * 
     * @return A Buffer that contains the image's raw bitmap pixel data.
     */
    fun getBitmap(): dynamic = 
        instance.getBitmap()

    /**
     * Notice that the returned pointer is a weak pointer to the underlying native 
     * image instead of a copy, so you must ensure that the associated nativeImage 
     * instance is kept around.
     * 
     * @return A Buffer that stores C pointer to underlying native handle of the image. On 
     * macOS, a pointer to NSImage instance would be returned.
     */
    fun getNativeHandle(): dynamic = 
        instance.getNativeHandle()

    /**
     * @return Whether the image is empty.
     */
    fun isEmpty(): Boolean = 
        instance.isEmpty()

    /**
     *  . width Integer
     *  . height Integer
     *
     * @return 
     */
    fun getSize(width: Int, height: Int): dynamic = 
        instance.getSize(width, height)

    /**
     * Marks the image as a template image.
     */
    fun setTemplateImage(option: Boolean): Unit = 
        instance.setTemplateImage(option)

    /**
     * @return Whether the image is a template image.
     */
    fun isTemplateImage(): Boolean = 
        instance.isTemplateImage()

    /**
     * @return The cropped image.
     */
    fun crop(rect: CropRect): NativeImage = 
        instance.crop(rect)

    /**
     * If only the height or the width are specified then the current aspect ratio 
     * will be preserved in the resized image.
     * 
     * @return The resized image.
     */
    fun resize(options: ResizeOptions.() -> Unit): NativeImage = 
        instance.resize(options.let { ResizeOptions().apply(it) })

    /**
     * @return The image's aspect ratio.
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

