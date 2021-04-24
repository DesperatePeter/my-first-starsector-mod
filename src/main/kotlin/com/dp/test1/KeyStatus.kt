package com.dp.test1
import com.fs.starfarer.api.input.InputEventAPI;
val weaponGroupKeys = 1..7
class KeyStatusManager() {
    var misCycleKey : Boolean = false
    var misCombineKey : Boolean = false
    var misWeaponGroupChange : Boolean = false
    private fun parseInputEvent(event : InputEventAPI){
        if (!event.isKeyDownEvent || event.isConsumed) return
        when (event.eventChar){
            WeaponControlBasePlugin.mcycleAutoFireModeHotkey -> misCycleKey = true
            WeaponControlBasePlugin.mcombineWeaponGroups -> misCombineKey = true
        }
        event.consume()
    }

    private fun reset(){
        misCombineKey = false
        misCycleKey = false
        misWeaponGroupChange = false
    }

    public fun parseInputEvents(events : MutableList<InputEventAPI>?){
        reset();
        events?.iterator()?.forEach {
            parseInputEvent(it)
        }
    }
}