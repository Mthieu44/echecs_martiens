package projet.echecmartien.vue

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.control.Button
import javafx.scene.layout.*
import javafx.scene.paint.Paint
import javafx.scene.shape.StrokeLineCap
import javafx.scene.shape.StrokeLineJoin
import javafx.scene.shape.StrokeType

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

        boutonSauvegarder.style = "-fx-background-radius: 15px; -fx-padding: 1em; -fx-background-color: #bbc2fa"
        boutonCharger.style = "-fx-background-radius: 15px; -fx-padding: 1em; -fx-background-color: #aee1f5"
        boutonRecommencer.style = "-fx-background-radius: 15px; -fx-padding: 1em; -fx-background-color: #f79e9e"
        boutonRetourAccueil.style = "-fx-background-radius: 15px; -fx-padding: 1em; -fx-background-color: #f5e2ae"
        boutonAfficherRegles.style = "-fx-background-radius: 15px; -fx-padding: 1em; -fx-background-color: #aef5ae"
    }

    fun fixeBoutonListener(bouton : Button, controleur : EventHandler<ActionEvent>){
        bouton.onAction = controleur
    }

}