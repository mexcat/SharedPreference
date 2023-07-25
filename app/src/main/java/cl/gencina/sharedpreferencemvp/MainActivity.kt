
package cl.gencina.sharedpreferencemvp

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import cl.gencina.sharedpreferencemvp.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var sharedPreferences:SharedPreferences
    private lateinit var binding :ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        initListeners()
        setContentView(binding.root)
    }

    private fun initListeners() {
        sharedPreferences = getSharedPreferences("MainActivityData", Context.MODE_PRIVATE)

        binding.btnMostrar.setOnClickListener {
            mostrarDatos()
        }

        binding.btnSave.setOnClickListener {
            val entero = binding.etNEntero.text.toString().toInt()
            val decimal = binding.etDecimal.text.toString().toFloat()
            val texto = binding.etText.text.toString()
            val boolean = binding.swBool.isChecked

            saveData(texto,decimal,entero, boolean )
        }

        binding.btnBorrar.setOnClickListener {
            deleteSharedPreference()
            clean()
        }
    }

    private fun mostrarDatos() {
        binding.tvText.text = sharedPreferences.getString("miTexto", "")
        binding.tvDecimal.text = sharedPreferences.getFloat("miDecimal", 0F).toString()
        binding.tvNEntero.text = sharedPreferences.getInt("miEntero", 0).toString()
        binding.tvSwitch.text = sharedPreferences.getBoolean("miBolean", false).toString()
        binding.swBool.isChecked = sharedPreferences.getBoolean("miBolean", false)
    }


    private fun saveData(texto: String, decimal: Float, entero: Int, boolean: Boolean) {
       sharedPreferences.edit().putString("miTexto", texto).apply()
       sharedPreferences.edit().putFloat("miDecimal", decimal).apply()
       sharedPreferences.edit().putInt("miEntero", entero).apply()
       sharedPreferences.edit().putBoolean("miBolean", boolean).apply()
        clean()
    }

    private fun deleteSharedPreference(){
        sharedPreferences.edit().clear().apply()
    }

    private fun clean(){
        binding.etNEntero.setText("")
        binding.etDecimal.setText("")
        binding.etText.setText("")

        binding.tvNEntero.text = ""
        binding.tvDecimal.text = ""
        binding.tvText.text = ""
        binding.tvSwitch.text = ""
        binding.swBool.isChecked = false
    }

}