/**
 * Created by 20142bsi0054 on 08/03/2017.
 */
public class TadRecursivos {

    public int soma(int[] numeros, int tam){
        if (tam == 0){
            return 0;
        }else{
            return numeros[tam - 1] + soma(numeros, tam - 1);
        }
        /*
            * Teste de mesa:
            * soma({1,2,3}, 3)
            * 3 + soma({1,2,3}, 2);
            * 2 + soma({1,2,3}, 1)
            * 1 + soma({1,2,3}, 0) - > 0
            * retorna 0
            * Desempilhando ...
            * 1 + 0 = 1
            * 2 + 1 = 3
            * 3 + 3 = 6
            *
            * */
    }

    public int mult(int a, int b){
        if(b == 0) return 0;
        return a + (mult(a, b-1));
    }

    public int divisao(int numerador, int denominador){
        if(numerador == 0){
            return 0;
        }else if(numerador < 0 && denominador < 0){
            return divisao(-numerador, -denominador);
        }else if (numerador < 0){
            return - divisao(-numerador, denominador);
        }else if (denominador < 0){
            return - divisao(numerador, -denominador);
        }else if(numerador < denominador){
            return 0;
        }else{
            return divisao(numerador - denominador, denominador) + 1;
        }
    }

    private float raizQuadrada(float n, float original){
        if(Math.abs((n*n) - original) <= 0.0001){
            return n;
        }else{
            float xn, xAnterior;

            if(n == original){
                xAnterior = n / 2;
            }else{
                xAnterior = n;
            }

            xn = xAnterior - (((xAnterior*xAnterior) - original) / (2*xAnterior));

            return raizQuadrada(xn, original);
        }
    }

    public float raiz(float num){
        return this.raizQuadrada(num, num);
    }


    public static void main(String[] args){
        TadRecursivos t = new TadRecursivos();
        int [] nums = {1,2,3};

        System.out.println(t.mult(3, 5));
        System.out.println(t.soma(nums, nums.length));
        System.out.println(t.divisao(7,-2));
        System.out.println(t.raiz(5));

    }



}
