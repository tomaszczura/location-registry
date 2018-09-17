package com.astalos.locationregistry.di.modules

import android.app.Application
import android.arch.persistence.room.Room
import com.astalos.locationregistry.model.database.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

/**
 * @author Tomasz Czura on 9/4/18.
 */
@Module
class ApplicationModule {

    @Singleton
    @Provides
    fun provideDb(app: Application): AppDatabase {
        return Room
                .databaseBuilder(app, AppDatabase::class.java, "app_database.db")
                .fallbackToDestructiveMigration()
                .build()
    }
}