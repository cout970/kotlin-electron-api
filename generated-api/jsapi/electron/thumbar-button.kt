@file:Suppress("UnsafeCastFromDynamic")
package jsapi.electron

class ThumbarButton(
        var icon: NativeImage,
        var click: () -> Unit,
        var tooltip: String? = null,
        var flags: Array<String>? = null
) {

    val instance: dynamic = this

    // ~ Builders ------------------------------------------------------------------------------
}

