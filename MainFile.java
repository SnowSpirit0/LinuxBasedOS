import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class MainFile {
    public static class Directory {
        List<String> subDirectories;
        List<String> secondSubDirectories;
        static String alias;

        public Directory(String alias) {
            this.alias = alias;
            this.subDirectories = new ArrayList<>();
            this.secondSubDirectories = new ArrayList<>();
        }
        @Override
        public String toString() {
            String result = alias;

            for (String subDirectory: subDirectories) {
                if (subDirectory != null) {
                    result = result + subDirectory + "\n";
                }
            }
            return result;
        }

    }
    public static HashMap<String, Integer> linux_types = new HashMap<String, Integer>();
    public static BufferedReader reader2 = new BufferedReader(new InputStreamReader(System.in));
    public static List<String> install_commands = new ArrayList<String>();
    public static Scanner reader = new Scanner(System.in);
    public static Directory boot = new Directory("/boot");
    public static Directory bin = new Directory("/bin");
    public static Directory home = new Directory("/home");
    public static Directory lib = new Directory("/lib");
    public static Directory media = new Directory("/media");
    public static Directory mnt = new Directory("/mnt");
    public static Directory opt = new Directory("/opt");
    public static Directory root = new Directory("/root");
    public static Directory sbin = new Directory("/sbin");
    public static Directory srv = new Directory("/srv");
    public static Directory tmp = new Directory("/tmp");
    public static Directory usr = new Directory("/usr");
    public static Directory var = new Directory("/var");
    public static Directory dev = new Directory("/dev");
    public static Directory etc = new Directory("/etc");
    public static String[] terminal_commands = {"dir", "exit", "reinstall", "help", "folder", "install", "uninstall", "pacman -o sys-reinstall"};
    public static String[] bootloaders = {"GRUB", "systemd-boot"};
    public static List<String> packages = new ArrayList<String>();
    public static void LoadInstallCommands() {

    }

    public static void main(String[] args) {
        linux_types.put("arch", 16000);
        linux_types.put("ubuntu", 14000);
        install();
        var = new Directory("/var");
        terminal();
    }

    public static void console(String message) {
        System.out.println(message);
    }
    public static void consoleI(int message) {
        System.out.println(message);
    }
    public static void install() {
        String install_conf;
        String linux_conf;
        String chroot_conf;
        console("Would you like to install the system?(y/n)");
            install_conf = reader.nextLine();
            if (install_conf.equals("y")) {
                createSubFileDirectory(usr, "/desktop");
                console("Which linux version would you like to install? (arch/ubuntu)");
                    linux_conf = reader.nextLine();
            } else {
                return;
            }
            if (linux_types.containsKey(linux_conf)) {
                console("Installing " + linux_conf + " to /boot/" + linux_conf);
                    createSubFileDirectory(boot, "/" + linux_conf);
                console("Installed, would you like to create a chroot folder?");
                    chroot_conf = reader.nextLine();
            } else {
                return;
            }
            if (chroot_conf.equals("y")) {
                console("Creating subfolder /root/chroot");
                    createSubFileDirectory(root, "/chroot");
            } else if (chroot_conf.equals("n")){
                console("Not creating /chroot");
            } else {
                return;
            }
            console("What bootloader would you like to use? (GRUB, systemd-boot)");
            String bootloader_conf = reader.nextLine();
            boolean validBootloader = false;
            while (!validBootloader) {
                for (int i = 0; i < bootloaders.length; i++) {
                    if (bootloaders[i].equalsIgnoreCase(bootloader_conf)) {
                        console("Installing " + bootloader_conf + " as the bootloader.");
                        createSubFileDirectory(boot, "/" + bootloader_conf);
                        validBootloader = true;
                        break;
                        }
                }
                if (!validBootloader) {
                    System.out.println("Invalid selection.");
                    bootloader_conf = reader.nextLine();
                }
            }

        console("Rebooting system..");
        console("Opening the terminal..");
    }
    public static void terminal() {
        String command = "";
        console("Linux terminal version 1.0A, all rights reserved.");
        while (!command.equals("exit")) {
            command = reader.nextLine();
              // Add this line for debugging
            for (int i = 0; i < terminal_commands.length; i++) {
                if (command.equals(terminal_commands[i])) {
                    doCommand(command);
                    break;
                }
            }
        }
    }
    public static void doCommand(String command) {
        switch (command) {
            case "install":
                package_manager.handlePackageInstallation(reader.nextLine());
                break;
            case "uninstall":
                package_manager.handlePackageUninstallation(reader.nextLine());
                break;
            case "packages":
                package_manager.showInstalledPackages();
                break;
            case "pacman -o sys-reinstall":
                System.out.println("Are you sure you want to proceed? (y/n)");
                String conf = reader.nextLine();
                    if (conf.equals("y")) {
                        boot.subDirectories.clear();
                        root.subDirectories.clear();
                        install();
                    } else {
                        break;
                    }
                break;
            case "exit":
                break;
            case "help":
                for(int i = 0; i < terminal_commands.length; i++) {
                    console(terminal_commands[i]);
                }
                break;
            case "folder":
                String folder_name;
                console("Enter desired folder name");
                folder_name = reader.nextLine();
                createSecondSubFileDirectory(usr, 0, folder_name);
                break;
            case "dir":
                String dir_conf;
                System.out.println("Which directory would you like to see?");
                dir_conf = reader.nextLine();
                switch (dir_conf) {
                    case "boot":
                        System.out.println(boot.subDirectories);
                        break;
                    case "bin":
                        System.out.println(bin.subDirectories);
                        break;
                    case "home":
                        System.out.println(home.subDirectories);
                        break;
                    case "lib":
                        System.out.println(lib.subDirectories);
                        break;
                    case "media":
                        System.out.println(media.subDirectories);
                        break;
                    case "mnt":
                        System.out.println(mnt.subDirectories);
                        break;
                    case "opt":
                        System.out.println(opt.subDirectories);
                        break;
                    case "root":
                        System.out.println(root.subDirectories);
                        break;
                    case "sbin":
                        System.out.println(sbin.subDirectories);
                        break;
                    case "srv":
                        System.out.println(srv.subDirectories);
                        break;
                    case "tmp":
                        System.out.println(tmp.subDirectories);
                        break;
                    case "usr":
                        System.out.println(usr.subDirectories);
                        break;
                    case "var":
                        System.out.println(var.subDirectories);
                        break;
                    case "dev":
                        System.out.println(dev.subDirectories);
                        break;
                    case "etc":
                        System.out.println(etc.subDirectories);
                        break;
                }
                break; // Add this break statement
            default:
                System.out.println("Invalid command");
                break;
        }
    }


    public static void createSubFileDirectory(Directory directory, String subDirectoryName) {
        directory.subDirectories.add(subDirectoryName);
        Directory newDirectory = new Directory(subDirectoryName);
    }
    public static void createSecondSubFileDirectory(Directory directory, int prevSubDirectoryIndex, String secondSubDirectoryName) {
        directory.secondSubDirectories.add(prevSubDirectoryIndex, secondSubDirectoryName);
    }

}
