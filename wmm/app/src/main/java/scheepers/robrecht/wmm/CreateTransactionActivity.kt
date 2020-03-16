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
    private val amountText by lazy { findViewById<EditText>(R.id.amountText) };

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_transaction)

        val numberButtonListener = View.OnClickListener { v ->
            val btn = v as Button
            amountText.append(btn.text)
        }

        findViewById<Button>(R.id.button0).setOnClickListener(numberButtonListener)
        findViewById<Button>(R.id.button1).setOnClickListener(numberButtonListener)
        findViewById<Button>(R.id.button2).setOnClickListener(numberButtonListener)
        findViewById<Button>(R.id.button3).setOnClickListener(numberButtonListener)
        findViewById<Button>(R.id.button4).setOnClickListener(numberButtonListener)
        findViewById<Button>(R.id.button5).setOnClickListener(numberButtonListener)
        findViewById<Button>(R.id.button6).setOnClickListener(numberButtonListener)
        findViewById<Button>(R.id.button7).setOnClickListener(numberButtonListener)
        findViewById<Button>(R.id.button8).setOnClickListener(numberButtonListener)
        findViewById<Button>(R.id.button9).setOnClickListener(numberButtonListener)
        findViewById<Button>(R.id.buttonComma).setOnClickListener(numberButtonListener)

        findViewById<Button>(R.id.buttonErase).setOnClickListener { v ->
            if(amountText.text.isNotEmpty())
                amountText.setText(amountText.text.substring(0,amountText.text.length - 1))
        }

        findViewById<Button>(R.id.buttonDate).setOnClickListener { v ->
            val c = Calendar.getInstance()
            val year = c.get(Calendar.YEAR)
            val month = c.get(Calendar.MONTH)
            val day = c.get(Calendar.DAY_OF_MONTH)

            val datePicker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{v, y, m, d ->
                dateText.setText("$d.$m.$y")}, year, month, day)
            datePicker.show()
        }



    }
}
