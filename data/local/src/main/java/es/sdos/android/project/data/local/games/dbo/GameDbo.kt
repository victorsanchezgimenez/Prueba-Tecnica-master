package es.sdos.android.project.data.local.games.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "game_table")
data class GameDbo(
    @PrimaryKey(autoGenerate = true) val id: Long? = 0,
    val date: Long,
    val totalScore: Int,
    val finished: Boolean
)