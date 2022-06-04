package com.saskiahfu.hfu.cookingapp

import android.app.Application
import com.saskiahfu.hfu.cookingapp.domain.downloadUseCase.DownloadMealsUseCase
import com.saskiahfu.hfu.cookingapp.domain.downloadUseCase.DownloadRecipesUseCase
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

/**
 * Main entry point into the application process.
 * Registered in the AndroidManifest.xml file.
 */
@HiltAndroidApp
class App : Application() {
    @Inject
    lateinit var downloadMeals: DownloadMealsUseCase
    lateinit var downloadRecipes: DownloadRecipesUseCase

    override fun onCreate() {
        super.onCreate()
        runBlocking {
            downloadMeals()
            downloadRecipes()
        }

    }
}
