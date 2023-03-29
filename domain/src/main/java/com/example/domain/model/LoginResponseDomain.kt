package com.example.domain.model

data class LoginResponseDomain(
    val image: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LoginResponseDomain

        if (!image.contentEquals(other.image)) return false

        return true
    }

    override fun hashCode(): Int {
        return image.contentHashCode()
    }
}
