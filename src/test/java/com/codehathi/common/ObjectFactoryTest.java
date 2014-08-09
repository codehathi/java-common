package com.codehathi.common;

import com.codehathi.common.models.AbstractExample;
import com.codehathi.common.models.PrivateConstructorExample;
import com.codehathi.common.models.ValidExample;
import org.junit.Assert;
import org.junit.Test;

public class ObjectFactoryTest {

    @Test
    public void testSize() throws InstantiationException, IllegalAccessException {

        int toGenerate = 1000;

        ObjectFactory<ValidExample> factory = new ObjectFactory<ValidExample>(ValidExample.class);

        for (int i = 0; i < toGenerate; i++) {
            ValidExample temp = factory.getInstance();
        }

        Assert.assertEquals(toGenerate, factory.size());
        Assert.assertEquals(toGenerate, factory.getInUse());

        factory.reclaimAll();

        for (int i = 0; i < toGenerate / 2; i++) {
            ValidExample temp = factory.getInstance();
        }

        Assert.assertEquals(toGenerate, factory.size());
        Assert.assertEquals(toGenerate / 2, factory.getInUse());

    }

    @Test(expected = InstantiationException.class)
    public void testInstantiationException() throws InstantiationException, IllegalAccessException {
        ObjectFactory<AbstractExample> factory = new ObjectFactory<AbstractExample>(AbstractExample.class);
    }

    @Test(expected = IllegalAccessException.class)
    public void testIllegalAccessException() throws InstantiationException, IllegalAccessException {
        ObjectFactory<PrivateConstructorExample> factory = new ObjectFactory<PrivateConstructorExample>(PrivateConstructorExample.class);
    }
}
