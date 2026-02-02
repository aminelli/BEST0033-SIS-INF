package com.corso.samples.javaadv;

import java.io.*;
import java.nio.*;
import java.nio.channels.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.nio.file.attribute.*;
import java.util.*;
import java.util.stream.*;
import java.util.zip.*;

/**
 * Esempio completo e avanzato su Input/Output in Java
 * Include:
 * - Stream di byte (InputStream, OutputStream)
 * - Stream di caratteri (Reader, Writer)
 * - Buffered I/O
 * - File I/O (FileInputStream, FileOutputStream, FileReader, FileWriter)
 * - PrintWriter e PrintStream
 * - Data I/O (DataInputStream, DataOutputStream)
 * - Object I/O e Serializzazione
 * - NIO.2 (New I/O) - java.nio.file
 * - Files e Paths API
 * - Channel e Buffer
 * - Memory-Mapped Files
 * - RandomAccessFile
 * - File attributes e metadata
 * - Directory operations
 * - File monitoring (WatchService)
 * - Compressione (ZIP, GZIP)
 * - Best practices
 */
public class InputOutputDemo {

    private static final String TEMP_DIR = System.getProperty("java.io.tmpdir") + "io-demo/";
    
    public static void sample() {
        InputOutputDemo demo = new InputOutputDemo();
        
        System.out.println("=== INPUT/OUTPUT IN JAVA - GUIDA COMPLETA ===\n");
        
        // Preparazione directory di lavoro
        demo.setupWorkingDirectory();
        
        // I/O di base
        demo.byteStreamIO();
        demo.characterStreamIO();
        demo.bufferedIO();
        
        // File I/O
        demo.fileInputOutput();
        demo.printWriterDemo();
        demo.dataInputOutput();
        demo.objectSerialization();
        
        // NIO.2 - New I/O
        demo.pathsAndFilesAPI();
        demo.fileOperations();
        demo.directoryOperations();
        demo.fileAttributes();
        demo.walkingFileTree();
        
        // I/O Avanzato
        demo.channelAndBufferIO();
        demo.memoryMappedFiles();
        demo.randomAccessFileDemo();
        demo.fileCompression();
        
        // Monitoring e Best Practices
        demo.fileWatchService();
        demo.bestPractices();
        
        // Cleanup
        demo.cleanup();
    }

    // ==================== SETUP ====================

    private void setupWorkingDirectory() {
        System.out.println("=== SETUP DIRECTORY ===");
        
        try {
            Path dir = Paths.get(TEMP_DIR);
            if (!Files.exists(dir)) {
                Files.createDirectories(dir);
                System.out.println("Directory creata: " + TEMP_DIR);
            } else {
                System.out.println("Directory già esistente: " + TEMP_DIR);
            }
        } catch (IOException e) {
            System.err.println("Errore setup: " + e.getMessage());
        }
        
        System.out.println();
    }

    // ==================== BYTE STREAM I/O ====================

    public void byteStreamIO() {
        System.out.println("=== BYTE STREAM I/O ===");

        String filename = TEMP_DIR + "bytes.dat";
        
        // Scrittura con OutputStream
        try (FileOutputStream fos = new FileOutputStream(filename)) {
            byte[] data = {65, 66, 67, 68, 69}; // A, B, C, D, E
            fos.write(data);
            System.out.println("Scritti " + data.length + " byte in " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Lettura con InputStream
        try (FileInputStream fis = new FileInputStream(filename)) {
            int byteRead;
            System.out.print("Byte letti: ");
            while ((byteRead = fis.read()) != -1) {
                System.out.print((char) byteRead + " ");
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Lettura con buffer
        try (FileInputStream fis = new FileInputStream(filename)) {
            byte[] buffer = new byte[1024];
            int bytesRead = fis.read(buffer);
            System.out.println("Letti " + bytesRead + " byte con buffer");
            System.out.print("Contenuto: ");
            for (int i = 0; i < bytesRead; i++) {
                System.out.print((char) buffer[i] + " ");
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // ByteArrayInputStream e ByteArrayOutputStream
        System.out.println("\nByteArray streams:");
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            baos.write("Hello World".getBytes());
            byte[] bytes = baos.toByteArray();
            
            try (ByteArrayInputStream bais = new ByteArrayInputStream(bytes)) {
                int data;
                System.out.print("Da ByteArray: ");
                while ((data = bais.read()) != -1) {
                    System.out.print((char) data);
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== CHARACTER STREAM I/O ====================

    public void characterStreamIO() {
        System.out.println("=== CHARACTER STREAM I/O ===");

        String filename = TEMP_DIR + "text.txt";
        
        // Scrittura con Writer
        try (FileWriter fw = new FileWriter(filename)) {
            fw.write("Prima riga\n");
            fw.write("Seconda riga\n");
            fw.write("Terza riga con caratteri speciali: à è ì ò ù €\n");
            System.out.println("Testo scritto in " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Lettura con Reader
        try (FileReader fr = new FileReader(filename)) {
            int charRead;
            System.out.print("Contenuto: ");
            while ((charRead = fr.read()) != -1) {
                System.out.print((char) charRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // StringReader e StringWriter
        System.out.println("\nStringReader/Writer:");
        String input = "Test con StringReader";
        try (StringReader sr = new StringReader(input);
             StringWriter sw = new StringWriter()) {
            
            int ch;
            while ((ch = sr.read()) != -1) {
                sw.write(Character.toUpperCase(ch));
            }
            System.out.println("StringWriter output: " + sw.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        // CharArrayReader e CharArrayWriter
        System.out.println("\nCharArrayReader/Writer:");
        try (CharArrayWriter caw = new CharArrayWriter()) {
            caw.write("Hello");
            caw.write(" ");
            caw.write("CharArray");
            
            char[] chars = caw.toCharArray();
            try (CharArrayReader car = new CharArrayReader(chars)) {
                int c;
                System.out.print("CharArray output: ");
                while ((c = car.read()) != -1) {
                    System.out.print((char) c);
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== BUFFERED I/O ====================

    public void bufferedIO() {
        System.out.println("=== BUFFERED I/O ===");

        String filename = TEMP_DIR + "buffered.txt";
        
        // BufferedWriter - più efficiente per scrittura
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filename))) {
            for (int i = 1; i <= 5; i++) {
                bw.write("Riga numero " + i);
                bw.newLine(); // Scrive line separator di sistema
            }
            System.out.println("Scritte 5 righe con BufferedWriter");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // BufferedReader - più efficiente per lettura
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            System.out.println("Lettura con BufferedReader:");
            while ((line = br.readLine()) != null) {
                System.out.println("  " + line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // BufferedInputStream e BufferedOutputStream
        String binFile = TEMP_DIR + "buffered.bin";
        try (BufferedOutputStream bos = new BufferedOutputStream(
                new FileOutputStream(binFile))) {
            for (int i = 0; i < 100; i++) {
                bos.write(i);
            }
            System.out.println("\nScritti 100 byte con BufferedOutputStream");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedInputStream bis = new BufferedInputStream(
                new FileInputStream(binFile))) {
            System.out.print("Primi 10 byte: ");
            for (int i = 0; i < 10; i++) {
                System.out.print(bis.read() + " ");
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Confronto prestazioni: con e senza buffer
        System.out.println("\nConfronto prestazioni:");
        String testFile = TEMP_DIR + "perf-test.txt";
        
        // Senza buffer
        long start = System.nanoTime();
        try (FileWriter fw = new FileWriter(testFile)) {
            for (int i = 0; i < 10000; i++) {
                fw.write("Line " + i + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long withoutBuffer = System.nanoTime() - start;

        // Con buffer
        start = System.nanoTime();
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(testFile))) {
            for (int i = 0; i < 10000; i++) {
                bw.write("Line " + i + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        long withBuffer = System.nanoTime() - start;

        System.out.println("Senza buffer: " + (withoutBuffer / 1_000_000) + " ms");
        System.out.println("Con buffer: " + (withBuffer / 1_000_000) + " ms");
        System.out.println("Speedup: " + (withoutBuffer / withBuffer) + "x");

        System.out.println();
    }

    // ==================== FILE INPUT/OUTPUT ====================

    public void fileInputOutput() {
        System.out.println("=== FILE INPUT/OUTPUT ===");

        // File class (vecchia API)
        File file = new File(TEMP_DIR + "test-file.txt");
        System.out.println("File info:");
        System.out.println("  Exists: " + file.exists());
        System.out.println("  Is file: " + file.isFile());
        System.out.println("  Is directory: " + file.isDirectory());
        System.out.println("  Can read: " + file.canRead());
        System.out.println("  Can write: " + file.canWrite());
        System.out.println("  Absolute path: " + file.getAbsolutePath());

        // Creazione file
        try {
            if (file.createNewFile()) {
                System.out.println("\nFile creato: " + file.getName());
            } else {
                System.out.println("\nFile già esistente");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Scrittura su file
        try (FileOutputStream fos = new FileOutputStream(file)) {
            String content = "Contenuto del file di test\nCon più righe\n";
            fos.write(content.getBytes());
            System.out.println("Contenuto scritto");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Lettura da file
        try (FileInputStream fis = new FileInputStream(file)) {
            byte[] buffer = new byte[(int) file.length()];
            fis.read(buffer);
            String content = new String(buffer);
            System.out.println("Contenuto letto:");
            System.out.println(content);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // File metadata
        System.out.println("Metadata:");
        System.out.println("  Size: " + file.length() + " bytes");
        System.out.println("  Last modified: " + new Date(file.lastModified()));

        // Listing directory
        File dir = new File(TEMP_DIR);
        System.out.println("\nFile nella directory:");
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                System.out.println("  " + f.getName() + 
                    (f.isDirectory() ? " [DIR]" : " (" + f.length() + " bytes)"));
            }
        }

        System.out.println();
    }

    // ==================== PRINTWRITER DEMO ====================

    public void printWriterDemo() {
        System.out.println("=== PRINTWRITER E PRINTSTREAM ===");

        String filename = TEMP_DIR + "print-output.txt";
        
        // PrintWriter - per file di testo
        try (PrintWriter pw = new PrintWriter(new FileWriter(filename))) {
            pw.println("PrintWriter Demo");
            pw.println("Numero: " + 42);
            pw.println("Double: " + 3.14159);
            pw.println("Boolean: " + true);
            pw.printf("Formatted: %s - %d - %.2f%n", "test", 100, 99.99);
            pw.println();
            pw.print("No newline");
            
            // Controllo errori
            if (pw.checkError()) {
                System.out.println("Errore durante scrittura");
            } else {
                System.out.println("Scritto con PrintWriter in " + filename);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Lettura e stampa
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            System.out.println("\nContenuto:");
            br.lines().forEach(line -> System.out.println("  " + line));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // PrintStream - System.out è un PrintStream
        System.out.println("\nPrintStream (System.out):");
        System.out.printf("Formatted output: %s = %d%n", "answer", 42);
        
        // PrintStream su file
        String binFile = TEMP_DIR + "print-stream.txt";
        try (PrintStream ps = new PrintStream(new FileOutputStream(binFile))) {
            ps.println("PrintStream output");
            ps.printf("Number: %d%n", 123);
            System.out.println("Scritto con PrintStream");
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== DATA INPUT/OUTPUT ====================

    public void dataInputOutput() {
        System.out.println("=== DATA INPUT/OUTPUT ===");

        String filename = TEMP_DIR + "data.dat";
        
        // Scrittura dati primitivi
        try (DataOutputStream dos = new DataOutputStream(
                new FileOutputStream(filename))) {
            
            dos.writeBoolean(true);
            dos.writeByte(127);
            dos.writeShort(32000);
            dos.writeChar('A');
            dos.writeInt(123456);
            dos.writeLong(123456789L);
            dos.writeFloat(3.14f);
            dos.writeDouble(2.718281828);
            dos.writeUTF("Stringa UTF-8");
            
            System.out.println("Dati primitivi scritti in " + filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Lettura dati primitivi (stesso ordine!)
        try (DataInputStream dis = new DataInputStream(
                new FileInputStream(filename))) {
            
            boolean b = dis.readBoolean();
            byte by = dis.readByte();
            short s = dis.readShort();
            char c = dis.readChar();
            int i = dis.readInt();
            long l = dis.readLong();
            float f = dis.readFloat();
            double d = dis.readDouble();
            String str = dis.readUTF();
            
            System.out.println("\nDati letti:");
            System.out.println("  boolean: " + b);
            System.out.println("  byte: " + by);
            System.out.println("  short: " + s);
            System.out.println("  char: " + c);
            System.out.println("  int: " + i);
            System.out.println("  long: " + l);
            System.out.println("  float: " + f);
            System.out.println("  double: " + d);
            System.out.println("  String: " + str);
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== OBJECT SERIALIZATION ====================

    public void objectSerialization() {
        System.out.println("=== OBJECT SERIALIZATION ===");

        String filename = TEMP_DIR + "person.ser";
        
        // Scrittura oggetto
        Person person = new Person("Mario Rossi", 35, "mario@example.com");
        person.addHobby("Reading");
        person.addHobby("Gaming");
        
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(filename))) {
            oos.writeObject(person);
            System.out.println("Oggetto serializzato: " + person);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Lettura oggetto
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(filename))) {
            Person loaded = (Person) ois.readObject();
            System.out.println("Oggetto deserializzato: " + loaded);
            System.out.println("Hobbies: " + loaded.getHobbies());
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        // Serializzazione multipla
        String multiFile = TEMP_DIR + "people.ser";
        List<Person> people = Arrays.asList(
            new Person("Alice", 25, "alice@test.com"),
            new Person("Bob", 30, "bob@test.com"),
            new Person("Charlie", 28, "charlie@test.com")
        );

        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(multiFile))) {
            oos.writeObject(people);
            System.out.println("\nLista serializzata con " + people.size() + " persone");
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(multiFile))) {
            @SuppressWarnings("unchecked")
            List<Person> loadedPeople = (List<Person>) ois.readObject();
            System.out.println("Lista deserializzata:");
            loadedPeople.forEach(p -> System.out.println("  " + p));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== PATHS AND FILES API (NIO.2) ====================

    public void pathsAndFilesAPI() {
        System.out.println("=== PATHS AND FILES API (NIO.2) ===");

        // Path creation
        Path path1 = Paths.get(TEMP_DIR, "test.txt");
        Path path2 = Paths.get(TEMP_DIR + "subdir/file.txt");
        Path path3 = Path.of(TEMP_DIR, "another.txt"); // Java 11+

        System.out.println("Path info:");
        System.out.println("  path1: " + path1);
        System.out.println("  Absolute: " + path1.toAbsolutePath());
        System.out.println("  Parent: " + path1.getParent());
        System.out.println("  Filename: " + path1.getFileName());
        System.out.println("  Root: " + path1.getRoot());

        // Path operations
        Path base = Paths.get(TEMP_DIR);
        Path file = Paths.get(TEMP_DIR, "subdir", "file.txt");
        Path relative = base.relativize(file);
        System.out.println("\n  Relative path: " + relative);
        
        Path resolved = base.resolve("newfile.txt");
        System.out.println("  Resolved: " + resolved);

        // Normalize path
        Path messyPath = Paths.get(TEMP_DIR + "dir1/../dir2/./file.txt");
        Path normalized = messyPath.normalize();
        System.out.println("\n  Messy: " + messyPath);
        System.out.println("  Normalized: " + normalized);

        // Files API - scrittura
        Path testFile = Paths.get(TEMP_DIR, "nio-test.txt");
        try {
            List<String> lines = Arrays.asList(
                "Prima riga",
                "Seconda riga",
                "Terza riga con UTF-8: à è ì ò ù €"
            );
            Files.write(testFile, lines, StandardCharsets.UTF_8);
            System.out.println("\nFile scritto con Files.write()");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Files API - lettura
        try {
            List<String> lines = Files.readAllLines(testFile, StandardCharsets.UTF_8);
            System.out.println("File letto con Files.readAllLines():");
            lines.forEach(line -> System.out.println("  " + line));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Files API - lettura come stream
        try {
            System.out.println("\nLettura come Stream:");
            Files.lines(testFile)
                .map(String::toUpperCase)
                .forEach(line -> System.out.println("  " + line));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Files API - byte array
        try {
            byte[] bytes = "Binary data".getBytes();
            Path binFile = Paths.get(TEMP_DIR, "binary.dat");
            Files.write(binFile, bytes);
            
            byte[] readBytes = Files.readAllBytes(binFile);
            System.out.println("\nByte array: " + new String(readBytes));
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== FILE OPERATIONS ====================

    public void fileOperations() {
        System.out.println("=== FILE OPERATIONS ===");

        try {
            // Create file
            Path file = Paths.get(TEMP_DIR, "operations-test.txt");
            Files.write(file, Arrays.asList("Test content"), StandardCharsets.UTF_8);
            System.out.println("File creato: " + file.getFileName());

            // File exists
            System.out.println("Exists: " + Files.exists(file));
            System.out.println("Is regular file: " + Files.isRegularFile(file));
            System.out.println("Is directory: " + Files.isDirectory(file));
            System.out.println("Is readable: " + Files.isReadable(file));
            System.out.println("Is writable: " + Files.isWritable(file));

            // File size
            long size = Files.size(file);
            System.out.println("Size: " + size + " bytes");

            // Copy file
            Path copy = Paths.get(TEMP_DIR, "operations-copy.txt");
            Files.copy(file, copy, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("\nFile copiato: " + copy.getFileName());

            // Move file
            Path moved = Paths.get(TEMP_DIR, "operations-moved.txt");
            Files.move(copy, moved, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File spostato: " + moved.getFileName());

            // Delete file
            Files.delete(moved);
            System.out.println("File eliminato: " + moved.getFileName());

            // Delete if exists
            boolean deleted = Files.deleteIfExists(file);
            System.out.println("Delete if exists: " + deleted);

            // Create temp file
            Path tempFile = Files.createTempFile("demo-", ".tmp");
            System.out.println("\nTemp file: " + tempFile);
            Files.write(tempFile, Arrays.asList("Temporary content"));
            
            // Delete on exit (tramite shutdown hook)
            tempFile.toFile().deleteOnExit();

            // Create temp directory
            Path tempDir = Files.createTempDirectory("demo-dir-");
            System.out.println("Temp dir: " + tempDir);

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== DIRECTORY OPERATIONS ====================

    public void directoryOperations() {
        System.out.println("=== DIRECTORY OPERATIONS ===");

        try {
            // Create directory
            Path dir = Paths.get(TEMP_DIR, "test-dir");
            if (!Files.exists(dir)) {
                Files.createDirectory(dir);
                System.out.println("Directory creata: " + dir.getFileName());
            }

            // Create directories (recursive)
            Path nestedDir = Paths.get(TEMP_DIR, "level1/level2/level3");
            Files.createDirectories(nestedDir);
            System.out.println("Directory annidate create: " + nestedDir);

            // Create some files in directory
            for (int i = 1; i <= 3; i++) {
                Path file = dir.resolve("file" + i + ".txt");
                Files.write(file, Arrays.asList("Content " + i));
            }
            System.out.println("Creati 3 file in " + dir.getFileName());

            // List directory contents
            System.out.println("\nContenuto directory:");
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(dir)) {
                for (Path entry : stream) {
                    System.out.println("  " + entry.getFileName());
                }
            }

            // List with filter
            System.out.println("\nSolo file .txt:");
            try (DirectoryStream<Path> stream = 
                    Files.newDirectoryStream(dir, "*.txt")) {
                for (Path entry : stream) {
                    System.out.println("  " + entry.getFileName());
                }
            }

            // List as Stream (Java 8+)
            System.out.println("\nCon Stream API:");
            Files.list(dir)
                .map(Path::getFileName)
                .forEach(name -> System.out.println("  " + name));

            // Find files
            System.out.println("\nCerca file che iniziano con 'file':");
            Files.find(dir, 1, 
                (path, attrs) -> path.getFileName().toString().startsWith("file"))
                .forEach(path -> System.out.println("  " + path.getFileName()));

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== FILE ATTRIBUTES ====================

    public void fileAttributes() {
        System.out.println("=== FILE ATTRIBUTES ===");

        try {
            Path file = Paths.get(TEMP_DIR, "attr-test.txt");
            Files.write(file, Arrays.asList("Test"), StandardCharsets.UTF_8);

            // Basic attributes
            BasicFileAttributes attrs = Files.readAttributes(file, BasicFileAttributes.class);
            System.out.println("Basic attributes:");
            System.out.println("  Creation time: " + attrs.creationTime());
            System.out.println("  Last modified: " + attrs.lastModifiedTime());
            System.out.println("  Last access: " + attrs.lastAccessTime());
            System.out.println("  Size: " + attrs.size() + " bytes");
            System.out.println("  Is directory: " + attrs.isDirectory());
            System.out.println("  Is regular file: " + attrs.isRegularFile());
            System.out.println("  Is symbolic link: " + attrs.isSymbolicLink());

            // Get single attribute
            FileTime lastModified = (FileTime) Files.getAttribute(file, "lastModifiedTime");
            System.out.println("\nSingle attribute:");
            System.out.println("  Last modified: " + lastModified);

            // Set attribute
            FileTime newTime = FileTime.fromMillis(System.currentTimeMillis());
            Files.setAttribute(file, "lastModifiedTime", newTime);
            System.out.println("  Modified time updated");

            // File owner (POSIX systems)
            try {
                UserPrincipal owner = Files.getOwner(file);
                System.out.println("\nOwner: " + owner.getName());
            } catch (UnsupportedOperationException e) {
                System.out.println("\nOwner info not supported on this system");
            }

            // POSIX attributes (Unix/Linux)
            if (FileSystems.getDefault().supportedFileAttributeViews().contains("posix")) {
                PosixFileAttributes posixAttrs = 
                    Files.readAttributes(file, PosixFileAttributes.class);
                System.out.println("\nPOSIX attributes:");
                System.out.println("  Owner: " + posixAttrs.owner().getName());
                System.out.println("  Group: " + posixAttrs.group().getName());
                System.out.println("  Permissions: " + posixAttrs.permissions());
            }

            // DOS attributes (Windows)
            if (FileSystems.getDefault().supportedFileAttributeViews().contains("dos")) {
                DosFileAttributes dosAttrs = 
                    Files.readAttributes(file, DosFileAttributes.class);
                System.out.println("\nDOS attributes:");
                System.out.println("  Hidden: " + dosAttrs.isHidden());
                System.out.println("  Archive: " + dosAttrs.isArchive());
                System.out.println("  System: " + dosAttrs.isSystem());
                System.out.println("  ReadOnly: " + dosAttrs.isReadOnly());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== WALKING FILE TREE ====================

    public void walkingFileTree() {
        System.out.println("=== WALKING FILE TREE ===");

        try {
            // Create directory structure
            Path root = Paths.get(TEMP_DIR, "walk-test");
            Files.createDirectories(root);
            Files.createDirectories(root.resolve("dir1"));
            Files.createDirectories(root.resolve("dir2/subdir"));
            Files.write(root.resolve("file1.txt"), Arrays.asList("File 1"));
            Files.write(root.resolve("dir1/file2.txt"), Arrays.asList("File 2"));
            Files.write(root.resolve("dir2/file3.txt"), Arrays.asList("File 3"));
            Files.write(root.resolve("dir2/subdir/file4.txt"), Arrays.asList("File 4"));

            // Walk file tree
            System.out.println("Tutti i file:");
            Files.walk(root)
                .filter(Files::isRegularFile)
                .forEach(file -> System.out.println("  " + root.relativize(file)));

            // Walk with depth limit
            System.out.println("\nFile (depth 1):");
            Files.walk(root, 1)
                .filter(Files::isRegularFile)
                .forEach(file -> System.out.println("  " + root.relativize(file)));

            // Find all .txt files
            System.out.println("\nTutti i file .txt:");
            Files.walk(root)
                .filter(path -> path.toString().endsWith(".txt"))
                .forEach(file -> System.out.println("  " + root.relativize(file)));

            // Custom FileVisitor
            System.out.println("\nCon FileVisitor custom:");
            Files.walkFileTree(root, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    System.out.println("  File: " + file.getFileName() + 
                        " (" + attrs.size() + " bytes)");
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) {
                    System.out.println("  [DIR] " + dir.getFileName());
                    return FileVisitResult.CONTINUE;
                }
            });

            // Calculate total size
            long totalSize = Files.walk(root)
                .filter(Files::isRegularFile)
                .mapToLong(path -> {
                    try {
                        return Files.size(path);
                    } catch (IOException e) {
                        return 0;
                    }
                })
                .sum();
            System.out.println("\nDimensione totale: " + totalSize + " bytes");

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== CHANNEL AND BUFFER I/O ====================

    public void channelAndBufferIO() {
        System.out.println("=== CHANNEL AND BUFFER I/O ===");

        String filename = TEMP_DIR + "channel-test.txt";
        
        // Write with Channel and Buffer
        try (FileChannel channel = FileChannel.open(
                Paths.get(filename), 
                StandardOpenOption.CREATE, 
                StandardOpenOption.WRITE)) {
            
            String content = "Channel and Buffer I/O Demo\n";
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            buffer.put(content.getBytes());
            buffer.flip(); // Switch to read mode
            channel.write(buffer);
            
            System.out.println("Scritto con Channel: " + content.trim());
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Read with Channel and Buffer
        try (FileChannel channel = FileChannel.open(
                Paths.get(filename), 
                StandardOpenOption.READ)) {
            
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            int bytesRead = channel.read(buffer);
            buffer.flip(); // Switch to read mode
            
            byte[] data = new byte[bytesRead];
            buffer.get(data);
            System.out.println("Letto con Channel: " + new String(data).trim());
            
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Buffer operations
        System.out.println("\nBuffer operations:");
        ByteBuffer buf = ByteBuffer.allocate(10);
        System.out.println("  Capacity: " + buf.capacity());
        System.out.println("  Position: " + buf.position());
        System.out.println("  Limit: " + buf.limit());
        
        buf.put((byte) 'H');
        buf.put((byte) 'E');
        buf.put((byte) 'L');
        System.out.println("  After put - Position: " + buf.position());
        
        buf.flip();
        System.out.println("  After flip - Position: " + buf.position() + 
            ", Limit: " + buf.limit());
        
        while (buf.hasRemaining()) {
            System.out.print((char) buf.get());
        }
        System.out.println("\n  After read - Position: " + buf.position());
        
        buf.rewind();
        System.out.println("  After rewind - Position: " + buf.position());

        // Direct buffer vs Heap buffer
        System.out.println("\nDirect vs Heap buffer:");
        ByteBuffer heapBuffer = ByteBuffer.allocate(1024);
        ByteBuffer directBuffer = ByteBuffer.allocateDirect(1024);
        System.out.println("  Heap buffer: " + !heapBuffer.isDirect());
        System.out.println("  Direct buffer: " + directBuffer.isDirect());

        System.out.println();
    }

    // ==================== MEMORY MAPPED FILES ====================

    public void memoryMappedFiles() {
        System.out.println("=== MEMORY MAPPED FILES ===");

        String filename = TEMP_DIR + "mapped.dat";
        int size = 1024;

        try {
            // Create and write to memory-mapped file
            try (RandomAccessFile raf = new RandomAccessFile(filename, "rw")) {
                FileChannel channel = raf.getChannel();
                MappedByteBuffer buffer = channel.map(
                    FileChannel.MapMode.READ_WRITE, 0, size);

                // Write data
                for (int i = 0; i < 26; i++) {
                    buffer.put((byte) ('A' + i));
                }
                
                System.out.println("Scritto alphabet in memory-mapped file");
                
                // Read data
                buffer.position(0);
                System.out.print("Letto: ");
                for (int i = 0; i < 26; i++) {
                    System.out.print((char) buffer.get());
                }
                System.out.println();
                
                // Random access
                buffer.position(5);
                buffer.put((byte) 'X');
                System.out.println("Modificato carattere in posizione 5");
                
                buffer.position(0);
                System.out.print("Dopo modifica: ");
                for (int i = 0; i < 26; i++) {
                    System.out.print((char) buffer.get());
                }
                System.out.println();
            }

            // Verify changes persisted
            try (FileChannel channel = FileChannel.open(
                    Paths.get(filename), StandardOpenOption.READ)) {
                ByteBuffer buffer = ByteBuffer.allocate(26);
                channel.read(buffer);
                buffer.flip();
                
                System.out.print("Verificato da file: ");
                while (buffer.hasRemaining()) {
                    System.out.print((char) buffer.get());
                }
                System.out.println();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== RANDOM ACCESS FILE ====================

    public void randomAccessFileDemo() {
        System.out.println("=== RANDOM ACCESS FILE ===");

        String filename = TEMP_DIR + "random.dat";

        try (RandomAccessFile raf = new RandomAccessFile(filename, "rw")) {
            
            // Write at different positions
            raf.writeUTF("First");
            long pos1 = raf.getFilePointer();
            
            raf.writeInt(12345);
            long pos2 = raf.getFilePointer();
            
            raf.writeDouble(3.14159);
            long pos3 = raf.getFilePointer();
            
            raf.writeUTF("Last");
            
            System.out.println("Scritti dati in posizioni diverse");
            System.out.println("  Pos after First: " + pos1);
            System.out.println("  Pos after int: " + pos2);
            System.out.println("  Pos after double: " + pos3);
            System.out.println("  File length: " + raf.length());

            // Read in reverse order
            System.out.println("\nLettura in ordine inverso:");
            
            raf.seek(pos3);
            String last = raf.readUTF();
            System.out.println("  Last string: " + last);
            
            raf.seek(pos2);
            double d = raf.readDouble();
            System.out.println("  Double: " + d);
            
            raf.seek(pos1);
            int i = raf.readInt();
            System.out.println("  Int: " + i);
            
            raf.seek(0);
            String first = raf.readUTF();
            System.out.println("  First string: " + first);

            // Modify in middle
            raf.seek(pos1);
            raf.writeInt(99999);
            System.out.println("\nModificato int in posizione " + pos1);
            
            raf.seek(pos1);
            System.out.println("  Nuovo valore: " + raf.readInt());

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== FILE COMPRESSION ====================

    public void fileCompression() {
        System.out.println("=== FILE COMPRESSION ===");

        // GZIP compression
        String original = TEMP_DIR + "compress-test.txt";
        String gzipFile = TEMP_DIR + "compressed.gz";
        
        try {
            // Create original file
            List<String> lines = new ArrayList<>();
            for (int i = 0; i < 100; i++) {
                lines.add("Line number " + i + " with some repeated content");
            }
            Files.write(Paths.get(original), lines);
            
            long originalSize = Files.size(Paths.get(original));
            System.out.println("File originale: " + originalSize + " bytes");

            // GZIP compress
            try (FileInputStream fis = new FileInputStream(original);
                 FileOutputStream fos = new FileOutputStream(gzipFile);
                 GZIPOutputStream gzos = new GZIPOutputStream(fos)) {
                
                byte[] buffer = new byte[1024];
                int len;
                while ((len = fis.read(buffer)) > 0) {
                    gzos.write(buffer, 0, len);
                }
            }
            
            long compressedSize = Files.size(Paths.get(gzipFile));
            System.out.println("File compresso GZIP: " + compressedSize + " bytes");
            System.out.println("Ratio: " + 
                String.format("%.2f%%", (100.0 * compressedSize / originalSize)));

            // GZIP decompress
            String decompressed = TEMP_DIR + "decompressed.txt";
            try (FileInputStream fis = new FileInputStream(gzipFile);
                 GZIPInputStream gzis = new GZIPInputStream(fis);
                 FileOutputStream fos = new FileOutputStream(decompressed)) {
                
                byte[] buffer = new byte[1024];
                int len;
                while ((len = gzis.read(buffer)) > 0) {
                    fos.write(buffer, 0, len);
                }
            }
            
            System.out.println("File decompresso: " + 
                Files.size(Paths.get(decompressed)) + " bytes");

            // ZIP archive
            System.out.println("\nZIP archive:");
            String zipFile = TEMP_DIR + "archive.zip";
            
            try (ZipOutputStream zos = new ZipOutputStream(
                    new FileOutputStream(zipFile))) {
                
                // Add multiple files
                for (int i = 1; i <= 3; i++) {
                    String fileName = "entry" + i + ".txt";
                    ZipEntry entry = new ZipEntry(fileName);
                    zos.putNextEntry(entry);
                    
                    String content = "Content of file " + i + "\n";
                    zos.write(content.getBytes());
                    zos.closeEntry();
                }
                
                System.out.println("  Creato ZIP con 3 file");
            }

            // Read ZIP
            try (ZipInputStream zis = new ZipInputStream(
                    new FileInputStream(zipFile))) {
                
                ZipEntry entry;
                System.out.println("  Contenuto ZIP:");
                while ((entry = zis.getNextEntry()) != null) {
                    System.out.println("    - " + entry.getName() + 
                        " (" + entry.getSize() + " bytes)");
                    zis.closeEntry();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== FILE WATCH SERVICE ====================

    public void fileWatchService() {
        System.out.println("=== FILE WATCH SERVICE ===");

        try {
            Path dir = Paths.get(TEMP_DIR, "watch-test");
            Files.createDirectories(dir);

            // Create WatchService
            WatchService watchService = FileSystems.getDefault().newWatchService();
            
            // Register directory
            dir.register(watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_MODIFY,
                StandardWatchEventKinds.ENTRY_DELETE);
            
            System.out.println("Monitoring directory: " + dir);
            System.out.println("Creazione di alcuni file per test...\n");

            // Create a thread to make changes
            Thread changesThread = new Thread(() -> {
                try {
                    Thread.sleep(500);
                    Files.write(dir.resolve("test1.txt"), 
                        Arrays.asList("Content 1"));
                    
                    Thread.sleep(500);
                    Files.write(dir.resolve("test2.txt"), 
                        Arrays.asList("Content 2"));
                    
                    Thread.sleep(500);
                    Files.delete(dir.resolve("test1.txt"));
                    
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
            });
            changesThread.start();

            // Watch for events (with timeout)
            int eventCount = 0;
            long startTime = System.currentTimeMillis();
            
            while (eventCount < 3 && 
                   (System.currentTimeMillis() - startTime) < 5000) {
                
                WatchKey key = watchService.poll(1, java.util.concurrent.TimeUnit.SECONDS);
                
                if (key != null) {
                    for (WatchEvent<?> event : key.pollEvents()) {
                        WatchEvent.Kind<?> kind = event.kind();
                        
                        if (kind == StandardWatchEventKinds.OVERFLOW) {
                            continue;
                        }
                        
                        @SuppressWarnings("unchecked")
                        WatchEvent<Path> ev = (WatchEvent<Path>) event;
                        Path filename = ev.context();
                        
                        System.out.println("  Event: " + kind.name() + 
                            " - " + filename);
                        eventCount++;
                    }
                    
                    boolean valid = key.reset();
                    if (!valid) {
                        break;
                    }
                }
            }

            changesThread.join(6000);
            watchService.close();
            
            System.out.println("\nMonitoring terminato");

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println();
    }

    // ==================== BEST PRACTICES ====================

    public void bestPractices() {
        System.out.println("=== I/O BEST PRACTICES ===\n");

        System.out.println("1. USA TRY-WITH-RESOURCES:");
        System.out.println("   ✓ try (FileReader fr = new FileReader(file)) { ... }");
        System.out.println("   ✗ FileReader fr = new FileReader(file); // dimenticare close()");

        System.out.println("\n2. USA BUFFERED STREAMS PER PERFORMANCE:");
        System.out.println("   ✓ BufferedReader, BufferedWriter");
        System.out.println("   ✗ FileReader/FileWriter senza buffer per grandi file");

        System.out.println("\n3. PREFERISCI NIO.2 (java.nio.file):");
        System.out.println("   ✓ Files.readAllLines(), Files.write()");
        System.out.println("   ✗ FileInputStream con loop manuale");

        System.out.println("\n4. GESTISCI CORRETTAMENTE IL CHARSET:");
        System.out.println("   ✓ StandardCharsets.UTF_8");
        System.out.println("   ✗ Usare charset di default del sistema");

        System.out.println("\n5. CHIUDI SEMPRE LE RISORSE:");
        System.out.println("   ✓ try-with-resources o finally block");
        System.out.println("   ✗ Dimenticare close() causa memory leak");

        System.out.println("\n6. USA PATH INVECE DI FILE:");
        System.out.println("   ✓ Path path = Paths.get(...)");
        System.out.println("   ✗ File file = new File(...) // vecchia API");

        System.out.println("\n7. GESTISCI IOException:");
        System.out.println("   ✓ try-catch appropriati");
        System.out.println("   ✗ Ignorare o fare solo printStackTrace()");

        System.out.println("\n8. USA STREAM API PER FILE DI TESTO:");
        System.out.println("   ✓ Files.lines().filter().collect()");
        System.out.println("   Efficiente per file grandi");

        System.out.println("\n9. CONSIDERA MEMORY-MAPPED FILES:");
        System.out.println("   Per file molto grandi con accesso random");
        System.out.println("   Migliori performance per read-intensive");

        System.out.println("\n10. USA CHANNEL E BUFFER PER PERFORMANCE:");
        System.out.println("    FileChannel più veloce per trasferimenti grandi");
        System.out.println("    Direct buffer per I/O intensivo");

        System.out.println("\n11. EVITA FLUSH ECCESSIVI:");
        System.out.println("    ✓ Flush solo quando necessario");
        System.out.println("    ✗ flush() dopo ogni write (troppo lento)");

        System.out.println("\n12. VALIDA PATH E PERMESSI:");
        System.out.println("    ✓ Files.exists(), Files.isReadable()");
        System.out.println("    Prima di operazioni I/O");

        System.out.println();
    }

    // ==================== CLEANUP ====================

    private void cleanup() {
        System.out.println("=== CLEANUP ===");
        
        try {
            Path dir = Paths.get(TEMP_DIR);
            if (Files.exists(dir)) {
                // Delete all files and subdirectories
                Files.walk(dir)
                    .sorted(Comparator.reverseOrder())
                    .forEach(path -> {
                        try {
                            Files.deleteIfExists(path);
                        } catch (IOException e) {
                            // Ignore
                        }
                    });
                
                System.out.println("Directory di lavoro pulita: " + TEMP_DIR);
            }
        } catch (IOException e) {
            System.err.println("Errore durante cleanup: " + e.getMessage());
        }
        
        System.out.println("\n=== DEMO COMPLETATA ===");
    }

    // ==================== CLASSI DI SUPPORTO ====================

    static class Person implements Serializable {
        private static final long serialVersionUID = 1L;
        
        private String name;
        private int age;
        private String email;
        private transient List<String> hobbies; // transient = non serializzato
        
        public Person(String name, int age, String email) {
            this.name = name;
            this.age = age;
            this.email = email;
            this.hobbies = new ArrayList<>();
        }
        
        public void addHobby(String hobby) {
            if (hobbies == null) {
                hobbies = new ArrayList<>();
            }
            hobbies.add(hobby);
        }
        
        public List<String> getHobbies() {
            return hobbies != null ? hobbies : new ArrayList<>();
        }
        
        // Custom serialization
        private void writeObject(ObjectOutputStream oos) throws IOException {
            oos.defaultWriteObject();
            // Serializza hobbies manualmente
            oos.writeInt(hobbies != null ? hobbies.size() : 0);
            if (hobbies != null) {
                for (String hobby : hobbies) {
                    oos.writeUTF(hobby);
                }
            }
        }
        
        private void readObject(ObjectInputStream ois) 
                throws IOException, ClassNotFoundException {
            ois.defaultReadObject();
            // Deserializza hobbies manualmente
            int hobbyCount = ois.readInt();
            hobbies = new ArrayList<>();
            for (int i = 0; i < hobbyCount; i++) {
                hobbies.add(ois.readUTF());
            }
        }
        
        @Override
        public String toString() {
            return String.format("Person{name='%s', age=%d, email='%s'}", 
                name, age, email);
        }
    }
}
