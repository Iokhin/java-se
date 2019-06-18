package ru.iokhin.tm;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static junit.framework.TestCase.assertEquals;

public class HazelcastTest {

    @Test
    public void testTwoMemberMapSizes() {
        // start the first member
        HazelcastInstance h1 = Hazelcast.newHazelcastInstance(null);
        // get the map and put 1000 entries
        Map map1 = h1.getMap("testmap");
        for (int i = 0; i < 1000; i++) {
            map1.put(i, "value" + i);
        }
        // check the map size
        assertEquals(1000, map1.size());
        // start the second member
        HazelcastInstance h2 = Hazelcast.newHazelcastInstance(null);
        // get the same map from the second member
        Map map2 = h2.getMap("testmap");
        // check the size of map2
        assertEquals(1000, map2.size());
        // check the size of map1 again
        assertEquals(1000, map1.size());
        h1.getMap("testmap").clear();
        h2.getMap("testmap").clear();
    }

    @Test
    @Ignore
    public void membersTest() {
        final HazelcastInstance instance = Hazelcast.newHazelcastInstance();
        final HazelcastInstance instance1 = Hazelcast.newHazelcastInstance();
        assertEquals(2, instance.getCluster().getMembers().size());
        assertEquals(2, instance1.getCluster().getMembers().size());
    }

}
