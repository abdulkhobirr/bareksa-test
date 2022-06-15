package khobir.abdul.bareksa_test.ui.perbandingan

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
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
import khobir.abdul.bareksa_test.utils.*

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
        binding.lineChart.apply {
            description.isEnabled = false
            legend.isEnabled = false
            isDragEnabled = true
            setDrawGridBackground(true)
            setGridBackgroundColor(ContextCompat.getColor(requireContext(),android.R.color.transparent))
            setTouchEnabled(true)
            setScaleEnabled(false)
            axisLeft.setDrawGridLines(false)
            axisLeft.isEnabled = false
            axisRight.setDrawAxisLine(false)
            axisRight.xOffset = 20f
            axisRight.isEnabled = true
            axisRight.typeface = ResourcesCompat.getFont(requireContext(), R.font.montserrat_medium)
            axisRight.valueFormatter = YAxisValueFormatter()
            axisRight.labelCount = 8
            xAxis.setDrawGridLines(false)
            xAxis.valueFormatter = XAxisValueFormatter()
            xAxis.granularity = 1F
            xAxis.typeface = ResourcesCompat.getFont(requireContext(), R.font.montserrat_medium)
            xAxis.position = XAxis.XAxisPosition.BOTTOM

            marker = object : MarkerView(requireContext(), R.layout.item_tooltip) {
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

            setOnChartValueSelectedListener(object : OnChartValueSelectedListener {
                override fun onValueSelected(e: Entry, h: Highlight?) {
                    if (h!=null) {
                        val selected = viewModel.onChartSelected(e, h)
                        binding.lineChart.highlightValues(selected.first.toTypedArray())
                        if (selected.second.count()==3){
                            binding.tvPercentGreen.text = String.format("${selected.second[0].y} %%")
                            binding.tvPercentPurple.text = String.format("${selected.second[1].y} %%")
                            binding.tvPercentBlue.text = String.format("${selected.second[2].y} %%")
                            binding.tvDate.text = Utils.formatDate("dd MMM yyyy", selected.second[0].x.toLong())
                        }
                        if (selected.second.count()==2){
                            binding.tvPercentGreen.text = String.format("${selected.second[0].y} %%")
                            binding.tvPercentPurple.text = String.format("${selected.second[1].y} %%")
                            binding.tvDate.text = Utils.formatDate("dd MMM yyyy", selected.second[0].x.toLong())
                        }
                    }
                }

                override fun onNothingSelected() {}
            })
        }
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
                val type = when(tab.text.toString()){
                    ImbalHasilType.OneWeek.string -> ImbalHasilType.OneWeek
                    ImbalHasilType.OneMonth.string -> ImbalHasilType.OneMonth
                    ImbalHasilType.OneYear.string -> ImbalHasilType.OneYear
                    ImbalHasilType.ThreeYear.string -> ImbalHasilType.ThreeYear
                    ImbalHasilType.FiveYear.string -> ImbalHasilType.FiveYear
                    ImbalHasilType.TenYear.string -> ImbalHasilType.TenYear
                    ImbalHasilType.ALL.string -> ImbalHasilType.ALL
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
                    binding.msvPerbandinganView.showDefaultState()
                    binding.customPerbandinganView.setPerbandinganData(it.data)
                    if (it.data.count()>=3) {
                        binding.ivPercentBlue.visibility = View.VISIBLE
                        binding.tvPercentBlue.visibility = View.VISIBLE
                    }
                    val tab = binding.tabLayoutHome.getTabAt(2)
                    binding.tabLayoutHome.selectTab(tab)
                    viewModel.getChartData()
                }
                is ResultWrapper.Failure -> {
                    binding.msvPerbandinganView.showErrorState(it.message, it.title){
                        viewModel.getPerbandinganData()
                    }
                }
                is ResultWrapper.Loading -> {
                    binding.msvPerbandinganView.showLoadingState()
                }
            }
        }

        viewModel.chartData.observe(viewLifecycleOwner) {
            when(it){
                is ResultWrapper.Success -> {
                    binding.msvLineChart.showDefaultState()
                    val processedList = viewModel.processChartData(it.data)
                    binding.lineChart.data = LineData(processedList)
                    binding.lineChart.invalidate()
                }
                is ResultWrapper.Failure -> {
                    binding.msvLineChart.showErrorState(it.message, it.title){
                        viewModel.getChartData()
                    }
                }
                is ResultWrapper.Loading -> {
                    binding.msvLineChart.showLoadingState()
                }
            }
        }
    }
}