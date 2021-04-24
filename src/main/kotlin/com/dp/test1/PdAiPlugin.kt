package com.dp.test1
import com.fs.starfarer.api.combat.AutofireAIPlugin
import com.fs.starfarer.api.combat.MissileAPI
import com.fs.starfarer.api.combat.ShipAPI
import com.fs.starfarer.api.combat.WeaponAPI
import com.fs.starfarer.launcher.ModManager

import org.lwjgl.util.vector.Vector2f

class PdAiPlugin constructor(var assignedWeapon : WeaponAPI, var baseAI : AutofireAIPlugin =
                                 ModManager.getInstance().pickWeaponAIPlugin(assignedWeapon))
    : AutofireAIPlugin{
    var mbaseAI = ModManager.getInstance().pickWeaponAIPlugin(assignedWeapon)

    override fun advance(p0: Float) {
        return mbaseAI.advance(p0)
    }

    override fun shouldFire(): Boolean {
        if(!mbaseAI.shouldFire()) return false
        if (null != targetMissile) return true
        if(null != targetShip && targetShip!!.isFighter) return true
        return false
    }

    override fun forceOff() {
        return mbaseAI.forceOff()
    }

    override fun getTarget(): Vector2f? {
        return mbaseAI.target
    }

    override fun getTargetShip(): ShipAPI? {
        return mbaseAI.targetShip
    }

    override fun getWeapon(): WeaponAPI? {
        return mbaseAI.weapon
    }

    override fun getTargetMissile(): MissileAPI? {
        return mbaseAI.targetMissile
    }
}