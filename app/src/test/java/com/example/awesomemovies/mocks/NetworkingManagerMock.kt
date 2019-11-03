package com.barriosartola.awesomeapp.mocks

import android.content.Context
import com.barriosartola.awesomeapp.data.helper.networking.NetworkingManager

class NetworkingManagerMock(context: Context) : NetworkingManager(context) {

    var networkingAvailable = true

    override fun isNetworkOnline() = networkingAvailable

}