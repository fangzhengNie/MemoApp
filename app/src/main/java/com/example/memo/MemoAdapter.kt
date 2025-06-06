package com.example.memo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.memo.data.Memo
import java.text.SimpleDateFormat
import java.util.*

class MemoAdapter(
    private val onItemClick: (Memo) -> Unit
) : ListAdapter<Memo, MemoAdapter.MemoViewHolder>(DIFF_CALLBACK) {

    private var onItemLongClick: ((Memo) -> Unit)? = null

    // 给外部提供设置长按监听的接口
    fun setOnItemLongClickListener(listener: (Memo) -> Unit) {
        onItemLongClick = listener
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Memo>() {
            override fun areItemsTheSame(oldItem: Memo, newItem: Memo): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Memo, newItem: Memo): Boolean {
                return oldItem == newItem
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_memo, parent, false)
        return MemoViewHolder(view)
    }

    override fun onBindViewHolder(holder: MemoViewHolder, position: Int) {
        val memo = getItem(position)
        holder.bind(memo)

        // 点击事件 - 编辑
        holder.itemView.setOnClickListener {
            onItemClick(memo)
        }

        // 长按事件 - 删除
        holder.itemView.setOnLongClickListener {
            onItemLongClick?.invoke(memo)
            true
        }
    }

    class MemoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvTitle: TextView = itemView.findViewById(R.id.tvTitle)
        private val tvContent: TextView = itemView.findViewById(R.id.tvContent)
        private val tvTime: TextView = itemView.findViewById(R.id.tvTime)

        fun bind(memo: Memo) {
            tvTitle.text = memo.title
            tvContent.text = memo.content

            val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
            tvTime.text = sdf.format(Date(memo.timestamp))
        }
    }
}
