package com.example.model

import com.squareup.moshi.Json

data class LoginResponse(
    val status: String?,
    val result: String?,
    val data: LoginData?
)

data class LoginData(
    @field:Json(name = "id") val userId: Int?,
    @field:Json(name = "country_code") val countryCode: String?,
    @field:Json(name = "language_code") val languageCode: String?,
    @field:Json(name = "name") val name: String?,
    @field:Json(name = "first_name") val firstName: String?,
    @field:Json(name = "last_name") val lastName: String?,
    @field:Json(name = "dob") val dateOfBirth: String?,
    @field:Json(name = "newsletter") val newsLetter: String?,
    @field:Json(name = "state") val state: String?,
    @field:Json(name = "city") val city: String?,
    @field:Json(name = "zipcode") val zipCode: String?,
    @field:Json(name = "address") val address: String?,
    @field:Json(name = "phone") val phone: String?,
    @field:Json(name = "username") val username: String?,
    @field:Json(name = "email") val email: String?,
    @field:Json(name = "remember_token") val remember_token: String?,
    @field:Json(name = "is_admin") val isAdmin: String?,
    @field:Json(name = "can_be_impersonated") val impersonated: String?,
    @field:Json(name = "disable_comments") val commentDisabled: String?,
    @field:Json(name = "receive_newsletter") val receiveNewsLetter: String?,
    @field:Json(name = "receive_advice") val receiveAdvice: String?,
    @field:Json(name = "verified_email") val emailVerified: String?,
    @field:Json(name = "verified_phone") val phoneVerified: String?,
    @field:Json(name = "blocked") val blocked: String?,
    @field:Json(name = "closed") val closed: String?,
    @field:Json(name = "user_new") val userNew: String?,
    @field:Json(name = "fcm_id") val fcmToken: String?,
    @field:Json(name = "auth_token") val authToken: String?
)