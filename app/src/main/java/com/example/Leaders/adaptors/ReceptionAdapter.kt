import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.Leaders.model.Departure
import com.example.tasmeme.adaptors.OnItemClickListener
import com.example.tasmeme.databinding.ReceptionRecItemsBinding

class ReceptionAdapter(private val listener: OnItemClickListener) :
    RecyclerView.Adapter<ReceptionAdapter.MyViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Departure>() {
        override fun areItemsTheSame(oldItem: Departure, newItem: Departure): Boolean {
            return oldItem.nid == newItem.nid &&
                    oldItem.status == newItem.status

        }

        override fun areContentsTheSame(oldItem: Departure, newItem: Departure): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    inner class MyViewHolder(
        val binding: ReceptionRecItemsBinding,
        private val listener: OnItemClickListener
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        init {
            binding.root.setOnClickListener(this)
        }

        override fun onClick(v: View?) {
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
                listener.onItemClick(position, differ.currentList[position].nid)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ReceptionRecItemsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding, listener)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val departure = differ.currentList[position]
        holder.binding.apply {
            recItemStuName.text = "الطالب " + departure.student
            recItemCheckBox.isChecked = departure.status == "7"
//            recItemAdminName.text = "بموافقة من المشرفة ${departure.department_supervisor}"
            recItemStuGrade.text="/"+departure.`class`
            recItemStuTimeToLeave.text = "غادر الصف الساعة ${departure.receptionist_approve_time}"
        }

        holder.binding.recItemCheckBox.setOnClickListener {
            listener.onItemClick(position, departure.nid)
        }
    }

    fun submitList(newList: List<Departure>) {
        differ.submitList(newList)
    }

//    interface OnItemClickListener {
//        fun onItemClick(position: Int, nid: String)
//    }
}
