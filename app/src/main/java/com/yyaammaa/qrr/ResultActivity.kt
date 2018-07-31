package com.yyaammaa.qrr

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView

class ResultActivity : AppCompatActivity() {

    companion object {
        private val SCANNED_TEXT = "scanned_text"

        fun createIntent(context: Context, text: String): Intent {
            val intent = Intent(context, ResultActivity::class.java)
            intent.putExtra(SCANNED_TEXT, text)
            return intent
        }
    }

    private lateinit var textView: TextView
    private lateinit var shareButton: Button
    private lateinit var viewButton: Button

    private var scannedText: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        scannedText = intent.getStringExtra(SCANNED_TEXT)
        initView()
    }

    private fun initView() {
        textView = findViewById(R.id.activity_result_text)
        textView.text = scannedText

        shareButton = findViewById(R.id.activity_result_share_button)
        shareButton.setOnClickListener {
            val sendIntent = Intent()
            sendIntent.action = Intent.ACTION_SEND
            sendIntent.putExtra(Intent.EXTRA_TEXT, scannedText)
            sendIntent.type = "text/plain"
            startActivity(Intent.createChooser(sendIntent, "Share to"))
        }

        viewButton = findViewById(R.id.activity_result_view_button)
        if (scannedText.startsWith("http")) {
            viewButton.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(scannedText)))
            }
        } else {
            viewButton.visibility = View.INVISIBLE
        }
    }
}
