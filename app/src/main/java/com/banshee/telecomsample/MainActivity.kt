package com.banshee.telecomsample

import android.Manifest
import android.content.ComponentName
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.telecom.TelecomManager
import android.view.Menu
import android.view.MenuItem
import android.view.View

import kotlinx.android.synthetic.main.activity_main.*
import android.Manifest.permission
import android.Manifest.permission.READ_PHONE_STATE
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        val permissionCheck = ActivityCompat.requestPermissions(
            this,
            arrayOf(
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.CALL_PHONE,
                Manifest.permission.MANAGE_OWN_CALLS,
                Manifest.permission.READ_CALL_LOG
            ),
            0
        )
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun registerAccount(button: View) {
        SampleConnectionService.register(this)
    }

    fun startOutboundCall(button: View) {
        val callBundle = Bundle();
        callBundle.putString(TelecomManager.EXTRA_PHONE_ACCOUNT_HANDLE, "SampleConnectionService");
        var uri = android.net.Uri.fromParts("tel", "1234", null);
        val telecomManager = SampleConnectionService.telecomManager(this)
        telecomManager.placeCall(uri, callBundle);
    }

    fun startInboundCall(button: View) {
    }

    fun connectInboundCall(button: View) {

    }

    fun connectOutboundCall(button: View) {

    }

    fun disconnectCall(button: View) {

    }
}
