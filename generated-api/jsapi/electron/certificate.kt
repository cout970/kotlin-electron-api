package jsapi.electron

class Certificate(
        var data: String, 
        var issuer: CertificatePrincipal, 
        var issuerName: String, 
        var issuerCert: Certificate, 
        var subject: CertificatePrincipal, 
        var subjectName: String, 
        var serialNumber: String, 
        var validStart: Float, 
        var validExpiry: Float, 
        var fingerprint: String
) {

    val instance: dynamic = this

    // ~ Builders -------------------------------------------------------------------------------

}

