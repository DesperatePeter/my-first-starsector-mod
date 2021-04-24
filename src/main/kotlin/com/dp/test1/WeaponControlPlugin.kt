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
        val weaponGroups = playerShip.weaponGroupsCopy

        keyManager.parseInputEvents(events)

        when (keyManager.mkeyStatus.mcontrolEvent){
            ControlEventType.COMBINE -> combineWeaponGroup()
            ControlEventType.CYCLE -> cycleWeaponGroupMode()
            else -> println("Nothing to do")
        }



    }

    private fun cycleWeaponGroupMode(): Nothing = TODO()

    private fun combineWeaponGroup(): Nothing = TODO()

    override fun init(engine: CombatEngineAPI?) {
        super.init(engine)
        if (engine != null) {
            this.engine = engine
        }
    }
}