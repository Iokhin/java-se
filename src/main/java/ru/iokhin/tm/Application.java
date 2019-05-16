package ru.iokhin.tm;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class Application {
    public static void main(String[] args) {
//        System.out.println(getManifestInfo());
        Bootstrap.init();
    }
}
