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
package org.exist.xquery;

import org.exist.dom.persistent.DocumentImpl;
import org.exist.dom.persistent.ExtNodeSet;
import org.exist.dom.persistent.NodeProxy;
import org.exist.dom.persistent.NodeSet;
import org.exist.numbering.NodeId;

public class AncestorSelector implements NodeSelector {

    private NodeSet ancestors;
    private NodeSet descendants = null;
    private int contextId;
    private boolean includeSelf;
    private boolean copyMatches;

    public AncestorSelector(NodeSet descendants, int contextId,
            boolean includeSelf, boolean copyMatches) {
        super();
        this.contextId = contextId;
        this.includeSelf = includeSelf;
        this.copyMatches = copyMatches;
        if (descendants instanceof ExtNodeSet)
            {this.descendants = descendants;}
        else
            {this.ancestors = descendants.getAncestors(contextId, includeSelf);}
    }

    public NodeProxy match(DocumentImpl doc, NodeId nodeId) {
        if (descendants == null)
            {return ancestors.get(doc, nodeId);}
        else
            {return ((ExtNodeSet) descendants).hasDescendantsInSet(doc, nodeId,
                includeSelf, contextId, copyMatches);}
    }
}
