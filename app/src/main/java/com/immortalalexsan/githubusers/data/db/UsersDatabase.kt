package com.immortalalexsan.githubusers.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.immortalalexsan.githubusers.data.db.dao.UsersDao
import com.immortalalexsan.githubusers.data.db.entities.UserEntity

/**
 * База данных приложения.
 */
@Database(
    version = UsersDatabase.DATABASE_VERSION,
    exportSchema = false,
    entities = [
        UserEntity::class
    ]
)
internal abstract class UsersDatabase : RoomDatabase() {

    /**
     * Дао для таблицы пользователей.
     */
    abstract val usersDao: UsersDao

    companion object {
        /**
         * Версия базы данных.
         */
        const val DATABASE_VERSION = 1

        /**
         * Имя базы данных.
         */
        private const val DATABASE_NAME = "users_database"

        @Volatile
        private var innerInstance: UsersDatabase? = null

        /**
         * Получает экземпляр базы данных.
         *
         * @param applicationContext Контекст приложения.
         * @return Экземпляр базы данных.
         */
        fun getInstance(applicationContext: Context): UsersDatabase {
            synchronized(this) {
                var instance = innerInstance
                if (instance == null) {
                    instance = Room.databaseBuilder(
                        applicationContext.applicationContext,
                        UsersDatabase::class.java,
                        DATABASE_NAME
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    innerInstance = instance
                }
                return instance
            }
        }
    }
}
