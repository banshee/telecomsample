package com.banshee.telecomsample

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.telecom.Connection
import android.telecom.DisconnectCause
import android.telecom.PhoneAccountHandle
import android.telecom.TelecomManager
import android.telecom.TelecomManager.PRESENTATION_ALLOWED
import android.util.Log

class SampleConnection(
    val telecomManager: TelecomManager,
    val phoneAccountHandle: PhoneAccountHandle,
    val callId: String
) : Connection() {
    companion object {
        val LOGTAG = "sqq"
        val allConnections = mutableListOf<SampleConnection>()

        fun setConferenceable(target: SampleConnection, items: List<SampleConnection>) {
            val otherConnections = items.filter { it != target }
            target.setConferenceableConnections(otherConnections)
        }

        fun setAllConferenceable(items: List<SampleConnection>) {
            items.forEach { setConferenceable(it, items) }
        }
    }

    init {
        println("qqr SampleConnection#constructor")
        this.connectionCapabilities =
                CAPABILITY_HOLD
        setCallerDisplayName("Seamus Ó Muradh", PRESENTATION_ALLOWED)
        setInitialized()
        allConnections.add(this)
        setAllConferenceable(allConnections)
    }

    @SuppressLint("MissingPermission")
    fun inboundCall() {
    }

    @SuppressLint("MissingPermission")
    fun outboundCall() {
        Log.d(LOGTAG, "outboundCall $callId")
        val uri = Uri.fromParts("tel", callId, null)
        val otherExtras = Bundle()
        otherExtras.putString("sample", this.callId)
        val callBundle = Bundle()
        callBundle.putParcelable(TelecomManager.EXTRA_PHONE_ACCOUNT_HANDLE, phoneAccountHandle)
        callBundle.putParcelable(TelecomManager.EXTRA_OUTGOING_CALL_EXTRAS, otherExtras)
        telecomManager.placeCall(uri, callBundle)
        this.setRinging()
        this.setAddress(uri, PRESENTATION_ALLOWED)
    }

    fun confirmed() {
        Log.d(LOGTAG, "confirmed $callId")
        this.setActive()
        this.connectionCapabilities = connectionCapabilities or Connection.CAPABILITY_HOLD
    }

    fun disconnect() {
        Log.d(LOGTAG, "disconnect $callId")
        this.setDisconnected(DisconnectCause(0, "button pushed"))
    }

    override fun onAnswer() {
        Log.d(LOGTAG, "onAnswer $callId")
        super.onAnswer()
    }

    override fun onDisconnect() {
        Log.d(LOGTAG, "onDisconnect $callId")
        super.onDisconnect()
        this.setDisconnected(DisconnectCause(0, "on disconnect"))
    }

    override fun onStateChanged(state: Int) {
        Log.d(LOGTAG, "onStateChanged $callId $state")
        super.onStateChanged(state)
    }

    override fun onShowIncomingCallUi() {
        Log.d(LOGTAG, "onShowIncomingCallUi $callId")
        super.onShowIncomingCallUi()
    }
}