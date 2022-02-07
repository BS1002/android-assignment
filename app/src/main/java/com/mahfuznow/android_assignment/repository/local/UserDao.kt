package com.mahfuznow.android_assignment.repository.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mahfuznow.android_assignment.model.user.User
import io.reactivex.rxjava3.core.Single

@Dao
interface UserDao {
    @Query("SELECT * FROM User")
    fun getUserResponse(): Single<User>

    @Insert
    fun insertUserResponse(user: User)

    @Delete
    fun deleteUserResponse(user: User)

    @Query("DELETE FROM User")
    fun deleteAllUserResponse()
}