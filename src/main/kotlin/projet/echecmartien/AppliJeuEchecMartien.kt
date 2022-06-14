package projet.echecmartien

import javafx.application.Application
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.layout.*

import javafx.stage.Stage
import projet.echecmartien.controleur.ControleurClicCase
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur
import projet.echecmartien.vue.CasePlateau
import projet.echecmartien.vue.CompteurPoints
import projet.echecmartien.vue.VueAccueil
import projet.echecmartien.vue.VuePlateau

class AppliJeuEchecMartien : Application() {

    private val modele = Jeu()
    private val tailleFenetreX = 1280.0
    private val tailleFenetreY = 720.0

    private val bx = 80.0 //taille Bouton x
    private val by = 80.0 //taille Bouton y

    private val plateau = VuePlateau(tailleFenetreX, tailleFenetreY, bx, by)
    override fun start(primaryStage: Stage) {
        val root = BorderPane()
        val scene = Scene(root, tailleFenetreX, tailleFenetreY)
        primaryStage.scene = scene

        var partieGauche = BorderPane()
        partieGauche.padding = Insets(30.0,100.0,60.0,40.0)
        partieGauche.center = CompteurPoints("Joueur1", "Joueur2", tailleFenetreX, tailleFenetreY, bx, by)
        var partieDroite = BorderPane()
        root.right = partieDroite
        root.left = partieGauche

        partieDroite.setPrefSize(3.0 * tailleFenetreX / 8.0, tailleFenetreY)
        partieGauche.setPrefSize(3.0 * tailleFenetreX / 8.0, tailleFenetreY)

        root.center = plateau

        for (l in plateau.tableauCase) {
            for (c in l) {
                c.clic(ControleurClicCase(modele, c))
            }
        }
        /*val vue = VueAccueil()

        val scene = Scene(vue,1280.0,720.0)
        primaryStage.title="Page d'accueil"
        primaryStage.scene=scene*/
        primaryStage.show()

        fun getTailleFenetreX() = tailleFenetreX
        fun getTailleFenetreY() = tailleFenetreX
    }
}

fun main() {

    Application.launch(AppliJeuEchecMartien::class.java)
}