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
    public void setN(boolean state) {
        N = state;
    }

    @Override
    public void setZ(boolean state) {
        Z = state;
    }

    @Override
    public void setP(boolean state) {
        P = state;
    }
}