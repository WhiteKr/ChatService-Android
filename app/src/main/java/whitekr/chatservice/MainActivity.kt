package whitekr.chatservice

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import whitekr.chatservice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

	private lateinit var mBinding: ActivityMainBinding
	private val binding: ActivityMainBinding get() = mBinding

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)

		mBinding = ActivityMainBinding.inflate(layoutInflater)
		setContentView(binding.root)

		binding.mainButton.setOnClickListener {

			val name: String = binding.mainEditText.text.toString()
			if (name.isEmpty()) {
				binding.mainEditText.error = "Name is required."
				return@setOnClickListener
			}

			val intent = Intent(this, ChatActivity::class.java)
			intent.putExtra("name", name)
			startActivity(intent)

		}
	}
}