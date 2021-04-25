package com.dp.test1

val weaponGroupKeys = '1'..'7'

enum class ControlEventType {
    CYCLE, COMBINE, NONE
}

class KeyStatus() {
    var mcontrolEvent: ControlEventType = ControlEventType.NONE
    var mpressedWeaponGroup: Int = 0 // 0 for inactive/unknown
    fun reset() {
        mcontrolEvent = ControlEventType.NONE
        mpressedWeaponGroup = 0
    }
}
