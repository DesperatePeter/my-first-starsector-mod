package com.dp.test1
import com.fs.starfarer.api.BaseModPlugin;
import com.fs.starfarer.api.Global;
import org.json.JSONException
import java.io.IOException

const val settingsFile = "hotkeys.json"
const val cycleJsonKey = "cycleAutoFireMode"
const val combineJsonKey = "combineWeaponGroups"

public class WeaponControlBasePlugin : BaseModPlugin(){
    companion object{
        var mcycleAutoFireModeHotkey : Char = 'u'
            get() = field

        var mcombineWeaponGroups : Char = 'i'
            get() = field
    }


    override fun onApplicationLoad() {
        super.onApplicationLoad()
        val hotkeyJson = Global.getSettings().loadJSON(settingsFile)
        try {
            mcycleAutoFireModeHotkey = hotkeyJson.get(cycleJsonKey).toString()[0]
            mcombineWeaponGroups = hotkeyJson.get(combineJsonKey).toString()[0]

        } catch (json: JSONException){
            println("Error: Invalid json file! Using default values instead.")
        } catch (fs: IOException){
            println("Error: hotkeys.json file not found! Using default values instead.")
        }

    }
}