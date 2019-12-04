package com.jdh.controller;

import com.jdh.feign.BackgroundService;
import com.jdh.pojo.Background;
import com.jdh.pojo.BackgroundImgDo;
import com.jdh.pojo.MyUser;
import com.jdh.utils.FileUtil;
import com.jdh.utils.JdhResult;
import com.jdh.utils.MyThumbnail;
import com.jdh.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;
import java.util.Map;

@RestController
public class UploadController {

    @Value("${myparameter.uploadPath}")
    public String materialPath;//d:/zp/material/

    @Autowired
    public BackgroundService backgroundService;


    /**
     * 上传背景图片
     * @param file
     * @param isPublic
     * @return
     */
    @PostMapping("/bgImg/upload")
    public JdhResult upload(@RequestParam("picFileUp") MultipartFile file,@RequestParam Boolean isPublic) {
            // System.out.println(isPublic);
            MyUser currUser = UserContext.getCurrUser();
            JdhResult result=null;
            String filePath=null;//保存的图片文件名
            String minFilePath=null;//缩略图文件地址

            try {
                String fileName = file.getOriginalFilename();
                fileName = FileUtil.renameToUUID(fileName);//获取唯一名称
                String fileType=fileName.substring(fileName.lastIndexOf(".")+1);//文件类型
                fileType=fileType.toLowerCase();
                //验证文件类型.png|.jpg|jpeg|.gif
                if (!fileType.equals("jpg")&&!fileType.equals("gif")
                     &&!fileType.equals("png")&&!fileType.equals("jpeg")){
                    throw  new RuntimeException("文件类型错误");
                }


                String fileMd5=FileUtil.getFileMd5(file.getBytes());//获取文件MD5
                List<BackgroundImgDo> lists = backgroundService.getBackgroundImgByMd5(fileMd5);
                //文件不存在
                if (lists==null||lists.size()<=0){
                 //   System.out.println("文件不存在");


                    //\img\bg 目录分级算法
                    String dir=FileUtil.getDir(fileName);
                    String savePath=materialPath+"img"+ File.separator+"bg"+dir;
                    FileUtil.uploadFile(file.getBytes(),savePath , fileName);//保存到磁盘
                    String pic="/material/img/bg"+dir.replace("\\","/")+fileName;//数据库图片地址
                    filePath=savePath+fileName;//文件路劲

                    //保存缩略图到硬盘
                    minFilePath=savePath+"min_"+fileName;//缩略图文件路径
                    boolean flag = MyThumbnail.getThumbnail(filePath, minFilePath, 188, 106);
                    String thumbnailPath=null;
                    if (flag){
                       //保存成功
                        thumbnailPath= "/material/img/bg"+dir.replace("\\","/")+"min_"+fileName;
                    }else {
                        throw new RuntimeException("缩略图保存失败");
                    }


                    //保存到数据库
                    BackgroundImgDo backgroundImgDo=new BackgroundImgDo();
                    backgroundImgDo.setAuthorId(currUser.getId());
                    backgroundImgDo.setPic(pic);
                    backgroundImgDo.setIspublic(isPublic);
                    backgroundImgDo.setExpire(false);//不过期
                    backgroundImgDo.setMd5(fileMd5);
                    backgroundImgDo.setThumbnail(thumbnailPath);
                    result = backgroundService.saveBackgroundImg(backgroundImgDo);

                    //保存图片成功后 设置用户背景
                    if (result.getData()!=null&&result.getStatus()==1){
                        //获取用户背景信息
                        Background background = backgroundService.getUserBackgroundById(currUser.getId());
                        //没有就添加 有就修改
                        if (background==null){
                     //       System.out.println("没有背景信息 保存一个");
                            background=new Background();
                            background.setPid(Long.valueOf( result.getData().toString()) );
                            background.setUid(currUser.getId());
                            background.setSid(null);
                            backgroundService.saveBackground(background);
                        }else {
                      //      System.out.println("有背景信息 更新");
                            background.setPid(Long.valueOf( result.getData().toString()));
                            backgroundService.updateBackground(background);
                        }
                    }



                    return  result;
                }else {
                //    System.out.println("文件存在");
                    BackgroundImgDo backgroundImgDo = lists.get(0);
                        //获取用户背景信息
                    Background background = backgroundService.getUserBackgroundById(currUser.getId());
                        //没有就添加 有就修改
                        if (background==null){
                        //    System.out.println("没有背景信息 保存一个");
                            background=new Background();
                            background.setPid(backgroundImgDo.getPid());
                            background.setUid(currUser.getId());
                            background.setSid(null);
                            backgroundService.saveBackground(background);
                        }else {
                       //     System.out.println("有背景信息 更新");
                            background.setPid(backgroundImgDo.getPid());
                            backgroundService.updateBackground(background);
                        }
                    return JdhResult.success("已有其他用户上传此图,无需再次上传,直接调用！！！");
                }




            } catch (Exception e) {
                e.printStackTrace();

               if (filePath!=null)FileUtil.deleteFile(filePath);//删除图片
               if(minFilePath!=null)FileUtil.deleteFile(minFilePath);//删除缩略图
                //此处还需要删除数据库
                if (result!=null&&result.getStatus()==1){
                    //删除数据库
                    backgroundService.deleteBackgroundImgByPid(Long.valueOf(result.getData().toString()));
                }
                return JdhResult.fail("背景图片:系统错误操作失败！！！");
            }



    }
}
