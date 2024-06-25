package com.androidalliance.mynilam.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.androidalliance.mynilam.data.models.Review
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {
    @Query("SELECT * FROM review")
    fun getAllReviews(): Flow<List<Review>>
    @Query("SELECT * FROM review WHERE user_id = :userId")
    fun getReviewByUser(userId: Int): Flow<List<Review>>
    @Query("SELECT * FROM review WHERE material_id = :materialId")
    fun getReviewByMaterials(materialId: Int): Flow<List<Review>>
    @Query("SELECT * FROM review WHERE review_id = :materialId")
    fun getReviewById(materialId: Int): Review?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertReview(review: Review)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun updateReview(review: Review)

    @Delete
    suspend fun deleteReview(review: Review)

}