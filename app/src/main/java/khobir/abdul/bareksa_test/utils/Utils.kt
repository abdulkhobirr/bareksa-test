package khobir.abdul.bareksa_test.utils

import android.util.Log
import java.lang.Exception

object Utils {
    fun simplifyNumber(number: String): String {
        var textToDisplay = ""
        return try {
            var len = number.length
            var temp = number.toInt()
            var count = 0
            while(len>3){
                temp = (temp/1000)
                len = temp.toString().length
                count++
                Log.d("TAG", "simplifyNumberTemp: $temp")
                Log.d("TAG", "simplifyNumberLen: $len")
                Log.d("TAG", "simplifyNumberCounter: $count")
            }
            var suffix = ""
            if (count==1) suffix = "Ribu"
            if (count==2) suffix = "Juta"
            if (count==3) suffix = "Miliar"
            textToDisplay = String.format("$temp $suffix")
            return textToDisplay
        } catch (e: Exception) {
            return ""
        }
    }
}