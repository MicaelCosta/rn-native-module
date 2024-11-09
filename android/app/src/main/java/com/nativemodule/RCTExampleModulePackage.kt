package com.nativemodule

import android.view.View
import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ReactShadowNode
import com.facebook.react.uimanager.ViewManager

//Dentro do arquivo MainApplication é que exportamos o classe RCTExampleModulePackage
class RCTExampleModulePackage: ReactPackage {
    //Sobreescreve o fun createNativeModules, dizendo qual classe será exportada ao RN
    override fun createNativeModules(reactContext: ReactApplicationContext
    ): MutableList<NativeModule> = listOf(RCTExampleModule(reactContext)).toMutableList()

    //Aqui é onde sobreescreveria a classe de view native
    //Mas nesse caso não usamos
    override fun createViewManagers(reactContext: ReactApplicationContext)
    : MutableList<ViewManager<View, ReactShadowNode<*>>> = mutableListOf()
}