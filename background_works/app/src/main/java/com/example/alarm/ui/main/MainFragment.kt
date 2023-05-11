package com.example.alarm.ui.main


import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.text.SimpleDateFormat
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.alarm.AlarmActivity
import com.example.alarm.databinding.FragmentMainBinding
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.android.material.timepicker.TimeFormat
import java.util.*

class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = MainFragment()
    }
// https://m3.material.io/components/time-pickers/overview
//    private lateinit var viewModel: MainViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        Log.d("AAA", "$_binding")
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Log.d("AAA", "onViewCreated start")

        val simpleDateFormat = SimpleDateFormat("HH:mm", Locale.getDefault())

        binding.buttonAlarm.setOnClickListener {
            val materialTimePicker = MaterialTimePicker.Builder()
                .setTimeFormat(TimeFormat.CLOCK_24H)
                .setHour(0)
                .setMinute(0)
                .setTitleText("Установите время будильника")
                .build()

            materialTimePicker.addOnPositiveButtonClickListener {
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.MILLISECOND, 0)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MINUTE, materialTimePicker.minute)
                calendar.set(Calendar.HOUR_OF_DAY, materialTimePicker.hour)

                val alarmManager = requireContext().getSystemService(Context.ALARM_SERVICE) as AlarmManager

                val alarmClockInfo = AlarmManager.AlarmClockInfo(calendar.timeInMillis, getAlarmInfoPendingIntent())

                alarmManager.setAlarmClock(alarmClockInfo, getAlarmActionPendingIntent())
                Toast.makeText(requireContext(), "Будильник установлен на ${simpleDateFormat.format(calendar.time)}", Toast.LENGTH_LONG).show()
            }

            materialTimePicker.show(childFragmentManager, "picker")
        }
    }

    private fun getAlarmInfoPendingIntent(): PendingIntent? {
        val alarmInfoIntent = Intent(requireContext(), MainFragment::class.java)
        alarmInfoIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        return PendingIntent.getActivity(requireContext(), 0, alarmInfoIntent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    private fun getAlarmActionPendingIntent(): PendingIntent? {
        var intent = Intent(requireContext(), AlarmActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK
        return PendingIntent.getActivity(requireContext(), 1, intent, PendingIntent.FLAG_UPDATE_CURRENT)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}