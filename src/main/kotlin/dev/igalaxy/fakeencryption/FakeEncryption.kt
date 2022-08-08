package dev.igalaxy.fakeencryption

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.ProtocolManager
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import net.axay.kspigot.main.KSpigot

class FakeEncryption : KSpigot() {
    companion object {
        lateinit var INSTANCE: FakeEncryption
        lateinit var PROTOCOL_MANAGER: ProtocolManager
    }

    override fun load() {
        INSTANCE = this
        PROTOCOL_MANAGER = ProtocolLibrary.getProtocolManager()!!
    }

    override fun startup() {
        PROTOCOL_MANAGER.addPacketListener(object : PacketAdapter(
            INSTANCE,
            ListenerPriority.NORMAL,
            PacketType.Play.Server.SERVER_DATA
        ) {
            override fun onPacketSending(event: PacketEvent?) {
                event?.packet?.booleans?.write(3, false)
            }
        })
    }

    override fun shutdown() { }
}