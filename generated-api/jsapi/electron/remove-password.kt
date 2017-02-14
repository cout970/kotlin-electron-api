@file:Suppress("UnsafeCastFromDynamic")
package jsapi.electron

class RemovePassword(
        var type: String,
        var origin: String? = null,
        var scheme: String? = null,
        var realm: String? = null,
        var username: String? = null,
        var password: String? = null
) {

    val instance: dynamic = this

    // ~ Builders ------------------------------------------------------------------------------
}

