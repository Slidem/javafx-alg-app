package com.algorithms.utils;

import com.algorithms.utils.exceptions.ClassFinderException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.lang.Thread.currentThread;
import static java.nio.file.Files.walk;

/**
 * @author Mihai Alexandru
 * @date 24.07.2018
 */
public class ClassFinder {

    private ClassFinder() {
    }

    public static List<Class<?>> getClasses(String packageName) {
        if (StringUtils.isEmpty(packageName)) {
            throw new IllegalArgumentException("Package name cannot be empty");
        }

        File packageFolder = getPackageFolder(packageName);
        Path packageFolderPath = getPackageFolderPath(packageFolder);

        try (Stream<Class<?>> s =
                     walk(packageFolderPath)
                             .filter(ClassFinder::isClassType)
                             .map(p -> getClassFromPath(packageName, p));) {

            return s.collect(Collectors.toList());

        } catch (IOException e) {
            throw new ClassFinderException(e);
        }
    }

    public static File getPackageFolder(String packageName) {
        String packageFolderUrl = StringUtils.toFolderUrl(packageName);
        URL packageUrl = currentThread().getContextClassLoader().getResource(packageFolderUrl);
        if (packageUrl == null) {
            throw new ClassFinderException("No resource found for pacakge name: " + packageName);
        }
        return new File(packageUrl.getFile());
    }

    public static File getFileFromPackage(String packageName, String fileName) throws IOException {
        if (packageName == null || fileName == null) {
            throw new NullPointerException("Package name or fileName cannot be null.");
        }
        File packageFolder = getPackageFolder(packageName);

        List<File> files = null;
        try (Stream<File> fileStream =
                     walk(Paths.get(packageFolder.getAbsolutePath()))
                             .filter(path -> fileName.equals(path.getFileName().toString()))
                             .map(Path::toFile)) {
            files = fileStream.collect(Collectors.toList());
        } catch (IOException e) {
            throw new ClassFinderException(e);
        }

        int filesFound = files.size();
        if (filesFound == 0) {
            throw new ClassFinderException("No files found for package: " + packageName + " and file name : " + fileName);
        }
        if (filesFound > 1) {
            throw new ClassFinderException("Multiple files found for fileName : " + fileName + ". Make sure your file name is unique!");
        }

        return files.iterator().next();
    }


    // ----------------------------------------------------------------------

    private static Path getPackageFolderPath(File packageFolder) {
        return Paths.get(packageFolder.getAbsolutePath());
    }

    private static boolean isClassType(Path p) {
        return p.getFileName().toString().contains(".class");
    }


    private static Class<?> getClassFromPath(String packageName, Path path) {
        String packagePath = StringUtils.toPackageName(path.toString());
        int packageIdx = packagePath.indexOf(packageName);
        String fullClassName = packagePath.substring(packageIdx);
        try {
            return Class.forName(removeClassSuffix(fullClassName));
        } catch (ClassNotFoundException e) {
            throw new ClassFinderException(e);
        }
    }

    private static String removeClassSuffix(String className) {
        return className.replace(".class", "");
    }

}
