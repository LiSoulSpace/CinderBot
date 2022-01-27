package xyz.soulspace.cinder.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import xyz.soulspace.cinder.utils.RunPythonUtil;

import java.io.File;

import static xyz.soulspace.cinder.constant.ConstantPythonFile.pythonFileDir;

@Service
public class HotPointNewsService {
    public static final Logger logger = LoggerFactory.getLogger(HotPointNewsService.class);

    @Value("${file.path.data:}")
    private String dataPath;

    String pythonFilesDir = "data" + File.separator + pythonFileDir;

    public File getZhiHuHotPoint(){
        String pythonFilePathZhiHu = pythonFilesDir + File.separator + "ZhiHuHotSearch.py";
        logger.info(pythonFilePathZhiHu);
        String str = RunPythonUtil.runPythonScript(pythonFilePathZhiHu);
        try{
            File returnFile = new File(pythonFilesDir + File.separator + "ZhiHuHotSearch.png");
            logger.info("Get ZhiHu Hot Point in Service!");
            return returnFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public File getWeiBoHotPoint(){
        String pythonFilePathWeiBo = pythonFilesDir + File.separator + "WeiboHotSearch.py";
        logger.info(pythonFilePathWeiBo);
        String str = RunPythonUtil.runPythonScript(pythonFilePathWeiBo);
        try{
            File returnFile = new File(pythonFilesDir + File.separator + "WeiboHotSearch.png");
            logger.info("Get WeiBo Hot Point in Service!");
            return returnFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public File getBaiduHotPoint(){
        String pythonFilePathBaidu = pythonFilesDir + File.separator + "BaiduHotSearch.py";
        String str = RunPythonUtil.runPythonScript(pythonFilePathBaidu);
        try{
            File returnFile = new File(pythonFilesDir + File.separator + "BaiduHotSearch.png");
            logger.info("Get Baidu Hot Point in Service!");
            return returnFile;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
