package com.androidalliance.mynilam.data.repositories.interfaces

import com.androidalliance.mynilam.data.models.Review
import kotlinx.coroutines.flow.Flow

interface ReviewRepository {
    fun getAllReviews(): Flow<List<Review>>
    fun getReviewByUser(userId: Int): Flow<List<Review>>
    fun getReviewByMaterials(materialId: Int): Flow<List<Review>>
    fun getReviewById(materialId: Int): Flow<Review>
    suspend fun insertReview(review: Review)
    suspend fun deleteReview(review: Review)
    suspend fun updateReview(review: Review)
}