package projet.echecmartien.vue

import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import javafx.stage.DirectoryChooser

class VuePlateau : VBox() {
    val plateauHaut = GridPane()
    val plateauBas = GridPane()
    val tableauCase = Array<MutableList<VueCasePlateau>>(8) { i -> mutableListOf() }

    val bx = 80.0 //taille Bouton x
    val by = 80.0 //taille Bouton y
    val pane = Pane()

    init {
        val nbr_buttons_x = 4
        val nbr_buttons_y = 8


        var lettresColonnes: Array<String> = arrayOf()
        var chr = 'A'
        for (lettre in 0 until nbr_buttons_x) {
            lettresColonnes = lettresColonnes.plus("${chr}")
            chr += 1
        }
        var numLignes: Array<String> = arrayOf()
        for (num in 1 until nbr_buttons_y + 1)
            numLignes = numLignes.plus("$num")

        var lettreColonne: String
        var numLigne: String

        for (num_ligne in 0 until (nbr_buttons_y / 2)) { //pour chaque ligne...
            numLigne = numLignes[num_ligne]
            for (num_colonne in 0 until nbr_buttons_x) { //pour chaque bouton de la ligne
                lettreColonne = lettresColonnes[num_colonne]
                val casePlateau = VueCasePlateau(bx, by)
                tableauCase[num_ligne].add(casePlateau)
                if (num_ligne + num_colonne < nbr_buttons_y / 4)
                    casePlateau.placerPion("3")
                else if (num_ligne + num_colonne < nbr_buttons_y / 4 + 1)
                    casePlateau.placerPion("2")
                else if ((num_ligne < nbr_buttons_y / 4 + 1) and (num_colonne < nbr_buttons_y / 4 + 1))
                    casePlateau.placerPion(("1"))
                plateauHaut.add(casePlateau, num_colonne, num_ligne)
            }
        }
        this.children.add(plateauHaut)

        pane.maxWidth = nbr_buttons_x * (bx + 2)
        pane.minHeight = pane.maxWidth / 32.8
        pane.style = "-fx-background-color: #000000;"
        this.children.add(pane)

        for (num_ligne in nbr_buttons_y / 2 until nbr_buttons_y) { //pour chaque ligne..
            numLigne = numLignes[num_ligne]
            for (num_colonne in 0 until nbr_buttons_x) { //pour chaque bouton de la ligne
                lettreColonne = lettresColonnes[num_colonne]
                val casePlateau = VueCasePlateau(bx, by)
                tableauCase[num_ligne].add(casePlateau)
                if (num_ligne + num_colonne > 3 * nbr_buttons_y / 4 + 2)
                    casePlateau.placerPion("3")
                else if (num_ligne + num_colonne > 3 * nbr_buttons_y / 4 + 1)
                    casePlateau.placerPion("2")
                else if ((num_ligne > 3 * nbr_buttons_y / 4 - 2) and (num_colonne > nbr_buttons_y / 4 - 2))
                    casePlateau.placerPion(("1"))
                plateauBas.add(casePlateau, num_colonne, num_ligne)
            }
        }
        this.children.add(plateauBas)
    }

    fun clic(controleur: EventHandler<MouseEvent>) {
        this.onMouseClicked = controleur
    }
}