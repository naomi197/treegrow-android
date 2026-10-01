package com.treegrow.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.treegrow.app.data.local.dao.AchievementDao
import com.treegrow.app.data.local.dao.TreeDao
import com.treegrow.app.data.local.dao.UserDao
import com.treegrow.app.domain.models.Achievement
import com.treegrow.app.domain.models.Tree
import com.treegrow.app.domain.models.User

@Database(
    entities = [
        User::class,
        Tree::class,
        Achievement::class
    ],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class TreeGrowDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun treeDao(): TreeDao
    abstract fun achievementDao(): AchievementDao
}
