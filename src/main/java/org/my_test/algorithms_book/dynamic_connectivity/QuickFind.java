package org.my_test.algorithms_book.dynamic_connectivity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class QuickFind extends AbstractUnionFind {
    // Map<site, ConnectId>
    private final Map<Object, String> siteToConnectionIdMap = new HashMap<>();

    public QuickFind(Object[] array) {
        super(array);
        for (Object site : array) {
            siteToConnectionIdMap.put(site, UUID.randomUUID().toString());
        }
    }

    @Override
    public String find(Object p) {
        return this.siteToConnectionIdMap.get(p);
    }

    @Override
    public void union(Object p, Object q) {
        if (!connected(p, q)) {
            String pConnectId = this.siteToConnectionIdMap.get(p);
            String qConnectId = this.siteToConnectionIdMap.get(q);
            siteToConnectionIdMap.forEach((object, connectId) -> {
                if (pConnectId.equals(connectId)) {
                    siteToConnectionIdMap.put(object, qConnectId);
                }
            });
        }
    }
}
