@file:Suppress("UnsafeCastFromDynamic")
package jsapi.electron

@Suppress("REDUNDANT_NULLABLE")
class MimeTypedBuffer(
    /**
     * The mimeType of the Buffer that you are sending
     */
        var mimeType: String,

    /**
     * The actual Buffer content
     */
        var buffer: dynamic

) {

    val instance: dynamic = this

    // ~ Builders ------------------------------------------------------------------------------
}

