package com.example.lab16

import android.content.ContentValues
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {
    private var items: ArrayList<String> = ArrayList()
    private lateinit var adapter: ArrayAdapter<String>
    private val uri = Uri.parse("content://com.example.lab16")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupListView()
        setListener()
    }

    // 初始化 ListView
    private fun setupListView() {
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, items)
        findViewById<ListView>(R.id.listView).adapter = adapter
    }

    // 設定 UI 元件的監聽器
    private fun setListener() {
        val ed_book = findViewById<EditText>(R.id.ed_book)
        val ed_price = findViewById<EditText>(R.id.ed_price)

        findViewById<Button>(R.id.btn_insert).setOnClickListener {
            handleInsert(ed_book, ed_price)
        }

        findViewById<Button>(R.id.btn_update).setOnClickListener {
            handleUpdate(ed_book, ed_price)
        }

        findViewById<Button>(R.id.btn_delete).setOnClickListener {
            handleDelete(ed_book)
        }

        findViewById<Button>(R.id.btn_query).setOnClickListener {
            handleQuery(ed_book)
        }
    }

    // 新增書籍
    private fun handleInsert(ed_book: EditText, ed_price: EditText) {
        val name = ed_book.text.toString()
        val price = ed_price.text.toString()

        if (name.isEmpty() || price.isEmpty()) {
            showToast("欄位請勿留空")
            return
        }

        val values = ContentValues().apply {
            put("book", name)
            put("price", price)
        }

        val contentUri = contentResolver.insert(uri, values)
        if (contentUri != null) {
            showToast("新增:$name,價格:$price")
            cleanEditText()
        } else {
            showToast("新增失敗")
        }
    }

    // 更新書籍
    private fun handleUpdate(ed_book: EditText, ed_price: EditText) {
        // ... (相似於 handleInsert 的流程，但操作 update)
    }

    // 刪除書籍
    private fun handleDelete(ed_book: EditText) {
        // ... (類似的流程)
    }

    // 查詢書籍
    private fun handleQuery(ed_book: EditText) {
        // ... (類似的流程)
    }

    // 顯示 Toast 訊息
    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_LONG).show()
    }

    // 清空輸入框
    private fun cleanEditText() {
        findViewById<EditText>(R.id.ed_book).setText("")
        findViewById<EditText>(R.id.ed_price).setText("")
    }
}
