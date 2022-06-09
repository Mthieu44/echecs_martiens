package projet.echecmartien.vue

import javafx.scene.control.Button
import javafx.scene.layout.Pane
import javafx.scene.paint.Paint
import javafx.scene.shape.Circle
import kotlin.math.absoluteValue

class CasePlateau(
    tailleBoutonX: Double,
    tailleBoutonY: Double,
    lettreColonne: String,
    numLigne: String,
    borderSize: Int
) : Pane() {
    private val bouton = Button("$lettreColonne$numLigne")
    private val isGrandPion: Boolean = false
    private val isMoyenPion: Boolean = false
    private val isPetitPion: Boolean = false

    private val minTailleBouton: Double

    private val tailleGrandPion: Double
    private val couleurGrandPion = "#BDA8A8"
    private val tailleMoyenPion: Double
    private val couleurMoyenPion = "#9C8165"
    private val taillePetitPion: Double
    private val couleurPetitPion = "#6E4404"


    private val cercle = Circle(tailleBoutonX / 2 + borderSize, tailleBoutonY / 2 + borderSize, 0.0)

    init {
        minTailleBouton = if (tailleBoutonX <= tailleBoutonY)
            tailleBoutonX
        else
            tailleBoutonY

        tailleGrandPion = (18.0 / 20.0) / 2.0 * minTailleBouton
        tailleMoyenPion = (6.0 / 8.0) / 2.0 * minTailleBouton
        taillePetitPion = (1.0 / 2.0) / 2.0 * minTailleBouton

        println(minTailleBouton)
        println(tailleGrandPion)

        bouton.isVisible = false
        bouton.setPrefSize(tailleBoutonX, tailleBoutonY)
        this.children.add(cercle)
        this.children.add(bouton)
        cercle.fill = Paint.valueOf("#FFD000")
        this.style = "-fx-border-color: #000000; -fx-border-width: ${borderSize}px;"
    }

    fun retirerPion() {
        cercle.radius = 0.0
    }

    fun placerPion(pion: String) {
        if (cercle.radius.absoluteValue != 0.0)
            throw IllegalStateException()
        else if (pion == "grand") {
            cercle.radius = tailleGrandPion
            cercle.fill = Paint.valueOf(couleurGrandPion)
        } else if (pion == "moyen") {
            cercle.radius = tailleMoyenPion
            cercle.fill = Paint.valueOf(couleurMoyenPion)
        } else if (pion == "petit") {
            cercle.radius = taillePetitPion
            cercle.fill = Paint.valueOf(couleurPetitPion)
        } else
            throw IllegalArgumentException()
    }

    fun accesBouton(): Button{
        return bouton
    }
}