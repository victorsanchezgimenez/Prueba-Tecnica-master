package es.sdos.android.project.data.local.games

import es.sdos.android.project.data.local.games.dbo.GameDbo
import es.sdos.android.project.data.local.games.dbo.RoundDbo
import es.sdos.android.project.data.model.game.GameBo
import es.sdos.android.project.data.model.game.RoundBo
import java.util.Date

//TODO (DONE)
fun GameDbo.toBo(): GameBo {
    return GameBo(
        id = this.id,
        date = Date(),
        rounds = listOf(),
        totalScore = 0,
        finished = false
    )
}

fun GameBo.toDbo(): GameDbo {
    return GameDbo(
        id = this.id,
        date = this.date.time,
        totalScore = this.totalScore,
        finished = this.finished
    )
}

fun RoundDbo.toBo(): RoundBo {
    return RoundBo(
        id = this.id,
        gameId = 0,
        roundNum = 0,
        firstShot = 0,
        secondShot = null,
        thirdShot = null,
        score = null
    )
}

fun RoundBo.toDbo(): RoundDbo {
    return RoundDbo(
        id = this.id,
        gameId = this.gameId,
        roundNum = this.roundNum,
        firstShot = this.firstShot,
        secondShot = this.secondShot,
        thirdShot = this.thirdShot,
        score = this.score
    )
}
