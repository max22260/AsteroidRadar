package com.udacity.asteroidradar.common.data.cache

import com.udacity.asteroidradar.common.data.cache.daos.AsteroidDao
import com.udacity.asteroidradar.common.data.cache.entities.CachedAsteroid
import com.udacity.asteroidradar.common.utils.DateUtils.getNextSevenDaysFormattedDates
import com.udacity.asteroidradar.common.utils.DateUtils.getPreviousDate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AsteroidCache @Inject constructor(
    private val asteroidDao: AsteroidDao
) : Cache {
    override suspend fun storeAsteroids(asteroids: List<CachedAsteroid>) {

        asteroidDao.insertAsteroids(asteroids)
    }

    override fun getAllAsteroids(): Flow<List<CachedAsteroid>> {

        return asteroidDao.getAllAsteroid()
    }

    override suspend fun deletePreviousDayAsteroidData() {

        val date = getPreviousDate()

        withContext(Dispatchers.IO) {
                asteroidDao.deleteAsteroid(date)


        }
    }
}