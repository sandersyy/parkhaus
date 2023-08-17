import logic.Rezension;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RezensionTest {
    @Test
    void rezenzionTest(){
        int sterne=2;
        int n=102;
        String bewert=" ";
        for(int i =0;i<n;i++){
            bewert +=" ";
        }
        String b=bewert;
        String name="Cassus";
        assertThrows(IllegalStateException.class,()->new Rezension(sterne,b,name));

    }

}