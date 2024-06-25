package com.androidalliance.mynilam.data.repositories.implementation

import com.androidalliance.mynilam.data.dao.ReviewDao
import com.androidalliance.mynilam.data.models.Review
import com.androidalliance.mynilam.data.repositories.interfaces.ReviewRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.flowOf

class ReviewRepositoryImpl(
    private val reviewDao: ReviewDao
): ReviewRepository {
    override fun getAllReviews(): Flow<List<Review>> {
        try{
            return reviewDao.getAllReviews()
        }catch (e: Exception){
            e.printStackTrace()
            return flowOf(emptyList())
        }
    }

    override fun getReviewByUser(userId: Int): Flow<List<Review>> {
        try{
            return reviewDao.getReviewByUser(userId)
        }catch (e: Exception){
            e.printStackTrace()
            return flowOf(emptyList())
        }
    }

    override fun getReviewByMaterials(materialId: Int): Flow<List<Review>> {
        try{
            return reviewDao.getReviewByMaterials(materialId)
        }catch (e: Exception){
            e.printStackTrace()
            return flowOf(emptyList())
        }
    }

    override fun getReviewById(materialId: Int): Review? {
        return reviewDao.getReviewById(materialId)
    }

    override suspend fun insertReview(review: Review) {
        try{
            reviewDao.insertReview(review)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override suspend fun deleteReview(review: Review) {
        try{
            reviewDao.deleteReview(review)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }

    override suspend fun updateReview(review: Review) {
        try{
            reviewDao.updateReview(review)
        }catch (e: Exception){
            e.printStackTrace()
        }
    }
}