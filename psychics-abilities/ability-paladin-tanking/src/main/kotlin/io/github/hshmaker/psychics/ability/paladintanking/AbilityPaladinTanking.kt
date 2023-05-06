package io.github.hshmaker.psychics.ability.paladintanking

import io.github.monun.psychics.Ability
import io.github.monun.psychics.AbilityConcept
import io.github.monun.psychics.AbilityType
import io.github.monun.psychics.ActiveAbility
import io.github.monun.psychics.attribute.EsperAttribute
import io.github.monun.psychics.attribute.EsperStatistic
import io.github.monun.tap.config.Config
import io.github.monun.tap.config.Name
import io.github.monun.tap.event.EntityProvider
import io.github.monun.tap.event.TargetEntity
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.entity.EntityDamageByEntityEvent
import org.bukkit.event.entity.EntityDamageEvent
import org.bukkit.event.player.PlayerEvent
import org.bukkit.inventory.ItemStack

@Name("paladinTanking")
class AbilityConceptPaladinTanking : AbilityConcept() {
    @Config
    var damageMultiple = 0.5
    init {
        cost = 30.0
        type = AbilityType.ACTIVE
        durationTime = 4000L
        cooldownTime = 15000L
        wand = ItemStack(Material.GOLD_INGOT)
    }
}

class AbilityPaladinTanking : ActiveAbility<AbilityConceptPaladinTanking>(), Listener {
    override fun onEnable() {
        psychic.registerEvents(this)
    }
    override fun onCast(event: PlayerEvent, action: WandAction, target: Any?) {
        val concept = concept
        psychic.consumeMana(concept.cost)
        cooldownTime = concept.cooldownTime
        durationTime = concept.durationTime
    }

    @EventHandler(ignoreCancelled = true)
    fun onEntityDamage(event: EntityDamageByEntityEvent) {
        val concept = concept
        val damage = event.finalDamage
        if (damage <= 0) return

        val entity = event.entity
        val player = esper.player

        if (entity == player) {
            if (durationTime > 0) event.damage *= concept.damageMultiple
        }
    }
}
