package com.example.notlarruygulamasi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import com.example.notlarruygulamasi.databinding.ActivityDetayBinding
import com.google.android.material.snackbar.Snackbar

class DetayActivity : AppCompatActivity() {
    private lateinit var binding:ActivityDetayBinding
    private lateinit var not:Notlar
    private lateinit var vt:VeriTabaniYardimcisi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityDetayBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.toolbarNotDetay.title="Not Detay"
        setSupportActionBar(binding.toolbarNotDetay)
        vt= VeriTabaniYardimcisi(this)
         not=intent.getSerializableExtra("nesne")as Notlar
        binding.editTextDers.setText(not.ders_adi)
        binding.editTextNot1.setText((not.not1).toString())
        binding.editTextNot2.setText((not.not2).toString())
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_sil->{
               Snackbar.make(binding.toolbarNotDetay,"Silinsin mi?", Snackbar.LENGTH_LONG)
                   .setAction("EVET"){
                       Notlardao().notSil(vt,not.not_id)
                       startActivity(Intent(this@DetayActivity, MainActivity::class.java))
                       finish()
                   }.show()
                return true
            }
            R.id.action_duzenle->{
                val dersAdi=binding.editTextDers.text.toString().trim()
                val not1=binding.editTextNot1.text.toString().trim()
                val not2=binding.editTextNot2.text.toString().trim()
                if(TextUtils.isEmpty(dersAdi)){
                    Snackbar.make(binding.toolbarNotDetay,"ders adı giriniz", Snackbar.LENGTH_LONG).show()

                    return false
                }
                if(TextUtils.isEmpty(not1)){
                    Snackbar.make(binding.toolbarNotDetay,"1. notu giriniz", Snackbar.LENGTH_LONG).show()
                    return false
                }
                if(TextUtils.isEmpty(not2)){
                    Snackbar.make(binding.toolbarNotDetay,"2. notu giriniz", Snackbar.LENGTH_LONG).show()
                    return false
                }
                Notlardao().notGuncelle(vt,not.not_id, dersAdi, not1.toInt(), not2.toInt())
                startActivity(Intent(this@DetayActivity, MainActivity::class.java))
                finish()
                return true
            }
            else->return false
        }
        return super.onOptionsItemSelected(item)
    }
}