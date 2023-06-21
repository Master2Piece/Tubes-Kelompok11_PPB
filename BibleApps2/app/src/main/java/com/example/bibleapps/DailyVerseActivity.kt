package com.example.bibleapps

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.*
import org.json.JSONObject
import java.io.IOException
import kotlin.random.Random

class DailyVerseActivity : AppCompatActivity() {

    private val client = OkHttpClient()
    private lateinit var titleTextView : TextView
    private lateinit var verseTextView : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_daily_verse)

        titleTextView = findViewById(R.id.titleTextView)
        verseTextView = findViewById(R.id.verseTextView)

        fetchDailyVerse()
    }

    private fun fetchDailyVerse() {
        val book = getRandomBook()
        val chapter = getRandomChapter()
        val verseFrom = getRandomVerse()
        val verseTo = getRandomVerse()

        val url = "https://ajith-holy-bible.p.rapidapi.com/GetVerses?Book=$book&chapter=$chapter&VerseFrom=$verseFrom&VerseTo=$verseTo"
        val request = Request.Builder()
            .url(url)
            .header("X-RapidAPI-Key", "27a98c0eccmshd1364892469dcd5p11329bjsn1ab17e5e85d5")
            .header("X-RapidAPI-Host", "ajith-holy-bible.p.rapidapi.com")
            .build()

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = client.newCall(request).execute()
                val responseData = response.body?.string()
                Log.d("API Call", "Response: $responseData")

                runOnUiThread {
                    if (responseData != null) {
                        val json = JSONObject(responseData)
                        val output = json.getString("Output")
                        val title = "$book $chapter:$verseFrom-$verseTo"
                        val verseText = "$output"

                        // Update tampilan UI dengan judul kitab dan ayat harian
                        titleTextView.text = title
                        verseTextView.text = verseText
                    } else {
                        // Handle jika tidak ada data respons
                    }
                }
            } catch (e: IOException) {
                Log.e("API Call", "Request failed: ${e.message}")
                runOnUiThread {
                    // Handle jika terjadi kesalahan saat melakukan request
                }
            }
        }
    }

    private fun getRandomBook(): String {
        // Implementasikan logika untuk mendapatkan nama buku secara acak
        // Misalnya, Anda dapat menggunakan daftar nama-nama buku dalam array dan mengambil satu secara acak
        val books = arrayOf("Genesis", "Exodus", "Leviticus", "Numbers", "Deuteronomy","Joshua", "Judges","Ruth","1 Samuel","2 Samuel","1 Kings","2 Kings","1 Chronicles","2 Chronicles","Ezra","Nehemiah","Esther","Job","Psalms","Proverbs","Ecclesiastes","Song of Solomon","Isaiah","Jeremiah","Lamentations","Ezekiel","Daniel","Hosea","Joel","Amos","Obadiah","Jonah","Micah","Nahum","Habakkuk","Zephaniah","Haggai","Zechariah","Malachi","Matthew","Mark","Luke","John","Acts","Romans","1 Corinthians","2 Corinthians","Galatians","Ephesians","Philippians","Colossians","1 Thessalonians","2 Thessalonians","1 Timothy\n","2 Timothy","Titus","Philemon","Hebrews","James","1 Peter","2 Peter", "1 John", "2 John", "3 John", "Jude","Revelation")
        val randomIndex = Random.nextInt(books.size)
        return books[randomIndex]
    }

    private fun getRandomChapter(): String {
        // Implementasikan logika untuk mendapatkan nomor chapter secara acak
        // Misalnya, Anda dapat menentukan batas atas dan bawah untuk nomor chapter dan menghasilkan nomor secara acak dalam rentang tersebut
        val minChapter = 1
        val maxChapter = 10
        return (minChapter..maxChapter).random().toString()
    }

    private fun getRandomVerse(): String {
        // Implementasikan logika untuk mendapatkan nomor verse secara acak
        // Misalnya, Anda dapat menentukan batas atas dan bawah untuk nomor verse dan menghasilkan nomor secara acak dalam rentang tersebut
        val minVerse = 1
        val maxVerse = 20

        return (minVerse..maxVerse).random().toString()
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