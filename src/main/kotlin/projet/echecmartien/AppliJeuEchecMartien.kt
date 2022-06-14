package projet.echecmartien

import javafx.application.Application
import javafx.geometry.Insets
import javafx.scene.Scene

import javafx.stage.Stage
import projet.echecmartien.controleur.ControleurBoutonValider
import projet.echecmartien.controleur.ControleurCheckboxIA
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur
import projet.echecmartien.vue.CasePlateau
import projet.echecmartien.vue.CompteurPoints
import projet.echecmartien.vue.VueAccueil
import projet.echecmartien.vue.VuePlateau

class AppliJeuEchecMartien: Application() {


    override fun start(primaryStage: Stage) {

        val vue = VueAccueil()
        val modele = Jeu()

        vue.checkBox(ControleurCheckboxIA(vue, modele))
        vue.valider(ControleurBoutonValider(primaryStage, vue, modele))

        val scene = Scene(vue,1090.0,550.0)
        primaryStage.title="Page d'accueil"
        primaryStage.scene=scene
        primaryStage.show()

    }
}

fun main() {

    Application.launch(AppliJeuEchecMartien::class.java)
}