package com.jdh.controller;

import com.jdh.feign.MouseStyleService;
import com.jdh.pojo.BackgroundImgDo;
import com.jdh.pojo.MouseImgDo;
import com.jdh.pojo.MouseStyleDo;
import com.jdh.pojo.MyUser;
import com.jdh.utils.FileUtil;
import com.jdh.utils.JdhResult;
import com.jdh.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.List;

@RestController
public class MouseStyleController {
    @Autowired
    private MouseStyleService mouseStyleService;

    @Value("${myparameter.uploadPath}")
    public String materialPath;//d:/zp/material/
    /**
     * 增加鼠标图标
     * @param file1 指针
     * @param file2 手指
     * @param share 是否共享
     * @return
     */
    @PostMapping("/mouseImg/add")
    public JdhResult addMouseImg(@RequestParam(value = "mousePointer1",required = false) MultipartFile file1, @RequestParam(value = "mousePointer2",required = false) MultipartFile file2, @RequestParam(value = "isPublicMouse",defaultValue = "false") Boolean share){
        MyUser currUser = UserContext.getCurrUser();
        String filePath1=null;//保存的图片路劲1
        String filePath2=null;//保存的图片路劲2
        String fileName1=null;
        String fileName2=null;
        Long mid1=null; //图标1 id
        Long mid2=null; //图标2 id
        MouseStyleDo mouseStyleDo=new MouseStyleDo();//用户鼠标映射表
        try {
             mouseStyleDo.setUid(currUser.getId());//映射用户
            //指针不为空
            if(file1!=null){
                fileName1 = file1.getOriginalFilename();
                if(fileName1!=null&&fileName1.trim().length()>0){
                    fileName1 = FileUtil.renameToUUID(fileName1);//获取唯一名称
                    String fileType1=fileName1.substring(fileName1.lastIndexOf(".")+1);//文件类型
                    fileType1=fileType1.toLowerCase();
                    //验证文件类型.png|.jpg|jpeg|.gif
                    if (!fileType1.equals("jpg")&&!fileType1.equals("gif")
                            &&!fileType1.equals("png")&&!fileType1.equals("jpeg")&&!fileType1.equals("ico")){
                        return JdhResult.fail("文件类型错误！！！");
                    }
                }else {
                    fileName1=null;
                }


            }
            //手指不为空
            if(file2!=null){
                fileName2 = file2.getOriginalFilename();
                if(fileName2!=null&&fileName2.trim().length()>0){
                    fileName2 = FileUtil.renameToUUID(fileName2);//获取唯一名称
                    String fileType2=fileName2.substring(fileName2.lastIndexOf(".")+1);//文件类型
                    fileType2=fileType2.toLowerCase();
                    //验证文件类型.png|.jpg|jpeg|.gif
                    if (!fileType2.equals("jpg")&&!fileType2.equals("gif")
                            &&!fileType2.equals("png")&&!fileType2.equals("jpeg")&&!fileType2.equals("ico")){
                        return JdhResult.fail("文件类型错误！！！");
                    }
                }else {
                    fileName2=null;
                }

            }

            boolean isExistFile=false;//是否存在此图

            //指针文件不为空
            if(fileName1!=null){
                String fileMd5=FileUtil.getFileMd5(file1.getBytes());//获取文件MD5
                MouseImgDo mouseImgOneDoByMd5 = mouseStyleService.getMouseImgDoByMd5(fileMd5);//是否有此图
                if(mouseImgOneDoByMd5==null){//无此图
                    //D:\zp\material\img\mouse\pointer
                    String dir=FileUtil.getDir(fileName1);//目录分离
                    String savePath=materialPath+"img"+ File.separator+"mouse"+File.separator+"pointer"+dir;
                    FileUtil.uploadFile(file1.getBytes(),savePath , fileName1);//保存到磁盘

                    String path="/material/img/mouse/pointer"+dir.replace("\\","/")+fileName1;//数据库图片地址
                    filePath1=savePath+fileName1;//文件路劲

                    MouseImgDo mouseImgDo=new MouseImgDo();
                    mouseImgDo.setMd5(fileMd5);
                    mouseImgDo.setMouseType(0); //'鼠标类型 0=指针 1=手指 2=都可以'
                    mouseImgDo.setMouseUrl(path);//地址
                    mouseImgDo.setShare(share);//是否共享
                    mouseImgDo.setUid(currUser.getId());//上传者
                    mouseImgDo.setUpDate(new Date());//上传时间
                    mouseImgDo.setUseCount(1l);//使用次数
                    JdhResult jdhResult = mouseStyleService.addMouseImg(mouseImgDo);
                    if (jdhResult.getStatus()==1){
                        Long pmid = Long.valueOf(jdhResult.getData().toString());
                        mid1=pmid;
                        mouseStyleDo.setPmid(pmid);
                    }else {
                        throw new RuntimeException("错误");
                    }


                }else {//有此图
                    isExistFile=true;
                    mouseStyleDo.setPmid(mouseImgOneDoByMd5.getMid());
                    //更新此图标 信息
                    mouseImgOneDoByMd5.setUseCount(mouseImgOneDoByMd5.getUseCount()+1);//使用次数加一
                    mouseImgOneDoByMd5.setMouseType(mouseImgOneDoByMd5.getMouseType()==0?0:2);
                    mouseStyleService.updateMouseImgDo(mouseImgOneDoByMd5);

                }
            }



            //手指不为空
            if(fileName2!=null){
                String fileMd5=FileUtil.getFileMd5(file2.getBytes());//获取文件MD5
                MouseImgDo mouseImgOneDoByMd5 = mouseStyleService.getMouseImgDoByMd5(fileMd5);//是否有此图
                if(mouseImgOneDoByMd5==null){//无此图
                    //D:\zp\material\img\mouse\hand
                    String dir=FileUtil.getDir(fileName2);//目录分离
                    String savePath=materialPath+"img"+ File.separator+"mouse"+File.separator+"hand"+dir;
                    FileUtil.uploadFile(file2.getBytes(),savePath , fileName2);//保存到磁盘

                    String path="/material/img/mouse/hand"+dir.replace("\\","/")+fileName2;//数据库图片地址
                    filePath2=savePath+fileName2;//文件路劲

                    MouseImgDo mouseImgDo=new MouseImgDo();
                    mouseImgDo.setMd5(fileMd5);
                    mouseImgDo.setMouseType(1); //'鼠标类型 0=指针 1=手指 2=都可以'
                    mouseImgDo.setMouseUrl(path);//地址
                    mouseImgDo.setShare(share);//是否共享
                    mouseImgDo.setUid(currUser.getId());//上传者
                    mouseImgDo.setUpDate(new Date());//上传时间
                    mouseImgDo.setUseCount(1l);//使用次数
                    JdhResult jdhResult = mouseStyleService.addMouseImg(mouseImgDo);
                    if (jdhResult.getStatus()==1){
                        Long hmid = Long.valueOf(jdhResult.getData().toString());
                        mid2=hmid;
                        mouseStyleDo.setHmid(hmid);
                    }else {
                        throw new RuntimeException("错误");
                    }


                }else {//有此图
                    isExistFile=true;
                    mouseStyleDo.setHmid(mouseImgOneDoByMd5.getMid());
                    //更新此图标 信息
                    mouseImgOneDoByMd5.setUseCount(mouseImgOneDoByMd5.getUseCount()+1);//使用次数加一
                    mouseImgOneDoByMd5.setMouseType(mouseImgOneDoByMd5.getMouseType()==1?1:2);
                    mouseStyleService.updateMouseImgDo(mouseImgOneDoByMd5);
                }
            }


            if (mouseStyleDo.getPmid()==null&&mouseStyleDo.getHmid()==null){
                return JdhResult.success("鼠标无更改,无需保存！！！");
            }

            //保存鼠标&用户映射
            MouseStyleDo mouseStyleDoByUid = mouseStyleService.getMouseStyleDoByUid(currUser.getId());
            //映射存在
            if (mouseStyleDoByUid!=null){
               if(mouseStyleDo.getPmid()!=null) mouseStyleDoByUid.setPmid(mouseStyleDo.getPmid());
               if(mouseStyleDo.getHmid()!=null) mouseStyleDoByUid.setHmid(mouseStyleDo.getHmid());

               mouseStyleService.updateMouseStyleDo(mouseStyleDoByUid);


            }else {//映射不存在
                mouseStyleService.saveMouseStyleDo(mouseStyleDo);
            }

            String msg="保存鼠标成功！！！";
            if(isExistFile)msg+="部分图片为其他用户上传直接调用！";
            return JdhResult.success(msg);
        }catch (Exception e){
            e.printStackTrace();
            if(filePath1!=null)
                FileUtil.deleteFile(filePath1);

            if(filePath2!=null)
                FileUtil.deleteFile(filePath2);

            if(mid1!=null)
                mouseStyleService.deleteMouseImgDoByMid(mid1);
            if(mid2!=null)
                mouseStyleService.deleteMouseImgDoByMid(mid2);

            return JdhResult.fail("服务器错误,操作失败！！！");
        }

    }

//    /**
//     * 保存用户鼠标样式映射
//     * @param mouseStyleDo
//     * @return
//     */
//    @PostMapping("/mouseStyle/save")
//    public Integer saveMouseStyleDo(@RequestBody MouseStyleDo mouseStyleDo){
//        return mouseStyleService.saveMouseStyleDo(mouseStyleDo);
//    }
//
//
//    /**
//     * 更新用户鼠标样式
//     * @param mouseStyleDo
//     * @return
//     */
//    @PostMapping("/mouseStyle/update")
//    public Integer updateMouseStyleDo(@RequestBody MouseStyleDo mouseStyleDo){
//        return mouseStyleService.updateMouseStyleDo(mouseStyleDo);
//    }
}
