package com.example.surveykshanassignment

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.surveykshanassignment.databinding.RecyclerItemBinding
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class DataAdapter(
    private val context: Context,
    private val dataList: List<dataItem>
) : RecyclerView.Adapter<DataAdapter.DataViewHolder>() {

    private var currentMediaPlayer: MediaPlayer? = null

    inner class DataViewHolder(private  val binding: RecyclerItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: dataItem) {
            binding.ivSelfie.setImageURI(Uri.parse(item.selfie))
            binding.tvGender.text = item.gender
            binding.tvAge.text = item.age.toString()
            binding.tvTimestamp.text = convertMillisToDate(item.timestamp.toLong())
            binding.btnPlayPause.setOnClickListener {
                val audioUri = Uri.parse(item.audio)

                if (currentMediaPlayer != null && currentMediaPlayer!!.isPlaying) {
                    currentMediaPlayer?.pause()
                    binding.btnPlayPause.setImageResource(R.drawable.ic_play) // Set last button to play icon
                    currentMediaPlayer = null
                } else {
                    currentMediaPlayer?.release()
                    binding.btnPlayPause.setImageResource(R.drawable.ic_play) // Reset icon for last button

                    currentMediaPlayer = MediaPlayer.create(context, audioUri).apply {
                        start()
                    }
                    binding.btnPlayPause.setImageResource(R.drawable.ic_pause) // Set icon to pause


                    currentMediaPlayer?.setOnCompletionListener {
                        binding.btnPlayPause.setImageResource(R.drawable.ic_play) // Reset to play icon
                        currentMediaPlayer?.release()
                        currentMediaPlayer = null
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view = RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val actualPosition = position % dataList.size
        holder.bind(dataList[actualPosition])

    }
    fun convertMillisToDate(millis: Long, format: String = "dd/MM/yyyy HH:mm:ss"): String {
        val date = Date(millis)
        val formatter = SimpleDateFormat(format, Locale.getDefault())
        return formatter.format(date)
    }
    override fun getItemCount(): Int = dataList.size

    fun releaseMediaPlayer() {
        currentMediaPlayer?.release()
        currentMediaPlayer = null
    }
}
