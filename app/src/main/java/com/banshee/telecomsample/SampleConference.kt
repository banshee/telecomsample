package com.banshee.telecomsample

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Bundle
import android.telecom.*
import android.telecom.Call.Details.CAPABILITY_MANAGE_CONFERENCE
import android.telecom.TelecomManager.PRESENTATION_ALLOWED
import android.util.Log

class SampleConference(
    val telecomManager: TelecomManager,
    phoneAccountHandle: PhoneAccountHandle,
    val callId: String
) : Conference(phoneAccountHandle) {
    companion object {
        const val LOGTAG = "qqr SampleConference"
    }

    init {
        Log.d(SampleConference.LOGTAG, "${object{}.javaClass.enclosingConstructor.name}")
        this.connectionCapabilities =
                CAPABILITY_MANAGE_CONFERENCE
        this.setActive()
    }

    override fun onUnhold() {
        Log.d(SampleConference.LOGTAG, "${object{}.javaClass.enclosingMethod.name}")
        super.onUnhold()
    }

    override fun onCallAudioStateChanged(state: CallAudioState?) {
        Log.d(SampleConference.LOGTAG, "${object{}.javaClass.enclosingMethod.name}")
        super.onCallAudioStateChanged(state)
    }

    override fun onConnectionAdded(connection: Connection?) {
        Log.d(SampleConference.LOGTAG, "${object{}.javaClass.enclosingMethod.name}")
        super.onConnectionAdded(connection)
    }

    override fun onExtrasChanged(extras: Bundle?) {
        Log.d(SampleConference.LOGTAG, "${object{}.javaClass.enclosingMethod.name}")
        super.onExtrasChanged(extras)
    }

    override fun onDisconnect() {
        Log.d(SampleConference.LOGTAG, "${object{}.javaClass.enclosingMethod.name}")
        this.setDisconnected(DisconnectCause(0))
        this.connections.forEach {
            it.setOnHold();
            this.removeConnection(it)
        }
        super.onDisconnect()
    }

    override fun onPlayDtmfTone(c: Char) {
        Log.d(SampleConference.LOGTAG, "${object{}.javaClass.enclosingMethod.name}")
        super.onPlayDtmfTone(c)
    }

    override fun onSeparate(connection: Connection?) {
        Log.d(SampleConference.LOGTAG, "${object{}.javaClass.enclosingMethod.name}")
        super.onSeparate(connection)
    }

    override fun onHold() {
        Log.d(SampleConference.LOGTAG, "${object{}.javaClass.enclosingMethod.name}")
        super.onHold()
    }

    override fun onStopDtmfTone() {
        Log.d(SampleConference.LOGTAG, "${object{}.javaClass.enclosingMethod.name}")
        super.onStopDtmfTone()
    }

    override fun onMerge(connection: Connection?) {
        Log.d(SampleConference.LOGTAG, "${object{}.javaClass.enclosingMethod.name}")
        super.onMerge(connection)
    }

    override fun onMerge() {
        Log.d(SampleConference.LOGTAG, "${object{}.javaClass.enclosingMethod.name}")
        super.onMerge()
    }

    override fun onSwap() {
        Log.d(SampleConference.LOGTAG, "${object{}.javaClass.enclosingMethod.name}")
        super.onSwap()
    }
}