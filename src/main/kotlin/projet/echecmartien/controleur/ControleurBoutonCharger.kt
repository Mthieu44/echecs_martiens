package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.FileChooser
import javafx.stage.Stage
import jdk.jfr.consumer.EventStream.openFile
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.VueJeu
import java.io.File
import java.util.*


class ControleurBoutonCharger(primary : Stage, vue : VueJeu, modele : Jeu) : EventHandler<ActionEvent> {
    val primary : Stage
    val vue : VueJeu
    val modele : Jeu

    init {
        this.primary = primary
        this.vue=vue
        this.modele=modele
    }

    override fun handle(p0: ActionEvent?) {
        val fileChooser = FileChooser()
        val file = fileChooser.showOpenDialog(primary)
        if (file != null) {
            openFile(file.toPath())
        }
    }

}