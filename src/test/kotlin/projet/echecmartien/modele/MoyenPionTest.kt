package projet.echecmartien.modele

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import projet.echecmartien.exeptions.DeplacementExeption

internal class MoyenPionTest {
    @Test
    fun getScore() {
        val p = MoyenPion()
        assertEquals(2, p.getScore())
    }

    @Test
    fun getDeplacementExeptionLongeur() {
        val p = MoyenPion()
        val d = Deplacement(Coordonnee(0, 0), Coordonnee(4, 4))
        org.junit.jupiter.api.assertThrows<DeplacementExeption> { p.getDeplacement(d) }
    }

    @Test
    fun getDeplacementQuiMarche() {
        val p = MoyenPion()
        val d = Deplacement(Coordonnee(0, 0), Coordonnee(2, 0))
        assertEquals(listOf(Coordonnee(1, 0), Coordonnee(2, 0)), p.getDeplacement(d))
    }

}