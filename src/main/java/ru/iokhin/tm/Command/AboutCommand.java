package ru.iokhin.tm.Command;

import ru.iokhin.tm.Bootstrap;
import com.jcabi.manifests.Manifests;

public class AboutCommand extends AbstractCommand {

    private static final String name = "about";
    private static final String description = "about: Show build info";

    public AboutCommand(Bootstrap bootstrap) {
        super(bootstrap);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void execute() {
        System.out.println("Built-By: " + Manifests.read("Built-By"));
        System.out.println("Build-Jdk: " + Manifests.read("Build-Jdk"));
    }
}
