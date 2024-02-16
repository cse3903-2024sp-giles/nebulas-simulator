import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class HelloworldTest1 {

	@Test
    public void checkForIllegalCharactersTest1(){
        String legalFile = "D:\\cse2221\\workspace\\WordCounter\\doc\\gettysbur.txt";
        assertEquals(true, HelloWorld.checkForIllegalCharacters(legalFile));

    }
    //dhai
    @Test
    public void checkForIllegalCharactersTest2(){
       String illegalFile = "12323jt.-xt";
       assertFalse(HelloWorld.checkForIllegalCharacters(illegalFile));
   }
    @Test
    public void validStartTest1(){
        HelloWorld.initLoadAddr = 60000;
        HelloWorld.programLength = 500;
        boolean test = HelloWorld.validStart();
        assertEquals(true, test);
    
    }
    @Test
    public void validStartTest2(){
        HelloWorld.initLoadAddr = 67000;
        HelloWorld.programLength = 500;
        boolean test = HelloWorld.validStart();
        assertEquals(false, test);
    
    }
    @Test
    public void validStartTest3(){
        HelloWorld.initLoadAddr = 62230;
        HelloWorld.programLength = 10000;
        boolean test = HelloWorld.validStart();
        assertEquals(true, test);
    
    }

}
