package csd.uoc.gr.TemplateValidator;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ValidatorScript {

    /* VARIABLES */

    private Set<String> _assignmentPackNames = new HashSet<>(); // The names of the assignment packages
    private Set<String> _assignmentPackPaths = new HashSet<>(); // The paths of the assignment packages

    private final int _assignmentSerie = 2;     // The serie number of the assignment
    private final int _assignmentNumber = 4;    // How many assignments are included

    private String sep = getSepChar();

    private final int AM_DIGITS = 4;

    private String _srcDirFullPath;                 // The path of the source folder
    private final String _csdPackPath = sep + "src" + sep + "csd" + sep + "uoc" + sep + "gr";  // The path to the assignment packages
    private final String _scriptPackName = "TemplateValidator"; // The package name of the script
    private final String RESET_COLOR = "\u001B[0m";
    private final String WARN_COLOR = "\u001B[33m";

    private int _warnings = 0;

    class DirectoryNotFoundException extends Exception{
        public DirectoryNotFoundException(String errorMsg){ super(errorMsg); }
    }

    /* CONSTRUCTORS */

    private ValidatorScript() throws IllegalStateException{
        _srcDirFullPath = getPathToProjTemplate() + _csdPackPath;
        for(int i = 0; i < this._assignmentNumber; i++) {
            this._assignmentPackPaths.add(this._srcDirFullPath + sep + "A" + this._assignmentSerie + (i + 1));
            this._assignmentPackNames.add("A" + this._assignmentSerie + (i + 1));
        }
    }

    /*
    * VALIDATORS
    */

    private void validate() throws DirectoryNotFoundException, IOException{
       valSrcPackExists();
       valAssignmentPacksNotMissing();
       valAssignmentPacksNotEmpty();
       valOuterJavaFilesNotExist();
    }

    private void valSrcPackExists() throws DirectoryNotFoundException{
        if(!new File(_srcDirFullPath).isDirectory())
            msgSrcNotExists();
        System.out.println("[ OK ] Source folder exists: " + _srcDirFullPath);
    }

    private void valAssignmentPacksNotMissing() throws DirectoryNotFoundException{
        File[] filesList = new File(_srcDirFullPath).listFiles();
        Set<String> packNamesClone = new HashSet<>(_assignmentPackNames);
        Set<String> packNamesTmp = new HashSet<>();
        for(File file : filesList){
            if(!file.getName().startsWith(".") && !file.getName().equals(_scriptPackName))
                packNamesTmp.add(file.getName());
        }
        packNamesClone.removeAll(packNamesTmp);
        if(!packNamesClone.isEmpty())
            msgAssignmentPackMissing(packNamesClone);
        System.out.println("[ OK ] No missing packages in " + _srcDirFullPath);
    }

    private void valOuterJavaFilesNotExist(){
        try {
            List<Path> javaFilePaths = Files.walk(Paths.get(getSrcFolderPath()))
                    .filter(path -> !path.toFile().isDirectory() &&
                            !path.toFile().getName().startsWith(".") &&
                            path.toFile().getName().endsWith(".java") &&
                            !path.toFile().getParent().endsWith(_scriptPackName) &&
                            !fileIsInAssignmentPack(path.toString()))
                    .collect(Collectors.toList());
            TreeMap<String, ArrayList<Path>> outerJavaFilesMap = getOuterJavaFilesMap(javaFilePaths);
            if(!outerJavaFilesMap.isEmpty()) {
                int outerJavaFilesNumber = 0;
                StringBuilder foldersWithFilesSb = new StringBuilder();
                for(String folder : outerJavaFilesMap.keySet()){
                    foldersWithFilesSb.append(folder).append("\n");
                    for(Path javaFile : outerJavaFilesMap.get(folder)) {
                        foldersWithFilesSb.append("\t").append(javaFile.toFile().getName()).append("\n");
                        outerJavaFilesNumber++;
                    }
                }
                msgOuterJavaFilesFound(outerJavaFilesNumber, outerJavaFilesMap, foldersWithFilesSb.toString());
            }
            else {
                System.out.println("[ OK ] No outer java files from packages " + _assignmentPackNames);
            }
        } catch (IOException e) { e.printStackTrace(); }
    }

    private void valAssignmentPacksNotEmpty() throws IOException{
        File[] filesList = new File(_srcDirFullPath).listFiles();
        Set<String> emptyPacks = new HashSet<>();
        for(File file : filesList){
            if (file.isDirectory() && _assignmentPackNames.contains(file.getName())){
                if(!folderContainsJavaFiles(file))
                    emptyPacks.add(file.getName());
            }
        }
        if(emptyPacks.size() > 0)
            msgEmptyAssignmentPackFound(emptyPacks);
        else
            System.out.println("[ OK ] No empty packages");
    }


    /*
    *  ERROR MESSAGE FUNCTIONS
    */

    // [ERROR #1]

    private void msgSrcNotExists() throws DirectoryNotFoundException{
        throw new DirectoryNotFoundException(
                "\n[ERROR #1]\n The source folder " + _srcDirFullPath + " does not exists \n" +
                "Please make sure that you did't change the folder structure of the given template\n" +
                "See Error section of \"Οδηγίες Παράδοσης\" in elearn for instructions\n"
        );
    }

    // [ERROR #2]

    private void msgAssignmentPackMissing(Set<String> missingPacks) throws DirectoryNotFoundException{
        throw new DirectoryNotFoundException(
                "\n[ERROR #2]\n The packages " + missingPacks.toString() + " are missing from the source folder " +
                        _srcDirFullPath + "\n" +
                "Please make sure that\n" +
                "1. You didn't change the folder structure of the given template\n" +
                "2. You didn't change the names of the initial packages\n" +
                "The source folder should contain the packs " + _assignmentPackNames.toString() + ".\n" +
                "See Error section of \"Οδηγίες Παράδοσης\" in elearn for instructions\n"
        );
    }

    // [ERROR #3]

    private void msgOuterJavaFilesFound(int outerJavaFilesNumber, TreeMap<String, ArrayList<Path>> outerJavaFilesMap,
                                        String foldersOfOuterFiles) throws IllegalStateException{
        throw new IllegalStateException(
                "\n[ERROR #3]\n" + outerJavaFilesNumber + " java files were found " +
                        "out of packages " + _assignmentPackNames.toString() + "\n" +
                        "The files were found in the following " + outerJavaFilesMap.size() + " folders:\n" +
                        foldersOfOuterFiles +
                        "If these java files are necessary for your submission, please move them into " +
                        "one of the packages " + _assignmentPackNames.toString() + "\nIf these java files " +
                        "aren't necessary for your submission delete them and run again the script\n" +
                        "See Error section of \"Οδηγίες Παράδοσης\" in elearn for instructions\n"
        );
    }

    /*
    *  WARNING MESSAGE FUNCTIONS
    */

    // [WARNING #1]

    private void msgEmptyAssignmentPackFound(Set<String> emptyPacks){
        _warnings++;
        System.out.println( WARN_COLOR +
                "\n[WARNING #4]\n The packages " + emptyPacks.toString() + " in " + _srcDirFullPath + " are empty.\n" +
                "The zip process will ignore those packages. If you didn't make this exercises of those packages " +
                "ignore this warning.\n" +
                "See Warning section of \"Οδηγίες Παράδοσης\" in elearn for instructions\n"
        + RESET_COLOR);
    }

    /*
    * GENERAL FUNCTIONS
    */

    private boolean fileIsInAssignmentPack(String path){
        for(String packPath : _assignmentPackPaths)
            if(path.startsWith(packPath))
                return true;
        return false;
    }

    private boolean folderContainsJavaFiles(File folder) throws IOException{
        List<Path> javaFilesList = Files.walk(Paths.get(folder.getAbsolutePath()))
                .filter(path -> !path.toFile().isDirectory() &&
                        path.toFile().getName().endsWith(".java"))
                .collect(Collectors.toList());
        return javaFilesList.size() > 0;
    }

    private TreeMap<String, ArrayList<Path>> getOuterJavaFilesMap(List<Path> javaFilePaths){
        TreeMap<String, ArrayList<Path>> outerJavaFilesMap = new TreeMap<>();
        for(Path javaFilePath : javaFilePaths){
            String javaFileParent = new File(javaFilePath.toString()).getParent();
            ArrayList<Path> filesInFolder =
                    outerJavaFilesMap.getOrDefault(javaFileParent, new ArrayList<>());
            filesInFolder.add(javaFilePath);
            outerJavaFilesMap.put(javaFileParent, filesInFolder);
        }
        return outerJavaFilesMap;
    }

    private void zipProject() throws IOException {
        String sourceDirPath = getPathToProjTemplate();
        String zipFilePathFinal = getZipFileName() + ".zip";
        System.out.println("zip file path final: " + zipFilePathFinal);
        File zipFile = new File(zipFilePathFinal);
        if(zipFile.exists())
            zipFile.delete();
        Path p = Files.createFile(Paths.get(zipFilePathFinal));
        try (ZipOutputStream zs = new ZipOutputStream(Files.newOutputStream(p))) {
            Path pp = Paths.get(sourceDirPath);
            System.out.println("\nZipping Assignment " + _assignmentSerie + " project");
            List<String> javaFilesNamesZipped = new ArrayList<>();
            Files.walk(pp)
                    .filter(path -> !Files.isDirectory(path) && !path.toFile().getName().endsWith(".class"))
                    .forEach(path -> {
                        if(path.toFile().getName().endsWith(".java"))
                            javaFilesNamesZipped.add(path.toFile().getName());
                        ZipEntry zipEntry = new ZipEntry(pp.relativize(path).toString());
                        try {
                            zs.putNextEntry(zipEntry);
                            Files.copy(path, zs);
                            zs.closeEntry();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
            for(String javaFile : javaFilesNamesZipped)
                System.out.println("- " + javaFile);
            System.out.println("[ OK ] " + javaFilesNamesZipped.size() + " java files zipped successfully");
            System.out.println("Your zipped project is in " + zipFilePathFinal);
            openFSLocation(zipFilePathFinal);

        }
    }

    private void openFSLocation(String path) throws IOException {
        JFrame frame = new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(200, 100));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(false);

        Object[] options = {"Yes", "No"};
        int choice = JOptionPane.showOptionDialog(null,
                "Your project zipped successfully.\nWould you like to open file location?",
                "Open File Location",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[1]);

        Desktop desktop = Desktop.getDesktop();
        if (choice == JOptionPane.YES_OPTION)
            desktop.open(new File(path).getParentFile());

        frame.dispose();
    }

    /*
    * GET FUNCTIONS
    */

    private String getSrcFolderPath() throws IllegalStateException{
        return getPathToProjTemplate() + sep + "src";
    }

    private String getZipFileName(){
        String pathToProjFolder = new File(getPathToProjTemplate()).getParent();
        String fileName = getEditorWindow() + "_A" + _assignmentSerie + "_" + getAMWindow();
        return pathToProjFolder + sep + fileName;
    }

    private String getEditorWindow(){
        JFrame frame = new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(200, 150));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(false);

        String[] editors = {"NetBeans", "Eclipse", "IntelliJ"};

        Object selected = JOptionPane.showInputDialog(frame,
                    null,
                    "Choose your IDE",
                    JOptionPane.DEFAULT_OPTION,
                    null, editors,
                    editors[0]);

        frame.dispose();

        if(selected == null) {
            System.out.println("Zip file wasn't created");
            System.exit(0);
        }

        String selectedString = selected.toString();
        return selectedString;
    }

    private String getAMWindow(){
        JFrame frame = new JFrame();
        frame.setLocationRelativeTo(null);
        frame.setSize(new Dimension(200, 100));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(false);

        boolean incorrectAm = true;
        String am = "";
        while (incorrectAm) {

            am = JOptionPane.showInputDialog(frame, "Give your AM (e.g 1234): ");

            if(am == null)
                break;

            am = am.replace(" ","").replace("\n","");
            incorrectAm = false;
            for(char c : am.toCharArray()) {
                if (!Character.isDigit(c))
                    incorrectAm = true;
            }
            if(incorrectAm)
                JOptionPane.showMessageDialog(frame, "AM must contain only numbers.");

            if(am.length() != AM_DIGITS) {
                incorrectAm = true;
                JOptionPane.showMessageDialog(frame,"AM must be only 4 digits");
            }
        }
        frame.dispose();
        if(am == null){
            System.out.println("Zip file wasn't created");
            System.exit(0);
        }
        return am;
    }

    private String getPathToProjTemplate() {
        return Paths.get("").toAbsolutePath().toString();
    }

    private String getSepChar(){
        String sepChar = System.getProperty("file.separator");
        if(sepChar.equals("\\"))
            return "\\";
        else
            return "/";
    }


    /* MAIN FUNCTION */

    public static void main(String[] args)
            throws IllegalStateException, DirectoryNotFoundException, IOException{
        ValidatorScript validatorScript = new ValidatorScript();
        validatorScript.validate();
        System.out.println("Script run successfully with 0 Errors, " + validatorScript._warnings + " Warnings\n");
        validatorScript.zipProject();
    }

}
