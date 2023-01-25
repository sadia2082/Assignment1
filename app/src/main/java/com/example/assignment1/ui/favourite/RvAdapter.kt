package com.example.assignment1.ui.favourite


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment1.Record
import com.example.assignment1.databinding.SingleRecordBinding

class RvAdapter(
    private var Record: List<Record>
) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    // create an inner class with name ViewHolder
    // It takes a view argument, in which pass the
    // generated class of single_item.xml
    // ie SingleItemBinding and in the
    // RecyclerView.ViewHolder(binding.root) pass it like this
    inner class ViewHolder(val binding: SingleRecordBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SingleRecordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    // bind the items with each item of
    // the list languageList which than will be
    // shown in recycler view
    // to keep it simple we are not
    // setting any image data to view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val language = Record[position]
//        with(holder) {
//            with(languageList[position]) {
        // set text to language name
        holder.binding.tvLangName.text = language.name
        // set exp
        holder.binding.tvExp.text = language.exp
//            }
//        }
    }
    // return the size of languageList
    override fun getItemCount(): Int {
        return Record.size
    }
}
