import java.util.ArrayList;
import java.util.List;

public class package_manager extends MainFile {
    public static void handlePackageInstallation(String inst_command) {
        List<String> packages = new ArrayList<>();
        packages.add("devtools-linux");
        packages.add("file-explorer");
        for (String packageName : packages) {
            if (inst_command.startsWith("pacman -i " + packageName)) {
                System.out.println("Installing >> " + packageName);
                createSubFileDirectory(opt, "/" + packageName);
            }
        }
    }
    public static void handlePackageUninstallation(String inst_command) {
        List<String> packages = new ArrayList<>();
        packages.add("devtools-linux");
        packages.add("file-explorer");
        for (String packageName : packages) {
            if (inst_command.startsWith("pacman -u " + packageName)) {
                System.out.println("Uninstalling >> " + packageName);
                opt.subDirectories.remove("/" + packageName);
            }
        }
    }
    public static void showInstalledPackages() {
        List<String> packages = new ArrayList<>();
        packages.add("devtools-linux");
        packages.add("file-explorer");
        System.out.println("Installed packages: " + opt.subDirectories);
    }
}
