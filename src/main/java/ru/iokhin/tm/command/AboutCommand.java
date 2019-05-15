package ru.iokhin.tm.command;

import ru.iokhin.tm.Bootstrap;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Enumeration;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

public class AboutCommand extends AbstractCommand {

    public AboutCommand(Bootstrap bootstrap, String name, String description) {
        super(bootstrap, name, description);
    }

    @Override
    public void execute() {

    }

    // TODO: 15.05.2019 Как читать манифест сгенеренный maven???
//    public static String getManifestInfo() {
//        Enumeration resEnum;
//        try {
//            resEnum = Thread.currentThread().getContextClassLoader().getResources(JarFile.MANIFEST_NAME);
//            while (resEnum.hasMoreElements()) {
//                try {
//                    URL url = (URL) resEnum.nextElement();
//                    InputStream is = url.openStream();
//                    if (is != null) {
//                        Manifest manifest = new Manifest(is);
//                        Attributes mainAttribs = manifest.getMainAttributes();
//                        String version = mainAttribs.getValue("Built-By");
//                        if (version != null) {
//                            return version;
//                        }
//                    }
//                } catch (Exception e) {
//                    // Silently ignore wrong manifests on classpath?
//                }
//            }
//        } catch (IOException e1) {
//            // Silently ignore wrong manifests on classpath?
//        }
//        return null;
//    }
//
//    public static void main(String[] args) {
//        System.out.println(getManifestInfo());
//    }
}
