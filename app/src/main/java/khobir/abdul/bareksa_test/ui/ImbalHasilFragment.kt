package khobir.abdul.bareksa_test.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.tabs.TabLayout
import dagger.hilt.android.AndroidEntryPoint
import khobir.abdul.bareksa_test.databinding.FragmentImbalHasilBinding
import khobir.abdul.bareksa_test.ui.custom.ImbalHasilType
import khobir.abdul.bareksa_test.utils.ResultWrapper

@AndroidEntryPoint
class ImbalHasilFragment : Fragment() {
    private var _binding : FragmentImbalHasilBinding ?= null
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
        viewModel.getChartData()
        viewModel.getPerbandinganData()
        initTab()
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
                }
            }
        }
    }
}