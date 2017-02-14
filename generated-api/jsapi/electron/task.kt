@file:Suppress("UnsafeCastFromDynamic")
package jsapi.electron

class Task(
        var program: String,
        var arguments: String,
        var title: String,
        var description: String,
        var iconPath: String,
        var iconIndex: Number
) {

    val instance: dynamic = this

    // ~ Builders ------------------------------------------------------------------------------
}

