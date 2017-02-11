package jsapi.electron

class Cookie(
        var name: String, 
        var value: String, 
        var domain: String? = null, 
        var hostOnly: Boolean? = null, 
        var path: String? = null, 
        var secure: Boolean? = null, 
        var httpOnly: Boolean? = null, 
        var session: Boolean? = null, 
        var expirationDate: Double? = null
) {

    val instance: dynamic = this

    // ~ Builders -------------------------------------------------------------------------------

}

