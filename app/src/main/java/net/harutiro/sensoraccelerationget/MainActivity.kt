package net.harutiro.sensoraccelerationget

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity(), SensorEventListener {

    private var sensorManager: SensorManager? = null
    private var textViewAccel: TextView? = null
    private var textViewGyro: TextView? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Get an instance of the SensorManager
        sensorManager = getSystemService(SENSOR_SERVICE) as SensorManager

        // Get an instance of the TextView
        textViewAccel = findViewById(R.id.text_view_accel)
        textViewGyro = findViewById(R.id.text_view_gyro)

    }

    override fun onResume() {
        super.onResume()
        // Listenerの登録
        val accel = sensorManager?.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)
        val gyro = sensorManager?.getDefaultSensor(Sensor.TYPE_GYROSCOPE)

        sensorManager?.registerListener(this, accel, SensorManager.SENSOR_DELAY_NORMAL)
        sensorManager?.registerListener(this, gyro, SensorManager.SENSOR_DELAY_UI)
    }

    // 解除するコードも入れる!
    override fun onPause() {
        super.onPause()
        // Listenerを解除
        sensorManager!!.unregisterListener(this)
    }

    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_ACCELEROMETER) {
            val sensorX = event.values[0]
            val sensorY = event.values[1]
            val sensorZ = event.values[2]
            val strTmp =
                    "加速度センサー\n " +
                    "X: $sensorX \n" +
                    "Y: $sensorY\n" +
                    "Z: $sensorZ\n"


            textViewAccel!!.text = strTmp
        }

        if (event.sensor.type == Sensor.TYPE_GYROSCOPE) {
            val sensorX = event.values[0]
            val sensorY = event.values[1]
            val sensorZ = event.values[2]
            val strTmp =
                        "ジャイロセンサー(角速度)\n " +
                        "X: $sensorX \n" +
                        "Y: $sensorY\n" +
                        "Z: $sensorZ\n"

            textViewGyro!!.text = strTmp
        }

    }


    override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
}