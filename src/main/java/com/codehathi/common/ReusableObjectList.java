package com.codehathi.common;

public class ReusableObjectList<T> extends ObjectFactory<T> {

    boolean lastAdded = true;

    /**
     * Initializes the object factory with the given class
     *
     * @param cls Class of objects to create
     * @throws InstantiationException
     * @throws IllegalAccessException
     */
    public ReusableObjectList(Class<T> cls) throws IllegalAccessException, InstantiationException {
        super(cls);
    }

    /**
     * Gets the number of elements that are in use on the list.
     *
     * @return the number elements
     */
    public int size() {
        return getInUse();
    }

    /**
     * Returns the object at index {@code i}.
     * <p/>
     * If {@code i} is greater than the number of objects used, an exception is thrown.
     *
     * @param i
     * @return
     * @throws IndexOutOfBoundsException
     */
    public T get(int i) throws IndexOutOfBoundsException {
        if (i >= getInUse()) {
            throw new IndexOutOfBoundsException("index in accessible");
        }

        return factory.get(i);
    }

    @Override
    public void reclaimAll() {
        super.reclaimAll();
        lastAdded = true;
    }

    /**
     * Gets an object from the factory.  If the last one wasn't
     * inserted in the list, the last one is returned
     *
     * @return Object of the specified class
     */
    @Override
    public T getInstance() {
        if (!lastAdded) {
            return factory.get(getInUse() - 1);
        }

        lastAdded = false;

        return super.getInstance();
    }

    /**
     * Inserts the object in the list. If it isn't the last object
     * returned from {@code getInstance()}, an exception is thrown
     *
     * @return Object of the specified class
     */
    public void add(T in) throws IllegalArgumentException {
        if (in != factory.get(getInUse() - 1)) {
            throw new IllegalArgumentException("Added object isn't the same as the last instance given");
        }
        lastAdded = true;
    }

}
