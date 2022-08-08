package dev.igalaxy.fakeencryption

import com.github.retrooper.packetevents.event.PacketListenerAbstract
import com.github.retrooper.packetevents.event.PacketListenerPriority
import com.github.retrooper.packetevents.event.PacketSendEvent
import com.github.retrooper.packetevents.protocol.packettype.PacketType
import com.github.retrooper.packetevents.wrapper.play.server.WrapperPlayServerServerData

class FakeEncryptionListener : PacketListenerAbstract(PacketListenerPriority.NORMAL) {
    override fun onPacketSend(event: PacketSendEvent?) {
        if (event?.packetType == PacketType.Play.Server.SERVER_DATA) {
            WrapperPlayServerServerData(event).isEnforceSecureChat = true
        }
    }
}