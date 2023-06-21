package com.example.bibleapps

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class DictionaryBibleActivity : AppCompatActivity() {

    private lateinit var recyclerViewBooks: RecyclerView
    private lateinit var bookAdapter: BookAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dictionary_bible)

        recyclerViewBooks = findViewById(R.id.recyclerViewBooks)

        // Mendapatkan daftar kitab Alkitab dengan nama dalam bahasa Inggris dan Bahasa Indonesia
        val bookList = getBookList()

        // Membuat dan mengatur adapter untuk RecyclerView
        bookAdapter = BookAdapter(bookList)
        recyclerViewBooks.adapter = bookAdapter
        recyclerViewBooks.layoutManager = LinearLayoutManager(this)
    }

    private fun getBookList(): List<Pair<String, String>> {
        return listOf(
            Pair("Genesis", "Kejadian"),
            Pair("Exodus", "Keluaran"),
            Pair("Leviticus", "Imamat"),
            Pair("Numbers", "Bilangan"),
            Pair("Deuteronomy", "Ulangan"),
            Pair("Joshua", "Yosua"),
            Pair("Judges", "Hakim-hakim"),
            Pair("Ruth", "Rut"),
            Pair("1 Samuel", "1 Samuel"),
            Pair("2 Samuel", "2 Samuel"),
            Pair("1 Kings", "1 Raja-raja"),
            Pair("2 Kings", "2 Raja-raja"),
            Pair("1 Chronicles", "1 Tawarikh"),
            Pair("2 Chronicles", "2 Tawarikh"),
            Pair("Ezra", "Ezra"),
            Pair("Nehemiah", "Nehemia"),
            Pair("Esther", "Ester"),
            Pair("Job", "Ayub"),
            Pair("Psalms", "Mazmur"),
            Pair("Proverbs", "Amsal"),
            Pair("Ecclesiastes", "Pengkhotbah"),
            Pair("Song of Solomon", "Kidung Agung"),
            Pair("Isaiah", "Yesaya"),
            Pair("Jeremiah", "Yeremia"),
            Pair("Lamentations", "Ratapan"),
            Pair("Ezekiel", "Yehezkiel"),
            Pair("Daniel", "Daniel"),
            Pair("Hosea", "Hosea"),
            Pair("Joel", "Yoel"),
            Pair("Amos", "Amos"),
            Pair("Obadiah", "Obaja"),
            Pair("Jonah", "Yunus"),
            Pair("Micah", "Mikha"),
            Pair("Nahum", "Nahum"),
            Pair("Habakkuk", "Habakuk"),
            Pair("Zephaniah", "Zefanya"),
            Pair("Haggai", "Hagai"),
            Pair("Zechariah", "Zakharia"),
            Pair("Malachi", "Maleakhi"),
            Pair("Matthew", "Matius"),
            Pair("Mark", "Markus"),
            Pair("Luke", "Lukas"),
            Pair("John", "Yohanes"),
            Pair("Acts", "Kisah Para Rasul"),
            Pair("Romans", "Roma"),
            Pair("1 Corinthians", "1 Korintus"),
            Pair("2 Corinthians", "2 Korintus"),
            Pair("Galatians", "Galatia"),
            Pair("Ephesians", "Efesus"),
            Pair("Philippians", "Filipi"),
            Pair("Colossians", "Kolose"),
            Pair("1 Thessalonians", "1 Tesalonika"),
            Pair("2 Thessalonians", "2 Tesalonika"),
            Pair("1 Timothy", "1 Timotius"),
            Pair("2 Timothy", "2 Timotius"),
            Pair("Titus", "Titus"),
            Pair("Philemon", "Filemon"),
            Pair("Hebrews", "Ibrani"),
            Pair("James", "Yakobus"),
            Pair("1 Peter", "1 Petrus"),
            Pair("2 Peter", "2 Petrus"),
            Pair("1 John", "1 Yohanes"),
            Pair("2 John", "2 Yohanes"),
            Pair("3 John", "3 Yohanes"),
            Pair("Jude", "Yudas"),
            Pair("Revelation", "Wahyu")
        )
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