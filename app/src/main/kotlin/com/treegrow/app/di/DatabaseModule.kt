package com.treegrow.app.di

import android.content.Context
import androidx.room.Room
import com.treegrow.app.data.local.dao.AchievementDao
import com.treegrow.app.data.local.dao.TreeDao
import com.treegrow.app.data.local.dao.UserDao
import com.treegrow.app.data.local.database.TreeGrowDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideTreeGrowDatabase(
        @ApplicationContext context: Context
    ): TreeGrowDatabase {
        return Room.databaseBuilder(
            context,
            TreeGrowDatabase::class.java,
            "treegrow_database"
        ).build()
    }

    @Provides
    fun provideUserDao(database: TreeGrowDatabase): UserDao = database.userDao()

    @Provides
    fun provideTreeDao(database: TreeGrowDatabase): TreeDao = database.treeDao()

    @Provides
    fun provideAchievementDao(database: TreeGrowDatabase): AchievementDao = database.achievementDao()
}
