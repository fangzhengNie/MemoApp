package com.example.memo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.memo.data.Memo
import com.example.memo.data.MemoDatabase
import kotlinx.coroutines.launch

class EditMemoActivity : AppCompatActivity() {

    private lateinit var etTitle: EditText
    private lateinit var etContent: EditText
    private lateinit var btnSave: Button
    private lateinit var btnDelete: Button

    private var memoId: Long = -1L
    private var currentMemo: Memo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_memo)

        etTitle = findViewById(R.id.etTitle)
        etContent = findViewById(R.id.etContent)
        btnSave = findViewById(R.id.btnSave)
        btnDelete = findViewById(R.id.btnDelete)

        memoId = intent.getLongExtra("memo_id", -1L)
        if (memoId == -1L) {
            Toast.makeText(this, "备忘录不存在", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        loadMemo()

        btnSave.setOnClickListener {
            val title = etTitle.text.toString().trim()
            val content = etContent.text.toString().trim()

            if (title.isEmpty()) {
                Toast.makeText(this, "请输入标题", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            lifecycleScope.launch {
                currentMemo?.let { memo ->
                    val updatedMemo = memo.copy(
                        title = title,
                        content = content,
                        timestamp = System.currentTimeMillis()
                    )
                    MemoDatabase.getDatabase(this@EditMemoActivity).memoDao().updateMemo(updatedMemo)
                    Toast.makeText(this@EditMemoActivity, "备忘录更新成功", Toast.LENGTH_SHORT).show()
                    setResult(RESULT_OK)
                    finish()
                }
            }
        }

        btnDelete.setOnClickListener {
            AlertDialog.Builder(this)
                .setTitle("删除确认")
                .setMessage("确定要删除该备忘录吗？")
                .setPositiveButton("删除") { _, _ ->
                    lifecycleScope.launch {
                        currentMemo?.let { memo ->
                            MemoDatabase.getDatabase(this@EditMemoActivity).memoDao().deleteMemo(memo)
                            Toast.makeText(this@EditMemoActivity, "备忘录已删除", Toast.LENGTH_SHORT).show()
                            setResult(RESULT_OK)
                            finish()
                        }
                    }
                }
                .setNegativeButton("取消", null)
                .show()
        }
    }

    private fun loadMemo() {
        lifecycleScope.launch {
            val memo = MemoDatabase.getDatabase(this@EditMemoActivity).memoDao().getMemoById(memoId)
            if (memo == null) {
                Toast.makeText(this@EditMemoActivity, "备忘录不存在", Toast.LENGTH_SHORT).show()
                finish()
            } else {
                currentMemo = memo
                etTitle.setText(memo.title)
                etContent.setText(memo.content)
            }
        }
    }
}
