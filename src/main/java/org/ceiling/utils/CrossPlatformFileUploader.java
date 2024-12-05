package org.ceiling.utils;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;

public class CrossPlatformFileUploader {

    /**
     * 文件上传工具方法，支持 Windows、macOS 和 Linux
     *
     * @param filePath 文件路径
     * @throws AWTException 如果无法初始化 Robot
     */
    public static void uploadFile(String filePath) throws AWTException {
        // 获取操作系统名称
        String osName = System.getProperty("os.name").toLowerCase();
        Robot robot = new Robot();

        // 将文件路径复制到剪贴板
        copyToClipboard(filePath);

        // 调用对应平台的上传逻辑
        if (osName.contains("win")) {
            uploadForWindows(robot);
        } else if (osName.contains("mac")) {
            uploadForMac(robot);
        } else if (osName.contains("nix") || osName.contains("nux")) {
            uploadForLinux(robot);
        } else {
            throw new UnsupportedOperationException("Unsupported operating system: " + osName);
        }
    }

    /**
     * 将文本内容复制到系统剪贴板
     *
     * @param text 文本内容
     */
    private static void copyToClipboard(String text) {
        StringSelection selection = new StringSelection(text);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);
    }

    /**
     * Windows 系统文件上传逻辑
     *
     * @param robot Robot 实例
     */
    private static void uploadForWindows(Robot robot) {
        // 模拟 Ctrl + V 粘贴
        simulateKeyCombination(robot, KeyEvent.VK_CONTROL, KeyEvent.VK_V);
        // 模拟按下 Enter 键
        pressEnter(robot);
    }

    /**
     * macOS 系统文件上传逻辑
     *
     * @param robot Robot 实例
     */
    private static void uploadForMac(Robot robot) {
        // 模拟 Command + V 粘贴 (Command 键是 VK_META)
        simulateKeyCombination(robot, KeyEvent.VK_META, KeyEvent.VK_V);
        // 模拟按下 Enter 键
        pressEnter(robot);
    }

    /**
     * Linux 系统文件上传逻辑
     *
     * @param robot Robot 实例
     */
    private static void uploadForLinux(Robot robot) {
        // 模拟 Ctrl + V 粘贴
        simulateKeyCombination(robot, KeyEvent.VK_CONTROL, KeyEvent.VK_V);
        // 模拟按下 Enter 键
        pressEnter(robot);
    }

    /**
     * 模拟组合键按下和释放
     *
     * @param robot Robot 实例
     * @param key1  第一个按键
     * @param key2  第二个按键
     */
    private static void simulateKeyCombination(Robot robot, int key1, int key2) {
        robot.delay(500);
        robot.keyPress(key1);
        robot.keyPress(key2);
        robot.keyRelease(key2);
        robot.keyRelease(key1);
    }

    /**
     * 模拟按下 Enter 键
     *
     * @param robot Robot 实例
     */
    private static void pressEnter(Robot robot) {
        robot.delay(500);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}

