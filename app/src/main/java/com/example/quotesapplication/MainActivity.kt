package com.example.quotesapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var mainViewModel: MainViewModel
    private  val txt1:TextView
        get()=findViewById(R.id.txt1)
    private  val txt2:TextView
        get()=findViewById(R.id.txt2)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainViewModel=ViewModelProvider(this,MainViewModelFactory(application)).get(MainViewModel::class.java)
        setquote(mainViewModel.getQuote())
    }
    fun setquote(quote: Quote){
        txt1.text=quote.text
        txt2.text=quote.author

    }

    fun onPrevious(view: View) {
        setquote(mainViewModel.previousQuote())
    }
    fun onNext(view: View) {
        setquote(mainViewModel.nextQuote())
    }
    fun onShare(view: View) {
        val intent=Intent(Intent.ACTION_SEND)
        intent.setType("text/plain")
        intent.putExtra(Intent.EXTRA_TEXT,mainViewModel.getQuote().text)
        startActivity(intent)
    }
}