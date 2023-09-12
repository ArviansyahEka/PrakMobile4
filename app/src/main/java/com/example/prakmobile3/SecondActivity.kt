package com.example.prakmobile3

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import com.example.prakmobile3.MainActivity.Companion.EXTRA_NAME
import com.example.prakmobile3.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val name = intent.getStringExtra(MainActivity.EXTRA_NAME)
        with(binding){
            btnToThirdActivity.setOnClickListener {
                val intent = Intent(this@SecondActivity, ThirdActivity::class.java)
                    .apply { putExtra(EXTRA_NAME,name) }
                launcher.launch(intent)
            }

            txtName.text = name
        }
    }
    private val launcher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
                result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val data = result.data
                val name = data?.getStringExtra(EXTRA_NAME)
                val address = data?.getStringExtra(EXTRA_ADDRESS)
                binding.txtName.text = "$name beralamat di $address"
            }
        }
    companion object{
        const val EXTRA_ADDRESS = "extra_address"
    }

}