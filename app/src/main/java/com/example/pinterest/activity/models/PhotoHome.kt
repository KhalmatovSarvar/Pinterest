package com.example.pinterest.activity.models

import java.io.Serializable


data class PhotoHome (
    val id: String,
    val createdAt: String? = null,
    val updatedAt: String? = null,
    val promotedAt: String? = null,
    val width: Long? = null,
    val height: Long? = null,
    val color: String? = null,
    val blurHash: String? = null,
    val description: String? = null,
    val altDescription: Any? = null,
    val urls: Urls? = null,
    val links: WelcomeLinks? = null,
    val categories: List<Any?>? = null,
    val likes: Long? = null,
    val likedByUser: Boolean? = null,
    val currentUserCollections: List<Any?>? = null,
    val sponsorship: Sponsorship? = null,
    val topicSubmissions: TopicSubmissions? = null,
    val user: User? = null
):Serializable

data class WelcomeLinks (
    val self: String,
    val html: String,
    val download: String,
    val downloadLocation: String
):Serializable

data class Sponsorship (
    val impressionUrls: List<String>,
    val tagline: String,
    val taglineURL: String,
    val sponsor: User
):Serializable

data class User (
    val id: String,
    val updatedAt: String,
    val username: String,
    val name: String,
    val firstName: String,
    val lastName: String? = null,
    val twitterUsername: String? = null,
    val portfolioURL: String? = null,
    val bio: String? = null,
    val location: String? = null,
    val links: UserLinks,
    val profileImage: ProfileImage,
    val instagramUsername: String,
    val totalCollections: Long,
    val totalLikes: Long,
    val totalPhotos: Long,
    val acceptedTos: Boolean,
    val forHire: Boolean,
    val social: Social
):Serializable

data class UserLinks (
    val self: String,
    val html: String,
    val photos: String,
    val likes: String,
    val portfolio: String,
    val following: String,
    val followers: String
):Serializable

data class ProfileImage (
    val small: String,
    val medium: String,
    val large: String
):Serializable

data class Social (
    val instagramUsername: String,
    val portfolioURL: String? = null,
    val twitterUsername: String? = null,
    val paypalEmail: Any? = null
):Serializable

class TopicSubmissions():Serializable

data class Urls (
    val raw: String,
    val full: String,
    val regular: String,
    val small: String,
    val thumb: String,
    val smallS3: String
):Serializable

