package scheepers.robrecht.wmm

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_create_transaction.*
import java.lang.NumberFormatException
import java.util.*

private const val LOGTAG: String = "CreateTransaction"
private const val AMOUNT: String = "amount"
private const val DATE: String = "date"

class CreateTransactionActivity : AppCompatActivity() {
    private var date:Calendar = Calendar.getInstance()
    private var amount:Double? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_transaction)

        // numeric keypad button handlers
        val numberButtonListener = View.OnClickListener { v ->
            val btn = v as Button
            amountText.append(btn.text)
            updateAmount()
        }
        button0.setOnClickListener(numberButtonListener)
        button1.setOnClickListener(numberButtonListener)
        button2.setOnClickListener(numberButtonListener)
        button3.setOnClickListener(numberButtonListener)
        button4.setOnClickListener(numberButtonListener)
        button5.setOnClickListener(numberButtonListener)
        button6.setOnClickListener(numberButtonListener)
        button7.setOnClickListener(numberButtonListener)
        button8.setOnClickListener(numberButtonListener)
        button9.setOnClickListener(numberButtonListener)
        buttonComma.setOnClickListener(numberButtonListener)
        buttonErase.setOnClickListener { v ->
            if(amountText.text.isNotEmpty()) {
                amountText.setText(amountText.text.substring(0, amountText.text.length - 1))
                updateAmount()
            }
        }

        // date
        updateDateText()
        buttonDate.setOnClickListener { v ->
            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
                    v, y, m, d ->
                run {
                    date.set(y, m, d)
                    updateDateText()
                }
            },date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }

        // continue
        buttonContinue.setOnClickListener { v ->
            val intent = Intent(this, FinishTransactionActivity::class.java)
            if(amount != null) {
                intent.putExtra("amount", amount)
            }
            intent.putExtra("date", date)
            startActivity(intent)
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        Log.d(LOGTAG, "onSaveInstanceState")
        super.onSaveInstanceState(outState)
        if(amount !=null) {
            outState.putDouble(AMOUNT, amount!!)
        }
        outState.putSerializable(DATE, date)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)

        if(savedInstanceState.containsKey(AMOUNT)) {
            amount = savedInstanceState.getDouble(AMOUNT)
        }
        date = savedInstanceState.getSerializable(DATE) as Calendar
    }

    private fun updateDateText()
    {
        dateText.setText(
            "${date.get(Calendar.YEAR)};${date.get(Calendar.MONTH)}.${date.get(Calendar.DAY_OF_MONTH)}")
    }

    private fun updateAmount()
    {
        amount = try {
            if(amountText.text.isNotEmpty())
                amountText.text.toString().toDouble()
            else
                null
        } catch(e:NumberFormatException) {
            null
        }
    }
}
