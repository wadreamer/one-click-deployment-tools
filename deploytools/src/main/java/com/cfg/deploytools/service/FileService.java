package com.cfg.deploytools.service;

import com.cfg.deploytools.common.domain.AjaxResult;
import com.cfg.deploytools.mapper.FileMapper;
import com.cfg.deploytools.model.File;
import com.cfg.deploytools.model.FileMapLocalPath;
import com.cfg.deploytools.model.SQLFile;
import com.cfg.deploytools.model.TaskFile;
import com.cfg.deploytools.utils.FileUtils;
import com.google.gson.Gson;
import org.apache.shiro.crypto.hash.Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * ClassName: FileService
 * Description:
 * date: 2020/6/8 9:23
 *
 * @author CFG
 * @since JDK 1.8
 */
@Service
public class FileService {

    @Autowired
    private FileMapper fileMapper;

    @Autowired
    private TaskFileService taskFileService;

    @Autowired
    private TaskStatusService taskStatusService;

    @Transactional
    public AjaxResult upload(MultipartFile[] multipartFiles, String[] sqlFiles, Integer taskId, String[] fileMapLocalPath, String configurationPath) {
        List<File> filesList = multipartFilesHandler(multipartFiles, fileMapLocalPath, configurationPath);
        List<File> sqlFilesList = sqlFilesHandler(sqlFiles, configurationPath);

        List<File> list = new ArrayList<>();
        list.addAll(filesList);
        list.addAll(sqlFilesList);

        System.out.println(list.size());

        int result_insertFile = fileMapper.insertSelectiveFile(list); // 批量插入 file 记录

        List<TaskFile> taskFileList = taskFilesHandler(taskId, list);
        int result_insertTaskFile = taskFileService.insertTaskFile(taskFileList); // 批量插入 taskfile 记录
        AjaxResult result_status = taskStatusService.updateTaskStatusForUpload(taskId);

        return (int) result_status.get("code") == 200 && result_insertFile > 0 && result_insertTaskFile > 0 ?
                AjaxResult.success("上传成功") : AjaxResult.error("操作失败，请稍后重试！");
    }

    public List<TaskFile> taskFilesHandler(int taskId, List<File> fileList) {
        List<TaskFile> list = new ArrayList<>();

        TaskFile taskFile;
        for (File file : fileList) {
            taskFile = new TaskFile(taskId, file.getFullPath(), file.getFileId());
            list.add(taskFile);
        }
        return list;
    }

    public List<File> multipartFilesHandler(MultipartFile[] files, String[] fileMapLocalPath, String configurationPath) {
        List<File> fileList = new ArrayList<>();
        if (files != null && files.length != 0) {
            HashMap<String, String> map = new HashMap<>();
            FileMapLocalPath fmp;
            for (String filePath : fileMapLocalPath) {
                // 将 json 转成 对象
                fmp = new Gson().fromJson(filePath.replaceAll("\\\\", "\\\\\\\\"), FileMapLocalPath.class);

                int end = fmp.getLocalPath().length();
                int start = configurationPath.length();
                fmp.setLocalPath(fmp.getLocalPath().substring(start, end - 1)); // 设置文件的相对路径

                map.put(fmp.getName(), fmp.getLocalPath());
            }

            File fileCustom;
            String content = "";
            String relativePath = "";
            try {
                for (MultipartFile file : files) {
                    content = FileUtils.readFileByChars(file.getInputStream()); // 文件内容
                    relativePath = map.get(file.getOriginalFilename()); // 文件相对路径

                    fileCustom = new File(relativePath, content, null, 0);
                    fileList.add(fileCustom);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return fileList;
    }

    public List<File> sqlFilesHandler(String[] sqlFiles, String configurationPath) {
        List<File> list = new ArrayList<>();
        if (sqlFiles != null) {
            // String[] strArr = configurationPath.split("\\\\");
            // String projectRootPath = "\\" + strArr[strArr.length - 1];

            String realativePath = "";
            File fileCustom;
            for (String sqlJson : sqlFiles) {
                System.out.println(sqlJson);
                SQLFile sqlFile = new Gson().fromJson(sqlJson, SQLFile.class);
                realativePath = "\\" + sqlFile.getName();

                fileCustom = new File(realativePath, null, sqlFile.getContent(), 1);
                list.add(fileCustom);
            }
        }

        return list;
    }

    /*
     * @Author wadreamer
     * @Description //TODO 删除 cfg_file 的多条记录 ok
     * @Date 10:35 2020/6/9
     * @Param [fileId]
     * @return int
     **/
    public int deleteFile(List<TaskFile> taskFileList) {
        return fileMapper.deleteFileByFileIdArray(taskFileList);
    }

    /*
     * @Author wadreamer
     * @Description //TODO 根据文件的全路径，获取当前任务正在使用的最新文件 ok
     * @Date 14:41 2020/6/9
     * @Param [taskFileList]
     * @return java.util.List<com.cfg.deploytools.model.File>
     **/
    public List<File> getFileListNewest(List<TaskFile> taskFileList) {
        List<File> resultList = new ArrayList<>();
        for (TaskFile tf : taskFileList) {
            File f = fileMapper.queryFileNewestByFullPath(tf);
            if (f != null) {
                resultList.add(f);
            }
        }
        return resultList;
    }


    public File getFileNewest(TaskFile taskFile) {
        File file = fileMapper.queryFileNewestByFullPath(taskFile);
        return file;
    }

    /*
     * @Author wadreamer
     * @Description //TODO 获取当前任务正在使用的文件信息 ok
     * @Date 16:51 2020/6/9
     * @Param [taskId]
     * @return java.util.List<com.cfg.deploytools.model.File>
     **/
    public List<File> getCurrentFileListByTaskId(int taskId) {
        return fileMapper.queryCurrentAllFileByTaskId(taskId);
    }

    /*
     * @Author wadreamer
     * @Description //TODO 获取某个文件的历史版本文件 ok
     * @Date 16:50 2020/6/9
     * @Param [fileId]
     * @return java.util.List<com.cfg.deploytools.model.File>
     **/
    public List<File> getFileHistory(String fullPath) {
        return fileMapper.queryFileHistoryByFullPath(fullPath);
    }

    /*
     * @Author wadreamer
     * @Description //TODO 获取文件内容 ok
     * @Date 10:25 2020/6/11
     * @Param [taskFile]
     * @return com.cfg.deploytools.common.domain.AjaxResult
     **/
    public AjaxResult getFileContent(TaskFile taskFile) {
        File file = fileMapper.queryFileContent(taskFile);
        return file != null ? AjaxResult.success(200, file) : AjaxResult.error("操作失败，请稍后重试");
    }

    /*
     * @Author wadreamer
     * @Description //TODO 根据 文件全路径 或 任务主键 判断上传的文件是否存在冲突 ok
     * @Date 10:23 2020/6/11
     * @Param [fullPath, taskId]
     * @return boolean
     **/
    public List<File> checkConflict(String fullPath, int taskId) {
        List<File> conflictWithTime = fileMapper.checkConflictWithTime(fullPath, taskId);
        List<File> conflictWithStatus = fileMapper.checkConflictWithTaskStatus(fullPath);

        List<File> result = new ArrayList<>();
        result.addAll(conflictWithStatus);
        result.addAll(conflictWithTime);

        return result;
    }

    public File getFileById(int fileId) {
        return fileMapper.queryFileById(fileId);
    }

}
