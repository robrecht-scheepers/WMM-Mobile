package scheepers.robrecht.wmm

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.addTransactionBtn)
        btn.setOnClickListener { addTransaction()  }
    }

    private fun addTransaction(){
        val intent = Intent(this, CreateTransactionActivity::class.java)
        startActivity(intent)
    }
}
