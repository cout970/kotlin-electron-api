package jsapi.electron

class CertificatePrincipal(
        var commonName: String, 
        var organizations: Array<String>, 
        var organizationUnits: Array<String>, 
        var locality: String, 
        var state: String, 
        var country: String
) {

    val instance: dynamic = this

    // ~ Builders -------------------------------------------------------------------------------

}

