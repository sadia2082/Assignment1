package com.example.assignment1.ui.favourite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.assignment1.Record
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavouriteViewModel : ViewModel() {

    private var RecordList: MutableLiveData<List<Record>> = MutableLiveData()

    fun getAllRecords(): LiveData<List<Record>> {
        viewModelScope.launch(Dispatchers.IO ){
            val records = arrayListOf<Record>()
            for(i in 0..10){
                records.add(Record( "Sadia-"+i, "a good programmer" ))
                RecordList.postValue(records)
            }
        }
        return RecordList;
    }
    // TODO: Implement the ViewModel
}


