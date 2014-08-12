package com.codehathi.common;

import com.codehathi.common.models.ValidExample;
import org.junit.Assert;
import org.junit.Test;

public class ReusableObjectListTest {

    @Test(expected = IllegalArgumentException.class)
    public void testList() throws InstantiationException, IllegalAccessException {
        ReusableObjectList<ValidExample> rol = new ReusableObjectList<ValidExample>(ValidExample.class);

        ValidExample ve = rol.getInstance();

        rol.add(ve);

        ve = rol.getInstance();
        ValidExample ve2 = rol.getInstance();

        Assert.assertEquals(ve, ve2);

        ve2 = new ValidExample();

        rol.add(ve2);
    }

}
