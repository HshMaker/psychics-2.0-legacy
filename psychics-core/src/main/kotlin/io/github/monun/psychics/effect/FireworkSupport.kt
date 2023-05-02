package io.github.monun.psychics.effect

import org.bukkit.FireworkEffect
import org.bukkit.Location
import org.bukkit.World
import org.bukkit.entity.Firework
import org.bukkit.inventory.meta.FireworkMeta

fun World.spawnFirework(x: Double, y: Double, z: Double, world: World, effect: FireworkEffect) {
    val location = Location(world, x, y, z)
    val firework: Firework = location.world.spawn(location, Firework::class.java)
    val fireworkMeta: FireworkMeta = firework.fireworkMeta
    fireworkMeta.addEffect(effect)
    fireworkMeta.power = 0
    firework.fireworkMeta = fireworkMeta
    firework.ticksToDetonate = 0
}
fun World.spawnFirework(loc: Location, effect: FireworkEffect) = spawnFirework(loc.x, loc.y, loc.z, loc.world, effect)