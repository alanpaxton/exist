/*
 * eXist-db Open Source Native XML Database
 * Copyright (C) 2001 The eXist-db Authors
 *
 * info@exist-db.org
 * http://www.exist-db.org
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.exist.storage.serializers;

import org.exist.indexing.AbstractMatchListener;
import org.exist.storage.DBBroker;

/**
 * Base class for custom filters which can be applied to the serialization
 * stream generated by the {@link org.exist.storage.serializers.Serializer}.
 * Custom filters can be registered in conf.xml.
 *
 * TODO: MatchListener should probably be renamed into SerializationFilter
 * or similar.
 */
public abstract class CustomMatchListener extends AbstractMatchListener {

    private DBBroker broker;

    protected void reset() {
        setCurrentNode(null);
    }

    protected void setBroker(DBBroker broker) {
        this.broker = broker;
    }

    protected DBBroker getBroker() {
        return broker;
    }
}