package projet.echecmartien

import com.google.gson.Gson
import javafx.application.Application
import javafx.geometry.Insets
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane
import javafx.stage.FileChooser
import javafx.stage.Stage
import projet.echecmartien.controleur.ControleurBoutonSave
import projet.echecmartien.controleur.ControleurClicCase
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur
import projet.echecmartien.vue.CasePlateau
import projet.echecmartien.vue.CompteurPoints
import projet.echecmartien.vue.VueAccueil
import projet.echecmartien.vue.VuePlateau
import java.io.File
import java.io.FileWriter


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