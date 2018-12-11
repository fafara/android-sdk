package co.omisego.omisego.websocket.channel.dispatcher

/*
 * OmiseGO
 *
 * Created by Phuchit Sirimongkolsathien on 12/5/2018 AD.
 * Copyright © 2017-2018 OmiseGO. All rights reserved.
 */

import co.omisego.omisego.constant.enums.ErrorCode
import co.omisego.omisego.model.APIError
import co.omisego.omisego.model.socket.SocketReceive
import co.omisego.omisego.websocket.channel.SocketMessageRef
import co.omisego.omisego.websocket.enum.SocketSystemEvent
import co.omisego.omisego.websocket.listener.SocketChannelListener
import co.omisego.omisego.websocket.listener.SocketConnectionListener

/**
 * A listener for dispatcher the [SocketConnectionListener] and [SocketChannelListener] events.
 */
class SystemEventDispatcher(
    override val socketChannelListener: SocketChannelListener
) : SocketDispatcherContract.SystemEventDispatcher {

    override fun handleEvent(systemEvent: SocketSystemEvent, response: SocketReceive<*>) {
        if (systemEvent == SocketSystemEvent.REPLY) {
            response.runIfRefSchemeIs(SocketMessageRef.SCHEME_JOIN) {
                socketChannelListener.onJoinedChannel(response.topic)
            }
            response.runIfRefSchemeIs(SocketMessageRef.SCHEME_LEAVE) {
                socketChannelListener.onLeftChannel(response.topic)
            }
        }
        else if (systemEvent == SocketSystemEvent.ERROR) {
            val error = response.error
                ?: APIError(ErrorCode.SDK_SOCKET_ERROR, "Something goes wrong while connecting to the channel")
            socketChannelListener.onError(error)
        }
    }

    private inline fun SocketReceive<*>.runIfRefSchemeIs(scheme: String, lambda: () -> Unit) {
        val ref = this.ref ?: return
        if (ref.startsWith(scheme)) {
            lambda()
        }
    }
}
