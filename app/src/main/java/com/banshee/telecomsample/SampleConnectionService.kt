package com.banshee.telecomsample

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.telecom.*
import android.util.Log

class SampleConnectionService : ConnectionService() {
    companion object {
        val LOGTAG = "qqr SampleConnectionService"

        fun register(context: Context) {
            var phoneAccount = phoneAccount(phoneAccountHandle(context))
            telecomManager(context).registerPhoneAccount(phoneAccount)
        }

        fun telecomManager(context: Context): TelecomManager {
            var telecomManager = context.getSystemService(Context.TELECOM_SERVICE) as TelecomManager;
            return telecomManager
        }

        fun phoneAccountHandle(context: Context): PhoneAccountHandle {
            var componentName = ComponentName(context, SampleConnectionService.javaClass.enclosingClass.canonicalName)
            var phoneAccountHandle = PhoneAccountHandle(componentName, "main")
            return phoneAccountHandle
        }

        private fun phoneAccount(phoneAccountHandle: PhoneAccountHandle): PhoneAccount? {
            var phoneAccount = PhoneAccount.Builder(phoneAccountHandle, "TelecomSampleLabel")
                .setCapabilities(PhoneAccount.CAPABILITY_CALL_PROVIDER)
                .addSupportedUriScheme("tel")
                .build()
            return phoneAccount
        }
    }

    override fun onCreateIncomingConnection(
        connectionManagerPhoneAccount: PhoneAccountHandle?,
        request: ConnectionRequest?
    ): Connection {
        Log.d(SampleConnection.LOGTAG, "onCreateIncomingConnection")

        return super.onCreateIncomingConnection(connectionManagerPhoneAccount, request)
    }

    override fun onCreateOutgoingConnection(
        connectionManagerPhoneAccount: PhoneAccountHandle?,
        request: ConnectionRequest?
    ): Connection? {
        Log.d(SampleConnection.LOGTAG, "${object {}.javaClass.enclosingMethod.name}")
        return when (request?.address.toString()) {
            "tel:501" -> MainActivity.call1
            "tel:502" -> MainActivity.call2
            else -> null
        }
    }

    override fun onCreateOutgoingConnectionFailed(
        connectionManagerPhoneAccount: PhoneAccountHandle?,
        request: ConnectionRequest?
    ) {
        Log.d(SampleConnection.LOGTAG, "${object {}.javaClass.enclosingMethod.name}")
        super.onCreateOutgoingConnectionFailed(connectionManagerPhoneAccount, request)
    }

    override fun onCreateIncomingConnectionFailed(
        connectionManagerPhoneAccount: PhoneAccountHandle?,
        request: ConnectionRequest?
    ) {
        Log.d(SampleConnection.LOGTAG, "${object {}.javaClass.enclosingMethod.name}")
        super.onCreateIncomingConnectionFailed(connectionManagerPhoneAccount, request)
    }

    override fun onConference(connection1: Connection?, connection2: Connection?) {
        Log.d(
            SampleConnection.LOGTAG,
            "${object {}.javaClass.enclosingMethod.name} ${(connection1 as SampleConnection).callId}"
        )
        super.onConference(connection1, connection2)
        val conference =
            SampleConference(telecomManager(applicationContext), phoneAccountHandle(applicationContext), "conference1")
        val connections = listOf(connection1, connection2)
        connections.filterNotNull().forEach { connection ->
            conference.addConnection(connection)
            connection.setActive()
        }
        this.addConference(conference)
    }

    override fun onCreateOutgoingHandoverConnection(
        fromPhoneAccountHandle: PhoneAccountHandle?,
        request: ConnectionRequest?
    ): Connection {
        Log.d(SampleConnection.LOGTAG, "${object {}.javaClass.enclosingMethod.name}")
        return super.onCreateOutgoingHandoverConnection(fromPhoneAccountHandle, request)
    }

    override fun onConnectionServiceFocusLost() {
        Log.d(SampleConnection.LOGTAG, "${object {}.javaClass.enclosingMethod.name}")
        super.onConnectionServiceFocusLost()
    }

    override fun onUnbind(intent: Intent?): Boolean {
        Log.d(SampleConnection.LOGTAG, "${object {}.javaClass.enclosingMethod.name}")
        return super.onUnbind(intent)
    }

    override fun onConnectionServiceFocusGained() {
        Log.d(SampleConnection.LOGTAG, "${object {}.javaClass.enclosingMethod.name}")
        super.onConnectionServiceFocusGained()
    }

    override fun onRemoteExistingConnectionAdded(connection: RemoteConnection?) {
        Log.d(SampleConnection.LOGTAG, "${object {}.javaClass.enclosingMethod.name}")
        super.onRemoteExistingConnectionAdded(connection)
    }

    override fun onHandoverFailed(request: ConnectionRequest?, error: Int) {
        Log.d(SampleConnection.LOGTAG, "${object {}.javaClass.enclosingMethod.name}")
        super.onHandoverFailed(request, error)
    }

    override fun onRemoteConferenceAdded(conference: RemoteConference?) {
        Log.d(SampleConnection.LOGTAG, "${object {}.javaClass.enclosingMethod.name}")
        super.onRemoteConferenceAdded(conference)
    }

    override fun onCreateIncomingHandoverConnection(
        fromPhoneAccountHandle: PhoneAccountHandle?,
        request: ConnectionRequest?
    ): Connection {
        Log.d(SampleConnection.LOGTAG, "${object {}.javaClass.enclosingMethod.name}")
        return super.onCreateIncomingHandoverConnection(fromPhoneAccountHandle, request)
    }


}