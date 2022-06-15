package projet.echecmartien.vue

import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.input.MouseEvent
import javafx.scene.layout.BorderPane
import javafx.scene.layout.VBox

class VueBouton : BorderPane() {
    val centre = VBox()
    val boutonSauvegarder = Button("Sauvegarder la partie")
    val boutonCharger = Button("Charger une partie")
    val boutonRecommencer = Button("Recommencer la partie")
    val boutonRetourAccueil = Button("Retour à la page d'accueil")
    val boutonAfficherRegles = Button("Afficher les règles")

    init {
        this.center = centre
        centre.children.addAll(boutonSauvegarder,boutonCharger,boutonRecommencer,boutonRetourAccueil,boutonAfficherRegles)

        centre.alignment = Pos.CENTER_LEFT
        boutonSauvegarder.prefWidth = 200.0
        boutonCharger.prefWidth = 200.0
        boutonRecommencer.prefWidth = 200.0
        boutonRetourAccueil.prefWidth = 200.0
        boutonAfficherRegles.prefWidth = 200.0

        centre.padding = Insets(25.0)
        centre.spacing = 10.0
    }

    fun fixeBoutonListener(bouton : Button, controleur : EventHandler<MouseEvent>){
        bouton.onMouseClicked = controleur
    }

}