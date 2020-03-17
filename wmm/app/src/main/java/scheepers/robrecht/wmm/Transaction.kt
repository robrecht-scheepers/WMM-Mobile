package scheepers.robrecht.wmm

import java.util.*

data class Transaction (val date: Calendar, val amount: Double,  val category: String,
                        val comments: String) {
}