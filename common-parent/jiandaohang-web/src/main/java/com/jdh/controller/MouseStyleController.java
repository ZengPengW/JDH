package com.jdh.controller;

import com.jdh.feign.MouseStyleService;
import com.jdh.pojo.BackgroundImgDo;
import com.jdh.pojo.MouseImgDo;
import com.jdh.pojo.MouseStyleDo;
import com.jdh.pojo.MyUser;
import com.jdh.utils.FileUtil;
import com.jdh.utils.JdhResult;
import com.jdh.utils.PageDataGridResult;
import com.jdh.utils.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
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
                    BufferedImage bufferedImage= ImageIO.read(file1.getInputStream());
                    int width = bufferedImage.getWidth();
                    int height = bufferedImage.getHeight();
                  //  System.out.println("width:"+width+"  heigth:"+height);
                    if (width>33||height>33){
                        return JdhResult.fail("文件宽高不符合要求！！！");
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

                    BufferedImage bufferedImage= ImageIO.read(file2.getInputStream());
                    int width = bufferedImage.getWidth();
                    int height = bufferedImage.getHeight();
                   // System.out.println("width:"+width+"  heigth:"+height);
                    if (width>33||height>33){
                        return JdhResult.fail("文件宽高不符合要求！！！");
                    }
                }else {
                    fileName2=null;
                }

            }

            boolean isExistFile=false;//是否存在此图

            //指针文件不为空
            if(fileName1!=null){
                String fileMd5=FileUtil.getFileMd5(file1.getBytes());//获取文件MD5
                List<MouseImgDo>  mouseImgOneDoByMd5 = mouseStyleService.getMouseImgDoByMd5(fileMd5);//是否有此图
                if(mouseImgOneDoByMd5==null||mouseImgOneDoByMd5.size()<=0){//无此图
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
                    MouseImgDo mouseImgDo = mouseImgOneDoByMd5.get(0);
                    mouseStyleDo.setPmid(mouseImgDo.getMid());
                    //更新此图标 信息
                    if(mouseImgDo.getMouseType()==1){
                        mouseImgDo.setUseCount(mouseImgDo.getUseCount()+1);//使用次数加一
                        mouseImgDo.setMouseType(2);
                        mouseStyleService.updateMouseImgDo(mouseImgDo);
                    }


                }
            }



            //手指不为空
            if(fileName2!=null){
                String fileMd5=FileUtil.getFileMd5(file2.getBytes());//获取文件MD5
                List<MouseImgDo> mouseImgOneDoByMd5 = mouseStyleService.getMouseImgDoByMd5(fileMd5);//是否有此图
                if(mouseImgOneDoByMd5==null||mouseImgOneDoByMd5.size()<=0){//无此图
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
                    MouseImgDo mouseImgDo = mouseImgOneDoByMd5.get(0);
                    mouseStyleDo.setHmid(mouseImgDo.getMid());
                    //更新此图标 信息
                    //更新此图标 信息
                    if(mouseImgDo.getMouseType()==0){
                        mouseImgDo.setUseCount(mouseImgDo.getUseCount()+1);//使用次数加一
                        mouseImgDo.setMouseType(2);
                        mouseStyleService.updateMouseImgDo(mouseImgDo);
                    }

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


    /**
     * 查询我的上传图标
     * @param mouseType 0 指针 1 手指 or 2都可以
     * @return
     */
    @GetMapping("/mouseImg/myUpload/mouseType/{mouseType}")
    public  PageDataGridResult<MouseImgDo>  getMouseImgDoByMyUpload(@PathVariable("mouseType") Integer mouseType, @RequestParam(required = false,name = "page") Integer page, @RequestParam(required = false,name = "size")Integer size){
        try {
            MyUser currUser = UserContext.getCurrUser();
            if (page==null)page=1;
            if (size==null)size=18;
            PageDataGridResult<MouseImgDo> list = mouseStyleService.getMouseImgDoByMyUpload(mouseType, currUser.getId(), page, size);
            return list;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }
    /**
     *
     * * 查询共享图标
     * @param mouseType 0 指针 1 手指 or 2都可以
     *  share 是否共享 true false
     * @param page 第几页
     * @param size 多少条
     * @param field 数据库表字段名
     * @param order 排序规则 asc desc
     * @return
     */
    @GetMapping("/mouseImg/share")
    public PageDataGridResult<MouseImgDo> getMouseImgDoByPublic(@RequestParam("mouseType") Integer mouseType, @RequestParam(value = "page",required = false) Integer page,@RequestParam(value = "size",required = false) Integer size,@RequestParam(value = "field",required = false)String field, @RequestParam(value = "order",required = false)String order){
        //两个值必须同时存在 否则不排序
        if(isEmpty(field)||isEmpty(order)){
            field=null;
            order=null;
        }else {
            field=field.trim();
            order=order.trim();

            if((!field.equals("1")&&!field.equals("2"))||(!order.equals("1")&&!order.equals("2"))){
                field=null;
                order=null;
            }else {
                //1.根据上传时间排序 2.根据热度排序
                if(field.equals("1"))field="up_date";
                else field="use_count";

                //1.asc 2.desc
                if(order.equals("1"))order="asc";
                else order="desc";
            }


        }

        return mouseStyleService.getMouseImgDoByPublic(mouseType,true,page,size,field,order);
    }



    /**
     * 修改用户鼠标样式
     * @param mousePointer 鼠标指针MD5
     * @param mouseHand 鼠标手指MD5
     * @return
     */
    @PostMapping("/mouseStyle/change")
    public JdhResult updateMouseStyleDo(@RequestParam(value = "mousePointer",required = false) String mousePointer,@RequestParam(value ="mouseHand",required = false) String mouseHand){
        try {
            MyUser currUser = UserContext.getCurrUser();

            List<MouseImgDo> mousePointers =null;//指针
            List<MouseImgDo> mouseHands =null;//手指

          if (mousePointer!=null) mousePointers=mouseStyleService.getMouseImgDoByMd5(mousePointer);
          if (mouseHand!=null)  mouseHands=mouseStyleService.getMouseImgDoByMd5(mouseHand);
            if ((mousePointers==null||mousePointers.size()<=0)&&(mouseHands==null||mouseHands.size()<=0)){
                return  JdhResult.fail("修改鼠标图标失败！图标不存在！");
            }


            //保存鼠标&用户映射
            MouseStyleDo mouseStyleDo = mouseStyleService.getMouseStyleDoByUid(currUser.getId());
            //映射存在
            if (mouseStyleDo!=null){
                if (mousePointers!=null&&mousePointers.size()>0){
                    MouseImgDo mouseImgDo = mousePointers.get(0);
                    mouseStyleDo.setPmid(mouseImgDo.getMid());
                    mouseImgDo.setUseCount(mouseImgDo.getUseCount()==null?1:mouseImgDo.getUseCount()+1);
                    mouseStyleService.updateMouseImgDo(mouseImgDo);
                }

                if (mouseHands!=null&&mouseHands.size()>0){
                    MouseImgDo mouseImgDo2 = mouseHands.get(0);
                    mouseStyleDo.setHmid(mouseImgDo2.getMid());
                    mouseImgDo2.setUseCount(mouseImgDo2.getUseCount()==null?1:mouseImgDo2.getUseCount()+1);
                    mouseStyleService.updateMouseImgDo(mouseImgDo2);
                }


                mouseStyleService.updateMouseStyleDo(mouseStyleDo);


            }else {//映射不存在
                mouseStyleDo=new MouseStyleDo();
                mouseStyleDo.setUid(currUser.getId());
                if (mousePointers!=null&&mousePointers.size()>0){
                    MouseImgDo mouseImgDo = mousePointers.get(0);
                    mouseStyleDo.setPmid(mouseImgDo.getMid());
                }

                if (mouseHands!=null&&mouseHands.size()>0){
                    MouseImgDo mouseImgDo2 = mouseHands.get(0);
                    mouseStyleDo.setHmid(mouseImgDo2.getMid());
                }
                mouseStyleService.saveMouseStyleDo(mouseStyleDo);
            }
            return JdhResult.success("鼠标图标修改成功！");

        }catch (Exception e){
            e.printStackTrace();
            return  JdhResult.fail("服务器错误！修改鼠标图标失败！");

        }

    }




    @PostMapping("/mouseImg/del/md5/{md5}")
    public JdhResult deleteMouseImgByMd5(@PathVariable(name = "md5") String md5){
        try {
            MyUser currUser = UserContext.getCurrUser();
            if (!isEmpty(md5)){
                List<MouseImgDo> mouseImgDoByMd5 = mouseStyleService.getMouseImgDoByMd5(md5);

                if (mouseImgDoByMd5!=null&&mouseImgDoByMd5.size()>0){
                    MouseImgDo mouseImgDo = mouseImgDoByMd5.get(0);
                    Long mid = mouseImgDo.getMid();
                    List<MouseStyleDo> mouseStyleList = mouseStyleService.getMouseStyleDoByMid(mid);
                    if (mouseStyleList!=null&&mouseStyleList.size()>0){
                        return JdhResult.fail("此图标有用户正在使用，删除失败！");
                    }

                    if (currUser.getId()==mouseImgDo.getUid()){//此用户上传的就可以删除
                        mouseStyleService.deleteMouseImgDoByMid(mouseImgDo.getMid());//删除数据库记录
                        String mouseUrl = mouseImgDo.getMouseUrl();
                        mouseUrl=materialPath+mouseUrl.substring(10);
                        FileUtil.deleteFile(mouseUrl);//删除文件
                        return JdhResult.success("成功删除图标！！！");
                    }else {
                        return JdhResult.fail("此图不是你上传的，删除失败！");
                    }
                }else {
                    return JdhResult.fail("无此图，删除失败！");
                }
            }else {
                return JdhResult.fail("无此图，删除失败！");
            }
        }catch (Exception e){
            e.printStackTrace();
            return JdhResult.fail("服务器错误，删除失败！");
        }

    }




    /**
     * 是否为空
     * @param str
     * @return 是true 否 false
     */
    public boolean isEmpty(String str){
        if(str==null)return true;
        if(str.trim().length()<=0)return true;
        return false;
    }
}
