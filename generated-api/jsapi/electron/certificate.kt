@file:Suppress("UnsafeCastFromDynamic")
package jsapi.electron

@Suppress("REDUNDANT_NULLABLE")
class Certificate(
    /**
     * PEM encoded data
     */
        var data: String,

    /**
     * Issuer principal
     */
        var issuer: CertificatePrincipal,

    /**
     * Issuer's Common Name
     */
        var issuerName: String,

    /**
     * Issuer certificate (if not self-signed)
     */
        var issuerCert: Certificate,

    /**
     * Subject principal
     */
        var subject: CertificatePrincipal,

    /**
     * Subject's Common Name
     */
        var subjectName: String,

    /**
     * Hex value represented string
     */
        var serialNumber: String,

    /**
     * Start date of the certificate being valid in seconds
     */
        var validStart: Number,

    /**
     * End date of the certificate being valid in seconds
     */
        var validExpiry: Number,

    /**
     * Fingerprint of the certificate
     */
        var fingerprint: String

) {

    val instance: dynamic = this

    // ~ Builders ------------------------------------------------------------------------------
}

