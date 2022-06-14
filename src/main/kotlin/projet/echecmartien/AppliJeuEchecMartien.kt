package projet.echecmartien

import com.google.gson.Gson
import javafx.application.Application
import javafx.scene.Scene
import javafx.scene.layout.BorderPane
import javafx.scene.layout.Pane
import javafx.stage.FileChooser
import javafx.stage.Stage
import projet.echecmartien.controleur.ControleurBoutonSave
import projet.echecmartien.controleur.ControleurClicCase
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur
import projet.echecmartien.vue.VuePlateau
import java.io.File
import java.io.FileWriter


class AppliJeuEchecMartien: Application() {

    private val modele = Jeu()
    private val plateau = VuePlateau()
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

        root.center = plateau

        for (l in plateau.tableauCase){
            for (c in l){
                c.clic(ControleurClicCase(modele, c))
            }
        }
        plateau.sauvegarde(ControleurBoutonSave(modele,plateau))

        /*val vue = VueAccueil()

        val scene = Scene(vue,1280.0,720.0)œ
        primaryStage.title="Page d'accueil"
        primaryStage.scene=scene*/
        primaryStage.show()
    }
}

fun main(){

    Application.launch(AppliJeuEchecMartien::class.java)
}