package com.dp.test1
import com.fs.starfarer.api.input.InputEventAPI;
val weaponGroupKeys = '1'..'7'
enum class ControlEventType{
    CYCLE, COMBINE, NONE
}
class KeyStatus(){
    var mcontrolEvent : ControlEventType = ControlEventType.NONE
    var mactiveWeaponGroup : Int = 0 // 0 for inactive/unknown
    var mpressedWeaponGroup : Int = 0
    fun reset(){
        mcontrolEvent = ControlEventType.NONE
        mpressedWeaponGroup = 0
    }
}
class KeyStatusManager() {
    var mkeyStatus = KeyStatus()

    private fun parseInputEvent(event : InputEventAPI){
        if (!event.isKeyDownEvent || event.isConsumed || (event.eventChar !in weaponGroupKeys)) return
        mkeyStatus.mpressedWeaponGroup = event.eventChar.toInt()

        if (event.isAltDown && event.isCtrlDown){
            mkeyStatus.mcontrolEvent = ControlEventType.CYCLE
            return
        }

        if (event.isAltDown){
            mkeyStatus.mcontrolEvent = ControlEventType.COMBINE
            return
        }

        mkeyStatus.mactiveWeaponGroup = mkeyStatus.mpressedWeaponGroup
    }

    public fun parseInputEvents(events : MutableList<InputEventAPI>?){
        mkeyStatus.reset();
        events?.iterator()?.forEach {
            parseInputEvent(it)
            it.consume()
        }
    }
}