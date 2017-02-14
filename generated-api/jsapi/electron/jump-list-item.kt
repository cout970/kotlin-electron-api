@file:Suppress("UnsafeCastFromDynamic")
package jsapi.electron

class JumpListItem(
        var type: String? = null,
        var path: String? = null,
        var program: String? = null,
        var args: String? = null,
        var title: String? = null,
        var description: String? = null,
        var iconPath: String? = null,
        var iconIndex: Number? = null
) {

    val instance: dynamic = this

    // ~ Builders ------------------------------------------------------------------------------
}

