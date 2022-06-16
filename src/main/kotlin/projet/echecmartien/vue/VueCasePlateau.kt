package projet.echecmartien.vue

import javafx.event.EventHandler
import javafx.scene.Cursor
import javafx.scene.input.DragEvent
import javafx.scene.input.MouseEvent
import javafx.scene.layout.BackgroundPosition
import javafx.scene.layout.Pane
import javafx.scene.paint.Paint
import javafx.scene.shape.Circle
import kotlin.math.absoluteValue

class VueCasePlateau(
    tailleBoutonX: Double,
    tailleBoutonY: Double,
) : Pane() {
    private val minTailleBouton: Double

    private val tailleGrandPion: Double
    private val couleurGrandPion = "#BDA8A8"
    private val tailleMoyenPion: Double
    private val couleurMoyenPion = "#9C8165"
    private val taillePetitPion: Double
    private val couleurPetitPion = "#6E4404"

    private val cercle = Circle(tailleBoutonX / 2 + 1, tailleBoutonY / 2 + 1, 0.0)

    init {
        this.cursor = Cursor.cursor("HAND")

        this.setPrefSize(tailleBoutonX, tailleBoutonY)

        minTailleBouton = if (tailleBoutonX <= tailleBoutonY)
            tailleBoutonX
        else
            tailleBoutonY

        tailleGrandPion = (18.0 / 20.0) / 2.0 * minTailleBouton
        tailleMoyenPion = (8.0 / 11.0) / 2.0 * minTailleBouton
        taillePetitPion = (1.0 / 2.0) / 2.0 * minTailleBouton


        this.children.add(cercle)
        cercle.fill = Paint.valueOf("#FFD000")
        this.style = "-fx-border-color: #000000; -fx-border-width: 1px;"
    }

    fun ombrePion() {
        cercle.fill = Paint.valueOf("#DDDDDD")
    }

    fun retirerPion() {
        cercle.radius = 0.0
    }

    fun placerPion(pion: String) {
        if (pion == "3") {
            cercle.radius = tailleGrandPion
            cercle.fill = Paint.valueOf(couleurGrandPion)
        } else if (pion == "2") {
            cercle.radius = tailleMoyenPion
            cercle.fill = Paint.valueOf(couleurMoyenPion)
        } else if (pion == "1") {
            cercle.radius = taillePetitPion
            cercle.fill = Paint.valueOf(couleurPetitPion)
        } else
            throw IllegalArgumentException()
    }

    fun changeCouleur(couleur : String){
        this.style = "-fx-background-color: $couleur; -fx-border-color: #000000; -fx-border-width: 1px;"
    }

    fun hasShadow() = (cercle.fill == Paint.valueOf("#DDDDDD"))
}