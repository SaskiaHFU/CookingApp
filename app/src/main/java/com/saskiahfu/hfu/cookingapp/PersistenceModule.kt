package com.saskiahfu.hfu.cookingapp

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.saskiahfu.hfu.cookingapp.data.database.AppDatabase
import com.saskiahfu.hfu.cookingapp.data.database.ProductDao
import com.saskiahfu.hfu.cookingapp.data.database.cart.ShoppingCartDao
import com.saskiahfu.hfu.cookingapp.data.database.recipe.RecipeDao
import com.saskiahfu.hfu.cookingapp.data.database.links.LinksCategoryDao
import com.saskiahfu.hfu.cookingapp.data.database.links.LinksDao
import com.saskiahfu.hfu.cookingapp.data.database.mealplan.DailyMealsDao
//import com.saskiahfu.hfu.cookingapp.data.database.recipe.RecipeCategoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {

    private val Context.userSettingsStore: DataStore<Preferences> by preferencesDataStore(name = "userSettings")

    @Provides
    @Singleton
    fun provideUserSettingsDataStore(
        @ApplicationContext context: Context,
    ): DataStore<Preferences> = context.userSettingsStore

    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext context: Context,
    ): AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, "app")
        .apply {
            if (BuildConfig.DEBUG) fallbackToDestructiveMigration()
        }
        .build()

    @Provides
    fun provideProductsDao(
        database: AppDatabase,
    ): ProductDao = database.productDao()

    @Provides
    fun provideShoppingCartDao(
        database: AppDatabase,
    ): ShoppingCartDao = database.shoppingCartDao()

    @Provides
    fun provideRecipeDao(
        database: AppDatabase,
    ): RecipeDao = database.recipeDao()

//    @Provides
//    fun provideRecipeCatDao(
//        database: AppDatabase,
//    ): RecipeCategoryDao = database.recipeCatDao()

    @Provides
    fun provideLinksDao(
        database: AppDatabase,
    ): LinksDao = database.linksDao()

    @Provides
    fun provideLinksCategoryDao(
        database: AppDatabase,
    ): LinksCategoryDao = database.linksCategoryDao()

    @Provides
    fun provideDailyMealsDao(
        database: AppDatabase,
    ): DailyMealsDao = database.dailyMealsDao()
}
