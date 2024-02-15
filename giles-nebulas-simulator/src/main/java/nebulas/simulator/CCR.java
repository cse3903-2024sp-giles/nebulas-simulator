package nebulas.simulator;

/**
 * CCR - Contains the current state of the control code registers and methods for manipulating them.
 *
 *<p>
 *The CCRs are simply 3 boolean values with methods to set and get them.
 *<p>
 **/
public interface CCR{


    public boolean getN();


    public boolean getZ();


    public boolean getP();


    public void setN();


    public void setZ();


    public void setP();

}
