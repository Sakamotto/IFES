#include <stdio.h>
#include <stdlib.h>
#include "tratadorDeExecao.h"
#include "equacao.h"

double execaoDeltaMenorZero(double delta){
    if(delta < 0){
        return -1;
    }else{
        return 1;
    }
}
