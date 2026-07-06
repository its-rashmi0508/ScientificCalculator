package calculator.engine;
public class MemoryManager {

    private double memory = 0;
    public void clearMemory(){
        memory = 0;
    }
    public void store(double value){
        memory = value;
    }
    public void add(double value){
        memory += value;
    }
    public void subtract(double value){
        memory -= value;
    }
    public double recall(){
        return memory;
    }
}