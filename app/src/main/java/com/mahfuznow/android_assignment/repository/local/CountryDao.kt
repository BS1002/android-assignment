package com.mahfuznow.android_assignment.repository.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.mahfuznow.android_assignment.model.country.Country
import io.reactivex.rxjava3.core.Single

@Dao
interface CountryDao {
    @Query("SELECT * FROM Country")
    fun getAllCountries(): Single<List<Country>>

    @Insert
    fun insertCountry(country: Country)

    @Insert
    fun insertAllCountries(vararg countries: Country)

    @Delete
    fun deleteCountry(country: Country)

    @Query("DELETE FROM Country")
    fun deleteAllCountries()
}