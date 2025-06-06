package com.example.memo.data

import androidx.room.*

@Dao
interface MemoDao {
    @Query("SELECT * FROM memos ORDER BY timestamp DESC")
    suspend fun getAllMemos(): List<Memo>

    @Query("SELECT * FROM memos WHERE id = :id LIMIT 1")
    suspend fun getMemoById(id: Long): Memo?
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMemo(memo: Memo): Long


    @Delete
    suspend fun deleteMemo(memo: Memo)


    @Update
    suspend fun updateMemo(memo: Memo)


}
