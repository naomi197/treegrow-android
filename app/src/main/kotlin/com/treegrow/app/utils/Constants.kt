package com.treegrow.app.utils

object Constants {
    const val TAG = "TreeGrow"
    const val DATABASE_NAME = "treegrow_database"
    const val BASE_URL = "https://api.treegrow.app/v1/"
    
    // Firebase
    const val FIREBASE_PROJECT_ID = "treegrow-project"
    
    // Preferences
    const val PREFS_USER_ID = "user_id"
    const val PREFS_USER_TOKEN = "user_token"
    const val PREFS_THEME_MODE = "theme_mode"
    const val PREFS_LANGUAGE = "language"
    
    // Trees
    const val TREES_PER_PAGE = 20
    const val MAX_TREES_PER_USER = 10000
    
    // Carbon
    const val AVERAGE_CARBON_PER_TREE = 20.0 // kg per year
    
    // Points
    const val POINTS_PER_TREE = 50
    const val POINTS_PER_ACHIEVEMENT = 100
    const val POINTS_PER_CHALLENGE = 200
}
