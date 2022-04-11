package advanced.entropy;

import java.util.List;

public class charHash {
    private int[] value;
    private char[] key;
    private int nextIndex;
    /*
    * TODO
    *  transformar em um JavaBean
    * */

    public charHash(){
        this.value = new int[100];
        this.key = new char[100];
    }

    public charHash(int qnt){
        this.value = new int[qnt];
        this.key = new char[qnt];
    }

    private boolean existsKey(char k){
        int index = 0;
        while ((index<this.key.length) || (0 == this.key[index]))
        for (char c : this.key){
            if (k == c){return true;}
        }
        return false;
    }

    public void add(char c){
        if (! existsKey(c)){
            this.key[nextIndex] = c;
        }
    }

}
