package com.banshee.telecomsample

import android.content.ComponentName
import android.content.Context
import android.telecom.*

class SampleConnectionService : ConnectionService() {
    companion object {
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
        return super.onCreateIncomingConnection(connectionManagerPhoneAccount, request)
    }

    override fun onCreateOutgoingConnection(
        connectionManagerPhoneAccount: PhoneAccountHandle?,
        request: ConnectionRequest?
    ): Connection {
        return MainActivity.call1!!;
    }

    override fun onCreateOutgoingConnectionFailed(
        connectionManagerPhoneAccount: PhoneAccountHandle?,
        request: ConnectionRequest?
    ) {
        super.onCreateOutgoingConnectionFailed(connectionManagerPhoneAccount, request)
    }

    override fun onCreateIncomingConnectionFailed(
        connectionManagerPhoneAccount: PhoneAccountHandle?,
        request: ConnectionRequest?
    ) {
        super.onCreateIncomingConnectionFailed(connectionManagerPhoneAccount, request)
    }
}