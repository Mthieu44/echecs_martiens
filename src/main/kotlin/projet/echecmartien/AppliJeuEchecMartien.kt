package projet.echecmartien

import javafx.application.Application
import javafx.scene.Scene

import javafx.stage.Stage
import projet.echecmartien.controleur.ControleurCheckboxIA
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur
import projet.echecmartien.vue.CasePlateau
import projet.echecmartien.vue.VueAccueil
import projet.echecmartien.vue.VuePlateau

class AppliJeuEchecMartien: Application() {


    override fun start(primaryStage: Stage) {/*
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

        root.center = plateau

        for (l in plateau.tableauCase){
            for (c in l){
                c.clic(ControleurClicCase(modele, c))
            }
        }*/
        val vue = VueAccueil()
        val modele = Jeu()

        vue.checkBox(ControleurCheckboxIA(vue, modele))

        val scene = Scene(vue,1090.0,550.0)
        primaryStage.title="Page d'accueil"
        primaryStage.scene=scene
        primaryStage.show()

    }
}

fun main(){

    Application.launch(AppliJeuEchecMartien::class.java)
}