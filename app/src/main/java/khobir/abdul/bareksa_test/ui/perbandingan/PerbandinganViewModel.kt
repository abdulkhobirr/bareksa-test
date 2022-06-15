package khobir.abdul.bareksa_test.ui.perbandingan

import android.graphics.Color
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.highlight.Highlight
import dagger.hilt.android.lifecycle.HiltViewModel
import khobir.abdul.bareksa_test.domain.perbandingan.PerbandinganRepository
import khobir.abdul.bareksa_test.domain.perbandingan.model.ChartDataModel
import khobir.abdul.bareksa_test.domain.perbandingan.model.PerbandinganDataModel
import khobir.abdul.bareksa_test.utils.ResultWrapper
import khobir.abdul.bareksa_test.utils.Utils
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PerbandinganViewModel @Inject constructor(private val repository: PerbandinganRepository): ViewModel() {

    private val _perbandinganData = MutableLiveData<ResultWrapper<List<PerbandinganDataModel>>>()
    val perbandinganData = _perbandinganData as LiveData<ResultWrapper<List<PerbandinganDataModel>>>

    private val _chartData = MutableLiveData<ResultWrapper<ChartDataModel>>()
    val chartData = _chartData as LiveData<ResultWrapper<ChartDataModel>>

    private val listDataSet = mutableListOf<LineDataSet>()

    fun getPerbandinganData(){
        _perbandinganData.postValue(ResultWrapper.loading())
        viewModelScope.launch {
            val result = repository.getPerbandinganData()
            _perbandinganData.postValue(result)
        }
    }

    fun getChartData(){
        _chartData.postValue(ResultWrapper.loading())
        viewModelScope.launch {
            val result = repository.getPerbandinganChartData()
            _chartData.postValue(result)
        }
    }

    fun processChartData(model: ChartDataModel): List<LineDataSet> {
        val mapData = HashMap<String, LineDataSet>()
        model.string.keys.forEach { key ->
            val entries = mutableListOf<Entry>()
            model.string[key]?.listData?.forEach { item ->
                entries.add(Entry(Utils.formatDateToMillis(item.date).toFloat(), item.growth.toFloat()))
            }
            val lineData = LineDataSet(entries, null)
            lineData.mode = LineDataSet.Mode.CUBIC_BEZIER
            lineData.setDrawCircles(false)
            lineData.setDrawHorizontalHighlightIndicator(false)
            lineData.lineWidth = 3F
            var color = 0
            when(key){
                "NI002EQCDANSIE00" -> color = Color.parseColor("#668054")
                "KI002MMCDANCAS00" -> color = Color.parseColor("#725E9C")
                "TP002EQCEQTCRS00" -> color = Color.parseColor("#6D98B6")
            }
            lineData.color = color
            mapData[key] = lineData
        }

        listDataSet.clear()

        if (_perbandinganData.value is ResultWrapper.Success){
            (_perbandinganData.value as ResultWrapper.Success<List<PerbandinganDataModel>>).data.forEach { compareModel ->
                mapData[compareModel.codeName]?.let {
                    listDataSet.add(it)
                }
            }
        } else {
            mapData.keys.forEach { key ->
                mapData[key]?.let { it1 ->
                    listDataSet.add(it1)
                }
            }
        }
        return listDataSet
    }

    fun onChartSelected(entry: Entry, highlight: Highlight): Pair<List<Highlight>, List<Entry>> {
        val listHighlight = mutableListOf<Highlight>()
        val listEntry = mutableListOf<Entry>()
        listDataSet.forEachIndexed { index, dataset ->
            val toHighlight = Highlight(highlight.x, index, -1)
            listHighlight.add(toHighlight)
            val entryToAdd = dataset.getEntryForXValue(highlight.x, entry.y)
            listEntry.add(entryToAdd)
        }
        return Pair(listHighlight.toList(), listEntry.toList())
    }
}