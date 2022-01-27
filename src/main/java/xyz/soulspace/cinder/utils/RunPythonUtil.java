package xyz.soulspace.cinder.utils;

import lombok.experimental.UtilityClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@UtilityClass
public class RunPythonUtil {
    public static final Logger logger = LoggerFactory.getLogger(RunPythonUtil.class);

    /**
     * 运行python脚本
     *
     * @param pathWithCommand python文件的路径
     * @return 运行返回的数据
     */
    public static String runPythonScript(String pathWithCommand) {
        Process process;
        String commandStr = "python3 " + pathWithCommand;
        logger.info("Run python Command : " + commandStr);
        try {
            process = Runtime.getRuntime().exec(commandStr);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            StringBuilder answerLine = new StringBuilder();
            while (line != null) {
                line = bufferedReader.readLine();
                logger.info(line);
                answerLine.append(line).append('\n');
            }
            bufferedReader.close();
            process.waitFor();
            return answerLine.toString();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
