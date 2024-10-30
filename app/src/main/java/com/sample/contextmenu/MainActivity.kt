package com.sample.contextmenu

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    companion object{
        const val ITEM_ON = 111
        const val ITEM_OFF = 112
    }

    private lateinit var textET : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        textET = findViewById(R.id.textET)
        registerForContextMenu(textET)

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
        menu?.add(Menu.NONE, ITEM_ON, Menu.NONE, "Цветовое качество")
        menu?.add(Menu.NONE, ITEM_OFF, Menu.NONE, "Выход из приложения")
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        var message = ""
        when (item.itemId) {
            ITEM_ON -> {
                message = "Пункт меню 'Цветовое качество'"
                textET.text.clear()
            }

            ITEM_OFF -> {
                message = "Пункт меню 'Выход из приложения'"
                finish()
            }

            else -> {
                return super.onContextItemSelected(item)
            }
        }
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
        return true
    }

}