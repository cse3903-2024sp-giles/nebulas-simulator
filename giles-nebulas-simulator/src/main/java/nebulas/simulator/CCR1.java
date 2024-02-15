package nebulas.simulator;

public class CCR1 implements CCR {
    private boolean N;
    private boolean Z;
    private boolean P;

    @Override
    public boolean getN() {
        return N;
    }

    @Override
    public boolean getZ() {
        return Z;
    }

    @Override
    public boolean getP() {
        return P;
    }

    @Override
    public void setN() {
        N = true;
        Z = false;
        P = false;
    }

    @Override
    public void setZ() {
        N = false;
        Z = true;
        P = false;
    }

    @Override
    public void setP() {
        N = false;
        Z = false;
        P = true;
    }
}