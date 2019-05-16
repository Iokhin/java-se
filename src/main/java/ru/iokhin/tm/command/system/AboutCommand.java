package ru.iokhin.tm.command.system;

import ru.iokhin.tm.Bootstrap;
import ru.iokhin.tm.command.AbstractCommand;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class AboutCommand extends AbstractCommand {

    public AboutCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public boolean security() {
        return false;
    }

    @Override
    public String name() {
        return "about";
    }

    @Override
    public String description() {
        return "Show application's info";
    }

    @Override
    public void execute() {
        System.out.println("task-manager");
        System.out.println(getManifestInfo());
    }

    private static String getManifestInfo() {
        Enumeration resEnum;
        try {
            resEnum = Thread.currentThread().getContextClassLoader().getResources(JarFile.MANIFEST_NAME);
            while (resEnum.hasMoreElements()) {
                try {
                    URL url = (URL)resEnum.nextElement();
                    InputStream is = url.openStream();
                    if (is != null) {
                        Manifest manifest = new Manifest(is);
                        Attributes mainAttribs = manifest.getMainAttributes();
                        String version = mainAttribs.getValue("Implementation-Build");
                        if(version != null) {
                            return version;
                        }
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        return null;
    }
}
