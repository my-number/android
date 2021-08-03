package dev.mynumber.connect.android.reader

import android.app.Activity
import android.content.Intent
import android.nfc.NfcAdapter

import android.content.IntentFilter

import android.nfc.tech.NfcB

import android.nfc.tech.NfcA

import android.nfc.tech.NfcF

import android.app.PendingIntent
import android.content.Context
import android.nfc.NfcManager
import android.nfc.Tag
import android.nfc.tech.IsoDep
import android.util.Log
import android.widget.Toast
import java.lang.Error
import java.lang.Exception


class MynaManager {
    private var pendingIntent: PendingIntent? = null;

    var activity: Activity? = null;
    var adapter: NfcAdapter? = null;
    var nfcService: NfcManager? = null;
    fun prepareNfc(_activity: Activity) {
        activity = _activity
        val context = activity!!.applicationContext
        pendingIntent = PendingIntent.getActivity(
            context, 0, Intent(
                context,
                activity!!.javaClass
            ).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0
        )

        nfcService = activity!!.getSystemService(Context.NFC_SERVICE) as NfcManager?
        adapter = nfcService!!.defaultAdapter
    }
    val ndef = IntentFilter(NfcAdapter.ACTION_NDEF_DISCOVERED).apply {
        try {
            addDataType("*/*")    /* Handles all MIME based dispatches.
                                     You should specify only the ones that you need. */
        } catch (e: IntentFilter.MalformedMimeTypeException) {
            throw RuntimeException("fail", e)
        }
    }

    val intentFiltersArray = arrayOf(ndef)

    val techListsArray = arrayOf(arrayOf<String>(NfcB::class.java.name))

    fun enableRead() {
        val mTechList = arrayOf(
            arrayOf(NfcF::class.java.name), arrayOf(
                NfcA::class.java.name
            ), arrayOf(NfcB::class.java.name)
        )

        adapter!!.enableForegroundDispatch(activity, pendingIntent, intentFiltersArray, mTechList)
    }

    fun disableRead() {
        adapter!!.disableForegroundDispatch(activity)
    }


    fun onNewIntent(intent: Intent) {
        val action = intent.action;
        if (NfcAdapter.ACTION_TECH_DISCOVERED != action) {
            return
        }
        val tag: Tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG)!!
        val techList: Array<String> = tag.techList
        if(!techList.contains("android.nfc.tech.NfcB")){
            return
        }
        val nfcb = IsoDep.get(tag)
        readTag(nfcb)
    }

    fun readTag(nfcb: IsoDep) {
        val parser = ApduParser(nfcb)
         try {
             parser.selectDf(
                 byteArrayOf(
                     0xD3.toByte(),
                     0x92.toByte(), 0xf0.toByte(), 0x00, 0x26, 0x01, 0x00, 0x00, 0x00, 0x01
                 )
             )
         }catch(e: Error){
             Log.e("MynaManager", e.toString())
             Toast.makeText(activity, e.message, Toast.LENGTH_SHORT).show()
         }
    }


    fun setCallback(callback: () -> Unit) {

    }

    companion object {
        private var instance: MynaManager? = null;
        fun getInstance() = instance ?: synchronized(this) {
            instance ?: MynaManager().also { instance = it }
        }
    }
}