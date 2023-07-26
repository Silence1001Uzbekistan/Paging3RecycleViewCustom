package uz.artikov.paging3recycleviewimportant.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import uz.artikov.paging3recycleviewimportant.databinding.ItemDataBinding
import uz.artikov.paging3recycleviewimportant.databinding.ItemLoadBinding
import uz.artikov.paging3recycleviewimportant.models.Data

class PaginationAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val LOADING = 0
    private val DATA = 1
    private var isLoadingAdded = false

    private val list = ArrayList<Data>()

    inner class LoadingVh(var itemLoadBinding: ItemLoadBinding) :
        RecyclerView.ViewHolder(itemLoadBinding.root) {

        fun onBind() {

            itemLoadBinding.progressBar.visibility = View.VISIBLE

        }

    }

    inner class DataVh(var itemDataBinding: ItemDataBinding) :
        RecyclerView.ViewHolder(itemDataBinding.root) {


        @SuppressLint("SetTextI18n")
        fun onBind(data: Data) {

            //apply viewData dagi viewlarni ko'radi
            itemDataBinding.apply {

                Picasso.get().load(data.avatar).into(avatar)
                tv1.text = "${data.first_name}  ${data.last_name}"
                tv2.text = data.email

            }


        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        if (viewType == LOADING) {

            return LoadingVh(
                ItemLoadBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        } else {

            return DataVh(
                ItemDataBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

        }

    }

    override fun getItemCount(): Int {

        return list.size

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val itemViewType = getItemViewType(position)

        if (itemViewType == LOADING) {

            val loadingVh = holder as LoadingVh

            loadingVh.onBind()

        } else {

            val dataVh = holder as DataVh
            dataVh.onBind(list[position])

        }

    }

    override fun getItemViewType(position: Int): Int {
        return if (position == list.size - 1 && isLoadingAdded) LOADING else DATA
    }

    @SuppressLint("NotifyDataSetChanged")
    fun addAll(otherlist: List<Data>) {

        list.addAll(otherlist)
        notifyDataSetChanged()

    }

    fun editLoading() {

        isLoadingAdded = true

    }

    fun removeLoading() {

        isLoadingAdded = false

    }

}