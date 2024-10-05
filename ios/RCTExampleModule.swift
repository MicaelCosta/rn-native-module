//
//  RCTExampleModule.swift
//  nativemodule
//
//  Created by Amorim on 15/09/24.
//

//import de recursos para trabalhar com o native
//mesma ideia do import do React
import Foundation
//Importa o react para utilizar o RCTPromise
import React

//comunicação entre o nativo e o react quem faz é o objective-C, ao inves do Swift
//Para isso é necessário criar um arquivo com mesmo nome desse, porém com a extensão .m
//Esse arquivo do objective-C fará a exportação dessa classe Swift para o React native

//Precisa declarar que esse arquivo dá suporte ao Objective-C
@objc(RCTExampleModule)

//Herda do NSObject porque no arquivo do Objective-C foi exportado como NSObject
class RCTExampleModule: NSObject {
  
  //A tipagem do parametro é essencial
  //o React acaba se perdendo na comunicação com os parametros
  //Devido a isso é colocado o "_" dizendo que qualquer informação passada será destinada aquele parametro
  //é uma particularidade do Swift
  //Após declarar o parametro é necessário ajustar o RCT do objective-C
  @objc
  func printMessage(_ title: String,_ value: CGFloat){
    //Essa função print aparecerá somente no console do Xcode
    //Necessário rodar o aplicativo pelo Xcode
    print("Native message with React native \(title) \(value)")
  }
  
  //O retorno precisa estar baseado em resolve e reject para caso de falha
  //Resolver e Rejecter não são parametros
  @objc
  func returnMessage(_ title: String, resolver resolve: @escaping RCTPromiseResolveBlock, rejecter reject: @escaping RCTPromiseRejectBlock){
    if(title == "João"){
      resolve("Message processed: \(title)")
    }else {
      let error = NSError(domain: "", code: 200, userInfo: [NSLocalizedDescriptionKey: "Title error"])
      reject("Error", "Message error: \(title)", error)
    }
  }
}
