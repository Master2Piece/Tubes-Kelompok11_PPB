package com.example.bibleapps

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ReadingPlanActivity : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var recyclerViewHistory: RecyclerView
    private lateinit var historyAdapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reading_plan)
        recyclerViewHistory = findViewById(R.id.recyclerViewHistory)
        // Inisialisasi SharedPreferences
        sharedPreferences = getSharedPreferences("History", Context.MODE_PRIVATE)

        // Mengambil daftar history Alkitab
        val historyList = getHistoryList()

        // Membuat dan mengatur adapter untuk RecyclerView
        historyAdapter = HistoryAdapter(historyList as MutableList<String>)
        recyclerViewHistory.adapter = historyAdapter
        recyclerViewHistory.layoutManager = LinearLayoutManager(this)
    }

    private fun getHistoryList(): List<String> {
        // Implementasikan logika untuk mendapatkan daftar history Alkitab
        // dari SharedPreferences atau database lainnya
        val historySet = sharedPreferences.getStringSet("history_set", HashSet())
        return historySet?.toMutableList() ?: mutableListOf()
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