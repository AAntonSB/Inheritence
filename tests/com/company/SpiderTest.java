package com.company;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SpiderTest {
    @Test
    void create() {
        Spider spider = new Spider("Gustav", "Liten", true);
        assertEquals("Gustav", spider.getName());
    }
}