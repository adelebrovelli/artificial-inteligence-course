import java.lang.Math;

public class Aspirador {
    private double[] posicao = new double[2]; // 0 = posição  1 = sujo ou nao

    
    public Aspirador() {
        // Inicializa a posição e a sujeira
        posicao[0] = 0; // 0 representa a direção "esquerda": começará aqui
        posicao[1] = Math.random(); // >0.5 representa que a área está suja: começará aleatório
    }

    public void limpeza(){
        if(posicao[0]==0){
            if(posicao[1]>0.5){ // se tiver sujo na esquerda, limpa
                System.out.println("esquerda, sujo");
                posicao[1]=0;
                System.out.println("esquerda, aspirado");
                posicao[0]=1; //adiciona 1 na posicao, logo vai pra direita
            } else {
                System.out.println("esquerda, estava limpo");
            posicao[0]=1; //vai pra direita se tiver limpo tb
            posicao[1] = Math.random(); //gambiarra
        }
        }

        if(posicao[0]==1){ 
            if(posicao[1]>0.5){ // se tiver sujo na direita, limpa
                System.out.println("direita, sujo");
                posicao[1]=0;
                System.out.println("direita, aspirado");
                posicao[0]=0; //volta pra esquerda
            } else {
                System.out.println("direita, estava limpo");
                posicao[0]=0; //volta pra esquerda se tiver limpo tb
                posicao[1] = Math.random(); 
            }
            
        }

        
    }
    
    
    public static void main(String[] args) throws Exception {
        Aspirador aspirador = new Aspirador();
            aspirador.limpeza();
        
    }

    
}
