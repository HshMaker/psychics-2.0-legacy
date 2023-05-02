package io.github.hshmaker.psychics.ability.mysample

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

@Name("mySample")
class AbilityConceptMySample : AbilityConcept() {
    init {

    }
}

class AbilityMySample : Ability<AbilityConceptMySample>(), Listener {

}
