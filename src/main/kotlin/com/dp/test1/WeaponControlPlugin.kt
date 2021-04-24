package com.dp.test1

import com.fs.starfarer.api.Global
import com.fs.starfarer.api.combat.*
import com.fs.starfarer.api.input.InputEventAPI
import org.lazywizard.lazylib.ui.LazyFont
import com.fs.starfarer.api.combat.ViewportAPI;
import com.fs.starfarer.launcher.ModManager
import java.awt.Color


public class WeaponControlPlugin : BaseEveryFrameCombatPlugin() {
    lateinit var engine: CombatEngineAPI;
    lateinit var mweaponGroups: MutableList<WeaponGroupAPI>;
    lateinit var mplayerShip: ShipAPI;
    private lateinit var font : LazyFont
    private var drawable : LazyFont.DrawableString? = null
    private var textFrameTimer : Int = 80
    private var isGroupPD = HashMap<Int, Boolean>()

    val keyManager = KeyStatusManager()
    override fun advance(amount: Float, events: MutableList<InputEventAPI>?) {
        super.advance(amount, events)
        if (null == engine) return

        mplayerShip = engine.playerShip ?: return
        // val combatUI = engine.combatUI ?: return
        mweaponGroups = mplayerShip.weaponGroupsCopy

        keyManager.parseInputEvents(events)

        when (keyManager.mkeyStatus.mcontrolEvent) {
            ControlEventType.COMBINE -> combineWeaponGroup()
            ControlEventType.CYCLE -> cycleWeaponGroupMode()
            // else -> println("Nothing to do")
        }


    }

    private fun cycleWeaponGroupMode() {
        val groupIndex = keyManager.mkeyStatus.mpressedWeaponGroup - 1
        val weaponGroup = mweaponGroups[groupIndex]
        if(null == isGroupPD[groupIndex]) isGroupPD[groupIndex] = false
        for (i in 0 until weaponGroup.aiPlugins.size) {
            val weapon = weaponGroup.aiPlugins[i].weapon
            if (isGroupPD[groupIndex] == true){
                weaponGroup.aiPlugins[i] = ModManager.getInstance().pickWeaponAIPlugin(weapon)

            }else {
                if (isPDWeapon(weapon)) weaponGroup.aiPlugins[i] = PdAiPlugin(weapon)
            }
        }
        isGroupPD[groupIndex] = !isGroupPD[groupIndex]!!
        val pdModeName = if (isGroupPD[groupIndex]!!) {
            "PD Only"
        } else {
            "Normal"
        }
        printMessage("Weapon Group " + keyManager.mkeyStatus.mpressedWeaponGroup + ": " + pdModeName)
    }

    private fun isPDWeapon(weapon: WeaponAPI) : Boolean{
        return weapon.hasAIHint(WeaponAPI.AIHints.PD)
    }

    private fun printMessage(message: String){
        drawable = font.createText(message,color = Color.GREEN)
    }


    private fun combineWeaponGroup() {
        val weaponGroup: WeaponGroupAPI = mweaponGroups[keyManager.mkeyStatus.mpressedWeaponGroup - 1] // weapon group 1
        println("Weapon Group Size (pre): " + weaponGroup.weaponsCopy.size.toString())
        val wp: WeaponAPI? = weaponGroup.removeWeapon(0)
        println("Is weapon null? " + (null == wp).toString())
        println("Weapon Group Size (post): " + weaponGroup.weaponsCopy.size.toString())

//        while (weaponGroup.weaponsCopy.isNotEmpty()){
//            val weapon = weaponGroup.removeWeapon(0)
//            if (null != weapon){
//                mplayerShip.selectedGroupAPI.addWeaponAPI(weapon)
//            }
//        }
        Global.getSoundPlayer().playUISound("ui_button_pressed", 1f, 0.5f);
        println("Executed combineWeaponGroup")
    }

    override fun init(engine: CombatEngineAPI?) {
        super.init(engine)
        font = LazyFont.loadFont("graphics/fonts/insignia15LTaa.fnt")
        if (null != engine) {
            this.engine = engine
        }
    }

    override fun renderInUICoords(viewport: ViewportAPI?) {
        super.renderInUICoords(viewport)
        if (null != drawable){
            drawable?.draw(600f,600f)
            textFrameTimer--
        }

        if (textFrameTimer <= 0){
            drawable = null
            textFrameTimer = 80
        }


    }
}