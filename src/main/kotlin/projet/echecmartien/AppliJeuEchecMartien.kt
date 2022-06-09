package projet.echecmartien

import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.*

import javafx.stage.Stage
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur
import projet.echecmartien.vue.VuePlateau

class AppliJeuEchecMartien: Application() {

    private val vuePlateau = VuePlateau()
    override fun start(primaryStage: Stage) {
        val root = BorderPane()
        val tailleFenetreX = 1280.0
        val tailleFenetreY = 720.0
        val scene = Scene(root, tailleFenetreX, tailleFenetreY)
        primaryStage.scene = scene

        val partieDroite = Pane()
        val partieGauche = Pane()
        root.right = partieDroite
        root.left = partieGauche

        partieDroite.setPrefSize(3.0 * tailleFenetreX / 8.0, tailleFenetreY)
        partieGauche.setPrefSize(3.0 * tailleFenetreX / 8.0, tailleFenetreY)

        root.center = vuePlateau




        primaryStage.show()
    }
}

fun main(){
    val j1 = Joueur("a")
    val j2 = Joueur("b")
    val j = Jeu()
    j.initialiserPartie(j1, j2, 3)
    println(j)
    println(j.deplacementPossible(1, 5, 0, 4, j1))
    j.deplacer(1, 5, 0, 4)
    println(j)

    Application.launch(AppliJeuEchecMartien::class.java)
}