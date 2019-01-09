package com.banshee.telecomsample

import android.telecom.Connection
import android.telecom.TelecomManager.PRESENTATION_ALLOWED

class SampleConnection: Connection {
    constructor() : super() {
        println("qqr SampleConnection#constructor")
        this.connectionCapabilities =
                CAPABILITY_HOLD
        setCallerDisplayName("Seamus Ó Muradh", PRESENTATION_ALLOWED)
    }

    override fun onAnswer() {
        super.onAnswer()
    }

    override fun onDisconnect() {
        super.onDisconnect()
    }

    override fun onShowIncomingCallUi() {
        super.onShowIncomingCallUi()
    }
}