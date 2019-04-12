package be.valuya.bob.core;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.MessageFormat;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BobTheReader {

    private static Logger LOGGER = Logger.getLogger(BobTheReader.class.getName());
    public static final String ADT_EXTENSION = ".adt";

    private Path resolveTablePath(BobFileConfiguration bobFileConfiguration, String tableName) {
        return resolveTablePathOptional(bobFileConfiguration, tableName)
                .orElseThrow(() -> {
                    Path baseFolderPath = bobFileConfiguration.getBaseFolderPath();
                    String baseFolderPathName = getPathFileNameString(baseFolderPath);

                    String fileName = getTableFileName(bobFileConfiguration, tableName);

                    String message = MessageFormat.format("Could not find file {0} in folder {1}", fileName, baseFolderPathName);
                    return new BobException(message);
                });
    }

    private String getPathFileNameString(Path archiveFolderPath) {
        return Optional.ofNullable(archiveFolderPath.getFileName())
                .map(Path::toString)
                .orElse("");
    }


    private Optional<Path> resolveTablePathOptional(BobFileConfiguration bobFileConfiguration, String tableName) {
        String fileName = getTableFileName(bobFileConfiguration, tableName);

        Path basePath = bobFileConfiguration.getBaseFolderPath();

        return resolveCaseInsensitivePathOptional(basePath, fileName);
    }

    public Optional<Path> resolveCaseInsensitivePathOptional(Path parentPath, String fileName) {
        try {
            boolean parentExists = Files.exists(parentPath);
            if (!parentExists) {
                return Optional.empty();
            }
            Path defaultPath = parentPath.resolve(fileName);
            if (Files.exists(defaultPath)) {
                return Optional.of(defaultPath);
            }
            if (fileName.endsWith(".dbf")) {
                String capitalizedExtensionFileName = fileName.replace(".dbf", ".DBF");
                Path capitalizedExtensionPath = parentPath.resolve(capitalizedExtensionFileName);
                if (Files.exists(capitalizedExtensionPath)) {
                    return Optional.of(capitalizedExtensionPath);
                }
            }

            long time0 = System.currentTimeMillis();
            Optional<Path> firstFoundPathOptional = Files.find(parentPath, 1,
                    (path, attr) -> isSamePathName(path, fileName))
                    .findFirst();
            long timeAfterWalk = System.currentTimeMillis();
            long deltaTimeWalk = timeAfterWalk - time0;
            LOGGER.log(Level.FINE, "****FIND TIME (" + fileName + "): " + deltaTimeWalk);
            return firstFoundPathOptional;
        } catch (IOException exception) {
            throw new BobException(exception);
        }
    }

    private boolean isSamePathName(Path path, String fileName) {
        return Optional.ofNullable(path.getFileName())
                .map(Path::toString)
                .map(fileName::equalsIgnoreCase)
                .orElse(false);
    }

    private String getTableFileName(BobFileConfiguration bobFileConfiguration, String tableName) {
        return tableName + ADT_EXTENSION;
    }

    public InputStream getTableInputStream(BobFileConfiguration bobFileConfiguration, String tableName) {
        Path path = resolveTablePath(bobFileConfiguration, tableName);
        return getFastInputStream(bobFileConfiguration, path);
    }

    private InputStream getFastInputStream(BobFileConfiguration bobFileConfiguration, Path path) {
        try {
            if (!bobFileConfiguration.isReadTablesToMemory()) {
                return Files.newInputStream(path);
            }

            long time0 = System.currentTimeMillis();
            byte[] bytes = Files.readAllBytes(path);
            long time1 = System.currentTimeMillis();
            long deltaTime = time1 - time0;
            LOGGER.log(Level.FINE, "READ TIME (" + path + "): " + deltaTime);

            return new ByteArrayInputStream(bytes);
        } catch (IOException exception) {
            throw new BobException(exception);

        }
    }


}
