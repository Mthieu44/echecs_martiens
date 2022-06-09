package projet.echecmartien.vue

import javafx.scene.control.Button
import javafx.scene.layout.Pane
import javafx.scene.paint.Paint
import javafx.scene.shape.Circle
import kotlin.math.absoluteValue

class CasePlateau(
    private val tailleBoutonX: Double,
    private val tailleBoutonY: Double,
    lettreColonne: String,
    numLigne: String,
    private val borderSize: Int
) : Pane() {
    val bouton = Button("$lettreColonne$numLigne")
    val isGrandPion: Boolean = false
    val isMoyenPion: Boolean = false
    val isPetitPion: Boolean = false

    val minTailleBouton: Double

    val tailleGrandPion: Double
    val couleurGrandPion = "#BDA8A8"
    val tailleMoyenPion: Double
    val couleurMoyenPion = "#9C8165"
    val taillePetitPion: Double
    val couleurPetitPion = "#6E4404"


    val cercle = Circle(tailleBoutonX / 2 + borderSize, tailleBoutonY / 2 + borderSize, 0.0)

    init {
        if (tailleBoutonX <= tailleBoutonY)
            minTailleBouton = tailleBoutonX
        else
            minTailleBouton = tailleBoutonY

        tailleGrandPion = (18.0 / 20.0)/2.0 * minTailleBouton
        tailleMoyenPion = (6.0 / 8.0)/2.0 * minTailleBouton
        taillePetitPion = (1.0 / 2.0)/2.0 * minTailleBouton

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