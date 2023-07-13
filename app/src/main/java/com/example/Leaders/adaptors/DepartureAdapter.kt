import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.Leaders.model.Departure
import com.example.nerd_android.helpers.ViewUtils.hide
import com.example.nerd_android.helpers.ViewUtils.show
import com.example.tasmeme.R
import com.example.tasmeme.databinding.DepartItemsBinding

class DepartureAdapter : RecyclerView.Adapter<DepartureAdapter.MyViewHolder>() {

    private val differCallback = object : DiffUtil.ItemCallback<Departure>() {
        override fun areItemsTheSame(oldItem: Departure, newItem: Departure): Boolean {
            return oldItem.nid == newItem.nid
        }

        override fun areContentsTheSame(oldItem: Departure, newItem: Departure): Boolean {
            return oldItem == newItem
        }
    }

    private val differ = AsyncListDiffer(this, differCallback)

    inner class MyViewHolder(val binding: DepartItemsBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = DepartItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val departure = differ.currentList[position]
        holder.apply {
            if (departure.status == "6") {
                // Apply changes for status 6
                binding.btnDecline.hide()
                binding.btnAccept.show()
                binding.btnAccept.text = "تم مغادرة الطالب بنجاح"
                binding.btnAccept.isClickable = false
                binding.btnDecline.isClickable = false
                binding.btnAccept.setBackgroundColor(Color.GRAY)
                binding.btnAccept.width = LinearLayout.LayoutParams.MATCH_PARENT
                val layoutParams = binding.btnAccept.layoutParams as LinearLayout.LayoutParams
                layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
                binding.btnAccept.layoutParams = layoutParams
            } else if (departure.status == "8") {
                // Apply changes for status 8
                binding.btnAccept.hide()
                binding.btnDecline.show()
                binding.btnDecline.text = "تم رفض مغادرة الطالب بنجاح"
                binding.btnDecline.isClickable = false
                binding.btnAccept.isClickable = false
                binding.btnDecline.width = LinearLayout.LayoutParams.MATCH_PARENT
                val layoutParams = binding.btnDecline.layoutParams as LinearLayout.LayoutParams
                layoutParams.width = LinearLayout.LayoutParams.MATCH_PARENT
                binding.btnDecline.layoutParams = layoutParams
            } else {
                // Reset buttons for other statuses
                binding.btnAccept.show()
                binding.btnDecline.show()
                binding.btnAccept.text = "موافقة"
                binding.btnDecline.text = "رفض"
                binding.btnAccept.isClickable = true
                binding.btnDecline.isClickable = true
                binding.btnAccept.setBackgroundColor(ContextCompat.getColor(binding.root.context, R.color.green))
                binding.btnAccept.width = LinearLayout.LayoutParams.WRAP_CONTENT
                binding.btnDecline.width = LinearLayout.LayoutParams.WRAP_CONTENT
                val layoutParamsAccept = binding.btnAccept.layoutParams as LinearLayout.LayoutParams
                val layoutParamsDecline = binding.btnDecline.layoutParams as LinearLayout.LayoutParams
                layoutParamsAccept.width = LinearLayout.LayoutParams.WRAP_CONTENT
                layoutParamsDecline.width = LinearLayout.LayoutParams.WRAP_CONTENT
                binding.btnAccept.layoutParams = layoutParamsAccept
                binding.btnDecline.layoutParams = layoutParamsDecline
            }
        }
        holder.binding.name.show()
        holder.binding.name.text = departure.student
       holder.binding.timeOfLeave.text = departure.receptionist_approve_time.toString()
        if(departure.relative_relation.isEmpty()){
            holder.binding.connection.hide()
            holder.binding.tv2.hide()
        }

        holder.binding.btnAccept.setOnClickListener {
            onApproveClickListener?.invoke(departure)
        }

        holder.binding.btnDecline.setOnClickListener {
            onRejectClickListener?.invoke(departure)
        }
    }


    private var onApproveClickListener: ((Departure) -> Unit)? = null

    fun onButtonApproveClicked(listener: (Departure) -> Unit) {
        onApproveClickListener = listener
    }

    private var onRejectClickListener: ((Departure) -> Unit)? = null

    fun onButtonRejectClicked(listener: (Departure) -> Unit) {
        onRejectClickListener = listener
    }

    fun submitList(newList: List<Departure>) {
        differ.submitList(newList)
    }
}
