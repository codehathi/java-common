package com.codehathi.common;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a factory that will create new objects and store them in an array
 * to be reused later.  This reduces the number of objects created thus reducing
 * the number of time the garbage collector runs.
 *
 * @param <Type> The type of objects to manufacture
 * @author David D. Jenkins
 */
public class ObjectFactory<Type> {
    protected List<Type> factory;
    private int inUse;
    private Class<Type> cls;

    /**
     * Initializes the object factory with the given class
     *
     * @param cls Class of objects to create
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public ObjectFactory(Class<Type> cls) throws IllegalAccessException, InstantiationException {
        this.cls = cls;
        this.factory = new ArrayList<Type>();
        this.inUse = 0;

        // create an object to force an exception
        factory.add(this.cls.newInstance());
    }

    private void manufacture() {
        try {
            factory.add(this.cls.newInstance());
        } catch (InstantiationException e) {
            // swallow exception
        } catch (IllegalAccessException e) {
            // swallow exception
        }
    }

    /**
     * Creates a specified number of objects
     *
     * @param numToMake Number of objects to make
     */
    @Deprecated
    private void manufactureTypes(int numToMake) {
        for (int i = 0; i < numToMake; i++) {
            manufacture();
        }
    }

    /**
     * Gets an object from the factory
     *
     * @return Object of the specified class
     */
    public Type getInstance() {
        if (inUse == factory.size()) {
            manufacture();
        }
        return factory.get(inUse++);
    }

    /**
     * Reset the factory to start reusing objects
     */
    public void reclaimAll() {
        inUse = 0;
    }

    public int getInUse() { return inUse; }

    public int size() { return factory.size(); }

}
