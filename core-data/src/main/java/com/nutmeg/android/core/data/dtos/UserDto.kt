package com.nutmeg.android.core.data.dtos

import com.squareup.moshi.Json

data class UserDto(
    val id: Int,
    val name: String,
    val username: String,
    val email: String,
    @Json(name = "Address")
    val address: AddressDto?,
    val phone: String,
    val website: String,
    @Json(name = "Company")
    val companyDto: CompanyDto?,
)

data class AddressDto(
    val street: String,
    val suite: String,
    val city: String,
    val zipcode: String,
    @Json(name = "Geo")
    val geoDto: GeoDto?,
)

data class GeoDto(
    val lat: String,
    val lng: String,
)

data class CompanyDto(
    val name: String,
    val catchPhrase: String,
    val bs: String,
)
