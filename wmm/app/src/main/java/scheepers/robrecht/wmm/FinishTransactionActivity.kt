package scheepers.robrecht.wmm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_finish_transaction.*
import java.util.*

private const val LOGTAG: String = "FinishTransaction"
private const val AMOUNT: String = "amount"
private const val DATE: String = "date"

class FinishTransactionActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish_transaction)

        val date: Calendar = intent.getSerializableExtra(DATE) as Calendar
        dateText.text =
            "${date.get(Calendar.DAY_OF_MONTH)}.${date.get(Calendar.MONTH)}.${date.get(Calendar.YEAR)}"

        amountText.text = intent.getDoubleExtra(AMOUNT, 0.0).toString()

        val spinnerAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item,
            listOf("Einkaufen", "Kinderkleidung", "Restaurant"))
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        categorySpinner.adapter = spinnerAdapter
    }
}
