package whitekr.chatservice

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import whitekr.chatservice.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {

	private var mBinding: ActivityChatBinding? = null
	private val binding get() = mBinding!!

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		mBinding = ActivityChatBinding.inflate(layoutInflater)
		setContentView(binding.root)

		val name: String = intent.getStringExtra("name").toString()

	}
}