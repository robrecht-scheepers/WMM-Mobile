package scheepers.robrecht.wmm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_finish_transaction.*
import java.io.OutputStreamWriter
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL
import java.util.*
import kotlinx.coroutines.*

private const val LOGTAG: String = "FinishTransaction"
private const val AMOUNT: String = "amount"
private const val DATE: String = "date"

class FinishTransactionActivity : AppCompatActivity() {
    private lateinit var date:Calendar
    private var amount: Double = 0.0
    private var category: String = ""
    private var comments: String = ""

    private val categories = arrayOf("Einkaufen", "Kinderkleidung", "Restaurant")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_transaction)

        // init date and amount passed from previous activity
        date = intent.getSerializableExtra(DATE) as Calendar
        dateText.text =
            "${date.get(Calendar.DAY_OF_MONTH)}.${date.get(Calendar.MONTH)}.${date.get(Calendar.YEAR)}"
        amount = intent.getDoubleExtra(AMOUNT, 0.0)
        amountText.text = amount.toString()

        // init category spinner
        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, categories)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = spinnerAdapter

        categorySpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onItemSelected(parent: AdapterView<*>?, view: View?,position: Int,id: Long){
                category = categories[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                category = ""
            }
        }

        sendButton.setOnClickListener { GlobalScope.launch{ sendTransaction() } }
    }

    private suspend fun sendTransaction(){

        val transaction = Transaction(date, amount, category, commentsText.text.toString())
        val postContent = Gson().toJson(transaction)
        Log.d(LOGTAG, "Posting transaction as $postContent")

        val reply = StringBuilder()
        val url = URL("https://localhost:44301/api/transaction")

        with(url.openConnection() as HttpURLConnection)
        {
            requestMethod = "POST"
            doOutput = true
            OutputStreamWriter(outputStream).write(postContent)
            inputStream.buffered().reader().use { reply.append(it.readText())}
            Log.d(LOGTAG, "Received reply ${reply.toString()}")
        }

    }



}
