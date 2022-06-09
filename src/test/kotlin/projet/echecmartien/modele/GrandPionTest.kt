package projet.echecmartien.modele

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import projet.echecmartien.exeptions.DeplacementExeption

internal class GrandPionTest {

    @Test
    fun getScore() {
        val p = GrandPion()
        assertEquals(3, p.getScore())
    }

    @Test
    fun getDeplacementExeptionDeplacement() {
        val p = GrandPion()
        val d = Deplacement(Coordonnee(0, 0), Coordonnee(4, 2))
        org.junit.jupiter.api.assertThrows<DeplacementExeption> { p.getDeplacement(d) }
    }

    @Test
    fun getDeplacementQuiMarcheHorizontal() {
        val p = GrandPion()
        val d = Deplacement(Coordonnee(0, 0), Coordonnee(2, 0))
        assertEquals(listOf(Coordonnee(1, 0), Coordonnee(2, 0)), p.getDeplacement(d))
    }

    @Test
    fun getDeplacementQuiMarcheVertical() {
        val p = GrandPion()
        val d = Deplacement(Coordonnee(0, 2), Coordonnee(0, 0))
        assertEquals(listOf(Coordonnee(0, 1), Coordonnee(0, 0)), p.getDeplacement(d))
    }

    @Test
    fun getDeplacementQuiMarcheDiagonal() {
        val p = GrandPion()
        val d = Deplacement(Coordonnee(0, 2), Coordonnee(2, 0))
        assertEquals(listOf(Coordonnee(1, 1), Coordonnee(2, 0)), p.getDeplacement(d))
    }

}