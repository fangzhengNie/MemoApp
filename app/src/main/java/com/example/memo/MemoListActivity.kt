package com.example.memo

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.memo.data.Memo
import com.example.memo.data.MemoDatabase
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch

class MemoListActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: MemoAdapter

    private val addMemoLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            loadMemos()
        }
    }

    private val editMemoLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            loadMemos()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_memo_list)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        adapter = MemoAdapter { memo ->
            val intent = Intent(this, EditMemoActivity::class.java)
            intent.putExtra("memo_id", memo.id)
            editMemoLauncher.launch(intent)
        }
        recyclerView.adapter = adapter

        findViewById<FloatingActionButton>(R.id.fabAddMemo).setOnClickListener {
            val intent = Intent(this, AddMemoActivity::class.java)
            addMemoLauncher.launch(intent)
        }

        adapter.setOnItemLongClickListener { memo ->
            showDeleteConfirmDialog(memo)
        }

        val btnLogout = findViewById<Button>(R.id.btnLogout)
        btnLogout.setOnClickListener {
            Toast.makeText(this, "已退出登录", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }

        val btnClearAccount = findViewById<Button>(R.id.btnClearAccount)
        btnClearAccount.setOnClickListener {
            val sp = getSharedPreferences("login_prefs", Context.MODE_PRIVATE)
            with(sp.edit()) {
                clear()
                apply()
            }
            Toast.makeText(this, "账户信息已清除", Toast.LENGTH_SHORT).show()
        }

        loadMemos()
    }

    private fun loadMemos() {
        val db = MemoDatabase.getDatabase(this)
        lifecycleScope.launch {
            val memos = db.memoDao().getAllMemos()
            adapter.submitList(memos)
            Toast.makeText(this@MemoListActivity, "加载了${memos.size}条备忘录", Toast.LENGTH_SHORT).show()
        }
    }

    private fun showDeleteConfirmDialog(memo: Memo) {
        AlertDialog.Builder(this)
            .setTitle("删除备忘录")
            .setMessage("确定删除标题为 \"${memo.title}\" 的备忘录吗？")
            .setPositiveButton("删除") { _, _ ->
                lifecycleScope.launch {
                    MemoDatabase.getDatabase(this@MemoListActivity).memoDao().deleteMemo(memo)
                    loadMemos()
                    Toast.makeText(this@MemoListActivity, "删除成功", Toast.LENGTH_SHORT).show()
                }
            }
            .setNegativeButton("取消", null)
            .show()
    }
}
