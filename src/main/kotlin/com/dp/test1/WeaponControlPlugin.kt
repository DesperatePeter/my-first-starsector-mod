package com.dp.test1
import com.fs.starfarer.api.combat.BaseEveryFrameCombatPlugin
import com.fs.starfarer.api.combat.CombatEngineAPI
import com.fs.starfarer.api.input.InputEventAPI

public class WeaponControlPlugin : BaseEveryFrameCombatPlugin(){
    lateinit var engine : CombatEngineAPI;
    val keyManager = KeyStatusManager()
    override fun advance(amount: Float, events: MutableList<InputEventAPI>?) {
        super.advance(amount, events)

        val playerShip = engine.playerShip ?: return
        val combatUI = engine.combatUI ?: return
        val weaponGroups = playerShip.

        keyManager.parseInputEvents(events)



    }

    override fun init(engine: CombatEngineAPI?) {
        super.init(engine)
        if (engine != null) {
            this.engine = engine
        }
    }
}