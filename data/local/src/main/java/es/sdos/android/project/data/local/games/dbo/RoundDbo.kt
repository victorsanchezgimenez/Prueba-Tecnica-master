package es.sdos.android.project.data.local.games.dbo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "round_table")
data class RoundDbo(
    @PrimaryKey(autoGenerate = true) val id: Long? = 0,
    val gameId: Long,
    val roundNum: Int,
    val firstShot: Int,
    val secondShot: Int?,
    val thirdShot: Int?,
    val score: Int?
)