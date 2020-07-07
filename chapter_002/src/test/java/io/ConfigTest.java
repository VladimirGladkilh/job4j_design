package io;

import org.junit.Test;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ConfigTest  {
    @Test
    public void testAppProperties() {
        String path = "./data/pair_with_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(config.value("cuba.mainMessagePack"), is("+ru.starkovgrp.awardsregistry.core"));
    }

    @Test
    public void whenPairWithoutComment() {
        String path = "./data/pair_without_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("name"),
                is("Petr Arsentev")
        );
    }
    @Test
    public void whenPairWithComment() {
        String path = "./data/pair_with_comments.properties";
        Config config = new Config(path);
        config.load();
        assertThat(
                config.value("reporting.office.path"),
                is("/usr/lib/libreoffice")
        );
    }
}