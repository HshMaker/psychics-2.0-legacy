package io.github.hshmaker.psychics.ability.selfishhealer

import io.github.monun.psychics.Ability
import io.github.monun.psychics.AbilityConcept
import io.github.monun.tap.config.Config
import io.github.monun.tap.config.Name
import io.github.monun.tap.event.EntityProvider
import io.github.monun.tap.event.TargetEntity
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.inventory.ItemStack

@Name("selfishHealer")
class AbilityConceptSelfishHealer : AbilityConcept() {
    @Config
    var healByDamage = 0.1
    init {
        displayName = "흡혈"

        wand = ItemStack(Material.REDSTONE)

        description = listOf(
            Component.text("적에게 입힌 피해의 일정량을 회복합니다."),
            Component.text("${healByDamage * 100} %")
        )
    }
}

class AbilitySelfishHealer : Ability<AbilityConceptSelfishHealer>(), Listener {
    override fun onEnable() {
        psychic.registerEvents(this)
    }

    @TargetEntity(EntityProvider.EntityDamageByEntity.Damager::class)
    @EventHandler(ignoreCancelled = true)
    fun onAttack(event: EntityDamageByEntityEvent) {
        val damage = event.damage
        val healPercent = damage * concept.healByDamage
        esper.player.psychicHeal(healPercent)
    }

}