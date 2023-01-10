package com.example.demoday2.data.local

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Entity
data class Demo constructor(val message: String, @PrimaryKey val id: Int = 0)

@Dao
interface DemoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDemo(demo: Demo)

    @get:Query("select * from Demo where id = 0")
    val demoLiveData: LiveData<Demo?>
}

@Database(entities = [Demo::class], version = 1, exportSchema = false)
abstract class DemoDatabase : RoomDatabase() {
    abstract val demoDao: DemoDao
}

private lateinit var INSTANCE: DemoDatabase

fun getDatabase(context: Context): DemoDatabase {
    synchronized(DemoDatabase::class) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room
                .databaseBuilder(
                    context,
                    DemoDatabase::class.java,
                    "demo_db"
                )
                .fallbackToDestructiveMigration()
                .build()
        }
    }
    return INSTANCE
}