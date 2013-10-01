/*
 * This file is part of ProDisFuzz, modified on 01.10.13 23:25.
 * Copyright (c) 2013 Volker Nebelung <vnebelung@prodisfuzz.net>
 * This work is free. You can redistribute it and/or modify it under the
 * terms of the Do What The Fuck You Want To Public License, Version 2,
 * as published by Sam Hocevar. See the COPYING file for more details.
 */

package model.connector;

import model.modificator.FuzzedData;

public class TCPConnector extends AbstractConnector {

    @Override
    protected boolean connect() {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected boolean call(final FuzzedData data) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    protected void setTarget(final String... args) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
