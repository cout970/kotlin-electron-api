package jsapi.electron

class Cookies() {

    val instance: dynamic

    init {
        val _constructor = js("require('electron').Cookies")
        instance = js("new _constructor(_)")
    }

    // ~ Events --------------------------------------------------------------------------------

    fun onEvent(event: String, callback: () -> Unit) = 
        module.on(event, callback)

    // ~ Methods -------------------------------------------------------------------------------

    /**
     * Sends a request to get all cookies matching details, callback will be called with callback(error, cookies) on complete.
     *
     * cookies is an Array of cookie objects.
     */
    fun get(filter: GetFilter.() -> Unit, callback: (error: Error, cookies: Array<Cookies>) -> Unit): Unit = 
        instance.get(filter.let { GetFilter().apply(it) }, callback)

    /**
     * Sets a cookie with details, callback will be called with callback(error) on complete.
     */
    fun set(details: SetDetails, callback: (error: Error) -> Unit): Unit = 
        instance.set(details, callback)

    /**
     * Removes the cookies matching url and name, callback will called with callback() on complete.
     */
    fun remove(url: String, name: String, callback: () -> Unit): Unit = 
        instance.remove(url, name, callback)

    // ~ Companion -----------------------------------------------------------------------------

    companion object { 

        private val module: dynamic = js("require('electron').Cookies")

    }

    // ~ Builders ------------------------------------------------------------------------------

    class GetFilter(
        var url: String? = null,
        var name: String? = null,
        var domain: String? = null,
        var path: String? = null,
        var secure: Boolean? = null,
        var session: Boolean? = null
    )

    class SetDetails(
        var url: String,
        var name: String? = null,
        var value: String? = null,
        var domain: String? = null,
        var path: String? = null,
        var secure: Boolean? = null,
        var httpOnly: Boolean? = null,
        var expirationDate: Double? = null
    )
}

