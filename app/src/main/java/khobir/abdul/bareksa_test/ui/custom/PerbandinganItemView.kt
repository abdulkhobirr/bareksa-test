package khobir.abdul.bareksa_test.ui.custom

import android.content.Context
import android.content.res.ColorStateList
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import com.bumptech.glide.Glide
import khobir.abdul.bareksa_test.databinding.ItemImbalHasilBinding
import khobir.abdul.bareksa_test.domain.perbandingan.model.PerbandinganDataModel
import khobir.abdul.bareksa_test.utils.Utils

class PerbandinganItemView @JvmOverloads constructor(
    context: Context,
    attributeSet: AttributeSet?= null,
    defStyleAttr: Int = 0
): LinearLayout(context, attributeSet, defStyleAttr) {
    private val binding = ItemImbalHasilBinding.inflate(LayoutInflater.from(context), this, true)

    private lateinit var data: PerbandinganDataModel
    fun initView(data: PerbandinganDataModel, color: Int, showLabel: Boolean){
        this.data = data
        val details = data.details
        binding.tvName.text = data.name
        binding.tvType.text = details.type
        binding.tvMinPembelian.text = Utils.formatPrice(details.minSubscription)
        binding.tvPeluncuran.text = details.inceptionDate
        binding.tvImbalHasil.text = String.format("${details.returnOneYear}%% / thn")
        val danaKelolaan = details.nav*details.totalUnit.toFloat()
        Log.d("TAG", "initView: ${data.name}")
        Log.d("TAG", "initView: ${danaKelolaan.toLong()}")
        val calculateDana = Utils.formatPrice(danaKelolaan.toString())
        binding.tvDanaKelolaan.text = calculateDana
        Glide.with(context).load(details.avatarUrl).into(binding.ivItem)
        setColorBg(color)
        hideLabel(showLabel)
        when(details.typeId){
            "1" -> {
                binding.tvJangkaWaktu.text = "1 Tahun"
                binding.tvTingkatResiko.text = "Rendah"
            }
            "2" -> {
                binding.tvJangkaWaktu.text = "1-5 Tahun"
                binding.tvTingkatResiko.text = "Moderat"
            }
            "3" -> {
                binding.tvJangkaWaktu.text = "5 Tahun"
                binding.tvTingkatResiko.text = "Tinggi"
            }
        }
    }

    fun updateTextImbalHasil(type: ImbalHasilType){
        when(type){
            ImbalHasilType.OneWeek -> {
                binding.tvImbalHasil.text = String.format("${data.details.returnOneWeek}%% / thn")
            }
            ImbalHasilType.OneMonth -> {
                binding.tvImbalHasil.text = String.format("${data.details.returnOneMonth}%% / thn")
            }
            ImbalHasilType.OneYear -> {
                binding.tvImbalHasil.text = String.format("${data.details.returnOneYear}%% / thn")
            }
            ImbalHasilType.ThreeYear -> {
                binding.tvImbalHasil.text = String.format("${data.details.returnThreeYear}%% / thn")
            }
            ImbalHasilType.FiveYear -> {
                binding.tvImbalHasil.text = String.format("${data.details.returnFiveYear}%% / thn")
            }
            ImbalHasilType.TenYear -> {
                binding.tvImbalHasil.text = "-"
            }
            ImbalHasilType.ALL -> {
                binding.tvImbalHasil.text = String.format("${data.details.returnInceptionGrowth}%% / thn")
            }
        }
    }

    private fun hideLabel(show: Boolean){
        if (!show){
            binding.tvLabelImbalHasil.visibility = View.INVISIBLE
            binding.tvLabelDanaKelolaan.visibility = View.INVISIBLE
            binding.tvLabelJangaWaktu.visibility = View.INVISIBLE
            binding.tvLabelMinPembelian.visibility = View.INVISIBLE
            binding.tvLabelPeluncuran.visibility = View.INVISIBLE
            binding.tvLabelTingkatResiko.visibility = View.INVISIBLE
            binding.tvLabelType.visibility = View.INVISIBLE
        }
    }

    private fun setColorBg(color1: Int){
        binding.llDanaKelolaan.backgroundTintList = ColorStateList.valueOf(color1)
        binding.llImbalhasil.backgroundTintList = ColorStateList.valueOf(color1)
        binding.llTitle.backgroundTintList = ColorStateList.valueOf(color1)
        binding.llJangkaWaktu.backgroundTintList = ColorStateList.valueOf(color1)
        binding.llPeluncuran.backgroundTintList = ColorStateList.valueOf(color1)
        binding.llMinPembelian.backgroundTintList = ColorStateList.valueOf(color1)
        binding.llTingkatResiko.backgroundTintList = ColorStateList.valueOf(color1)
        binding.llType.backgroundTintList = ColorStateList.valueOf(color1)
    }
}