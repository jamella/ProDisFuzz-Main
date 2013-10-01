/*
 * This file is part of ProDisFuzz, modified on 01.10.13 23:25.
 * Copyright (c) 2013 Volker Nebelung <vnebelung@prodisfuzz.net>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */

package model.callable;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.concurrent.Callable;

public class FuzzingReconnectCallable implements Callable<Boolean> {

    private final InetSocketAddress target;
    private final int timeout;

    /**
     * Instatntiates a new fuzzing reconnect callable.
     *
     * @param target  the fuzzing target
     * @param timeout the timeout to wait before canceling the connection
     */
    public FuzzingReconnectCallable(final InetSocketAddress target, final int timeout) {
        this.target = target;
        this.timeout = timeout;
    }

    @Override
    public Boolean call() throws Exception {
        // Open connection
        try (final Socket socket = new Socket()) {
            socket.setSoTimeout(timeout);
            socket.connect(target, timeout);
            try (DataOutputStream out = new DataOutputStream(socket.getOutputStream());
                 DataInputStream in = new DataInputStream(socket.getInputStream())) {
                out.writeBoolean(false);
                in.readByte();
                return true;
            }
        } catch (SocketTimeoutException | SocketException e) {
            return false;
        }
    }
}
