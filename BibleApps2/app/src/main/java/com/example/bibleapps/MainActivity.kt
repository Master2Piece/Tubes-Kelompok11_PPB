package com.example.bibleapps

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONObject
import java.io.IOException

class MainActivity : AppCompatActivity() {

    private val client = OkHttpClient()

    private lateinit var searchButton: Button
    private lateinit var bookEditText: EditText
    private lateinit var chapterEditText: EditText
    private lateinit var resultTextView: TextView
    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(androidx.appcompat.R.style.Theme_AppCompat)
        setContentView(R.layout.activity_main)

        searchButton = findViewById(R.id.searchButton)
        bookEditText = findViewById(R.id.bookEditText)
        chapterEditText = findViewById(R.id.chapterEditText)
        resultTextView = findViewById(R.id.resultTextView)
        sharedPreferences = getSharedPreferences("History", Context.MODE_PRIVATE)

        searchButton.setOnClickListener {
            val book = bookEditText.text.toString()
            val chapter = chapterEditText.text.toString()

            if (book.isNotEmpty() && chapter.isNotEmpty()) {
                val url = "https://ajith-holy-bible.p.rapidapi.com/GetChapter?Book=$book&chapter=$chapter"
                val request = Request.Builder()
                    .url(url)
                    .header("X-RapidAPI-Key", "27a98c0eccmshd1364892469dcd5p11329bjsn1ab17e5e85d5")
                    .header("X-RapidAPI-Host", "ajith-holy-bible.p.rapidapi.com")
                    .build()
                saveHistory(book, chapter)

                lifecycleScope.launch(Dispatchers.IO) {
                    try {
                        val response = client.newCall(request).execute()
                        val responseData = response.body?.string()
                        Log.d("API Call", "Response: $responseData")

                        runOnUiThread {
                            if (responseData != null) {
                                val json = JSONObject(responseData)
                                val output = json.getString("Output")
                                resultTextView.text = output
                                saveHistory(book, chapter)
                            } else {
                                resultTextView.text = "No response data"
                            }
                        }
                    } catch (e: IOException) {
                        Log.e("API Call", "Request failed: ${e.message}")
                        runOnUiThread {
                            resultTextView.text = "Request failed: ${e.message}"
                        }
                    }
                }
            }
        }
    }

    private fun saveHistory(book: String, chapter: String) {
        val sharedPreferences = getSharedPreferences("History", Context.MODE_PRIVATE)
        val historySet = sharedPreferences.getStringSet("history_set", HashSet())

        // Menggabungkan histori yang ada dengan pencarian baru
        val updatedHistorySet = HashSet<String>(historySet)
        updatedHistorySet.add("$book $chapter")

        // Menyimpan histori yang diperbarui kembali ke SharedPreferences
        val editor = sharedPreferences.edit()
        editor.putStringSet("history_set", updatedHistorySet)
        editor.apply()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_bible, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_reading_plan -> {
                // Aksi yang dijalankan ketika opsi "Reading Plan" dipilih
                // Implementasikan logika atau navigasi yang sesuai di sini
                val intent = Intent(this, ReadingPlanActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_main -> {
                // Aksi yang dijalankan ketika opsi "Reading Plan" dipilih
                // Implementasikan logika atau navigasi yang sesuai di sini
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_daily_verse -> {
                // Aksi yang dijalankan ketika opsi "Ayat Harian" dipilih
                // Implementasikan logika atau navigasi yang sesuai di sini
                val intent = Intent(this, DailyVerseActivity::class.java)
                startActivity(intent)
                return true
            }
            R.id.menu_bible_dictionary -> {
                // Aksi yang dijalankan ketika opsi "Dictionary Alkitab" dipilih
                // Implementasikan logika atau navigasi yang sesuai di sini
                val intent = Intent(this, DictionaryBibleActivity::class.java)
                startActivity(intent)
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

}