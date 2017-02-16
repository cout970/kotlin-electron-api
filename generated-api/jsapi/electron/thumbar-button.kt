@file:Suppress("UnsafeCastFromDynamic")
package jsapi.electron

@Suppress("REDUNDANT_NULLABLE")
class ThumbarButton(
    /**
     * The icon showing in thumbnail toolbar.
     */
        var icon: NativeImage,

    /**
     * 
     */
        var click: () -> Unit,

    /**
     * The text of the button's tooltip.
     */
        var tooltip: String? = null,

    /**
     * Control specific states and behaviors of the button. By default, it is 
     * ['enabled'].
     */
        var flags: Array<String>? = null

) {

    val instance: dynamic = this

    // ~ Builders ------------------------------------------------------------------------------
}

