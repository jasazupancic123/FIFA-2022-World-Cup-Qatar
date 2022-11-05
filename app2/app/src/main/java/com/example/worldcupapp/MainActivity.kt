package com.example.worldcupapp

//import adapter
import com.example.worldcupapp.adapter.MatchAdapter
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import api.MatchAPI
import com.example.worldcupapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext


class MainActivity : AppCompatActivity(){
    private val scope = CoroutineScope(newSingleThreadContext("name"))
    private lateinit var binding: ActivityMainBinding
    lateinit var recyclerView: RecyclerView
    private var matches: MutableList<Match>? = null

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        recyclerView = binding.recyclerMatch

        scope.launch async@{
            matches = MatchAPI().find()
            for (match in matches!!){
                println(match.toString())
            }
        }

        while(matches == null){ }
        //generate view for each match
        recyclerView.adapter = MatchAdapter(this, matches!!)
        recyclerView.layoutManager = LinearLayoutManager(this)

        /*
        recyclerView = binding.recyclerMatch
        val manager = RequestManager(this)
        manager.getMatches(listener)
        */
    }
}
/*
private final val listener: ResponseListener = object : ResponseListener {
override fun didFetch(matchResponse: MatchResponse, message: String) {
   recyclerView.setHasFixedSize(true)
   recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
   println("matches: ${matchResponse.matches}")
   recyclerView.adapter = MatchAdapter(this@MainActivity, matchResponse.matches)
}

override fun didError(message: String) {
   Toast.makeText(this@MainActivity, message, Toast.LENGTH_SHORT).show()
}
}
}


private abstract class MyTask : AsyncTask<String?, Int?, String>() {
// Runs in UI before background thread is called
override fun onPreExecute() {
super.onPreExecute()

// Do something like display a progress bar
}

// This is run in a background thread
protected override fun doInBackground(vararg params: String): String {
// get the string from params, which is an array
val myString = params[0]

// Do something that takes a long time, for example:
for (i in 0..100) {

   // Do things

   // Call this to update your progress
   publishProgress(i)
}
return "this string is passed to onPostExecute"
}

// This is called from background thread but runs in UI
protected override fun onProgressUpdate(vararg values: Int) {
super.onProgressUpdate(*values)

// Do things like update the progress bar
}

// This runs in UI when background thread finishes
override fun onPostExecute(result: String) {
super.onPostExecute(result)

// Do things like hide the progress bar or change a TextView
}
}

*/

/*
var matches: MutableList<Match> = mutableListOf()
scope.launch {
   matches = MatchAPI().find()
   for (match in matches) {
       println(match.toString())
   }
}



while(matches.size == 0) { }
for(match in matches) {
   val linearLayout = LinearLayout(this)
   binding.linearLayoutVertical.addView(linearLayout)
   linearLayout.layoutParams = LinearLayout.LayoutParams(
       LinearLayout.LayoutParams.MATCH_PARENT,
       LinearLayout.LayoutParams.WRAP_CONTENT
   )
   linearLayout.orientation = LinearLayout.HORIZONTAL

   val linearLayoutSpecs: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
       LinearLayout.LayoutParams.WRAP_CONTENT,
       LinearLayout.LayoutParams.WRAP_CONTENT,
       1.0f,
   )

   val homeTeamText = TextView(this)
   homeTeamText.id = View.generateViewId()
   homeTeamText.text = match.homeTeam.name
   linearLayout.addView(homeTeamText)

   linearLayoutSpecs.gravity = 10 //center vertical
   homeTeamText.layoutParams = linearLayoutSpecs
   //homeTeamText.gravity = View.TEXT_ALIGNMENT_CENTER

   val date = TextView(this)
   date.id = View.generateViewId()
   date.text = match.date.toString()
   date.layoutParams = linearLayoutSpecs
   linearLayout.addView(date)

   val awayTeamText = TextView(this)
   awayTeamText.id = View.generateViewId()
   awayTeamText.text = match.awayTeam.name
   awayTeamText.layoutParams = linearLayoutSpecs
   linearLayout.addView(awayTeamText)
}
Picasso.with(this).load("https://countryflagsapi.com/png/br").into(binding.TEST)
*/





