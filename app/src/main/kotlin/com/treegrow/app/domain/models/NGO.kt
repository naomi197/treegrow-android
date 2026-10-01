package com.treegrow.app.domain.models

data class NGO(
    val id: String,
    val name: String,
    val description: String,
    val logo: String,
    val website: String,
    val email: String,
    val phone: String,
    val country: String,
    val region: String,
    val totalTrees: Int = 0,
    val rating: Double = 0.0,
    val mission: String,
    val socials: SocialLinks = SocialLinks()
)

data class SocialLinks(
    val facebook: String? = null,
    val twitter: String? = null,
    val instagram: String? = null,
    val linkedin: String? = null
)
