package com.example.worldcupinputapp

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import api.LineupAPI
import api.PlayerAPI
import com.example.worldcupapp.Match
import com.example.worldcupapp.Player
import com.example.worldcupinputapp.databinding.ActivityLineupBinding
import com.google.gson.Gson
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList


class LineupActivity : AppCompatActivity() {
    lateinit var binding: ActivityLineupBinding
    private lateinit var match: Match
    private var isHomeLineup: Boolean = false
    private lateinit var players: MutableList<Player>
    private var goalkeeper: Player? = null

    private var defenders: ArrayList<Player?> = ArrayList()
    lateinit var selectedDefenders: BooleanArray

    private var midfielders: ArrayList<Player?> = ArrayList()
    lateinit var selectedMidfielders: BooleanArray
    private var forwards: ArrayList<Player?> = ArrayList()
    lateinit var selectedForwards: BooleanArray

    private var defenderNames: MutableList<String> = mutableListOf()
    private var midfielderNames: MutableList<String> = mutableListOf()
    private var forwardNames: MutableList<String> = mutableListOf()
    private var goalkeeperNames: MutableList<String> = mutableListOf()
    //vsi ostali so pol substitutioni
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLineupBinding.inflate(layoutInflater)

        val extras = intent.extras
        var matchObject = extras?.get("match")
        match = Gson().fromJson(matchObject.toString(), Match::class.java)
        var homeLineup = extras?.get("homeOrAway")
        isHomeLineup = homeLineup == "home"

        if(isHomeLineup){
            lifecycleScope.launch{
                players = PlayerAPI().findByTeam(match.homeTeam._id)
                binding.lineupTypeEditText.visibility = View.VISIBLE
                binding.lineupTypeEditText.setOnClickListener {
                    binding.lineupTypeEditText.text.clear()
                }
                println("players: $players")
                for (p in players){
                    when (p.position){
                        "goalkeeper" -> goalkeeperNames.add(p.firstName + " " + p.lastName)
                        "defender" -> defenderNames.add(p.firstName + " " + p.lastName)
                        "midfielder" -> midfielderNames.add(p.firstName + " " + p.lastName)
                        "forward" -> forwardNames.add(p.firstName + " " + p.lastName)
                    }
                }
                selectedDefenders = BooleanArray(defenderNames.size)
                selectedMidfielders = BooleanArray(midfielderNames.size)
                selectedForwards = BooleanArray(forwardNames.size)

                binding.progressBar.visibility = View.GONE
                binding.loadingPlayersText.visibility = View.GONE
                binding.addButton.visibility = View.VISIBLE

                val selectGoalkeeperTextView = binding.selectGoalkeeperText
                selectGoalkeeperTextView.visibility = View.VISIBLE
                selectGoalkeeperTextView.setOnClickListener {
                    val builder = AlertDialog.Builder(this@LineupActivity)
                    builder.setTitle("Select goalkeeper")
                    builder.setSingleChoiceItems(goalkeeperNames.toTypedArray(), -1) { dialog, which ->
                        goalkeeper = players[which]
                        selectGoalkeeperTextView.text = goalkeeperNames[which]
                        dialog.dismiss()
                    }
                    builder.setPositiveButton("OK") { dialog, which ->
                        dialog.dismiss()
                    }
                    builder.setNegativeButton("Cancel") { dialog, which -> dialog.dismiss() }
                    builder.show()
                }

                val selectDefendersTextView = binding.selectDefendersTextView
                selectDefendersTextView.visibility = View.VISIBLE
                val defendersArray = defenderNames.toTypedArray()
                selectDefendersTextView.setOnClickListener(View.OnClickListener{
                    println(defenders)
                    println("clicked")
                    val builder = AlertDialog.Builder(this@LineupActivity)
                    builder.setTitle("Select defenders")
                    builder.setMultiChoiceItems(defendersArray, selectedDefenders) { dialog, which, isChecked ->
                        if (isChecked) {
                            defenders.add(players[which])
                        } else {
                            defenders.remove(players[which])
                        }
                    }
                    builder.setPositiveButton("OK") { dialog, which ->
                        val stringBuilder = StringBuilder()
                        for (i in selectedDefenders.indices) {
                            val checked = selectedDefenders[i]
                            if (checked) {
                                stringBuilder.append(defendersArray[i]).append("\n")
                            }
                        }
                        selectDefendersTextView.text = stringBuilder.toString()
                    }
                    builder.setNeutralButton("Clear all") { dialog, which ->
                        for (i in selectedDefenders.indices) {
                            selectedDefenders[i] = false
                            defenders.clear()
                        }
                        selectDefendersTextView.text = "Select Defenders"
                    }
                    builder.show()
                })

                val selectMidfieldersTextView = binding.selectMidfieldersTextView
                selectMidfieldersTextView.visibility = View.VISIBLE
                val midfieldersArray = midfielderNames.toTypedArray()
                selectMidfieldersTextView.setOnClickListener(View.OnClickListener{
                    println(midfielders)
                    println("clicked")
                    val builder = AlertDialog.Builder(this@LineupActivity)
                    builder.setTitle("Select midfielders")
                    builder.setMultiChoiceItems(midfieldersArray, selectedMidfielders) { dialog, which, isChecked ->
                        if (isChecked) {
                            midfielders.add(players[which])
                        } else {
                            midfielders.remove(players[which])
                        }
                    }
                    builder.setPositiveButton("OK") { dialog, which ->
                        val stringBuilder = StringBuilder()
                        for (i in selectedMidfielders.indices) {
                            val checked = selectedMidfielders[i]
                            if (checked) {
                                stringBuilder.append(midfieldersArray[i]).append("\n")
                            }
                        }
                        selectMidfieldersTextView.text = stringBuilder.toString()
                    }
                    builder.setNeutralButton("Clear all") { dialog, which ->
                        for (i in selectedMidfielders.indices) {
                            selectedMidfielders[i] = false
                            midfielders.clear()
                        }
                        selectMidfieldersTextView.text = "Select Midfielders"
                    }
                    builder.show()
                })

                val selectForwardsTextView = binding.selectForwardsTextView
                selectForwardsTextView.visibility = View.VISIBLE
                val forwardsArray = forwardNames.toTypedArray()
                selectForwardsTextView.setOnClickListener(View.OnClickListener{
                    println(forwards)
                    println("clicked")
                    val builder = AlertDialog.Builder(this@LineupActivity)
                    builder.setTitle("Select forwards")
                    builder.setMultiChoiceItems(forwardsArray, selectedForwards) { dialog, which, isChecked ->
                        if (isChecked) {
                            forwards.add(players[which])
                        } else {
                            forwards.remove(players[which])
                        }
                    }
                    builder.setPositiveButton("OK") { dialog, which ->
                        val stringBuilder = StringBuilder()
                        for (i in selectedForwards.indices) {
                            val checked = selectedForwards[i]
                            if (checked) {
                                stringBuilder.append(forwardsArray[i]).append("\n")
                            }
                        }
                        selectForwardsTextView.text = stringBuilder.toString()
                    }
                    builder.setNeutralButton("Clear all") { dialog, which ->
                        for (i in selectedForwards.indices) {
                            selectedForwards[i] = false
                            forwards.clear()
                        }
                        selectForwardsTextView.text = "Select Forwards"
                    }
                    builder.show()
                })
            }
        }
        else {
            lifecycleScope.launch{
                binding.lineupTypeEditText.visibility = View.VISIBLE
                binding.lineupTypeEditText.setOnClickListener {
                    binding.lineupTypeEditText.text.clear()
                }
                println("players: $players")
                for (p in players){
                    when (p.position){
                        "goalkeeper" -> goalkeeperNames.add(p.firstName + " " + p.lastName)
                        "defender" -> defenderNames.add(p.firstName + " " + p.lastName)
                        "midfielder" -> midfielderNames.add(p.firstName + " " + p.lastName)
                        "forward" -> forwardNames.add(p.firstName + " " + p.lastName)
                    }
                }
                selectedDefenders = BooleanArray(defenderNames.size)
                selectedMidfielders = BooleanArray(midfielderNames.size)
                selectedForwards = BooleanArray(forwardNames.size)

                binding.progressBar.visibility = View.GONE
                binding.loadingPlayersText.visibility = View.GONE
                binding.addButton.visibility = View.VISIBLE

                val selectGoalkeeperTextView = binding.selectGoalkeeperText
                selectGoalkeeperTextView.visibility = View.VISIBLE
                selectGoalkeeperTextView.setOnClickListener {
                    val builder = AlertDialog.Builder(this@LineupActivity)
                    builder.setTitle("Select goalkeeper")
                    builder.setSingleChoiceItems(goalkeeperNames.toTypedArray(), -1) { dialog, which ->
                        goalkeeper = players[which]
                        selectGoalkeeperTextView.text = goalkeeperNames[which]
                        dialog.dismiss()
                    }
                    builder.setPositiveButton("OK") { dialog, which ->
                        dialog.dismiss()
                    }
                    builder.setNegativeButton("Cancel") { dialog, which -> dialog.dismiss() }
                    builder.show()
                }

                val selectDefendersTextView = binding.selectDefendersTextView
                selectDefendersTextView.visibility = View.VISIBLE
                val defendersArray = defenderNames.toTypedArray()
                selectDefendersTextView.setOnClickListener(View.OnClickListener{
                    println(defenders)
                    println("clicked")
                    val builder = AlertDialog.Builder(this@LineupActivity)
                    builder.setTitle("Select defenders")
                    builder.setMultiChoiceItems(defendersArray, selectedDefenders) { dialog, which, isChecked ->
                        if (isChecked) {
                            defenders.add(players[which])
                        } else {
                            defenders.remove(players[which])
                        }
                    }
                    builder.setPositiveButton("OK") { dialog, which ->
                        val stringBuilder = StringBuilder()
                        for (i in selectedDefenders.indices) {
                            val checked = selectedDefenders[i]
                            if (checked) {
                                stringBuilder.append(defendersArray[i]).append("\n")
                            }
                        }
                        selectDefendersTextView.text = stringBuilder.toString()
                    }
                    builder.setNeutralButton("Clear all") { dialog, which ->
                        for (i in selectedDefenders.indices) {
                            selectedDefenders[i] = false
                            defenders.clear()
                        }
                        selectDefendersTextView.text = "Select Defenders"
                    }
                    builder.show()
                })

                val selectMidfieldersTextView = binding.selectMidfieldersTextView
                selectMidfieldersTextView.visibility = View.VISIBLE
                val midfieldersArray = midfielderNames.toTypedArray()
                selectMidfieldersTextView.setOnClickListener(View.OnClickListener{
                    println(midfielders)
                    println("clicked")
                    val builder = AlertDialog.Builder(this@LineupActivity)
                    builder.setTitle("Select midfielders")
                    builder.setMultiChoiceItems(midfieldersArray, selectedMidfielders) { dialog, which, isChecked ->
                        if (isChecked) {
                            midfielders.add(players[which])
                        } else {
                            midfielders.remove(players[which])
                        }
                    }
                    builder.setPositiveButton("OK") { dialog, which ->
                        val stringBuilder = StringBuilder()
                        for (i in selectedMidfielders.indices) {
                            val checked = selectedMidfielders[i]
                            if (checked) {
                                stringBuilder.append(midfieldersArray[i]).append("\n")
                            }
                        }
                        selectMidfieldersTextView.text = stringBuilder.toString()
                    }
                    builder.setNeutralButton("Clear all") { dialog, which ->
                        for (i in selectedMidfielders.indices) {
                            selectedMidfielders[i] = false
                            midfielders.clear()
                        }
                        selectMidfieldersTextView.text = "Select Midfielders"
                    }
                    builder.show()
                })

                val selectForwardsTextView = binding.selectForwardsTextView
                selectForwardsTextView.visibility = View.VISIBLE
                val forwardsArray = forwardNames.toTypedArray()
                selectForwardsTextView.setOnClickListener(View.OnClickListener{
                    println(forwards)
                    println("clicked")
                    val builder = AlertDialog.Builder(this@LineupActivity)
                    builder.setTitle("Select forwards")
                    builder.setMultiChoiceItems(forwardsArray, selectedForwards) { dialog, which, isChecked ->
                        if (isChecked) {
                            forwards.add(players[which])
                        } else {
                            forwards.remove(players[which])
                        }
                    }
                    builder.setPositiveButton("OK") { dialog, which ->
                        val stringBuilder = StringBuilder()
                        for (i in selectedForwards.indices) {
                            val checked = selectedForwards[i]
                            if (checked) {
                                stringBuilder.append(forwardsArray[i]).append("\n")
                            }
                        }
                        selectForwardsTextView.text = stringBuilder.toString()
                    }
                    builder.setNeutralButton("Clear all") { dialog, which ->
                        for (i in selectedForwards.indices) {
                            selectedForwards[i] = false
                            forwards.clear()
                        }
                        selectForwardsTextView.text = "Select Forwards"
                    }
                    builder.show()
                })
            }
        }

        binding.addButton.setOnClickListener {
            val lineupType = binding.lineupTypeEditText.text.toString()
            if(lineupType.length < 1 || lineupType.length > 11){
                Toast.makeText(this, "Please enter a valid lineup type", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            val substitutes: ArrayList<Player> = arrayListOf()
            for (p in players){
                if (!defenders.contains(p) && !midfielders.contains(p) && !forwards.contains(p) && p != goalkeeper){
                    substitutes.add(p)
                }
            }
            /*
            println("Goalkeeper: $goalkeeper")
            println("Defenders: $defenders")
            println("Midfielders: $midfielders")
            println("Forwards: $forwards")
             */
            lifecycleScope.launch {
                binding.sendingLineupText.visibility = View.VISIBLE
                LineupAPI().postAndUpdateEverything(
                    lineupType,
                    match.homeTeam._id,
                    match._id,
                    goalkeeper!!,
                    defenders,
                    midfielders,
                    forwards,
                    substitutes,
                    this@LineupActivity
                )
                finish()
            }
        }

        binding.backButton.setOnClickListener{
            finish()
        }
        setContentView(binding.root)
    }
}

/*
class MainActivity : AppCompatActivity() {
    // initialize variables
    var textView: TextView? = null
    var selectedLanguage: BooleanArray
    var langList = java.util.ArrayList<Int>()
    var langArray = arrayOf("Java", "C++", "Kotlin", "C", "Python", "Javascript")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // assign variable
        textView = findViewById(R.id.textView)

        // initialize selected language array
        selectedLanguage = BooleanArray(langArray.size)
        textView.setOnClickListener(View.OnClickListener { // Initialize alert dialog
            val builder = AlertDialog.Builder(this@MainActivity)

            // set title
            builder.setTitle("Select Language")

            // set dialog non cancelable
            builder.setCancelable(false)
            builder.setMultiChoiceItems(
                langArray, selectedLanguage
            ) { dialogInterface, i, b ->
                // check condition
                if (b) {
                    // when checkbox selected
                    // Add position in lang list
                    langList.add(i)
                    // Sort array list
                    Collections.sort(langList)
                } else {
                    // when checkbox unselected
                    // Remove position from langList
                    langList.remove(Integer.valueOf(i))
                }
            }
            builder.setPositiveButton(
                "OK"
            ) { dialogInterface, i -> // Initialize string builder
                val stringBuilder = StringBuilder()
                // use for loop
                for (j in langList.indices) {
                    // concat array value
                    stringBuilder.append(langArray[langList[j]])
                    // check condition
                    if (j != langList.size - 1) {
                        // When j value not equal
                        // to lang list size - 1
                        // add comma
                        stringBuilder.append(", ")
                    }
                }
                // set text on textView
                textView.setText(stringBuilder.toString())
            }
            builder.setNegativeButton(
                "Cancel"
            ) { dialogInterface, i -> // dismiss dialog
                dialogInterface.dismiss()
            }
            builder.setNeutralButton(
                "Clear All"
            ) { dialogInterface, i ->
                // use for loop
                for (j in selectedLanguage.indices) {
                    // remove all selection
                    selectedLanguage[j] = false
                    // clear language list
                    langList.clear()
                    // clear text view value
                    textView.setText("")
                }
            }
            // show dialog
            builder.show()
        })
    }
}*/


