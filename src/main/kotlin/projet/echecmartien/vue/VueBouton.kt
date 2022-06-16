package projet.echecmartien.vue

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.geometry.Insets
import javafx.geometry.Pos
import javafx.scene.Cursor
import javafx.scene.control.Button
import javafx.scene.layout.*

class VueBouton : BorderPane() {
    val centre = VBox()
    val boutonSauvegarder = Button("Sauvegarder la partie")
    val boutonCharger = Button("Charger une partie")
    val boutonRecommencer = Button("Recommencer la partie")
    val boutonRetourAccueil = Button("Retour à la page d'accueil")
    val boutonAfficherRegles = Button("Afficher les règles")

    init {
        this.center = centre
        centre.children.addAll(boutonSauvegarder, boutonCharger, boutonRecommencer, boutonRetourAccueil, boutonAfficherRegles)

        val prefSize = 225.0
        centre.alignment = Pos.CENTER
        boutonSauvegarder.prefWidth = prefSize
        boutonCharger.prefWidth = prefSize
        boutonRecommencer.prefWidth = prefSize
        boutonRetourAccueil.prefWidth = prefSize
        boutonAfficherRegles.prefWidth = prefSize

        centre.padding = Insets(25.0)
        centre.spacing = 10.0


        val valBackgroundRadius = 25
        val valBorderRadius: Int = valBackgroundRadius
        val valPadding = 1
        val valBackgroundColor = "#f7eee9"
        val valHoverBackgroundColor = "#f2e7e1"
        val valBorderWidth = 5

        val valBorderColorSauvegarder = "#bbc2fa"
        val valBorderColorCharger = "#aee1f5"
        val valBorderColorRecommender = "#f79e9e"
        val valBorderColorAccueil = "#f5e2ae"
        val valBorderColorAfficherRegles = "#aef5ae"


        val backgroundRadius = "-fx-background-radius: ${valBackgroundRadius}px;"
        val borderRadius = "-fx-border-radius: ${valBorderRadius}px;"
        val padding = "-fx-padding: ${valPadding}em;"
        val backgroundColor = "-fx-background-color: ${valBackgroundColor};"
        val hoverBackgroundColor = "-fx-background-color: ${valHoverBackgroundColor};"
        val borderWidth = "-fx-border-width: ${valBorderWidth}px;"

        val borderColorSauvegarder = "-fx-border-color: ${valBorderColorSauvegarder};"
        val borderColorCharger = "-fx-border-color: ${valBorderColorCharger};"
        val borderColorRecommencer = "-fx-border-color: ${valBorderColorRecommender};"
        val borderColorAccueil = "-fx-border-color: ${valBorderColorAccueil};"
        val borderColorAfficherRegles = "-fx-border-color: ${valBorderColorAfficherRegles};"


        boutonSauvegarder.style = "$backgroundRadius $borderRadius $padding $backgroundColor $borderColorSauvegarder $borderWidth"
        boutonSauvegarder.setOnMouseEntered {
            boutonSauvegarder.cursor = Cursor.cursor("HAND");
            boutonSauvegarder.style = "$backgroundRadius $borderRadius $padding $hoverBackgroundColor $borderColorSauvegarder $borderWidth"
        }
        boutonSauvegarder.setOnMouseExited { boutonSauvegarder.style = "$backgroundRadius $borderRadius $padding $backgroundColor $borderColorSauvegarder $borderWidth" }

        boutonCharger.style = "$backgroundRadius $borderRadius $padding $backgroundColor $borderColorCharger $borderWidth"
        boutonCharger.setOnMouseEntered {
            boutonCharger.cursor = Cursor.cursor("HAND");
            boutonCharger.style = "$backgroundRadius $borderRadius $padding $hoverBackgroundColor $borderColorCharger $borderWidth"
        }
        boutonCharger.setOnMouseExited { boutonCharger.style = "$backgroundRadius $borderRadius $padding $backgroundColor $borderColorCharger $borderWidth" }

        boutonRecommencer.style = "$backgroundRadius $borderRadius $padding $backgroundColor $borderColorRecommencer $borderWidth"
        boutonRecommencer.setOnMouseEntered {
            boutonRecommencer.cursor = Cursor.cursor("HAND");
            boutonRecommencer.style = "$backgroundRadius $borderRadius $padding $hoverBackgroundColor $borderColorRecommencer $borderWidth"
        }
        boutonRecommencer.setOnMouseExited { boutonRecommencer.style = "$backgroundRadius $borderRadius $padding $backgroundColor $borderColorRecommencer $borderWidth" }

        boutonRetourAccueil.style = "$backgroundRadius $borderRadius $padding $backgroundColor $borderColorAccueil $borderWidth"
        boutonRetourAccueil.setOnMouseEntered {
            boutonRetourAccueil.cursor = Cursor.cursor("HAND");
            boutonRetourAccueil.style = "$backgroundRadius $borderRadius $padding $hoverBackgroundColor $borderColorAccueil $borderWidth"
        }
        boutonRetourAccueil.setOnMouseExited { boutonRetourAccueil.style = "$backgroundRadius $borderRadius $padding $backgroundColor $borderColorAccueil $borderWidth" }

        boutonAfficherRegles.style = "$backgroundRadius $borderRadius $padding $backgroundColor $borderColorAfficherRegles $borderWidth"
        boutonAfficherRegles.setOnMouseEntered {
            boutonAfficherRegles.cursor = Cursor.cursor("HAND");
            boutonAfficherRegles.style = "$backgroundRadius $borderRadius $padding $hoverBackgroundColor $borderColorAfficherRegles $borderWidth"
        }
        boutonAfficherRegles.setOnMouseExited { boutonAfficherRegles.style = "$backgroundRadius $borderRadius $padding $backgroundColor $borderColorAfficherRegles $borderWidth" }
    }

    fun fixeBoutonListener(bouton: Button, controleur: EventHandler<ActionEvent>) {
        bouton.onAction = controleur
    }

}