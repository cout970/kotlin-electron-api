package jsapi.electron

class ShortcutDetails(
        var target: String,
        var cwd: String? = null,
        var args: String? = null,
        var description: String? = null,
        var icon: String? = null,
        var iconIndex: Number? = null,
        var appUserModelId: String? = null
) {

    val instance: dynamic = this

    // ~ Builders ------------------------------------------------------------------------------
}

