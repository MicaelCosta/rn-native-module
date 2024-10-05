import React from 'react';
import { StyleSheet, Text, View, NativeModules, Button } from 'react-native';

export default function App() {
  //Do lado nativo o nome é RCTExampleModule
  //Por ser um padrão do React, o "RCT" é retirado pelo proprio react na comunicação
  //Como a exportação foi utilizando o NSObject, é possivel desestruturar o retorno
  const { ExampleModule } = NativeModules;

  console.log(ExampleModule);

  function printMessage(){
    ExampleModule.printMessage("My title", 17)
  }

  function returnMessage(){
    ExampleModule.returnMessage("João")
    .then(result => console.log(`Message from native module: ${result}`))
    .catch(error => console.error(error));
  }

  return (
    <View style={styles.container}>
      <Text>Open up App.js to start working on your app!</Text>
      <Button title='Print message' onPress={printMessage} />
      <Button title='Return message' onPress={returnMessage} />
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
