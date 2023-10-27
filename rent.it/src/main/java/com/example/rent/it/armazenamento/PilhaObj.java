package com.example.rent.it.armazenamento;

public class PilhaObj <T>{

        // 01) Atributos
        private T[] pilha;
        private int topo;

        // 02) Construtor
        public PilhaObj(int capacidade) {
            pilha = (T[]) new Object[capacidade];
            topo = -1;

        }

        // 03) MÃ©todo isEmpty
        public Boolean isEmpty() {

            if(topo == -1){
                return true;
            }
            return false;
        }

        // 04) MÃ©todo isFull
        public Boolean isFull() {
            if(topo == pilha.length - 1){
                return true;
            }
            return false;
        }

        // 05) MÃ©todo push
        public void push(T info) throws IllegalStateException {
            if(!isFull()){
                pilha[++topo] = info;
            }else {
                throw new IllegalStateException("Pilha cheia!");


            }


        }

        // 06) MÃ©todo pop
        public T pop() {

            return pilha[topo--];
        }

        // 07) MÃ©todo peek
        public T peek() {
            if(!isEmpty()) {
                return pilha[topo];
            }
            return null;
        }

        // 08) MÃ©todo exibe
        public void exibe() {
            if(!isEmpty()){
                for (int i = topo; i >= 0; i--){
                    System.out.println(pilha[i]);
                }
            }else{
                System.out.println("Pilha vazia");
            }

        }


        //Getters & Setters (manter)
        public int getTopo() {
            return topo;
        }

}
