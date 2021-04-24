package com.dp.test1
import com.fs.starfarer.api.input.InputEventAPI;
val weaponGroupKeys = '1'..'7'
enum class ControlEventType{
    CYCLE, COMBINE, NONE
}
class KeyStatus(){
    var mcontrolEvent : ControlEventType = ControlEventType.NONE
    var mactiveWeaponGroup : Int = 1
    var mpressedWeaponGroup : Int = 0 // 0 for inactive/unknown
    fun reset(){
        mcontrolEvent = ControlEventType.NONE
        mpressedWeaponGroup = 0
    }
}
class KeyStatusManager() {
    var mkeyStatus = KeyStatus()

    private fun parseInputEvent(event : InputEventAPI){
        if (event.isConsumed || !event.isKeyDownEvent) return // || event.isConsumed

        if (event.eventChar !in weaponGroupKeys) return
        // Note: char.toInt gets the ascii value rather than the contained number
        mkeyStatus.mpressedWeaponGroup = event.eventChar.toString().toInt()

        if (event.isAltDown && event.isCtrlDown){
            mkeyStatus.mcontrolEvent = ControlEventType.CYCLE
            if (!event.isConsumed) event.consume()
            return
        }

        if (event.isAltDown){
            mkeyStatus.mcontrolEvent = ControlEventType.CYCLE //COMBINE
            if (!event.isConsumed) event.consume()
            return
        }

        mkeyStatus.mactiveWeaponGroup = mkeyStatus.mpressedWeaponGroup
    }

    public fun parseInputEvents(events : MutableList<InputEventAPI>?){
        mkeyStatus.reset();
        events?.iterator()?.forEach {
            parseInputEvent(it)
        }
    }
}