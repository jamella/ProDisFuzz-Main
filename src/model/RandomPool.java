/*
 * This file is part of ProDisFuzz, modified on 01.10.13 23:25.
 * Copyright (c) 2013 Volker Nebelung <vnebelung@prodisfuzz.net>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */

package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class RandomPool extends Random {

    private static final RandomPool INSTANCE = new RandomPool();

    /**
     * Singelton Constructor.
     */
    private RandomPool() {
        super();
    }

    /**
     * Gets the only instance of Random.
     */
    public static RandomPool getInstance() {
        return INSTANCE;
    }

    /**
     * Generates an amount of random bytes within a range from 0 to the given length x 10000.
     *
     * @param length the length
     * @return the random bytes
     */
    public List<Byte> nextBloatBytes(final int length) {
        // Generate random bytes according to the maximum length of the given protocol part
        final int fuzzDataLength = nextInt(length * 10000 + 1);
        final byte[] bytes = new byte[fuzzDataLength];
        nextBytes(bytes);
        final List<Byte> lBytes = new ArrayList<>(bytes.length);
        for (final byte b : bytes) {
            lBytes.add(b);
        }
        return lBytes;
    }
}
