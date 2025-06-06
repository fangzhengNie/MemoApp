package com.example.memo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.memo.data.Memo
import com.example.memo.data.MemoDatabase
import kotlinx.coroutines.launch

class AddMemoActivity : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var etContent: EditText
    private lateinit var btnSave: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_memo)

        etTitle = findViewById(R.id.etTitle)
        etContent = findViewById(R.id.etContent)
        btnSave = findViewById(R.id.btnSave)

        btnSave.setOnClickListener {
            val title = etTitle.text.toString().trim()
            val content = etContent.text.toString().trim()

            if (title.isEmpty()) {
                Toast.makeText(this, "请输入标题", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                val memo = Memo(
                    title = title,
                    content = content,
                    timestamp = System.currentTimeMillis()
                )
                MemoDatabase.getDatabase(this@AddMemoActivity).memoDao().insertMemo(memo)
                Toast.makeText(this@AddMemoActivity, "备忘录保存成功", Toast.LENGTH_SHORT).show()
                setResult(RESULT_OK)
                finish() // 关闭页面，返回上一页
            }
        }
    }
}
