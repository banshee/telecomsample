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
        const val LOGTAG = "qqr SampleConnection"
        val allConnections = mutableListOf<SampleConnection>()

        fun doForOthers(exclude: SampleConnection, fn: (t: SampleConnection) -> Unit) {
            val otherConnections = allConnections.filter { it != exclude }
            otherConnections.forEach(fn)
        }

        fun setConferenceable(target: SampleConnection, items: List<SampleConnection>) {
            val otherConnections = items.filter { it != target }
            target.setConferenceableConnections(otherConnections)
        }

        fun setAllConferenceable(items: List<SampleConnection>) {
            items.forEach { setConferenceable(it, items) }
        }
    }

    init {
        Log.d(SampleConnection.LOGTAG, "${object {}.javaClass.enclosingConstructor.name}")
        this.connectionCapabilities =
                CAPABILITY_SUPPORT_HOLD
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
        doForOthers(this, { it.setOnHold() })
    }

    fun confirmed() {
        this.setActive()
        this.connectionCapabilities =
                (connectionCapabilities or Connection.CAPABILITY_HOLD or Connection.CAPABILITY_MUTE)
        this.setAddress(Uri.fromParts("tel","13242342342342", ""), PRESENTATION_ALLOWED)
        this.setCallerDisplayName("display anme  for ${this.callId}", PRESENTATION_ALLOWED)
//        Log.d(LOGTAG, "confirmed $callId ${this.connectionCapabilities}")
    }

    fun disconnect() {
        Log.d(LOGTAG, "disconnect $callId")
        this.setDisconnected(DisconnectCause(0, "button pushed"))
        this.destroy()
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

    override fun onHold() {
        super.onHold()
        this.setOnHold()
        Log.d(LOGTAG, "onHold $callId")
    }

    override fun onSeparate() {
        super.onSeparate()
        Log.d(SampleConference.LOGTAG, "${object {}.javaClass.enclosingMethod.name}")
    }
}