package whitekr.chatservice

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import whitekr.chatservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var mBinding: ActivityMainBinding? = null
    private val binding get() = mBinding!!

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.confirmButton.setOnClickListener {

            Toast.makeText(this, binding.nameEditText.text, Toast.LENGTH_SHORT).show()

        }
    }
}