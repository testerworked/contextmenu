package com.sample.contextmenu

//import android.graphics.Color
import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.ui.graphics.Color
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    companion object{
        const val ITEM_ON = 111
        const val ITEM_CHECK = 113
        const val ITEM_OFF = 112
    }

    private lateinit var textET : EditText
    private lateinit var randButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        textET = findViewById(R.id.textET)
        registerForContextMenu(textET)

        randButton = findViewById(R.id.buttonRandom)

        registerForContextMenu(textET)

        randButton.setOnClickListener {
            val randomScore = (1..50).random()
            textET.setText(randomScore.toString())
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        menu?.add(Menu.NONE, ITEM_ON, Menu.NONE, "Цветовое качество1")
        menu?.add(Menu.NONE, ITEM_CHECK, Menu.NONE, "Цветовое качество2")
        menu?.add(Menu.NONE, ITEM_OFF, Menu.NONE, "Выход из приложения")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var message = ""
        when (item.itemId) {
            ITEM_ON -> {
                message = "Пункт меню 'Цветовое качество1'"
                val score = textET.text.toString().toIntOrNull()
                if (score != null) {
                    setColorBasedOnScoreFirst(score)
                } else {
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }
            }
            ITEM_CHECK -> {
                message = "Пункт меню 'Цветовое качество2'"
                val score = textET.text.toString().toIntOrNull()
                if (score != null) {
                    setColorBasedOnScore(score)
                } else {
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                }
            }

            ITEM_OFF -> {
                message = "Пункт меню 'Выход из приложения'"
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
                finish()
            }

            else -> {
                return super.onContextItemSelected(item)
            }
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        return true
    }

    private fun setColorBasedOnScore(score: Int) {
        when (score) {
            in 1..10 -> textET.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
            in 11..20 -> textET.setBackgroundColor(ContextCompat.getColor(this, R.color.orange))
            in 21..30 -> textET.setBackgroundColor(ContextCompat.getColor(this, R.color.yellow))
            in 31..40 -> textET.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
            in 41..50 -> textET.setBackgroundColor(ContextCompat.getColor(this, R.color.blue))
            else -> {
                textET.setBackgroundColor(ContextCompat.getColor(this, R.color.turquoise))
            }
        }
    }

    fun setColorBasedOnScoreFirst(score: Int){
        when (score) {
            1 -> textET.setBackgroundColor(ContextCompat.getColor(this, R.color.orange))
            2 -> textET.setBackgroundColor(ContextCompat.getColor(this, R.color.yellow))
            3 -> textET.setBackgroundColor(ContextCompat.getColor(this, R.color.green))
            4 -> textET.setBackgroundColor(ContextCompat.getColor(this, R.color.blue))
            5 -> textET.setBackgroundColor(ContextCompat.getColor(this, R.color.red))
            else -> textET.setBackgroundColor(ContextCompat.getColor(this, R.color.turquoise))
        }
    }

}