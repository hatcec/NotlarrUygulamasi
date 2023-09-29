package com.example.notlarruygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.notlarruygulamasi.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private  lateinit var notlarListe:ArrayList<Notlar>
    private lateinit var  adapter:NotlarAdapter
    private lateinit var  vt:VeriTabaniYardimcisi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbar.title="Notlar Uygulaması"

        setSupportActionBar(binding.toolbar)
        binding.rv.setHasFixedSize(true)//düzgün görünmesi
        binding.rv.layoutManager=LinearLayoutManager(this)
/*
        notlarListe=ArrayList()
        val n1=Notlar(1,"Tarih", 70 ,50)
        val n2=Notlar(2,"Kimya", 65 ,80)
        val n3=Notlar(3,"Fizik", 45 ,55)
        notlarListe.add(n1)
        notlarListe.add(n2)
        notlarListe.add(n3)*/
        vt=VeriTabaniYardimcisi(this)
        notlarListe=Notlardao().tumNotlar(vt)

        adapter=NotlarAdapter(this, notlarListe)//tasarimi bağladı adapterden
        binding.rv.adapter=adapter
        var toplam=0
        for(n in notlarListe){
            toplam=toplam+(n.not1+n.not2)/2
        }
        if (toplam!=0){
            binding.toolbar.subtitle="Ortalama : ${toplam/notlarListe.size}"
        }
        binding.fab.setOnClickListener{
            startActivity(Intent(this@MainActivity, NotKayitActivity::class.java))
        }

    }

    override fun onBackPressed() {
        val intent=Intent(Intent.ACTION_MAIN)//ana intentt
        intent.addCategory(Intent.CATEGORY_HOME)//ana ekrana dönecek
        intent.flags=Intent.FLAG_ACTIVITY_NEW_TASK//yeni görev
        startActivity(intent)
    }
}