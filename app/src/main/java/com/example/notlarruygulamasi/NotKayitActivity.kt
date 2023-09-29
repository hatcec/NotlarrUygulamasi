package com.example.notlarruygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import com.example.notlarruygulamasi.databinding.ActivityNotKayitBinding
import com.google.android.material.snackbar.Snackbar

class NotKayitActivity : AppCompatActivity() {
    private lateinit var binding:ActivityNotKayitBinding
    private lateinit var  vt:VeriTabaniYardimcisi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityNotKayitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        vt=VeriTabaniYardimcisi(this)
        binding.toolbarNotKayit.title="Not Kayıt"
        setSupportActionBar(binding.toolbarNotKayit)
      binding.buttonKaydet.setOnClickListener{
          val dersAdi=binding.editTextDers.text.toString().trim()
          val not1=binding.editTextNot1.text.toString().trim()
          val not2=binding.editTextNot2.text.toString().trim()
          if(TextUtils.isEmpty(dersAdi)){
              Snackbar.make(binding.toolbarNotKayit,"ders adı giriniz", Snackbar.LENGTH_LONG).show()
              return@setOnClickListener//geri kalan kodlamaya bakmıcak
          }
          if(TextUtils.isEmpty(not1)){
              Snackbar.make(binding.toolbarNotKayit,"1. notu giriniz", Snackbar.LENGTH_LONG).show()
              return@setOnClickListener
          }
          if(TextUtils.isEmpty(not2)){
              Snackbar.make(binding.toolbarNotKayit,"2. notu giriniz", Snackbar.LENGTH_LONG).show()
              return@setOnClickListener
          }
          Notlardao().notEkle(vt, dersAdi,not1.toInt(), not2.toInt())
          startActivity(Intent(this@NotKayitActivity, MainActivity::class.java))
          finish()
      }
    }

}