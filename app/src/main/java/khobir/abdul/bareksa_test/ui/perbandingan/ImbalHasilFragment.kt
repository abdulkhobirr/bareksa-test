package khobir.abdul.bareksa_test.ui.perbandingan

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.github.mikephil.charting.components.MarkerView
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.highlight.Highlight
import com.github.mikephil.charting.listener.OnChartValueSelectedListener
import com.github.mikephil.charting.utils.MPPointF
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import khobir.abdul.bareksa_test.R
import khobir.abdul.bareksa_test.databinding.FragmentImbalHasilBinding
import khobir.abdul.bareksa_test.ui.custom.ImbalHasilType
import khobir.abdul.bareksa_test.utils.XAxisValueFormatter
import khobir.abdul.bareksa_test.utils.ResultWrapper
import khobir.abdul.bareksa_test.utils.Utils
import khobir.abdul.bareksa_test.utils.YAxisValueFormatter

@AndroidEntryPoint
class ImbalHasilFragment : Fragment() {
    private var _binding : FragmentImbalHasilBinding?= null
    private val binding get() = _binding!!

    private val viewModel: PerbandinganViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentImbalHasilBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservable()
        viewModel.getPerbandinganData()
        initTab()
        setupChart()
    }

    private fun setupChart(){
        binding.lineChart.description.isEnabled = false
        binding.lineChart.marker = object : MarkerView(requireContext(), R.layout.item_tooltip) {
            override fun refreshContent(e: Entry?, highlight: Highlight?) {
                val v = this.findViewById<ImageView>(R.id.ivTooltip)
                when(highlight?.dataSetIndex) {
                    0 -> v?.setImageResource(R.drawable.bg_circle_green)
                    1 -> v?.setImageResource(R.drawable.bg_circle_purple)
                    2 -> v?.setImageResource(R.drawable.bg_circle_blue)
                }
                super.refreshContent(e, highlight)
            }

            override fun getOffset(): MPPointF {
                return MPPointF(-(width / 2).toFloat(), (-height/2).toFloat())
            }
        }
        binding.lineChart.setDrawGridBackground(true)
        binding.lineChart.setGridBackgroundColor(ContextCompat.getColor(requireContext(),android.R.color.transparent))
        binding.lineChart.axisLeft.setDrawGridLines(false)
        binding.lineChart.xAxis.setDrawGridLines(false)
        binding.lineChart.setTouchEnabled(true)
        binding.lineChart.isDragEnabled = true
        binding.lineChart.setScaleEnabled(false)
        binding.lineChart.axisLeft.isEnabled = false
        binding.lineChart.axisRight.isEnabled = true
        binding.lineChart.legend.isEnabled = false
        binding.lineChart.setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
            override fun onValueSelected(e: Entry, h: Highlight?) {
                if (h!=null) {
                    val selected = viewModel.onChartSelected(e, h)
                    Log.d("TAG", "onValueSelected: ${selected.first}, ${selected.second}")
                    binding.lineChart.highlightValues(selected.first.toTypedArray())
                    if (selected.second.count()==3){
                        binding.tvPercentGreen.text = String.format("${selected.second[0].y} %%")
                        binding.tvPercentPurple.text = String.format("${selected.second[1].y} %%")
                        binding.tvPercentBlue.text = String.format("${selected.second[2].y} %%")
                        binding.tvDate.text = Utils.formatDate("dd MMM yyyy", selected.second[0].x.toLong())
                    }
                }
            }

            override fun onNothingSelected() {}
        })
        binding.lineChart.xAxis.apply {
            valueFormatter = XAxisValueFormatter()
            granularity = 1F
            position = XAxis.XAxisPosition.BOTTOM
        }
        binding.lineChart.axisRight.valueFormatter = YAxisValueFormatter()
    }

    private fun initTab(){
        val listString = listOf(ImbalHasilType.OneWeek.string, ImbalHasilType.OneMonth.string,
            ImbalHasilType.OneYear.string, ImbalHasilType.ThreeYear.string,
            ImbalHasilType.FiveYear.string, ImbalHasilType.TenYear.string, ImbalHasilType.ALL.string)
        listString.forEachIndexed { index, string ->
            binding.tabLayoutHome.addTab(binding.tabLayoutHome.newTab().setText(string), index)
        }

        binding.tabLayoutHome.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                //change percent
                val type = when(tab.text.toString()){
                    "1W" -> ImbalHasilType.OneWeek
                    "1M" -> ImbalHasilType.OneMonth
                    "1Y" -> ImbalHasilType.OneYear
                    "3Y" -> ImbalHasilType.ThreeYear
                    "5Y" -> ImbalHasilType.FiveYear
                    "10Y" -> ImbalHasilType.TenYear
                    "ALL" -> ImbalHasilType.ALL
                    else -> {
                        ImbalHasilType.OneYear
                    }
                }
                binding.customPerbandinganView.updateImbalHasil(type)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
    }

    private fun initObservable(){
        viewModel.perbandinganData.observe(viewLifecycleOwner) {
            when(it){
                is ResultWrapper.Success -> {
                    binding.customPerbandinganView.setPerbandinganData(it.data)
                    viewModel.getChartData()
                }
            }
        }

        viewModel.chartData.observe(viewLifecycleOwner) {
            when(it){
                is ResultWrapper.Success -> {
                    val processedList = viewModel.processChartData(it.data)
                    binding.lineChart.data = LineData(processedList)
                    binding.lineChart.invalidate()
                }
            }
        }
    }
}