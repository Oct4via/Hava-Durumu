package com.example.havaa_durumu

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.havaa_durumu.iml.WeatherListener
import com.example.havadurumuv10.havadurumubilgisi.weatherDataManager
import kotlinx.android.synthetic.main.activity_show_weather.*


class show_weather : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_show_weather)
        var button: Button? = null
        var mapButton: Button? = null
        button = findViewById(R.id.dButton1)
        mapButton = findViewById(R.id.mapButton)

        if(Const.SWITCH_CASE == 0) {getDataStart()}

        mapButton.setOnClickListener{
            val intent = Intent(this,MapActivity::class.java)
            startActivity(intent)
        }


    }
fun getDataStart(){
    var dm  = weatherDataManager()
    dm.setWeatherListener(object : WeatherListener {

        override fun execute(data: String) {

            findViewById<TextView>(R.id.textView).setText(data)

            Glide.with(this@show_weather)
                .load(Const.STATUS_MAP.get(Const.CURRENT_STATUS))
                .placeholder(R.drawable.ic_launcher_foreground)
                .into(wimg1)
                dButton2.setText(Const.DAYS_OF_WEEK1)
                dButton3.setText(Const.DAYS_OF_WEEK2)
                dButton4.setText(Const.DAYS_OF_WEEK3)
                dButton5.setText(Const.DAYS_OF_WEEK4)
                dButton6.setText(Const.DAYS_OF_WEEK5)
                dButton7.setText(Const.DAYS_OF_WEEK6)
        }
    })

    dm.getData(Const.CURRENT_CITY)
}
    fun getDataClone(){
        var dm  = weatherDataManager()
        dm.setWeatherListener(object : WeatherListener {

            override fun execute(data: String) {

                findViewById<TextView>(R.id.textView).setText(data)

                Glide.with(this@show_weather)
                    .load(Const.STATUS_MAP.get(Const.CURRENT_STATUS))
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(wimg1)

            }
        })

        dm.getData(Const.CURRENT_CITY)
    }

    fun DayChooseOnClick(view: View) {
        Const.SWITCH_CASE = view.tag.toString().toInt() -1
        getDataClone()
    }


}