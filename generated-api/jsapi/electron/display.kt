@file:Suppress("UnsafeCastFromDynamic")
package jsapi.electron

class Display(
        var id: Number,
        var rotation: Number,
        var scaleFactor: Number,
        var touchSupport: String,
        var bounds: Rectangle,
        var size: dynamic,
        var workArea: Rectangle,
        var workAreaSize: dynamic
) {

    val instance: dynamic = this

    // ~ Builders ------------------------------------------------------------------------------

    class Size(
        var height: Number,
        var width: Number
    )

    class WorkAreaSize(
        var height: Number,
        var width: Number
    )
}

