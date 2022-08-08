package dev.igalaxy.fakeencryption

import com.github.retrooper.packetevents.PacketEvents
import io.github.retrooper.packetevents.factory.spigot.SpigotPacketEventsBuilder
import net.axay.kspigot.main.KSpigot

class FakeEncryption : KSpigot() {
    companion object {
        lateinit var INSTANCE: FakeEncryption
    }

    override fun load() {
        INSTANCE = this

        PacketEvents.setAPI(SpigotPacketEventsBuilder.build(INSTANCE))
        PacketEvents.getAPI().settings.debug(false).checkForUpdates(false)
        PacketEvents.getAPI().load()
    }

    override fun startup() {
        PacketEvents.getAPI().eventManager.registerListener(FakeEncryptionListener())
        PacketEvents.getAPI().init()
    }

    override fun shutdown() { }
}