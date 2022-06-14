package khobir.abdul.bareksa_test.ui.custom

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import khobir.abdul.bareksa_test.databinding.CustomPerbandinganViewBinding
import khobir.abdul.bareksa_test.domain.perbandingan.model.PerbandinganDataModel

class PerbandinganView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet ?= null,
    defStyleAttr: Int = 0
): LinearLayout(context, attributeSet, defStyleAttr) {
    private val binding = CustomPerbandinganViewBinding.inflate(LayoutInflater.from(context), this, true)

    val itemViews = mutableListOf<PerbandinganItemView>()
    fun setPerbandinganData(listData: List<PerbandinganDataModel>){
        binding.llPerbandinganContent.removeAllViews()
        itemViews.clear()
        listData.forEachIndexed { index, it ->
            val view = PerbandinganItemView(context)
            var color = "#E2EBDD"
            var showLabel = false
            if (index==0) {
                showLabel = true
                color = "#E2EBDD"
            } else if (index==1) {
                color = "#E0DBEB"
            } else if (index==2) color = "#E0E8EE"
            view.initView(it, color, showLabel)
            val lp = LayoutParams(0, LayoutParams.WRAP_CONTENT, 1F)
            itemViews.add(view)
            binding.llPerbandinganContent.addView(view, lp)
        }
    }

    fun updateImbalHasil(type: ImbalHasilType) {
        itemViews.forEach {
            it.updateTextImbalHasil(type)
        }
    }
}

enum class ImbalHasilType(val string: String) {
    OneWeek("1W"),
    OneMonth("1M"),
    OneYear("1Y"),
    ThreeYear("3Y"),
    FiveYear("5Y"),
    TenYear("10Y"),
    ALL("ALL")
}