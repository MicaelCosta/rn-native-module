package com.nativemodule

import android.util.Log
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.modules.core.DeviceEventManagerModule

//Como a classe já está sendo extendida de ReactContextBaseJavaModule
//Já quer dizer que ela será externalizada a camada do React Native
class RCTExampleModule(reactContext: ReactApplicationContext): ReactContextBaseJavaModule(reactContext) {
    private var listenerCount = 0 //Cria um contador de eventos devido ao warning do Android

    //Necessário sobreescrever essa função para dizer qual será o nome do pacote
    //Parecido com a configuração de @OBJ do Swift
    override fun getName() = "RCTExampleModule"

    @ReactMethod //Define que a função será visivel pela parte do React native
    fun printMessage(title: String, value: Int) {
        //o Log será apresentado no console do LogCat no Android Studio
        Log.d("RCTExampleModule", "Native message with React Native $title $value")
    }

    @ReactMethod
    fun returnMessage(title: String, promise: Promise) {
        if(title.equals("João")) {
            promise.resolve("Message processed: $title")
        }else {
            promise.reject("Error", "Message not processed")
        }
    }

    @ReactMethod
    fun eventMessage(value: Int) {
        //Para padronizar o envio dos parametros como objeto igual ao IOS
        var params = Arguments.createMap()
        params.putInt("value", value)

        sendEvent("onMessagePrinted", params)
    }

    //Cria a função privada que irá emitir o evento
    private fun sendEvent(eventName: String, params: Any?) {
        reactApplicationContext
            .getJSModule((DeviceEventManagerModule.RCTDeviceEventEmitter::class.java))
            .emit(eventName, params)
    }

    //Implementação de funções do proprio React native que serão utilizadas pelo EventEmitter
    @ReactMethod
    fun addListener(eventName: String) {
        listenerCount += 1
    }

    //Implementação de funções do proprio React native que serão utilizadas pelo EventEmitter
    @ReactMethod
    fun removeListeners(event: Int) {
        listenerCount -= 1
    }
}