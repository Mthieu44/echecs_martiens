package projet.echecmartien.modele

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class CoordonneeTest {

    private val c = Coordonnee(-10,666)

    @Test
    fun getX() {
        assertEquals(-10,c.getX())
    }

    @Test
    fun getY() {
        assertEquals(666,c.getY())
    }

    @Test
    fun testToString() {
        assertEquals("(-10,666)", c.toString())
    }
}