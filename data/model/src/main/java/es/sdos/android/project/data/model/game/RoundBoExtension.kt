package es.sdos.android.project.data.model.game

fun List<RoundBo>.addShot(gameId: Long, shotScore: Int): List<RoundBo> {
    val result = this.toMutableList()
    if (result.isEmpty()) {
        result.add(RoundBo(null, gameId, 1, shotScore, null, null, null))
    } else {
        val lastRound = result.last()
        if (lastRound.isComplete()) {
            result.add(RoundBo(null, gameId, lastRound.roundNum + 1, shotScore, null, null, null))
        } else {
            result[result.size - 1] = if (lastRound.secondShot == null) {
                lastRound.copy(secondShot = shotScore)
            } else {
                lastRound.copy(thirdShot = shotScore)
            }
        }

        updateScores(result)
    }

    return result
}

private fun RoundBo.isStrike(): Boolean {
    return firstShot == 10
    TODO("DONE")
}

private fun RoundBo.isSpare(): Boolean {
    return firstShot != 10 && (firstShot + (secondShot ?: 0) == 10)
    TODO("DONE")
}

/**
 * Indica que la ronda esta finalizada, no quedan lanzamientos pendientes
 */
private fun RoundBo.isComplete(): Boolean {
    return firstShot == 10 || secondShot != null
    TODO("DONE")
}

private fun updateScores(result: MutableList<RoundBo>) {
    for (i in 0 until result.size) {
        val roundBo = result[i]
        if (!roundBo.isComplete()) {
            break
        }
        val previousScore = result.getOrNull(i - 1)?.score ?: 0

        val roundScore = roundBo.firstShot + (roundBo.secondShot ?: 0) + (roundBo.thirdShot ?: 0)
        val extraScore = if (roundBo.roundNum != 10 && roundBo.isStrike()) {
            getNextShotsScore(result, i + 1, 2)
        } else if (roundBo.roundNum != 10 && roundBo.isSpare()) {
            getNextShotsScore(result, i + 1, 1)
        } else {
            0
        }

        if (extraScore != null) {
            result[i] = roundBo.copy(score = previousScore + roundScore + extraScore)
        }
    }
}

/**
 * Obtiene la puntuación acumulada de los 'x' siguientes lanzamientos a partir de 'startIndex'
 */
private fun getNextShotsScore(roundList: List<RoundBo>, startIndex: Int, numberOfShots: Int): Int? {
    var shotsCount = 0
    var score = 0

    for (i in startIndex until roundList.size) {
        if (shotsCount >= numberOfShots) break

        val round = roundList[i]

        if (shotsCount < numberOfShots) {
            score += round.firstShot
            shotsCount++
        }

        if (shotsCount < numberOfShots && round.secondShot != null) {
            score += round.secondShot
            shotsCount++
        }

        if (shotsCount < numberOfShots && round.thirdShot != null) {
            score += round.thirdShot
            shotsCount++
        }
    }

    return if (shotsCount < numberOfShots) null else score
    TODO("DONE")
}