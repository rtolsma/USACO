/*
ID:1tolsma1
LANG:JAVA
TASK:UnitTesting
*/

/**
 * Created by ryan on 2/12/17.
 */

import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import org.junit.Assert.*;
public class UnitTesting {


    @Test
    public void sequence() throws Exception {
        ariprog test=new ariprog();
        ariprog.main(null);

        Assert.assertTrue(ariprog.sequence(5,37,4));

    }

}
