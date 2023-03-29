package com.example.data.mapper

import com.example.datasource.request.LoginRequestDTO
import com.example.datasource.response.LoginResponseDTO
import com.example.domain.model.LoginRequestDomain
import com.example.domain.model.LoginResponseDomain
import org.apache.commons.codec.binary.Base64
import org.apache.commons.codec.digest.DigestUtils

class LoginDataMapper {

    fun mapResponseDtoToDomain(dto: LoginResponseDTO): LoginResponseDomain =
        LoginResponseDomain(image = dto.image?.let { decodeFromBase64(it) } ?: byteArrayOf())

    fun mapRequestDomainToDTO(domain: LoginRequestDomain) = LoginRequestDTO(
        username = domain.username,
        password = encodeToSha1(domain.password)
    )

    private fun encodeToSha1(plain: String) = DigestUtils.sha1Hex(plain)

    private fun decodeFromBase64(encoded: String) = Base64.decodeBase64(encoded)
}