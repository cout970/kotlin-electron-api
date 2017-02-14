@file:Suppress("UnsafeCastFromDynamic")
package jsapi.electron

class UploadFile(
        var type: String,
        var filePath: String,
        var offset: Int,
        var length: Int,
        var modificationTime: Double
) {

    val instance: dynamic = this

    // ~ Builders ------------------------------------------------------------------------------
}

