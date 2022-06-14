package projet.echecmartien.controleur

import javafx.event.EventHandler
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import projet.echecmartien.modele.Coordonnee
import projet.echecmartien.modele.Jeu
import projet.echecmartien.vue.CasePlateau
import projet.echecmartien.vue.VuePlateau

class ControleurClicCase(modele : Jeu, vue : VuePlateau) : EventHandler<MouseEvent> {
    val modele : Jeu
    val vue : VuePlateau

    init {
        this.modele = modele
        this.vue = vue
    }

    override fun handle(event: MouseEvent) {
        val x = convertX(event.x, vue)
        val y = convertY(event.y, vue)

        if (modele.getCoordOrigineDeplacement() == null){
            modele.setCoordOrigineDeplacement(Coordonnee(x, y))
            return
        }
        modele.setCoordDestinationDeplacement(Coordonnee(x, y))
        if (modele.getCoordOrigineDeplacement() != modele.getCoordDestinationDeplacement()){
            val xO = modele.getCoordOrigineDeplacement()!!.getX()
            val yO = modele.getCoordOrigineDeplacement()!!.getY()
            val xD = modele.getCoordDestinationDeplacement()!!.getX()
            val yD = modele.getCoordDestinationDeplacement()!!.getY()
            modele.deplacer(xO, yO, xD, yD)
            vue.tableauCase[y][x].placerPion("petit")
            vue.tableauCase[yO][xO].retirerPion()
        }
        modele.setCoordDestinationDeplacement(null)
        modele.setCoordOrigineDeplacement(null)
    }
}

fun convertX(x : Double, vue : VuePlateau) : Int{
    return (x/vue.bx).toInt()
}

fun convertY(y : Double, vue : VuePlateau) : Int{
    if (y > 4*vue.by)
        return((y-vue.pane.minHeight)/vue.by).toInt()
    return (y/vue.by).toInt()
}