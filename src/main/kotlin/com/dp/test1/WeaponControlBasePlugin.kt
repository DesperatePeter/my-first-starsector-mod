package com.dp.test1
import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import org.json.JSONException
import java.io.File
import java.io.IOException

const val settingsFile = "hotkeys.json"
const val cycleJsonKey = "cycleAutoFireMode"
const val combineJsonKey = "combineWeaponGroups"

public class WeaponControlBasePlugin : BaseModPlugin(){

    override fun onApplicationLoad() {
        super.onApplicationLoad()
        File("/home/jannes/tmp/log").writeText("success")
    }
}