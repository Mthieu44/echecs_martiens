package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.FileChooser
import javafx.stage.Stage
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.VuePlateau
import java.io.File

class ControleurBoutonSauvegarder(primary : Stage, vue: VuePlateau, modele : Jeu) : EventHandler<ActionEvent> {
    private val primary : Stage
    private val modele: Jeu
    private val vue: VuePlateau

    init {
        this.primary = primary
        this.modele = modele
        this.vue = vue
    }

    override fun handle(p0: ActionEvent?) {
        val fileChooser = FileChooser()
        fileChooser.initialFileName = "save.json"
        val extensionFilter = FileChooser.ExtensionFilter("Json file (*.json)", "*.json")
        fileChooser.extensionFilters.add(extensionFilter)
        val file = fileChooser.showSaveDialog(primary)
        modele.serialiser(file.absolutePath)
    }
}