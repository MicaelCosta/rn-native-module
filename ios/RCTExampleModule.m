//
//  RCTExampleModule.m
//  nativemodule
//
//  Created by Amorim on 15/09/24.
//

//Importa do React as funcionalidades para comunicação com a Brigde
#import "React/RCTBridgeModule.h"

//Declara a exposição da classe
@interface RCT_EXTERN_MODULE(RCTExampleModule, NSObject)

//Além que expor a classe, expoe a função
//Nesse caso também está declarando que a função recebe um parametro do tipo string
//o "*" diz que qualquer coisa que vier será destinado para esse parametro
RCT_EXTERN_METHOD(printMessage: (NSString *) title: (CGFloat *) value)


//Finaliza o arquivo
@end
