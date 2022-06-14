package khobir.abdul.bareksa_test.ui

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import khobir.abdul.bareksa_test.domain.perbandingan.PerbandinganRepository
import khobir.abdul.bareksa_test.domain.perbandingan.model.ChartDataModel
import khobir.abdul.bareksa_test.domain.perbandingan.model.PerbandinganDataModel
import khobir.abdul.bareksa_test.utils.ResultWrapper
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PerbandinganViewModel @Inject constructor(private val repository: PerbandinganRepository): ViewModel() {

    private val _perbandinganData = MutableLiveData<ResultWrapper<List<PerbandinganDataModel>>>()
    val perbandinganData = _perbandinganData as LiveData<ResultWrapper<List<PerbandinganDataModel>>>

    private val _chartData = MutableLiveData<ResultWrapper<ChartDataModel>>()
    val chartData = _chartData as LiveData<ResultWrapper<ChartDataModel>>

    fun getPerbandinganData(){
        _perbandinganData.postValue(ResultWrapper.loading())
        viewModelScope.launch {
            val result = repository.getPerbandinganData()
            Log.d("TAG", "getPerbandinganData: $result")
            _perbandinganData.postValue(result)
        }
    }

    fun getChartData(){
        _chartData.postValue(ResultWrapper.loading())
        viewModelScope.launch {
            val result = repository.getPerbandinganChartData()
            Log.d("TAG", "getPerbandinganChartData: $result")
            _chartData.postValue(result)
        }
    }
}