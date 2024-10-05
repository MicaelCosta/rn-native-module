import React from 'react';
import { StyleSheet, Text, View, NativeModules, Button, NativeEventEmitter } from 'react-native';

export default function App() {
  //Do lado nativo o nome é RCTExampleModule
  //Por ser um padrão do React, o "RCT" é retirado pelo proprio react na comunicação
  //Como a exportação foi utilizando o NSObject, é possivel desestruturar o retorno
  const { ExampleModule } = NativeModules;

  console.log(ExampleModule);

  //Registra o evento para utilizar a funçao eventMessage
  const eventEmitter = new NativeEventEmitter(ExampleModule);
  const subscription = eventEmitter.addListener("onMessagePrinted", (event) => {
    console.log("Evento recebido: ", event)
  });

  function printMessage(){
    ExampleModule.printMessage("My title", 17)
  }

  function returnMessage(){
    ExampleModule.returnMessage("João")
    .then(result => console.log(`Message from native module: ${result}`))
    .catch(error => console.error(error));
  }

  function eventMessage(){
    ExampleModule.eventMessage(10);
  }

  function killEvent(){
    subscription.remove();
  }

  return (
    <View style={styles.container}>
      <Text>Open up App.js to start working on your app!</Text>
      <Button title='Print message' onPress={printMessage} />
      <Button title='Return message' onPress={returnMessage} />
      <Button title='Event message' onPress={eventMessage} />
      <Button title='Kill event' onPress={killEvent} />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    alignItems: 'center',
    justifyContent: 'center',
  },
});
