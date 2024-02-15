package nebulas.simulator;

public class Machine1 implements Machine {
    

    //init our registers
    CCR ccr = new CCR1();

    

    @Override
    public Word setPC(Word newPC) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setPC'");
    }

    @Override
    public Word getPC() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPC'");
    }

    @Override
    public void setRegister(int registerNumber, Word word) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setRegister'");
    }

    @Override
    public Word getRegister(int registerNumber) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getRegister'");
    }

    @Override
    public void setCCR() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCCR'");
    }

    @Override
    public boolean getCCR() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCCR'");
    }

}