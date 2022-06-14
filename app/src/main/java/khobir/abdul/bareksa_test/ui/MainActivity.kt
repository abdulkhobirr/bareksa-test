package khobir.abdul.bareksa_test.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint
import khobir.abdul.bareksa_test.R
import khobir.abdul.bareksa_test.databinding.ActivityMainBinding
import khobir.abdul.bareksa_test.utils.CommonPagerAdapter

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewPager()
    }

    private fun initViewPager(){
        val tabList = listOf("Imbal Hasil", "Dana Kelolaan")
        val fragmentList = listOf(ImbalHasilFragment(), DanaKelolaanFragment())

        val pagerAdapter = CommonPagerAdapter(fragmentList, supportFragmentManager, lifecycle)
        binding.vpHome.apply {
            orientation = ViewPager2.ORIENTATION_HORIZONTAL
            adapter = pagerAdapter
            offscreenPageLimit = fragmentList.size
            isUserInputEnabled = true
        }
        binding.tabLayoutHome.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                binding.vpHome.currentItem = tab.position
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
            }
        })
        TabLayoutMediator(binding.tabLayoutHome, binding.vpHome){ tab, position ->
            tab.text = tabList[position]
        }.attach()
    }
}