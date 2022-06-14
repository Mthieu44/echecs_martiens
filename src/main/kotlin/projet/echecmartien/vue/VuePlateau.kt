package projet.echecmartien.vue

import javafx.event.EventHandler
import javafx.scene.input.MouseEvent
import javafx.scene.layout.GridPane
import javafx.scene.layout.Pane
import javafx.scene.layout.VBox
import projet.echecmartien.controleur.ControleurClicCase
import projet.echecmartien.modele.Jeu

class VuePlateau(
    private val tailleFenetreX: Double,
    private val tailleFenetreY: Double,
    private val bx: Double,
    private val by: Double
) : VBox() {
    val plateauHaut = GridPane()
    val plateauBas = GridPane()
    val tableauCase = Array<MutableList<CasePlateau>>(8) { i -> mutableListOf() }


    init {
        val nbr_buttons_x = 4
        val nbr_buttons_y = 8
        //val cy: Double = nbr_buttons_y * bx //taille Colonne y


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

        val borderSize = 1

        for (num_ligne in 0 until (nbr_buttons_y / 2)) { //pour chaque ligne...
            numLigne = numLignes[num_ligne]
            for (num_colonne in 0 until nbr_buttons_x) { //pour chaque bouton de la ligne
                lettreColonne = lettresColonnes[num_colonne]
                //val bouton = Button("$lettreColonne$numligne")
                //bouton.setPrefSize(bx,by)
                val casePlateau = CasePlateau(bx, by, borderSize, num_colonne, num_ligne)
                tableauCase[num_ligne].add(casePlateau)
                //plateauHaut.add(bouton,num_colonne,num_ligne)
                if (num_ligne + num_colonne < nbr_buttons_y / 4)
                    casePlateau.placerPion("grand")
                else if (num_ligne + num_colonne < nbr_buttons_y / 4 + 1)
                    casePlateau.placerPion("moyen")
                else if ((num_ligne < nbr_buttons_y / 4 + 1) and (num_colonne < nbr_buttons_y / 4 + 1))
                    casePlateau.placerPion(("petit"))
                plateauHaut.add(casePlateau, num_colonne, num_ligne)
            }
        }
        this.children.add(plateauHaut)

        val pane = Pane()
        //pane.setPrefSize(nbr_buttons_x * (bx + 2*borderSize), nbr_buttons_x * (bx + 2*borderSize) / 32.8)
        pane.maxWidth = nbr_buttons_x * (bx + 2 * borderSize)
        pane.minHeight = pane.maxWidth / 32.8 // 32.8 -> rapport entre longueur et hauteur de la séparation
        pane.style = "-fx-background-color: #000000;"
        this.children.add(pane)

        for (num_ligne in nbr_buttons_y / 2 until nbr_buttons_y) { //pour chaque ligne..
            numLigne = numLignes[num_ligne]
            for (num_colonne in 0 until nbr_buttons_x) { //pour chaque bouton de la ligne
                lettreColonne = lettresColonnes[num_colonne]
                //val bouton = Button("$lettreColonne$numligne")
                //bouton.setPrefSize(bx,by)
                val casePlateau = CasePlateau(bx, by, borderSize, num_colonne, num_ligne)
                tableauCase[num_ligne].add(casePlateau)
                //plateauHaut.add(bouton,num_colonne,num_ligne)
                if (num_ligne + num_colonne > 3 * nbr_buttons_y / 4 + 2)
                    casePlateau.placerPion("grand")
                else if (num_ligne + num_colonne > 3 * nbr_buttons_y / 4 + 1)
                    casePlateau.placerPion("moyen")
                else if ((num_ligne > 3 * nbr_buttons_y / 4 - 2) and (num_colonne > nbr_buttons_y / 4 - 2))
                    casePlateau.placerPion(("petit"))
                plateauBas.add(casePlateau, num_colonne, num_ligne)
            }
        }
        this.children.add(plateauBas)
    }
}