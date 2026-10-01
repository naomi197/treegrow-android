package com.treegrow.app.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
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
abstract class TreeGrowDatabase : RoomDatabase() {
    // DAOs will be added here
}
