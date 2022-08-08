package dev.igalaxy.fakeencryption

import com.comphenix.protocol.PacketType
import com.comphenix.protocol.ProtocolLibrary
import com.comphenix.protocol.events.ListenerPriority
import com.comphenix.protocol.events.PacketAdapter
import com.comphenix.protocol.events.PacketEvent
import net.axay.kspigot.main.KSpigot

class FakeEncryption : KSpigot() {
    companion object {
        lateinit var INSTANCE: FakeEncryption
    }

    override fun load() {
        INSTANCE = this
    }

    override fun startup() {
        val protocolManager = ProtocolLibrary.getProtocolManager()!!
        protocolManager.addPacketListener(object : PacketAdapter(
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