package es.sdos.android.project.data.local.games

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import es.sdos.android.project.data.local.games.dbo.GameDbo
import es.sdos.android.project.data.local.games.dbo.RoundDbo

@Dao
abstract class GamesDao {

    @Query("SELECT * FROM game_table WHERE id = :gameId")
    abstract suspend fun getGame(gameId: Long): GameDbo?

    @Query("SELECT * FROM game_table")
    abstract suspend fun getGames(): List<GameDbo>

    @Query("SELECT * FROM round_table WHERE id = :gameId")
    abstract suspend fun getRounds(gameId: Long): List<RoundDbo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun saveGame(gameDbo: GameDbo) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract suspend fun saveRound(roundDbo: RoundDbo) : Long

    @Query("DELETE FROM game_table WHERE id = :gameId")
    abstract suspend fun deleteGame(gameId: Long)

    @Query("DELETE FROM round_table WHERE id = :gameId")
    abstract suspend fun deleteRounds(gameId: Long)

    @Update
    abstract suspend fun updateGame(game: GameDbo)

}