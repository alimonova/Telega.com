package com.example.tg.telegacom

import androidx.room.Room
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.tg.telegacom.database.User
import com.example.tg.telegacom.database.UserDao
import com.example.tg.telegacom.database.TelegaDataBase
import org.junit.Assert.assertEquals
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class DBTest {

    private lateinit var userDao: UserDao
    private lateinit var db: TelegaDataBase

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        // Using an in-memory database because the information stored here disappears when the
        // process is killed.
        db = Room.inMemoryDatabaseBuilder(context, TelegaDataBase::class.java)
            // Allowing main thread queries, just for testing.
            .allowMainThreadQueries()
            .build()
        userDao = db.UserDao
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetChannel() {
        val user = User()
        user.Id = 1
        user.Email = "test@gmail.com"
        user.Password = "pass123"
        user.Nickname = "test_nickname"
        userDao.insert(user)

        val some_user = userDao.get(user.Id)
        assertEquals(user?.Nickname, some_user?.Nickname)
    }
}