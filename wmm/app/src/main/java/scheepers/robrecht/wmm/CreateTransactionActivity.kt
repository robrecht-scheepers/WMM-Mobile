package scheepers.robrecht.wmm

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_create_transaction.*
import java.util.*

class CreateTransactionActivity : AppCompatActivity() {
    private var date:Calendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_transaction)

        // numeric keypad button handlers
        val numberButtonListener = View.OnClickListener { v ->
            val btn = v as Button
            amountText.append(btn.text)
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
            if(amountText.text.isNotEmpty())
                amountText.setText(amountText.text.substring(0,amountText.text.length - 1))
        }

        // date
        updateDateText()
        buttonDate.setOnClickListener { v ->
            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{
                    v, y, m, d ->
                run {
                    updateDateText(d, m, y)
                    date.set(y, m, d)
                }
            },
                date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH))
            datePicker.show()
        }

        
    }

    private fun updateDateText()
    {
        dateText.setText("${date.get(Calendar.YEAR)};${date.get(Calendar.MONTH)}.${date.get(Calendar.DAY_OF_MONTH)}")
    }
}
