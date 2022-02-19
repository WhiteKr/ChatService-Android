package whitekr.chatservice

import android.content.Intent
import android.os.Bundle
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

            val intent = Intent(this, ChatActivity::class.java)
            intent.putExtra("name", binding.nameEditText.text.toString())
            startActivity(intent)

        }
    }
}