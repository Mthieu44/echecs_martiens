package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.FileChooser
import javafx.stage.Stage
import projet.echecmartien.modele.Jeu
import projet.echecmartien.modele.Joueur
import projet.echecmartien.vue.VueJeu
import java.io.File
import java.io.FileReader


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
        val fileReader = FileReader(file)
        var charCode: Int
        var data: MutableList<String> = mutableListOf()
        var string = ""
        while (fileReader.read().also { charCode = it } != -1) {
            if (charCode.toChar().toString() == ";") {
                data.add(string)
                string = ""
            }
            else {
                string += charCode.toChar().toString()
            }
        }
        println(data)
        var maValeur: String = apresVerif(data[0])


        val nombreCoupsSansPrise = maValeur.toInt()
        val nombreCoupsSansPriseMax = data[1].toInt()
        var stringJoueur1 = ""
        var stringJoueur2 = ""
        var joueur1EnCours = true
        for (caractere in data[2]){
            if (joueur1EnCours){
                if (caractere != ',')
                    if (caractere != '[')
                        stringJoueur1 += caractere
                else
                    joueur1EnCours = false
            }
        }

        var nouveauJeu = Jeu(nombreCoupsSansPrise,nombreCoupsSansPriseMax,null,null)
        fileReader.close()
    }

    fun apresVerif(valeur: String): String{
        var sortie = ""
        for (caractere in valeur)
            if (caractere in "0123456789")
                sortie += caractere
        return sortie
    }

    fun verif(valeur: String): Boolean{
        for (caractere in valeur)
            if (caractere !in "0123456789")
                return false
        return true
    }
}