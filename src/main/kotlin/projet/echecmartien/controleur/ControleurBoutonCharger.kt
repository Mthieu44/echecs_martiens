package projet.echecmartien.controleur

import javafx.event.ActionEvent
import javafx.event.EventHandler
import javafx.stage.FileChooser
import javafx.stage.Stage
import projet.echecmartien.modele.*
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


        val nombreCoupsSansPrise = data[0][1].toString().toInt()
        val nombreCoupsSansPriseMax = data[1].toInt()
        val joueursStr = data[2]
        var stringJoueur1 = joueursStr.substring(1, joueursStr.indexOf(','))
        var stringJoueur2 = joueursStr.substring(joueursStr.indexOf(',')+2, joueursStr.length-1)
        val j1 = Joueur(stringJoueur1)
        val j2 = Joueur(stringJoueur2)
        val joueurs = arrayOf(j1,j2)
        val pionJ1Str = data[3]
        val pionJ2Str = data[4]
        for (i in pionJ1Str.indices){
            if (i == 1){
                j1.ajouterPionCaptures(PetitPion())
            }
            if (i == 2){
                j1.ajouterPionCaptures(MoyenPion())
            }
            if (i == 3){
                j1.ajouterPionCaptures(GrandPion())
            }
        }
        for (i in pionJ2Str.indices){
            if (i == 1){
                j2.ajouterPionCaptures(PetitPion())
            }
            if (i == 2){
                j2.ajouterPionCaptures(MoyenPion())
            }
            if (i == 3){
                j2.ajouterPionCaptures(GrandPion())
            }
        }
        val joueurCourant = Joueur(data[5])
        var nouveauJeu = Jeu(nombreCoupsSansPrise,nombreCoupsSansPriseMax,null,null,joueurs,joueurCourant)
        fileReader.close()
    }
}