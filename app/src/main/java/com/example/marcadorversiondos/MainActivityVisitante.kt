package com.example.marcadorversiondos

import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView
import androidx.activity.ComponentActivity

class MainActivityVisitante : ComponentActivity() {

    var isTimerPaused = false
    var tiempoRestanteMillis: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_visitante)

        var contadorLocal: Int
        var contadorVisitante: Int
        var contadorPersonalesAtaque: Int
        var contadorPersonalesDefensa: Int

        val textViewContadorVisitante = findViewById<TextView>(R.id.textViewPantallaVisitante)
        contadorVisitante = intent.getIntExtra("contadorVisitante", 0)
        textViewContadorVisitante.text = contadorVisitante.toString()

        val btSumarUnoVisitante = findViewById<Button>(R.id.buttonMasUnoVisitante)
        val btSumarDosVisitante = findViewById<Button>(R.id.buttonMasDosVisitante)
        val btSumarTresVisitante = findViewById<Button>(R.id.buttonMasTresVisitante)
        val btRestarUnoVisitante = findViewById<Button>(R.id.buttonMenosUnoVisitante)

        btSumarUnoVisitante.setOnClickListener {
            contadorVisitante++
            textViewContadorVisitante.text = contadorVisitante.toString()
        }

        btSumarDosVisitante.setOnClickListener {
            contadorVisitante += 2
            textViewContadorVisitante.text = contadorVisitante.toString()
        }

        btSumarTresVisitante.setOnClickListener {
            contadorVisitante += 3
            textViewContadorVisitante.text = contadorVisitante.toString()
        }

        btRestarUnoVisitante.setOnClickListener {
            if (contadorVisitante > 0) {
                contadorVisitante -= 1
                textViewContadorVisitante.text = contadorVisitante.toString()
            }
        }

        val textViewPersonalesDefensa = findViewById<TextView>(R.id.textViewPersonalesDefensa)
        contadorPersonalesDefensa = intent.getIntExtra("contadorPersonalesDefensa", 0)
        textViewPersonalesDefensa.setText(contadorPersonalesDefensa.toString())

        val btRestarUnoPersonalesDefensa = findViewById<Button>(R.id.buttonMenosUnoPersonalesDefensa)
        val btSumarUnoPersonalesDefensa = findViewById<Button>(R.id.buttonMasUnoPersonalesDefensa)

        val textViewPersonalesAtaque = findViewById<TextView>(R.id.textViewPersonalesAtaque)
        contadorPersonalesAtaque = intent.getIntExtra("contadorPersonalesAtaque", 0)
        textViewPersonalesAtaque.setText(contadorPersonalesAtaque.toString())

        val btRestarUnoPersonalesAtaque = findViewById<Button>(R.id.buttonMenosUnoPersonalesAtaque)
        val btSumarUnoPersonalesAtaque = findViewById<Button>(R.id.buttonMasUnoPersonalesAtaque)

        btRestarUnoPersonalesAtaque.setOnClickListener {
            if (contadorPersonalesAtaque > 0) {
                contadorPersonalesAtaque -= 1
                textViewPersonalesAtaque.text = contadorPersonalesAtaque.toString()
            }
        }

        btSumarUnoPersonalesAtaque.setOnClickListener {
            contadorPersonalesAtaque++
            textViewPersonalesDefensa.text = contadorPersonalesAtaque.toString()
        }

        btRestarUnoPersonalesDefensa.setOnClickListener {
            if (contadorPersonalesDefensa > 0) {
                contadorPersonalesDefensa -= 1
                textViewPersonalesDefensa.text = contadorPersonalesDefensa.toString()
            }
        }

        btSumarUnoPersonalesDefensa.setOnClickListener {
            contadorPersonalesDefensa++
            textViewPersonalesAtaque.text = contadorPersonalesDefensa.toString()
        }

        val btBorrarPersonales = findViewById<Button>(R.id.buttonBorrarPersonales)

        btBorrarPersonales.setOnClickListener {
            contadorPersonalesAtaque = 0
            contadorPersonalesDefensa = 0

            textViewPersonalesAtaque.text = contadorPersonalesAtaque.toString()
            textViewPersonalesDefensa.text = contadorPersonalesDefensa.toString()
        }

        val btBorrarTodo = findViewById<Button>(R.id.buttonBorrarTodo)

        btBorrarTodo.setOnClickListener {
            contadorVisitante = 0
            contadorPersonalesAtaque = 0
            contadorPersonalesDefensa = 0

            textViewContadorVisitante.text = contadorVisitante.toString()
            textViewPersonalesAtaque.text = contadorPersonalesAtaque.toString()
            textViewPersonalesDefensa.text = contadorPersonalesDefensa.toString()
        }

        val textViewContadorLocal = findViewById<TextView>(R.id.textViewPantallaLocal)
        contadorLocal = intent.getIntExtra("contadorLocal", 0)
        textViewContadorLocal.setText(contadorLocal.toString())

        val btCambiarPosesionVisitante = findViewById<Button>(R.id.buttonCambioPosesionVisitante)

        btCambiarPosesionVisitante.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("contadorLocal", contadorLocal)
            intent.putExtra("contadorVisitante", contadorVisitante)
            intent.putExtra("contadorPersonalesAtaque", contadorPersonalesAtaque)
            intent.putExtra("contadorPersonalesDefensa", contadorPersonalesDefensa)
            startActivity(intent)
        }

        var countDownTimer: CountDownTimer? = null
        val textViewTiempo = findViewById<TextView>(R.id.textViewTiempo)

        val initialTimeInMinutes = 0.4

        val initialTimeInMillis = (initialTimeInMinutes * 60 * 1000).toLong()

        countDownTimer = object : CountDownTimer(initialTimeInMillis, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                tiempoRestanteMillis = millisUntilFinished
                val segundosActuales = millisUntilFinished / 1000
                val formattedTime = String.format("%02d:%02d", segundosActuales / 60, segundosActuales % 60)
                textViewTiempo.text = formattedTime
            }

            override fun onFinish() {
                textViewTiempo.text = "00:00"
            }
        }

        countDownTimer.start()

        val btCatorce = findViewById<Button>(R.id.buttonCatorce)

        btCatorce.setOnClickListener {

            countDownTimer?.cancel()

            val nuevoTiempoEnSegundos = 14
            val nuevoTiempoEnMillis = nuevoTiempoEnSegundos * 1000L

            val nuevoCountDownTimer = object : CountDownTimer(nuevoTiempoEnMillis, 1000) {

                override fun onTick(millisUntilFinished: Long) {
                    tiempoRestanteMillis = millisUntilFinished
                    val segundosActuales = millisUntilFinished / 1000
                    val formattedTime = String.format("%02d:%02d", segundosActuales / 60, segundosActuales % 60)
                    textViewTiempo.text = formattedTime
                }

                override fun onFinish() {
                    textViewTiempo.text = "00:00"
                }
            }

            countDownTimer = nuevoCountDownTimer
            nuevoCountDownTimer.start()

        }

        val btPausa = findViewById<Button>(R.id.buttonPausa)


        btPausa.setOnClickListener{
            if (!isTimerPaused) {
                tiempoRestanteMillis = tiempoRestanteMillis
                countDownTimer?.cancel()
                isTimerPaused = true
            }
        }

        val btReanudar = findViewById<Button>(R.id.buttonPlay)

        btReanudar.setOnClickListener {

            isTimerPaused = false

            countDownTimer = object : CountDownTimer(tiempoRestanteMillis, 1000) {
                override fun onTick(millisUntilFinished: Long) {
                    tiempoRestanteMillis = millisUntilFinished
                    val minutosActuales = millisUntilFinished / (60 * 1000)
                    val seconds = (millisUntilFinished % (60 * 1000)) / 1000

                    val formattedTime = String.format("%02d:%02d", minutosActuales, seconds)
                    textViewTiempo.text = formattedTime
                }

                override fun onFinish() {
                    textViewTiempo.text = "00:00"
                }
            }
            countDownTimer?.start()
        }
    }
}