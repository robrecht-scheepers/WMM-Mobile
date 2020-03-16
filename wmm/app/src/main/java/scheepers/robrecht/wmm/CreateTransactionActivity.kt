package scheepers.robrecht.wmm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText

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
    }
}
