package nebulas.simulator;
class Registers1 implements Registers {
    
    Word zero;
    Word one;
    Word two;
    Word three;
    Word four;
    Word five;
    Word six;
    Word seven;

    public Registers1(){
        
        zero = new Word1();
        one = new Word1();
        two = new Word1();
        three = new Word1();
        four = new Word1();
        five = new Word1();
        six = new Word1();
        seven = new Word1();

    }

    
    @Override
    public Word getRegister(int registerNumber){

        Word deliverable;
        
        switch (registerNumber) {
            case 0:
                deliverable = zero;
                break;
            case 1:
                deliverable = one;
                break;
            case 2:
                deliverable = two;
                break;
            case 3:
                deliverable = three;
                break;
            case 4:
                 deliverable = four;
                break;
            case 5:
                 deliverable = five;
                break;
            case 6:
                 deliverable = six;
                break;
            case 7:
                 deliverable = seven;
                break;
            default:
                throw new UnsupportedOperationException("Tried to set an invalid register");
        }
        
        return deliverable;
    }
    
    @Override
    public void setRegister(int registerNumber, Word value){

        switch (registerNumber) {
            case 0:
                zero = value;
                break;
            case 1:
                one = value;
                break;
            case 2:
                two = value;
                break;
            case 3:
                three = value;
                break;
            case 4:
                four = value;
                break;
            case 5:
                five = value;
                break;
            case 6:
                six = value;
                break;
            case 7:
                seven = value;
                break;
            default:
                throw new UnsupportedOperationException("Tried to set an invalid register");
        }
       
        
    }
}
