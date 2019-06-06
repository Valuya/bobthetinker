package be.valuya.bob.core.util.path;

import be.valuya.bob.core.domain.BobException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class PathUtils {

    public static void createDireHierarchyIfNotExists(Path path) {
        // To handle ftp errors, create directories at each level after checking it does not exist yet
        int pathNameCount = path.getNameCount();
        Path curPath = path.getFileSystem().getRootDirectories().iterator().next();

        for (int nameIndex = 0; nameIndex < pathNameCount; nameIndex++) {
            Path nextName = path.getName(nameIndex);
            Path nextPath = curPath.resolve(nextName);
            boolean nextExists = Files.exists(nextPath);

            if (nextExists) {
                boolean nextIsDirectory = Files.isDirectory(nextPath);
                if (!nextIsDirectory) {
                    throw new BobException("Attempt to create directory " + nextPath + ", but this is already a file.");
                }
                curPath = nextPath;
            } else {
                try {
                    Files.createDirectory(nextPath);
                    curPath = nextPath;
                } catch (IOException e) {
                    throw new BobException("Failed to create path " + nextPath, e);
                }
            }
        }
    }
}
