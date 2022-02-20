package whitekr.chatservice

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import io.socket.client.IO
import io.socket.client.Socket
import io.socket.client.Socket.*
import io.socket.emitter.Emitter
import io.socket.engineio.client.transports.WebSocket
import whitekr.chatservice.databinding.ActivityChatBinding

class ChatActivity : AppCompatActivity() {

	private var mBinding: ActivityChatBinding? = null
	private val binding: ActivityChatBinding get() = mBinding!!

	private lateinit var name: String
	private lateinit var socket: Socket

	override fun onCreate(savedInstanceState: Bundle?) {

		super.onCreate(savedInstanceState)
		mBinding = ActivityChatBinding.inflate(layoutInflater)
		setContentView(binding.root)

		name = intent.getStringExtra("name").toString()

		initSocket()

		socket.connect()
		Log.e("Socket connection", "${socket.connected()}")

	}

	private fun initSocket() {

		try {
			val opts: IO.Options = IO.Options().also {

				it.transports = arrayOf(WebSocket.NAME)
				it.forceNew = true

			}
			socket = IO.socket("http://192.168.123.101:3000/?name=$name", opts)

			socket.on("join", onJoin)
				.on("left", onLeft)
				.on("message", onMessage)

				.on(EVENT_CONNECT, onConnect)
				.on(EVENT_DISCONNECT, onDisconnect)

				.on("connect_timeout", onError)
				.on(EVENT_CONNECT_ERROR, onError)

			binding.chatImageButton.setOnClickListener {

				val string: String = binding.chatEditText.text.toString()
				if (string === "") return@setOnClickListener

				send(string)

			}
		} catch (e: Exception) {

			e.printStackTrace()
			Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show()
			finish()

		}
	}

	private fun send(message: String) = socket.emit("message", "${name}: $message")

	private val onJoin = Emitter.Listener {
		runOnUiThread { Toast.makeText(this, "${it[0]}", Toast.LENGTH_SHORT).show() }
	}

	private val onLeft = Emitter.Listener {
		runOnUiThread { Toast.makeText(this, "${it[0]}", Toast.LENGTH_SHORT).show() }
	}

	private val onMessage = Emitter.Listener {
		runOnUiThread { Toast.makeText(this, "${it[0]}", Toast.LENGTH_SHORT).show() }
	}

	private val onConnect = Emitter.Listener {
		Log.d("Socket Connect", "Connected! ID: ${socket.id()}")
	}

	private val onDisconnect = Emitter.Listener {
		runOnUiThread {

			Toast.makeText(this, "Disconnected from server.", Toast.LENGTH_SHORT).show()
			finish()

		}
	}

	private val onError = Emitter.Listener {
		runOnUiThread {

			Log.e("Socket Error", it[0].toString())
			Toast.makeText(this, it[0].toString(), Toast.LENGTH_SHORT).show()
			finish()

		}
	}

	override fun onDestroy() {

		super.onDestroy()
		socket.disconnect()
		socket.off()

	}
}